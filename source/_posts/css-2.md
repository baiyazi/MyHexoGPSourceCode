---
title: CSS | 选择器  
date: 2019-5-24 15:35:33
author: 无涯明月
comments: true
categories: "CSS样式"
tags: 
    - css
---
# 最常用的四种选择器
元素选择器、类选择器、ID选择器、通配符选择器
由于比较简单，不介绍了。

## 多类选择器
如：`<p class="important warning"></p>`
在设置`CSS`样式值的时候，可以分开写，也可以采用下面的写法：
``` css
.important.warning {background:silver;}
```

# 其他选择器
## 属性选择器
如果希望选择有某个属性的元素，而不论属性值是什么，可以使用简单属性选择器。
①单一属性：
>[attribute]	用于选取带有指定属性的元素。

如：对有 `href` 属性的锚（a 元素）应用样式：
``` css
a[href] {color:red;}
```

②多属性：
如：将同时有 `href` 和 `title` 属性的` HTML` 超链接的文本设置为红色，可以这样写：
``` css
a[href][title] {color:red;}
```

③根据具体属性值选择：
>[attribute=value]	用于选取带有指定属性和值的元素。

例如，假设希望将指向 `Web 服务器`上某个指定文档的超链接变成红色，可以这样写：
``` css
a[href="http://www.w3school.com.cn/about_us.asp"] {color: red;}
```

④类似的：
可以把多个属性-值选择器链接在一起来选择一个文档。
``` css
a[href="http://www.w3school.com.cn/"][title="W3School"] {color: red;}
```

## <span class="title2">注意：</span>
### 1. 根据具体属性值选择
**属性与属性值必须完全匹配**
``` html
<p class="important warning"></p>
```

可以两个分开设置，如果要一起设置，就必须按照下面的形式：
``` css
p[class="important warning"] {color: red;}
```

### 2. 根据部分属性值选择
>[attribute~=value]	用于选取属性值中包含指定词汇的元素。

**需要使用波浪号（~）**
假设您想选择 `class` 属性中包含` important` 的元素，可以用下面这个选择器做到这一点：
``` css
p[class~="important"] {color: red;}
```

## 特定属性选择类型
>[attribute|=value]	用于选取带有以指定值开头的属性值的元素，该值必须是整个单词。

假设一个 HTML 文档中有一系列图片，其中每个图片的文件名都形如 figure-1.jpg 和 figure-2.jpg。就可以使用以下选择器匹配所有这些图像：
``` css
img[src|="figure"] {border: 1px solid gray;}
```



##  子串匹配属性选择器 ^
[attribute^=value]	匹配属性值以指定值开头的每个元素。
[abc^="def"]	选择 abc 属性值以 "def" 开头的所有元素

## 子串匹配属性选择器 $
[attribute$=value]	匹配属性值以指定值结尾的每个元素。
[abc$="def"]	选择 abc 属性值以 "def" 结尾的所有元素

## 子串匹配属性选择器 *
[attribute*=value]	匹配属性值中包含指定值的每个元素。

[abc*="def"]选择 abc 属性值中包含子串 "def" 的所有元素

## 后代选择器
根据上下文选择元素如果您希望只对 h1 元素中的 em 元素应用样式，可以这样写：

``` CSS
h1 em {color:red;}
```

需要注意的是：两个元素之间的层次间隔可以是无限的。（只要是后代就应用）
## 子元素选择器 >
子元素选择器（Child selectors）只能选择作为某元素子元素的元素。
例如，如果您希望选择只作为 h1 元素子元素的 strong 元素，可以这样写：
``` CSS
h1 > strong {color:red;}
```

## 相邻兄弟选择器 +
如果需要选择紧接在另一个元素后的元素，而且二者有相同的父元素，可以使用相邻兄弟选择器（Adjacent sibling selector）。
例如，如果要增加紧接在 h1 元素后出现的段落的上边距，可以这样写：
``` CSS
h1 + p {margin-top:50px;}
```

## 通用兄弟选择器 ~
通用兄弟元素选择器通过两个简单选择符通过波浪号（~）分隔组成。
它匹配第二个简单选择符中所匹配的元素，而且与第一个简单选择符中匹配的元素要出现在他的前面。
这两个元素必须具有同一个父元素，但是第二个元素不一定必须紧跟在第一个元素之后（非直接兄弟）。
如：这条CSS规则将会匹配所有p元素之后ul元素：  
``` CSS
p ~ ul { background:#ff0; }
```

这个选择器读作：“选择紧接在 h1 元素后出现的段落，h1 和 p 元素拥有共同的父元素”。
## 伪类
>:active	向被激活的元素添加样式。	
:focus	向拥有键盘输入焦点的元素添加样式。	
:hover	当鼠标悬浮在元素上方时，向元素添加样式。	
:link	向未被访问的链接添加样式。	
:visited	向已被访问的链接添加样式。	
:first-child	向元素的第一个子元素添加样式。


伪类的语法：
```CSS
selector : pseudo-class {property: value}
或者:
selector.class : pseudo-class {property: value}
```

### 如，使用a标签的例子：
``` CSS
a:link {color: #FF0000}		/* 未访问的链接 */
a:visited {color: #00FF00}	/* 已访问的链接 */
a:hover {color: #FF00FF}	/* 鼠标移动到链接上 */
a:active {color: #0000FF}	/* 选定的链接 */
```

提示：在 CSS 定义中，a:hover 必须被置于 a:link 和 a:visited 之后，才是有效的。
提示：在 CSS 定义中，a:active 必须被置于 a:hover 之后，才是有效的。
### :first-child 伪类
:first-child 伪类来选择元素的第一个子元素。
``` css
li:first-child {text-transform:uppercase;}
```

解释：“第一个子元素是li的应用，而不是选择 p 元素的第一个子元素。”
如下面的案例：
``` html
<html>
<head>
<style type="text/css">
p:first-child {
  color: red;
  } 
</style>
</head>

<body>
<p>some text</p>
<p>some text</p>
</body>
</html>
```

如果是：选择 p 元素的第一个子元素。因该按照下面的案例书写：
案例：所有 &lt;p> 元素中的第一个 &lt;i> 元素：
``` html
<html>
<head>
<style type="text/css">
p > i:first-child {
  font-weight:bold;
  } 
</style>
</head>

<body>
<p>some <i>text</i>. some <i>text</i>.</p>
<p>some <i>text</i>. some <i>text</i>.</p>
</body>
</html>
```

## 伪元素
>:first-letter	向文本的第一个字母添加特殊样式。	
:first-line	向文本的首行添加特殊样式。	
:before	在元素之前添加内容。	
:after	在元素之后添加内容。

### :first-line 伪元素
"first-line" 伪元素用于向文本的首行设置特殊样式。
如：p 元素的第一行文本进行格式化：
``` css
p:first-line{
  color:#ff0000;
  font-variant:small-caps;
}
```

注释："first-line" 伪元素只能用于块级元素。
注释：下面的属性可**应用于** "first-line" 伪元素：
* font
* color
* background
* word-spacing
* letter-spacing
* text-decoration
* vertical-align
* text-transform
* line-height
* clear
### :first-letter 伪元素
"first-letter" 伪元素用于向文本的首字母设置特殊样式：
``` css
p:first-letter{
  color:#ff0000;
  font-size:xx-large;
}
```

注释："first-letter" 伪元素只能用于块级元素。
注释：下面的属性可应用于 "first-letter" 伪元素：
* font
* color
* background
* margin
* padding
* border
* text-decoration
* vertical-align (仅当 float 为 none 时)
* text-transform
* line-height
* float
* clear

### :before 伪元素
":before" 伪元素可以在元素的内容前面插入新内容。

下面的例子在每个 &lt;h1> 元素前面插入一幅图片：
``` css
h1:before{
  content:url(logo.gif);
}
```

### :after 伪元素
":after" 伪元素可以在元素的内容之后插入新内容。

下面的例子在每个 &lt;h1> 元素后面插入一幅图片：
``` css
h1:after{
  content:url(logo.gif);
}
```