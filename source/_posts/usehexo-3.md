---
title: hexo在线聊天功能 | DaoVoice
author: weizu
comments: true
date: 2019-5-5 22:07:45
categories: "Hexo教程"
tags: 
    - hexo
    - 网页在线聊天插件
---

# 注册
首先需要注册一个 DaoVoice，[点击注册](http://dashboard.daovoice.io/get-started?invite_code=7f3d6e70)

注册成功后，进入后台控制台，进入到`(左边导航栏) 应用设置-->(次左边导航栏)安装到网站 `页面，可以看见：您想和谁沟通
出现下面的代码：
1.将下面代码粘贴在页面的 </head> 之前
``` html
<script>(function(i,s,o,g,r,a,m){i["DaoVoiceObject"]=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;a.charset="utf-8";m.parentNode.insertBefore(a,m)})(window,document,"script",('https:' == document.location.protocol ? 'https:' : 'http:') + "//widget.daovoice.io/widget/bdf6be59.js","daovoice")</script>
```

2.调用下面的 JavaScript 与注册用户沟通
``` javascript
daovoice('init', {
  app_id: "bdf6be59",
  user_id: "NO_89757", // 必填: 该用户在您系统上的唯一ID
  email: "daovoice@example.com", // 选填:  该用户在您系统上的主邮箱
  name: "道客船长", // 选填: 用户名
  signed_up: 1449821660 // 选填: 用户的注册时间，用Unix时间戳表示
});
daovoice('update');
```

3.调用下面的 JavaScript 与匿名访客沟通
``` javascript
daovoice('init', {
  app_id: "bdf6be59"
});
daovoice('update');
```

我们，不妨再普通网站上键入试试。发现也成功了。

---

# 定制
下面我们需要为hexo定制
以 next 主题为例，打开 themes/next/layout/_partials/head.swig 文件中添加如下代码，位置随意：
``` javascript
{% if theme.daovoice %}
  <script>
  (function(i,s,o,g,r,a,m){i["DaoVoiceObject"]=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;a.charset="utf-8";m.parentNode.insertBefore(a,m)})(window,document,"script",('https:' == document.location.protocol ? 'https:' : 'http:') + "//widget.daovoice.io/widget/0f81ff2f.js","daovoice")
  daovoice('init', {
      app_id: "{{theme.daovoice_app_id}}"
    });
  daovoice('update');
  </script>
{% endif %}
```

在主题配置文件 _config.yml，任意位置，新建添加如下代码：
```
# Online contact 
daovoice: true
daovoice_app_id: 这里输入前面获取的app_id
```

打开你的网站，可以看见效果。
也可以看看官网给的效果：[点击打开聊天](http://chat.daovoice.io/?id=bdf6be59)

---

下面我们来设置一下:
* 定制欢迎辞
聊天设置-定制欢迎辞-针对访客中键入即可。

* 定制聊天窗口样式
设置窗口的颜色和位置，凭自己喜好。

* 去除版权
这里是收费了的了。

* 修改用户信息
点击右上角-个人设置，可以修改用户名和头像，显得正式。

* 绑定微信
还是设置，中可以绑定，扫描二维码即可。

---


以上就是全部了。



