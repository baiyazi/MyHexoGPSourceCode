---
title: Android插件化开发指南 | 2.15 实现一个音乐播放器APP
date: 2021年11月9日 16:19:53
comments: true
categories: "Android插件化开发"
tags: 
    - Android
---

欢迎访问：[Android插件化开发指南——实践之ViewPager+Fragment优化（预加载和懒加载）](https://blog.csdn.net/qq_26460841/article/details/121220878)

@[toc]
# 1. 前言
`ViewPager`+`Fragment`的组合比较适合用来做页面的导航，这里因为在`Android`插件化开发指南——实践之仿酷狗音乐首页一文的实践中需要用来这块的知识。为了`app`加载更加流畅，这里考虑使用预加载和懒加载两种机制。当然，这里对于`ViewPager`+`Fragment`的简单实现，这里记录下：
首先定义好`ViewPager`控件：

```xml
<androidx.viewpager.widget.ViewPager
    android:id="@+id/fx_viewpager"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"/>
```
然后定义好需要显示的三个子项的布局文件：
![在这里插入图片描述](https://img-blog.csdnimg.cn/c7e01232841049fdbb7ed5443d818e28.png#pic_center =250x)
然后定义一个`ViewPager`控件的适配器：

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
然后只需要在`Fragment`中完成初始化`item`到`View`实例，并设置`adapter`，最后设置`OnPageChangeListener`监听即可。这里我的效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/fe2e0c90c7b34d17a953bf4010202d19.gif#pic_center =250x)

# 2. ViewPager+Fragment优化
预加载和懒加载。`ViewPager`控件有个特有的预加载机制，即默认情况下当前页面左右两侧的`1`个页面会被加载，以方便用户滑动切换到相邻的界面时，可以更加顺畅的显示出来。这样就会导致本来加载一个页面，其实在背后会预先加载三个页面，也就是会导致内存消耗比较严重。如果页面的数据也很大的时候，可能存在极端的情况，即将内存撑爆，也就是`OOM`问题。

所以在内存消耗比较低的场景中，可以使用预加载技术来提高响应时间，进而带来比较丝滑的滑动效果。在内存消耗比较高的场景中，对应的需要使用懒加载技术，来延迟资源的加载。懒加载对服务器端和客户端内存有一定的缓解压力作用，预加载则会增加服务器和和客户端压力。

## 2.1 预加载
在`ViewPager`中，可以通过`setOffscreenPageLimit(int limit)`来设置预加载页面数量，当前页面相邻的`limit`个页面会被预加载进内存。不妨来看看源码：

```clike
// ViewPager.java
private static final int DEFAULT_OFFSCREEN_PAGES = 1;
private int mOffscreenPageLimit = DEFAULT_OFFSCREEN_PAGES;

public void setOffscreenPageLimit(int limit) {
    if (limit < DEFAULT_OFFSCREEN_PAGES) { // 如果小于1
        limit = DEFAULT_OFFSCREEN_PAGES; // 直接设置为1
    }
    if (limit != mOffscreenPageLimit) { // 如果大于1
        mOffscreenPageLimit = limit; // 设置预加载页面数量为设置的limi
        populate(); // 填充
    }
}
```
从上面的代码中可以看出，预加载无法按照预想的，将`limit`设置为`0`来取消预加载。所以我们需要考虑其余的方式来实现取消`ViewPager`+`Fragment`的预加载。在博客 [ViewPager+Fragment取消预加载（延迟加载）](https://blog.csdn.net/myatlantis/article/details/42643733)一文中给出了一个解决的思路。即：

通过判断`Fragment`对用于的可见性来实现，也就是在这个`Fragment`对用户可见了再进行数据的加载。而再`Fragment`中提供了两个方法，分别是：

```clike
boolean getUserVisibleHint() // 获得Fragment可见状态
void setUserVisibleHint(boolean isVisibleToUser) // 设置Fragment可见状态
```
我们只需要复写这两个方法即可，就可以判断当前的`Fragment`是否可见，进而判断是否进行数据的加载。其实根据上面的分析，这里我们知道根本上这个`Fragment`还是会加载，只是我们将那些实际请求数据的操作放置在了之后，其实也就是懒加载。

也即是说，取消`Fragment`预加载的解决为使用懒加载。因为预加载从前面代码中我们知道，解决不了，且默认设置为`1`，也就是会预加载左右两个页面。

## 2.2 懒加载
比如定义如下一个懒加载的`Fragment`父类：

```clike
public abstract class LazyFragment extends Fragment {

    private View rootView; // 表示当前View实例对象
    private boolean isViewCreated = false; // rootView是否创建
    private boolean isDatasLoaded = false; // 是否加载过
    private boolean isCurrentVisible = false; // 是否可见

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(getLayoutResources(), container, false);
        }
        isViewCreated = true; // rootView创建完毕
        if(getUserVisibleHint()) setUserVisibleHint(true);
        return rootView;
    }

    protected View getRootView(){
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isViewCreated){
            Log.e("TAG", "isCurrentVisible: " + isCurrentVisible + " | isVisibleToUser: " + isVisibleToUser );
            if(!isCurrentVisible && isVisibleToUser){
                // 加载数据
                if(!isDatasLoaded) lazyLoadData();
                isDatasLoaded = true;
                isCurrentVisible = isVisibleToUser;
            }else if(isCurrentVisible && !isVisibleToUser){
                // 停止加载数据
                stopLoadData();
                isCurrentVisible = isVisibleToUser;
            }
        }
    }

    /**
     * 对子类提供一个查找元素的方法
     */
    protected <T extends View> T findViewById(int id) {
        if(isViewCreated) return (T) rootView.findViewById(id);
        return null;
    }

    /**
     * 由具体子类来实现这个方法，以实现返回当前页面的布局文件ID
     */
    protected abstract int getLayoutResources();

    /**
     * 提供两个方法，用来进行加载数据，或者停止加载数据
     */
    protected abstract void lazyLoadData();
    protected void stopLoadData(){}
}
```
然后将原本传入`ViewPager`的直接通过`LayoutInflater`实例化的`View`对象换成`Fragment`对象。同时，将适配器修改为继承自`FragmentPagerAdapter`的类：

```clike
public class PageViewPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    // 外部传入的ViewPager对应的Item对象
    private List<T> mList;

    public PageViewPagerAdapter(@NonNull FragmentManager fm, List<T> mList) {
        super(fm);
        this.mList = mList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }
}
```

然后再创建`ViewPager`关联的三个页面的`Fragment`的时候，就需要继承自前面所定义的`LazyFragment`，比如在下面的示例中，我使用`Handler`发送一个延迟消息，来模拟数据的耗时加载：

```clike
public class FxPageMusicFragment extends LazyFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = getRootView();
        initViews();
        return rootView;
    }

    private void initViews() {
        // todo 初始化一些数据
    }

    @Override
    protected int getLayoutResources() {
        // 设置布局文件-音乐
        return R.layout.fx_viewpager_item_yy;
    }

    @Override
    protected void lazyLoadData() {
        Log.e("TAG", "lazyLoadData: 音乐加载数据");
        // todo 加载数据模拟
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0){
                    Button button = findViewById(R.id.yy_page_loading_button);
                    button.setText("数据加载完毕。");
                }
            }
        };
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessageDelayed(msg, 1000);
    }

    @Override
    protected void stopLoadData() {
        super.stopLoadData();
        Log.e("TAG", "lazyLoadData: 音乐停止加载数据");
    }
}
```
最后的效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/e16b5b7066674783a6b00c11865a1edd.gif#pic_center =250x)
当然，对于`ViewPager`+`Fragment`优化的懒加载处理这块，我看的`bilibili`的视频：[懒加载方案源码解析之一](https://www.bilibili.com/video/BV1oa4y1J7qC?p=5)。

# 3. 后记
对于`ViewPager`+`Fragment`优化的懒加载处理，主要参考了上面的那个视频。然后简单修改了一部分。从观看视频到依葫芦画瓢的这个过程，确实也说明了自己知识储备确实不够。还需要多加练习。
___
**References**
- [Android懒加载vs预加载------Viewpager+Fragment](https://www.jianshu.com/p/2eb190614870)
- [ViewPager+Fragment组合的预加载和懒加载](https://www.jianshu.com/p/7a47907f49c2)
- [ViewPager+Fragment取消预加载（延迟加载）](https://blog.csdn.net/myatlantis/article/details/42643733)
- [【视频】懒加载方案源码解析之一](https://www.bilibili.com/video/BV1oa4y1J7qC?p=5)



