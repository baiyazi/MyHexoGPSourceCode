---
title: android | 控件
date: 2019-8-12 17:40:20
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

>比较常见的控件：
RadioGroup和RadioButton、CheckBox、Toast


先上案例截图：
![](/images/201908/2019-08-12_173538.png)

再看看布局文件：

``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="20dp"
            android:text="性别："
            android:textSize="18sp" />

        <RadioGroup
            android:id="@+id/sex"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:paddingTop="17dp" >

            <RadioButton
                android:id="@+id/man"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="男"
                android:textSize="16sp" />

            <RadioButton
                android:id="@+id/woman"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:text="女"
                android:textSize="16sp" />
        </RadioGroup>
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="20dp"
            android:text="爱好："
            android:textSize="18sp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal" >

            <CheckBox
                android:id="@+id/zq"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:text="足球"
                android:textSize="16sp" />

            <CheckBox
                android:id="@+id/ymq"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:text="羽毛球"
                android:textSize="16sp" />
        </LinearLayout>
    </LinearLayout>
	
</LinearLayout>
```


然后就是顺风顺水的注册监听函数；不过需要注意的是：
1. 单选监听的注册时注册到组；多选直注册到每一个具体的控件
2. 两个监听都是`OnCheckedChangeListener`接口，但是所属不同的类
3. 分别是：`RadioGroup.OnCheckedChangeListener`和`CompoundButton.OnCheckedChangeListener`

下面看看`MainActivity`
``` java
package com.weizu.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	private RadioGroup sex;
	private RadioButton man;
	private RadioButton woman;
	private CheckBox zq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sex = (RadioGroup) findViewById(R.id.sex);
		man = (RadioButton) findViewById(R.id.man);
		woman = (RadioButton) findViewById(R.id.woman);
		
		//单选监听注册到组
		sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				// checkedId
				// 举例man：R.id.man对应的0x7f070001，然后转换成十进制2131165185，Toast一下可以看见checkedId
				if (man.getId() == checkedId) {
					Toast.makeText(MainActivity.this, man.getText().toString(),
							200).show();
				} else {
					Toast.makeText(MainActivity.this,
							woman.getText().toString(), 200).show();
				}
			}
		});
		
		
		//多选，监听注册到自己,举一个例子
		zq = (CheckBox)findViewById(R.id.zq);
		zq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				if(isChecked){
					Toast.makeText(MainActivity.this,
							zq.getText().toString(), 200).show();
				}
			}
		});
		
	}
}
```

