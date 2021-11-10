---
title: Android插件化开发指南 | 仿酷狗音乐首页
date: 2021年10月31日 22:22:34
comments: true
categories: "Android插件化开发"
tags: 
    - Android
---

欢迎访问：[Android插件化开发指南——实践之仿酷狗音乐首页](https://blog.csdn.net/qq_26460841/article/details/121069213)


# 1. 前言
在[Android插件化开发指南——2.15 实现一个音乐播放器APP](https://blog.csdn.net/qq_26460841/article/details/120992273)中介绍了音乐播放的基本知识，以及在最后提到了想仿一个音乐播放器，所以在接下来的日子里将继续仿造。上篇中介绍了[仿酷狗音乐启动页——Activity转场效果](https://blog.csdn.net/qq_26460841/article/details/121061646)，按照逻辑将进入主页部分，所以这篇将简单实现首页部分逻辑。首先先来张截图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/b0b0c77f2dfa4aad81b8f0b62e67e157.jpg#pic_center =250x)
# 2. 布局分析
映入眼帘的是底部的导航栏部分，所以这里我使用`Fragment`来进行导航的实现。顶部是三选项栏关联了三个不同的布局页面，且可以侧滑切换，所以这里我将使用`ViewPager`来实现。那么这篇博客就实现这两个部分的框架即可。

# 3. 底部导航栏的实现
首先看下`MainActivity`的布局文件，也就是一个`FrameLayout`加底部的导航布局文件，如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <FrameLayout
        android:id="@+id/fx_framelayout"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="@color/gray"
        />

    <include layout="@layout/bottom_nav"/>

</LinearLayout>
```
对于引入的`bottom_nav.xml`文件，就是一个简单的`LinearLayout`包装的导航栏，效果如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/590882373d7f478c9351245cd152d7ae.png#pic_center)
对应的布局文件这里就不再给出，感兴趣的可以查看这篇文章底部的源码部分。因为在`FrameLayout`容器中每次切换为当前逻辑的`Fragment`页面，故而这里的主要逻辑在于`MainActivity`的逻辑实现上。当然，还是给出一个简单的示例，首先是发现页面的布局文件`fragment_fx.xml`：

```xml
// fragment_fx.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/fx"
        android:textColor="@color/black"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
然后是对应的发现页面的`Fragment`类文件`FxPageFragment.java`：

```java
public class FxPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_fx, container, false);
        return inflate;
    }
}
```
下面就是主要的部分，也就是在`MainActivity`中完成点击切换部分的逻辑。具体也就是首先用`currentBottomNavIndex`来记录当前显示的`Fragment`对应的底部导航栏的下标。为了统一，这里将所有的`Fragment`组成一个数组，将底部导航栏中的图像等组成一个数组：

```java
fragments = new Fragment[]{fxPageFragment, zbPageFragment, null, kgPageFragment, wdPageFragment};

bottomNavImgsRescourseDefault = new int[]{R.drawable.fx_not_choosed,
                R.drawable.zb_not_choosed, -1, R.drawable.kg_not_choosed, R.drawable.wd_not_choosed};
bottomNavImgsRescourseChoosed = new int[]{R.drawable.fx_choosed,
                R.drawable.zb_choosed, -1, R.drawable.kg_choosed, R.drawable.wd_choosed};
```

然后定义底部导航栏整体的样式清除的函数：

```java
private void clearAllBottomNavStyle() {
    for (int i = 0; i < bottomNavTxts.length; i++) {
        if(bottomNavTxts[i] != null && bottomNavImgsRescourseDefault[i] != -1){
            bottomNavTxts[i].setTextColor(getColor(R.color.item_not_choosed));
            bottomNavImgs[i].setImageResource(bottomNavImgsRescourseDefault[i]);
        }
    }
}
```
定义设置当前的底部导航栏的样式的函数：

```java
private void setBottomNavStyleByIndex(int index) {
    if(bottomNavTxts[index] != null && bottomNavImgsRescourseDefault[index] != -1) {
        bottomNavTxts[index].setTextColor(getColor(R.color.full_screen));
        bottomNavImgs[index].setImageResource(bottomNavImgsRescourseChoosed[index]);
    }
}
```
以及设置当前与之关联的`Fragment`的函数：

```java
private void switchToFragmentByIndex(int clickedIndex) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    if(clickedIndex != currentBottomNavIndex){
        transaction.hide(fragments[currentBottomNavIndex]);
        //如果没有，就添加Fragment
        if(fragments[clickedIndex].isAdded()){
            transaction.add(R.id.fx_framelayout, fragments[clickedIndex]);
        }
        transaction.show(fragments[clickedIndex]);
        transaction.commit();
    }
    currentBottomNavIndex = clickedIndex;
}
```

当然，在一开始进入这个`MainActivity`的时候，我们应该设置显示第一个`Fragment`，隐藏其余的`Fragment`，即：

```java
fragmentManager.beginTransaction()
        .add(R.id.fx_framelayout, fxPageFragment, "fxPageFragment")
        .add(R.id.fx_framelayout, kgPageFragment, "kgPageFragment")
        .add(R.id.fx_framelayout, wdPageFragment, "wdPageFragment")
        .add(R.id.fx_framelayout, zbPageFragment, "zbPageFragment")
        .hide(kgPageFragment)
        .hide(wdPageFragment)
        .hide(zbPageFragment)
        .show(fxPageFragment)
        .commit();
```
最终效果：

![在这里插入图片描述](https://img-blog.csdnimg.cn/36d8ee0295cf4774adcfb678e16c7581.gif#pic_center =250x)

当然，还有具体页面的顶部`ViewPager`以及对应的切换还没有实现。准备明天或者之后，空了继续这个博客。
___

2021年11月8日 19:45:05继续：
采用和前面类似的操作，定义顶部发现页面的几个文本和按钮，这里命名为`fx_top_nav.xml`，最终的效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/ca40c6b3514d4dabacd83cbd8eb851bc.png#pic_center =400x)
然后，这里需要为发现页面配置`ViewPager`页面。也就是在发现页所在的`fragment_fx.xml`文件中定义一个`ViewPager`，比如我们这里的`fragment_fx.xml`定义为：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >
    <include layout="@layout/fx_top_nav"/>

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/fx_viewpager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>

</LinearLayout>
```
进一步，需要为这个`ViewPager`配置三个项。首先定义好对应的适配器：

```clike
public class PageViewPagerAdapter<T extends View> extends PagerAdapter {

    // 外部传入的ViewPager对应的Item对象
    private List<T> mList;

    public PageViewPagerAdapter(List<T> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mList.get(position));
    }
}
```

因为我们需要传入这三个页面对应的`View`对象到适配器中，所以这里我们需要先定义三个页面的布局文件，比如这里我分别命名为`fx_viewpager_item_sp.xml`、`fx_viewpager_item_yy.xml`、`fx_viewpager_item_ts.xml`，即对应视频、音乐和听书三个`Item`项。

然后在发现页的的`Java`文件中，进行适配器的关联，这里也就是在`FxPageFragment.java`文件中：

```clike
/**
 * 发现页面的Fragment
 */
public class FxPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_fx, container, false);
        // 设置ViewPager数据项
        ViewPager viewPager = inflate.findViewById(R.id.fx_viewpager);
        List<View> views = InitViewPagerData.initFxPageViewPagerData(inflate.getContext());
        PageViewPagerAdapter<View> adapter = new PageViewPagerAdapter<>(views);
        viewPager.setAdapter(adapter);
        return inflate;
    }
}
```
至于初始化数据的方法这里就不再给出，所完成的事情也就是使用`LayoutInflate`来完成对上面定义的视频、音乐和听书三个页面布局文件的实例化，进而得到代表该项的`View`对象。目前效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/53a55b46540546428ca258928b38cb64.png#pic_center =250x)
进行滑动，可以进行`ViewPager`页面的切换。但是这里并没有和顶部的对应逻辑项关联，所以接下来的工作就是关联这两个部分的内容。

# 4. 顶部导航栏和ViewPager+Fragment的关联
对于顶部导航栏和对应的ViewPager的关联，主要是使用两个部分各自的监听函数。比如在ViewPager中进行设置：

```clike
viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if(curTopNavIndex != position){
            lastTopNavIndex = curTopNavIndex;
            curTopNavIndex = position;
        }
        setTopNavTextViewFontSize(curTopNavIndex, 24);
        setTopNavTextViewBottomLine(curTopNavIndex, true);
        setTopNavTextViewFontSize(lastTopNavIndex, 18);
        setTopNavTextViewBottomLine(lastTopNavIndex, false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
});
```
用来设置顶部导航的三个`TextView`的样式。同样的，为三个顶部导航的`TextView`设置监听来切换对应的`ViewPager`：

```clike
private void setTopNavTextViewElementsOnClickListener() {
    t_yy.setOnClickListener(this);
    t_sp.setOnClickListener(this);
    t_ts.setOnClickListener(this);
}

@Override
public void onClick(View v) {
    switch (v.getId()){
        case R.id.fx_textview_yy:
            viewPager.setCurrentItem(0);
            break;
        case R.id.fx_textview_sp:
            viewPager.setCurrentItem(1);
            break;
        case R.id.fx_textview_ts:
            viewPager.setCurrentItem(2);
            break;
    }
}
```

在实现了关联之后，因为我们这里使用的结构为`ViewPager`+`Fragment`来实现，所以考虑使用`Fragment`的懒加载技术。在[Android插件化开发指南——实践之ViewPager+Fragment优化（预加载和懒加载）](https://blog.csdn.net/qq_26460841/article/details/121220878)一文中，基本完成了对这部分的介绍。同样这里给出最后的效果图：

![](https://img-blog.csdnimg.cn/e16b5b7066674783a6b00c11865a1edd.gif#pic_center =300x)
这部分代码我上传到了`Github`中，[代码地址](https://github.com/baiyazi/MusicApp/tree/main/mymusicdemo-02)。











___
**References**
- [Fragment使用为什么要开启事务？Fragment怎么进行查找？](https://blog.csdn.net/qq_26460841/article/details/120183947)
- [Android侧滑菜单DrawerLayout，NavigationView](https://blog.csdn.net/qq_26460841/article/details/119250832)





