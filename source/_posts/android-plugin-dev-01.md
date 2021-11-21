---
title: Android插件化开发指南 | 2.15 实现一个音乐播放器APP
date: 2021年10月28日 10:53:46
comments: true
categories: "Android插件化开发"
tags: 
    - Android
---

欢迎访问：[Android插件化开发指南——2.15 实现一个音乐播放器APP](https://blog.csdn.net/qq_26460841/article/details/120992273)

# 1. 前言
最近对`Android`插件化开发比较感兴趣，也读了部分《`Android`插件化开发指南》这本书。在该书中`1.4`部分介绍了这么一句话：
> 我们曾天真地认为，Android插件化是为了增加新的功能，或者增加一个完整的模块。费了不少时间和精力，等项目实施了插件化后，我们才发现，**插件化80%的使用场景，是为了修复线上bug**。

我现在也粗浅的认为插件化是为了新增新的功能，至于修复线上`bug`这部分，确实还没有接触到或者说了解。希望后续自己能了解更多。

对于《`Android`插件化开发指南》这本书，我决定将其消化吸收了再整理博客笔记，因为确实写的比较好，有很多地方值得学习和借鉴。在这篇博客中，就简单的将书中`2.15`的例子展示出来，看看值得学习的点。

# 2. 实现一个音乐播放器APP
代码作者包老师也给出了源码地址，分别是：
- [ReceiverTestBetweenActivityAndService1](https://github.com/BaoBaoJianqiang/ReceiverTestBetweenActivityAndService1)；
- [ReceiverTestBetweenActivityAndService2](https://github.com/BaoBaoJianqiang/ReceiverTestBetweenActivityAndService2)；

感兴趣的可以直接看作者提供的源码。
## 2.1 设计思路一（一个Service和两个BroadcastReceiver）
- 音乐前台使用一个`Activity`来展示歌曲信息；
- 音乐后台播放，一般使用`Service`来实现；
- 用户交互至少需要提供开始和暂停按钮，无论点击那个按钮都是通知后台`Service`播放或者停止音乐；
- 当前音乐播放完毕，自动切换到下一首的时候，需要通知前台`Activity`；

所以这个案例中至少需要一个`Service`和两个`BroadcastReceiver`。

首先是在`MainActivity`中简单定义两个按钮，并为这两个按钮提供发送广播（发送广播到`Service`）的方法。

```clike
btnPlay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //send message to receiver in Service
        Intent intent = new Intent("UpdateService");  // 传入Action
        intent.putExtra("command", 1);
        sendBroadcast(intent);
    }
});

btnStop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //send message to receiver in Service
        Intent intent = new Intent("UpdateService");  // 传入Action
        intent.putExtra("command", 2);
        sendBroadcast(intent);
    }
});
```
同时还需要提供一个`BroadcastReceiver`用来接受从`Service`发来的播放结束的广播消息。

```clike
// 用来接受Service发来的消息
public class Receiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        status = intent.getIntExtra("status", -1);
        int current = intent.getIntExtra("current", -1);
        if (current >= 0) {
            tvTitle.setText(MyMusics.musics[current].title);
            tvAuthor.setText(MyMusics.musics[current].author);
        }

        switch (status) {
            case 0x11:
                btnPlay.setImageResource(R.drawable.play);
                break;
            case 0x12:
                btnPlay.setImageResource(R.drawable.pause);
                break;
            case 0x13:
                btnPlay.setImageResource(R.drawable.play);
                break;
            default:
                break;
        }
    }
}
```
同时还需要在`onCreate`方法后面完成对上面`Receiver1`的注册：

```clike
receiver1 = new Receiver1();
IntentFilter filter = new IntentFilter();
filter.addAction("UpdateActivity"); // 可以收到action=UpdateActivity
registerReceiver(receiver1, filter);
```

因为在播放按钮中我们并没有启动后台`Service`，所以还需要在`onCreate`的后面完成：

```clike
Intent intent = new Intent(this, MyService.class);
startService(intent);
```
对于启动的`MyService`服务，播放完毕后需要发送广播到`Activity`：

```clike
mPlayer = new MediaPlayer();
mPlayer.setOnCompletionListener(new OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mp) {
        current++;
        if (current >= 3) {
            current = 0;
        }
        prepareAndPlay(MyMusics.musics[current].name);

        //send message to receiver in Activity
        Intent sendIntent = new Intent("UpdateActivity");
        sendIntent.putExtra("status", -1);
        sendIntent.putExtra("current", current);
        sendBroadcast(sendIntent);
    }
});
```
为了接收从`Activity`中传入的信号（播放或者暂停），这里定义一个接收器：

```clike
public class Receiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        int command = intent.getIntExtra("command", -1);
        switch (command) {
            case 1:
                if (status == 0x11) {
                    prepareAndPlay(MyMusics.musics[current].name);
                    status = 0x12;
                }
                else if (status == 0x12) {
                    mPlayer.pause();
                    status = 0x13;
                }
                else if (status == 0x13) {
                    mPlayer.start();
                    status = 0x12;
                }
                break;
            case 2:
                if (status == 0x12 || status == 0x13) {
                    mPlayer.stop();
                    status = 0x11;
                }
        }

        //send message to receiver in Activity
        Intent sendIntent = new Intent("UpdateActivity");
        sendIntent.putExtra("status", status);
        sendIntent.putExtra("current", current);
        sendBroadcast(sendIntent);
    }
}
```
让媒体播放器执行相应的方法，并把状态更新，传递到`Activity`。

当然这个`Receiver2`也需要在这个`MyService`中注册，为：

```clike
receiver2 = new Receiver2();
IntentFilter filter = new IntentFilter();
filter.addAction("UpdateService");
registerReceiver(receiver2, filter);
```
## 2.2 设计思路二（一个Service和一个BroadcastReceiver）
上面的逻辑比较清晰，就是`A（Activity）`，`B（Service）`两个组件直接各自注册一个用来接收对方广播的接收器，然后收到消息后进行对应的逻辑处理。上面的流程可以简化为如下的图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/b7320c1124ce449191e901d30404fd95.png)

其实，很多音乐播放器只有一个`Receiver`，这是怎么实现的呢？

因为从上图可以看出，`MyActivity`和`MyService`的代码耦合度比较高，在学习设计模式中我们知道，为了解耦我们通常需要面向接口编程，而不是具体的实现。所以在《`Android`插件化开发指南》一书中作者使用了接口来进行解耦，从而替代从`Activity`向`Service`中发送广播的过程。如下图所示：![在这里插入图片描述](https://img-blog.csdnimg.cn/e7c41cd629f049e7abc45d5962b67292.png)
首先定义好接口：

```clike
public interface IServiceInterface {
    public void play();
    public void stop();
}
```
在`Activity`中定义一个`IServiceInterface`的实例：

```clike
IServiceInterface myService = null;

ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName name, IBinder binder) {
        myService = (IServiceInterface) binder;
        Log.e("MainActivity", "onServiceConnected");
    }

    public void onServiceDisconnected(ComponentName name) {
        Log.e("MainActivity", "onServiceDisconnected");
    }
};
```
这里使用`onBind`方式，因为在`ServiceConnection`回调中可以得到一个`IBinder`对象，而这个对象是在`Service`的`onBind`中返回的，所以我们可以返回一个实现了`IServiceInterface`接口的`IBinder`对象，那么就可以得到接口的实现。在`Activity`中对应的将按钮的响应事件定义为：

```clike
btnPlay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        myService.play();
    }
});

btnStop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        myService.stop();
    }
});
```
当然在`Activity`中对应的定义广播接收器不变，一样需要注册这个广播接收器，并在最后启动定义的`MyService`服务。和前面的保持一致，这里就不再给出。

对于`MyService`这个类，才是本次的重点，因为在这个类中需要提供在`onBind`之后的返回对象，而我们需要让这个对象是接口的实例，故而定义如下：

```clike
// MyService类的内部类
private class MyBinder extends Binder implements IServiceInterface {

    @Override
    public void play() {
        if (status == 0x11) {
            prepareAndPlay(MyMusics.musics[current].name);
            status = 0x12;
        } else if (status == 0x12) {
            mPlayer.pause();
            status = 0x13;
        } else if (status == 0x13) {
            mPlayer.start();
            status = 0x12;
        }

        sendMessageToActivity(status, current);
    }

    @Override
    public void stop() {
        if (status == 0x12 || status == 0x13) {
            mPlayer.stop();
            status = 0x11;
        }

        sendMessageToActivity(status, current);
    }
}
```
其余的和前面的保持一致，也就是在播放完毕后发送广播通知`Activity`。

# 3.仿一个音乐播放器
从上面的两个案例中，感觉确实案例二更加优美。所以接下来继续尝试在这个案例剖析的基础上进行拓展。决定以酷狗音乐为例，来进行仿写试试。当然，写的过程将持续记录到这篇博客中。

[Android启动页白屏/黑屏问题解决](https://blog.csdn.net/qq_26460841/article/details/121003811)；
[仿酷狗音乐启动页——Activity转场效果](https://blog.csdn.net/qq_26460841/article/details/121061646)
___
**Thanks**
- 《Android插件化开发指南》








