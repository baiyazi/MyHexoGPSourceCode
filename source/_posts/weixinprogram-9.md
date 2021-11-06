---
title: 微信小程序开发 | 云数据库更新异常
date: 2019-7-20 19:30:27
comments: true
categories: "微信小程序"
tags: 
    - 小程序代码构成
---

<i class="fa  fa-bookmark fa-lg"></i> 使用update的时候，出现了错误：stats:
也即是数据根本没有更新。寻寻觅觅……(参考地址：[参考解决地址](https://blog.csdn.net/Cooler_max/article/details/88756943))
然后，用add方法来测试一下添加的数据是什么样的：
参考官网地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-client-api/database/collection.add.html


``` JavaScript
db.collection('weightdata').add({
      // data 字段表示需新增的 JSON 数据
      data: {
        // _id: 'todo-identifiant-aleatoire', // 可选自定义 _id，在此处场景下用数据库自动分配的就可以了
        description: "learn cloud database",
        due: new Date("2018-09-01"),
        tags: [
          "cloud",
          "database"
        ],
        // 为待办事项添加一个地理位置（113°E，23°N）
        location: new db.Geo.Point(113, 23),
        done: false
      },
      success: function (res) {
        // res 是一个对象，其中有 _id 字段标记刚创建的记录的 id
        console.log(res)
      },
      fail: console.error
    })
```

添加完成后，我们打开云开发控制台，然后，截图如下：
![e](/images/201907/2019-07-20_192846.png)

而我自己添加的数据记录如下图：
![e](/images/201907/2019-07-20_193111.png)



```

```


