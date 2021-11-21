---
title: Python高级编程教程（一）| 第一章 装饰器类
date: 2019-04-13 14:46:43
comments: true
categories: "Python高级编程教程系列"
tags: 
    - Python
---
# 第一章 装饰器类
## 简介
用于封装函数或者类代码的工具。其核心也就是一个可以接受调用也可以返回调用的调用。无非就是一个函数（或调用），该函数接受被装饰的函数作为其位置参数。装饰器通过使用该参数来执行一些操作，然后返回原始参数或者其他的一些调用。
**装饰器类也就是接受另一个函数作为参数，并用其完成一些操作的函数。**
装饰器类：通常是接受被装饰的可调用函数作为唯一参数，并返回一个可调用函数。
**示例：**
``` python
def debug(func):
    def wrapper():
        print("[DEBUG]: enter {}()".format(func.__name__))
        return func()
    return wrapper

@debug
def say_hello():
    print("hello!")

if __name__ == '__main__':
    say_hello()
```
在 `say_hello()`函数的定义前加上`@debug`，相当于执行了：`say_hello=debug(say_hello)`
也就是说：
> 当装饰器应用到装饰函数时（而不是调用装饰器），会执行装饰代码本身。 

### 练习: 计算任意函数运行时间
``` python
import time
def decorator(func):
    def wrapper(*args, **kwargs):
        start_time = time.time()
        func()
        end_time = time.time()
        print(end_time - start_time)
    return wrapper

@decorator
def func():
    time.sleep(0.8)

func() # 函数调用
# 输出：0.800644397735595
if __name__ == '__main__':
    func()
```

> 这里的内层函数-wrapper，其实就相当于闭包函数，它起到装饰给定函数的作用，wrapper参数为*args, **kwargs。*args表示的参数以列表的形式传入；**kwargs表示的参数以字典的形式传入
> 什么函数可以被称为闭包函数呢？主要是满足两点：函数内部定义的函数；引用了外部变量但非全局变量。

## 类装饰器
在Python中，一切皆对象，函数和类本质没有什么不一样。[装饰器函数、装饰器类]
装饰器函数其实是这样一个接口约束，它必须接受一个callable对象作为参数，然后返回一个callable对象。在Python中一般callable对象都是函数，但也有例外。只要某个对象重载了`__call__()`方法，那么这个对象就是callable的。
回到装饰器上的概念上来，装饰器要求接受一个callable对象，并返回一个callable对象（不太严谨，详见后文）。那么用类来实现也是也可以的。我们可以让类的构造函数`__init__()`接受一个函数，然后重载`__call__()`并返回一个函数，可将一个类实例变成一个可调用对象，也可以达到装饰器函数的效果。
``` python
class Decorator(object):
    def __init__(self, f):
        self.f = f
    def __call__(self):
        print("decorator start")
        self.f()
        print("decorator end")

@Decorator
def func():
    print("func")

func()
#运行结果：
#decorator start
#func
#decorator end
```
**解释：**
> p = `Decorator(func)`   p是类Decorator的一个实例
p()  实现了`__call__()`方法后，p可以被调用
要使用类装饰器必须实现类中的`__call__()`方法，就相当于将实例变成了一个方法。

## 多个装饰器
当有多个装饰器修饰一个函数的时候，装饰器的执行顺序是**由近及远**
## Python装饰器库-functools
``` python
def decorator(func):
    def inner_function():
        pass
    return inner_function

@decorator
def func():
    pass
print(func.__name__)
```
>执行的结果：
inner_function

也就是说，代码执行的不是`func`，而是直接调用的`inner_function`函数。
**需要借助functools模块**
因为返回的那个`inner_function()`函数名字就是`inner_function`，所以，需要把原始函数的`__name__`等属性复制到`inner_function()`函数中，否则，有些依赖函数签名的代码执行就会出错。
不需要编写`inner_function.__name__ = func.__name__`这样的代码，Python内置的`functools.wraps`就是干这个事的
修改后：
```
from functools import wraps
def decorator(func):
    @wraps(func) 
    def inner_function():
        pass
    return inner_function

@decorator
def func():
    pass

print(func.__name__)
```
>运行结果：
func

## 应用场景
* 某个功能在你原有的函数基础上，添加了新的功能，而你又不希望去修改原有的函数定义，从而定义的新的函数。这种在代码运行期间动态增加功能的方式，称之为装饰器。
* Django中使用@login_required作为装饰器
* Flask中使用@app.route充当指定URI路由

