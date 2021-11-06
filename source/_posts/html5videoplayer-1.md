---
title: html5 | 网页播放器开发 一
date: 2019-7-29 16:37:11
comments: true
categories: "html5"
tags: 
    - 网页播放器
---

访问[w3school网站](https://www.w3school.com.cn/html5/html_5_video.asp)，看见了需要的视频播放相关文档。下面就简单摘抄一些：
# 相关简介
HTML5 规定了一种通过 `video` 元素来包含视频的标准方法。
当前，`video` 元素支持三种视频格式：
* `Ogg` = 带有 `Theora` 视频编码和 `Vorbis` 音频编码的 `Ogg` 文件
* `MPEG4` = 带有 `H.264` 视频编码和 `AAC` 音频编码的 `MPEG 4` 文件
* `WebM` = 带有 `VP8` 视频编码和 `Vorbis` 音频编码的 `WebM` 文件

<table class="dataintable">
<tbody><tr>
<th>格式</th>
<th style="width:16%">IE</th>
<th style="width:16%">Firefox</th>
<th style="width:16%">Opera</th>
<th style="width:16%">Chrome</th>
<th style="width:16%">Safari</th>
</tr>

<tr>
<td>Ogg</td>
<td>No</td>
<td>3.5+</td>
<td>10.5+</td>
<td>5.0+</td>
<td>No</td>
</tr>

<tr>
<td>MPEG 4</td>
<td>9.0+</td>
<td>No</td>
<td>No</td>
<td>5.0+</td>
<td>3.0+</td>
</tr>

<tr>
<td>WebM</td>
<td>No</td>
<td>4.0+</td>
<td>10.6+</td>
<td>6.0+</td>
<td>No</td>
</tr>
</tbody></table>

很带感，如果需要正式化，还需要做浏览器适配相关的解决。不过我这里不考虑，先做出来像个东西了再说。

## video标签的属性
`autoplay=“autoplay” `&nbsp;&nbsp;&nbsp;&nbsp;如果出现该属性，则视频在就绪后马上播放。
`controls="controls"` &nbsp;&nbsp;&nbsp;&nbsp;如果出现该属性，则**向用户显示控件**，比如播放按钮。
`height="pixels" `&nbsp;&nbsp;&nbsp;&nbsp;设置视频播放器的高度。
`width="pixels" `&nbsp;&nbsp;&nbsp;&nbsp;设置视频播放器的宽度。
`loop="loop"`&nbsp;&nbsp;&nbsp;&nbsp;如果出现该属性，则当媒介文件完成播放后再次开始播放。
`preload="preload"`&nbsp;&nbsp;&nbsp;&nbsp;如果出现该属性，则视频在页面加载时进行加载，并预备播放。如果使用 "autoplay"，则忽略该属性。
`src="url"`&nbsp;&nbsp;&nbsp;&nbsp;要播放的视频的 URL。

### <i class="fa fa-compass "></i>  controls属性学习
功能：**向用户显示控件**，也就是说播放器的显示控件可设置不显示
下面我们看看文档中的原话：
>`controls` 属性规定浏览器应该为视频提供播放控件。
如果设置了该属性，则规定不存在作者设置的脚本控件。
浏览器控件应该包括：
播放/暂停/定位/音量/全屏/切换/字幕（如果可用）/音轨（如果可用）

#### 来做一个对比案例
<i class="fa fa-video-camera"></i> 添加`controls`的`video`

``` html
<video  width="320" height="240" controls>
  <source src="http://www.html5videoplayer.net/videos/madagascar3.mp4" type="video/mp4">
</video>
```

<video  width="320" height="240" controls>
  <source src="http://www.html5videoplayer.net/videos/madagascar3.mp4" type="video/mp4">
</video>

<i class="fa fa-video-camera"></i>不 添加`controls`的`video`
``` html
<video  width="320" height="240">
  <source src="http://www.html5videoplayer.net/videos/madagascar3.mp4" type="video/mp4">
</video>
```

<video  width="320" height="240">
  <source src="http://www.html5videoplayer.net/videos/madagascar3.mp4" type="video/mp4">
</video>

很显然，不使用`controls`属性，为定制播放器提供了一种可能。话不多说，就来用`css`定制看看。

下面用到video标签的一些方法和属性，这里给出链接地址：
[文档地址1](https://www.runoob.com/tags/ref-av-dom.html)
[文档地址2](https://www.runoob.com/tags/tag-video.html)
[文档地址3](https://www.runoob.com/tags/ref-av-dom.html)
[文档地址4](https://blog.csdn.net/weixin_39987434/article/details/90641925)


下面摘抄一些会用到的方法和属性：
#### video的部分方法
<table><tbody><tr><th>方法</th><th>描述</th><th>案例</th></tr><tr><td>load()</td><td>重新加载音频/视频元素。</td><td>document.getElementById("video").load();<br>不更改来源是重新播放;<br>更改视频来源，就是切换</td></tr><tr><td>play()</td><td>开始播放音频/视频。</td><td>document.getElementById("video").play();</td></tr><tr><td>pause()</td><td>暂停当前播放的音频/视频。</td><td>document.getElementById("video").pause();</td></tr></tbody></table>

#### video的部分属性
<table><tbody><tr><th>属性</th><th>值</th><th>描述</th></tr><tr><td>autoplay</td><td>autoplay</td><td>如果出现该属性，则视频在就绪后马上播放。</td></tr><tr><td>controls</td><td>controls</td><td>如果出现该属性，则向用户显示控件，比如播放按钮。</td></tr><tr><td>height</td><td>pixels</td><td>设置视频播放器的高度。</td></tr><tr><td>loop</td><td>loop</td><td>如果出现该属性，则当媒介文件完成播放后再次开始播放。</td></tr><tr><td>muted</td><td>muted</td><td>如果出现该属性，视频的音频输出为静音。</td></tr><tr><td>poster</td><td>URL</td><td>规定视频正在下载时显示的图像，直到用户点击播放按钮。</td></tr><tr><td>preload</td><td>auto<br>metadata<br>none</td><td>如果出现该属性，则视频在页面加载时进行加载，并预备播放。如果使用"autoplay"，则忽略该属性。</td></tr><tr><td>src</td><td>URL</td><td>要播放的视频的URL。</td></tr><tr><td>width</td><td>pixels</td><td>设置视频播放器的宽度。</td></tr></tbody></table>

# 接下来就开始吧
给出操作的`html`的代码结构：
``` html
<link rel="stylesheet" href="./style.css"/>

<div id="weizu-wrap">
	<video id="weizu">
		<source src="http://www.html5videoplayer.net/videos/madagascar3.mp4" type="video/mp4">
	</video>
</div>

<!--在body后-->
<script src="./main.js"></script>
```

对应的`css`文件如下：
``` css
*{
	margin:0;
	padding:0;
	border:0;
}

div#weizu-wrap{
	width:680px;
	position:relative;
	margin:50px auto;
	border:1px solid red;
}
div#weizu-wrap video#weizu{
	width:100%;
}
```
之所以不设置控件高度，是因为想外层div的高度随着视频的高度自适应就可以了，单控制宽度一个维度就可以了。

百度了一下，找了一个更加适合的用法，然后`CSS`改版了：
对应的`css`文件如下：
``` css
*{
	margin:0;
	padding:0;
	border:0;
}

div#weizu-wrap{
	width:680px;
	height: 400px;
	position:relative;
	margin:50px auto;
	border:1px solid red;
}
div#weizu-wrap video#weizu{
	width: 680px;
	height: 400px;
	object-fit:fill;  /*拉伸视频，填充整个设置的区域的*/
}
```

## 一些尝试
### 播放按钮
资源载入完成，视频上显示播放按钮，单击视频任意位置，就开始播放




