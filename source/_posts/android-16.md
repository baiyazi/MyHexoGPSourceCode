---
title: android | 广播
date: 2019-8-29 16:03:34
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>BroadcastReceiver

广播：`Android 组件（安卓有四大组件`Activity`、`ContentProvider`、`BroadcastReceiver`、`Service`）之间的通信方式。
使用了观察者模式，基于消息的发布/订阅事件模型。（解耦，易扩展）
流程：[原文地址](https://www.cnblogs.com/lwbqqyumidi/p/4168017.html)
1. 广播接收者`BroadcastReceiver`通过`Binder`机制向`AMS(Activity Manager Service)`进行注册；
2. 广播发送者通过`binder`机制向`AMS`发送广播；
3. `AMS`查找符合相应条件（`IntentFilter/Permission`等）的`BroadcastReceiver`，将广播发送到`BroadcastReceiver`（一般情况下是`Activity`）相应的消息循环队列中；
4. 消息循环执行拿到此广播，回调`BroadcastReceiver`中的`onReceive()`方法。

## 查看文档
文档地址：[广播接收器](https://developer.android.google.cn/reference/kotlin/android/content/BroadcastReceiver?hl=en)
首先是抽象类，其抽象方法只有一个就是`onReceive(Context context, Intent intent)`方法（当`BroadcastReceiver`接收`Intent`广播时调用此方法。`context`是上下文，`intent`是广播携带的数据）

## 分步骤实现
### 编写对应事件的BroadCastReveicer
继承`BroadCastReveicer`，复写`onReceive`方法。

### 注册到安卓系统
作为四大组件之一，就需要注册。有两种注册方式：
1. 静态注册（AndroidManifest.xml中）
``` xml
<receiver android:name=".myReceiver"
android:permission="如果设置，具有相应权限的广播发送方发送的广播才能被此broadcastReceiver所接收；">
      <intent-filter>
      	//要监听的广播类型
           <action android:name="android.intent.action.ACTION_SCREEN_OFF" />
           <action android:name="android.intent.action.ACTION_SCREEN_ON" />
       </intent-filter>
       <intent-filter>
       <!--下面是检测应用首次启动，就开始广播-->
        <action android:name="android.intent.action.BOOT_COMPLETED" />
       </intent-filter>
</receiver>
```

使用`Intent`对象启动广播，在`intent`过滤器中过滤，符合标准才调用。
2. 动态注册（代码中）
在代码中通过调用`Context`的`registerReceiver`函数，可以在程序中动态注册BroadcastReceiver。
``` java
public class MainActivity extends Activity {
	private MyBroadcastReceiver receiver ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerMyReceiver();
    }
    private void registerMyReceiver() {
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();//创建IntentFilter对象		
		//IntentFilter对象中添加要接收的关屏广播。对应Manifest.xml中的action
		filter.addAction(Intent.ACTION_SCREEN_OFF);
        //IntentFilter对象中添加要接收的点亮屏幕广播。对应Manifest.xml中的action
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
         if(receiver != null){
            unregisterReceiver(receiver);//Android中所有与观察者模式有关的设计中，一旦涉及到register，必定在相应的时机需要unregister。
        }
    }
}
```









###  差异

看看这两种方式有什么不同：
1. 静态注册 
静态注册依附于清单文件，只要`APP`启动过一次，所静态注册的广播就会生效，无论当前的`APP`处于停止使用还是正在使用状态。
只要相应的广播事件发生，系统就会遍历所有的清单文件，通知相应的广播接收者接收广播，然后调用广播接收者的`onReceiver`方法。
也就是无论你的程序是否关闭，都会接收到广播事件。这很好，像电量监听等。但是，也无疑很耗电、耗`CPU`等，故而在有些场合就不合适了。
手机的电量等资源是有限的，有些情况并不需要一直接收广播，故而我们也需要动态注册。

2. 动态注册 
动态注册方式依赖于所注册的组件，当`APP`关闭后，组件对象都不在了，动态注册的代码都不存在了，所动态注册监听的`action`自然不在生效。
如果一个`BroadcastReceiver`用于更新`UI`，那么通常会使用这种方法进行注册，建议在`Activity`启动时注册，在`Activity`不可见的时候取消注册。

3. 静态注册的广播传播速度要远远慢于动态注册的广播。

4. 动态注册的广播的优先级大于静态注册的广播。

5. 可同时注册。. 

### 案例
接下来一个简单的案例来理解广播。
先看布局文件，是一个按钮：
activity_main.xml
``` xml
<Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:text="发送消息给Receiver" />
```

然后看看自定义的myBroadcast.java文件：
```java
package com.weizu.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class myBroadcast extends BroadcastReceiver {
	//系统自调用，需要无参的构造函数
	public myBroadcast() {}
	
	//接收广播
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Log.i("onReceive", "调用了");
	}
}
```

再然后就是在manifest.xml文件中注册一下：
``` xml
<receiver android:name=".myBroadcast" >
            <intent-filter>
                <action android:name="android.intent.action.EDIT" />
            </intent-filter>
</receiver>
```

由于是四大组件之一，故而和`activity`平级放置。

最后就是我们的MainActivity.java文件：
``` java
btn = (Button)findViewById(R.id.btn);
btn.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View arg0) {
			//发送广播
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_EDIT);
			MainActivity.this.sendBroadcast(intent);
		}
	});
```

运行结果：
在出现的按钮上点击，然后用LogCat可以看到输出的日志。
![](/images/201909/2019-09-07_094613.png)


到此，也就简单完成了一个广播案例。

