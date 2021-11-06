---
title: Pygame初探
date: 2019-9-30 12:05:28
author: 无涯明月
comments: true
categories: "Pygame"
tags: 
    - Pygame
---
>前言：
>为什么上了研究生才开始试着系统学习Pygame?
>以前总觉得知道上哪去找就好，也就没那么多的追求。在做小游戏的时候，也是遇到不会的就百度，然后用一点时间就简单解决了，感觉良好。
>但是实际上是，到了如今阶段还是什么都靠百度。可能到 了某个面试的时刻，然后给别人说“Wait a few minutes for me.”。谁他妈会等你。
>这个世界虽不能说效率至上，时间至高，但却不是每个人都愿意等，不是每个人都等得起。
>至少，我知道，我自己可能浪费了太多的时间，可能等不起了。

官方文档：[Pygame文档](https://www.pygame.org/docs/tut/PygameIntro.html)
## Pygame简介
`PyGame`是一个用`Python`写的`SDL`库。
`SDL（Simple DirectMedia Layer）`是一套开放源代码的跨平台多媒体开发库，使用`C`语言写成。`SDL`提供了数种控制图像、声音、输出入的函数，让开发者只要用相同或是相似的代码就可以开发出跨多个平台（`Linux`、`Windows`、`Mac OS X`等）的应用软件。目前`SDL`多用于开发游戏、模拟器、媒体播放器等多媒体应用领域。
广泛的用于许多著名的游戏。
`SDL`也有其他语言的包装。由于`SDL`用`C`语言写成，而且可以很容易在`C++`下面工作，并且SDL绑定了许多其它的语言，这其中就包括`C#`,  ` Java`,`Objective C`,` Pascal`, `Perl`, `PHP`,  `Python`,` Ruby`等。
包装得比较好的是`python`语言的`pygame`。

## 从案例学习
从官网的第一个案例开始，是一个弹球的案例：
``` python
import sys, pygame

# 初始化
pygame.init()

width, height = 320, 240
speed = [2, 2]
black = (0, 0, 0)

# 创建一个图形窗口
screen = pygame.display.set_mode((width, height), pygame.RESIZABLE, 32)
# 载入图像
ball = pygame.image.load("intro_ball.gif")
ballrect = ball.get_rect()  # 表示得到一个矩形位置区域。

while True:
    # 检查是否发生了QUIT事件。
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()

    # 球移动，记录新的矩形位置区域
    ballrect = ballrect.move(speed)

    # 如果球已移到屏幕外，我们将沿该方向反转速度。
    if ballrect.left < 0 or ballrect.right > width:
        speed[0] = -speed[0]
    if ballrect.top < 0 or ballrect.bottom > height:
        speed[1] = -speed[1]

    # 通过用黑色RGB颜色填充屏幕来擦除屏幕。
    screen.fill(black)
    # 绘制img  ball源文件  ， ballrect目标位置
    screen.blit(ball, ballrect)
    # 更新可见显示
    pygame.display.flip()
```

`init()`：在使用`pygame`做很多事情之前，您需要初始化它。
`quit()`：初始化的模块通常还有一个将要清理的`quit()`函数。 没有必要显式调用这些，因为`pygame`将在`python`完成时干净地退出所有初始化的模块。
`display.set_mode(size)`：`Pygame`将图像表示为`Surface`对象。该`display.set_mode()`函数创建一个新的`Surface` 对象，代表了在桌面上出现的那个窗口。您对此`Surface`所做的任何绘图都将在监视器上显示。它有三个参数：
第一个为元祖，代表分 辨率（必须）；
第二个显示窗口的设置，有`FULLSCREEN`（全屏）、`RESIZABLE`（可变大小）、`NOFRAME`（无边框）等，其他的略。
第三个为色深。
`image.load("intro_ball.gif")`：加载球图像。`Pygame`通过`SDL_image`库支持多种图像格式，包括`BMP`，`JPG`，`PNG`，`TGA`和`GIF`。该`pygame.image.load()`函数返回一个包含球数据的`Surface`。`Surface`将保留文件中的所有`colorkey`或`alpha`透明度。
`get_rect()`：绘制矩形
`while True`：如果程序不加循环，那么也就是只载入首次的界面，然后退出。故而在程序中需要有动态或者持久效果，就需要一直循环绘制它。在这个主循环里做的事情就是不停地画背景和更新光标位置，虽然背景是不动的，我们还是需要每次都画它。
`blit(ball, ballrect)`：该方法处理图像的绘制 。从根本上说，`blit`意味着将像素颜色从一个图像复制到另一个图像。我们通过`blit`方法传递`Surface` **要复制的源**（`ball`），以及将源**要放置到目标的位置**（`ballrect`）。
`display.flip()`：更新可见显示。`Pygame`使用双缓冲区管理显示。完成绘制后，将整个显示曲面更新为屏幕，这使我们在屏幕“曲面”上绘制的所有内容均可见。这种缓冲确保了我们只能在屏幕上看到完全绘制的帧。没有它，用户将在创建时看到屏幕的一半完成部分。

<a href="/images/201909/intro_ball.gif" alt="intro_ball.gif">intro_ball.gif</a>


---
最后还是给出运行的截图：
<img src="/images/201909/jfcb8-0f8nv.gif">



