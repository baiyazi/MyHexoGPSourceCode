---
title: Pygame中的Display Modes
date: 2019-9-30 19:35:21
author: 无涯明月
comments: true
categories: "Pygame"
tags: 
    - Pygame
---
[原文地址](https://www.pygame.org/docs/tut/DisplayModes.html)
## Introduction
这句话比较意思：
>Setting the display mode in pygame creates a visible image surface on the monitor. This surface can either cover the full screen, or be windowed on platforms that support a window manager. The display surface is nothing more than a standard pygame surface object. There are special functions needed in the pygame.display pygame module to control the display window and screen module to keep the image surface contents updated on the monitor.

意思是：在`pygame`中设置显示模式，会在监视器上创建一个可视的图像`surface`。
这个`surface`要么是覆盖这个屏幕，要么是一个支持窗口管理平台上以窗口形式显示。
这个显示`surface`也仅仅只是一个标准的`pygame`  `surface`对象。
在`pygame.display`  `pygame`模块中需要有一些特殊的方法去控制显示屏幕和窗口模块，来保持图像`surface`内容更新在监视器上。

### 该如何设置
`pygame.display.set_mode((width, height), flags, depth)`
这个显示模式可以在`pygame.display`模块已经初始化后的任意时刻，以前已经设置过了，再设置一次会改变当前的模式。
前面也说过，`(width, height)`这个元组是必须的；
`depth`是这个`surface`每个像素的请求位，如果`depth=8`，`pygame`将会创建一个颜色映射的`surface`。（我这里百度了一下[位深度](https://baike.baidu.com/item/%E4%BD%8D%E6%B7%B1%E5%BA%A6/9676686?fr=aladdin)）
>位深度
计算机之所以能够显示颜色，是采用了一种称作“位”( bit ) 的记数单位来记录所表示颜色的数据。当这些数据按照一定的编排方式被记录在计算机中，就构成了一个数字图像的计算机文件。“位”( bit )是计算机存储器里的最小单元，它用来记录每一个像素颜色的值。图像的色彩越丰富，“位”就越多。每一个像素在计算机中所使用的这种位数就是“位深度”。
黑白二色的图像是数字图像中最简单的一种，它的每个像素只有1位颜色，位深度是1， 用2的1次幂，两种颜色；
8位颜色的图，位深度就是8，用2的8次幂表示，它含有256种颜色 ( 或256种灰度等级 )；

`depth`默认值是`0`，当设置为`0`的时候，`pygame`会选择使用最合适的位深度，通常是和系统当前的位深度一样。
`flags`前面也提到过，是控制显示模式的。这里就不说了。


### 这些数值是如何确定的
下面提供了一些方法来收集一些关于屏幕设备的信息，这些方法都必须在`display`模块初始化完成后调用。
`pygame.display.Info()`；
`pygame.display.list_modes(depth, flags)` 可以用来找到系统支持的图像模式；
`pygame.display.mode_ok((width, height), flags, depth)` 返回最接近你请求的位深度；


### 测试一下
由于没有必要持续保持窗口的在线，故而这里没有用到循环来一直保持：
``` python
import pygame
from pygame.locals import *

def main():
    # 初始化
    pygame.init()
    screen = pygame.display.set_mode((200, 100))
    pygame.display.set_caption('Basic Pygame program')

    print(pygame.display.Info()) # info
    print(pygame.display.mode_ok((200,100)))  # 32
    print(pygame.display.list_modes())
main()
```

结果：
``` text
&lt;VideoInfo(hw = 0, wm = 1,video_mem = 0
         blit_hw = 0, blit_hw_CC = 0, blit_hw_A = 0,
         blit_sw = 0, blit_sw_CC = 0, blit_sw_A = 0,
         bitsize  = 32, bytesize = 4,
         masks =  (16711680, 65280, 255, 0),
         shifts = (16, 8, 0, 0),
         losses =  (0, 0, 0, 8),
         current_w = 200, current_h = 100
>

32   # 当前模式最好的位深度
[(1366, 768), (1360, 768), (1280, 768), (1280, 720), (1024, 768), (800, 600), (640, 480), (640, 400), (512, 384), (400, 300), (320, 240), (320, 200)]  # 我这里理解为默认全屏状态下，应用可以设置的分辨率列表
```

很有意思的是，返回的`mode`列表，就是我电脑的屏幕的可以调的分辨率。最大是`(1366, 768)`。






