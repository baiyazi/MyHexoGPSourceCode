---
title: hexo评论系统 | 来必力
author: weizu
comments: true
date: 2019-05-05 19:46:48
categories: "Hexo教程"
tags: 
    - hexo
    - 网页评论插件
---

# hexo评论系统
来必力https://livere.com
注册，登录。
然后出现了一般网站的使用代码：
``` html
<!-- 来必力City版安装代码 -->
<div id="lv-container" data-id="city" data-uid="MTAyMC80NDA3OC8yMDYxMw==">
	<script type="text/javascript">
   (function(d, s) {
       var j, e = d.getElementsByTagName(s)[0];

       if (typeof LivereTower === 'function') { return; }

       j = d.createElement(s);
       j.src = 'https://cdn-city.livere.com/js/embed.dist.js';
       j.async = true;

       e.parentNode.insertBefore(j, e);
   })(document, 'script');
	</script>
<noscript> 为正常使用来必力评论功能请激活JavaScript</noscript>
</div>
<!-- City版安装代码已完成 -->
```
我试了一下，在随便一个网页中使用如上的代码，就可以实现评论。
不得不说，JavaScript很强大。

---

由于next中已经做了处理，所以这里修改配置文件就可以了。
复制其中的uid字段。
打开主题目录下的blog/themes/next/_config.yml配置文件，定位到livere_uid字段，粘贴上刚刚复制的UID。
至此，大功告成。
效果图：
![e](/images/201905/2019-05-05_212038.jpg "来必力评论效果展示")