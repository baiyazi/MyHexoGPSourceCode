---
title: android | Content Provider初识
date: 2019-8-21 21:47:51
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>mars视频19集，讲解Content Provider，按照惯例先百度一下它是什么？有什么用？
其实，在android第一讲中也简略提到过：**内容提供程序管理一组共享的应用数据。您可以将数据存储在文件系统、SQLite 数据库、网络上或您的应用可以访问的任何其他永久性存储位置。 其他应用可以通过内容提供程序查询数据，甚至修改数据（如果内容提供程序允许）。**
简而言之：**Android允许一个程序访问另一个程序的数据，并且保证被访问数据的安全性。主要是通过Content Provider来实现的。**

官方文档：[地址](https://developer.android.google.cn/guide/topics/providers/content-providers?hl=en) 
`Android` 四大组件之一
## 功能
1. `Content Provider`提供了存储和获取数据的同一接口。
2.	使用`ContentProvider`可以在不同的应用程序之间共享数据。（如：前面讲的`SQLite`它只能用于当前的应用程序，如果我们需要暴露本应用程序的存储数据的接口，就需要用到`ContentProvider`来实现。） 
3. 提供了定义数据安全性的机制。
4. `Android`为一些常见的数据（如：音频、视频、图片和通讯录等）提供了`Content Provider`。（就可以直接用`Provider`来访问数据，而不关心具体的存储数据的细节。）

## 作用
借张图：[地址](https://www.runoob.com/android/android-content-providers.html)
![](/images/201908/content.jpg)
从上图中可以看出，主要的功能：
1. 应用程序间通信，即跨进程通信（每个程序处于不同进程）。
2. `ContentProvider`相当于第三方搬运，实际并不存储数据，数据还是存放于网络、文件、数据库等。

`ContentProvider`内部可以使用不同的方式来存储数据。数据可以被存放在数据库，文件，甚至是网络。而向外提供统一的接口即可。
操作`ContentProvider`比较方便，和数据库很像。你可以查询，编辑它的内容，使用 `insert()`， `update()`， `delete() `和` query() `来添加或者删除内容。

## 如何组织数据的？
`ContentProvider`内部可以使用不同的方式来存储数据，而需要向外提供统一的操作接口，那么我们就需要看它统一的接口是如何组织数据的。
`ContentProvider`在安卓平台中展现为用户字典，像下面的一个表格：
![](/images/201908/2019-08-24_132602.png)

以上每一行都代表一个实例，每一列都表示该`word`所代表的一些数据，表头也就是在`Provider`中存储的每一列的名字，`_ID`代表主键，由`Provider`自动维护。

## 哪里需要用到
两种情况：
1. 访问其他应用程序中的现有内容提供程序，以共享数据。
2. 在应用程序中创建新的内容提供程序以与其他应用程序共享数据。

## 如何用
如果要访问内容提供程序中的数据，可以使用 `ContentResolver`应用程序中的对象`Context`作为客户端与提供程序 进行通信。该 `ContentResolver`对象与提供者对象通信，提供者对象是实现的类的实例`ContentProvider`。提供者对象从客户端接收数据请求，执行请求的操作，并返回结果。该对象具有在提供者对象中调用具有相同名称的方法的方法，该对象是其中一个具体子类的实例`ContentProvider`。这些 `ContentResolver`方法提供了持久存储的基本“`CRUD`”（创建，检索，更新和删除）功能。如下图：
![](/images/201908/content-provider-interaction.png)


## 如何找到我们需要的ContentProvider？
`URI（Uniform Resource Identifier）`统一资源标识符。
1. 每一个`ContentProvider`都有一个公共的`URI`，这个`URI`用于表示这个`ContentProvider`所提供的数据。
2. `Android`所提供的`ContentProvider`都存放在`android.provider`包中。

`URI`用来表示需要获得哪一部分的数据，`URI`的格式为：
`<协议://<authority>/<path>`  其中`authority`一般为程序的包名。 
一个标准的URI的表示方式为：`content://com.example.app/table1`
当然在查询数据的时候，主要用到的是`URI`的对象，所以以上`String`类型的参数，还需要通过`Uri uri = Uri.parse("content://com.example.app/table1")`获得，其中`table1`可以理解为表名。
可以理解成 `协议/主机中的位置/表/（行id/列index）`
比如：`content：//com.example.app.provider/table/1/name`
获取`provider`中`table`表第一行的`name`字段的值。
![](/images/201908/content_provider_uri.jpg)

`*`：匹配任意长度的任何有效字符的字符串
`eg`: `content://com.example.app.provider/*`匹配provider的任何内容 
`＃`：匹配任意长度的数字字符的字符串
`eg`: `content://com.example.app.provider/table/# `匹配provider中的table表的所有行

## MIME
作用：指定某个扩展名的文件用某种应用程序来打开
如：指定`.html`文件采用`text`应用程序打开
每种`MIME`类型 由两个部分组成 = 类型 + 子类型
如：`text/xml`，类型是`text`，子类型是`xml`
`ContentProvider`有两个方法返回`MIME`类型：
1. `getType()`该方法必须在`provider`中实现；
该`getType()`方法返回 `String``MIME`格式，该格式描述内容`URI`参数返回的数据类型。
所述`Uri`参数可以是图案，而不是一个特定的`URI`; 在这种情况下，您应该返回与模式匹配的内容`URI`相关的数据类型。
对于常见类型的数据（如文本，`HTML`或`JPEG`）， `getType()`应返回该数据的标准`MIME`类型。
对于指向一行或多行表数据的内容`URI`， `getType()`应返回`Android`特定于供应商的`MIME`格式的`MIME`类型：
* 键入部分： `vnd`
* 子类型部分：
如果`URI`模式用于单行： `android.cursor.item/`
如果`URI`模式适用于多行： `android.cursor.dir/`
* 特定于提供者的部分：`vnd.<name>.<type>`
该`<name>`值应该是全局唯一的，并且该`<type>`值对于相应的URI模式应该是唯一的。
一个很好的选择`<name>`是应用程序的Android软件包名称的某些部分。
一个很好的选择 `<type>`是一个字符串，用于标识与`URI`关联的表。
**例如**，如果提供者的权限是` com.example.app.provider`，并且它公开了一个名为的表 `table1`，则在`table1`中，多个行的`MIME`类型为：
`vnd.android.cursor.dir/vnd.com.example.provider.table1`
单行是：
`vnd.android.cursor.item/vnd.com.example.provider.table1`

2. `getStreamTypes()`如果您的`provider`提供文件，您应该实施的方法。
如果您的提供商提供文件，请执行 `getStreamTypes()`。该方法返回`String`供应商可以为给定内容`URI`返回的文件的`MIME`类型数组。您应该过滤`MIME`类型过滤器参数提供的`MIME`类型，以便仅返回客户端要处理的`MIME`类型。
例如，考虑一个供应商，提供照片图像中的文件`.jpg`，` .png`和`.gif`格式。如果应用程序`ContentResolver.getStreamTypes()`使用过滤器字符串调用`image/*`，则该`ContentProvider.getStreamTypes()`方法应返回该数组：
`{“image / jpeg”，“image / png”，“image / gif”}`
如果应用程序只对`.jpg`文件感兴趣，那么它可以`ContentResolver.getStreamTypes()`使用过滤字符串调用 `*\/jpeg`，并 `ContentProvider.getStreamTypes()`应返回：
`{"image/jpeg"}`
如果您的提供程序未提供过滤器字符串中请求的任何`MIME`类型，` getStreamTypes()` 则应返回`null`。

## 检索数据的步骤
1. 请求访问权限
默认情况下，存储在设备内部存储器中的数据文件对您的应用程序和提供程序是私有的。
默认情况下您的提供程序没有设置权限。要更改此设置，请使用元素的属性或子元素在清单文件中为提供程序设置权限` <provider>`。下面就介绍`<provider>`元素：
* 权限（`android:authorities`）
标识系统中整个提供程序的符号名称。
`android:grantUriPermssions`：临时权限标志。
`android:permission`：单一提供程序范围的读/写权限。
`android:readPermission`：提供商范围的读取权限。
`android:writePermission`：提供商范围的写入权限。
* 提供者类名（`android:name`）
* 启动和控制属性
这些属性决定了`Android`系统启动提供程序的方式和时间，提供程序的进程特征以及其他运行时设置：
`android:enabled`：标志允许系统启动提供程序。
`android:exported`：标记允许其他应用程序使用此提供程序。
`android:initOrder`：相对于同一进程中的其他提供程序，应启动此提供程序的顺序。
`android:multiProcess`：`Flag`允许系统在与调用客户端相同的进程中启动提供程序。
`android:process`：提供程序应运行的进程的名称。
`android:syncable`：指示提供程序的数据将与服务器上的数据同步的标志。
* 信息属性
提供者的可选图标和标签：
`android:icon`：包含提供程序图标的可绘制资源。该图标将显示在“设置” >“ 应用” >“ 全部”中应用列表中的提供商标签旁边 。
`android:label`：描述提供程序或其数据或两者的信息标签。该标签显示在“ 设置” >“ 应用” >“ 全部”中的应用列表中 。

2. 提供查询数据的代码

## ContentProvider所提供的函数
### 查询 query
`query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder): return Cursor` 返回值查询到的数据集的游标对象。
为了从`ContentProvider`中得到`word`列表，就需要调用`ContentResolver.query()`，而这个`query()`方法，它调用的是由用户字典`Provider`中定义的`ContentProvider.query()`方法。参考如下：
``` java
// Queries the user dictionary and returns results
cursor = getContentResolver().query(
    uri,   // The content URI of the words table
    projection,                        // The columns to return for each row
    selection,                   // Selection criteria
    selectionArgs,                     // Selection criteria
    sortOrder);                        // The sort order for the returned rows
```

解释：
<table border="1"><tbody><tr><td>
			<p>query（）方法参数</p>
			</td>
			<td>
			<p>对应Sql部分</p>
			</td>
		</tr><tr><td>
			<p>uri</p>
			</td>
			<td>
			<p>from table_name</p>
			</td>
		</tr><tr><td>
			<p>projection</p>
			</td>
			<td>
			<p>select column1,column2</p>
			</td>
		</tr><tr><td>
			<p>selection</p>
			</td>
			<td>
			<p>where column = ?</p>
			</td>
		</tr><tr><td>
			<p>selectionArgs</p>
			</td>
			<td>
			<p>where条件中占位符对应的数据</p>
			</td>
		</tr><tr><td>
			<p>sortOrder</p>
			</td>
			<td>
			<p>order by column1,column2</p>
			</td>
</tr></tbody></table>


### 增加insert
`insert(Uri url,ContentValues values): return Uri` 返回值为新建数据的`uri`
### 更新update
`update(  Uri uri,ContentValues values,  String where,String[] selectionArgs): return int` 返回值为被更新的行数
### 删除 delete
`delete(  Uri url,  String where,String[] selectionArgs):return int`返回值被删除的行数
### 得到数据类型 getType
`getType(Uri uri):return String`返回当前 `Uri` 所代表数据的`MIME`类型
### onCreate
`onCreate():return Boolean` 进程第一次访问该`ContentProvider`时 由系统进行调用。由于运行在`ContentProvider`进程的主线程，故不能做耗时操作。


### 注：
1. `query`、`insert`、`update`、`delete`由外部进程回调，并运行在`ContentProvider`进程的`Binder`线程池中（不是主线程）
2. 存在多线程并发访问，需要实现线程同步
a. 若`ContentProvider`的数据存储方式是使用`SQLite` & 一个，则不需要，因为`SQLite`内部实现好了线程同步，若是多个`SQLite`则需要，因为`SQL`对象之间无法进行线程同步
b. 若`ContentProvider`的数据存储方式是内存，则需要自己实现线程同步

## ContentUris类
作用：操作` URI`
具体使用： 核心方法有两个：
`withAppendedId()`：向`URI`追加一个`id`
``` java
Uri uri = Uri.parse("content://cn.scu.myprovider/user") 
Uri resultUri = ContentUris.withAppendedId(uri, 7);  
// 最终生成后的Uri为：content://cn.scu.myprovider/user/7
```


` parseId()`：从URL中获取ID
``` java
Uri uri = Uri.parse("content://cn.scu.myprovider/user/7") 
long personid = ContentUris.parseId(uri); 
//获取的结果为:7
``` 

原文链接：https://blog.csdn.net/carson_ho/article/details/76101093
## UriMatcher类
作用：在ContentProvider 中注册URI、根据 URI 匹配 ContentProvider 中对应的数据表

步骤1：初始化UriMatcher对象
`UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);` 
常量UriMatcher.NO_MATCH  = 不匹配任何路径的返回码
即初始化时不匹配任何东西

步骤2：在ContentProvider 中注册URI（addURI（））
`int URI_CODE_a = 1；`
`int URI_CODE_b = 2；`
`matcher.addURI("cn.scu.myprovider", "user1", URI_CODE_a); `
`matcher.addURI("cn.scu.myprovider", "user2", URI_CODE_b); `
若`URI资源路径 = content://cn.scu.myprovider/user1` ，则返回注册码`URI_CODE_a`
若`URI资源路径 = content://cn.scu.myprovider/user2` ，则返回注册码`URI_CODE_b`

步骤3：根据URI 匹配` URI_CODE`，从而匹配`ContentProvider`中相应的资源（`match（）`）
``` java
@Override   
public String getType(Uri uri) {   
  Uri uri = Uri.parse(" content://cn.scu.myprovider/user1");   
  switch(matcher.match(uri)){   
     // 根据URI匹配的返回码是URI_CODE_a
     // 即matcher.match(uri) == URI_CODE_a
      case URI_CODE_a:   
        return tableNameUser1;   
        // 如果根据URI匹配的返回码是URI_CODE_a，则返回ContentProvider中的名为tableNameUser1的表
      case URI_CODE_b:   
        return tableNameUser2;
        // 如果根据URI匹配的返回码是URI_CODE_b，则返回ContentProvider中的名为tableNameUser2的表
    }   
}
```


## ContentObserver类
定义：内容观察者
作用：观察` Uri`引起 `ContentProvider `中的数据变化 & 通知外界（即访问该数据访问者）
当`ContentProvider `中的数据发生变化（增、删 & 改）时，就会触发该 `ContentObserve`r类

具体使用
``` java
// 步骤1：注册内容观察者ContentObserver
getContentResolver().registerContentObserver（uri）；
// 通过ContentResolver类进行注册，并指定需要观察的URI

// 步骤2：当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
public class UserContentProvider extends ContentProvider { 
      public Uri insert(Uri uri, ContentValues values) { 
      db.insert("user", "userid", values); 
      getContext().getContentResolver().notifyChange(uri, null); 
      // 通知访问者
   } 
}

// 步骤3：解除观察者
getContentResolver().unregisterContentObserver（uri）；
// 同样需要通过ContentResolver类进行解除
```


## 自定义Content Provider
一般而言，自定义`ContentProvider`并不常见，使用`Android`内置的`ContentProvider`比较常见。
1. 定义`CONTENT_URI`常量，也就是该`ContentProvider`的资源标识符。
2. 定义一个类，继承`ContentProvider`
写一个类继承[ContentProvider](https://developer.android.google.cn/reference/android/content/ContentProvider.html)；
`ContentProvider`是以结构化格式保存的数据的**接口**。在创建界面之前，您必须决定如何存储数据。您可以以任何您喜欢的形式存储数据，然后设计接口以根据需要读取和写入数据。

3. 实现`query`、`insert`、`update`、`delete`、`getType`和`onCreate`方法

4. 在`AndroidManifest.xml`中声明

这里，就按照视频的讲解，然后做一个简单的案例：
布局文件没有做，也不需要修改，默认即可。

DatabaseHelper.java
``` java
public class DatabaseHelper extends SQLiteOpenHelper {

	//Create a helper object to create, open, and/or manage a database.
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	//Called when the database is created for the first time.
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table cp(id int, name varchar(20), age int)");
	}

	//Called when the database needs to be upgraded.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

```

myProvider.java
``` java
package com.weizu.intent;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class myProvider extends ContentProvider {
	private SQLiteDatabase db;
	//设置ContentProvider的唯一标识
	public static final String authorities = "com.weizu.intent.myProvider";
	public static final String Database_Name = "contentprovider.db";
	public static final int Database_Version = 1;
	public static final Uri CONTENT_URI = Uri.parse("content://" + authorities + "/user");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.myprovider.user";
	public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.myprovider.user";
	private static final UriMatcher matcher;
	public static final int USER_CODE_COLLECTION = 1;
	public static final int USER_CODE_SINGLE = 2;
	//在ContentProvider 中注册URI
	static{
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		//若URI资源路径 = content://com.weizu.intent.myProvider/user ，则返回注册码USER_CODE_COLLECTION
		matcher.addURI(authorities, "user", USER_CODE_COLLECTION);
		//#匹配数字字符的字符串，指代的是记录，如URI资源路径 = content://com.weizu.intent.myProvider/user/1，表示第一行
		matcher.addURI(authorities, "user/#", USER_CODE_SINGLE);	
	}
	
	@Override
	public boolean onCreate() {
		db = new DatabaseHelper(getContext(), Database_Name, null, 1).getWritableDatabase();
		new Thread(new Runnable(){
			@Override
			public void run() {
				db.execSQL("insert into cp values(1, 'Tom', 23)");
				db.execSQL("insert into cp values(2, 'Holly', 32)");
				db.execSQL("insert into cp values(3, 'Jarry', 20)");
			}
		}).start();
		return false;
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		switch(matcher.match(uri)){
		case USER_CODE_COLLECTION:
			return CONTENT_TYPE;
		case USER_CODE_SINGLE:
			return CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowId = db.insert("cp", null, values);
		if(rowId > 0){
			Uri insertedUserUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
//通知数据发生修改			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		Cursor cursor = db.query("cp", projection, selection, selectionArgs, null, null, sortOrder, null);
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		return 0;
	}
}

```

MainActivity.java
``` java
public class MainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Uri uri = Uri.parse("content://com.weizu.intent.myProvider/user");
		ContentResolver resolver = getContentResolver();
		
		query(resolver, uri);
		
		ContentValues values = new ContentValues();
		values.put("id", "10");
		values.put("name", "cp_name");
		values.put("age", 22);
		resolver.insert(uri, values);
		
		query(resolver, uri);
		
	}
	private void query(ContentResolver resolver, Uri uri){
		Cursor cursor = resolver.query(uri, new String[]{"id", "name", "age"}, null, null, null);
		while(cursor.moveToNext()){
			int _id = cursor.getInt(cursor.getColumnIndex("id"));
			String _name = cursor.getString(cursor.getColumnIndex("name")); 
			int _age = cursor.getInt(cursor.getColumnIndex("age")); 
			Toast.makeText(MainActivity.this, "id=" + _id + "|name=" + _name + "|age=" + _age, 20).show();
		}
		cursor.close();
	}

}
```

添加权限：AndroidManifest.xml
``` xml
<provider
            android:authorities="com.weizu.intent.myProvider"
            android:name=".myProvider"
            android:exported="true"
            ></provider>
```

用Toast可以看见首次的查询和插入后的查询效果。





