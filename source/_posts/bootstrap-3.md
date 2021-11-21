---
title: Bootstrap格子系统（一）
date: 2019-4-30 10:48:11
comments: true
categories: "bootstrap"
tags: 
    - Bootstrap
---
# 一、格子系统(Grid system)
移动设备优先的特性是因为格子系统中的12列系统，5个默认的响应层以及很多的CSS类。
Bootstrap的网格系统使用一系列容器、行和列来布局和对齐内容。
It’s built with flexbox and is fully responsive. Below is an example and an in-depth look at how the grid comes together.
它是用flexbox构建的，是完全响应式。
><span>CSS属性：flexbox</span>
>box-flex 属性规定框的子元素是否可伸缩其尺寸。
提示：可伸缩元素能够随着框的缩小或扩大而缩写或放大。只要框中有多余的空间，可伸缩元素就会扩展来填充这些空间。
><span>浏览器支持</span>
目前没有浏览器支持 `box-flex` 属性。
Firefox 支持替代的 `-moz-box-flex` 属性。
Safari、Opera 以及 Chrome 支持替代的` -webkit-box-flex` 属性。
><span>案例：</span>
<div style="display:-moz-box; /* Firefox */display:-webkit-box; /* Safari and Chrome */display:box;">
	<p style="-moz-box-flex:1.0; /* Firefox */-webkit-box-flex:1.0; /* Safari and Chrome */box-flex:1.0;
	border:1px solid red;">hello</p>
	<p style="	-moz-box-flex:2.0; /* Firefox */	-webkit-box-flex:2.0; /* Safari and Chrome */box-flex:2.0;	border:1px solid blue;">flexbox</p>
</div>

上面flexbox案例的代码如下：
``` html
<div style="display:-moz-box; /* Firefox */
 			display:-webkit-box; /* Safari and Chrome */
 			display:box;">
	<p style="-moz-box-flex:1.0; /* Firefox */
			  -webkit-box-flex:1.0; /* Safari and Chrome */
			  box-flex:1.0;
	          border:1px solid red;">
		hello
	</p>
	<p style="-moz-box-flex:2.0; /* Firefox */	
	          -webkit-box-flex:2.0; /* Safari and Chrome */
	          box-flex:2.0;	
	          border:1px solid blue;">
		flexbox
	</p>
</div>
```
<span class="title">说明：</span>
在使用`hexo`中的`md文件`时，`&lt;style&gt;``&lt;/style&gt;`中的`css`样式需要放入外部`css`文件中，或者直接使用`style`。

---

下面正式学习Grid System。
## 案例：
使用第一章的模板，这里不给出模板了，代码如下：
```html
<div class="container">
  <div class="row">
    <div class="col-sm">
      One of three columns
    </div>
    <div class="col-sm">
      One of three columns
    </div>
    <div class="col-sm">
      One of three columns
    </div>
  </div>
</div>
```

产生三个等宽度的列，对大中小设备都适用，也就是你在测试时缩放窗口也是适用的。并且这些列，在页面的父元素（`.container`）中是居中的。
至于为什么对大中小设备都适用，可以查看下图，因为我们只设置了小设备，中大超大都没有设置，那么就会默认使用小设备的设置。

<table><thead><tr><th></th><th>Extra small<br><small>&lt;576px</small></th><th>Small<br><small>≥576px</small></th><th>Medium<br><small>≥768px</small></th><th>Large<br><small>≥992px</small></th><th>Extra large<br><small>≥1200px</small></th></tr></thead><tbody><tr><th>Max container width</th><td>None (auto)</td><td>540px</td><td>720px</td><td>960px</td><td>1140px</td></tr><tr><th>Class prefix</th><td><code>.col-</code></td><td><code>.col-sm-</code></td><td><code>.col-md-</code></td><td><code>.col-lg-</code></td><td><code>.col-xl-</code></td></tr><tr><th># of columns</th><td colspan="5">12</td></tr><tr><th>Gutter width</th><td colspan="5">30px (15px on each side of a column)</td></tr><tr><th>Nestable</th><td colspan="5">Yes</td></tr><tr><th>Column ordering</th><td colspan="5">Yes</td></tr></tbody></table>
超大屏没有，找大屏，大屏没有找中屏，中屏没有找小屏，小屏没有找col

### 1.1 等宽度
``` html
<div class="container">
  <div class="row">
    <div class="col">
      1 of 2
    </div>
    <div class="col">
      2 of 2
    </div>
  </div>
  <div class="row">
    <div class="col">
      1 of 3
    </div>
    <div class="col">
      2 of 3
    </div>
    <div class="col">
      3 of 3
    </div>
  </div>
</div>
```
<span class='title'>效果：</span>
![e](/images/201904/2019-04-30_104132.jpg "效果图1")

在没有设置宽度的时候，每一行的所有的列按个数平分宽度。

### 1.2 不等宽度
``` html
<div class="container">
  <div class="row">
    <div class="col">
      1 of 3
    </div>
    <div class="col-6">
      2 of 3 (wider)
    </div>
    <div class="col">
      3 of 3
    </div>
  </div>
  <div class="row">
    <div class="col">
      1 of 3
    </div>
    <div class="col-5">
      2 of 3 (wider)
    </div>
    <div class="col">
      3 of 3
    </div>
  </div>
</div>
```

<span class='title'>效果：</span>
![e](/images/201904/2019-04-30_104451.jpg "效果图2")

在设置宽度后，如`col-6`表示占`6`列的宽度，其余的两列没有指定，就平分剩下的`6`列。

### 1.3 可变宽度
``` html
<div class="container">
  <div class="row justify-content-md-center">
    <div class="col col-lg-2">
      1 of 3
    </div>
    <div class="col-md-auto">
      Variable width content
    </div>
    <div class="col col-lg-2">
      3 of 3
    </div>
  </div>
  <div class="row">
    <div class="col">
      1 of 3
    </div>
    <div class="col-md-auto">
      Variable width content
    </div>
    <div class="col col-lg-2">
      3 of 3
    </div>
  </div>
</div>
```

>Use col-{breakpoint}-auto classes to size columns based on the natural width of their content.
使用col-{breakpoint}-auto类，基于他们的内容自然宽度来调整列的大小。

为了方便查看效果，我这里都加了红色边框。
<span class='title'>效果1：大屏</span>
![e](/images/201904/2019-04-30_110100.jpg "效果图3")
大屏，对应`lg`，也就是此时的`lg`生效，第一行左右都占两列，由于中间没有设置`lg`，此时`md`生效，故而是：左右两列，中间按内容占列宽度，然后整个`div`在`container`中居中。（第二列同理）

现在我们调整浏览器宽度为中等设备宽度
<span class='title'>效果2：中屏</span>
![e](/images/201904/2019-04-30_110430.jpg "效果图4")
中屏，对应`md`，也就是中间的生效，其余的都是等宽度`col`，也就是中间适应内容占据宽度，剩余的宽度由左右两边的列平分。

调整浏览器宽度为小设备宽度。
<span class='title'>效果3：小屏</span>
![e](/images/201904/2019-04-30_110723.jpg "效果图5")

代码中没有使用`col-sm`，这就很有意思了。因为小屏是`col-sm`。
其实测试后发现：
* 如果没有设置`col-sm`样式在行中。只有大屏或者中屏的设置，那么就默认每一个大屏(或者中屏)中的列，都占据一行，也就是小屏的12列。
* 如果每一行设置的都是`col`，也即是等宽度，此时无论大中小还是特大屏幕都是平分，
* <span class="emphasis">换句话说：如果你想所有的大中小屏幕中你的视图的效果一样，从最小的设备到最大的设备都相同的网格，可以使用<code>.col</code>和<code>.col-*</code>类。</span>

### 1.4 多行等宽度
使用`.w-100`来插入新的一行
``` HTML
<div class="container">
	<div class="row"> 
		<div class="col red">col</div>
		<div class="col red">col</div>
		<div class="w-100"></div>
		<div class="col red">col</div>
		<div class="col red">col</div>
	</div>
</div>
```

### 1.5 混杂分析
``` html
<!-- Stack the columns on mobile by making one full-width and the other half-width -->
<div class="row">
  <div class="col-12 col-md-8">.col-12 .col-md-8</div>
  <div class="col-6 col-md-4">.col-6 .col-md-4</div>
</div>

<!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
<div class="row">
  <div class="col-6 col-md-4">.col-6 .col-md-4</div>
  <div class="col-6 col-md-4">.col-6 .col-md-4</div>
  <div class="col-6 col-md-4">.col-6 .col-md-4</div>
</div>

<!-- Columns are always 50% wide, on mobile and desktop -->
<div class="row">
  <div class="col-6">.col-6</div>
  <div class="col-6">.col-6</div>
</div>
```

由于没有设置大屏，也就是lg。所以我们的大屏和中屏都是加载中屏，然后显示。
小屏也没有设置，所以缩小窗口大小的时候，显示col-*的设置。
效果很简单，可以结合第一张的模板，自己设置边框，缩小浏览器窗口大小查看效果。

