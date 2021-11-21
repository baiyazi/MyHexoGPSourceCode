---
title: scrapy-7 |  Response内置CSS选择器
date: 2019-6-9 15:31:11
comments: true
categories: "scrapy"
tags: 
    - scrapy
---
和`xpath`类似，`CSS`在`Response`中也集成了。CSS选择器的语法更加简单，但是功能不如xpath强大。

`CSS`即层叠样式表。
## 语法
<link href="/css/spider.css" rel="stylesheet" type="text/css">
<table border=1 class = "three"><tbody><tr class="head"><td>表达式</td><td>描述</td><td>例子</td></tr><tr><td>*</td><td>选中所有元素</td><td>css('*')</td></tr><tr><td>Element</td><td>选中Element元素</td><td>css('img')</td></tr><tr><td>E1, E2</td><td>选中E1和E2元素</td><td>css('div,p')</td></tr><tr><td>E1 E2</td><td>选中E1<b>后代元素</b>中的E2元素</td><td>css('div input')</td></tr><tr><td>E1 > E2</td><td>选中E1<b>子元素</b>后的E2元素</td><td>css('div > span')</td></tr><tr><td>E1 + E2</td><td>选中E1兄弟元素中的E2元素</td><td>css('div + b')</td></tr><tr><td>.Class</td><td>选中class属性<b>包含</b>Class的元素</td><td>css('.info')</td></tr><tr><td>#id</td><td>选中id属性为id的元素</td><td>css('name')</td></tr><tr><td>[Attr]</td><td>选中包含Attr属性的元素</td><td>css('[id]')</td></tr><tr><td>[Attr = value]</td><td>选中包含Attr属性且值<b>为</b>value的元素</td><td>css("[class = 'name']")</td></tr><tr><td>[Attr ~= value]</td><td>选中包含Attr属性且值<b>包含</b>value的元素</td><td>css("[class ~= 'name']")</td></tr><tr><td>E:nth-child(n)</td><td>选中E元素，且该元素必须是其父元素第n个子元素</td><td>css("li:nth-child(2)")</td></tr><tr><td>E:nth-last-child(n)</td><td>选中E元素，且该元素必须是其父元素倒数第n个子元素</td><td>css("li:nth-last-child(2)")</td></tr><tr><td>E:first-child</td><td>选中E元素，且该元素必须是其父元素第1个子元素</td><td>css("li:first-child")</td></tr><tr><td>E:last-child</td><td>选中E元素，且该元素必须是其父元素倒数第1个子元素</td><td>css("li:last-child")</td></tr><tr><td>E:empty</td><td>选中没有子元素的E元素</td><td>css("div:empty")</td></tr><tr><td>E<b>::</b>text</td><td>选中E元素的文本节点</td><td>css("p::text")</td></tr></tbody></table>

不难发现，其实和[CSS的选择器](http://www.w3school.com.cn/cssref/css_selectors.asp)有着一拼
### 案例
由于比较简单，和xpath类似，这里就举一个小案例：
``` python
from scrapy.selector import Selector
from scrapy.http import HtmlResponse

body = """
<ul>
    <li class="name first"><b>Name</b></li>
    <li class="sex">M</li>
    <li class="age">23</li>
</ul>
"""
response = HtmlResponse(url='baiyazi.top', body=body, encoding='utf8')
a = response.css("li:nth-child(2)").extract()  
#['<li class="sex">M</li>']

a = response.css("li[class ~= first]").extract()  
#['<li class="name first"><b>Name</b></li>']

a = response.css("li::text").extract()  
#['M', '23']  因为第一个节点包含子节点
```

