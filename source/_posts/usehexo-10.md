---
title: Hexo主题推荐
author: weizu
comments: true
cover: true
top: true
date: 2019-7-22 14:53:44
categories: "Hexo主题"
tags: 
    - hexo
---
前段时间电脑键盘用着莫名的无效了，加上自己的C盘已经快满了，就想着重装一个系统，瘦瘦身。
由于昨天重新安装了系统，hexo博客感觉next的主题也不是那么好看了。于是想着重找一个。下面推荐几款自己感觉比较好看的主题。

1. 主题：Melody  
参考文档：[安装详情](https://molunerfinn.com/hexo-theme-melody-doc/quick-start.html#installation)
效果参考：https://molunerfinn.com/
效果参考：https://elody-07.github.io/

2. 主题：hexo-theme-matery   
参考文档：[安装详情](https://blinkfox.github.io/2018/09/28/qian-duan/hexo-bo-ke-zhu-ti-zhi-hexo-theme-matery-de-jie-shao/)
效果参考：[闪烁之狐](https://blinkfox.github.io/2018/09/28/qian-duan/hexo-bo-ke-zhu-ti-zhi-hexo-theme-matery-de-jie-shao/)

3. 主题：Material X  
参考文档：[安装详情](https://segmentfault.com/a/1190000016006194?utm_source=tag-newest)
效果参考：https://xaoxuu.com/


我这里比较喜欢第二个，然后，就跟着教程安装一通。
# 安装hexo-theme-matery
## 下载主题
方法一：点击链接[下载](https://codeload.github.com/blinkfox/hexo-theme-matery/zip/master)，然后解压到themes文件夹下
方法二：使用命令来下载
在themes文件夹下，按住Ctrl，单击右键，选择Git Bash Here，保持联网，然后输入下面的命令即可下载：
``` 
git clone https://github.com/blinkfox/hexo-theme-matery.git
```

![e](/images/201907/2019-07-22_151929.png )
## 主配置文件基础配置
修改项目配置文件_config.yml 的 theme 的值：theme: matery-master
当然，名字看你自己文件夹是什么名字，如下图：
![e](/images/201907/2019-07-22_153312.png )

然后添加站点信息，以及URL信息：
``` Python
# Site
title: 无涯明月
subtitle: Where there is a will,there is a way.
description: 蹲得越用力，可能跳的越远，必然脚就越麻。
keywords: weizu,无涯明月,weizu学习之路,营业执照办理
author: 无涯明月
language: zh-Hans
timezone: UTC

# URL
## If your site is put in a subdirectory, set url as 'http://yoursite.com/child' and root as '/child/'
url: http://baiyazi.top
```

## 创建分类页
使用命令窗口，切换到博客根目录中，然后输入如下命令：
```
hexo new page "categories"
```

![e](/images/201907/2019-07-22_154458.png)
在根目录下的source文件夹下就会生成categories\index.md
编辑该文件（不妨先安装Typora软件，打开md文件更方便）：

``` text
---
title: categories
date: 2019-07-22 15:40:08
type: "categories"
layout: "categories"
---
```

## 创建标签页
同理，运行：
```
hexo new page "tags"
```

![e](/images/201907/2019-07-22_155250.png)
同理，会生成tags\index.md，编辑该文件：
``` text
---
title: tags
date: 2019-07-22 15:52:40
type: "tags"
layout: "tags"
---
```

## 创建关于页面
命令：`hexo new page "about"`
![e](/images/201907/2019-07-22_155543.png)
编辑页面：
``` text
---
title: about
date: 2019-07-22 15:55:36
type: "about"
layout: "about"
---
```

## 创建友情链接页面
很有意思，决定copy过来。类似的创建friends页：`hexo new page "friends"`
![e](/images/201907/2019-07-22_155919.png)
编辑页面：
``` text
---
title: friends
date: 2019-07-22 15:59:12
type: "friends"
layout: "friends"
---
```

同时，在你的博客 source 目录下新建 _data 目录，在 _data 目录中新建 friends.json 文件，文件内容如下所示：
``` json
[{
    "avatar": "http://image.luokangyuan.com/1_qq_27922023.jpg",
    "name": "码酱",
    "introduction": "我不是大佬，只是在追寻大佬的脚步",
    "url": "http://luokangyuan.com/",
    "title": "前去学习"
}, {
    "avatar": "http://image.luokangyuan.com/4027734.jpeg",
    "name": "闪烁之狐",
    "introduction": "编程界大佬，技术牛，人还特别好，不懂的都可以请教大佬",
    "url": "https://blinkfox.github.io/",
    "title": "前去学习"
}, {
    "avatar": "http://image.luokangyuan.com/avatar.jpg",
    "name": "ja_rome",
    "introduction": "平凡的脚步也可以走出伟大的行程",
    "url": "ttps://me.csdn.net/jlh912008548",
    "title": "前去学习"
}]
```

## 代码高亮
在根的主配置文件中，有highlight，可以设置代码高亮。
由于 Hexo 自带的代码高亮主题显示不好看，所以主题中使用到了 [hexo-prism-plugin](https://github.com/ele828/hexo-prism-plugin) 的 Hexo 插件来做代码高亮，安装命令如下：
运行命令：
``` text
npm i -S hexo-prism-plugin
```

![e](/images/201907/2019-07-22_160945.png)
至于警告，忽略就可以了。和window平台相关。
然后在根的主配置文件中，修改highlight字段：
``` json
highlight:
  enable: false
  line_number: true
  auto_detect: false
  tab_replace:

prism_plugin:
  mode: 'preprocess'    # realtime/preprocess
  theme: 'tomorrow'
  line_number: false    # default false
  custom_css:
```

## 搜索
本主题中还使用到了 [hexo-generator-search](https://github.com/wzpan/hexo-generator-search) 的 Hexo 插件来做内容搜索，安装命令如下：
```
npm install hexo-generator-search --save
```

![e](/images/201907/2019-07-22_161616.png)
在 Hexo 根目录下的 _config.yml 文件中，新增以下的配置项：
```
search:
  path: search.xml
  field: post
```

## 文章字数统计插件
如果你想要在文章中显示文章字数、阅读时长信息，可以安装 [hexo-wordcount](https://github.com/willin/hexo-wordcount)插件。
安装命令如下：
```
npm i --save hexo-wordcount
```

![e](/images/201907/2019-07-22_162011.png)
然后只需在本主题下的 _config.yml 文件中，激活以下配置项即可：
```
wordCount:
  enable: false # 将这个值设置为 true 即可.
  postWordCount: true
  min2read: true
  totalCount: true
```

## 中文链接转拼音
如果你的文章名称是中文的，那么 Hexo 默认生成的永久链接也会有中文，这样不利于 SEO，且 gitment 评论对中文链接也不支持。我们可以用 [hexo-permalink-pinyin](https://github.com/viko16/hexo-permalink-pinyin) Hexo 插件使在生成文章时生成中文拼音的永久链接。
安装命令如下：
```
npm i hexo-permalink-pinyin --save
```

![e](/images/201907/2019-07-22_162835.png)
在 Hexo 根目录下的 _config.yml 文件中，新增以下的配置项：
```
permalink_pinyin:
  enable: true
  separator: '-' # default: '-'
```

注：除了此插件外，[hexo-abbrlink](https://github.com/rozbo/hexo-abbrlink) 插件也可以生成非中文的链接。

## 添加 RSS 订阅支持
本主题中还使用到了 [hexo-generator-feed](https://github.com/hexojs/hexo-generator-feed) 的 Hexo 插件来做 RSS，安装命令如下：
```
npm install hexo-generator-feed --save
```

![e](/images/201907/2019-07-22_163804.png)
在 Hexo 根目录下的 _config.yml 文件中，新增以下的配置项：
```
feed:
  type: atom
  path: atom.xml
  limit: 20
  hub:
  content:
  content_limit: 140
  content_limit_delim: ' '
  order_by: -date
```

执行 hexo clean && hexo g 重新生成博客文件，然后在 public 文件夹中即可看到 atom.xml 文件，说明你已经安装成功了。

## 修改社交链接
在主题的 _config.yml 文件中，默认支持 QQ、GitHub 和邮箱的配置，你可以在主题文件的 /layout/_partial/social-link.ejs 文件中，新增、修改你需要的社交链接地址，增加链接可参考如下代码：
```
<a href="https://github.com/blinkfox" class="tooltipped" target="_blank" data-tooltip="访问我的GitHub" data-position="top" data-delay="50">
    <i class="fa fa-github"></i>
</a>
```

其中，社交图标（如：fa-github）你可以在 Font Awesome 中搜索找到。以下是常用社交图标的标识，供你参考：
```
Facebook: fa-facebook
Twitter: fa-twitter
Google-plus: fa-google-plus
Linkedin: fa-linkedin
Tumblr: fa-tumblr
Medium: fa-medium
Slack: fa-slack
新浪微博: fa-weibo
微信: fa-wechat
QQ: fa-qq
```

注意: 本主题中使用的 Font Awesome 版本为 4.7.0。

## 修改打赏的二维码图片
在主题文件的 source/medias/reward 文件中，你可以替换成你的的微信和支付宝的打赏二维码图片。

## 配置音乐播放器
要支持音乐播放，就必须开启音乐的播放配置和音乐数据的文件。
首先，在你的博客 source 目录下的 _data 目录（没有的话就新建一个）中新建 musics.json 文件，文件内容如下所示：
```json
[{
    "name": "五月雨变奏电音",
    "artist": "AnimeVibe",
    "url": "http://xxx.com/music1.mp3",
    "cover": "http://xxx.com/music-cover1.png"
}, {
    "name": "Take me hand",
    "artist": "DAISHI DANCE,Cecile Corbel",
    "url": "/medias/music/music2.mp3",
    "cover": "/medias/music/cover2.png"
}, {
    "name": "Shape of You",
    "artist": "J.Fla",
    "url": "http://xxx.com/music3.mp3",
    "cover": "http://xxx.com/music-cover3.png"
}]
```
注：以上 JSON 中的属性：name、artist、url、cover 分别表示音乐的名称、作者、音乐文件地址、音乐封面。
然后，在主题的 _config.yml 配置文件中激活配置即可：
```
# 是否在首页显示音乐.
music:
  enable: true
  showTitle: false
  title: 听听音乐
  fixed: false # 是否开启吸底模式
  autoplay: false # 是否自动播放
  theme: '#42b983'
  loop: 'all' # 音频循环播放, 可选值: 'all', 'one', 'none'
  order: 'list' # 音频循环顺序, 可选值: 'list', 'random'
  preload: 'auto' # 预加载，可选值: 'none', 'metadata', 'auto'
  volume: 0.7 # 默认音量，请注意播放器会记忆用户设置，用户手动设置音量后默认音量即失效
  listFolded: false # 列表默认折叠
  listMaxHeight: # 列表最大高度
```

## 文章 Front-matter 介绍
Front-matter 选项详解
Front-matter 选项中的所有内容均为非必填的。但我仍然建议至少填写 title 和 date 的值。
<table><thead><tr><th>配置选项</th><th>默认值</th><th>描述</th></tr></thead><tbody><tr><td>title</td><td><code>Markdown</code> 的文件标题</td><td>文章标题，强烈建议填写此选项</td></tr><tr><td>date</td><td>文件创建时的日期时间</td><td>发布时间，强烈建议填写此选项，且最好保证全局唯一</td></tr><tr><td>author</td><td>根 <code>_config.yml</code> 中的 <code>author</code></td><td>文章作者</td></tr><tr><td>img</td><td><code>featureImages</code> 中的某个值</td><td>文章特征图，推荐使用图床(腾讯云、七牛云、又拍云等)来做图片的路径.如: <code>http://xxx.com/xxx.jpg</code></td></tr><tr><td>top</td><td><code>true</code></td><td>推荐文章（文章是否置顶），如果 <code>top</code> 值为 <code>true</code>，则会作为首页推荐文章</td></tr><tr><td>cover</td><td><code>false</code></td><td><code>v1.0.2</code>版本新增，表示该文章是否需要加入到首页轮播封面中</td></tr><tr><td>coverImg</td><td>无</td><td><code>v1.0.2</code>版本新增，表示该文章在首页轮播封面需要显示的图片路径，如果没有，则默认使用文章的特色图片</td></tr><tr><td>password</td><td>无</td><td>文章阅读密码，如果要对文章设置阅读验证密码的话，就可以设置 <code>password</code> 的值，该值必须是用 <code>SHA256</code> 加密后的密码，防止被他人识破。前提是在主题的 <code>config.yml</code> 中激活了 <code>verifyPassword</code> 选项</td></tr><tr><td>toc</td><td><code>true</code></td><td>是否开启 TOC，可以针对某篇文章单独关闭 TOC 的功能。前提是在主题的 <code>config.yml</code> 中激活了 <code>toc</code> 选项</td></tr><tr><td>mathjax</td><td><code>false</code></td><td>是否开启数学公式支持 ，本文章是否开启 <code>mathjax</code>，且需要在主题的 <code>_config.yml</code> 文件中也需要开启才行</td></tr><tr><td>summary</td><td>无</td><td>文章摘要，自定义的文章摘要内容，如果这个属性有值，文章卡片摘要就显示这段文字，否则程序会自动截取文章的部分内容作为摘要</td></tr><tr><td>categories</td><td>无</td><td>文章分类，本主题的分类表示宏观上大的分类，只建议一篇文章一个分类</td></tr><tr><td>tags</td><td>无</td><td>文章标签，一篇文章可以多个标签</td></tr></tbody></table>
注意:
如果 img 属性不填写的话，文章特色图会根据文章标题的 hashcode 的值取余，然后选取主题中对应的特色图片，从而达到让所有文章都的特色图各有特色。
date 的值尽量保证每篇文章是唯一的，因为本主题中 Gitalk 和 Gitment 识别 id 是通过 date 的值来作为唯一标识的。
如果要对文章设置阅读验证密码的功能，不仅要在 Front-matter 中设置采用了 SHA256 加密的 password 的值，还需要在主题的 _config.yml 中激活了配置。有些在线的 SHA256 加密的地址，可供你使用：开源中国在线工具、chahuo、站长工具。
以下为文章的 Front-matter 示例。

最简示例
```
---
title: typora-vue-theme主题介绍
date: 2018-09-07 09:25:00
---
```

最全示例
```
---
title: typora-vue-theme主题介绍
date: 2018-09-07 09:25:00
author: 赵奇
img: /source/images/xxx.jpg
top: true
cover: true
coverImg: /images/1.jpg
password: 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
toc: false
mathjax: false
summary: 这是你自定义的文章摘要内容，如果这个属性有值，文章卡片摘要就显示这段文字，否则程序会自动截取文章的部分内容作为摘要
categories: Markdown
tags:
  - Typora
  - Markdown
---
```

## 修改 banner 图和文章特色图
你可以直接在 /source/medias/banner 文件夹中更换你喜欢的 banner 图片，主题代码中是每天动态切换一张，只需 7 张即可。如果你会 JavaScript 代码，可以修改成你自己喜欢切换逻辑，如：随机切换等，banner 切换的代码位置在 /layout/_partial/bg-cover-content.ejs 文件的 <script></script> 代码中：
```
$('.bg-cover').css('background-image', 'url(/medias/banner/' + new Date().getDay() + '.jpg)');
```

在 /source/medias/featureimages 文件夹中默认有 24 张特色图片，你可以再增加或者减少，并需要在 _config.yml 做同步修改。

版本记录










```

```
