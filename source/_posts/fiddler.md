---
title: Fiddler的安装与使用
date: 2019-7-24 17:07:05
author: 无涯明月
comments: true
categories: "Fiddler"
tags: 
    - Fiddler
---

# 前面的话
以前写的简单的爬虫程序，都是用的浏览器的自带的工具来抓包分析的。而且写得程序没有什么高大上的，很普通，也就用不到`Fiddler`这个软件。
今天，需要写一个案例，然后就想着还是用一下。拓展拓展眼界。

# 简介
`Fiddler`是一款强大`Web`调试工具，它能记录所有客户端和服务器的`HTTP`请求。 Fiddler启动的时候，默认IE的代理设为了`127.0.0.1:8888`，而其他浏览器是需要手动设置。
# 安装与使用
安装地址：[下载页面](https://www.telerik.com/download/fiddler)
需要注意的是，需要选择：`Mobile application development/debugging`
其他的选择了我这里下载没反应。

安装就不说了，直接看简单的设置：
## 配置Fiddler
打开`Fiddler`，选择工具栏`Tools`=>`Options`=>`HTTPS`
选中`Capture HTTPS CONNECTs` (捕捉HTTPS连接)，
选中`Decrypt HTTPS traffic`（解密HTTPS通信）
另外我们要用`Fiddler`获取本机所有进程的`HTTPS`请求，所以中间的下拉菜单中选中`...from all processes` （从所有进程）
选中下方`Ignore server certificate errors`（忽略服务器证书错误）
![](/images/201907/2019-07-24_172142.png)

然后，为 `Fiddler` 配置`Windows`信任这个根证书解决安全警告：`Actions`=>`Trust Root Certificate`（受信任的根证书）。
还是在`Options`中，选择`Connections`:
选中`Allow remote computers to connect`（允许远程连接）
`Act as system proxy on startup`（作为系统启动代理）
![](/images/201907/2019-07-24_172628.png)

重启`Fiddler`，使配置生效（这一步很重要，必须做）。
## 配置浏览器
打开`Fiddler`，在浏览器中输入：http://127.0.0.1:8888/ ， 可以看见下面的页面：
![](/images/201907/2019-07-24_173402.png)
然后就是点击上面的链接，下载证书，然后安装证书到电脑上。
![](/images/201907/2019-07-24_173702.png)

## 测试
不放输入任意一个网址，测试一下，看是否有抓包。

## 图标说明
![](/images/201907/1415062-20180630153923232-386817153.png)


