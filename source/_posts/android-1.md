---
title: android | activity中Intent
date: 2019-8-9 09:25:10
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

>主要涉及两点，如何启动`Activity`；如何传递数据；

官方文档地址：[地址链接](https://developer.android.google.cn/guide/components/fundamentals.html)

# 应用组件
应用组件是 `Android` 应用的基本构建基块。每个组件都是一个不同的点，系统可以通过它进入您的应用。 并非所有组件都是用户的实际入口点，有些组件相互依赖，但每个组件都以独立实体形式存在，并发挥特定作用 — 每个组件都是唯一的构建基块，有助于定义应用的总体行为。
共有四种不同的应用组件类型。每种类型都服务于不同的目的，并且具有定义组件的创建和销毁方式的不同生命周期。
以下便是这**四种应用组件**类型：
1. **Activity**
`Activity `表示具有用户界面的单一屏幕。例如，电子邮件应用可能具有一个显示新电子邮件列表的` Activity`、一个用于撰写电子邮件的 `Activity` 以及一个用于阅读电子邮件的 `Activity`。 尽管这些` Activity `通过协作在电子邮件应用中形成了一种紧密结合的用户体验，但每一个 `Activity` 都独立于其他 `Activity `而存在。 因此，其他应用可以启动其中任何一个 `Activity`（如果电子邮件应用允许）。 例如，相机应用可以启动电子邮件应用内用于撰写新电子邮件的 `Activity`，以便用户共享图片。
2. **服务（Service）**
服务是一种在后台运行的组件，用于执行长时间运行的操作或为远程进程执行作业。 服务不提供用户界面。 例如，当用户位于其他应用中时，服务可能在后台播放音乐或者通过网络获取数据，但不会阻断用户与` Activity` 的交互。 诸如 `Activity` 等其他组件可以启动服务，让其运行或与其绑定以便与其进行交互。
3. **内容提供程序（ContentProvider）**
内容提供程序管理一组共享的应用数据。您可以将数据存储在文件系统、`SQLite` 数据库、网络上或您的应用可以访问的任何其他永久性存储位置。 其他应用可以通过内容提供程序查询数据，甚至修改数据（如果内容提供程序允许）。 例如，`Android` 系统可提供管理用户联系人信息的内容提供程序。 因此，任何具有适当权限的应用都可以查询内容提供程序的某一部分`（如 ContactsContract.Data）`，以读取和写入有关特定人员的信息。
4. **广播接收器（BroadcastReceiver）**
广播接收器是一种用于响应系统范围广播通知的组件。 许多广播都是由系统发起的 — 例如，通知屏幕已关闭、电池电量不足或已拍摄照片的广播。应用也可以发起广播 — 例如，通知其他应用某些数据已下载至设备，并且可供其使用。 尽管广播接收器不会显示用户界面，但它们可以创建状态栏通知，在发生广播事件时提醒用户。 但广播接收器更常见的用途只是作为通向其他组件的“通道”，设计用于执行极少量的工作。 例如，它可能会基于事件发起一项服务来执行某项工作。并且每条广播都作为` Intent` 对象进行传递。

`Android` 系统设计的独特之处在于，任何应用都可以启动其他应用的组件。
由于系统在单独的进程中运行每个应用，且其文件权限会限制对其他应用的访问，因此您的应用无法直接启动其他应用中的组件， 但 `Android` 系统却可以。因此，要想启动其他应用中的组件，您必须向系统传递一则消息，说明您想启动特定组件的 `Intent`。 系统随后便会为您启动该组件。

# 启动组件
四种组件类型中的三种 — `Activity`、服务和广播接收器 — 通过名为 `Intent `的异步消息进行启动。`Intent `会在运行时将各个组件相互绑定（您可以将` Intent` 视为从其他组件请求操作的信使），无论组件属于您的应用还是其他应用。

`Intent `使用` Intent` 对象创建，它定义的消息用于启动特定组件或特定类型的组件 — `Intent `可以是显式的，也可以是隐式的。
每种类型的组件有不同的启动方法：
* 您可以通过将` Intent` 传递到 `startActivity()` 或` startActivityForResult()`（当您想让 `Activity `返回结果时）来启动 `Activity`（或为其安排新任务）。
* 您可以通过将　`Intent` 传递到` startService() `来启动服务（或对执行中的服务下达新指令）。 或者，您也可以通过将 `Intent` 传递到` bindService() `来绑定到该服务。
* 您可以通过将 `Intent` 传递到` sendBroadcast()`、`sendOrderedBroadcast()` 或 `sendStickyBroadcast()` 等方法来发起广播；
* 您可以通过在` ContentResolver` 上调用 `query()` 来对内容提供程序执行查询。

# Intent
文档地址：[地址](https://developer.android.google.cn/guide/components/intents-filters.html)
Intent 是一个消息传递对象，您可以使用它从其他应用组件请求操作。尽管` Intent` 可以通过多种方式促进组件之间的通信，但其基本用例主要包括以下三个：
* **启动 Activity**：
`Activity` 表示应用中的一个屏幕。通过将` Intent `传递给 `startActivity()`，您可以启动新的` Activity` 实例。Intent 描述了要启动的` Activity`，并携带了任何必要的数据。
如果您希望在` Activity` 完成后收到结果，请调用` startActivityForResult()`。在 Activity 的 `onActivityResult() `回调中，您的 `Activity `将结果作为单独的` Intent `对象接收。如需了解详细信息，请参阅` Activity` 指南。

* **启动服务**：
`Service` 是一个不使用用户界面而在后台执行操作的组件。通过将` Intent` 传递给 `startService()`，您可以启动服务执行一次性操作（例如，下载文件）。`Intent `描述了要启动的服务，并携带了任何必要的数据。
如果服务旨在使用客户端-服务器接口，则通过将` Intent` 传递给` bindService()`，您可以从其他组件绑定到此服务。如需了解详细信息，请参阅服务指南。

* **传递广播**：
广播是任何应用均可接收的消息。系统将针对系统事件（例如：系统启动或设备开始充电时）传递各种广播。通过将 `Intent` 传递给 `sendBroadcast()`、`sendOrderedBroadcast()` 或 `sendStickyBroadcast()`，您可以将广播传递给其他应用。


`Intent` 对象携带了` Android` 系统用来确定要启动哪个组件的信息（例如，准确的组件名称或应当接收该` Intent` 的组件类别），以及收件人组件为了正确执行操作而使用的信息（例如，要采取的操作以及要处理的数据）。

在`Activity`之间传递数据，就涉及一个对象`Intent`，它包含了一组信息：
* `Component name`(要启动的组件名称。)；
* `Action`（指定要执行的通用操作（例如，“查看”或“选取”）的字符串。）；
* `Data`（引用待操作数据和/或该数据 MIME 类型的 URI（Uri 对象）。）；
* `Category`（一个包含应处理 Intent 组件类型的附加信息的字符串。）；
* `Extras`（携带完成请求操作所需的附加信息的键值对。）；
* `Flags`（在 Intent 类中定义的、充当 Intent 元数据的标志。 标志可以指示 Android 系统如何启动 Activity，以及启动之后如何处理）；


## 案例一：启动同一应用Activity
`MainActivity.java`
``` java
package com.weizu.intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View arg0) {
        		// TODO Auto-generated method stub
        		Intent intent = new Intent();
        		intent.putExtra("text", "这是传递的数据");
        		intent.setClass(MainActivity.this, OtherActivity.class);
        		MainActivity.this.startActivity(intent);
        	}
        });
    }
}
```

`OtherActivity.java`
``` java
package com.weizu.intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends Activity {
	private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
       
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        
        textView = (TextView)findViewById(R.id.text);
        textView.setText((String)bundle.get("text"));
        
    }
}
```

对应的`layout`布局文件，`res/layout/activity_main.xml`
``` xml
<Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/btn"
        android:text="@string/btn"
        />
```

`activity_other.xml`
``` xml
<TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:text="@string/other" />
```

当然，引用的`values`字符串定义新增如下：
`values/string.xml`
```xml
<string name="other">Other Activity</string>
<string name="btn">Go</string>
```

最后，在资源配置文件中需要声明我们新建的`Activity`：
`AndroidManifest.xml`
``` xml
<activity
            android:name="com.weizu.intent.OtherActivity"
            android:label="@string/other" />
```


下面展示一下案例的效果：
![](/images/201908/intent_1.gif)


当然了，既然有启动同一应用的`Activity`，那么也就有：
## 案例二：启动不同应用Activity
MainActivity.java
``` java
package com.weizu.intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View arg0) {
        		// TODO Auto-generated method stub
        		Uri uri = Uri.parse("smsto://329544235");
        		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        		intent.putExtra("sms_body", "这是发送的数据");
        		MainActivity.this.startActivity(intent);
        	}
        });
    }
}
```

效果展示：
![](/images/201908/intent_2.gif)