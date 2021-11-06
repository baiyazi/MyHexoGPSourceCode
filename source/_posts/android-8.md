---
title: android | handler
date: 2019-8-14 16:11:28
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

>首先百度一下什么是Handler，[地址1](https://blog.csdn.net/nmwwbreed/article/details/79485773)   [地址2](https://blog.csdn.net/bobo8945510/article/details/51864102)
Handler是Android SDK来处理异步消息的核心类。 
子线程与主线程通过Handler来进行通信。子线程可以通过Handler来通知主线程进行UI更新。
**使用原因：**
主线程无法进行时间比较繁长的任务，所以需要子线程进行处理，然而子线程无法进行UI的界面更新，所以我们需要使用handler来传递消息给主线程，让其完成UI的更新。由于主线程和子线程进行不同的时间工作，所要需要用MessageQueue用来保存子线程从Handler所发送未处理的消息，Looper依次取出MessageQueue中的消息传递给主线程响应处理。
**Android为什么要设计只能用handler机制更新UI呢？**
答：最根本的目的就是为了解决多线程并发的问题！
打个比方，如果在一个activity中有多个线程，并且没有加锁，就会出现界面错乱的问题。但是如果对这些更新UI的操作都加锁处理，又会导致性能下降。
处于对性能的问题考虑，Android给我们提供这一套更新UI的机制我们只需要遵循这种机制就行了。不用再去关系多线程的问题，所有的更新UI的操作，都是在主线程的消息队列中去轮训的。



# Message的对象
查阅文档，找到了Handler对象的API，[地址](https://developer.android.google.cn/reference/android/os/Handler.html?hl=en)
这里，我们先看[Message](https://developer.android.google.cn/reference/android/os/Message.html?hl=en)对象。
Defines a message containing a description and arbitrary data object that can be sent to a Handler. This object contains two extra int fields and an extra object field that allow you to not do allocations in many cases.
定义了一种包含一个描述和专门的数据对象的信息，该信息可以被发送到Handler。
这个对象包含了两个额外的int字段和一个额外的object字段，在许多情况下，它们允许你不进行分配。
1. what属性： 
int类型，主线程用来识别子线程发来的是什么消息。
2. arg1属性： 
int类型，如果传递的消息类型为int型，可以将数字赋给arg1,arg2。
3. obj属性： 
Objectt类型，如果传递的消息是String或者其他，可以赋给obj。


## 案例：更新进度条
案例效果：
![](/images/201908/handler_1.gif)

先看看布局文件，activity_main.xml：
``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ProgressBar
        style="?android:attr/progressBarStyleHorizontal"
        android:max="100"
		android:id="@+id/progressbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ProgressBar>
    <Button 
        android:layout_width="match_parent"
        android:layout_height="wrap_content" 
        android:text="开始"
        android:id="@+id/btn"
        />

</LinearLayout>
```

然后，就是MainActivity.java的逻辑处理，我参考的是mars的视频，然后总觉得他的案例差了些什么，然后就自己百度了一个[案例](https://blog.csdn.net/nmwwbreed/article/details/79485773)，杂糅了一下，便于自己理解。
我觉得，线程就应该只是调用一次，而视频中却是多次post，多次加入消息队列，就反复调用了一样，总感觉很别扭。（当然，可能理解有误）
MainActivity.java：
``` java
package com.weizu.intent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity{
	private Button btn;
	private ProgressBar progressBar;
	private int i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button)findViewById(R.id.btn);
		progressBar = (ProgressBar)findViewById(R.id.progressbar);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						while(i<=100){
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							Message message = handler.obtainMessage();
							message.what = 1;
							message.arg1 = i;
							handler.sendMessage(message);
							i+=10;
						}
						if(i==100){
							handler.removeCallbacks(this);
						}
					}
				}).start();
			}
		});
	}
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int what = msg.what;
			int arg1 = msg.arg1;
			Log.i("weizu", what + " || "+ arg1);
			if(msg.what == 1){
				progressBar.setProgress(msg.arg1);
			}
		}
	};
}
```