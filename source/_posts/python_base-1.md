---
title: Python编程入门
date: 2019-9-11 09:54:12
comments: true
categories: "《Python编程从入门到实践》读书笔记"
tags: 
  - Python
---
由于如何安装网上教程一大堆，这里就不说了。
## 字符串的常见操作
1. 修改字符串的大小写
* `title()`以首字母大写的方式显示每个单词，即将每个单词的首字母都改为大写。
如：`"good morning".title()`   ` =>` ` Good Morning`
* `upper()`全部大写，如：`"good morning".upper()`   ` =>` ` GOOD MORNING`
* `lower()`全部小写，如：`"GOOD MORNING".lower()`   ` =>` `good morning`

2. 合并（拼接）字符串
使用加号（+）来合并字符串。
3. 使用制表符或换行符来添加空白
如：`"Languages:\nPython\nC\nJavaScript"`
4. 删除空白
`Python`能够找出字符串开头和末尾多余的空白。要确保字符串末尾没有空白，可使用方法
`rstrip()`；开头没有空白，可使用方法`lstrip()`；还有一个去除两端的`strip()`。
由于字符串是不可变变量，故而生成新字符串，而不是修改原有字符串。

## 列表的常见操作
1. 修改列表元素
列表是可变类型，故而直接用等号赋值修改值即可。
如： 
``` python
a = [2,"str", 3]
a[0] = "good"
print(a)          #['good', 'str', 3]
```

2. 在列表中添加元素
* `append(obj)`在列表末尾添加元素。
如：`['good', 'str', 3].append(5)` ` =>`  `['good', 'str', 3, 5]`
* `insert(index, obj)`可在列表的任何位置添加新元素。
如：`['good', 'str', 3].insert(0, "first")`  ` =>`  `['first', good', 'str', 3]`

3. 从列表中删除元素
* 使用`del(index)`语句删除元素。
如：
``` python
motorcycles = ['honda', 'yamaha', 'suzuki']
print(motorcycles)  # ['honda', 'yamaha', 'suzuki']
del motorcycles[0]
print(motorcycles)  # ['yamaha', 'suzuki']
```

* 使用方法`pop()`删除末尾的元素，并返回被删除元素。
* `pop(index)`可以删除列表中任何位置的元素，并返回被删除元素。
* `remove(value)`根据值删除元素。
如：`['good', 'str', 3, 5].remove("str")`  ` =>`  `['good', 3, 5]`

4. 排序
* `sort()`对列表进行永久性排序
列表值可修改，故而可以将排序的结果覆盖到原列表中。
还可以按与字母顺序相反的顺序排列列表元素，为此，只需向`sort()`方法传递参数
`sort(reverse=True)`。
* `sorted()`对列表进行临时排序
它保留列表元素原来的排列顺序，同时以特定的顺序呈现它们（返回排序的结果）。
如果你要按与字母顺序相反的顺序显示列表，也可向函数`sorted()`传递参数`reverse=True`。

5. 反转
reverse()可以反转列表元素的排列顺序，永久性的。
如：
`['bmw', 'audi', 'toyota', 'subaru']`  `=>`  `['subaru', 'toyota', 'audi', 'bmw']`

6. 列表的长度
`len()`可快速获悉列表的长度。

7. 遍历列表
* `for-in`循环，直接操作
如：
``` python
for ele in [1,2,3]:
	pass
```

* `for-in`循环，取小标操作
如：
``` python
a = [1,2,3]
for ele in range(len(a)):
	pass
```

>range()创建数字列表，案例如下：
print(type(range(3))) # <class 'range'>
print(range(3)) # range(0, 3)
print(type(list(range(3))))  # <class 'list'>
print(list(range(3)))  # [0, 1, 2]

8. 列表解析
将`for`循环和创建新元素的代码合并成一行，并自动附加新元素。
如：[x*2 for x in [1, 2, 3, 4]]    =>  [2, 4, 6, 8]

9. 切片
首先，列表和字符串的下标从前到后从0开始，依次是`0, 1, 2, 3...`；从后到前从-1开始，依次是`-1, -2, -3,...`。
`[a:b]`，起始位置是`a`，不包括`b`的区间，即：`[a, b)`。如：
``` python
a = [1,2,3,4,5,6]
print(a[0:3])  # [1, 2, 3]
```

如果你没有指定第一个索引，`Python`将自动从列表开头开始，如`[:3]`，即区间是`[0:3)`。
同理，如果你没有指定最后一个索引，`Python`将自动从列表的表尾的所有元素。

10. 复制列表
结合前面的切片，我们可以截取整个列表，也就是复制了列表。就可以同时省略起始索引和终止索引。如：
``` python
a = [1,2,3,4,5,6]
print(a[:])  # [1,2,3,4,5,6]
```

我们来看一个失败的案例：
``` python
a = [1,2,3,4,5,6]
copy_a = a
a[0] = "Error"
print(copy_a)   # ['Error', 2, 3, 4, 5, 6]
```

也不难理解，因为列表是可变类型，也就相当于指针，故而修改是作用在本身的内存中。
当然，也有相应的`copy()`函数，如下：
``` python
a = [1,2,3,4,5,6]
copy_a = a.copy()
a[0] = "Error"
print(copy_a)   # [1, 2, 3, 4, 5, 6]
```



## 元组
和列表类似，但是是不可变的类型。可以看成是不可变的列表。用圆括号来标识。
虽然不能修改元组的元素，但可以给存储元组的变量赋值。如：
``` python
a = (2, 3, 4)
print(type(a))  #<class 'tuple'>
a = (1, 2)
print(a)   # (1, 2)
```

但貌似没有什么意义。

## 字典
字典是一系列键—值对。如：`a = {"color":"red", "age":"24"}`
1. 添加元素
如：
``` python
a = {}
print(type(a))  # <class 'dict'>
a['age'] = 24
print(a) # {'age': 24}
```

2. 删除元素
如：
``` python
a = {}
print(type(a))  # <class 'dict'>
a['age'] = 24
del a['age']
print(a) # {}
```

3. 遍历字典
* `for-in`循环，结合`items()`
如：
``` python
a = {'age':24, 'name':'Tom'}
for key, value in a.items():
    print(key, value)
# age 24
# name Tom
```

* `for-in`循环，结合`keys()`
如：
``` python
a = {'age':24, 'name':'Tom'}
for key in a.keys():
    print(key , a[key])
# age 24
# name Tom
```

* 当然，也可以直接遍历所有的值
``` python
a = {'age':24, 'name':'Tom'}
for v in a.values():
    print(v)
# 24
# Tom
```

## 类编码风格
类名应采用驼峰命名法，即将类名中的每个单词的首字母都大写，而不使用下划线。实例名和模块名都采用小写格式，并在单词之间加上下划线。
对于每个类，都应紧跟在类定义后面包含一个文档字符串。这种文档字符串简要地描述类的
功能，并遵循编写函数的文档字符串时采用的格式约定。每个模块也都应包含一个文档字符串，
对其中的类可用于做什么进行描述。可使用空行来组织代码，但不要滥用。在类中，可使用一个空行来分隔方法；而在模块中，可使用两个空行来分隔类。

## 文件和异常
### 读取文件操作
1. 读取整个文件
如：
``` python
with open("f:/a.text") as f:
    contents = f.read()
    print(contents)
# 3.234
# 4565656
# 12.23
```

2. 逐行读取
``` python
with open("f:/a.text") as f:
    for line in f:
        print(line.strip())
# 3.234
# 4565656
# 12.23
```

加入`strip()`，主要是去除尾部文件的换行。不加会出现空白行。
为何会出现空白行呢？因为在这个文件中，每行的末尾都有一个看不见的换行符，而`print`语句也会加上一个换行符，因此每行末尾都有两个换行符：一个来自文件，另一个来自`print`语句。

3. 使用readlines读取所有的行到一个列表中
``` python
with open("f:/a.text") as f:
    lines = f.readlines()
for line in lines:
    print(line.strip())
# 3.234
# 4565656
# 12.23
```

### 写入文件操作
1. 写入空文件（对应模式：`w  ->  write`）
``` python
with open("f:/b.text", 'w') as f:
    f.write("I'm so tired.")
```

然后在F盘中就可以看见相应的文件以及其内容。

2. 追加到已经存在的文件（对应模式：`a  ->  append`）
``` python
with open("f:/b.text", 'a') as f:
    f.write("\nAre you ok?")
```

文件原来的内容还在，它们后面是我们刚添加的内容。

### 异常
`Python`不知所措的错误时，它都会创建一个异常对象。如果你编写了处理该异常的代码，程序将继续运行；如果你未对异常进行处理，程序将停止，并显示一个`traceback`，其中包含有关异常的报告。
异常是使用`try-except`代码块处理的。`try-except`代码块让`Python`执行指定的操作，同时告
诉`Python`发生异常时怎么办。
1. 处理`ZeroDivisionError` 异常
``` python
try:
    a = 5 / 0
except:
    print("Error:can't divide by zero!")
```

2. `else`代码块
通过将可能引发错误的代码放在`try-except`代码块中，可提高这个程序抵御错误的能力。
错误是执行除法运算的代码行导致的，因此我们需要将它放到`try-except`代码块中。依赖于`try`代码块成功执行的代码都应放到`else`代码块中。看看下面的具体的用法：
``` python
try:
    a = 5 / 2
except:
    print("Error:can't division zero!")
else:
    print(a)
```

结果：2.5

其余的异常就不介绍了。