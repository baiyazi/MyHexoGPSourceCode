---
title: hexo搭博客教程-1 | 入门与基础安装
date: 2019-04-12 15:07:20
categories: "Hexo教程"
tags: 
    - github
    - hexo
---

# 1. 简介
简单了解 `Github Pages`

* `Github Pages`是一个可以托管静态网页的代码托管平台。
* `Github Pages`为你提供一个免费的服务器。
* `Github Pages`可以绑定自己的域名。

简单了解 `Hexo`
`Hexo` 快速、简洁、高效的博客框架。
* `Hexo` 基于`Node.js`搭建的静态页面生成框架，可以快速将Markdown编辑的文本解析按照它指定的规则生成Html代码。

# 2. 安装与配置
## 步骤一：安装Node.js
`Hexo`是基于`node.js`编写的，所以需要安装一下`node.js`和里面的`npm`工具。
`Node.js`官网下载地址：[点击访问](https://nodejs.org/en/download/)
下载自己电脑需要的版本就可以了，然后安装。

运行`cmd`，然后输入如下命令：
```
node -v
npm -v
```

都出现版本信息，说明安装成功。
## 步骤二：安装git
<i class="fa fa-quote-left fa-3x pull-left fa-border"></i>`Git`是目前世界上最先进的分布式版本控制系统，可以有效、高速的处理从很小到非常大的项目版本管理。也就是用来管理你的`hexo`博客文章，上传到`GitHub`的工具。`Git`非常强大，我觉得建议每个人都去了解一下。廖雪峰老师的`Git教程`写的非常好，大家可以了解一下。[Git教程](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)   [By:fangzh](http://fangzh.top/2018/2018090514/)

`git`官网下载即可：[点击访问](https://gitforwindows.org/)
比较简单，可以查看参考博客：[《如何在windows下安装GIT》](https://www.cnblogs.com/jytx/p/5602927.html)

运行`cmd`，然后输入如下命令：
```
git --version
```

都出现版本信息，说明安装成功。
## 步骤三：Hexo安装
### a. 安装Hexo
前面`git`和`node.js`安装好后，就可以安装`hexo`了。
打开`cmd`；或者在任意文件夹下右键，点击`Git Bash Here`，打开`git`窗口。
然后，输入下面的命令，安装Hexo：
```
npm install -g hexo-cli
```

用`hexo -v`查看一下版本

安装完成后，可以看见：
>C:\Users\o\AppData\Roaming\npm\hexo

也即是Hexo的安装目录，也即是说和你前面打开的文件目录是无关的。
**注释**：在windows环境下安装有WARN警告，忽略就好。

### b. Hexo初始化配置
在磁盘中新建一个文件夹，如：`blog`，切换`cmd`命令行窗口到这个文件夹，完成下面的操作：
```
hexo init myblog
注释：这个myblog可以自己取什么名字都行，完成后会在blog目录下创建myblog的文件夹及其文件，需要联网。

cd myblog     //进入这个myblog文件夹
npm install   //Create a new Hexo folder. 
```

完成后，我们可以看看都有什么命令：
>E:\so\img\myblog>hexo
Usage: hexo <command>
<span style="color:red;">**Commands:**</span>
&nbsp;&nbsp;**clean**     &nbsp;&nbsp;&nbsp;&nbsp;Remove generated files and cache.  
&nbsp;&nbsp;**config**    &nbsp;&nbsp;&nbsp;&nbsp;Get or set configurations.  
&nbsp;&nbsp;**deploy**    &nbsp;&nbsp;&nbsp;&nbsp;Deploy your website.  
&nbsp;&nbsp;**generate** &nbsp;&nbsp;&nbsp;&nbsp; Generate static files.  
&nbsp;&nbsp;**help**      &nbsp;&nbsp;Get help on a command.  
&nbsp;&nbsp;**init**   &nbsp;&nbsp;&nbsp;&nbsp;Create a new Hexo folder.  
&nbsp;&nbsp;**list**   &nbsp;&nbsp;&nbsp;&nbsp; List the information of the site  
&nbsp;&nbsp;**migrate**   &nbsp;&nbsp;&nbsp;&nbsp;Migrate your site from other system to Hexo.  
&nbsp;&nbsp;**new **   &nbsp;&nbsp;&nbsp;&nbsp;Create a new post.  
&nbsp;&nbsp;**publish**   &nbsp;&nbsp;&nbsp;&nbsp;Moves a draft post from _drafts to _posts folder.  
&nbsp;&nbsp;**render**   &nbsp;&nbsp;&nbsp;&nbsp;Render files with renderer plugins.  
&nbsp;&nbsp;**server**   &nbsp;&nbsp;&nbsp;&nbsp;Start the server.  
&nbsp;&nbsp;**version**   &nbsp;&nbsp;&nbsp;&nbsp; Display version information.
<span style="color:red;">**Global Options:**</span>
&nbsp;&nbsp;**--config**   &nbsp;&nbsp;&nbsp;&nbsp;Specify config file instead of using _config.yml  
&nbsp;&nbsp;**--cwd**   &nbsp;&nbsp;&nbsp;&nbsp; Specify the CWD  
&nbsp;&nbsp;**--debug**   &nbsp;&nbsp;&nbsp;&nbsp; Display all verbose messages in the terminal  
&nbsp;&nbsp;**--draft**   &nbsp;&nbsp;&nbsp;&nbsp;Display draft posts  
&nbsp;&nbsp;**--safe**   &nbsp;&nbsp;&nbsp;&nbsp;   Disable all plugins and scripts  
&nbsp;&nbsp;**--silent**   &nbsp;&nbsp;&nbsp;&nbsp; Hide output on console
For more help, you can use 'hexo help [command]' for the detailed information or you can check the docs: http://hexo.io/docs/

### c. 查看效果
还是在刚才的命令行窗口中，输入`hexo server`，启动服务器。
浏览器中输入：http://127.0.0.1:4000/ 以查看效果

以上：也就是`Hexo`在本地的效果搭建完毕。
## 步骤四：创建Github仓库
### a. 建立gibhub账户
要托管到`github`，那你就应该要有一个属于你自己的`github`帐号，所以你应该先到`github.com`注册
打开浏览器
在地址栏输入地址：`github.com`
填写用户名、邮箱、密码
点击`Sign up`即可简单地注册
### b. 建立托管仓库
完成注册，进入`github`平台，
点击`new repositories` 
新建一个新项目（你也可以加入到一个已有的项目）
![hexo](/images/201904/github_create.jpg "创建仓库1")
![hexo](/images/201904/github_create_2.jpg "创建仓库2")
上面的名字应该是如：`baiyazi.github.io`

## 步骤五：生成ssh添加到github

```
git config --global user.name "yourname"
git config --global user.email "youremail"
```

yourname输入你的GitHub用户名，youremail输入你GitHub的邮箱。这样GitHub才能知道你是不是对应它的账户。

可以用以下两条，检查一下你有没有输对
```
git config user.name
git config user.email
```
![hexo](/images/201904/github_git_count.jpg "验证github账户")

然后创建SSH,一路回车

```
ssh-keygen -t rsa -C "youremail"
```
![hexo](/images/201904/ssh_keygen.jpg "生成ssh key的正确样式")

这个时候它会告诉你已经生成了.ssh的文件夹。在你的电脑中找到这个文件夹。
![hexo](/images/201904/github_git_ssh.jpg "验证github账户")


>ssh，简单来讲，就是一个秘钥，其中，id_rsa是你这台电脑的私人秘钥，不能给别人看的，id_rsa.pub是公共秘钥，可以随便给别人看。把这个公钥放在GitHub上，这样当你链接GitHub自己的账户时，它就会根据公钥匹配你的私钥，当能够相互匹配时，才能够顺利的通过git上传你的文件到GitHub上。

而后在GitHub的setting中，找到SSH keys的设置选项，点击New SSH key
把你的id_rsa.pub里面的信息复制进去。Title随便什么都可以。

![hexo](/images/201904/github_git_seting_1.jpg "验证github账户")

![hexo](/images/201904/github_git_seting_2.jpg "验证github账户")

在gitbash中，查看是否成功

```
ssh -T git@github.com
```

![hexo](/images/201904/github_git_seting_test.jpg "验证github账户")


前面的主要目的就是生成秘钥，然后将公钥上传到`github`。
一台电脑可以配置多个`Hexo`，在生成第二个秘钥的时候，需要将秘钥所在的目录中的第一个删除，然后同样上传到对应的`github`的设置中去。
不同的是，第二个在执行`hexo d`发布的时候，需要输入用户邮箱和密码，以上传。

## 步骤六：将Hexo部署到Github
也就是需要将`hexo`和`GitHub`关联起来。
打开站点配置文件` _config.yml`，翻到最后，修改为
`YourgithubName`就是你的`GitHub`账户
```
deploy:
  type: git
  repo: https://github.com/YourgithubName/YourgithubName.github.io.git
  branch: master
```

还需要cmd切换到根目录，然后控制台安装`deploy-git` ，也就是部署的命令,这样你才能用命令部署到`GitHub`。
```
npm install hexo-deployer-git --save
```

执行`hexo d`上传资源到仓库中去。


## 最后： 修改为我们自己的域名：
我在阿里云购买的：https://wanwang.aliyun.com/?spm=5176.8142029.digitalization.2.e9396d3e46JCc5

注册后，点击右上角的控制台：
![dns](/images/201904/github_aliyun_1.jpg "登录后")
然后在左边导航栏中找到域名：
![dns](/images/201904/github_aliyun_2.jpg "进入控制台")
可以看见自己购买的域名的状态，没有购买的，在页面中找到域名注册即可：
![dns](/images/201904/github_aliyun_3.jpg "域名列表")
点击`解析`，进入域名配置，我只添加了CNAME记录和A记录：
![dns](/images/201904/github_aliyun_4.jpg "域名")
对了，IP地址来自于ping
![dns](/images/201904/github_aliyun_5.jpg "域名")


如果你有自己的域名的话，映射到github上，需要新建一个CNAME文件。
在github仓库中，新建一个CNAME文件，内容直接写需要绑定的域名
![dns](/images/201904/github_aliyun_6.png "设置CNAME")











