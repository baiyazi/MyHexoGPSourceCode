---
title: CSS媒体查询  |  利用@media screen实现网页布局的自适应
date: 2019-5-6 09:55:11
comments: true
categories: "CSS样式"
tags: 
    - css
---
# 利用@media screen实现网页布局的自适应
## 准备工作1：设置Meta标签
首先我们在使用Media的时候需要先设置下面这段代码，来兼容移动设备的展示效果：
``` html
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
```

这段代码的几个参数解释：
* width = device-width：宽度等于当前设备的宽度
* height = device-height：高度等于当前设备的高度
* initial-scale：初始的缩放比例（默认设置为1.0）  
* minimum-scale：允许用户缩放到的最小比例（默认设置为1.0）    
* maximum-scale：允许用户缩放到的最大比例（默认设置为1.0）   
* user-scalable：用户是否可以手动缩放（默认设置为no，因为我们不希望用户放大缩小页面） 

## 准备工作2：加载兼容文件JS
因为IE8既不支持HTML5也不支持CSS3 Media，所以我们需要加载两个JS文件，来保证我们的代码实现兼容效果：

``` html
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
```

## 准备工作3：设置IE渲染方式默认为最高(这部分可以选择添加也可以不添加)
现在有很多人的IE浏览器都升级到IE9以上了，所以这个时候就有又很多诡异的事情发生了，例如现在是IE9的浏览器，但是浏览器的文档模式却是IE8:

为了防止这种情况，我们需要下面这段代码来让IE的文档模式永远都是最新的：
``` html
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 （如果想使用固定的IE版本，可写成：<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">）
```


不过我最近又发现了一个更给力的写法：
``` html
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
```

怎么这段代码后面加了一个chrome=1，这个Google Chrome Frame（谷歌内嵌浏览器框架GCF），如果有的用户电脑里面装了这个chrome的插件，就可以让电脑里面的IE不管是哪个版本的都可以使用Webkit引擎及V8引擎进行排版及运算，无比给力，不过如果用户没装这个插件，那这段代码就会让IE以最高的文档模式展现效果。这段代码我还是建议你们用上，不过不用也是可以的。

## 最后就是设置媒体查询样式了
小于960px尺寸的代码：
``` css
@media screen and (max-width:960px){
    body{
        background:orange;
    }
}
```

等于960px尺寸的代码：
``` css
@media screen and (max-device-width:960px){
    body{
        background:red;
    }
}
```


然后就是当浏览器尺寸大于960px时候的代码了：
``` css
@media screen and (min-width:960px){
    body{
        background:orange;
    }
}
```

我们还可以混合使用上面的用法：
``` css
@media screen and (min-width:960px) and (max-width:1200px){
    body{
        background:yellow;
    }
}
```

上面的这段代码的意思是当页面宽度大于960px小于1200px的时候执行下面的CSS。

 

---

如，下面的代码，查看效果，调整浏览器的宽度可以查看效果：
``` html
<!DOCTYPE HTML>
<html lang="en">
 <head>
  <title> hello </title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <style>
	
		@media screen and (max-width:960px){
			body{
				background:black;
			}
		}
		@media screen and (min-width:960px) and (max-width:1200px){
			body{
				background:green;
			}
		}
		@media screen and (min-width:1200px){
			body{
				background:red;
			}
		}
  </style>
 </head>

 <body>
	
 </body>
</html>
```




