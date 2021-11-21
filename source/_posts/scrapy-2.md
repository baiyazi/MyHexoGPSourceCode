---
title: scrapy-2 |  第一个爬虫程序
date: 2019-6-5 08:43:33
comments: true
categories: "scrapy"
tags: 
    - scrapy
---
安装完成了，接下来，我们开始写程序。

``` text
#1 查看帮助
    scrapy -h
    scrapy <command> -h
#2 有两种命令：其中Project-only必须切到项目文件夹下才能执行，而Global的命令则不需要
    Global commands:
        startproject #创建项目
        genspider    #创建爬虫程序
        settings     #如果是在项目目录下，则得到的是该项目的配置
        runspider    #运行一个独立的python文件，不必创建项目
        shell        #scrapy shell url地址  在交互式调试，如选择器规则正确与否
        fetch        #独立于程单纯地爬取一个页面，可以拿到请求头
        view         #下载完毕后直接弹出浏览器，以此可以分辨出哪些数据是ajax请求
        version      #scrapy version 查看scrapy的版本，scrapy version -v查看scrapy依赖库的版本
    Project-only commands:
        crawl        #运行爬虫，必须创建项目才行，确保配置文件中ROBOTSTXT_OBEY = False
        check        #检测项目中有无语法错误
        list         #列出项目中所包含的爬虫名
        edit         #编辑器，一般不用
        parse        #scrapy parse url地址 --callback 回调函数  #以此可以验证我们的回调函数是否正确
        bench        #scrapy bentch压力测试

#3 官网链接
    https://docs.scrapy.org/en/latest/topics/commands.html
```


# 创建项目
``` text
D:\scrapy>scrapy startproject scrapyOne
New Scrapy project 'scrapyOne', using template directory 'e:\python\lib\site-packages\scrapy\templates\project', created
 in:
    D:\scrapy\scrapyOne

You can start your first spider with:
    cd scrapyOne
    scrapy genspider example example.com
```

按照提示，`cd scrapyOne`， 然后执行：`scrapy genspider baidu baidu.com`
这里说明一下：`baidu`是应用名称， `baidu.com`是域名

由于我们需要编辑`py`文件，不妨使用`pycharm`打开这个项目。

然后，我们需要打开项目中的`settings.py`文件，在22行：`ROBOTSTXT_OBEY =’False‘`  #可以忽略或者不遵守`robots`协议
这样我们在执行爬虫项目的时候，才能看见效果。

打开spiders目录下我们的baidu.py文件，然后输出一下，代码如下：

``` python
# -*- coding: utf-8 -*-
import scrapy

class BaiduSpider(scrapy.Spider):
    name = 'baidu' #应用名称
    #允许爬取的域名（如果遇到非该域名的url则爬取不到数据）
    allowed_domains = ['baidu.com']
    #起始爬取的url
    start_urls = ['http://baidu.com']

    #访问起始URL并获取结果后的回调函数，该函数的response参数就是向起始的url发送请求后，获取的响应对象.该函数返回值必须为可迭代对象或者NUll
    def parse(self, response):
        print(response.text) #获取字符串类型的响应内容
```

打开pycharm的Treminal终端，输入：
```
D:\scrapy\scrapyOne>scrapy crawl chouti --nolog
```

就可以看见百度网页的源代码。

---
参考：
书籍：精通Scrapy网络爬虫 [刘硕]