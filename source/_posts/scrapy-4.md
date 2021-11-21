---
title: scrapy-4 |  Selector提取数据
date: 2019-6-6 08:42:10
comments: true
categories: "scrapy"
tags: 
    - scrapy
---
数据处理

Python中常用的处理HTTP解析库：
* BeautifulSoup
很流行的HTTP解析库，API简洁易用，但是解析速度比较慢。
* lxml
由C语言编写的xml解析库，API相对复杂，解析速度快。

Scrapy的Selector类，基于lxml库构建，并简化了API接口。使用时，通过XPath或CSS选择器，先选择数，然后提取数据。

接下来我们看看Selector对象：
# 1. 构造Selector对象
`Selector`类位于`scrapy.selector`模块，构造`Selector`对象有两种方式：
不妨追踪看看它的源码：
``` python
def __init__(self, response=None, text=None, type=None, root=None, _root=None, **kwargs):
```
## 1. 使用response对象
``` python
from scrapy.selector import Selector
from scrapy.http import HtmlResponse

text = """
<ul>
    <li>Python</li>
    <li>Java</li>
    <li>JavaScript</li>
</ul>
"""
response = HtmlResponse(url="baidu.com", body=text, encoding="utf8")
selector = Selector(response = response)
print(selector) 
#<Selector xpath=None data='<html><body><ul>\n    <li>Python</li>\n   '>
```

## 2. 使用文档字符串方式
``` python
from scrapy.selector import Selector

text = """
<ul>
    <li>Python</li>
    <li>Java</li>
    <li>JavaScript</li>
</ul>
"""

selector = Selector(text = text)
print(selector) 
#<Selector xpath=None data='<html><body><ul>\n    <li>Python</li>\n   '>
```
# 2. 使用Selector对象，进行数据处理
<h2 id="xpath">使用XPath选择数据</h2><link href="/css/spider.css" rel="stylesheet" type="text/css"><span>XPath选择器：</span>
<table>
  <tr><td>//</td><td>表示子孙中</td></tr>
  <tr><td>.//</td><td>表示当前对象的子孙中</td></tr>
  <tr><td>/</td><td>表示儿子</td></tr>
  <tr><td>/div</td><td>表示儿子的div标签</td></tr>
  <tr><td>/div[@id="content"]</td><td>表示儿子div标签且id='content'</td></tr>
  <tr><td>/div[@class='show']</td><td>表示儿子div标签，且class='show'</td></tr>
  <tr><td>//div/text()</td><td>获取div标签的文本内容</td></tr>
  </table>

下面看看案例：
### 案例：

``` python
from scrapy.selector import Selector

text = """
<ul>
    <li>Python</li>
    <li>Java</li>
    <li>JavaScript</li>
</ul>
"""

selector = Selector(text = text)
lis = selector.xpath("//li/text()") 
# 选中文档中所有的li标签的文本内容
for i in lis:
    print(i)
#<Selector xpath='//li/text()' data='Python'>
# <Selector xpath='//li/text()' data='Java'>
# <Selector xpath='//li/text()' data='JavaScript'>

li = selector.xpath("//li/text()").extract() # 从Selector中内容提取
print(li)
#['Python', 'Java', 'JavaScript']
```


``` python
from scrapy.selector import Selector

text = """
<ul>
    <li>Python</li>
    <li class = "weizu">Java</li>
    <li>JavaScript</li>
</ul>
"""

selector = Selector(text = text)
lis = selector.xpath("//li[@class='weizu']/text()") 
# 选中文档中所有的class='weizu'的li标签的文本内容
for i in lis:
    print(i)
# <Selector xpath='//li/text()' data='Java'>


li = selector.xpath("//li[@class='weizu']/text()").extract() #内容提取
print(li)
#['Java']

li = selector.xpath("//li[@class='weizu']/text()").extract_first() #内容提取
print(li)
#Java
```

下面还需要另外介绍一下提取数据：
## 使用extract()提取数据
  <table>
  <tr><td>obj.extract()</td><td>列表中的每一个对象转换字符串，构成字符串列表</td></tr>
  <tr><td>obj.extract_first()</td><td>列表中的第一个元素转换成字符串</td></tr>
  <tr><td>obj.re()</td><td>列表中的每一个对象转换字符串，每个字符串正则匹配</td></tr>
  <tr><td>obj.re_first()</td><td>列表中的第一个元素转换成字符串，该字符串正则匹配</td></tr>
</table>
  
在上面的例子中，我们已经使用过了`extract()`提取数据内容。
下面我们看看`re()`的用法：
### 案例：

``` python
from scrapy.selector import Selector

text = """
<ul>
    <li>Python <b>价格：200.00元</b></li>
    <li class = "weizu">Java <b>价格：300.00元</b></li>
    <li>JavaScript <b>价格：400.00元</b></li>
</ul>
"""

selector = Selector(text = text)
lis = selector.xpath(".//li/b/text()").re('\d+\.\d+') 
# 选中文档中所有的li标签，只提取数字
print(lis)
#['200.00', '300.00', '400.00']

li = selector.xpath(".//li/b/text()").re_first('\d+\.\d+') 
# 选中文档中第一个li标签，只提取数字
print(li)
#200.00
```