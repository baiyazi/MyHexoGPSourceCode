---
title: Bootstrap容器
date: 2019-4-29 20:41:43
comments: true
categories: "bootstrap"
tags: 
    - Bootstrap
---
# 一、简介
在Bootstrap中，容器是最基本的布局元素，同时当我们使用默认的网格系统的时候是必须的。选择响应式固定宽度容器(即在每个断点处其最大宽度发生变化)或流宽容器(即始终100%宽)
尽管容器可以嵌套，大多数的布局并不需要嵌套的容器。
## 1. 两种容器
### 1.1 container
内容不会填充整个视图，同样填充的大小随着窗口大小的变化而变化。
``` html
<div class="container">
  123
</div>
```

### 1.2 container-fluid 
内容填充整个视图，填充大小也会随之变化，填充的大小始终是整个窗口的大小。
``` html
<div class="container-fluid">
  ...
</div>
```

这两种容器，响应有阈值控制，也就是响应断点。
由于Bootstrap被开发为移动设备优先，所以我们使用一些媒体查询来为布局和界面创建合理的断点。这些断点大多基于最小视口宽度，允许我们在视口更改时扩展元素。
Bootstrap主要在用于布局、网格系统和组件的资源css文件中使用以下媒体查询范围(或断点)。

```
// Small devices (landscape phones, 576px and up) 小设备 
@media (min-width: 576px) { ... }

// Medium devices (tablets, 768px and up)  中等设备 
@media (min-width: 768px) { ... }

// Large devices (desktops, 992px and up) 大设备
@media (min-width: 992px) { ... }

// Extra large devices (large desktops, 1200px and up) 超大设备
@media (min-width: 1200px) { ... }
```
在bootstrap.min.css文件中可以找到。

## 2. Z-index
在Bootstrap中默认设置了各个组件的Z-index，就像工具提示，弹出窗口，导航条，下拉菜单等，他们的Z-index开始于一个比较高且任意的值，足够高也足够特殊去理想地避免冲突。
并不建议修改他们的Z-index值，如果你定制一个，就意味着需要改变所有的值。
我们一起看看默认值：

```
$zindex-dropdown:          1000 !default;
$zindex-sticky:            1020 !default;
$zindex-fixed:             1030 !default;
$zindex-modal-backdrop:    1040 !default;
$zindex-modal:             1050 !default;
$zindex-popover:           1060 !default;
$zindex-tooltip:           1070 !default;
```






















