---
title: Hexo官网阅读笔记 | 来必力
author: weizu
comments: true
date: 2019-6-14 19:23:15
categories: "Hexo教程"
tags: 
    - hexo
---
# 1. 不要渲染帖子
如果您不希望处理您的帖子，可以`layout: false`在前面设置。

我测试了一下，很好用，也就是页面所有的CSS、js都需要自己定义。也就是自己写一个页面内容+渲染效果。
下面一起看看，在每一个md文件的开头的YAML都有哪些内容：
<table><thead><tr><th><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">设置</font></font></th><th><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">描述</font></font></th><th><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">默认</font></font></th></tr></thead><tbody><tr><td><code>layout</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">布局</font></font></td><td></td></tr><tr><td><code>title</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">标题</font></font></td><td></td></tr><tr><td><code>date</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">发布日期</font></font></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">文件创建日期</font></font></td></tr><tr><td><code>updated</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">更新日期</font></font></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">文件更新日期</font></font></td></tr><tr><td><code>comments</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">为帖子启用评论功能</font></font></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">真正</font></font></td></tr><tr><td><code>tags</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">标签（不适用于页面）</font></font></td><td></td></tr><tr><td><code>categories</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">分类（不适用于页面）</font></font></td><td></td></tr><tr><td><code>permalink</code></td><td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">覆盖帖子的默认永久链接</font></font></td><td></td></tr></tbody></table>
```

```
