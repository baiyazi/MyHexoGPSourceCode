---
title: android | SQLite数据库操作
date: 2019-8-18 14:36:47
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>得到了数据库的对象，接着就是对数据库进行操作。增删改查。我们就需要查看[SQLiteDatabase](https://developer.android.google.cn/reference/android/database/sqlite/SQLiteDatabase.html)

## 数据库操作的一些方法
下面就是我从文档中摘取的一些方法：
<table>
<tr><td>返回值</td><td>方法</td><td>说明</td></tr>
<tr><td><font style="color:#A94947;">void</font></td><td><font style="color:#F08D49;">execSQL</font>(<font style="color:#00887A;">String</font> sql)</td><td>Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.</td></tr>
<tr><td><font style="color:#A94947;">void</font></td><td><font style="color:#F08D49;">execSQL</font>(<font style="color:#00887A;">String</font> sql, Object[] bindArgs)</td><td>Execute a single SQL statement that is NOT a SELECT/INSERT/UPDATE/DELETE.</td></tr>
<tr><td><font style="color:#0000FF;">int</font></td><td><font style="color:#F08D49;">getVersion<font style="color:#F08D49;"></font>()</td><td>Gets the database version.</td></tr>
<tr><td><font style="color:#0000FF;">long</font></td><td><font style="color:#F08D49;">insert</font>(<font style="color:#00887A;">String</font> table, <font style="color:#00887A;">String</font> nullColumnHack, <a href="https://developer.android.google.cn/reference/android/content/ContentValues.html">ContentValues</a> values)</td><td>Convenience method for inserting a row into the database.</td></tr>
<tr><td><font style="color:#0000FF;">boolean</font></td><td><font style="color:#F08D49;">isReadOnly</font>()</td><td>Returns true if the database is opened as read only.</td></tr>
<tr><td><font style="color:#0000FF;">boolean</font></td><td><font style="color:#F08D49;">isOpen</font>()</td><td>Returns true if the database is currently open.</td></tr>
<tr><td><a href="https://developer.android.google.cn/reference/android/database/Cursor.html">Cursor</a></td><td><font style="color:#F08D49;">query</font>(<font style="color:#00887A;">boolean</font> distinct, <font style="color:#00887A;">String</font> table, <font style="color:#00887A;">String[]</font> columns, <font style="color:#00887A;">String</font> selection, <font style="color:#00887A;">String[]</font> selectionArgs, <font style="color:#00887A;">String</font> groupBy, <font style="color:#00887A;">String</font> having, <font style="color:#00887A;">String</font> orderBy, <font style="color:#00887A;">String</font> limit)</td><td>Query the given URL, returning a Cursor over the result set.</td></tr>
<tr><td><a href="https://developer.android.google.cn/reference/android/database/Cursor.html">Cursor</a></td><td><font style="color:#F08D49;">query</font>(<font style="color:#00887A;">String</font> table, <font style="color:#00887A;">String[]</font> columns, <font style="color:#00887A;">String</font> selection, <font style="color:#00887A;">String[]</font> selectionArgs, <font style="color:#00887A;">String</font> groupBy, <font style="color:#00887A;">String</font> having, <font style="color:#00887A;">String</font> orderBy, <font style="color:#00887A;">String</font> limit)</td><td>Query the given table, returning a Cursor over the result set.
</td></tr>

<tr><td><a href="https://developer.android.google.cn/reference/android/database/Cursor.html">Cursor</a></td><td><font style="color:#F08D49;">query</font>(<font style="color:#00887A;">boolean</font> distinct, <font style="color:#00887A;">String</font> table, <font style="color:#00887A;">String[]</font> columns, <font style="color:#00887A;">String</font> selection, <font style="color:#00887A;">String[]</font> selectionArgs, <font style="color:#00887A;">String</font> groupBy, <font style="color:#00887A;">String</font> having, <font style="color:#00887A;">String</font> orderBy, <font style="color:#00887A;">String</font> limit, <a href="https://developer.android.google.cn/reference/android/os/CancellationSignal.html">CancellationSignal</a> cancellationSignal)</td><td>Query the given URL, returning a Cursor over the result set.</td></tr>

<tr><td><a href="https://developer.android.google.cn/reference/android/database/Cursor.html">Cursor</a></td><td><font style="color:#F08D49;">query</font>(<font style="color:#00887A;">String</font> table, <font style="color:#00887A;">String[]</font> columns, <font style="color:#00887A;">String</font> selection, <font style="color:#00887A;">String[]</font> selectionArgs, <font style="color:#00887A;">String</font> groupBy, <font style="color:#00887A;">String</font> having, <font style="color:#00887A;">String</font> orderBy)</td><td>Query the given table, returning a Cursor over the result set.</td></tr>

<tr><td><font style="color:#0000FF;">int</font></td><td><font style="color:#F08D49;">update</font>(<font style="color:#00887A;">String</font> table,  <a href="https://developer.android.google.cn/reference/android/content/ContentValues.html">ContentValues</a> values, <font style="color:#00887A;">String</font> whereClause, <font style="color:#00887A;">String[]</font> whereArgs)</td><td>Convenience method for updating rows in the database.</td></tr>
</table>


## 插入数据
### insert的SQL语句
不妨看看`SQL`语句是如何进行插入数据的：
``` sql
insert into TABLE_NAME values(value1, value2, value3, value4);
insert into TABLE_NAME(ParameterName1,ParameterName2, ParameterName3) values (value1, value2, value3);
```

故而如果在程序中指定，由于不确定那些参数是默认不写，故而需要指定表中列名+对应的值。
插入数据，我们就要先看[ContentValues](https://developer.android.google.cn/reference/android/content/ContentValues.html)
### [ContentValues](https://developer.android.google.cn/reference/android/content/ContentValues.html)
用来存储值得序列，先看构造函数：
`ContentValues()  Creates an empty set of values using the default initial size` 用默认大小的存储创建一个空的值集合。
`ContentValues(int size)   Creates an empty set of values using the given initial size` 用指定大小的`size`存储创建一个空值集合。
既然是集合，我们就需要了解如何存储值：
<table>
<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Short</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Long</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Double</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Integer</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">String</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Boolean</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Float</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">byte[]</font> value)</td><td>
Adds a value to the set</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">put</font>(<font style="color:#039BE5;">String</font> key, <font style="color:#039BE5;">Byte</font> value)</td><td>
Adds a value to the set</td></tr>
<tr><td>void</td><td><font style="color:#039BE5;">putNull</font>(<font style="color:#039BE5;">String</font> key)</td><td>
Adds a null value to the set.</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">remove</font>(<font style="color:#039BE5;">String</font> key)</td><td>
Remove a single value.</td></tr>

<tr><td>int</td><td><font style="color:#039BE5;">size</font>()</td><td>Returns the number of values.</td></tr>

<tr><td>Object</td><td><font style="color:#039BE5;">get</font>(<font style="color:#039BE5;">String</font> key)</td><td>
Gets a value.</td></tr>

<tr><td>void</td><td><font style="color:#039BE5;">clear</font>()</td><td>
Removes all values.</td></tr>

<tr><td>boolean</td><td><font style="color:#039BE5;">containsKey</font>(<font style="color:#039BE5;">String</font> key)</td><td>
Returns true if this object has the named value.</td></tr>
</table>

几乎和Map集合一样，接着就进行简单的数据插入操作：
这里还是使用前一讲的`DatabaseHelper.java`类，在`MainActivity.java`中完成数据插入的操作。
在布局文件中新增一个按钮`insert`，然后添加监听，在监听函数中，写入插入数据的代码：
``` java
insert = (Button)findViewById(R.id.insert);
insert.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View arg0) {
		DatabaseHelper helper = new DatabaseHelper(MainActivity.this, "weizu", null, 1);
		//Create and/or open a database.创建过了，调用就是打开open
		//"create table weizu(id int, name varchar(20), age int)"
		SQLiteDatabase db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", 1);
		values.put("name", "无涯明月");
		values.put("age", 24);
		db.insert("weizu", null, values);
	}
});
```

同样的，还是使用`CMD`窗口，我们查看我们的数据库中是否有我们插入的数据：
使用命令：`select * form weizu;`
![](/images/201908/2019-08-18_161718.png)

控制台窗口的编码是`GBK`，数据库编码应该是`UTF-8`，出现了乱码。不过可见插入数据成功。

## 更新数据
### update的SQL语句
不妨看看`SQL`语句是如何进行数据更新的：
``` sql
update TABLE_NAME set ParameterName1 = value1 where ParameterName2 = value2 
update TABLE_NAME set ParameterName1 = value1,ParameterName2 = value2 where ParameterName3 = value3
```

在java操作数据库的时候，我们都知道可以使用`?`占位符，然后在后面确定值，这里也同样。
### update的原型：
```java
public int update (String table, 
                ContentValues values, 
                String whereClause, 
                String[] whereArgs)
```

看看参数的解释：
<table>
    <tbody><tr><th colspan="2">Parameters</th></tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>table</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code>: the table to update in<p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>values</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>ContentValues</span></code>: a map from column names to new column values. null is a
            valid value that will be translated to NULL.<p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>whereClause</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code>: the optional WHERE clause to apply when updating.
            Passing null will update all rows.<p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>whereArgs</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code>: You may include ?s in the where clause, which
            will be replaced by the values from whereArgs. The values
            will be bound as Strings.<p></p></td>
      </tr>
</tbody></table>

上面的参数说明也告知了可以使用`?`占位符在`whereClause`中，然后在`whereArgs`中填值。而`values`也就是新的列值。下面就来个案例操作一下：
当然，还是在上面的案例的基础上，我们修改年龄为50。
同样的新增一个按钮`update`，然后添加事件监听：
``` java
update = (Button)findViewById(R.id.update);
update.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View arg0) {
		DatabaseHelper helper = new DatabaseHelper(MainActivity.this, "weizu", null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("age", 50);
		db.update("weizu", values, "id=?",new String[]{"1"});
	}
});
```

效果，居然是两行，说明实际上原本的数据库没有随着重新安装程序删除掉。如下图：
![](/images/201908/2019-08-18_164954.png)


## 查询数据
### query的SQL语句
不妨看看`SQL`语句是如何进行数据更新的：[原文](https://blog.csdn.net/wfh6732/article/details/56680959)
``` sql
SELECT select_list　　　
FROM table_name　　
[ WHERE search_condition ]　　
[ GROUP BY group_by_expression ]　　
[ HAVING search_condition ]　　
[ ORDER BY order_expression [ ASC | DESC ] ]
[limit m,n]
```

如：`select classNo  from table_name group by classNo  having(avg(成绩)>70) order by classNo limit 0,10`
`limit 0,10`是从第一条开始,取10条数据

1. `where`后跟条件用来筛选我们所需的行。它后面可以跟的操作符有`=`、`！=`、`<`、`>`、`<=`、`>=`、 `in`、 `like`(可以和通配符%结合一起用，效果将会更好) 、`between....and.......`、 `AND`、`OR` 、`NOT` 
2. `group by`（`GROUP BY` 语句用于结合合计函数（也叫聚合函数`sum` `count` `avg` `max` `min`），根据一个或多个列对结果集进行分组。否则没有多大的意义）
`group by` 有一个原则,就是 `select` 后面的所有列中,没有使用聚合函数的列,必须出现在 `group by` 后面
如果你使用了`group by`，而没有相应的使用聚合函数那么结果就没有意义了
3. `having` 这个是针对查询的结果进行作用，只能对结果拥有的列进行操作，与`where`不同的是`where`是针对原表(就是`from`后面的那张表的字段)发挥作用。其中`having`里面可以使用聚合函数。为`group by`子句设置条件，类似于`where`为`select`语句设置条件的方法。`having`的查找条件可以包括集合函数表达式。除此之外，它的查找条件与`where`查找条件相同。


### 看query原型：
```java
public Cursor query (boolean distinct, 
                String table, 
                String[] columns, 
                String selection, 
                String[] selectionArgs, 
                String groupBy, 
                String having, 
                String orderBy, 
                String limit)
```

参数：
<table class="responsive">
    <tbody><tr><th colspan="2"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">参数</font></font></th></tr>
<tr>
        <td><code translate="no" dir="ltr"><span>distinct</span></code></td>
<td width="100%">
          <code translate="no" dir="ltr"><span>boolean</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：如果您希望每一行都是唯一的，则为true，否则为false。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>table</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：用于查询的表名。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>columns</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：要查询的列的列名。</font><font style="vertical-align: inherit;">传递null将返回所有列，相当于 select语句 select关键字后面部分</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>selection</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：一个过滤器，声明要返回哪些行，格式化为SQL WHERE子句（不包括WHERE本身）。</font><font style="vertical-align: inherit;">传递null将返回给定表的所有行。在条件子句中允许使用占位符。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>selectionArgs</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：您可以在selection中包含？，它将被selectionArgs中的值替换，以便它们出现在selection中。</font><font style="vertical-align: inherit;">这些值将绑定为字符串。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>groupBy</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：一个过滤器，声明如何对行进行分组，格式化为SQL GROUP BY子句（不包括GROUP BY本身）。</font><font style="vertical-align: inherit;">传递null将导致行不被分组。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>having</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：过滤器声明要在游标中包含哪些行组，如果正在使用行分组，则格式化为SQL HAVING子句（不包括HAVING本身）。</font><font style="vertical-align: inherit;">传递null将导致包含所有行组，并且在未使用行分组时是必需的。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>orderBy</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：如何对行进行排序，格式化为SQL ORDER BY子句（不包括ORDER BY本身）。</font><font style="vertical-align: inherit;">传递null将使用默认排序顺序，该顺序可能是无序的。</font></font><p></p></td>
      </tr>
      <tr>
        <td><code translate="no" dir="ltr"><span>limit</span></code></td>
        <td width="100%">
          <code translate="no" dir="ltr"><span>String</span></code><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">：限制查询返回的行数，用于进行分页，格式为LIMIT子句。</font><font style="vertical-align: inherit;">传递null表示没有LIMIT子句。</font></font><p></p></td>
      </tr>
</tbody></table>

### [Cursor](https://developer.android.google.cn/reference/android/database/Cursor.html)
是一个接口，该接口提供对数据库查询结果集的随机读写权限。下面还是摘取部分的方法：
<table>
<tr><td>int</td><td>getColumnCount()</td><td>返回总列数。</td></tr>
<tr><td>int</td><td>getColumnIndex(String columnName)</td><td>返回给定列名的从零开始的索引，如果该列不存在，则返回-1。</td></tr>
<tr><td>String</td><td>getColumnName(int columnIndex)</td><td>返回给定的从零开始的列索引处的列名称。</td></tr>
<tr><td>String[]</td><td>getColumnNames()</td><td>返回一个字符串数组，其中包含结果集中所有列的名称，按其在结果中列出的顺序排列。</td></tr>
<tr><td>int</td><td>getCount()</td><td>
返回游标中的行数。</td></tr>
<tr><td>Bundle</td><td>getExtras()</td><td>
返回一组额外值。</td></tr>
<tr><td>byte[]</td><td>getBlob(int columnIndex)</td><td>
以字节数组的形式返回请求列的值。</td></tr>
<tr><td>double</td><td>getDouble(int columnIndex)</td><td>
以double形式返回请求列的值。</td></tr>
<tr><td>float</td><td>getFloat(int columnIndex)</td><td>
以float形式返回请求列的值。</td></tr>
<tr><td>long</td><td>getLong(int columnIndex)</td><td>
以long形式返回请求列的值。</td></tr>
<tr><td>int</td><td>getPosition()</td><td>
返回行集中光标的当前位置。</td></tr>
<tr><td>short</td><td>getShort(int columnIndex)</td><td>
以short形式返回请求列的值。</td></tr>
<tr><td>String</td><td>	getString(int columnIndex)</td><td>
以String形式返回所请求列的值。</td></tr>
<tr><td>int</td><td>getType(int columnIndex)</td><td>
返回给定列的值的数据类型。</td></tr>
<tr><td>boolean</td><td>isAfterLast()</td><td>
返回光标是否指向最后一行之后的位置。</td></tr>
<tr><td>boolean</td><td>isBeforeFirst()</td><td>
返回光标是否指向第一行之前的位置。</td></tr>
<tr><td>boolean</td><td>isClosed()</td><td>
如果光标关闭则返回true
<tr><td>boolean</td><td>isFirst()</td><td>
返回光标是否指向第一行。</td></tr>
<tr><td>boolean</td><td>isLast()</td><td>
返回光标是否指向最后一行。</td></tr>
<tr><td>boolean</td><td>isNull(int columnIndex)</td><td>
true如果指示列中的值为null，则返回。</td></tr>
<tr><td>boolean</td><td>move(int offset)</td><td>
将光标从当前位置向前或向后移动相对量。</td></tr>
<tr><td>boolean</td><td>moveToFirst()</td><td>
将光标移动到第一行。</td></tr>
<tr><td>boolean</td><td>moveToLast()</td><td>
将光标移动到最后一行。</td></tr>
<tr><td>boolean</td><td>moveToNext()</td><td>
将光标移动到下一行。</td></tr>
<tr><td>boolean</td><td>moveToPosition(int position)</td><td>
将光标移动到绝对位置。</td></tr>
<tr><td>boolean</td><td>moveToPrevious()</td><td>
将光标移动到上一行。</td></tr>
 </table>
 
案例：
还是加一个按钮，然后下面是MainActivity.java中新增的代码：
```java
query = (Button)findViewById(R.id.query);
query.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View arg0) {
		DatabaseHelper helper = new DatabaseHelper(MainActivity.this, "weizu", null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query(false, "weizu", new String[]{"id", "name", "age"}, "id=?", new String[]{"1"}, null, null, null, null);
		while(cursor.moveToNext()){
			int _id = cursor.getInt(cursor.getColumnIndex("id"));
			String _name = cursor.getString(cursor.getColumnIndex("name"));
			int _age = cursor.getInt(cursor.getColumnIndex("age"));
			Toast.makeText(MainActivity.this, "id=" + _id + "|name=" + _name + "|age=" + _age, 20).show();
		}
	}
});
```

效果：
![](/images/201908/query_1.gif)
 
 





