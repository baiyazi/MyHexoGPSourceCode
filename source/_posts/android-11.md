---
title: android | 文件下载
date: 2019-8-19 15:43:42
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>互联网的世界，文件的下载和上传是最常见的操作。当然，涉及到下载文件的协议有很多，如FTP、SFTP、HTTP等，这里就简单使用HTTP协议来下载文件。


而还获取网络的文件数据，我们就需要获取网络的链接（连接）。百度了一下，可以用的类比较多，这里就使用[HttpURLConnection](https://developer.android.google.cn/reference/kotlin/java/net/HttpURLConnection?hl=en)来操作。如下摘抄一下：
使用这个类遵循以下的规则：
1. 使用`URL#openConnection()`来得到连接对象`HttpURLConnection`
2. 数据请求头部可以设置URI、登录凭证、首选数据类型、`Session`和`Cookies`。
3. 上传数据实例如果包含请求体就必须配置 `setDoOutput(true)`
4. 读响应的数据，响应的头部通常包含响应头如响应正文的内容类型、长度、修改日期以及`Cookie`，响应体。如果没有正文，该流为空。
5. 一旦读取了响应流数据，就应该调用`disconnect()`来关闭流，释放资源。

在文档中提供了一个简单的案例：
``` java
URL url = new URL("http://www.android.com/");
HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
try {
    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
    readStream(in);
  } finally {
    urlConnection.disconnect();
}
```

有网页开发的都知道，`HTTP`协议是有状态码的，这里也有（该类的属性）：
状态码很多，这里就只谈`200`。
<table><tr><td>HTTP_OK</td><td>HTTP状态代码200：好的。</td></tr></table>

接下来就看看方法（包括从URLConnection中继承的）：【摘取部分】
<table>
 <tr><td>String</td><td>getRequestMethod()</td><td>获取请求方法</td></tr>
 <tr><td>Int</td><td>getResponseCode()</td><td>获取状态代码</td></tr>
<tr><td>Unit</td><td>setRequestMethod(method: String!)</td><td>设置URL请求的方法,GET|POST|HEAD|OPTIONS|PUT|DELETE|TRACE</td></tr>
<tr><td>Int</td><td>getConnectTimeout()</td><td>返回连接超时的设置。0为无限超时。</td></tr>
<tr><td>Unit</td><td>setConnectTimeout(timeout: Int)</td><td>设置在打开与此URLConnection引用的资源的通信链接时要使用的指定超时值（以毫秒为单位）。如果超时在可以建立连接之前到期，则引发java.net.SocketTimeoutException。超时为零被解释为无限超时。</td></tr>
<tr><td>String</td><td>getContentEncoding()</td><td>返回content-encoding标头字段的值。</td></tr>
<tr><td>Int</td><td>getContentLength()</td><td>返回content-length标头字段的值。</td></tr>
<tr><td>Long</td><td>getContentLengthLong()</td><td>content-length以long形式返回标头字段的值。</td></tr>
<tr><td>String</td><td>getContentType()</td><td>返回content-type标头字段的值。</td></tr>
<tr><td>Long</td><td>getDate()</td><td>返回date标头字段的值。</td></tr>
<tr><td>InputStream</td><td>getInputStream()</td><td>返回从此打开的连接读取的输入流。如果读取超时在数据可用于读取之前到期，则在从返回的输入流读取时可以抛出SocketTimeoutException。</td></tr>
<tr><td>Long</td><td>getLastModified()</td><td>返回last-modified标头字段的值。结果是格林威治标准时间1970年1月1日以来的毫秒数。</td></tr>
<tr><td>OutputStream</td><td>getOutputStream()</td><td>返回写入此连接的输出流。</td></tr>
<tr><td>Int</td><td>getReadTimeout()</td><td>返回读取超时的设置。0为无限超时。</td></tr>
<tr><td>URL</td><td>getURL()</td><td>返回此值URLConnection的URL字段。</td></tr>
<tr><td>String</td><td>toString()</td><td>返回此值URLConnection的URL字段的字符串形式。</td></tr>
</table>


下面就开始图片下载的案例：
图片地址：https://pic.cnblogs.com/avatar/1142647/20170416093225.png
这里就不写如果在`ImageView`中设置图片的了。在[下一篇博客](/2019/08/android-12/)中写明了。这里就简单使用，然后下载下来。
所以这里也就不需要关注`ImageView`中是如何设置图片的，要不然，其实在设置图片的时候已经可以保存了，这里再继续用按钮点击事件去访问图片就多余了。这里就关注按钮的事件。

获取当前设备`SD`卡的目录：
``` java
Environment.getExternalStorageDirectory()
```

还是在adb shell中，在/mnt目录下，查看一下sdcard的权限，如图：
![](/images/201908/2019-08-20_165057.png)

也就是只读的，我们需要修改一下：
运行命令：mount -o remount rw /
然后用chmod命令修改： chmod 777 sdcard  我这里为了方便，赋予了最高的读写权限
照例，还是查看一下：
![](/images/201908/2019-08-20_165354.png)

然后，编程测试发现还是解决不了，这里就不做深入探究了。果断弃之。然后就用下面的目录，将文件还是放置到本应用下：
``` java
File file = new File("data/data/com.weizu.intent", filename);
//File file  = new File(Environment.getExternalStorageDirectory(), filename); //抛弃
```

布局文件是ImageView+Button，着重点也就是在Button的事件监听函数中：
至于ImageView中是如何设置网站的链接图片的，就参考下一讲吧。
这里就给出MainActivity.java，还是一样的访问网络的操作放置到子线程中，然后保存文件就在主线程中完成。使用Message对象传递消息的时候，不能直接设置消息是obj，要不然会报主线程中不能访问网络的错误，也就是在HttpURLConnection得到的InputStream是能代表网络的连接对象的，需要包装一下，这里如何包装就放置到了下下讲，看代码:
``` java
package com.weizu.intent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{
	private MyImageView img;
	private Button btn;
	private final String path = "http://pics.sc.chinaz.com/files/pic/pic9/201811/bpic9300.jpg";
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){
				Bitmap bp = (Bitmap)msg.obj;
				//转换
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bp.compress(Bitmap.CompressFormat.PNG, 100, baos);
				InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
				saveFile("weizu.png", isBm);
			}else{
				Toast.makeText(MainActivity.this, "获取失败", 20).show();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (MyImageView)findViewById(R.id.image);
		img.setImageURL(path);
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//子线程中完成联网获取数据的操作。
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							URL url = new URL(path);
							HttpURLConnection connection = (HttpURLConnection)url.openConnection();
							connection.setRequestMethod("GET");
							if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
								InputStream in = connection.getInputStream();
								Bitmap bp = BitmapFactory.decodeStream(in);
								//发送消息给主线程，以更新UI
								Message message = Message.obtain();
								message.what = 1;
								message.obj = bp;
								handler.sendMessage(message);
							}
						} catch (Exception e) {
							handler.sendEmptyMessage(2);
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
	}
	public void saveFile(String filename, InputStream in){
		FileOutputStream fos = null;
		try {
			File file = new File("data/data/com.weizu.intent", filename);
			fos=new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while((in.read(buffer)) != -1){
				fos.write(buffer);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
```


存储权限和联网权限：
``` xml
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.INTERNET"/>
```


效果，直接在Eclipse中用DBMS->File Explorer->data/data/com.weizu.intent目录下就可以看见weizu.png：
![](/images/201908/2019-08-21_213335.png)

 





