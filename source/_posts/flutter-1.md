---
title: flutter开发 | 一
date: 2019-8-3 16:05:39
author: 无涯明月
comments: true
categories: "flutter"
tags: 
    - flutter
---
>在大学接触过使用Java安装的eclipse集成开发环境来做apk。但是，还是感觉比较难，然后学习了一段时间就放弃了，当时也没有写博客的习惯，就完全忘记了。这两天想做一个VIP视频解析的apk，虽然网站已经有了。但是，广告还是比较多，所以想自己封装一个。
百度了一下，除了原生，都推荐flutter开发，试一试。

百度就可以看见文档：[地址](https://flutterchina.club/tutorials/)
一起来看看：
# 搭建开发环境
1. 安装[Git for Windows](https://git-scm.com/download/win)
2. 使用镜像
由于在国内访问`Flutter`有时可能会受到限制，`Flutter`官方为中国开发者搭建了临时镜像（`google`为国内开发者搭建的临时镜像），大家可以将如下环境变量加入到用户环境变量中：
打开`git`命令窗口：
``` text
export PUB_HOSTED_URL=https://pub.flutter-io.cn
export FLUTTER_STORAGE_BASE_URL=https://storage.flutter-io.cn
```
3. 下载SDK，[地址](https://flutter.io/sdk-archive/#windows)
下载完成后，解压。在`Flutter`安装目录的`flutter`文件下找到`flutter_console.bat`，双击运行并启动`flutter`命令。第一次运行一个`flutter`命令（如`flutter doctor`）时，它会下载它自己的依赖项并自行编译。以后再运行就会快得多。
按照提示：
> Unable to locate Android SDK.
https://developer.android.com/studio/index.html
On first launch it will assist you in installing the Android SDK
components.

4. 说是没有`Android SDK`，不妨先忽略，先安装`Android Studio`
不妨百度一下：`Android studio`，因为上面的官网需要梯子，就百度了。[地址](http://www.android-studio.org/)
其实，安装了`android studio`就安装了`sdk`，就是我们需要找到其位置，然后设置环境变量即可。
`setting -->Apperance & Behavior --> System Settings-->Android SDK`
点击相应的版本下载即可，也可以看见我的下载在：`C:\Users\baiyazi\AppData\Local\Android\Sdk`
新增一个环境变量：
`ANDROID_HOME` `：``C:\Users\baiyazi\AppData\Local\Android\Sdk`
再利用`flutter doctor`检查一下，发现就不说没有`SDK`了。

5. 设置`Android`模拟器
我这里跳过了设置您的`Android`设备，感觉还是模拟器带感，按照教程：
* 在`BIOS`中设置了`Intel Virtualization Technology`为`Enable`
* 开始创建虚拟机`Tools-->AVD Manager` ，然后`next`，按照提示我下载了`x86_64` 的` Android7.0`系统镜像，但是在右边出现了错误`/dev/kvm is not found`问题。下面就解决这个问题：

百度了一下，说是`intelhaxm`的部分安装失败导致的，在`Setting`中找到`Appearance & Behavior-->System Setting --> Android SDK-->SDK Tools`中可以看见`Android Studio`中是否安装了`Intel x86 Emulator Accelerator`是否安装，我这里测试了多次其实这里不需要安装（当然可能具有特殊性），如下图：
![](/images/201908/2019-08-04_152037.png)
找到`intelhaxm`目录：`C:\Users\baiyazi\AppData\Local\Android\Sdk\extras\intel\Hardware_Accelerated_Execution_Manager`
把电脑上已有的`haxm`卸载了，卸载方法也就是运行`intelhaxm-android.exe`，然后卸载掉。
在[Release HAXM v7.4.1 · intel/haxm · GitHub](https://github.com/intel/haxm/releases/tag/v7.4.1)中下载`haxm`，然后替换到上面的目录中，然后运行`intelhaxm-android.exe`重新安装。
在`cmd`中输入下面的命令可以查看是否成功：
``` text
C:\Users\baiyazi>sc query intelhaxm
SERVICE_NAME: intelhaxm
        TYPE               : 1  KERNEL_DRIVER
        STATE              : 4  RUNNING
                                (STOPPABLE, NOT_PAUSABLE, IGNORES_SHUTDOWN)
        WIN32_EXIT_CODE    : 0  (0x0)
        SERVICE_EXIT_CODE  : 0  (0x0)
        CHECKPOINT         : 0x0
        WAIT_HINT          : 0x0
```

接着就简单了，在来一次创建虚拟机，就发现不报错了，下面给出启动虚拟机的截图：
![](/images/201908/2019-08-04_153500.png)

启动了虚拟机，然后再次cmd输入flutter doctor，检查可发现设备可用了，但是Android Studio中的flutter 插件还没有安装。看文档需要安装两个插件Flutter和Dart插件

6. Flutter和Dart插件
Flutter插件： 支持Flutter开发工作流 (运行、调试、热重载等).
Dart插件： 提供代码分析 (输入代码时进行验证、代码补全等).
启动Android Studio.
打开插件首选项 (File>Settings>Plugins).
搜索Dart和flutter，然后分别并点击 install.（比较慢），很不幸出现了Plugin Flutter download or installing failed的提示，不甘心，多试了几次就可以了。
重启Android Studio后插件生效。不妨再来检查一下：
![](/images/201908/2019-08-04_161150.png)

7. 体验Flutter
File>New Flutter Project，然后需要选择Flutter SDK Path的路径，我这里是：E:\flutter
然后创建一个应用就可以了，好了后，运行：
![](/images/201908/2019-08-04_163428.png)








