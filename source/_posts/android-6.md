---
title: android | 控件
date: 2019-8-12 18:12:03
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

>包括ProgressBar和ListView

# ProgressBar
看见`ProgressBar`，于是百度了一下，很多博客。大部分都是定制自己的进度条，都觉得系统的比较丑。这里还是先介绍系统自带的。
照例，先来张图片：
![](/images/201908/2019-08-12_180831.png)

看看布局文件：
``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ProgressBar
        android:id="@+id/pb1"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="100" />

    <ProgressBar
        android:id="@+id/pb2"
        style="?android:attr/progressBarStyle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="开始" />

</LinearLayout>
```


上面我设置水平进度条的总长度为100；在java代码中设置值得函数是`setProgress(int)`，下面就使用这个函数，在`MainActivity`中：
``` java
package com.weizu.intent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressBar pb1;
	private ProgressBar pb2;
	private Button start;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pb1 = (ProgressBar) findViewById(R.id.pb1);
		pb2 = (ProgressBar) findViewById(R.id.pb2);
		start = (Button) findViewById(R.id.start);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 设置进度条的值
				i += 10;
				if (i == 100) {
					Toast.makeText(MainActivity.this, "Done", 50).show();
				} else {
					pb1.setProgress(i);
				}
			}
		});

	}
}
```

然后就可以实现点击一个按钮，进度条就走10%。

# ListView
同样的，`ListView`也是比较牛皮，可定制性比较强，这里也还是介绍基础的用法。
## 先来个最简答的用法
原文地址：[地址](https://blog.csdn.net/wangjun5159/article/details/51761621)
按照博客干就是了，先给出效果图：
![](/images/201908/2019-08-12_184255.png)


布局文件：
``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ListView>

</LinearLayout>
```


`MainActivity`文件中：
``` java
package com.weizu.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (ListView)findViewById(R.id.list);
		String[] data = {"apple", "banana", "orange", "water"};
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		list.setAdapter(adapter);
		
		
	}	
}

```

---

接着我们看看定制的，当然也是简单的定制`ListView`。
按照上面链接的博客案例，我们可以仿一个`QQ`的列表。
[参考博客](https://blog.csdn.net/lw_android1/article/details/70170950)
在网上找了下面的图片：
![](/images/201908/timg2019-8-12.jpg)

跟着上面的博客做，效果如下：
![](/images/201908/2019-08-14_152852.png)

先做布局文件activity_main.xml：
``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ListView>

</LinearLayout>
```

然后是每一个列表对应的布局文件`item.xml`：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal" >

    <ImageView
        android:id="@+id/image"
        android:layout_width="65dp"
        android:layout_height="65dp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="65dp"
        android:orientation="vertical" >

        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content" >

            <TextView
                android:id="@+id/title"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"  //相对位置的布局设置
                android:paddingLeft="10dp"
                android:paddingTop="5dp"
                android:text="博导"
                android:textSize="18sp" />

            <TextView
                android:id="@+id/date"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentRight="true"
                android:padding="10dp"
                android:text="13:20"
                android:textColor="#606060" />

            <TextView
                android:id="@+id/subTitle"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:layout_below="@+id/date"  //在谁下面
                android:paddingLeft="10dp"
                android:paddingBottom="10dp"
                android:text="说些声明"
                android:textColor="#606060" />
        </RelativeLayout>
    </LinearLayout>

</LinearLayout>
```

item.xml确定了，也就确定了所需要的实体中的数据的个数。
需要定义一个数据类`item.java`，存放`item`中的四个数据。
``` java
package com.weizu.intent;

public class item {
	private String title;
	private String subTitle;
	private String date;
	private int imageId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public item(String title, String subTitle, String date, int imageId) {
		super();
		this.title = title;
		this.subTitle = subTitle;
		this.date = date;
		this.imageId = imageId;
	}
}
```

然后根据这个实体类，定义对应的适配器`adapter.java`：
``` java
package com.weizu.intent;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adapter extends ArrayAdapter<item> {
	private final int resourceId;
	//值得注意的是，这里装入了数据列表，我们不妨看看上面使用系统自带item布局创建的时候，ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);也是传入了数据data
	public adapter(Context context, int textViewResourceId, List<item> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		item item = (item) getItem(position); // 获取当前项的item实例
		//使用Inflater对象来将布局文件解析成一个View
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        //找到控件
        ImageView image = (ImageView) view.findViewById(R.id.image);//获取该布局内的图片视图
        TextView title = (TextView) view.findViewById(R.id.title);//获取该布局内的文本视图-title
        TextView subtitle = (TextView) view.findViewById(R.id.subTitle);//获取该布局内的文本视图-subtitle
        TextView date = (TextView) view.findViewById(R.id.date);//获取该布局内的文本视图-date
        //设置控件资源
        image.setImageResource(item.getImageId());//为图片视图设置图片资源
        title.setText(item.getTitle());//为文本视图设置文本内容-title
        subtitle.setText(item.getSubTitle());//为文本视图设置文本内容-subtitle
        date.setText(item.getDate());//为文本视图设置文本内容-date

		
		return view;
	}

}
```

适配器的构造对象中，可以传入的数据对象还是挺多的，看看下图：
![](/images/201908/2019-08-14_154222.png)

从上图可以看出，可以传入数组，而且前面已经有传入数组的案例；
可以传入List封装的对象，比如这样的：`ArrayList<HashMap<String, String>>`
在`getView`中使用`item item = (item) getItem(position);` 来获取每一个具体的List中的数据内容。


最后就是按照步骤在`MainActivity.java`中创建列表：
* 第一步：找到主布局文件`ListView`控件
* 第二部：生成分装数据的列表对象
* 第三部：创建适配器对象
* 第四部：关联


```java
package com.weizu.intent;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	private List<item> datalist = new ArrayList<item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//第一步：找到主布局文件ListView控件
		ListView listView = (ListView) findViewById(R.id.list);
		//第二部：生成分装数据的列表对象
		datalist.add(new item("群助手", "百度经验-导师:呵呵", "13:58", R.drawable.avatar));
		datalist.add(new item("安卓客服", "新手-次奥:牛皮", "15:08", R.drawable.ic_launcher));
		datalist.add(new item("吴越", "添上天地:欢迎你，呵呵", "16:20", R.drawable.avatar));
		
		//第三部：创建适配器对象
        adapter adapter = new adapter(MainActivity.this, R.layout.item, datalist);
        //第四部：关联
        listView.setAdapter(adapter);

//		list = (ListView)findViewById(R.id.list);
//		String[] data = {"apple", "banana", "orange", "water"};
//		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//		list.setAdapter(adapter);
	
	}	
}
```



