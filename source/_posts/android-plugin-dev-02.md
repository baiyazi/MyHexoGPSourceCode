---
title: Android插件化开发指南 | 2.15 实现一个音乐播放器APP
date: 2021年10月31日 17:35:10
comments: true
categories: "Android插件化开发"
tags: 
    - Android
---

欢迎访问：[Android插件化开发指南——实践之Activity转场效果（仿酷狗音乐启动页）](https://blog.csdn.net/qq_26460841/article/details/121061646)


@[toc]
# 1. 前言
在[Android插件化开发指南——2.15 实现一个音乐播放器APP](https://blog.csdn.net/qq_26460841/article/details/120992273)中介绍了音乐播放的基本知识，以及在最后提到了想仿一个音乐播放器，所以在接下来的日子里将继续仿造。刚刚结合[Android启动页白屏/黑屏问题解决](https://blog.csdn.net/qq_26460841/article/details/121003811)一文，简单实现了仿酷狗音乐的启动页面，具体效果如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/29b5bbe069aa40119f5496316b7f94a2.gif#pic_center =200x)
但是退出的效果和酷狗的退出效果差别很大，比如下面我截取片段：

![在这里插入图片描述](https://img-blog.csdnimg.cn/8a67646aaa66485d9c38205f777a9e9b.gif#pic_center =200x)
可以发现明显的页面退出效果更加炫酷。所以在这篇的接下来的篇幅中，将一起来探寻下这个炫酷的效果是怎么实现的。百度了一下，发现了这篇博客写的挺好的：[酷炫的Activity切换动画，打造更好的用户体验](https://blog.csdn.net/huachao1001/article/details/51659963)。接下来就来学习下这个实现过程。

# 2. Activity退出动画
在这篇博客：[酷炫的Activity切换动画，打造更好的用户体验](https://blog.csdn.net/huachao1001/article/details/51659963)中提到：

>`Android 5.0 (API 21) `及更高版本系统内置了`Activity`之间的切换动画

那么不妨去[Android官网](https://developer.android.google.cn/)看看是否存在相关说明，很容易找到了这篇文档：[使用动画启动 Activity](https://developer.android.google.cn/training/transitions/start-activity)，接下来就来看看这篇文档。

对于使用使用动画启动 `Activity`，一共提供了**三种过渡**，进入和退出过渡，以及 `Activity` 之间共享元素的过渡指定自定义动画。

- **进入**过渡决定了 `Activity` 中的视图如何进入场景。例如，在“爆炸式”进入过渡中，视图从外场进入场景，飞向屏幕中心。
- **退出**过渡决定了 `Activity` 中的视图如何退出场景。例如，在“爆炸式”退出过渡中，视图从屏幕中心离开场景。
- **共享元素**过渡决定了两个 `Activity` 共享的视图如何在这些 `Activity` 之间过渡。例如，如果两个 `Activity` 使用相同的图片（但位置和大小不同），`changeImageTransform` 共享元素过渡就会在这些 `Activity` 之间流畅地平移和缩放该图片

结合自己当前的需求，很容易知道自己所需要的为：`Activity` 视图从屏幕中心退出的场景。而在`Android` 支持以下进入和退出过渡：

- **爆炸式（Explode）** - 将视图移入场景中心或从中移出。
- **滑动式（Slide）** - 将视图从场景的其中一个边缘移入或移出。
- **淡入淡出式（Fade）** - 通过更改视图的不透明度，在场景中添加视图或从中移除视图。

故而最终所需要的效果应该是：**淡入淡出式从从屏幕中心移除视图**。至于其余的动画，可以参考博客[酷炫的Activity切换动画，打造更好的用户体验](https://blog.csdn.net/huachao1001/article/details/51659963)或者官方文档之 [使用动画启动 Activity](https://developer.android.google.cn/training/transitions/start-activity)，这里不再介绍。

## 2.1 简单使用
为了可以使用窗口过渡特效，所以首先需要启动可用，即在主题配置文件中，加上：

```xml
<!-- enable window content transitions -->
<item name="android:windowActivityTransitions">true</item>
```

我这里定义在`themes.xml`文件中，比如：

```xml
<style name="WelcomeThemeBefore" parent="Theme.AppCompat.NoActionBar">
    <!-- enable window content transitions -->
    <item name="android:windowActivityTransitions">true</item>
    <!--设置背景颜色-->
    <item name="android:windowBackground">@color/full_screen</item>
    <!--设置没有ActionBar-->
    <item name="android:windowNoTitle">true</item>
    <!--设置顶部状态栏颜色-->
    <item name="android:statusBarColor" tools:targetApi="l">@color/full_screen</item>
    <!-- 状态栏字体设置为深色 -->
    <item name="android:windowLightStatusBar">true</item>
</style>
```
当然，也可以使用代码的方式来启动支持过渡，比如：

```java
getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
```
任意选择一种即可。这里我选择`xml`文件配置的方式。

因为我们需要在第一个页面的退出过渡，所以需要为这个`Activity`指定退出的过渡，当使用`startActivity`的时候进行指定启动另一个`Activity`的时候，会激活配置的过渡。在`Activity`指定退出的过渡代码为：

```java
getWindow().setExitTransition(new Explode());
```
同理也可以指定进入的过渡：

```java
 getWindow().setEnterTransition(new Explode().setDuration(500));
```

但是其实很不幸，并没有生效。因为需要设置效果为：在所要跳转的`Activity`页面（即目标`Activity`）中设置该`Activity`的进出场动画。而不是为当前`WelcomeActivity`设置。比如我们在目标`MainActivity`设置为：

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode().setDuration(500));
        setContentView(R.layout.activity_main);

    }
}
```
然后在`WelcomeActivity`中进行启动：

```java
Intent intent = new Intent();
intent.setClass(this, MainActivity.class);
startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
```
就可以看到`MainActivity`的启动效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/5f431ebda0c147b9b3a3931fc79f18b1.gif#pic_center =200x)
但这确实不是这里所需要的，所以需要另一种**转场效果**。
## 2.2 overridePendingTransition
在`5.0`之前如果我们想要在启动`Activity`有特效，就需要使用`overridePendingTransition`方法来指定入场和出场的两个补间动画。所以这里可以采用这种方式来试试。因为这里只需要出场动画，所以这里定义一个`/anim/out.xml`文件，其内容为：

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <scale
        android:duration="1000"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:pivotX="50%p"
        android:pivotY="50%p"
        android:toXScale="0.5"
        android:toYScale="0.5" />
    <alpha
        android:duration="1000"
        android:fromAlpha="1.0"
        android:toAlpha="0" />
</set>
```
然后在`startActivity`之后，调用`overridePendingTransition`方法来指定动画文件即可，即：

```java
Intent intent = new Intent();
intent.setClass(this, MainActivity.class);
startActivity(intent);
overridePendingTransition(0, R.anim.out);
```
效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/ff59f05b0d9d4955aeebc623977d3aca.gif#pic_center =200x)


上面这种方式来自博客： [使用动画启动 Activity](https://developer.android.google.cn/training/transitions/start-activity)，感兴趣可以阅读原文。

# 3. 后记
完整代码地址：[https://github.com/baiyazi/MusicApp](https://github.com/baiyazi/MusicApp/tree/main/mymusicdemo-01)


___
**References**
- [用IdleHandler来做闲时等待](https://blog.csdn.net/qq_26460841/article/details/119841956)
- [Android启动页白屏/黑屏问题解决](https://blog.csdn.net/qq_26460841/article/details/121003811)
- [Android插件化开发指南——2.15 实现一个音乐播放器APP](https://blog.csdn.net/qq_26460841/article/details/120992273)
- [酷炫的Activity切换动画，打造更好的用户体验](https://blog.csdn.net/huachao1001/article/details/51659963)
- [使用动画启动 Activity](https://developer.android.google.cn/training/transitions/start-activity)

