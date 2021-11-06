---
title: scrapy-5 |  Response内置Selector
date: 2019-6-6 09:50:50
comments: true
categories: "scrapy"
tags: 
    - scrapy
---

前面大致介绍了Selector，但是在实际开发中，我们基本上都是不需要手动创建Selector对象的。

`Response`对象的`selector`属性被第一次访问时，`Response`对象内部就会自动创建一个`Selector`对象，并且将该`Selector`对象缓存。
# 对比一下
第四讲中，我们使用的是`HtmlResponse`对象构造`Selector`，如下：
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

接下来，我们不创建`Selector`，使用`Response`内置的`Selector`，如下：
``` python
from scrapy.http import HtmlResponse

text = """
<ul>
    <li>Python</li>
    <li>Java</li>
    <li>JavaScript</li>
</ul>
"""
response = HtmlResponse(url="baidu.com", body=text, encoding="utf8")
selector = response.selector
print(selector)
#<Selector xpath=None data='<html><body><ul>\n    <li>Python</li>\n   '>
```

结果是一致的，也就是说，`Response`内部会自动创建`Selector`对象。

# 不妨追踪源码：
①在`pycharm`中，按住`ctrl``，点击上面源码中的`HtmlResponse`追踪，可以看见：
``` python
class HtmlResponse(TextResponse):
    pass
```

②不难发现，`HtmlResponse`是继承了`TextResponse`对象，继续追踪，可以看见：
``` python
class TextResponse(Response):

    def __init__(self, *args, **kwargs):
        ...
        self._cached_selector = None
        ...
    @property
    def selector(self):
        from scrapy.selector import Selector
        if self._cached_selector is None:
        	self._cached_selector = Selector(self)
        return self._cached_selector
```

（摘取部分）
## 使用xpath案例
不难看出`Response`是以自身参数创建的`Selector`对象。
也就是说我们可以使用`Response`内部内置的`Selector`对象，然后使用`XPath`和`CSS`方法。如下：
``` python
from scrapy.http import HtmlResponse

text = """
<ul>
    <li>Python</li>
    <li>Java</li>
    <li>JavaScript</li>
</ul>
"""
response = HtmlResponse(url="baidu.com", body=text, encoding="utf8")
selector = response.selector
res = selector.xpath(".//li/text()").re("J\w+")
print(res)
#['Java', 'JavaScript']
```

## 另一种方式：
但是，为了方便用户使用，`Response`对象提供了`xpath`和`css`方法，他们分别调用内置`Selector`对象的`xpath`和`css`方法。案例：
``` python
response = HtmlResponse(url="baidu.com", body=text, encoding="utf8")
res = response.xpath(".//li/text()").re("J\w+")
print(res)
#['Java', 'JavaScript']
```

（上面有部分没写，和上一个案例的一样）
不妨还是追踪一下源码：
追踪到`TextResponse`可以看见，构成`xpath`的源码，如下：
``` python
def xpath(self, query, **kwargs):
	return self.selector.xpath(query, **kwargs)

def css(self, query):
	return self.selector.css(query)
```

<span class="title2">提示：</span>&nbsp;&nbsp;`css`和`xpath`都是选择器，用于提取数据。

下面，我们就来介绍这两个选择器。