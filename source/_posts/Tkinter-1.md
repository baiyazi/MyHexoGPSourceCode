---
title: Tkinter 编程  | 初识tkinter
date: 2019-5-18 11:48:44
comments: true
categories: "tkinter"
tags: 
    - tkinter
---
# Tkinter 简介
Tkinter 是 Python 的标准 GUI 库。内置在 python 的安装包中，故而只要安装好 Python 之后就能 import Tkinter 库。对于简单的图形界面 Tkinter 还是能应付自如。导入包就可以直接使用，如下：
``` python
import tkinter as tk

#创建窗口
win = tk.Tk()
#设置窗口标题
win.title("first window program")
win.geometry("400x200+200+50")      # 大小400x200 位置200 50

win.resizable(0,0) #阻止Python GUI的大小调整

# 进入消息循环
win.mainloop()
```

# Tkinter 控件
kinter的提供各种控件，如按钮，标签和文本框，一个GUI应用程序中使用。这些控件通常被称为控件或者部件。Tkinter的部件。如下：
1. [Button](../Tkinter-2)
2. [Label](../Tkinter-3)
3. Entry
4. Checkbutton
5. Frame
6. Canvas
7. Listbox
8. Menubutton
9. Menu
10. Message
11. Radiobutton
12. Scale
13. Srollbar
14. Text
15. Toplevel
16. Spinbox
17. PanedWindow
18. LabelFrame
19. tkMessageBox

