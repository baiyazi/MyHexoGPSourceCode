---
title: 微信小程序开发 | wx:for
date: 2019-5-14 15:04:13
comments: true
categories: "微信小程序"
tags: 
    - wx_for
    - bindtap
    - this_setData
---

# bindtap
组件事件处理函数
事件是**视图层到逻辑层的通讯方式。**

bindtap，当用户点击该组件的时候会在该页面对应的Page中找到相应的事件处理函数。
``` html
<view id="tapTest" data-hi="WeChat" bindtap="tapName">Click me!</view>
```
在相应的Page定义中写上相应的事件处理函数，参数是event。
``` js
Page({
  tapName(event) {
    console.log(event)
  }
})
```
更多事件可以访问：https://developers.weixin.qq.com/miniprogram/dev/framework/view/wxml/event.html

# wx:for
默认数组的当前项的下标变量名默认为 index，数组当前项的变量名默认为 item
如，在data存入下面的数据：
``` js
array: [{
message: 'foo',
}
```
然后，在wxml中使用该数组：
``` html
<view wx:for="{{array}}">
  {{index}}: {{item.message}}
</view>
```
使用 wx:for-item 可以指定数组当前元素的变量名，
使用 wx:for-index 可以指定数组当前下标的变量名：
``` html
<view wx:for="{{array}}" wx:for-index="idx" wx:for-item="itemName">
  {{idx}}: {{itemName.message}}
</view>
```

wx:for 也可以嵌套，下边是一个九九乘法表
``` html
<view wx:for="{{[1, 2, 3, 4, 5, 6, 7, 8, 9]}}" wx:for-item="i">
  <view wx:for="{{[1, 2, 3, 4, 5, 6, 7, 8, 9]}}" wx:for-item="j">
    <view wx:if="{{i <= j}}">
      {{i}} * {{j}} = {{i * j}}
    </view>
  </view>
</view>
```
# this.setData
Page.prototype.setData(Object data, Function callback)
setData 函数用于将数据从逻辑层发送到视图层（异步），同时改变对应的 this.data 的值（同步）。
参数说明
字段	            类型	      必填	          描 述	
data	       Object 	    是	          这次要改变的数据	
callback	 Function	否	          setData引起的界面更新渲染完毕后的回调函数

Object 以 key: value 的形式表示，将 this.data 中的 key 对应的值改变成 value。
其中 key 可以以数据路径的形式给出，支持改变数组中的某一项或对象的某个属性，如 array[2].message，a.b.c.d，并且**不需要在 this.data 中预先定义。**

<span class="title2">注意：</span>
**直接修改 this.data 而不调用 this.setData 是无法改变页面的状态的，还会造成数据不一致。**
仅支持设置可 JSON 化的数据。
单次设置的数据不能超过1024kB，请尽量避免一次设置过多的数据。
请不要把 data 中任何一项的 value 设为 undefined ，否则这一项将不被设置并可能遗留一些潜在问题。

不要直接修改 this.data，以达到期望的修改：
this.data.text = 'changed data'  

应该使用 setData
``` js
this.setData({
text: 'changed data'
})
```
或者，可以修改 this.data 之后马上用 setData 设置一下修改了的字段
``` js
this.data.num = 1
this.setData({
num: this.data.num
})
```
对于对象或数组字段，可以直接修改一个其下的子字段，这样做通常比修改整个对象或数组更好
``` js
this.setData({
'array[0].text': 'changed data'
})
```

甚至都不需要再this.data中申明，直接设置数据，然后就可以使用
``` html
<view>{{object.text}}</view>
<button bindtap="changeItemInObject">Change Object data</button>
```

``` js
changeItemInObject() {
    this.setData({
      'text': 'changed data'
    })
  }
```

