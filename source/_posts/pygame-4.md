---
title: Pygame中的Color和显示器display
date: 2019-9-30 19:35:21
author: 无涯明月
comments: true
categories: "Pygame"
tags: 
    - Pygame
---
## Pygame中的Color
有下面三种生成`Color`颜色对象的方式：
``` python
Color(r, g, b) -> Color
Color(r, g, b, a=255) -> Color
Color(color_value) -> Color
```

也就是常见的`RGB`、`GRBA`、`Hex number str`。
下面是得到或者设置值得一些方法：
``` python
pygame.Color.r   # Gets or sets the red value of the Color.
pygame.Color.g   # Gets or sets the green value of the Color.
pygame.Color.b   # Gets or sets the blue value of the Color.
pygame.Color.a   # Gets or sets the alpha value of the Color.
# 三个值得取值范围都是0到255
```

## Pygame中的显示器display
pygame.display模块，用来控制显示窗口和屏幕。
下面是一些常见的方法：
方法具体的具体解释：[文档地址](https://www.pygame.org/docs/ref/display.html)
<table>  
<tr><td>pygame.display.init</td>
<td>Initialize the display module
</td></tr><tr><td>pygame.display.quit
<td>Uninitialize the display module
</td></tr><tr><td>pygame.display.get_init
<td>Returns True if the display module has been initialized
</td></tr><tr><td>pygame.display.set_mode
<td>Initialize a window or screen for display
</td></tr><tr><td>pygame.display.get_surface
<td>Get a reference to the currently set display surface
</td></tr><tr><td>pygame.display.flip
<td>Update the full display Surface to the screen
</td></tr><tr><td>pygame.display.update
<td>Update portions of the screen for software displays
</td></tr><tr><td>pygame.display.get_driver
<td>Get the name of the pygame display backend
</td></tr><tr><td>pygame.display.Info
<td>Create a video display information object
</td></tr><tr><td>pygame.display.get_wm_info
<td>Get information about the current windowing system
</td></tr><tr><td>pygame.display.list_modes
<td>Get list of available fullscreen modes
</td></tr><tr><td>pygame.display.mode_ok
<td>Pick the best color depth for a display mode
</td></tr><tr><td>pygame.display.get_active
<td>Returns True when the display is active on the display
</td></tr><tr><td>pygame.display.set_icon
<td>Change the system image for the display window
</td></tr><tr><td>pygame.display.set_caption
<td>Set the current window caption
</td></tr><tr><td>pygame.display.get_caption
<td>Get the current window caption
</td></tr><tr><td>pygame.display.get_window_size
<td>Return the size of the window or screen</td></tr>
</table>








