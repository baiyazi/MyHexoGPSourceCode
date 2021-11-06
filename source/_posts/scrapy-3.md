---
title: scrapy-3 |  Spider开发流程
date: 2019-6-5 17:46:10
comments: true
categories: "scrapy"
tags: 
    - scrapy
---

感觉还差点东西，觉得流程这个东西需要搞出来。
说Spider开发的流程，这里就不得不提一下爬虫的执行流程和Scrapy框架的工作原理。如下：
# 爬虫的执行流程
## 1. 下载页面
在爬取内容之前，首先需要根据网页URL，下载网页。
## 2. 提取页面数据
当网页下载完成，就需要对页面内容和结构进行分析，然后提取我们需要的内容。
## 3. 提取链接
通常而言，我们需要的数据都是需要爬取多个页面，页面中包含相关页面的链接的情况很常见，然后我们就需要把页面中的某些链接提取出来，然后循环1-2-3。

在爬取网页的时候需要考虑如何去重、网页的搜索策略（深度优先和广度优先）、爬虫边界等
# Scrapy框架的工作原理
爬虫可以不使用scrapy框架，但是从头开发一个爬虫程序很麻烦。scrapy框架是比较优秀的爬虫框架，有人造好了轮子，我们就不需要再去造了。
毫无疑问，使用框架可以降低开发成本，提高程序的质量，让我们更专注于爬虫的业务逻辑。
## Scrapy框架的组成
百度了一张图片：
![e](/images/201906/1036857-20171109221422778-1731419400.png "scrapy框架图示")
<table>
  <tr><td>ENGINE</td><td>引擎，框架的核心</td><td>内部组件</td></tr>
  <tr><td>ENGINE</td><td>引擎，框架的核心</td><td>内部组件</td></tr>
  <tr><td>SCHEDULER</td><td>调度器，负责对SPIDER提交的下载请求进行调度</td><td>内部组件</td></tr>
  <tr><td>DOWNLOADER</td><td>下载器，负责下载页面</td><td>内部组件</td></tr>
  <tr><td>SPIDER</td><td>爬虫，页面请求下载，提取页面内数据</td><td>用户实现</td></tr>
  <tr><td>MIDDLEWARE</td><td>中间件，负责对Request和Response对象进行处理</td><td>可选组件</td></tr>
  <tr><td>ITEM PIPELINE</td><td>数据通道，负责对爬到的数据进行处理</td><td>可选组件</td></tr>
  </table>
对于使用框架的开发者而言，我们需要做的就是’关心‘Spider

# Spider开发流程
可以参看第一个例子，很容易得出结论，分四步：
①写一个类继承`scrapy.Spider`
继承`Scrapy`框架提供的一个`Spider`基类
②为`Spider`取名，即设置`name`
一个项目可以有多个`Spider`，每一个爬虫之间需要一个区分标志，即`name`。
③设置起始爬取点，即设置`start_ruls `
④实现页面解析函数，即`parse(self, response)`

