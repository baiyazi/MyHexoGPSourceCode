---
title: hexo-NexT主题各类标签用法
author: 无涯明月
comments: true
date: 2019-5-7 09:07:37
categories: "Hexo教程"
tags: 
    - hexo
---
# 使用fontawesome字体
一句话将Font Awesome加入您的网页中。您完全不用下载或者安装任何东西！
将以下代码粘贴到网页HTML代码的 &lt; head&gt;部分.

>&lt;link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

参考下面的示例，然后开始使用Font Awesome吧!

## 基本图标
您可以将Font Awesome图标使用在几乎任何地方，只需要使用CSS前缀 fa ，再加上图标名称。 Font Awesome是为使用内联元素而设计的。我们通常更喜欢使用 &lt;i&gt; ，因为它更简洁。 但实际上使用 &lt;span&gt; 才能更加语义化。

<i class="fa fa-camera-retro"></i> fa-camera-retro
>&lt;i class="fa fa-camera-retro">&lt;/i> fa-camera-retro

## 大图标
如果您修改了图标容器的字体大小，图标大小会随之改变。同样的变化也会发生在颜色、阴影等其它任何CSS支持的效果上。
使用 fa-lg (33%递增)、fa-2x、 fa-3x、fa-4x，或者 fa-5x 类 来放大图标。
<i class="fa fa-camera-retro fa-lg"></i> fa-lg
<i class="fa fa-camera-retro fa-2x"></i> fa-2x
<i class="fa fa-camera-retro fa-3x"></i> fa-3x
<i class="fa fa-camera-retro fa-4x"></i> fa-4x
<i class="fa fa-camera-retro fa-5x"></i> fa-5x

>&lt;i class="fa fa-camera-retro fa-lg">&lt;/i> fa-lg
&lt;i class="fa fa-camera-retro fa-2x">&lt;/i> fa-2x
&lt;i class="fa fa-camera-retro fa-3x">&lt;/i> fa-3x
&lt;i class="fa fa-camera-retro fa-4x">&lt;/i> fa-4x
&lt;i class="fa fa-camera-retro fa-5x">&lt;/i> fa-5x

如果图标的底部和顶部被截断了，您需要增加行高来解决此问题。

## 固定宽度
使用 fa-fw 可以将图标设置为一个固定宽度。主要用于不同宽度图标无法对齐的情况。 尤其在列表或导航时起到重要作用。

<div class="list-group">
  <a class="list-group-item" href="#"><i class="fa fa-home fa-fw"></i>&nbsp; Home</a>
  <a class="list-group-item" href="#"><i class="fa fa-book fa-fw"></i>&nbsp; Library</a>
  <a class="list-group-item" href="#"><i class="fa fa-pencil fa-fw"></i>&nbsp; Applications</a>
  <a class="list-group-item" href="#"><i class="fa fa-cog fa-fw"></i>&nbsp; Settings</a>
</div>

>&lt;div class="list-group">
  &lt;a class="list-group-item" href="#">&lt;i class="fa fa-home fa-fw">&lt;/i>&nbsp; Home&lt;/a>
  &lt;a class="list-group-item" href="#">&lt;i class="fa fa-book fa-fw">&lt;/i>&nbsp; Library&lt;/a>
  &lt;a class="list-group-item" href="#">&lt;i class="fa fa-pencil fa-fw">&lt;/i>&nbsp; Applications&lt;/a>
  &lt;a class="list-group-item" href="#">&lt;i class="fa fa-cog fa-fw">&lt;/i>&nbsp; Settings&lt;/a>
&lt;/div>

## 用于列表
使用 fa-ul 和 fa-li 便可以简单的将无序列表的默认符号替换掉。

<ul class="fa-ul">
  <li><i class="fa-li fa fa-check-square"></i>List icons</li>
  <li><i class="fa-li fa fa-check-square"></i>can be used</li>
  <li><i class="fa-li fa fa-spinner fa-spin"></i>as bullets</li>
  <li><i class="fa-li fa fa-square"></i>in lists</li>
</ul>
``` html
<ul class="fa-ul">
  <li><i class="fa-li fa fa-check-square"></i>List icons</li>
  <li><i class="fa-li fa fa-check-square"></i>can be used</li>
  <li><i class="fa-li fa fa-spinner fa-spin"></i>as bullets</li>
  <li><i class="fa-li fa fa-square"></i>in lists</li>
</ul>
```

## 边框与对齐
使用 fa-border 以及 pull-right 或 pull-left 可以轻易构造出引用的特殊效果。

<i class="fa fa-quote-left fa-3x pull-left fa-border"></i>
...tomorrow we will run faster, stretch out our arms farther...
And then one fine morning— So we beat on, boats against the
current, borne back ceaselessly into the past.
``` html
<i class="fa fa-quote-left fa-3x pull-left fa-border"></i>
...tomorrow we will run faster, stretch out our arms farther...
And then one fine morning— So we beat on, boats against the
current, borne back ceaselessly into the past.
```

## 动画
使用 fa-spin 类来使任意图标旋转，现在您还可以使用 fa-pulse 来使其进行8方位旋转。尤其适合 fa-spinner、fa-refresh 和 fa-cog 。

<i class="fa fa-spinner fa-spin"></i>
<i class="fa fa-circle-o-notch fa-spin"></i>
<i class="fa fa-refresh fa-spin"></i>
<i class="fa fa-cog fa-spin"></i>
<i class="fa fa-spinner fa-pulse"></i>
``` html
<i class="fa fa-spinner fa-spin"></i>
<i class="fa fa-circle-o-notch fa-spin"></i>
<i class="fa fa-refresh fa-spin"></i>
<i class="fa fa-cog fa-spin"></i>
<i class="fa fa-spinner fa-pulse"></i>
```


