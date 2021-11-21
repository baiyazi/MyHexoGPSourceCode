---
title: android | 广播
date: 2019-9-7 15:52:43
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
直接说案例：
布局文件：
``` xml
<EditText
        android:id="@+id/vaild_code"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:hint="内容" />
    <Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="发送消息给Receiver" />
```

然后就是在manifest.xml文件中注册接收短信和阅读短信的权限：
```xml
<uses-permission android:name="android.permission.RECEIVE_SMS"/>
<uses-permission android:name="android.permission.READ_SMS"/>
```

接着就是编写对应的广播处理类myBroadcast.java：
``` java
package com.weizu.broadcast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;

public class myBroadcast extends BroadcastReceiver {
	private EditText text;
	private int codeLength = 6;
	//空构造是必须的
	public myBroadcast(){}
	//含参构造，传入自己需要的参数
	public myBroadcast(EditText text){
		this.text = text;
	}
	
	@Override
	public void onReceive(Context arg0, Intent intent) {
		Bundle bundle = intent.getExtras();
        if(bundle != null){         
            Object[] datas = (Object[]) bundle.get("pdus");
            for(Object data : datas){
                byte[] sms = (byte[]) data;
                SmsMessage message = SmsMessage.createFromPdu(sms);  
                // 发送人号码  
                String number = message.getOriginatingAddress(); 
                // 短信内容  
                String content = message.getMessageBody();  
                // 发送时间  
                Date date = new Date(message.getTimestampMillis());  
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);  

                Log.e("Receiver", "发送号码：" + number + 
                        " 发送内容：" + content + 
                        " 发送时间" + time); 
                this.checkCodeAndSend(content);
            }
        }
	}
	
	private void checkCodeAndSend(String content) {
		// 正则表达式验证
		Pattern pattern = Pattern.compile("\\d{" + codeLength + "}");
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			String code = matcher.group(0);
			//直接设置
			text.setText(code);
		} else {
			Log.e("sms", "短信中没有找到符合规则的验证码");
		}
	}
}
```

接着就是在mainActivity.java文件中，为按钮添加监听，然后就是注册我们的广播监听：
``` java
package com.weizu.broadcast;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	private Button btn;
	private int codeLength = 6;
	private EditText text;
	private myBroadcast receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text = (EditText)findViewById(R.id.vaild_code);
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				receiver = new myBroadcast(text);
				IntentFilter filter = new IntentFilter();
				filter.addAction("android.provider.Telephony.SMS_RECEIVED");
				registerReceiver(receiver, filter);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}
```

效果：
![](/images/201909/smsReceiver.gif)

这个案例和上一个案例的不同之处就在与这里使用的是系统的广播，而是不用户自己的行为广播。那么Intent对象有哪些广播呢，我们在文档中看看：


