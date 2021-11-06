---
title: scrapy-6 |  Response内置XPath选择器
date: 2019-6-7 08:55:20
comments: true
categories: "scrapy"
tags: 
    - scrapy
---
和Selector类似，为了方便用户编码操作，`XPath`在`Response`中也集成了。
`Xpath（XML Path Language）XML`路径语言，是一种用来确定`xml`文档中某元素位置的语言。
**提示：** `HTML`属于`xml`
在<a href="/2019/scrapy-4/#xpath">第四讲案例</a>中我们已经按照实际举过例子，下面我们看看具体的语法：
## 语法
 <table>
  <tr><td>表达式</td><td>描述</td></tr>
  <tr><td>/</td><td>选中文档的根（root）</td></tr>
  <tr><td>.</td><td>选中当前节点</td></tr>
  <tr><td>..</td><td>选中当前节点的父节点</td></tr>
  <tr><td>Element</td><td>选中子节点中所有Element元素节点</td></tr>
  <tr><td>//Element</td><td>选中后代节点中所有的Element元素节点</td></tr>
  <tr><td>*</td><td>选中所有元素子节点</td></tr>
  <tr><td>text()</td><td>选中所有文本子节点</td></tr>
  <tr><td>@Attr</td><td>选中名为Attr的属性节点</td></tr>
  <tr><td>@*</td><td>选中所有属性节点</td></tr>
  <tr><td>[谓语]</td><td>查找特定的节点或者包含特定值的节点</td></tr>
</table>

下面举一些案例：
### 案例
``` python
from scrapy.http import HtmlResponse

text = """
<ul>
    <li class="one">Python</li>
    <li class="two">Java</li>
    <li id = "three">JavaScript</li>
</ul>
"""
response = HtmlResponse(url="baidu.com", body=text, encoding="utf8")
res = response.xpath("//li").extract() 
# 找到所有的li，为了便于观察打印的结果，这里均抽取内容

res = response.xpath("//li/text()").extract() # 找到所有的li中的文本内容
res = response.xpath("/*").extract()  # 根下的所有节点
res = response.xpath("//@class").extract()  #['one', 'two']
res = response.xpath("//@*").extract()  #['one', 'two', 'three']

##[谓语]
# 操作元素属性
res = response.xpath("//li[@class='one']").extract() 
# ['<li class="one">Python</li>']

# 列表集下标(从1开始)
res = response.xpath("//li[1]").extract()  
#['<li class="one">Python</li>']

# last() 选中最后一个
res = response.xpath("//li[last()]").extract()  
#['<li id="three">JavaScript</li>']

# position()函数
res = response.xpath("//li[position()<=2]").extract()  # 选中前两个元素 
#['<li class="one">Python</li>', '<li class="two">Java</li>']
```

除此之外，`xpath`还提供了很多常用函数：
## 其他
① 取多个路径
使用“|”运算符可以选取多个路径
``` python
xpath(‘//div|//table’)  #选取所有的div和table节点
```

② 轴 （定义相对于当前节点的节点集）
<table border=1 class = "three"><tbody><tr class="head"><td>轴名称</td><td>表达式</td><td>描述</td></tr><tr><td>ancestor<br></td><td>xpath(‘./ancestor::*’)</td><td>选取当前节点的所有先辈节点（父、祖父）</td></tr><tr><td>ancestor-or-self</td><td>xpath(‘./ancestor-or-self::*’)</td><td>选取当前节点的所有先辈节点以及节点本身</td></tr><tr><td>attribute</td><td>xpath(‘./attribute::*’)</td><td>选取当前节点的所有属性</td></tr><tr><td>child</td><td>xpath(‘./child::*’)</td><td>返回当前节点的所有子节点</td></tr><tr><td>descendant</td><td>xpath(‘./descendant::*’)</td><td>返回当前节点的所有后代节点（子节点、孙节点）</td></tr><tr><td>following</td><td>xpath(‘./following::*’)</td><td>选取文档中当前节点结束标签后的所有节点<br></td></tr><tr><td>following-sibing</td><td>xpath(‘./following-sibing::*’)</td><td>选取当前节点之后的兄弟节点</td></tr><tr><td>parent</td><td>xpath(‘./parent::*’)</td><td>选取当前节点的父节点</td></tr><tr><td>preceding</td><td>xpath(‘./preceding::*’)</td><td>选取文档中当前节点开始标签前的所有节点</td></tr><tr><td>preceding-sibling</td><td>xpath(‘./preceding-sibling::*’)</td><td>选取当前节点之前的兄弟节点</td></tr><tr><td>self</td><td>xpath(‘./self::*’)</td><td>选取当前节点</td></tr></tbody></table>
③功能函数
<table border=1 class = "three"><tbody><tr class="head"><td>函数</td><td>用法</td><td>解释</td></tr><tr><td>starts-with</td><td>xpath(‘//div[starts-with(@id,”ma”)]‘)</td><td>选取id值以ma开头的div节点<br></td></tr><tr><td>contains<br></td><td>xpath(‘//div[contains(@id,”ma”)]‘)</td><td>选取id值包含ma的div节点</td></tr><tr><td>and<br></td><td>xpath(‘//div[contains(@id,”ma”) and contains(@id,”in”)]‘)</td><td>选取id值包含ma和in的div节点</td></tr><tr><td>text()</td><td>xpath(‘//div[contains(text(),”ma”)]‘)</td><td>选取节点文本包含ma的div节点</td></tr></tbody></table>
---

当然，在实际操作用，有更加人性化的操作去获取元素的`xpath`书写
在浏览器中，审查元素，在代码中找到元素节点，然后`Copy`->`Copy Xpath`，如下面的就是我复制的：
>//*[@id="asideCategory"]/h3

xpath文档：https://www.w3.org/TR/xpath/all/