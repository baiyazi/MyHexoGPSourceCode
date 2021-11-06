---
title: android | `Activity`生命周期
date: 2019-8-9 16:23:22
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

>还是上一节的文档中，我们找到`Activity`部分的介绍，这里给出[地址](https://developer.android.google.cn/guide/components/activities/)
选择部分摘要。

# 简介
`Activity` 是一个应用组件，用户可与其提供的屏幕进行交互，以执行拨打电话、拍摄照片、发送电子邮件或查看地图等操作。 每个 `Activity` 都会获得一个用于绘制其用户界面的窗口。窗口通常会充满屏幕，但也可小于屏幕并浮动在其他窗口之上。

一个应用通常由多个彼此松散联系的 `Activity` 组成。 一般会指定应用中的某个 `Activity` 为“主”`Activity`，即首次启动应用时呈现给用户的那个 `Activity`。 而且每个 `Activity` 均可启动另一个 `Activity`，以便执行不同的操作。 每次新 `Activity` 启动时，前一 `Activity` 便会停止，但系统会在堆栈（“返回栈”）中保留该 `Activity`。 当新 `Activity` 启动时，系统会将其推送到返回栈上，并取得用户焦点。 返回栈遵循基本的“后进先出”堆栈机制，因此，当用户完成当前 `Activity` 并按“返回”按钮时，系统会从堆栈中将其弹出（并销毁），然后恢复前一 `Activity`。 

当一个 `Activity` 因某个新 `Activity` 启动而停止时，系统会通过该 `Activity` 的生命周期回调方法通知其这一状态变化。`Activity` 因状态变化—系统是创建 `Activity`、停止 `Activity`、恢复 `Activity` 还是销毁 `Activity`— 而收到的回调方法可能有若干种，每一种回调都会为您提供执行与该状态变化相应的特定操作的机会。

# `Activity` 生命周期
`Activity` 基本上以三种状态存在：
* **继续**
此 `Activity` 位于屏幕前台并具有用户焦点。（有时也将此状态称作“运行中”。）
* **暂停**
另一个 `Activity` 位于屏幕前台并具有用户焦点，但此 `Activity` 仍可见。也就是说，另一个 `Activity` 显示在此 `Activity` 上方，并且该 `Activity` 部分透明或未覆盖整个屏幕。 暂停的 `Activity` 处于完全活动状态（`Activity` 对象保留在内存中，它保留了所有状态和成员信息，并与窗口管理器保持连接），但在内存极度不足的情况下，可能会被系统终止。
* **停止**
该 `Activity` 被另一个 `Activity` 完全遮盖（该 `Activity` 目前位于“后台”）。 已停止的 `Activity` 同样仍处于活动状态（`Activity` 对象保留在内存中，它保留了所有状态和成员信息，但未与窗口管理器连接）。 不过，它对用户不再可见，在他处需要内存时可能会被系统终止。

如果 `Activity` 处于暂停或停止状态，系统可通过要求其结束（调用其 `finish()` 方法）或直接终止其进程，将其从内存中删除。（将其结束或终止后）再次打开 `Activity` 时，必须重建。

## 对应的生命周期方法：
* `onCreate(Bundle savedInstanceState)`
必须实现此方法。系统会在创建您的 `Activity` 时调用此方法。您应该在实现内初始化 `Activity` 的必需组件。 最重要的是，您必须在此方法内调用 `setContentView()`，以定义 `Activity` 用户界面的布局。始终后接 `onStart()`。
* `onStart()`
在 `Activity` 即将对用户可见之前调用。
如果 `Activity` 转入前台，则后接 `onResume()`，如果 `Activity` 转入隐藏状态，则后接 `onStop()`。
* `onRestart()`
在 `Activity` 已停止并即将再次启动前调用。
始终后接 `onStart()`
* `onResume()`
在 `Activity` 即将开始与用户进行交互之前调用。 此时，`Activity` 处于 `Activity` 堆栈的顶层，并具有用户输入焦点。
始终后接 `onPause()`。
* `onPause()`
系统将此方法作为用户离开 `Activity` 的第一个信号（但并不总是意味着 `Activity` 会被销毁）进行调用。 您通常应该在此方法内确认在当前用户会话结束后仍然有效的任何更改（因为用户可能不会返回）。当系统即将开始继续另一个 `Activity` 时调用。 此方法通常用于确认对持久性数据的未保存更改、停止动画以及其他可能消耗 CPU 的内容，诸如此类。 它应该非常迅速地执行所需操作，因为它返回后，下一个 `Activity` 才能继续执行。
如果 `Activity` 返回前台，则后接 `onResume()`，如果 `Activity` 转入对用户不可见状态，则后接 `onStop()`。
* `onStop()`
在 `Activity` 对用户不再可见时调用。如果 `Activity` 被销毁，或另一个 `Activity`（一个现有 `Activity` 或新 `Activity`）继续执行并将其覆盖，就可能发生这种情况。
如果 `Activity` 恢复与用户的交互，则后接 `onRestart()`，如果 `Activity` 被销毁，则后接 `onDestroy()`。
* `onDestroy()` 
在 `Activity` 被销毁前调用。这是 `Activity` 将收到的最后调用。 当 `Activity` 结束（有人对 `Activity` 调用了 `finish()`），或系统为节省空间而暂时销毁该 `Activity` 实例时，可能会调用它。 您可以通过 `isFinishing() `方法区分这两种情形。

## 三种说法需要了解
1. **整个生命周期**
`Activity` 的**整个生命周期**发生在 `onCreate()` 调用与 `onDestroy()` 调用之间。您的 `Activity` 应在 `onCreate()` 中执行“全局”状态设置（例如定义布局），并释放 `onDestroy()` 中的所有其余资源。例如，如果您的 `Activity` 有一个在后台运行的线程，用于从网络上下载数据，它可能会在 `onCreate()` 中创建该线程，然后在 `onDestroy()` 中停止该线程。
2. **可见生命周期**
`Activity` 的**可见生命周期**发生在 `onStart()` 调用与 `onStop()` 调用之间。在这段时间，用户可以在屏幕上看到 `Activity` 并与其交互。 例如，当一个新 `Activity` 启动，并且此 `Activity` 不再可见时，系统会调用 `onStop()`。您可以在调用这两个方法之间保留向用户显示 `Activity` 所需的资源。 例如，您可以在 `onStart()` 中注册一个 BroadcastReceiver 以监控影响 UI 的变化，并在用户无法再看到您显示的内容时在 `onStop()` 中将其取消注册。在 `Activity` 的整个生命周期，当 `Activity` 在对用户可见和隐藏两种状态中交替变化时，系统可能会多次调用 `onStart()` 和 `onStop()`。
3. **前台生命周期**
`Activity` 的**前台生命周期**发生在 `onResume() `调用与 `onPause()` 调用之间。在这段时间，`Activity` 位于屏幕上的所有其他 `Activity` 之前，并具有用户输入焦点。 `Activity` 可频繁转入和转出前台 — 例如，当设备转入休眠状态或出现对话框时，系统会调用 `onPause()`。 由于此状态可能经常发生转变，因此这两个方法中应采用适度轻量级的代码，以避免因转变速度慢而让用户等待。

![生命周期图示](/images/201908/activity_lifecycle.png)


## 协调 Activity
当` Activity A` 启动` Activity B `时一系列操作的发生顺序：
`Activity A `的 `onPause()` 方法执行。
`Activity B` 的 `onCreate()`、`onStart() `和 `onResume() `方法依次执行。（`Activity B` 现在具有用户焦点。）
然后，如果` Activity A` 在屏幕上不再可见，则其 `onStop()` 方法执行。
您可以利用这种可预测的生命周期回调顺序管理从一个 `Activity` 到另一个` Activity` 的信息转变。 例如，如果您必须在第一个 `Activity` 停止时向数据库写入数据，以便下一个` Activity` 能够读取该数据，则应在` onPause()` 而不是 `onStop()` 执行期间向数据库写入数据。


## 需要注意的地方
如果 `Activity` 转入对用户不可见状态，则后接 `onStop()`。
即`onStop()`在 `Activity `对用户不再可见时调用。
也就是没有完全遮盖的时候，第一个`Activity`是不会调用 `onStop()`