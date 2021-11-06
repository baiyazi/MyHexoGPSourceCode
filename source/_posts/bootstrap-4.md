---
title: Bootstrap格子系统（二）
date: 2019-5-1 10:48:11
comments: true
categories: "bootstrap"
tags: 
    - Bootstrap
---
# 1. 对其方式
## 1.1 垂直对齐（Vertical alignment）
``` html
<div class="container">
  <div class="row align-items-start">
    <div class="col">
      One of three columns
    </div>
    <div class="col">
      One of three columns
    </div>
    <div class="col">
      One of three columns
    </div>
  </div>
  <div class="row align-items-center">
    <div class="col">
      One of three columns
    </div>
    <div class="col">
      One of three columns
    </div>
    <div class="col">
      One of three columns
    </div>
  </div>
  <div class="row align-items-end">
    <div class="col">
      One of three columns
    </div>
    <div class="col">
      One of three columns
    </div>
    <div class="col">
      One of three columns
    </div>
  </div>
</div>
```

为了能观察到效果，我们设置如下的css样式：
``` css
div.row, div.col{
    border:1px solid red;
}

div.row{
    height:100px;

}
```

![e](/images/201904/2019-04-30_115523.jpg "效果")
``` html
<div class="container">
  <div class="row">
    <div class="col align-self-start">
      One of three columns
    </div>
    <div class="col align-self-center">
      One of three columns
    </div>
    <div class="col align-self-end">
      One of three columns
    </div>
  </div>
</div>
```

![e](/images/201904/2019-04-30_115739.jpg "效果")

## 1.2 水平对齐（Horizontal alignment）
```html
<div class="container">
  <div class="row justify-content-start">
    <div class="col-4">
      One of two columns
    </div>
    <div class="col-4">
      One of two columns
    </div>
  </div>
  <div class="row justify-content-center">
    <div class="col-4">
      One of two columns
    </div>
    <div class="col-4">
      One of two columns
    </div>
  </div>
  <div class="row justify-content-end">
    <div class="col-4">
      One of two columns
    </div>
    <div class="col-4">
      One of two columns
    </div>
  </div>
  <div class="row justify-content-around">
    <div class="col-4">
      One of two columns
    </div>
    <div class="col-4">
      One of two columns
    </div>
  </div>
  <div class="row justify-content-between">
    <div class="col-4">
      One of two columns
    </div>
    <div class="col-4">
      One of two columns
    </div>
  </div>
</div>
```

``` css
div.row, div.col-4{
    border:1px solid red;
}
```

![e](/images/201904/2019-05-01_082107.jpg "效果")
`justify-content-start`        列内容在行内左对齐
`justify-content-center`     列内容在行内居中对齐
`justify-content-end`          列内容在行内右对齐
`justify-content-around`     列内容在行内分散对齐，每列的左右边距一样，故中间是单边的两倍
`justify-content-between`   列内容在行内分别左右对齐





























