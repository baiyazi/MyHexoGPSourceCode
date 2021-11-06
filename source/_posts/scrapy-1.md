---
title: scrapy-1 |  安装scrapy
date: 2019-6-4 20:40:49
comments: true
categories: "scrapy"
tags: 
    - scrapy
---
决定系统化的学习爬虫，使用Python实现。以下内容来自博客园、CSDN等。非原创。

# 1、安装wheel（安装后，便支持通过wheel文件安装软件）
``` text
pip3 install wheel
```

# 2、安装lxml、pyopenssl
lxml：解析XML的库，很强大，做爬虫BS4,selenium,XPATH都会用到
``` text
pip3 install lxml
pip3 install pyopenssl
```

# 3、安装pywin32
进入https://sourceforge.net/projects/pywin32/files/pywin32/ 
下载与已安装python版本对应的pywin64安装包，例如python3.4 64位对应的安装包为pywin32-218.win-amd64-py3.4.exe，下载完成后一直next安装。
运行cmd，然后输入python ，可以看见python的版本以及是多少位
我的如下：
``` text
Python 3.6.0 (v3.6.0:41df79263a11, Dec 23 2016, 08:06:12) [MSC v.1900 64 bit (AMD64)] on win32
```

我是python3.6， 是32位的。找到所需的pywin32所在页：https://sourceforge.net/projects/pywin32/files/pywin32/Build%20220/
我选择的是：pywin32-220.win32-py3.6.exe（32位）
而不是：pywin32-220.win-amd64-py3.6.exe（64位）
然后我们开始安装：
运行我们下载的pywin32-220.win-amd64-py3.6.exe，以完成安装。

## 测试：
import win32api
然后很不幸，报错了。
报错:ImportError: DLL load failed: %1 不是有效的 Win32 应用程序

尝试着解决：
在python包的页面中：https://www.lfd.uci.edu/~gohlke/pythonlibs/#pywin32
我看见了pywin32的包，故而也是可以用pip来安装的。
故而我们删除之前安装的东西，也就是在python安装目录中删除与pywin32相关的文件，
我的在：E:\python\Lib\site-packages，然后删除相关目录和文件后，在cmd中执行：

``` text
C:\Users\o\Downloads>pip3 install pywin32
Collecting pywin32
  Downloading https://files.pythonhosted.org/packages/b2/1a/7727b406391b0178b6ccb7e447e963df5ebf1ce9e0f615fc6ce23b6f6753
/pywin32-224-cp36-cp36m-win_amd64.whl (9.1MB)
     |████████████████████████████████| 9.1MB 61kB/s
Installing collected packages: pywin32
Successfully installed pywin32-224
```




# 4、下载twisted
下载twisted的wheel文件：https://www.lfd.uci.edu/~gohlke/pythonlibs/#twisted
打开链接，下载，然后安装：
>Twisted‑19.2.0‑cp36‑cp36m‑win32.whl
Twisted-19.2.0-cp36-cp36m-win_amd64.whl

cp后面跟的数字是python的版本，比如现在我的python版本是3.6，那么需要下载cp36；
如果还提示不支持platform，就把win32和win64的版本都下下来分别试一下。
我这里安装的是win32.whl

``` text
C:\Users\o>pip3 install C:\Users\o\Downloads\Twisted-19.2.0-cp36-cp36m-win_amd64.whl

Successfully installed Twisted-19.2.0
```
# 5、安装scrapy
``` text
C:\Users\o>pip3 install scrapy

Installing collected packages: pyasn1, pyasn1-modules, service-identity, queuelib, scrapy
Successfully installed pyasn1-0.4.5 pyasn1-modules-0.2.5 queuelib-1.5.0 scrapy-1.6.0 service-identity-18.1.0
```
# 6、测试

``` text
C:\Users\o>scrapy
Scrapy 1.6.0 - no active project

Usage:
  scrapy <command> [options] [args]

```
---
参考：
链接1：https://segmentfault.com/a/1190000010377113
链接2：https://www.cnblogs.com/liluning/p/8338219.html
下载pywin32地址：https://sourceforge.net/projects/pywin32/files/pywin32/Build%20220/
下载twisted地址：https://www.lfd.uci.edu/~gohlke/pythonlibs/#twisted

