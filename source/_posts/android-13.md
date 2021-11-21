---
title: android | InputStream和Bitmap之间的相互转换
date: 2019-8-21 16:59:52
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---
>在写11讲，也就是下载文件的时候，遇到了问题。
①read-only file system
②NetworkOnMainThreadException
但是实际上我也是用adb shell改了文件夹的权限，但是没有用。
主要问题是第二个，我在按钮的OnClick中实际上是开了一个子线程，然后访问的网络，但是实际上是失败了。百度了一下说是：**OnClick实际上是主线程调用的** What？没毛病呀，我又不是用的主线程。
然后，想起了12讲的，写的ImageView加载图片是成功了的，于是就在上一个案例的基础上进行测试，最后发现原来是自己在message中传入的是InputStream到Handler中就会出现主线程无法访问网络的错误。
于是，大胆猜测，也就是说，虽然最终访问网络的流InputStream我们得到了本地，但是链接未短或者说这个InputStream还是能代表网络连接，故而我们需要流的转换。
果不其然，就成功了。


## InputStream->Bitmap
``` java
InputStream in = connection.getInputStream();
Bitmap bm = BitmapFactory.decodeStream(in);
```

## Bitmap->InputStream
``` java
Bitmap bm = xxx;
ByteArrayOutputStream baos = new ByteArrayOutputStream();
bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
InputStream in = new ByteArrayInputStream(baos.toByteArray());
```







