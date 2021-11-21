# MyHexoGPSourceCode
 我的`Github Pages`的源码，使用`hexo`主题。地址可访问：https://baiyazi.github.io



关于如何在`github`上搭建个人博客，以及如何使用`hexo`主题？这部分内容在我的博客：https://baiyazi.github.io 中均有文章说明。



因为`Github`访问受限，所以考虑还是使用阿里的服务器来挂。

将`hexo`生成的`public`复制到这个目录中，然后使用`pm2`运行即可。

```
pm2 start app.js
pm2 stop app.js
```
默认端口为`4000`。

