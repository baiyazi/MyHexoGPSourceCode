---
title: Hexo'压缩'代码
author: weizu
comments: true
cover: true
top: true
date: 2019-7-22 14:53:44
categories: "Hexo"
tags: 
    - hexo
---

在写文章的时候避免不了需要使用表格来展示数据列表，比如想展示这样一个表格：
<table>
<thead>
<tr>
<th>配置选项</th>
<th>默认值</th>
<th>描述</th>
</tr>
</thead>
<tbody><tr>
<td>`title`</td>
<td><code>`Markdown`</code> 的文件标题</td>
<td>文章标题，强烈建议填写此选项</td>
</tr>
<tr>
<td>date</td>
<td>文件创建时的日期时间</td>
<td>发布时间，强烈建议填写此选项，且最好保证全局唯一</td>
</tr>
</tbody>
</table>

在这个主题中可以直接写，不用压缩，下面给出上面表格的源码：
``` html
<table>
<thead>
<tr>
<th>配置选项</th>
<th>默认值</th>
<th>描述</th>
</tr>
</thead>
<tbody><tr>
<td>title</td>
<td><code>Markdown</code> 的文件标题</td>
<td>文章标题，强烈建议填写此选项</td>
</tr>
<tr>
<td>date</td>
<td>文件创建时的日期时间</td>
<td>发布时间，强烈建议填写此选项，且最好保证全局唯一</td>
</tr>
</tbody>
</table>
```

但是在比如：`Next`的主题中就不行，需要自己压缩代码，不然会有很大的留白。
可能是作者在后台加入了相关的逻辑。

___

虽然这里用不到了，但是还是将'压缩'代码的`Python`代码写下来：
``` Python
sourceText = """
<thead>
<tr>
<th>配置选项</th>
<th>默认值</th>
<th>描述</th>
</tr>
</thead>
"""
import re
partten = re.compile(r'\s')
resu = partten.sub(r'', sourceText)

print(resu)
# <thead><tr><th>配置选项</th><th>默认值</th><th>描述</th></tr></thead>
```
