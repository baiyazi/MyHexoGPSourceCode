---
title: hexo设置右上角 fork me on github
author: weizu
comments: true
date: 2019-5-6 09:13:36
categories: "Hexo教程"
tags: 
    - hexo
---

# 右上角点击到github

打开链接https://github.blog/2008-12-19-github-ribbons/ 挑选自己喜欢的样式


打开`theme\next\layout\_layout.swig`，添加html代码。
``` html

<div class="{{ container_class }} {% block page_class %}{% endblock %}">
    <div class="headband"></div>
<!--上面是原有的，找到上面的代码，下面是自己编写的，拷贝上面链接中的代码即可。-->
<a href="https://github.com/baiyazi">
      <img width="100" height="100" src="https://github.blog/wp-content/uploads/2008/12/forkme_right_darkblue_121621.png?resize=149%2C149" style="position:absolute;top:0;right:0;border:0;" alt="Fork me on GitHub" data-recalc-dims="1">
</a>
```

# 小屏幕不显示fork me on github
为它添加`CSS`样式， 在上面的a标签中添加类样式就可以了。

```
.forkme{
 	display: none;
 }
  @media (min-width: 768px) {
     .forkme{
     	display: inline;
     }
  }
```
<span class="title2">解释一下：</span>
## Media Queries工作方式:
● screen 是媒体类型里的一种，CSS2.1定义了10种媒体类型
● and 被称为关键字，其他关键字还包括 not(排除某种设备)，only(限定某种设备)
● (min-width: 400px) 就是媒体特性，其被放置在一对圆括号中。


### 第一种方式：
可以直接在link中判断设备的尺寸，然后引用不同的css文件：
``` html
<link rel="stylesheet" type="text/css" href="styleA.css" media="screen and (min-width: 400px)">
```

意思是当屏幕的宽度大于等于400px的时候，应用styleA.css
``` html
<link rel="stylesheet" type="text/css" href="styleB.css"  media="screen and (min-width: 600px) and (max-width: 800px)">
```

意思是当屏幕的宽度大于600小于800时，应用styleB.css

### 第二种方式
另一种方式，即是直接写在 style 标签里：
例如：(根据窗口大小不同使用不同大小的字体)
``` html
//窗口宽度大于900px
@media screen and(min-width:900px)
{
  body{
       font-size:25px;
      }
}
//窗口宽度大于500px小于900px
@media screen and(min-width:500px)and(max-width:900px)
{
  body{
       font-size:20px;
      }
}
//窗口宽度小于500px
@media screen and(max-width:500px)
{
  body{
       font-size:15px;
      }
}
```

---

前端响应式布局最出名的框架莫过于 Bootstrap

