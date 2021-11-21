---
title: K-近邻算法(二)
date: 2019-10-4 19:52:47
author: 无涯明月
comments: true
categories: "Machine Learning"
tags: 
    - Machine Learning
---
## 海伦约会案例
数据集可以在：[Jack-Cherish]()的GIthub上下载，数据格式是这样的：
<img src="/images/201910/2019-10-04_200811.png">
刚刚在百度的时候，看见了这本书的英文原文，居然是免费的！！！
地址：[《Machine Learning in Action》](https://www.manning.com/books/machine-learning-in-action)
在上图中，结果分三类：不喜欢的人、魅力一般的人、极具魅力的人
海伦希望自己的约会对象，周一到周五是魅力一般的人，周末是极具魅力的人。
故而我们的分类软件，就需要根据她收集的这些数据，将相亲对象归类。
### K-近邻算法步骤
①收集数据：这里就是上面的文本
②准备数据：使用Python解析我们的文本文件
③分析数据：使用功能Matplotlib画二维扩散图
④训练算法：不适用与k-近邻算法
⑤测试算法：使用上面的数据，部分作为测试样本
⑥使用算法：输入一些特征数据以判断对方是否是自己喜欢的类型

#### 准备数据
也就是将文本文件中的数据，处理成我们需要的格式的数据。
前一个案例中，我们的数据分两个部分：数据样本、数据样本对应标签
这里，我们还是这样分。
##### 下面涉及的Numpy方法
1. `zeros`函数，返回来一个给定形状和类型的用0填充的数组
案例：[numpy.zeros](https://www.jianshu.com/p/18ff98f7126b)
①只指定长度
如：`a = np.zeros(5)`， 创建的是一维数组，数据类型是浮点型
结果：`[ 0.  0.  0.  0.  0.]`
②指定长度和数据类型
如：`a = np.zeros(5, dtype=int)`，创建一维数组，数据类型指定为整形
结果：`[0 0 0 0 0]`
③创建多维数组，不指定数据类型
如：`a = np.zeros([1,3])`   # 创建一个一行三列的矩阵，默认数据类型是浮点型
结果：`[[ 0.  0.  0.]]`
④创建多维数组，指定数据类型`int`
如：`a = np.zeros([1,3], dtype=int)`
结果：`[[0 0 0]]`


准备数据的代码如下：
``` python
import numpy as np

# 创建数据集
def createDataset(filename):
    # 打开文件
    file = open(filename, mode='r', encoding='utf-8')
    # 读取数据
    datalines = file.readlines()
    # 根据数据的行数，创建对应行数和特征值列数的矩阵
    returnMat = np.zeros((len(datalines), 3)) # 3个特征值，对应三列
    index = 0
    classLabelVector = []
    # 每行读取，填装进矩阵returnMat
    for line in datalines:
        line = line.strip() # 去除首位两端的空格
        listFromLine = line.split('\t')
        returnMat[index:index+1] = listFromLine[0:3] # [index:index+1]只放该index行数据
        classLabelVector.append(listFromLine[-1])  # 我这里存放文本标签
        index = index + 1
    return returnMat, classLabelVector


if __name__ == '__main__':
    filename = "datingTestSet.txt"
    returnMat, classLabelVector = createDataset(filename)
    print(returnMat)
    print(classLabelVector)
    
```

测试一下：
<img src="/images/201910/2019-10-04_205105.png">

#### 分析数据
使用功能Matplotlib画二维扩散图，也即是数据的可视化。
这里简单使用，找到[官方文档](https://www.matplotlib.org.cn/)
##### Matplotlib
Matplotlib是一个Python 2D绘图库，下面就实用 Matplotlib 创建散点图。






----
[《Machine Learning in Action》](https://www.manning.com/books/machine-learning-in-action)
