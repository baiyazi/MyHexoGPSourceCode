---
title: android | ListView的点击事件
date: 2019-8-14 16:11:28
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - Android
    - Eclipse
---

>看了ListView的简单生成，在实际中点击是很常见的，这里就介绍ListView的点击事件


还是上一个案例，我们在里面添加代码：
首先，为`item.java`的对象添加`toString`方法，因为后面我们需要用到该类的`toString`方法，就自动生成吧，`Source->Generate toString()...`
然后选中定义的属性，`ok`。(`item.java`添加如下)
``` java
@Override
	public String toString() {
		return "item [title=" + title + ", subTitle=" + subTitle + ", date="
				+ date + ", imageId=" + imageId + "]";
	}
```


然后在MainActivity.java中为listView注册列表点击的事件，注册在关联前即可：
代码如下：
``` java
// 点击关联
// 设置listview点击事件
listView.setOnItemClickListener(new OnItemClickListener() {
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i,
long l) {
		item item = datalist.get(i);
		Toast.makeText(MainActivity.this, item.toString(), 20).show();
	}
});
// 第四部：关联
listView.setAdapter(adapter);
```

看看效果图：
![](/images/201908/listview_click_1.gif)
