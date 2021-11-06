---
title: seo-7 |  用Item数据封装
date: 2019-6-9 16:45:26
comments: true
categories: "scrapy"
tags: 
    - scrapy
---
学习了`xpath`和`css`，我们就可以很容易的从下载的网页中提取出我们想要的数据内容了。数据较多的时候，自然而然就想到了使用字典、列表来封装数据。

`Scrapy`中提供了`Item`类，来封装爬取到的数据。
当然，可以直接使用字典、列表等，虽然能完成任务。但是影响可读性，也缺乏对字段名字的检测。
使用`Item`能明确爬取内容，需要爬取的字段清晰明了；避免出现变量名写错的低级错误。
爬虫流程就成了这样：
抓取 --> 按`item`规则收集需要数据 -->使用`pipeline`处理（存储等）

`Scrapy`提供了两个类，来封装爬取到的数据：
# Item基类 和 Field类 的基本用法
自定义一个类来封装数据的步骤：
①编写一个类继承Item基类；
②使用Field类对象，来创建类的属性；
这里举一个小案例：
自定义一个学生数据类
``` python
from scrapy import Item,Field

class student(Item):
    name = Field()
    age = Field()
    sex = Field()


stu1 = student(name = "Tom", age=12, sex = "男")
print(stu1)               #{'age': 12, 'name': 'Tom', 'sex': '男'}
print(stu1.get('name'))   #Tom
print(stu1['name'])       #Tom
print(stu1['age'])        #12

stu2 = student()
stu2['name'] = "Jarry"
stu2['age'] = 23
stu2['sex'] = "女"
print(stu2)              #{'age': 23, 'name': 'Jarry', 'sex': '女'}

all = list(stu2.items())
print(stu2.items()) #ItemsView({'age': 23, 'name': 'Jarry', 'sex': '女'})
print(all)        #[('name', 'Jarry'), ('age', 23), ('sex', '女')]
```

