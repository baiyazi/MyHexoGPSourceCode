---
title: Python高级编程教程（二）| 第二章 上下文管理器
date: 2019-04-15 14:46:43
comments: true
categories: "Python高级编程教程系列"
tags: 
    - Python
---
# 第二章 上下文管理器
## 简介

上下文管理器是装饰器的近亲，都是包装其他代码的工具。
装饰器包装函数或者类；上下文管理器包装任意格式的代码块。在大多数情况下，作用等价。
上下文管理器是一个包装任意代码块的对象。保证进入上下文管理器时，每次代码执行的一致性；当退出上下文管理器时，相关的资源会被正确回收。
上下文管理器应用最多的就是--作为确保资源被正确清理的一种方式。有点类似于try-exception-finally的结构。
## 引例
我们先看看下面简单的打开文件的代码：
``` python
file = open('a.txt',"w")
file.write("hello")
file.close()
```
如果在文件打开失败了，代码执行到`file.close()`就会是一个空对象执行`close()`方法。
显然这是不行的，所以有了下面的代码：
``` python
file = ""
try:
    file = open('a.txt','w')
    file.write('se')
finally:
    file.close()
```

`finally`语句块中的代码无论try语句块中发生了什么都会执行。因此可以保证文件一定会关闭。这么做有什么问题么？当然没有，但当我们写一些更复杂的代码的时候，`try-finally`语句就会变得丑陋无比。
Python内置函数open也可以作为上下文管理器使用，所以就有了下面的改进版本：
``` python
with open("a.txt", 'w') as f:
    f.write('Hello ')
```
**`“with”`是一个新关键词，并且总是伴随着上下文管理器出现**
`__enter__` 方法的返回结果会被赋给`as`关键字之后的变量。
`__exit__` 方法则在离开代码块之后被调用(即使在代码块中遇到了异常)。
与装饰器相同的是，使用上下文管理器的关键原因在于避免代码的重复。
### enter和exit方法
with语句的表达式的作用是返回一个遵循特定协议的对象。具体来说，该对象必须定义一个`__enter__`方法和一个`__exit__`方法，且后者必须接受特定参数。
* `__enter__`方法，除了`self`参数外，不接受任何其他参数。一般来说，`__enter__`方法负责执行一些配置。
* `__exit__`方法，除了`self`参数外，还有三个位置参数：一个异常类型、一个异常实例、一个回溯。如果没有异常，这3个参数全被设置成`None`，但如果在代码中发生了异常，则参数被填充。

**下面举一个简单的例子**

``` python
class Test(object):
    def __init__(self):
        self.entered = False
    def __enter__(self):
        self.entered = True
        return self
    def __exit__(self, exc_type, exc_val, exc_tb):
        self.entered = False

t = Test()
print(t.entered)   # False

with Test() as t:
    print(t.entered)   # True

```
上面有两种调用方式，右边分别给出了运行的结果。不难看出：`with`和普通调用的区别就在于是否执行了`__enter__`方法。
### 异常处理
`__exit__`方法中可以选择性地处理包装代码块中出现的异常，或者处理其他需要关闭上下文管理器状态的事情。（具体的异常处理，本文后面会讲解）
## 自定义上下文管理器
要实现上下文管理器，必须实现两个方法 – 一个负责进入语句块的准备操作，另一个负责离开语句块的善后操作。
Python类包含两个特殊的方法，分别名为：`__enter__`以及`__exit__`(双下划线作为前缀及后缀)。
> 当一个对象被用作上下文管理器时：
`__enter__` 方法的返回结果会被赋给`as`关键字之后的变量。
`__exit__` 方法则在离开代码块之后被调用(即使在代码块中遇到了异常)。
与装饰器相同的是，使用上下文管理器的关键原因在于避免代码的重复。

``` python
class Test:
    def __enter__(self):
        print("Enter")
    def __exit__(self, *unused):
        print("Exit")

with Test():
    print("invoking")
#运行结果
#Enter
#invoking
#Exit
```
在此没有使用`“as”`关键词。下面我们自己定义打开文件的方法：
``` python
class TestOpen:
    # 新增初始化方法
    def __init__(self, filename, mode):
        self.filename = filename
        self.mode = mode
    def __enter__(self):
        self.openfile = open(self.filename, self.mode)
        return self.openfile
    def __exit__(self, *unused):
        self.openfile.close()
        
with TestOpen("a.txt", "w") as file:
    file.write("Hello world")
```
如果语句块内部发生了异常，`__exit__`方法将被调用，而异常将会被重新抛出(re-raised)。我们可以让`__exit__`方法简单的返回True来忽略语句块中发生的所有异常(大部分情况下这都不是明智之举)。

## 何时应该编写上下文管理器
### 资源清理
打开和关闭资源（如数据库和文件的连接）。确保异常时正确关闭资源很重要。如下面连接数据库的案例：
``` python
import pymysql

class DBConnection(object):
    def __init__(self, dbName=None,user=None,password=None,host='localhost'):
        self.host = host
        self.dbName = dbName
        self.user = user
        self.password = password
    def __enter__(self):
        self.connectioin = pymysql.connect(self.host,self.user,self.password, self.dbName)
        return self.connectioin.cursor()
    def __exit__(self, exc_type, exc_val, exc_tb):
        self.connectioin.close()

with DBConnection(dbName='weizu',user='root',password='123') as db:
    db.execute('SELECT VERSION()')
    data = db.fetchall()
    print(data)   # (('5.7.24',),)
```
### 避免重复
最常见的是避免重复处理异常。上下文管理器能够传播和终止异常，那么就最好将它和`except`子句放在同一个地方定义。
#### 1.传播异常
`__exit__`方法只是向流程链上传播异常，这是通过返回`False`实现的，根本不需要与异常实例进行交互，如下面的上下文管理器：
``` python
class Test(object):
    def __init__(self):
        pass
    def __enter__(self):
        return self
    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_val:
            print('Bubbling up exception: %s' % exc_val)
        return False

with Test():
    5/0
# Traceback (most recent call last):
# Bubbling up exception: division by zero
#   File "E:/codes/django_learn01/blog/test.py", line 14, in <module>
#     5/0
# ZeroDivisionError: division by zero
```
观察上面的结果，`__exit__`方法确实执行到了`return False`。因为输出了`print`，然后必然执行的是`return`，所以发送给`__exit__`的异常只是被重新抛出了。
#### 2.终止异常
不妨试试，将`__exit__`中的`return False`改为`return True`。看是否还会抛出异常，显然不会。
``` python
class Test(object):
    def __init__(self):
        pass
    def __enter__(self):
        return self
    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_val:
            print('Bubbling up exception: %s' % exc_val)
        return True

with Test():
    5/0
# ZeroDivisionError: division by zero
```
* 回溯消失了。
* 由于异常被`__exit__`方法处理终止了，因此程序没有引发异常，继续执行。
但是，永远也不要这样做

#### 3.处理特定异常类
在`__exit__`方法中，可以仅检查是否是特定异常类的实例，执行异常处理。

```python
class Test(object):
    def __init__(self):
        pass
    def __enter__(self):
        return self
    def __exit__(self, exc_type, exc_val, exc_tb):
        if not exc_type:
            return True
        if issubclass(exc_type,ValueError):
            print("ValueError: %s" % exc_val)
            return True
        return False
#end

```

下面是一些测试的代码：

``` python
with Test():
    raise ValueError("wrong value.")
# ValueError: wrong value.

```

``` python
with Test():
    raise TypeError("wrong type.")
# Traceback (most recent call last):
#   File "E:/codes/django_learn01/blog/test.py", line 21, in <module>
#     raise TypeError("wrong type.")
# TypeError: wrong type.

```
但是，就本身而言，这并没有多少价值，毕竟可以使用一个更加直观的`try`代替：
``` python
try:
	do something
except ValueError as exc_instance:
	print("ValueError: %s" % exc_instance)

```


## 最后
在网页中，加入如下的`JavaScript`代码，可以实现网页的`伪加密`：
``` JavaScript
<script>
	window.onload=function(){
     var pa = prompt('请输入文章密码');
     if(pa!='123'){
      	history.back();
     }
	};
</script>

```











