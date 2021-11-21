---
title: android | SQLite初使用
date: 2019-8-17 15:38:17
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>按照惯例，百度一下：[地址](https://www.cnblogs.com/foxy/p/7725010.html)  [官网地址](http://www.sqlite.org)
`SQLite`是一款轻型的数据库，是遵守`ACID`的关联式数据库管理系统，它的设计目标是嵌入  式的，而且目前已经在很多嵌入式产品中使用了它，它占用资源非常的低，在嵌入式设备中，可能只需要几百K的内存就够了。它能够支持` Windows/Linux/Unix`等等主流的操作系统，同时能够跟很多程序语言相结合，比如`Tcl`、`PHP`、`Java`、`C++`、`.Net`等，还有`ODBC`接口，同样比起 `Mysql`、`PostgreSQL`这两款开源世界著名的数据库管理系统来讲，它的处理速度比他们都快。

## 特点
* 轻量级
`SQLite`和`C/S`模式的数据库软件不同，它是进程内的数据库引擎，因此不存在数据库的客户端和服务器。使用`SQLite`一般只需要带上它的一个动态库，就可以享受它的全部功能。而且那个动态库的尺寸也挺小，以版本3.6.11为例，`Windows`下`487KB`、`Linux`下`347KB`。

* 不需要"安装"
`SQLite`的核心引擎本身不依赖第三方的软件，使用它也不需要"安装"。有点类似那种绿色软件。

* 单一文件  
数据库中所有的信息（比如表、视图等）都包含在一个文件内。这个文件可以自由复制到其它目录或其它机器上。

* 跨平台/可移植性
除了主流操作系统 `windows`，`linux`之后，`SQLite`还支持其它一些不常用的操作系统。

* 弱类型的字段
同一列中的数据可以是不同类型

* 开源

## 数据类型
`SQLite`采用的是动态数据类型，会根据存入值自动判断。
<table><tr>
	<td>数据类型</td>
	<td>描述</td>
  </tr>
<tr><td>NULL</td><td>这个值为空值</td></tr>
<tr><td>VARCHAR(n)</td><td>长度不固定且其最大长度为 n 的字串，n不能超过 4000。</td></tr>
<tr><td>CHAR(n)</td><td>长度固定为n的字串，n不能超过 254。</td></tr>
<tr><td>INTEGER</td><td>值被标识为整数,依据值的大小可以依次被存储为1,2,3,4,5,6,7,8.</td></tr>
<tr><td>REAL </td><td>所有值都是浮动的数值,被存储为8字节的IEEE浮动标记序号.</td></tr>
<tr><td>TEXT</td><td>值为文本字符串,使用数据库编码存储(TUTF-8, UTF-16BE or UTF-16-LE).</td></tr>
<tr><td>BLOB</td><td>值是BLOB数据块，以输入的数据格式进行存储。如何输入就如何存储,不改  变格式。</td></tr>
<tr><td>DATA</td><td>包含了 年份、月份、日期。</td></tr>
<tr><td>TIME </td><td>包含了 小时、分钟、秒。</td></tr>
</table>

## SQLite创建和打开数据库的简单使用
### 方式一：继承SQLiteOpenHelper
继承 [SQLiteOpenHelper](https://developer.android.google.cn/reference/android/database/sqlite/SQLiteOpenHelper?hl=en) 打开或创建数据库。（打开链接，可以看见这是一个抽象类，故而我们需要继承它）

按照`Mars`视频讲解，这里我先看能否创建成功数据库。
先写一个类`DatabaseHelper`继承`SQLiteOpenHelper`，`DatabaseHelper.java`：
``` java
package com.weizu.intent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	//Create a helper object to create, open, and/or manage a database.
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	//Called when the database is created for the first time.
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table weizu(id int, name varchar(20), age int)");
	}

	//Called when the database needs to be upgraded.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
```

然后在`MainActivity.java`中简单创建表：
布局文件就是一个`Button`，这里就不给出了。
`MainActivity.java`
``` java
package com.weizu.intent;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.weizu.intent.DatabaseHelper;

public class MainActivity extends Activity{
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button)findViewById(R.id.btn);	
		btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				DatabaseHelper helper = new DatabaseHelper(MainActivity.this, "weizu", null, 1);
				//Create and/or open a database.
				SQLiteDatabase db = helper.getReadableDatabase();
			}
		});
	}
}
```

运行就是一个按钮，当我们点击了按钮后，按照逻辑应该创建一个表`weizu`。接着就看看表的位置：
使用`adb`工具，我这里没有配置到环境变量，故而直接在我安装的路径的`SDK`的文件夹中的`tools`中找到`adb.exe`。按住`Shift`键，然后右键，在当前文件夹中打开`cmd`命令窗口：
输入`adb shell`，进入安卓的文件系统，如图：
![](/images/201908/2019-08-17_171209.png)

然后，切换目录到运行的应用目录，我这里是`data/data/com.weizu.intent`，就可以看见`databases`文件夹，我们进入，就可以看见创建的数据库`weizu`：
![](/images/201908/2019-08-17_171535.png)

不妨输入命令`sqlite3 weizu`，进入，然后可以看见表weizu：
![](/images/201908/2019-08-17_171800.png)


这个博客写得很全面：[地址](https://blog.csdn.net/guyuelin123/article/details/60957305)
当然，要操作数据库，需要先了解`SQLiteDatabase`。[SQLiteDatabase](https://developer.android.google.cn/reference/android/database/sqlite/SQLiteDatabase.html) `has methods to create, delete, execute SQL commands, and perform other common database management tasks.`


### 方式二：Context.openOrCreateDatabase
先找文档，看看这个函数的说明：
``` text
abstract SQLiteDatabase!	
openOrCreateDatabase(name: String!, mode: Int, factory: SQLiteDatabase.CursorFactory!)
```

`Open a new private SQLiteDatabase associated with this Context's application package.`
<table>
<tr><td>name: </td><td>The name (unique in the application package) of the database.</td></tr>
<tr><td>mode: </td><td>Operating mode. Value is either 0 or a combination of android.content.Context#MODE_PRIVATE, android.content.Context#MODE_WORLD_READABLE, android.content.Context#MODE_WORLD_WRITEABLE, android.content.Context#MODE_ENABLE_WRITE_AHEAD_LOGGING, and android.content.Context#MODE_NO_LOCALIZED_COLLATORS</td></tr>
<tr><td>factory: </td><td>An optional factory class that is called to instantiate a cursor when query is called.</td></tr>
</table>

这里就是简单的改一下上面的按钮监听事件如下：
``` java
btn.setOnClickListener(new View.OnClickListener(){
	@Override
	public void onClick(View arg0) {
		SQLiteDatabase db = MainActivity.this.openOrCreateDatabase("intent.db", MODE_WORLD_WRITEABLE, null);
	}
});
```

我这里还是按照上面的方法，查看一下，如下图：
![](/images/201908/2019-08-18_111142.png)



