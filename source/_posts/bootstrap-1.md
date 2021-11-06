---
title: Bootstrap安装
date: 2019-4-28 20:41:43
author: 无涯明月
top: true
cover: true
comments: true
categories: "bootstrap"
tags: 
    - Bootstrap
---
# 一、简介
`Bootstrap` 是世界上最受欢迎的前端框架，用于构建`响应式`、移动设备优先的网站。
## 1.  快速开始
### 1.1 CSS
将引入 Bootstrap 样式表的` &lt;link&gt`; 标签复制并粘贴到 `&lt;head&gt`;中，并放在所有其他样式表之前。

``` html
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
```

### 1.2 JS
`Bootstrap `中的许多组件需要依赖 `JavaScript` 才能运行。具体来说，他们依赖的是 `jQuery`、`Popper.js` 以及我们自己开发的 `JavaScript` 插件。具体操作就是将下列 `&lt;script&gt`; 标签放到页面底部的 `&lt;/body&gt;` 标签之前。注意顺序，`jQuery` 必须放在最前面，然后是 `Popper.js`，最后是我们自己的 `JavaScript` 插件。

```html
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
```

### 1.3 初学者模板
使用 `HTML5` `doctype` 声明、添加一个 `viewport` 标签让页面正确支持响应式布局。
上面的css文件和js文件，为了学习方便。我下载到了本地，然后我直接用的本地的。

```html
<!DOCTYPE HTML>
<html lang="en">
 <head>
  <title> hello </title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="icon" href="image/favicon.ico">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/index.css">
 </head>

 <body>

    <!-- jQuery first, then popper.js then Bootstrap.js -->
    <script src="js/jquery.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
 </body>
</html>
```

以上这些就是所有页面必须的。请访问 [布局](https://v4.bootcss.com/docs/4.0/layout/overview/ "布局") 或 [官方实例](https://v4.bootcss.com/docs/4.0/examples/ "官方实例") 以作参考，然后就可以开始布局你的网站内容和组件了。

---

从下一章我们开始学习Bootstrap


