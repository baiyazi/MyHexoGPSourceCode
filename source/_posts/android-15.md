---
title: android | xml文件解析
date: 2019-8-27 11:01:09
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>每种语言都有解析xml文档的插件包，在java中常见的有两种，Dom和SAX两种方式。

## 介绍
`SAX（Simple API for XML）`是一个解析速度快并且占用内存少的`XML`解析器，非常适合用于`Android`等移动设备。
`SAX`解析`XML`文件采用的是事件驱动，也就是说，它并不需要解析完整个文档，在按内容顺序解析文档的过程中，`SAX`会判断当前读到的字符是否合法`XML`语法中的某部分，如果符合就会触发事件。
所谓事件，其实就是一些回调（`callback`）方法，这些方法(事件)定义在`ContentHandler`接口。

## ContentHandler
既然回调的触发事件定义在`ContentHandler`中，那么不妨看看[这个类](https://developer.android.google.cn/reference/org/xml/sax/ContentHandler?hl=en)：
`org.xml.sax.ContentHandler`

大多数`SAX`应用程序都实现了这个接口，这个解析器就关联了一些文档事件，如元素开始、元素结束和文本数据。
在这个接口中事件的顺序是非常重要，所有的元素内容都出现在`startElement`事件和`endElement`事件之间。

<table>
<tr><td>abstract void</td><td>characters(char[] ch, int start, int length)</td><td>
接收字符数据的通知。</td></tr>
<tr><td>abstract void</td><td>endDocument()</td><td>
接收文档结束的通知。</td></tr>
<tr><td>abstract void</td><td>endElement(String uri, String localName, String qName)</td><td>
接收元素结束的通知。</td></tr>
<tr><td>abstract void</td><td>endPrefixMapping(String prefix)</td><td>
结束前缀URI映射的范围。</td></tr>
<tr><td>abstract void</td><td>ignorableWhitespace(char[] ch, int start, int length)</td><td>
接收元素内容中可忽略空白的通知。</td></tr>
<tr><td>abstract void</td><td>processingInstruction(String target, String data)</td><td>
接收处理指令的通知。</td></tr>
<tr><td>abstract void</td><td>setDocumentLocator(Locator locator)</td><td>
接收用于查找SAX文档事件原点的对象。</td></tr>
<tr><td>abstract void</td><td>skippedEntity(String name)</td><td>
接收跳过的实体的通知。</td></tr>
<tr><td>abstract void</td><td>startDocument()</td><td>
接收文档开头的通知。</td></tr>
<tr><td>abstract void</td><td>startElement(String uri, String localName, String qName, Attributes atts)</td><td>
接收元素开头的通知。</td></tr>
<tr><td>abstract void</td><td>startPrefixMapping(String prefix, String uri)</td><td>
开始前缀-URI名称空间映射的范围。</td></tr>
</table>

看上面的函数，都是抽象函数，如果我们实现`ContentHandler`，就会比较麻烦，故而我们找它已知的子类[DefaultHandler](https://developer.android.google.cn/reference/org/xml/sax/helpers/DefaultHandler.html?hl=en)(SAX2事件处理程序的默认基类。)

## 案例
这里解析的xml文件是在`res/raw/person.xml`，`raw`是新建的文件夹。具体的`xml`如下：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<persions>
    <person>
        <name>Tom</name>
        <age>20</age>
        <sex>男</sex>
    </person>
</persions>
```

然后，就是写一个解析类，`saxParser.java`，它继承`DefaultHandler`，简单的实现如下：

``` java
package com.weizu.intent;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

public class saxParser extends DefaultHandler {
	private String tagName = null;
	private person pn = null;
	private ArrayList<person> list = new ArrayList<person>();
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		//处理文本标签，容易猜想。ch字符数组,start开始位置，长度
		if(tagName != null){
			String data = new String(ch, start, length);
			if(tagName.equals("name")){
				Log.i("name", data);
				pn.setName(data);
			}else if(tagName.equals("age")){
				Log.i("age", data);
				pn.setAge(Integer.parseInt(data));
			}else if(tagName.equals("sex")){
				Log.i("sex", data);
				pn.setSex(data);
			}
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//由于开始，结束，文本都是调用这三个函数，故而需要标记
		tagName = localName;
		if(localName.equals("person")){
			pn = new person();
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if(localName.equals("person")){
			list.add(pn);
			pn = null;
		}
		tagName = null;
	}
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	//返回数据
	public ArrayList<person> getPersons(){
		return list;
	}
}

```

最后就是调用了，布局文件是一个按钮，这里就不给出了，`MainActivity.java`如下：
``` java
public class MainActivity extends Activity{
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				InputStream in = getResources().openRawResource(R.raw.person);
				if(in!=null){
					ArrayList<person> list = saxParser(in);
					if(list!=null){
						Toast.makeText(MainActivity.this, list.get(0).toString(), 20).show();
					}else{
						Toast.makeText(MainActivity.this, "resulut is null", 20).show();
					}
				}else{
					Toast.makeText(MainActivity.this, "input is null", 20).show();
				}
			}
		});
		
	}
	
	public ArrayList<person> saxParser(InputStream inputstream){
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			saxParser sax = new saxParser();
			saxParser.parse(inputstream, sax);
			inputstream.close();
			return sax.getPersons();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
```

效果：
![](/images/201908/sax_parser_1.gif)






