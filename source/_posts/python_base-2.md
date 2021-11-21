---
title: Python编程入门
date: 2019-9-11 09:54:12
comments: true
categories: "《Python编程从入门到实践》读书笔记"
tags: 
  - Python
---

## 创建一个空的Pygame窗口
代码：
``` python
import pygame, sys

def run():
    pygame.init()
    screen = pygame.display.set_mode((600,200))
    pygame.display.set_caption("test")

    # 设置背景色
    bg_color = (230, 230, 230)
    while True:
    	# 监视键盘和鼠标事件
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()

        # 每次循环时都重绘屏幕
        screen.fill(bg_color)
        # 绘制屏幕
        pygame.display.flip()

if __name__ == '__main__':
    run()
```


玩家单击游戏窗口的关闭按钮时，将检测到`pygame.QUIT`事件，而我们调用`sys.exit()`来退出游戏。
了`pygame.display.flip()`，命令`Pygame`让最近绘制的屏幕可见。在这里，它在每次执行`while`循环时都绘制一个空屏幕，并擦去旧屏幕，使得只有新屏幕可见。在我们移动游戏元素时，`pygame.display.flip()`将不断更新屏幕，以显示元素的新位置，并在原来的位置隐藏元素，从而营造平滑移动的效果。



```