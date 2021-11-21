---
title: flutter开发 | 二
date: 2019-8-4 17:28:54
author: 无涯明月
comments: true
categories: "flutter"
tags: 
    - flutter
---
>安装完成了，然后找了找教程。看见了这样一句话：
`Dart`是由谷歌开发的计算机编程语言,它可以被用于`web`、服务器、移动应用 和物联网等领域的开发。
`Dart`诞生于2011年，号称要取代`JavaScript`。但是过去的几年中一直不温不火。直到`Flutter`的出现现在被人们重新重视。
要学`Flutter`的话我们必须首先得会`Dart`。
`Flutter`是谷歌公司开发的一款开源、免费的移动UI框架，可以让我们快速的在`Android`和`iOS`上构建高质量`App`。它最大的特点就是跨平台、以及高性能。
由于谷歌的推广,以及国内阿里的闲鱼App是`Flutter`开发的。所以`Flutter`目前关注度非常高的框架。


Dart教程在线试听地址： https://www.bilibili.com/video/av52490605
Flutter教程在线试听地址： https://www.bilibili.com/video/av53072584/?p=1
Flutter中文网地址：https://flutterchina.club/widgets-intro/

# 目录结构
`android`和`ios`，是编译不同平台的支持；
`lib`中放置我们应用的资源文件，也是我们主要的编写地方；
`pubspec.yaml`是我们项目的配置文件；

## 入口
`APP`的入口是`lib/main.dart`文件，入口代码是：`void main() => runApp(new MyApp());`
提示：`main`函数使用了(`=>`)符号, 这是Dart中单行函数或方法的简写。
等价于：`void main(){runApp(new MyApp());}`
[在微信小程序中其实看见过，之前没注意，现在才知道是`Dart`。]

## hello
在`lib/main.dart`中编写代码，如下：
``` dart
import 'package:flutter/material.dart';

void main(){
  runApp(new Center(
    child: new Text(
      "Hello, world",
      textDirection: TextDirection.ltr,
    ),
  ));
}
```

上面的效果也就是简单的Hello world程序，就不截图了。
要使用dart，首先要导入包；然后在flutter中载入的所有的东西都是widget，也就是类；
widget大致分成两类，无状态的StatelessWidget或者是有状态的StatefulWidget：
具体的选择取决于您的widget是否需要管理一些状态。
widget的主要工作是实现一个build函数，用以构建自身。








``` text

```
