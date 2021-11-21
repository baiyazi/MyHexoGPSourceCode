---
title: android | Android六大基本布局
date: 2019-8-10 10:46:10
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

原文地址：[地址](https://www.cnblogs.com/web424/p/6961764.html)
![](/images/201908/1152574-20170608110040887-334790846.png)
分别是：线性布局`LinearLayout`、表格布局`TableLayout`、相对布局`RelativeLayout`、
层布局`FrameLayout`、绝对布局`AbsoluteLayout`、网格布局`GridLayout`。
# LinearLayout
所有包含在线性布局里的控件在线性方向上依次排列。
## orientation
`android:orientation`控制方向，属性值垂直（`vertical`）和水平(`horizontal`)，默认水平方向。
## gravity
`android:gravity`：内部控件对齐方式，常用属性值有`center`、`center_vertical`、`center_horizontal`、`top`、`bottom`、`left`、`right`等。
## layout_gravity
这里要与`android:layout_gravity`区分开，`layout_gravity`是用来设置自身相对于父元素的布局。
## layout_weight
`android:layout_weight`：权重，用来分配当前控件在剩余空间的大小。
使用权重一般要把分配该权重方向的长度设置为零，比如在水平方向分配权重，就把`width`设置为零。
## 案例
按照上面博客地址的案例，分析了一下，然后用不用的排列方式构造了一下，效果图如下：
![](/images/201908/2019-08-10_145710.png)

我这里采用的是垂直方向的主体构造；比较简单的方式是先构造主体，再填充内容。
``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >
    <RelativeLayout 
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="2"
        android:background="#FCFCFC"
        >
        <TextView 
            android:layout_width="wrap_content"
        	android:layout_height="wrap_content"
        	android:text="0"
        	android:padding="20dp"
        	android:textSize="30sp"
        	android:layout_alignParentBottom="true"
            android:layout_alignParentRight="true"
            />
    </RelativeLayout>
	<LinearLayout 
	    android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="4"
        android:orientation="vertical"
        android:background="#FCFCFC"
	    >
	    <LinearLayout 
		    android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"
	        android:orientation="horizontal"
	        android:background="#F5F5F5"
		    >
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="MC" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="M+" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="M-" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="MR" />
		</LinearLayout>
		<LinearLayout 
		    android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"
	        android:orientation="horizontal"
		    >
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="C" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="÷" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="×" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="←" />
		</LinearLayout>
		<LinearLayout 
		    android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"
	        android:orientation="horizontal"
		    >
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="7" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="8" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="9" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="－" />
		</LinearLayout>
		<LinearLayout 
		    android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"
	        android:orientation="horizontal"
		    >
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="4" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="5" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="6" />
		  	<Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#F5F5F5"
	             android:text="＋" />
		</LinearLayout>
	</LinearLayout>
	<LinearLayout 
	    android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="2"
        android:orientation="horizontal"
	    >
	    <LinearLayout 
		    android:layout_width="0dp"
	        android:layout_height="match_parent"
	        android:layout_weight="3"
	        android:orientation="vertical"
		    >
		    <LinearLayout 
			    android:layout_width="match_parent"
		        android:layout_height="0dp"
		        android:layout_weight="3"
		        android:orientation="horizontal"
			    >
			    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="1" />
			    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="2" />
			    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="3" />
			</LinearLayout>
			<LinearLayout 
			    android:layout_width="match_parent"
		        android:layout_height="0dp"
		        android:layout_weight="3"
		        android:orientation="horizontal"
			    >
			    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="%" />
			    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="0" />
			    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#FCFCFC"
	             android:text="." />
			</LinearLayout>
		</LinearLayout>
		<LinearLayout 
		    android:layout_width="0dp"
	        android:layout_height="match_parent"
	        android:layout_weight="1"
	        android:orientation="horizontal"
		    >
		    <Button
	             android:layout_width="0dp"
	             android:layout_height="match_parent"
	             android:layout_weight="1"
	             android:background="#00B5E2"
	            android:textColor="#fff"
	             android:text="＝" />
		</LinearLayout>
	</LinearLayout>
</LinearLayout>
```


# TableLayout
表格布局，适用于多行多列的布局格式，每个`TableLayout`是由多个`TableRow`组成，一个`TableRow`就表示`TableLayout`中的每一行，这一行可以由多个子元素组成。
`TableRow`实际是一个横向的线性布局
`TableLayout`常用属性：
`android:shrinkColumns`：设置可收缩的列，内容过多就收缩显示到第二行
`android:stretchColumns`：设置可伸展的列，将空白区域填充满整个列
`android:collapseColumns`：设置要隐藏的列
列的索引从0开始，`shrinkColumns`和`stretchColumns`可以同时设置。
子控件常用属性：
`android:layout_column`：第几列
`android:layout_span`：占据列数。
`android:gravity`：内部控件对齐方式，常用属性值有`center`、`center_vertical`、`center_horizontal`、`top`、`bottom`、`left`、`right`等。
# RelativeLayout
相对布局可以让子控件相对于兄弟控件或父控件进行布局，可以设置子控件相对于兄弟控件或父控件进行上下左右对齐。
## RelativeLayout中子控件常用属性：
### 1. 相对于父控件
**例如：android:layout_alignParentTop=“true”**
`android:layout_alignParentTop     ` 控件的顶部与父控件的顶部对齐;
`android:layout_alignParentBottom ` 控件的底部与父控件的底部对齐;
`android:layout_alignParentLeft  `    控件的左部与父控件的左部对齐;
`android:layout_alignParentRight`     控件的右部与父控件的右部对齐;
### 2. 相对给定Id控件
**例如：android:layout_above=“@id/*”**
`android:layout_above `控件的底部置于给定ID的控件之上;
`android:layout_below     `控件的底部置于给定ID的控件之下;
`android:layout_toLeftOf    `控件的右边缘与给定ID的控件左边缘对齐;
`android:layout_toRightOf  `控件的左边缘与给定ID的控件右边缘对齐;
`android:layout_alignBaseline`  控件的baseline与给定ID的baseline对齐;
`android:layout_alignTop    `    控件的顶部边缘与给定ID的顶部边缘对齐;
`android:layout_alignBottom`   控件的底部边缘与给定ID的底部边缘对齐;
`android:layout_alignLeft `      控件的左边缘与给定ID的左边缘对齐;
`android:layout_alignRight`      控件的右边缘与给定ID的右边缘对齐;

### 3. 居中
**例如：android:layout_centerInParent=“true”**
`android:layout_centerHorizontal` 水平居中;
`android:layout_centerVertical`    垂直居中;
`android:layout_centerInParent`  父控件的中央;
## gravity
`android:gravity`：内部控件对齐方式，常用属性值有`center`、`center_vertical`、`center_horizontal`、`top`、`bottom`、`left`、`right`等。

# FrameLayout
帧布局或叫层布局，从屏幕左上角按照层次堆叠方式布局，后面的控件覆盖前面的控件。
该布局在开发中设计地图经常用到，因为是按层次方式布局，我们需要实现层面显示的样式时就可以
采用这种布局方式，比如我们要实现一个类似百度地图的布局，我们移动的标志是在一个图层的上面。
在普通功能的软件设计中用得也不多。层布局主要应用就是地图方面。

# AbsoluteLayout
绝对布局中将所有的子元素通过设置`android:layout_x` 和 `android:layout_y`属性，将子元素的坐标位置固定下来，即坐标(`android:layout_x`, `android:layout_y`) ，`layout_x`用来表示横坐标，`layout_y`用来表示纵坐标。屏幕左上角为坐标`(0,0)`，横向往右为正方，纵向往下为正方。实际应用中，这种布局用的比较少，因为`Android`终端一般机型比较多，各自的屏幕大小。分辨率等可能都不一样，如果用绝对布局，可能导致在有的终端上显示不全等。


# GridLayout

