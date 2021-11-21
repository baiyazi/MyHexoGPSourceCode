---
title: Tkinter 编程  | pack布局
date: 2019-5-19 14:22:16
comments: true
categories: "tkinter"
tags: 
    - tkinter
---
# pack简介
pack(包装)是tkinter中的一个布局管理模块
# 属性
我们在将控件添加到win时，如下：
``` python
tk.Label(win, text="Tom").pack()
```

按住ctrl，点击pack，然后我们追踪，可以看见注释的属性：
``` text
在父控件中包装一个组件。使用下面的选项：
anchor=NSEW (or subset) -  依据给定的方向，定位组件。
expand=bool - 父控件大小增加，是否扩展组件大小
fill=NONE or X or Y or BOTH - 组件变大，是否填充，是否向水平或垂直方向填充
ipadx=amount - add internal padding in x direction
ipady=amount - add internal padding in y direction
padx=amount - add padding in x direction
pady=amount - add padding in y direction
side=TOP or BOTTOM or LEFT or RIGHT -  设置组件的对齐方式

```

案例①：anchor
``` python
import tkinter as tk
win = tk.Tk()
win.geometry("400x200+200+50")
li = [tk.N,tk.E,tk.S,tk.W,tk.NW,tk.NE,tk.SW,tk.SE,tk.CENTER]
for i in li:
    tk.Label(win, text=str(i)).pack(anchor=i)
win.mainloop()
```

案例②：expand   && fill
只使用expand=True，是组件在父容器中居中显示
``` python
import tkinter as tk
win = tk.Tk()
win.geometry("400x200+200+50")

tk.Label(win, text="12346", bg = "white").pack(expand = True)
win.mainloop()
```

使用expand=True, 和fill = tk.X or tk.Y，就会对应方向上的组件填充
在expand = True 和fill = tk.BOTH的时候，填充父控件为设置的背景，内容居中。
``` python
import tkinter as tk
win = tk.Tk()
win.geometry("400x200+200+50")

tk.Label(win, text="12346", bg = "white").pack(expand = True, fill = tk.BOTH)
win.mainloop()
```

![e](/images/201905/2019-05-19_144311.jpg)

案例③：side
``` python
import tkinter as tk
win = tk.Tk()
win.geometry("400x200+200+50")

tk.Label(win, text="12346789", bg = "white").pack(side = tk.LEFT)
tk.Label(win, text="12", bg = "white").pack(side = tk.TOP)
tk.Label(win, text="12346789", bg = "white").pack(side = tk.BOTTOM)
tk.Label(win, text="12", bg = "white").pack(side = tk.RIGHT)
win.mainloop()
```
![e](/images/201905/2019-05-19_145010.jpg)

---

side 加载图片的案例：
```python
from tkinter import *

root = Tk()
txt = """At present, only GIF and PPM/PGM
formats are supported, but an interface 
exists to allow additional image file
formats to be added easily."""
w2 = Label(root,
           justify=LEFT,
           padx=10,
           text=txt).pack(side="left")
logo = PhotoImage(file="H:/avatar.png")
w1 = Label(root, image=logo).pack(side="right")
root.mainloop()
```

![e](/images/201905/2019-05-19_150031.jpg)
