---
title: Pygame图像绘制
date: 2019-9-30 21:19:49
author: 无涯明月
comments: true
categories: "Pygame"
tags: 
    - Pygame
---
pygame使用功能pygame.draw模块来绘制形状。
## 绘制图形
### 绘制矩形
``` python
pygame.draw.rect    # draw a rectangle
```

有下面两种用法：
``` python
rect(surface, color, rect) -> Rect
rect(surface, color, rect, width=0) -> Rect
```

surface：绘图的画布surface对象；
color：颜色，三元组形式即可；
rect：矩形类，这里给出简单使用，其他的看文档，[传送门](https://www.pygame.org/docs/ref/rect.html#pygame.Rect)。

``` python
Rect(left, top, width, height) -> Rect
Rect((left, top), (width, height)) -> Rect
```

也即是，用左边距离，顶部距离，宽度和高度四个属性就可以创建一个矩形框。

width=0：可选参数width，当取值为0表示填充这个矩形，当取值大于0表示使用边框的厚度，当取值小于0不绘制矩形。
这里不妨测试一下，循环中的代码涉及测试部分：
``` python
screen.fill((255, 255, 255))
pygame.draw.rect(screen, (255,0,0), (100,100,20,20), 1)
pygame.display.flip()
```

修改width的值，就可以发现当width大于0的时候，该值即为边框的大小。

### 绘制多边形
``` python
pygame.draw.polygon			#draw a polygon
``` 

它有两种方式实现：
``` python
polygon(surface, color, points) -> Rect
polygon(surface, color, points, width=0) -> Rect
```

相信看了上面的画矩形，下面的也就容易理解了。这里就说说points
在文档中给了一个例子：[(x1, y1), (x2, y2), (x3, y3)]
这里不妨编程看看，同样还是改循环中的代码：
``` python
import pygame
from pygame.locals import *

def main():
    # 初始化
    pygame.init()
    screen = pygame.display.set_mode((600, 400), pygame.RESIZABLE, 0)
    pygame.display.set_caption('Basic Pygame program')

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return
        screen.fill((255, 255, 255))
        pygame.draw.polygon(screen, (255,0,0), [(100,100),(20,20),(300,5),(150,200)], 0)
        pygame.display.flip()

main()
```

结果：
<img src="/images/201910/2019-10-01_143446.png">

### 绘制圆圈
``` python
pygame.draw.circle	# draw a circle
```

实现的方法：
``` python
circle(surface, color, center, radius) -> Rect
circle(surface, color, center, radius, width=0) -> Rect
```

字面意思翻译一下，就知道了是中心点和半径参数。
比较简单，这里就测试了。

### 椭圆
其实这个单词ellipse我也是不认识的，翻译了一下是椭圆。
``` python
pygame.draw.ellipse	# draw an ellipse
```

它有两种实现：
``` python
ellipse(surface, color, rect) -> Rect
ellipse(surface, color, rect, width=0) -> Rect
```

对于rect参数，这里我就摘取原文的内容：
>rect (Rect) -- rectangle to indicate the position and dimensions of the ellipse, the ellipse will be centered inside the rectangle and bounded by it

大致意思就是使用矩形来表示椭圆的位置和尺寸，它会画在这个矩形的内部，居中并且以这个矩形为边界。

不妨用代码测试一下：
``` python 
pygame.draw.ellipse(screen, (255,0,0), [100,100,100,50])
```

显示：
<img src="/images/201910/2019-10-01_144428.png">

### 其他
``` python
pygame.draw.arc    # draw an elliptical arc
```

arc -->弧形

``` python
pygame.draw.line 	# draw a straight line
```


``` python
pygame.draw.lines	# draw multiple contiguous straight line segments
```