---
title: hexo搭博客教程-2 | 安装主题
date: 2019-04-12 15:50:20
comments: true 
categories: "Hexo教程"
tags: 
    - hexo
    - markdown
---
# 1. 如何为博客更换自己喜欢的主题？
在项目配置文件`_config.yml`中的第73~76行：
``` 
# Extensions
## Plugins: https://hexo.io/plugins/
## Themes: https://hexo.io/themes/
theme: landscape
```
默认的主题是`landscape`；主题的官方网址是：`https://hexo.io/themes/`
## 1. 选择并安装主题
访问网址，然后选择自己喜欢的主题样式。如：我选择的是：https://github.com/xiangming/landscape-plus
这个网页中也给出了安装的命令：
安装
从release页面下载，然后解压到hexo的themes目录下。
或者直接clone最新版：（可能会存在bug，不建议新手尝试）
```
# 在hexo根目录下执行
git clone https://github.com/xiangming/landscape-plus.git themes/landscape-plus
```

## 2. 修改博客主配置文件
打开博客目录下的`_config.yml`配置文件，大约在`73~76`行可以看见配置主题。修改为刚才下载的主题的文件夹名：`theme: landscape-plus`
然后，还是在`cmd`窗口中，运行
```
hexo g
hexo s
```

查看效果。

# 2. 主题配置
接下来就是对选定的主题进行配置。
## a. 文章内容部分显示
首先打开地址`127.0.0.1:4000`，发现`Hello World`比较难受，它显示了整个篇幅。希望它显示部分，就在你需要截断的位置，加入`&lt;!--more-->`
## b. 左栏显示
左边的栏目没有显示内容，其实也就是自己没有设置。打开`E:myblog\source\_postshello-world.md`文件，然后加入下面的内容即可：
```
- - -
title: Hello World
category: "first"
tag: 
 - hexo
- - -
```

## c. 添加自我展示
左栏中没有我想要的自我展示框，感觉很难受，觉得有必要加入一个。
寻寻觅觅，发现左栏的内容布局文件就在这里：`E:myblog\themes\landscape-plus\layout\_widget`
好像可以自己定义一个，接着就尝试一下：
在该目录下，选择任意一个，赋值一个拷贝，改名称为：`introduction.ejs`
当然，相应的，我们需要在配置文件中申明一下，打开主题配置文件，大约在15~24行，找到Sidebar，在组件下面添加一个，如下：
```
widgets:
- introduction
```

（此时，重新生成页面`hexo g`，重启服务`hexo s`可以看见效果）

容器是放到页面了，但是，页框的效果、文字内容不是我想要的，接着就再次修改。
打开文件，查看内容，发现其实就是`HTML`文档，不妨直接写`HTML`内容。
我们先找找样式文件位置，在：`E:myblog\themes\landscape-plus\source\css`
打开`style.styl`文件，然后在最后看见了导入样式的代码，很高兴。
于是，在`_partial`文件夹中，我创建了自己的样式文件，命名为`sidebar-author`，然后在`style.styl`文件中，添加的代码如下：
```
if sidebar
  @import "_partial/sidebar"
  @import "_partial/sidebar-author"
```

接着，就开始编辑自定义的`sidebar-author`样式文件。观察其他的样式文件内容，不难发现代码中不使用括号、分号，使用两个空格的缩进来标志代码块。

也就是说，我们需要编辑两个文件：
* 一个是HTML（`E:myblog\themes\landscape-plus\layout\_widget\introduction.ejs `）；
* 一个是CSS（`E:myblog\themes\landscape-plus\source\css\sidebar-author.styl` ）。

开始编辑：
### 圆角
感觉将侧栏中所有的`widget`，顶部都变成圆角比较好看，然后为了方便，在`sidebar.styl`文件中找到`widget`，添加代码：
```
.widget
  ...
  border-top-left-radius:12px
  border-top-right-radius:12px
  border-bottom-left-radius:6px
  border-bottom-right-radius:6px
  ...
```

文章也应该使用圆角，在浏览器中审查元素，发现其div的class是article-inner，打开article.styl文件，第四行就是需要的article-inner，接着加入圆角样式。（这里略）

### 使用font-awesome
在css文件中没有我们希望的font-awesome.css文件，然后在浏览器中评论功能出发现使用了的，所以还是右键检查元素。然后可以看见，简单的使用如下：
```
.article-comment-link:before {
    content: "\f075";
    font-family: FontAwesome;
    padding-right: 8px;
}
```

故而我们就会用了。
百度一下找到`font-awesome`对应的代码：http://www.fontawesome.com.cn/faicons/
在应用的时候，如 `fa-link Unicode:f0c1`，我们`content: "\f0c1"`;
常用的如下：
 * qq： `fa-qq   Unicode: f1d6 `
 * 微信： `fa-weixin  Unicode: f1d7`
 * 邮箱： `fa-envelope  Unicode: f0e0`
 * 微博： `fa-weibo   Unicode: f18a`

### 效果展示
![自定义侧栏效果图](/images/201906/2019-06-13_175647.jpg)

下面分别将这两个文件的内容放在下面：
HTML（`E:myblog\themes\landscape-plus\layout\_widget\introduction.ejs `）：
``` html
<div class="widget-wrap">
  <h3 class="widget-title">Introduction</h3>
  <div class="widget introduction">
    <img class="avatar" src="/images/timg.jpg">  
    <!--在E:myblog\source\ 目录下新建images目录， 然后存放的timg.jpg图片-->
    <div class="site-info">
      <h2>小王财务</h2>
      <p>如何做靠谱的经营</p>
    </div>
    <div class="site-state-info">
      <div>
        <a href="/archives/">
	  <span>11</span>
	  <span>日志</span>
	</a>
      </div>
      <div>
        <a href="/archives/">
	  <span>1</span>
	  <span>归档</span>
	</a>
      </div>
      <div>
        <a href="/tags/">
	  <span>2</span>
	  <span>随笔</span>
	</a>
      </div>
    </div>
    <div class="site-firend-link">
      <p>友情链接</p>
      <p class="p-two">
        <a href="1270563429@qq.com">邮箱</a>
	<a href="https://user.qzone.qq.com/1270563429">QQ空间</a>
      </p>
      <p class="p-three">
        <a href="http://blog.sina.com.cn/u/5385828767">微博</a>
        <a href="https://baiyazi.top/">无涯明月</a>
      </p>
    </div>
  </div>
</div>
```

CSS（`E:myblog\themes\landscape-plus\source\css\sidebar-author.styl` ）：
``` css
.introduction,.introduction img.avatar
  padding:0
  margin:0
  padding-bottom:5px

.introduction img.avatar
  border-top-left-radius:12px
  border-top-right-radius:12px

.introduction img.avatar
  width:100%;

.introduction div.site-info
  text-align:center
  margin-bottom:5px

.introduction div.site-info h2
  font-size:24px

.introduction div.site-info p
  font-size:18px
  font-weight:bolder

.introduction div.site-state-info
  display:flex
  padding-left:30px
  padding-right:30px

.introduction div.site-state-info div
  flex:1
  text-align:center
  margin-top:20px
.introduction div.site-state-info div a
  text-decoration:none
.introduction div.site-state-info div a:hover
  color:#000
.introduction div.site-state-info div a span
  display:block
  font-size:16px

.introduction div.site-state-info div a span:first-child
  font-weight:bolder
.introduction div.site-state-info div a span:last-child
  margin-top:10px

.introduction div.site-state-info div:nth-child(1),
.introduction div.site-state-info div:nth-child(2)
  border-right:1px solid #DDD

 
.introduction div.site-firend-link
  padding:10px
  
.introduction div.site-firend-link p:last-child,
.introduction div.site-firend-link p:nth-child(2)
  display:flex

.introduction div.site-firend-link p:first-child
  border-top:1px dashed #DDD
  border-bottom:1px dashed #DDD
  color:orange
  line-height:30px
  text-align:center
  font-weight:bolder
  font-size:14px
 
.introduction div.site-firend-link p:first-child:before 
  content: "\f0c1";
  font-family: FontAwesome
  padding-right: 8px;

.introduction div.site-firend-link a
  flex:1
  text-align:center
  height:50px
  line-height:50px
  text-decoration:none
.introduction div.site-firend-link p
  margin-left:10px
  margin-right:10px
.introduction div.site-firend-link p.p-two a:nth-child(2):before
  content: "\f1d6";
  font-family: FontAwesome
  padding-right: 5px;

.introduction div.site-firend-link p.p-two a:nth-child(1):before
  content: "\f0e0";
  font-family: FontAwesome
  padding-right: 5px;

.introduction div.site-firend-link p.p-three a:nth-child(1):before
  content: "\f18a";
  font-family: FontAwesome
  padding-right: 5px;

.introduction div.site-firend-link p.p-three a:nth-child(2):before
  content: "\f140";
  font-family: FontAwesome
  padding-right: 5px;
```

篇幅是比较长，实际上没有多少东西。下面就继续对页面进行CSS定制。

---

但是吧，感觉希望自己还是自定义个主题，所以还是好好读读官网还是比较好。
所以还是研究研究。
由于前段时间写了关于Next主题的配置，这里也就不删除了，就直接跳过了。
这里给出学习官网的链接：<a href="/2019/usehexo-9/">这里</a>