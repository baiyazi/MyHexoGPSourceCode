---
title: SEO优化设置
author: weizu
comments: true
date: 2019-5-6 15:22:50
categories: "Hexo教程"
tags: 
    - hexo
---

虽说写博客不是为了搜索排名来写，但是，一定的搜索引擎流量或许能够让博客被更多的人知晓，也就有可能认识到与你志同道合的朋友~ 这里记录一些简单的SEO做法
1、添加robots.txt
　　robots.txt是搜索引擎中访问网站的时候要查看的第一个文件。robots.txt文件告诉蜘蛛程序在服务器上什么文件是可以被查看的。 
这里推荐我这种写法(当然，hexo的版本不同可能文件目录有所不同)
robots.txt 放置在网站的根目录中，这里也就是我们的public文件下
```
# welcome to : www.baiyazi.top
User-agent: *
Allow: /
Allow: /archives/

Disallow: /js/
Disallow: /css/
Disallow: /fonts/
Disallow: /vendors/
Disallow: /lib/
Sitemap: https://www.baiyazi.top/sitemap.xml
Sitemap: https://www.baiyazi.top/baidusitemap.xml
```

2、安装sitemap插件
sitemap在SEO过程中有着十分重要的地位，同时也能限制蜘蛛对某些特定目录的爬取。

站点地图是一种文件，您可以通过该文件列出您网站上的网页，从而将您网站内容的组织架构告知Google和其他搜索引擎。Googlebot等搜索引擎网页抓取工具会读取此文件，以便更加智能地抓取您的网站

首先安装插件
在站点的根目录下执行以下命令：
```
npm install hexo-generator-sitemap --save
npm install hexo-generator-baidu-sitemap --save
```

然后配置博客配置文件，在_config.yml中添加如下代码：

```
# 自动生成sitemap
sitemap:
	path: sitemap.xml
baidusitemap:
	path: baidusitemap.xml
```

最后编译博客文件：
```
hexo g
```

如果你在你的博客根目录的public下面发现生成了sitemap.xml以及baidusitemap.xml就表示成功了

每次hexo g后都会在/public目录下生成sitemap.xml和baidusitemap.xml，这就是你的站点地图。


优化结构：

seo搜索引擎优化认为，网站的最佳结构是用户从首页点击三次就可以到达任何一个页面，但是我们使用hexo编译的站点打开文章的url是：sitename/year/mounth/day/title四层的结构，这样的url结构很不利于seo，爬虫就会经常爬不到我们的文章，于是，我们可以将url直接改成sitename/title的形式，并且title最好是用英文，在根目录的配置文件下修改permalink如下：
```
# URL
## If your site is put in a subdirectory, set url as 'http://yoursite.com/child' and root as '/child/'
url: http://baiyazi.top
root: /
permalink: :year/:month/:title/
permalink_defaults:
```



开启SEO优化选项
hexo博客next提供了seo优化选项，在主题配置文件_config.yml中有个选项是seo，默认是false，改成true即开启了seo优化，会进行一些seo优化，如改变博文title等
```
seo: true
```


开启推送

百度站长平台
http://zhanzhang.baidu.com/
首先要配置站点认证
这里有一个坑需要注意。如果你的hexo博客是托管在github上的就不要用html静态key来做验证了。应该是域名解析的方式也就是别名解析。

我这里在阿里云的控制台中添加一个按照百度站长管理平台要求的CNAME记录：

![e](/images/201905/2019-05-06_165703.jpg)

然后再点击验证，等一会儿就可以看见：

![e](/images/201905/2019-05-06_165850.jpg "验证成功")


认证成功后找到Robots：
前面我们设置了站点地图sitemap和robots文件，这里检测一下就没什么问题：
![e](/images/201905/2019-05-06_164627.jpg "robots.txt")


开启推送
然后就可以开始推送了，基本上推送有三种方式：主动推送>自动推送>sitemap三种，一般主动提交比手动提交效果好。

先看看自动推送
把下面的代码放到D:\lang_blog\themes\next\source\js\src目录下，文件名为baitui.js
下面的代码来自站长平台中的-链接提交-自动推送
自动推送代码如何安装使用？
站长需要在每个页面的HTML代码中包含以下自动推送JS代码：
``` html
<script>
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
    }
    else {
        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>
```

为了方便，可以在模板文件中引入：
```
<script type="text/javascript" src="/js/src/baitui.js"></script>
```

这样只要访问你的这个页面，就会自动向百度推送你的这个网页。


可以看见，自动提交中有sitemap方式，

提交sitemap
可以看看你自己网站的sitemap:
https://baiyazi.top/baidusitemap.xml
根据提示操作即可。建议采用自动提交额，手动提交会累死人

提交完了等着就好了，可能要很久才生效。。。



---

最后就是一些优化的配置，关键字等

在文章模板中scaffolds/post.md中添加如下代码，用于生成的文章中添加关键字和描述。
```
keywords:
description:
```

首页title优化
更改index.swig文件，文件路径是 your-hexo-site\themes\next\layout ：
``` text
{% block title %}{{ config.title }} {% if theme.index_with_subtitle and config.subtitle %} - {{config.subtitle }}{% endif %}{% endblock %}

改成
{% block title %}{{ config.title }} - {{ theme.description }} {% if theme.index_with_subtitle and config.subtitle %} - {{config.subtitle }}{% endif %}{% endblock %}

```


给非友情链接的出站链接添加 “nofollow” 标签
经过chinaz站长工具友情链接检测，发现有不必要的PR值输出，对于非友情链接的PR值输出，我们可以加上nofollow便签避免不必要的PR输出。

以hexo的NexT主题为例，需要修改两处
找到footer.swig,路径在your-hexo-site\themes\next\layout\_partials，将下面代码

```
{{ __('footer.powered', '<a class="theme-link" href="http://hexo.io">Hexo</a>') }}
```

改成

```
{{ __('footer.powered', '<a class="theme-link" href="http://hexo.io" rel="external nofollow">Hexo</a>') }}
```

也就是为外链加上`rel="external nofollow"`，不过我在配置文件中关闭了版权，可以不用设置。

将下面代码
```
<a class="theme-link" href="https://github.com/iissnan/hexo-theme-next">
```

改成

```
<a class="theme-link" href="https://github.com/iissnan/hexo-theme-next" rel="external nofollow">
```

同样的我这里还是不用设置。

---

关键词与描述
搜索引擎除了主要抓取标题外，页面的关键词和描述也会抓取。
在\scaffolds\post.md中添加如下代码，用于生成的文章中添加关键字和描述。
```
keywords: 
description: 
```

在\themes\next\layout_partials\head.swig有如下代码，用于生成文章的keywords。暂时还没找到生成description的位置。
```
{% if page.keywords %}
  <meta name="keywords" content="{{ page.keywords }}" />
{% elif page.tags and page.tags.length %}
  <meta name="keywords" content="{% for tag in page.tags %}{{ tag.name }},{% endfor %}" />
{% elif theme.keywords %}
  <meta name="keywords" content="{{ theme.keywords }}" />
{% endif %}
```

然后在\themes\next\layout_macro\post.swig中找到这个位置：
```
{% if post.description %}
```

将以下代码去掉：
```
{% if post.description and theme.excerpt_description %}
          {{ post.description }}
          <!--noindex-->
          <div class="post-button text-center">
            <a class="btn" href="{{ url_for(post.path) }}">
              {{ __('post.read_more') }} &raquo;
            </a>
          </div>
          <!--/noindex-->
{% elif post.excerpt  %}
```

否则首页的文章摘要就会变成文章的description。

举个例子：
```
---
title: HEXO SEO优化方法总结
keywords: [HEXO,HEXO SEO优化]
description: 总结一下使用Hexo搭建博客后，SEO优化方案的总结，后期会不定期更新。
---
```

