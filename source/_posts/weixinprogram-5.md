---
title: 微信小程序开发 | 分装组件【日历】
date: 2019-7-7 10:07:11
comments: true
categories: "微信小程序"
tags: 
    - 小程序代码构成
---

<i class="fa  fa-bookmark fa-lg"></i> 有了前面的分装对话框，接下来分装一个日历相比是要复杂一些，接着就来试试。

官方文档：https://developers.weixin.qq.com/miniprogram/dev/framework/custom-component/ 

首先我们需要解决两个问题：
1. 给定日期，如何计算是星期几？
参考文章地址：https://blog.csdn.net/qq_35455503/article/details/82256784 
基姆拉尔森计算公式
W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400+1) mod 7
在公式中d表示日期中的日数，m表示月份数，y表示年数。
注意：在公式中有个与其他公式不同的地方：
把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
2. 了解平年、闰年
参考文章地址：https://www.cnblogs.com/mq0036/p/3534096.html
首先，我们看看两者有什么不同：一年有365天，叫做平年；一年有366天，叫做闰年，把这一天加在2月里。 四年一闰，百年不闰，四百年再闰。
常用的计算方式：
①非世纪年能被4整除，且不能被100整除的是闰年。（如2004年是闰年，1901年不是闰年）
②世纪年能被400整除的是闰年。（如2000年少闰年，1900年不是闰年）
两个条件需要一起使用，才不会漏掉。
3. 怎样分装数据，以方便表示
常见的app的日历，都是日期六行，然后7列，故而我这里也就用一个数组来表示。
尝试了一下，我这里将数据按照日历中的42个格子，按位置封装元素。
每一个元素的格式是：[天， 是否是今天的标志， 是否是记录过的标志]
标志位置，1==True, 0==False
设置标志位置，主要是为了方便使用css样式
如：
``` javascript
[
[[30,0,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]],  //行 
[[7,0,0],[8,0,0],[9,0,0],[10,0,0],[11,0,0],[12,0,0],[13,0,0]],
[[14,0,1],[15,0,0],[16,0,1],[17,0,0],[18,0,1],[19,0,0],[20,0,0]],
[[21,0,0],[22,0,0],[23,0,1],[24,0,1],[25,0,0],[26,0,0],[27,0,0]],
[[28,0,0],[29,1,0],[30,0,0],[31,0,0],[1,0,0],[2,0,0],[3,0,0]],
[[4,0,0],[5,0,0],[6,0,0],[7,0,0],[8,0,0],[9,0,0],[10,0,0]]
]
```

这里先看看如何使用的css样式：
``` html 
 <view wx:for="{{daylist}}" wx:for-item="list" class='dayrow' wx:for-index="i">
    <block wx:for="{{list}}">
      <text wx:if="{{index + i * 7<=firstday || index + i * 7>=lastday}}" class="notcurentmonth">{{item[0]}}</text>
      <block wx:else>
        <!-- <text wx:elif="{{index + i * 7 == today }}" class="today">{{item[0]}}</text> -->
        <block wx:if="{{item[1]==1}}">
          <text wx:if="{{item[2]==1}}" class="todayandrecord">{{item[0]}}</text>
          <text wx:else class="today">{{item[0]}}</text>
        </block>
        <block wx:if="{{item[1]==0}}">
          <text wx:if="{{item[2]==1}}" class="record">{{item[0]}}</text>
          <text wx:if="{{item[2]==0}}">{{item[0]}}</text>
        </block>
      </block>

    </block>
  </view>
```


来看看效果：
![e](/images/201907/2019-07-08_121823.png)

----

有了上一节的模板，我们可以很快的实现。但是先不需要关注直接定义组件，因为不容易观察和调试。
我们可以先直接定义成一个页面，如我定义成showdate页面，在app.json中，将它设置成首页：
``` json
"pages": [
    "pages/showdate/showdate",
]
```

然后和写html代码一样，关注如何实现就可以了，下面分别给出对应的源码：
showdate.js
``` javascript
// miniprogram/pages/showdate/showdate.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    daylist:[],//当前月的天数，如30 30 1 2 3 ... 一共6*7=42个元素
    //需要注意的是，下面的下标是按照42天拉通排的下标，在wxml中使用index + i * 7来得到实际下标，然后计算的。
    firstday:0, // 每个月一号前的序号下标,即属于上一个月的天数的最后一天的下标
    lastday:0, // 属于下一个月第一天的小标
    today:0,  //今天的下标，以设置样式
    //得到当前日期
    current_day: 9,
    current_year: 2019,
    current_month: 7
    // currentmonth_record:[] //当前月份的包含三个记录的json数组，从数据库中取。数据格式：[y:'2019',m:'7',d:'8', daylist:[[[30,0,0],[1,0,0],[],[],[],[],[],[]],[],[],[],[],[],[]]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var day =  this.data.current_day;
    var year = this.data.current_year;
    var month = this.data.current_month;
    this.setData({
      current_day: day,
      current_year: year,
      current_month: month,
      daylist: this.datetimepick(year, month)
    });
    this.setcurrentday();
    console.log(this.data.daylist);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
   /**
   * 按照42个元素位置封装每一个位置的元素
   */
  datetimepick:function(y, m){
    var list=[];
    //首先拿到本月的天数
    var day = this.calculatedays(y, m);
    //然后计算本月第一天是星期几
    var first = this.calculateweek(y, m, 1);
    this.setData({
      firstday:first-1  //返回下标
    });
    //假如是2，即星期二，前面应该是退两次，得几退几次，退的是上月的天数
    var previousday = 0;
    //需要考虑是否涉及上一年
    //因为1月份中，前一部分是上一年的12月的
    if (m == 1) {
      previousday = this.calculatedays(y-1, 12);
    }else{
      previousday = this.calculatedays(y, m - 1);
    }
    for(var i=0;i<first;i++){
      // list.push(previousday);//数据分装的不好，应该是[day, isrecord, istoday]
      list.push([previousday, 0, 0]);  //0=False, 1=True
      previousday-=1;
    }
    //然后正常计算，在这里计算第几天的下标
    var curentday = 1;
    for (var i = 0; i < day; i++) {
      if (curentday == this.data.current_day){
        //计算今天的下标
        this.setData({
          today: list.length
        });
      }
      list.push([curentday, 0, 0]); // 同理，加入三元组
      curentday += 1;
    }
    //计算余下的，后面的一定是42减去元素的个数，然后从1开始累加，
    var length = list.length
    this.setData({
      lastday: length  //返回下标
    });
    var curentday = 1;
    for (var i = 0; i < 42 - length; i++) {
      // list.push(curentday);
      list.push([curentday, 0, 0]);
      curentday += 1;
    }

    //为了方便表示，可以将list数组切割
    var num = 7;  // 每页显示 10 条
    var index = 0;
    var newlist=[];
    for (var i = 0; i < 42; i++) {
      if (i % num === 0 && i !== 0) { // 可以被 10 整除
        newlist.push(list.slice(index, i));
        index = i;
      };
      if ((i + 1) === 42) {
        newlist.push(list.slice(index, (i + 1)));
      }
    };
    return newlist;
  },
  /**
   * 计算某年某月某天，是星期几
   */
  calculateweek:function(year, month, d){
    var d=d, m = month, y = year;
    if(month == 1){
      y = year - 1
      m = 13
    }
    if(month == 2){
      y = year - 1
      m = 14
    }
    //带入公式：W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400+1) mod 7
    var w = (d + 2 * m + parseInt(3 * (m + 1) / 5) + y + parseInt(y / 4) - parseInt(y / 100) + parseInt(y / 400) + 1) % 7;
    return w;
  },
  /***
   * 得出某年的某月是有多少天
   */
  calculatedays:function(year, month){
    var months = this.calculatemonth(year);
    //计算该月有几天
    return months[month - 1];
  },
  /**
   * 
   * 根据某年，得出本年所有的月份的天数
   */
  calculatemonth:function(year){
    var flag = false;
    if ((year / 4 == 0 && year / 100 != 0) || year / 400 == 0){
      flag = true;
    }
    if(flag){
      return [31,29,31,30,31,30,31,31,30,31,30,31];
    }else{
      return [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    }
  },
  previousmonth:function(){
    var day = 1;
    var year = this.data.current_year;
    var month = this.data.current_month - 1;
    if(month == 1){
      month = 12;
      year = year - 1;
    }
    this.setData({
      current_day: day,
      current_year: year,
      current_month: month
    });
    this.setData({
      daylist: this.datetimepick(year, month)
    });
    this.setcurrentday();
  },
  nextmonth:function(){
    var day = 1;
    var year = this.data.current_year;
    var month = this.data.current_month + 1;
    if (month == 12) {
      month = 1;
      year = year + 1;
    }
    this.setData({
      current_day: day,
      current_year: year,
      current_month: month
    });
    this.setData({
      daylist: this.datetimepick(year, month)
    });
    this.setcurrentday();
  },
  //测试函数：如果是当前月，将daylist item中的对应位置置1
  setcurrentday:function(){
    var index = this.data.today  //取今天的下标
    var tempdaylist = this.data.daylist
    //循环判断得到对应index位置的元素
    for(var i=0; i<6;i++){
      for(var j=0;j<7;j++){
        if ( j + i * 7 == index){
          //找到对应位置，设置当前的值为1[xx, 1, 0]  0是打卡记录
          tempdaylist[i][j][1] = 1;
        }
        // console.log((j + i * 7) % 5)
        if ((j + i * 7) % 5 == 3) {
          console.log(123)
          tempdaylist[i][j][2] = 1;
        }
      }
    }

    //设置回去
    this.setData({
      daylist: tempdaylist
    });
  }
})

```

showdate.wxml
``` html
<view class="container">
  <view class='selectmonth'>
    <text catchtap="previousmonth">＜</text>
    <text>{{current_year}}-{{current_month}}-{{current_day}}</text>
    <text catchtap="nextmonth">＞</text>
  </view>
  <view class="datetitle">
    <text>日</text>
    <text>一</text>
    <text>二</text>
    <text>三</text>
    <text>四</text>
    <text>五</text>
    <text>六</text>
  </view>

  <view wx:for="{{daylist}}" wx:for-item="list" class='dayrow' wx:for-index="i">
    <block wx:for="{{list}}">
      <text wx:if="{{index + i * 7<=firstday || index + i * 7>=lastday}}" class="notcurentmonth">{{item[0]}}</text>
      <block wx:else>
        <!-- <text wx:elif="{{index + i * 7 == today }}" class="today">{{item[0]}}</text> -->
        <block wx:if="{{item[1]==1}}">
          <text wx:if="{{item[2]==1}}" class="todayandrecord">{{item[0]}}</text>
          <text wx:else class="today">{{item[0]}}</text>
        </block>
        <block wx:if="{{item[1]==0}}">
          <text wx:if="{{item[2]==1}}" class="record">{{item[0]}}</text>
          <text wx:if="{{item[2]==0}}">{{item[0]}}</text>
        </block>
      </block>

    </block>
  </view>
</view>

```


showdate.wxss
``` css
.container{
  width: 95%;
  margin: 20rpx auto;
  border-radius: 30rpx;
  box-shadow: #eee 0 0 30rpx;
}
.selectmonth{
  margin-top: 20 rpx;
  color: #01DBCA;
  display: flex;
  width: 100%;
  align-items: center;
  text-align: center;
  justify-content: center;
}
.selectmonth text{
  flex: 1;
  flex-direction: row;
  line-height: 80rpx;
}

.datetitle{
  display: flex;
  flex-direction:row;
  align-items:center;/*垂直居中*/
  justify-content: center;/*水平居中*/
  width: 100%;
  text-align: center;
  background: #01DBCA;
  color: white;
  font-size: 0.8rem;
}
.datetitle text{
  display: block;
  flex: 1;
  line-height: 60rpx;
}

.dayrow{
  display: flex;
  flex-direction:row;
  align-items:center;/*垂直居中*/
  justify-content: center;/*水平居中*/
  width: 100%;
  text-align: center;
  color: #888;
  font-size: 0.8rem;
}
.dayrow text{
  display: block;
  flex: 1;
  line-height: 80rpx;
}
.notcurentmonth{
  color:#eee;
}
.today{
  color: #01DBCA;
  font-weight: bold;
}
.record{
  background: #EFE4B0;
  color: white;
  border-radius: 20rpx;
}
.todayandrecord{
  color: #01DBCA;
  font-weight: bold;
  background: #EFE4B0;
  border-radius: 20rpx
}
```

上面也就实现了，接着我们就需要抽象成为组件：
还是在components文件夹下定义组件，这里命名为calendar，开始抽取：
其中wxml文件和wxss文件不变，直接拷贝进去就可以，需要弄得就是js文件：
calendar.js
``` javascript
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
    //当前月的天数，如30 30 1 2 3 ... 一共6*7=42个元素
    daylist: { // 属性名
      type: Array,
      value: [] // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
    //得到当前日期
    current_day: { //今天的下标，以设置样式
      type: Number,
      value: 0
    },
    current_year: { //今天的下标，以设置样式
      type: Number,
      value: 0
    },
    current_month: { //今天的下标，以设置样式
      type: Number,
      value: 0
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    firstday: 0, // 每个月一号前的序号下标,即属于上一个月的天数的最后一天的下标
    lastday: 0, // 属于下一个月第一天的小标
    today: 0 //今天的下标，以设置样式
  },
  pageLifetimes: {
    // 组件所在页面的生命周期函数
    show: function() {
      var day = this.data.current_day;
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month,
        daylist: this.datetimepick(year, month)
      });
      this.setcurrentday();
    },
    hide: function() {},
    resize: function() {},
  },
  /**
   * 组件的方法列表
   */
  methods: {
    /**
     * 按照42个元素位置封装每一个位置的元素
     */
    datetimepick: function(y, m) {
      var list = [];
      //首先拿到本月的天数
      var day = this.calculatedays(y, m);
      //然后计算本月第一天是星期几
      var first = this.calculateweek(y, m, 1);
      this.setData({
        firstday: first - 1 //返回下标
      });
      //假如是2，即星期二，前面应该是退两次，得几退几次，退的是上月的天数
      var previousday = 0;
      //需要考虑是否涉及上一年
      //因为1月份中，前一部分是上一年的12月的
      if (m == 1) {
        previousday = this.calculatedays(y - 1, 12);
      } else {
        previousday = this.calculatedays(y, m - 1);
      }
      for (var i = 0; i < first; i++) {
        // list.push(previousday);//数据分装的不好，应该是[day, isrecord, istoday]
        list.push([previousday, 0, 0]); //0=False, 1=True
        previousday -= 1;
      }
      //然后正常计算，在这里计算第几天的下标
      var curentday = 1;
      for (var i = 0; i < day; i++) {
        if (curentday == this.data.current_day) {
          //计算今天的下标
          this.setData({
            today: list.length
          });
        }
        list.push([curentday, 0, 0]); // 同理，加入三元组
        curentday += 1;
      }
      //计算余下的，后面的一定是42减去元素的个数，然后从1开始累加，
      var length = list.length
      this.setData({
        lastday: length //返回下标
      });
      var curentday = 1;
      for (var i = 0; i < 42 - length; i++) {
        // list.push(curentday);
        list.push([curentday, 0, 0]);
        curentday += 1;
      }

      //为了方便表示，可以将list数组切割
      var num = 7; // 每页显示 10 条
      var index = 0;
      var newlist = [];
      for (var i = 0; i < 42; i++) {
        if (i % num === 0 && i !== 0) { // 可以被 10 整除
          newlist.push(list.slice(index, i));
          index = i;
        };
        if ((i + 1) === 42) {
          newlist.push(list.slice(index, (i + 1)));
        }
      };
      return newlist;
    },
    /**
     * 计算某年某月某天，是星期几
     */
    calculateweek: function(year, month, d) {
      var d = d,
        m = month,
        y = year;
      if (month == 1) {
        y = year - 1
        m = 13
      }
      if (month == 2) {
        y = year - 1
        m = 14
      }
      //带入公式：W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400+1) mod 7
      var w = (d + 2 * m + parseInt(3 * (m + 1) / 5) + y + parseInt(y / 4) - parseInt(y / 100) + parseInt(y / 400) + 1) % 7;
      return w;
    },
    /***
     * 得出某年的某月是有多少天
     */
    calculatedays: function(year, month) {
      var months = this.calculatemonth(year);
      //计算该月有几天
      return months[month - 1];
    },
    /**
     * 
     * 根据某年，得出本年所有的月份的天数
     */
    calculatemonth: function(year) {
      var flag = false;
      if ((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) {
        flag = true;
      }
      if (flag) {
        return [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
      } else {
        return [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
      }
    },
    previousmonth: function() {
      var day = 1;
      var year = this.data.current_year;
      var month = this.data.current_month - 1;
      if (month == 1) {
        month = 12;
        year = year - 1;
      }
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month
      });
      this.setData({
        daylist: this.datetimepick(year, month)
      });
      this.setcurrentday();
    },
    nextmonth: function() {
      var day = 1;
      var year = this.data.current_year;
      var month = this.data.current_month + 1;
      if (month == 12) {
        month = 1;
        year = year + 1;
      }
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month
      });
      this.setData({
        daylist: this.datetimepick(year, month)
      });
      this.setcurrentday();
    },
    //测试函数：如果是当前月，将daylist item中的对应位置置1
    setcurrentday: function() {
      var index = this.data.today //取今天的下标
      var tempdaylist = this.data.daylist
      //循环判断得到对应index位置的元素
      for (var i = 0; i < 6; i++) {
        for (var j = 0; j < 7; j++) {
          if (j + i * 7 == index) {
            //找到对应位置，设置当前的值为1[xx, 1, 0]  0是打卡记录
            tempdaylist[i][j][1] = 1;
          }
          // console.log((j + i * 7) % 5)
          if ((j + i * 7) % 5 == 3) {
            console.log(123)
            tempdaylist[i][j][2] = 1;
          }
        }
      }

      //设置回去
      this.setData({
        daylist: tempdaylist
      });
    }
  }
})
```

值得注意的有两点：
1. 属性和私有数据都可以使用this.data来访问
实际运用中，属性就相当于外部接口，用于外部设置data数据
而data中的，就是自己根据需要定义的
这两种类型的数据都可以用this.data.xx来访问。
``` JavaScript
  properties: {
    myProperty: { // 属性名
      type: String,
      value: ''
    },
    myProperty2: String // 简化的定义方式
  },
  
  data: {}, // 私有数据，可用于模板渲染
```
2. 组件的声明周期函数：
``` JavaScript
pageLifetimes: {
    // 组件所在页面的生命周期函数
    show: function () { },
    hide: function () { },
    resize: function () { },
  },
```

我们来看一看结果：
还是在showdate.wxml文件中使用看看效果。首先我们需要在json中引用：
showdate.json
``` json
{
  "usingComponents": {
    "calendar": "/pages/components/calendar/calendar"
  }
}
```

然后，就可以在showdate.wxml文件中使用：
showdate.wxml
``` HTML
 <calendar 
      daylist="[[[30,1,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]],[[30,1,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]],[[30,1,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]],[[30,1,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]],[[30,1,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]],[[30,1,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,1,0]]]"
    current_day="9"
    current_month="7"
    current_year="2019"  
    >
  </calendar>
```

值得注意的就是：**在使用参数的时候是等号，不是冒号，而且无论定义的是什么类型的数据，传入都需要用双引号包起来**

需要设置一下样式：
showdate.wxss

``` css
calendar{
  width: 100%;
}
```

---

其实我上面的daylist数据没有被使用到，而是直接根据输入的日期来计算得到的日历
那么为什么要在这里保留daylist?
其实，因为我觉得数据应该从数据库中获取，打个比方，上一个月的打卡记录，就应该是保存在数据库中的，也就是在daylist数组中因该有[x, 0,1]的记录，而记录这个东西不是算的，而是直接读取的。
所以第二个版本因该就是从数据库中读和写
感觉应该设置一个标志flag，表示是否从数据库中读，不读就直接根据当前的日期计算得了。

姑且将上面的命名为1.0版本。
