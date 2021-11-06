---
title: hexo页面底部添加网站运行时间
author: weizu
comments: true
date: 2019-5-6 10:18:28
categories: "Hexo教程"
tags: 
    - hexo
---

# 修改主题布局文件

打开`theme\next\layout\_layout.swig`，添加html代码。
``` html
<footer id="footer" class="footer">
      <div class="footer-inner">
        {% include '_partials/footer.swig' %}
        {% include '_third-party/analytics/analytics-with-widget.swig' %}
        {% block footer %}{% endblock %}

<!--上面是原有的，找到上面的代码，下面是自己编写的，拷贝上面链接中的代码即可。-->
	<span id="timeDate">载入天数...</span><span id="times">载入时分秒...</span>
    <script>
	var now = new Date(); 
	function createtime() { 
	    var grt= new Date("04/13/2019 12:00:00");//此处修改你的建站时间或者网站上线时间 
	    now.setTime(now.getTime()+250); 
	    days = (now - grt ) / 1000 / 60 / 60 / 24; dnum = Math.floor(days); 
	    hours = (now - grt ) / 1000 / 60 / 60 - (24 * dnum); hnum = Math.floor(hours); 
	    if(String(hnum).length ==1 ){hnum = "0" + hnum;} minutes = (now - grt ) / 1000 /60 - (24 * 60 * dnum) - (60 * hnum); 
	    mnum = Math.floor(minutes); if(String(mnum).length ==1 ){mnum = "0" + mnum;} 
	    seconds = (now - grt ) / 1000 - (24 * 60 * 60 * dnum) - (60 * 60 * hnum) - (60 * mnum); 
	    snum = Math.round(seconds); if(String(snum).length ==1 ){snum = "0" + snum;} 
	    document.getElementById("timeDate").innerHTML = "本站已安全运行 "+dnum+" 天 "; 
	    document.getElementById("times").innerHTML = hnum + " 小时 " + mnum + " 分 " + snum + " 秒"; 
	} 
    setInterval("createtime()",250);
    </script>
      </div>
    </footer>
</a>
```

然后重新启动服务器，就可以看见效果了。
```
hexo g
hexo s
```