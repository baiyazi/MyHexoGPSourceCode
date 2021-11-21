---
title: 微信小程序开发 | 分装组件【日历】二
date: 2019-7-12 12:03:00
comments: true
categories: "微信小程序"
tags: 
    - 小程序代码构成
---
上一节提到了比较多的问题没有解决，这里就开始对不合理的数据进行在改造

官方文档：https://developers.weixin.qq.com/miniprogram/dev/framework/custom-component/ 

问题比较多，列举几个：
1. 代码冗余
一个函数，就应该是一个功能，而不应该是冗余在一起，这样不利于后期的代码修改与调试
2. 数据的封装规划不合理
对于数据的封装，也不应该冗余，也应该抽调出一个单独的函数来完成各自的功能
而且，在数据封装的时候需要考虑到处理数据的逻辑
处理的过程应该面相接口 ， 如firstday和lastday的封装
3. 新增数据card，标记变量flag
这样就不用再重构daylist的时候去考虑计算每一个item中的最后一个元素的下标应该如何表示
4. 边界问题
对于事件响应函数的处理，因该考虑边界问题，不应该出现没有实际意义的月份/年份
如2019-14-1就是不合理的
5. 事件处理中的当前月
当前月，就因该在本月本日显示不一样的样式，也就是在daylist中的item中的第二个元素置1
非当前月份，设置在1号

同样的还是先在showdate中修改代码，然后在封装成组件，先看看效果：
![e](/images/201907/2019-07-12_121212.png)

代码修改的是逻辑，所以也就是修改的js代码，由于上一讲比较详细的讲了如何封装，这里就给出相应的js代码。
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
    card: { // 属性名
      type: Array,
      value: [] // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
    flag: { // 属性名
      type: Boolean,
      value: false // 属性初始值（可选），如果未指定则会根据类型选择一个
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
      console.log(this.data.daylist)
      var day = this.data.current_day;
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month,
      });
      this.setData({
        daylist: this.datetimepick(year, month)
      });
      //判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
      this.setdaylistbycard(this.data.card);
      console.log(this.data.card)
      console.log(this.data.current_day)
      
      this.setbackgroundcolor();
    },
    hide: function() {},
    resize: function() {},
  },
  /**
   * 组件的方法列表
   */
  methods: {
    //根据card计算daylist
    setdaylistbycard: function (card) {
      //card = [1,5,8,9]
      let tempdaylist = this.data.daylist
      for (var card_i = 0; card_i < card.length; card_i++) {
        for (var i = 0; i < 6; i++) {
          for (var j = 0; j < 7; j++) {
            if (tempdaylist[i][j][0] == card[card_i]) {
              tempdaylist[i][j][2] = 1;
            }
          }
        }
      }
      //设置回去
      this.setData({
        daylist: tempdaylist
      });
    },
    //设置本月日历中上一个月和下一个月的占位是多少
    setheadandlastday: function (y, m) {
      var firstday = this.calculateweek(y, m, 1) - 1; //计算本月第一天是星期几
      //由于日历中前面不可能空一周，所以占位就是星期数
      //拿到本月天数
      var daynum = this.calculatedays(y, m); // 拿到本月的天数
      this.setData({
        firstday: firstday,
        lastday: (firstday + daynum + 1)
      });
    },
    //计算本月的上一个月有多少天
    getpremonthdays: function (y, m) {
      var previousday = 0;
      //需要考虑是否涉及上一年
      //因为1月份中，前一部分是上一年的12月的
      if (m == 1) {
        previousday = this.calculatedays(y - 1, 12);
      } else {
        previousday = this.calculatedays(y, m - 1);
      }
      return previousday;
    },
    /**
     * 按照42个元素位置封装每一个位置的元素
     */
    datetimepick: function (y, m) {
      var list = [];
      var firstday = this.calculateweek(y, m, 1); //计算本月第一天是星期几
      var previousday = this.getpremonthdays(y, m) //上个月的天数,计算就回退
      //假如是2，即星期二，前面应该是退两次，得几退几次，退的是上月的天数 
      for (var i = 0; i < firstday; i++) {
        list.push([previousday, 0, 0]);
        previousday -= 1; //从最后一天开始回退
      }
      list.reverse();
      //然后正常计算，在这里计算第几天的下标
      var curentday = 1;
      var day = this.calculatedays(y, m); // 拿到本月的天数
      for (var i = 0; i < day; i++) {
        list.push([curentday, 0, 0]); // 同理，加入三元组
        curentday += 1;
      }
      var length = list.length //列表的长度就是上一个月占位 + 本月实际天数占位  剩余的就是下一个月占位
      var curentday = 1; //下一个月的日历， 从1开始占位
      for (var i = 0; i < 42 - length; i++) {
        list.push([curentday, 0, 0]);
        curentday += 1;
      }
      this.setheadandlastday(y, m);
      return this.slicelist(list);
    },
    //将上面生成的日历列表按照周切割
    slicelist: function (list) {
      //为了方便表示，可以将list数组切割
      var num = 7; // 每页显示 7 条
      var index = 0;
      var newlist = [];
      for (var i = 0; i < 42; i++) {
        if (i % num === 0 && i !== 0) { // 可以被 7 整除
          newlist.push(list.slice(index, i));
          index = i;
        };
        if ((i + 1) === 42) {
          newlist.push(list.slice(index, (i + 1)));
        }
      }
      return newlist;
    },

    /**
     * 计算某年某月某天，是星期几
     */
    calculateweek: function (year, month, d) {
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
    calculatedays: function (year, month) {
      var months = this.calculatemonth(year);
      //计算该月有几天
      return months[month - 1];
    },
    /**
     * 
     * 根据某年，得出本年所有的月份的天数
     */
    calculatemonth: function (year) {
      var fg = false;
      if ((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) {
        fg = true;
      }
      if (fg) {
        return [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
      } else {
        return [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
      }
    },

    //事件响应函数，上一个月
    previousmonth: function () {
      let date = new Date();
      var day = 1;
      if (this.data.current_year == date.getFullYear() && this.data.current_month - 1 == date.getMonth() + 1) {
        day = date.getDate()
      }
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setheadandlastday(year, month);
      if (month == 1) {
        month = 12;
        year = year - 1;
      } else {
        month -= 1;
      }
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month
      });
      this.setData({
        daylist: this.datetimepick(year, month)
      });
      
      //判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
      this.setdaylistbycard(this.data.card);  //模拟一下，需要从云端加载


      this.setbackgroundcolor();
    },
    //后一个月
    nextmonth: function () {
      //计算当前的月份和年份是否是本年本月，是就需要设置today为今天的下标，否则设置为1
      // console.log(new Date().getFullYear())  //2019
      // console.log(new Date().getDate())   //12
      // console.log(new Date().getMonth())  // 6
      let date = new Date();
      var day = 1;
      if (this.data.current_year == date.getFullYear() && this.data.current_month + 1 == date.getMonth() + 1) {
        day = date.getDate()
      }
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setheadandlastday(year, month);
      if (month >= 12) {
        month = 1;
        year = year + 1;
      } else {
        month = month + 1;
      }
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month
      });

      this.setData({
        daylist: this.datetimepick(year, month)
      });
      //判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
      this.setdaylistbycard(this.data.card);  //模拟一下，需要从云端加载


      this.setbackgroundcolor();
    },
    //测试函数：如果是当前月，将daylist item中的对应位置置1
    setbackgroundcolor: function () {
      var tempdaylist = this.data.daylist
      var index = this.getdayindex(tempdaylist, this.data.current_day)
      //循环判断得到对应index位置的元素
      for (var i = 0; i < 6; i++) {
        for (var j = 0; j < 7; j++) {
          if (j + i * 7 == index) {
            //找到对应位置，设置当前的值为1[xx, 1, 0]  0是打卡记录
            tempdaylist[i][j][1] = 1;
          }
        }
      }

      //设置回去
      this.setData({
        daylist: tempdaylist
      });
    },
    //计算本月某一天所在daylist的整体拉通的下标(从0开始)
    getdayindex: function (tempdaylist, day) {
      let index = 0;
      for (var i = 0; i < 6; i++) {
        for (var j = 0; j < 7; j++) {
          if (tempdaylist[i][j][0] == this.data.current_day) {
            this.setData({
              today: index
            });
            return index;
          } else {
            index += 1;
          }
        }
      }
    }
  }
})
```

然后在引用组件时候需要注意的是数组的传递需要使用括号，如下：
showdate.wxml
``` html 
 <calendar 
    card= '{{[1,4,9,10,11]}}'
    current_day="12"
    current_month="7"
    current_year="2019"  
    >
  </calendar>
```

当然，card需要每次在响应事件的时候，从云端加载。下面就实现从云端加载的功能：

打开官网API：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/basis/getting-started.html

打开云开发控制台，不妨建立一个card的数据集合，然后设置该集合的权限，如下图：
![e](/images/201907/2019-07-12_123655.png)
然后，添加记录，ID使用系统生成，具体的如下图：
![e](/images/201907/2019-07-12_123820.png)

寻寻觅觅，找到和操作相关的API（地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-client-api/database/collection.get.html ），找到案例，修改修改：
``` JavaScript
const db = wx.cloud.database()
    db.collection('card').where({
      month:7,
      year:2019
    }).get().then(res => {
      console.log(res.data[0].card)
    })
```

然后，在控制台中可以看见输出：
![e](/images/201907/2019-07-12_124802.png)

刚好就是我们设置的数据。
完整的逻辑因该是：
在show函数中，修改一下
``` JavaScript
//判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
    if (!this.data.flag) {
      const db = wx.cloud.database()
      db.collection('card').where({
          month: this.data.current_month,
          year: this.data.current_year
      }).get().then(res => {
        this.setdaylistbycard(res.data[0].card);
        this.setData({
          card: res.data[0].card
        })
      })
    }else{
      this.setdaylistbycard(this.data.card);
    }
    this.setbackgroundcolor();
```

当然在事件响应函数中也应该处理，完整的calendar.js代码如下：
``` JavaScript
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
    card: { // 属性名
      type: Array,
      value: [] // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
    flag: { // 属性名
      type: Boolean,
      value: false // 属性初始值（可选），如果未指定则会根据类型选择一个
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
      console.log(this.data.daylist)
      var day = this.data.current_day;
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month,
      });
      this.setData({
        daylist: this.datetimepick(year, month)
      });
      //判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
      if (!this.data.flag) {
        const db = wx.cloud.database()
        db.collection('card').where({
          month: this.data.current_month,
          year: this.data.current_year
        }).get().then(res => {
          this.setdaylistbycard(res.data[0].card);
          this.setData({
            card: res.data[0].card
          })
        })
      } else {
        this.setdaylistbycard(this.data.card);
      }
      this.setbackgroundcolor();
    },
    hide: function() {},
    resize: function() {},
  },
  /**
   * 组件的方法列表
   */
  methods: {
    //根据card计算daylist
    setdaylistbycard: function (card) {
      //card = [1,5,8,9]
      let tempdaylist = this.data.daylist
      for (var card_i = 0; card_i < card.length; card_i++) {
        for (var i = 0; i < 6; i++) {
          for (var j = 0; j < 7; j++) {
            if (tempdaylist[i][j][0] == card[card_i]) {
              tempdaylist[i][j][2] = 1;
            }
          }
        }
      }
      //设置回去
      this.setData({
        daylist: tempdaylist
      });
    },
    //设置本月日历中上一个月和下一个月的占位是多少
    setheadandlastday: function (y, m) {
      var firstday = this.calculateweek(y, m, 1) - 1; //计算本月第一天是星期几
      //由于日历中前面不可能空一周，所以占位就是星期数
      //拿到本月天数
      var daynum = this.calculatedays(y, m); // 拿到本月的天数
      this.setData({
        firstday: firstday,
        lastday: (firstday + daynum + 1)
      });
    },
    //计算本月的上一个月有多少天
    getpremonthdays: function (y, m) {
      var previousday = 0;
      //需要考虑是否涉及上一年
      //因为1月份中，前一部分是上一年的12月的
      if (m == 1) {
        previousday = this.calculatedays(y - 1, 12);
      } else {
        previousday = this.calculatedays(y, m - 1);
      }
      return previousday;
    },
    /**
     * 按照42个元素位置封装每一个位置的元素
     */
    datetimepick: function (y, m) {
      var list = [];
      var firstday = this.calculateweek(y, m, 1); //计算本月第一天是星期几
      var previousday = this.getpremonthdays(y, m) //上个月的天数,计算就回退
      //假如是2，即星期二，前面应该是退两次，得几退几次，退的是上月的天数 
      for (var i = 0; i < firstday; i++) {
        list.push([previousday, 0, 0]);
        previousday -= 1; //从最后一天开始回退
      }
      list.reverse();
      //然后正常计算，在这里计算第几天的下标
      var curentday = 1;
      var day = this.calculatedays(y, m); // 拿到本月的天数
      for (var i = 0; i < day; i++) {
        list.push([curentday, 0, 0]); // 同理，加入三元组
        curentday += 1;
      }
      var length = list.length //列表的长度就是上一个月占位 + 本月实际天数占位  剩余的就是下一个月占位
      var curentday = 1; //下一个月的日历， 从1开始占位
      for (var i = 0; i < 42 - length; i++) {
        list.push([curentday, 0, 0]);
        curentday += 1;
      }
      this.setheadandlastday(y, m);
      return this.slicelist(list);
    },
    //将上面生成的日历列表按照周切割
    slicelist: function (list) {
      //为了方便表示，可以将list数组切割
      var num = 7; // 每页显示 7 条
      var index = 0;
      var newlist = [];
      for (var i = 0; i < 42; i++) {
        if (i % num === 0 && i !== 0) { // 可以被 7 整除
          newlist.push(list.slice(index, i));
          index = i;
        };
        if ((i + 1) === 42) {
          newlist.push(list.slice(index, (i + 1)));
        }
      }
      return newlist;
    },

    /**
     * 计算某年某月某天，是星期几
     */
    calculateweek: function (year, month, d) {
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
    calculatedays: function (year, month) {
      var months = this.calculatemonth(year);
      //计算该月有几天
      return months[month - 1];
    },
    /**
     * 
     * 根据某年，得出本年所有的月份的天数
     */
    calculatemonth: function (year) {
      var fg = false;
      if ((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) {
        fg = true;
      }
      if (fg) {
        return [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
      } else {
        return [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
      }
    },

    //事件响应函数，上一个月
    previousmonth: function () {
      let date = new Date();
      var day = 1;
      if (this.data.current_year == date.getFullYear() && this.data.current_month - 1 == date.getMonth() + 1) {
        day = date.getDate()
      }
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setheadandlastday(year, month);
      if (month == 1) {
        month = 12;
        year = year - 1;
      } else {
        month -= 1;
      }
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month
      });
      this.setData({
        daylist: this.datetimepick(year, month)
      });
      
      //判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
      if (!this.data.flag) {
        const db = wx.cloud.database()
        db.collection('card').where({
          month: this.data.current_month,
          year: this.data.current_year
        }).get().then(res => {
          if (res.data[0]!=null){
            this.setdaylistbycard(res.data[0].card);
            this.setData({
              card: res.data[0].card
            })
          }
        })
      } else {
        this.setdaylistbycard(this.data.card);
      }
      this.setbackgroundcolor();
    },
    //后一个月
    nextmonth: function () {
      //计算当前的月份和年份是否是本年本月，是就需要设置today为今天的下标，否则设置为1
      // console.log(new Date().getFullYear())  //2019
      // console.log(new Date().getDate())   //12
      // console.log(new Date().getMonth())  // 6
      let date = new Date();
      var day = 1;
      if (this.data.current_year == date.getFullYear() && this.data.current_month + 1 == date.getMonth() + 1) {
        day = date.getDate()
      }
      var year = this.data.current_year;
      var month = this.data.current_month;
      this.setheadandlastday(year, month);
      if (month >= 12) {
        month = 1;
        year = year + 1;
      } else {
        month = month + 1;
      }
      this.setData({
        current_day: day,
        current_year: year,
        current_month: month
      });

      this.setData({
        daylist: this.datetimepick(year, month)
      });
      //判断flag，是否需要从云端加载当前月的card,然后根据card计算daylist item的第三个元素
      if (!this.data.flag) {
        const db = wx.cloud.database()
        db.collection('card').where({
          month: this.data.current_month,
          year: this.data.current_year
        }).get().then(res => {
          if (res.data[0] != null) {
            this.setdaylistbycard(res.data[0].card);
            this.setData({
              card: res.data[0].card
            })
          }
        })
      } else {
        this.setdaylistbycard(this.data.card);
      }
      this.setbackgroundcolor();
    },
    //测试函数：如果是当前月，将daylist item中的对应位置置1
    setbackgroundcolor: function () {
      var tempdaylist = this.data.daylist
      var index = this.getdayindex(tempdaylist, this.data.current_day)
      //循环判断得到对应index位置的元素
      for (var i = 0; i < 6; i++) {
        for (var j = 0; j < 7; j++) {
          if (j + i * 7 == index) {
            //找到对应位置，设置当前的值为1[xx, 1, 0]  0是打卡记录
            tempdaylist[i][j][1] = 1;
          }
        }
      }

      //设置回去
      this.setData({
        daylist: tempdaylist
      });
    },
    //计算本月某一天所在daylist的整体拉通的下标(从0开始)
    getdayindex: function (tempdaylist, day) {
      let index = 0;
      for (var i = 0; i < 6; i++) {
        for (var j = 0; j < 7; j++) {
          if (tempdaylist[i][j][0] == this.data.current_day) {
            this.setData({
              today: index
            });
            return index;
          } else {
            index += 1;
          }
        }
      }
    }
  }
})

```

然后就是添加打卡到数据库中存储就可以了。
