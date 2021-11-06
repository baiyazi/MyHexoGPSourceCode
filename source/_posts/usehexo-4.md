---
title: hexo设置背景颜色
author: weizu
comments: true
date: 2019-5-5 22:07:45
categories: "Hexo教程"
tags: 
    - hexo
---
# 修改背景，颜色样式等

打开`theme\hexo-theme-next\source\css\_custom\custom.styl`，添加样式代码。
``` css
//添加背景图片
body { background:url(/images/backGround.jpg)}
//改掉题头颜色
.site-meta {
  background: #F0D784; //修改为自己喜欢的颜色
}
//主标题颜色
.brand{
  color: #2f9833
  }
//副标题颜色
.site-subtitle{
  color: #47b54a
}
//页脚统计文字颜色
.footer{
  color: #F0D784
}
//修改页脚备案链接颜色
.footer a{
  color: #F0D784
}
//修改页脚统计人数的颜色
.footer .with-love{
  color: #F0D784
}
```

其他需要的样式设置，可以用浏览器审查元素，然后找到你所需要的元素，设置样式。



