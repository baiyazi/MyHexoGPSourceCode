---
title: hexo搭博客教程-3 | hexo的使用
date: 2019-04-12 16:07:20
categories: "Hexo教程"
tags: 
    - hexo
---
# 1. 如何撰写文章？
## 步骤一： 创建文件

`win+R`，`cmd`切换到站点文件目录，输入如下命令创建文章，其中`title`是文章的标题

``` python
hexo new "title"
```
>D:\blog\weizu>hexo new usehexo-1
>INFO  Created: D:\blog\weizu\source\_posts\usehexo-1.md
当输入命令后，就会在 source/_post 文件夹下创建一个文件，命名为：howtobuildhexopage.md
[markdown语法参考](https://blog.csdn.net/witnessai1/article/details/52551362 "markdown")

## 步骤二： 打开我们创建的usehexo-1.md文件
这里，推荐给大家一款简洁易用的 `Markdown` 编辑器`Typora` 的下载地址：https://www.typora.io/
打开后，我加入了标签，列表的形式：
```
---
title: hexo-next搭建好，你需要了解编写文本的常见markdown语法
date: 2019-04-11 11:21:15
comments: true   是否开启本页的评论
categories: ”hexo教程“  # 里面内容是举例
tags:            标签， 每一页写了这个，然后主页的标签页就会自动生成一个页面
    - hexo
    - weizu_cool
---
注意：属性和属性值之间必须有一个空格，否则会解析错误
```
> hexo比较好的一点就是，在运行了网站后，我们添加新的页面，以及修改原有页面的时候，是不会影响服务器的，服务会自动重新启动，也就是：在修改了hexo源码后，不需要ctrl+c，然后再hexo server 去启动服务，以查看效果。

## 步骤三： 编写文章内容，在适合的位置，插入&lt;!--more-->标签
在利用 `Hexo` 框架搭建的博客网站中，首页会显示文章的内容，且默认显示文章的全部内容
如果当文章太长的时候就会显得十分冗余，所以我们有必要对其进行精简
这时，我们只需在文章中使用 该标志即可，表示只会显示标志前面的内容。
> 当然，这里也可以修改主题配置文件，一步搞定。
>Automatically Excerpt. Not recommend.
>Please use &lt;!-- more --> in the post to control excerpt accurately.
>auto_excerpt:
&nbsp;&nbsp;enable: true
&nbsp;&nbsp;length: 150


## 步骤四：部署和发布站点到GitHub
```
hexo generate   # 生成静态文件。可简写 hexo g
hexo deploy     # 发布到github。可简写 hexo d
#如果需要本地查看，请运行hexo server
```

## 注意：
其实并不需要使用上面的命令创建，如命令：`hexo new "title"`来创建以md结尾的文章，其实也就是使用`scaffolds`文件夹下的模板文件来创建文章。完全可以自己创建。这里我的模板文件目录是：`D:\blog\weizu\scaffolds`

# 2. 常见markdown语法
## markdown教程官网
[markdown](https://kennylee26.gitbooks.io/markdown/content/index.html)
## 下面介绍一些markdown的常见语法
### （1） 标题
``` python
# 一级标题
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题
```
### （2）粗体、斜体、删除线和下划线
``` python
*斜体*
**粗体**
***加粗斜体***
~~删除线~~
```
**效果：**
*斜体*
**粗体**
***加粗斜体***
~~删除线~~

### （3）引用块
``` python
> 文字引用
```
**效果：**
> 文字引用

### （4）代码块
``` python
`行内代码`

#```#   #没有'#'，我这里的代码块就是```，这三个点，我是复制的，不知道怎么敲来的
多行代码
多行代码
#```#

```
**效果：**
`行内代码`
```
多行代码
```
### （5）列表
``` python
1. 有序列表项
* 无序列表项
+ 无序列表项
- 无序列表项
```
**效果：**
1. 有序列表项
* 无序列表项
+ 无序列表项
- 无序列表项

### （6）超链接
``` python
方法一：[链接文字](链接地址 "链接描述")
例如：[示例链接](https://www.example.com/ "示例链接")

方法二：<链接地址>
例如：<https://www.example.com/>
```
**效果：**
[我的第一个博客：百丫子](http://baiyazi.top "百丫子")
### （7）图片
``` python
![图片文字](图片地址 "图片描述")
例如：![示例图片](https://www.example.com/example.PNG "alt text")
```
Hexo中添加本地图片
* 把主配置文件_config.yml 里的post_asset_folder:这个选项设置为true
* 在source下创建/images文件夹，先把图片xx.jpg/png复制到这个文件夹
* 最后在xxxx.md中想引入图片时，只需要在xxxx.md中按照markdown的格式引入图片：
![想输入的提示名字，可不输入](/images/201904/2019-04-18_220932.jpg "实例")


**效果：**
![博客中插入的第一张图片](https://www.baidu.com/img/bd_logo1.png  "百度")
图片大小的指定，需要用html的方式来解决：
``` html
<img width=200 src="https://www.baidu.com/img/bd_logo1.png">
```
<img width=200 src="https://www.baidu.com/img/bd_logo1.png" >
### （8）表格
```
<table><tr><th>Tables</th><th>Are</th><th>Cool</th></tr><tr><td>col 1 is</td><td>left-aligned</td><td>$1600</td></tr><tr><td>col 2 is</td><td>centered</td><td>$12</td></tr><tr><td>col 3 is</td><td>right-aligned</td><td>$1</td></tr></table>
```
注意：使用的时候，HTML需要紧凑，不然会在表格前面留有大量的空白。
**效果：**
<table><tr><th>Tables</th><th>Are</th><th>Cool</th></tr><tr><td>col 1 is</td><td>left-aligned</td><td>$1600</td></tr><tr><td>col 2 is</td><td>centered</td><td>$12</td></tr><tr><td>col 3 is</td><td>right-aligned</td><td>$1</td></tr></table>
### （9）代码
```
# 行内式
C语言里的函数 `scanf()` 怎么使用？
```
**效果：**
C语言里的函数 `scanf()` 怎么使用？
```
# 缩进式多行代码
    #include <stdio.h>
    int main(void){
        printf("Hello world\n");
    }
```
缩进 4 个空格或是 1 个制表符
**效果：**
    #include <stdio.h>
    int main(void){
        printf("Hello world\n");
    }

# 3. 关于导航栏的设置
我发现我`hexo`博客的导航栏还没有激活，这里来创建和导航栏相关的页面。
就比如说，我的导航栏-关于，点击打开链接：http://localhost:4000/about/ 出现的是`Cannot GET /about/`
下面说说如何操作添加相关的页面：
同理，在站点目录中按住Shift，打开cmd窗口。输入：
```
hexo new page about
```

就会在`source`文件夹中生成`about`文件夹，在`about`文件夹下生成`index.md`。这个就叫做页面，不在文章列表显示，可以通过http://localhost/about 浏览。
在文件夹窗口中，用`Typora`打开我们创建好的`source/about/index.md`文件，然后编辑文件成你想要的样子就好了。
同理，我的创建标签页、分类页、日程表
```
hexo new page tags
hexo new page categories
hexo new page schedule
```

归档页archives是自动就有的。
我们可以关闭上面三个页面的评论功能。
```
---
title: categories
date: 2019-04-12 16:21:03
comments: false
type: categories
---
```
并且，加入相应的type类型。









