---
title: android | 声明父控件，显顶部返回
date: 2019-8-9 18:09:56
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

先看看声明和未声明父控件的对比图：
![](/images/201908/2019-08-09_183414.png)

多了返回按钮。
参考文档[地址](https://developer.android.google.cn/training/basics/firstapp/starting-activity)

声明方法：只需要在 `AndroidManifest.xml` 文件中声明哪个 `Activity` 是逻辑父屏幕即可。
``` xml
<activity android:name=".DisplayMessageActivity"
          android:parentActivityName=".MainActivity">
        <!-- The meta-data tag is required if you support API level 15 and lower -->
   <meta-data
         android:name="android.support.PARENT_ACTIVITY"
         android:value=".MainActivity" />
</activity>    
```

父`Activity`是`MainActivity`，子`Activity`是`DisplayMessageActivity`。
