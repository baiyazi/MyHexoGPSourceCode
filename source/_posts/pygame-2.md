---
title: Pygame中的convert()
date: 2019-9-30 18:41:27
author: 无涯明月
comments: true
categories: "Pygame"
tags: 
    - Pygame
---
## convert()
还是从案例开始：
``` python
import pygame
from pygame.locals import *

def main():
    # 初始化
    pygame.init()
    screen = pygame.display.set_mode((200, 100))
    pygame.display.set_caption('Basic Pygame program')

    # 填充背景
    background = pygame.Surface(screen.get_size())
    background = background.convert()  
    background.fill((250, 250, 250))  # RGB

    # 创建文本文本
    font = pygame.font.Font(None, 36)
    text = font.render("Hello There", 1, (10, 10, 10))

    # 设置字体要放置的矩形框的位置
    textpos = text.get_rect()
    textpos.centerx = background.get_rect().centerx
    textpos.centery = background.get_rect().centery

    # 绘制文本到指定位置,画布对象是backgorund, 位置(centerx, centery)
    background.blit(text, textpos)

    # 绘制画布对象background到窗口screen,位置(0,0)
    screen.blit(background, (0, 0))
    pygame.display.flip()

    # 循环，使窗口一直存在，直到发生退出事件
    while 1:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return

main()
```

上面用到了`convert()`这个方法，这里解释：
直面意思：转变，转换，转化
这里转化的是“像素格式”。这是指`surface`在特定像素中记录各个颜色的特定方式。
如果`surface`格式与显示格式不同，则`SDL`将不得不在每次转换时即时对其进行转换，这是一个非常耗时的过程。
故而在加载图像的时候，应该注意这一点， 不仅仅是这样做：
``` python
surface = pygame.image.load('foo.png')
```

而应该：
``` python
surface = pygame.image.load('foo.png').convert()
```

也就是`convert()`的加入是为了渲染的速度的考虑。那什么又是我们前面提到的`surface`?
### 什么又是surface 
是`pygame`中最重要的一部分。可以将`surface`理解为一张空白的纸，也就是一张画布。而且大小任意。
1. 使用`pygame.display.set_mode()`创建的`surface`是特殊的一类，它代表了屏幕对象，无论你在做了什么它都会出现在用户`surface`上。
2. 使用`image.load()`来创建一个包含图像的`surface`。
3. 使用`font.render()`来创建一个包含文本的`surface`。
4. 也可以使用`Surface()`来创建一个不包含任何内容的`surface`。



