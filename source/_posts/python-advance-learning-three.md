---
title: Python高级编程教程（三） | 第三章 生成器
date: 2019-04-19 14:46:43
comments: true
categories: "Python高级编程教程系列"
tags: 
    - Python
---
# 第三章 生成器	

## 简介
生成器处理值序列的时候，允许序列中的值只在需要的时候才计算。而不是传统的计算列表中所有的值。
在恰当的地方使用生成器可以节省内存。
* 生成器是一个函数，按照顺序返回一个或者多个值
* 生成器可以表现为一个无限序列

生成器函数的特征：函数内部有一个或者多个`yield`语句，而不是`return`。在`python2`中`yeild`和`return`不能共存，`python3`中可以共存
`yield`和`return`一样，命令函数返回一个值给调用者。不同的是，`yield`不会终止函数的执行，执行会暂时停顿直到调用代码重新恢复生成器，在停顿的地方再次执行。

## 引例
我们先看看下面简单的打开文件的代码：
``` python
def fibonacci():
    yield 1
    yield 1
    yield 2
    yield 3
    yield 5
    yield 8

for i in fibonacci():#之所以用for in循环，因为我们前面说了，生成器是一个值序列
    print(i)
```

会输出`1，2，3，4，5，6，7，8` （逗号是换行，这里为了书写方便）
下面我们看看无限的生成器：

``` python
def fibonacci():
    li = []
    while True:
        if len(li)<2:
            li.append(1)
        else:
            li.append(sum(li))
            li.pop(0)
        yield li[-1]

for i in fibonacci():
    print(i)
    import time   #程序执行太快，这里我们休眠一下
    time.sleep(1)
```

由于`python`列表不能存储无穷的数值序列，此时我们的生成器表示的`fibonacci`序列就很方便了。

## next函数
虽然for-in循环可以循环得到值，但是有时可能只打算一个单一的值或者固定数量的值，就不适用了。
python中提供了next函数，用于请求下一个值。


``` python
def fibonacci():
    li = []
    while True:
        if len(li)<2:
            li.append(1)
        else:
            li.append(sum(li))
            li.pop(0)
        yield li[-1]
        
gen = fibonacci()
val = next(gen)   #1
print(val)
val = next(gen)  #1
print(val)
val = next(gen)  #2
print(val)
val = next(gen)  #3
print(val)
```

因为`yield`在循环中，调用后，回归状态，然后继续执行循环。

## 生成器之间的交互
生成器提供了一个send方法，允许生成器的反向沟通。能赋值给yield表达式的结果

**下面举一个简单的例子**

``` python
def squares():
    cursor = 1
    while True:
        response = yield cursor ** 2
        if response:
            cursor = int(response)
        else:
            cursor += 1

sq = squares()
print(next(sq)) #1
print(next(sq)) #4
print(sq.send(8))#64
print(next(sq)) #81
print(next(sq)) #100
```

不难看出，我们的send方法，传值给了yield调用处的response，然后我们判断一下response的值是否是None，完成了对cursor的修改，最后就完成了生成器的跳跃。

可以看出，不是就意味着我们使用了send，然后我们的生成器就可以完成跳跃，而是需要由代码的逻辑来决定的。
如：

``` python
def squares():
    cursor = 3
    re = None
    while True:
        if re:
            yield re ** 2   #这里计算8*8,加入值到序列
            re = None       #置初始值None，使if不执行
        else:
            re = yield cursor ** 2  #send从这里传入re=8
            cursor += 1

sq = squares()
print(next(sq)) #9
print(next(sq)) #16
print(sq.send(8)) #64
print(next(sq)) #25
```

---

## 并非所有的迭代对象都是迭代器
迭代器：
* 包含`__next__`方法的任何对象（可以响应`next`函数）

迭代对象：
* 定义了`__iter__`方法的任何对象，可迭代对象的`__iter__`方法返回一个迭代器。

``` python
>>> r = range(5)
>>> type(r)
<class 'range'>
>>> r.next
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'range' object has no attribute 'next'
>>> iter(r)
<range_iterator object at 0x000000000038A470>
>>> iterator = iter(r)   #通过__iter__方法，返回一个迭代器，迭代器可迭代
>>> next(iterator)
0
>>> next(iterator)
1
```

可以看出我们常用的`range`方法，是迭代对象，不是迭代器。可通过`__iter__`方法返回迭代器。


## 标准库中的生成器
* range函数，range对象的迭代器是一个生成器，返回序列值。
* dict.items及其家族，kyes、values和items (均是：迭代器是一个生成器的迭代对象)
>di = {"hello":"你好", "world":"世界"}
ite = iter(di.items())
print(next(ite))
print(next(ite
>('hello', '你好')   <class 'tuple'>
>('world', '世界')

* zip函数
>z = zip(['a','b','c'],[1,2])
print(type(z))     #<class 'zip'>
it = iter(z)
print(next(it))    #('a', 1)
print(next(it))    #('b', 2)
print(next(it))  #StopIteration
<span>或者：</span>
z = zip(['a','b','c'],[1,2])
print(next(z))    #('a', 1)
print(next(z))    #('b', 2)

* map
* 文件对象，中的特殊方法readline可以用next替代。





