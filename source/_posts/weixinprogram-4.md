---
title: 微信小程序开发 | 分装组件【窗口对话框】
date: 2019-7-7 10:07:11
comments: true
categories: "微信小程序"
tags: 
    - 小程序代码构成
---
 前端，很多时候都需要一些组件，然而实际没有直接提供，我们就需要自己封转。

官方文档：https://developers.weixin.qq.com/miniprogram/dev/framework/custom-component/ 
这里先看一下分装一个组件的目录结构：
![e](/images/201907/2019-07-07_100937.png)
不难看出目录的结构和实际的每个页面都是一样的。实际上也是差不多的。
首先是dialog.json文件：
``` json
{
  "component": true,  /*设置模式是组件*/
  "usingComponents": {}  /*这里还可以引用别的组件*/
}
```

然后我们看看dialog.js文件，但是首先我们需要看一下用开发工具建立的空白的dialog.js文件
``` js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {

  }
})

```

然后，贴下dialog.js编写后的码：
``` js
Component({
  options: {
    multipleSlots: true // 在组件定义时的选项中启用多slot支持
  },
  /**
   * 组件的属性列表
   */
  properties: {
  // 弹窗提示文字
    pop_info: {            // 属性名
      type: String,     // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
      value: '内容'     // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
    // 弹窗确认按钮文字
    pop_confirm: {
      type: String,
      value: '确定'
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    flag: true,   //也就是设置整个窗口是否显示的标志，类似于HTML中的display:none/block;
  },

  /**
   * 组件的方法列表
   */
  methods: {
    //展示弹框
    showpop: function () {
      console.log("showpop")
      this.setData({
        flag: !this.data.flag
      })
    },
    //隐藏弹框
    hidepop: function () {
      console.log("hidepop")
      this.setData({
        flag: !this.data.flag
      })
    },
    /*
    * triggerEvent 用于触发事件
    */
    confirm: function () {
      //操作
      console.log("confirm")
      this.hidepop();  //隐藏窗口
      //触发取消回调
      this.triggerEvent("error")
    }
  }
})
```

popwindow的显示和隐藏问题也就是基于上面数据data中的标志，来设置窗口是否显示。
其实也不难理解，可以看做HTML中的某个div采用的是绝对布局，大小刚好是浏览器的页面的宽度和高度，在满足某种触发条件的时候，触发，设置display为block，然后在满足某种条件的情况下display设置为none。
也即是，其实这是的代码一直都在页面中。

接着，看看对应的dialog.wxml文件， 不同的是使用的不是display，而是hidden：
``` html 
<view class="fullwindow" hidden="{{flag}}">
  <view class='popwindow'>
    <view class="content">
      <text>{{pop_info}}</text>
    </view>
    <view class="confirm">
      <text catchtap='confirm'>{{pop_confirm}}</text>
    </view>
  </view>
</view>
```

接着就是样式文件，设置绝对布局，然后设置页面大小，封装背景为黑的半透明，在其上加入popwindow：
``` css
/* component/popup.wxss */
.fullwindow {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, .5);
}
.popwindow {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 90%;
  max-width: 600rpx;
  border-radius: 20rpx;
  box-sizing: bordre-box;
  transform: translate(-50%, -50%); 
  overflow: hidden;
  background: #fff;
  border: 1px solid #888;
}

.content {
  padding: 40rpx;
  color: #323233;
  font-size: 0.8rem;
}
.content text{
  line-height: 60rpx;
  text-align: center;
  display: block
}
.confirm text {
  display: block;
  width: 100%;
  justify-content: center;
  height: 88rpx;
  line-height: 88rpx;
  text-align: center;
  color: #67B1FC;
  letter-spacing: 10rpx;
  border-top: 1rpx solid #F5F6F8;
}
```

来看看效果：
![e](/images/201907/2019-07-07_144147.png)