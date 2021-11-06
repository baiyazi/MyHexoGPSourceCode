---
title: Tkinter 编程  | grid布局
date: 2019-5-19 13:51:01
comments: true
categories: "tkinter"
tags: 
    - tkinter
---
# grid简介
grid布局又被称作网格布局，是最被推荐使用的布局。
程序大多数都是矩形的界面，我们可以很容易把它划分为一个几行几列的网格，然后根据行号和列号，将组件放置于网格之中。
使用grid 布局时，需要在里面指定两个参数，分别用row 表示行，column 表示列。
需要注意的是 row 和 column 的序号都从0 开始。
# 属性
我们在将控件添加到win时，如下：
``` python
label = tk.Label(win,textvariable = txt,bg = "#50AC40", fg = "white", font=("黑体", 20)).grid(row = 0,column = 0)
```

按住ctrl，点击grid，然后我们追踪，可以看见注释的属性：
``` text
在父控件的网格中定位一个控件，使用下面的选项：
column=number - 列编号，从0开始(starting with 0)
columnspan=number - 跨越几列，取值为跨越占用的列数
ipadx=amount - 垂直内边距,类似padding-left and padding-right
ipady=amount - 水平内边距,类似padding-top and padding-bottom
padx=amount - 垂直外边距，类似margin-left and margin-right
pady=amount - 水平外边距，类似margin-top and margin-bottom
row=number - 列编号，从0开始 (starting with 0)
rowspan=number - 跨越几行，取值为跨越占用的行数

```

``` python
import tkinter as tk
win = tk.Tk()
win.geometry("400x200+200+50")

th = ["Phone", "Number", "Number"]
k = 0
for i in th:
    label = tk.Label(win, text=i).grid(row=0, column=k,ipadx = 5, ipady = 5)
    k+=1

tk.Label(win, text="Tom").grid(row=1, column=0,ipadx = 5, ipady = 5)
tk.Label(win, text="15128459508").grid(row=1, column=1,ipadx = 5, ipady = 5, columnspan = 2)

tk.Label(win, text="Jack").grid(row=2, column=0,ipadx = 5, ipady = 5)
tk.Label(win, text="4008887575").grid(row=2, column=1,ipadx = 5, ipady = 5)
tk.Label(win, text="15128459507").grid(row=2, column=2,ipadx = 5, ipady = 5)
win.mainloop()
```

![e](/images/201905/2019-05-19_141936.jpg)

