---
title: Tkinter 编程  | Button
date: 2019-5-19 11:44:17
comments: true
categories: "tkinter"
tags: 
    - tkinter
---
# Button
``` text
w = Button ( master, option=value, ... )
```
* master: 按钮的父容器。
* options: 可选项，即该按钮的可设置的属性。这些选项可以用键 = 值的形式设置，并以逗号分隔。
(options)属性：

## 标准选项：（有些选项，测试的时候，发现无多大用处，这里没列举出来。）
### 文本相关
text	设置按钮的文本内容
textvariable   可以修改文本内容的变量
font    文本字体
underline	下划线。默认按钮上的文本都不带下划线。取值就是带下划线的字符串索引，为 0 时，第一个字符带下划线，为 1 时，前两个字符带下划线，以此类推
justify	显示多行文本的时候,设置不同行之间的对齐方式，可选项包括LEFT, RIGHT, CENTER
wraplength	限制按钮每行显示的字符的数量
anchor          锚选项，控制文本的位置，默认为center， 取值：  n, ne, e, se, s, sw, w, nw, or center

案例1，使用textvariable做可修改文本内容的案例：
``` python
from tkinter import *
root = Tk()
txt = StringVar()
txt.set("before set") #设置txt的文本内容
b = Button(root,textvariable = txt)
print(txt.get()) #得到原文本内容，输出
b.pack()
txt.set("789")
root.mainloop()
```

不难发现，我们的按钮的文本就可以在pack()后，使用代码修改文本内容。
另一种方式：
``` python
b = Button(root,text = "before set") #使用text设置，而不是textvariable
b.pack()
b['text'] = 123456
```

案例2，指定文本字体：
``` python
from tkinter import *
root = Tk()
# 字体使用tuple，（字体名称，字体大小）
for ft in (('Arial',20), ('Courier New',10), ('Comic Sans MS',20), ('Fixdsys',30), ('MS Sans Serif',20), ('MS Serif',), ('Symbol',), ('System',),
('Times New Roman',), ('Verdana',)):
    Button(root, text=ft[0], font=ft).pack()

root.mainloop()
```
直接使用font指定字体，需要用元组（字体名称，字体大小）
![e](/images/201905/2019-05-19_092914.jpg)

另一种方式：
由于我们上面使用的是元组，所以只能传入两个字体参数，下面我们自创建字体样式：
``` python
from tkinter import *
import tkinter.font as tf
root = Tk()

# size 设置字体大小，整型值
ft = tf.Font(family = 'Comic Sans MS',size = 30)
Button(root, text='123', font=ft).pack()
# weight 设置字体加粗，可取值: tf.BOLD  tf.NORMAL(不加粗)
ft = tf.Font(family = 'Comic Sans MS',size = 30, weight = tf.BOLD)
Button(root, text='123', font=ft).pack()
# underline 设置下划线，可取值：True False(无下划线)
ft = tf.Font(family = 'Comic Sans MS',size = 30, weight = tf.BOLD, underline=True)
Button(root, text='123', font=ft).pack()
# slant 设置字体倾斜，可取值：tf.ITALIC  tf.ROMAN(不倾斜)
ft = tf.Font(family = 'Comic Sans MS',size = 30, weight = tf.BOLD, underline=True,slant = tf.ITALIC)
Button(root, text='123', font=ft).pack()
# overstrike 设置字体删除线，可取值True, False
ft = tf.Font(family = 'Comic Sans MS',size = 30, weight = tf.BOLD, underline=True,slant = tf.ITALIC,overstrike = True)
Button(root, text='123', font=ft).pack()
root.mainloop()
```

![e](/images/201905/2019-05-19_095835.jpg)
### 颜色相关
background（可简写：bg）   按钮的背景色，可以设置white或者#eee格式的颜色
foreground（可简写：fg）    按钮的前景色，也就是文本的颜色
activebackground	当鼠标按下时，按钮的背景色
activeforeground 	当鼠标按下时，按钮的前景色，也即是字体颜色
``` python
from tkinter import *
root = Tk()
Button(root, text="123",font=("Comic Sans MS", 20), bg = "#8EBAE1", fg = "white").pack()
Button(root, text="123",font=("Comic Sans MS", 20), bg = "#8EBAE1", fg = "white", activebackground = "red").pack()
Button(root, text="123",font=("Comic Sans MS", 20), bg = "#8EBAE1", fg = "white", activeforeground = "red").pack()
root.mainloop()
```

![e](/images/201905/2019-05-19_101703.gif)

### 图片相关
image	按钮上要显示的图片，可以使用png和gif图像，不过gif也只是一张图像。图像的加载方法image = PhotoImage(file = filepath)
bitmap          位图，可选的值如下："error"，"hourglass"，"info"，'questhead'，'question'，'warning'，
'gray12'，'gray25'，'gray50'，'gray75'
但是在同时设置了text和位图的时候，就默认显示位图，不显示文本内容。
``` python
from tkinter import *
root = Tk()
img1 = PhotoImage(file = "H:/avatar.png")
Button(root, text="123", image = img1).pack()
img2 = PhotoImage(file = "D:/blog/weizu/source/images/201905/2019-05-19_101703.gif")
Button(root, image = img2).pack()
root.mainloop()
```

![e](/images/201905/2019-05-19_104613.jpg)
``` python
from tkinter import *
root = Tk()
li = ["error","hourglass","info",'questhead','question','warning','gray12','gray25','gray50','gray75']
for i in li:
    #compound设置图片在文字的哪边 bottom, center, left, none, right, or top
    Button(root, text = i ,bitmap=i, compound='left').pack()

root.mainloop()
```

![e](/images/201905/2019-05-19_105312.jpg)

### 边框相关
边框默认2个像素，左边和上边（内：#E3E3E3，外：#FFF），右边和下边（内：A0A0A0，外：#696969），按钮颜色：#F0F0F0；
如下面的案例3D样式：
``` html
<div style="width:30px;background-color:#F0F0F0; height:30px;border-left:1px solid #FFF;border-top:1px solid #FFF;border-right:1px solid #696969;border-bottom:1px solid #696969;"></div>
```
<div style="width:30px;background-color:#F0F0F0; height:30px;border-left:1px solid #FFF;border-top:1px solid #FFF;border-right:1px solid #696969;border-bottom:1px solid #696969;"></div>
borderwidth	按钮边框的大小，默认为 2 个像素
padx	按钮在x轴方向上的内边距(padding)，是指按钮的内容与按钮边缘的距离
pady	按钮在y轴方向上的内边距(padding)
relief	边框样式，设置控件3D效果，可选的有：flat, groove, raised, ridge, solid, or sunken。默认为 RAISED 。(注：赋值的时候可以使用小写字符串，也可以直接使用大写)
``` python
from tkinter import *

root = Tk()
# flat, groove, raised, ridge, solid, or sunken
Button(root, text='default').pack()
Button(root, text='flat', relief=FLAT).pack()
Button(root, text='groove', relief="groove").pack()
Button(root, text='raised', relief="raised").pack()
Button(root, text='ridge', relief="ridge").pack()
Button(root, text='solid', relief="solid").pack()
Button(root, text='sunken', relief="sunken").pack()

root.mainloop()
```

![e](/images/201905/2019-05-19_113555.jpg)

### 鼠标
cursor 鼠标的样式
``` python
import tkinter as tk
win = tk.Tk()

tk.Button(win, text="123", cursor="hand2").pack()
tk.Button(win, text="123", cursor="left_ptr").pack()

win.mainloop()
```


## 组件特有选项：
command 	按钮关联的函数，当按钮被点击时，执行该函数
width	按钮的宽度，如未设置此项，其大小以适应按钮的内容（文本或图片的大小）
height	按钮的高度
state  设置按钮组件状态,可选的有normal、active、 disabled。默认 narmal。


``` python
import tkinter as tk

win = tk.Tk()
win.title("test button")
win.geometry("400x200+200+50")

def func():
    print(123)
#创建按钮
button1  = tk.Button(win, text = "按钮1", command = lambda: print("aaa"), width = 200, bg = "#fff", activebackground = "red")
button1.pack()
button2  = tk.Button(win, text = "按钮2", command = func, width = 200, activeforeground = "#eee")
button2.pack()
# 进入消息循环
win.mainloop()
```

效果图：
![e](/images/201905/2019-05-18_160458.jpg "Button")

