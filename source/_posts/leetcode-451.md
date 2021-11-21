---
title: leetcode-451 | 根据字符出现频率排序 
date: 2019-4-26 09:20:58
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - lambda
---

## 451. 根据字符出现频率排序（Sort Characters By Frequency）

给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

><span>示例1：</span>
输入:
"tree"
输出:
"eert"
><span>解释：</span>
'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。


><span>示例2：</span>
输入:
"cccaaa"
输出:
"cccaaa"
><span>解释：</span>
'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
注意"cacaca"是不正确的，因为相同的字母必须放在一起。


><span>示例3：</span>
输入:
"Aabb"
输出:
"bbAa"
><span>解释：</span>
此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
注意'A'和'a'被认为是两种不同的字符。






## 思路
### 方法一： 统计计数，然后排列


``` python
class Solution(object):
    def frequencySort(self, s):
        """
        :type s: str
        :rtype: str
        """
        #数据装入字典中
        sd = dict()
        for i in s:
            sd[i] = sd.get(i,0) + 1
        #想办法对字典进行排序
        re = sorted(zip(sd.values(), sd.keys()))  #[(1, 'e'), (2, 'g')]
        #上面是按照频次从大到小排序，反转一下
        re.reverse()
        #结果字符串
        result = ""
        for tuple in re:
            #字符串拼接
            result += tuple[1]*tuple[0]
        return result
        

```

><span style="color:red;">结果：</span>
执行用时 : 60 ms, 在Sort Characters By Frequency的Python提交中击败了69.70% 的用户
内存消耗 : 14.2 MB, 在Sort Characters By Frequency的Python提交中击败了30.00% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>60 ms</td><td>14.2MB</td><td>python</td></tr></table>

### 简化代码

```python
class Solution(object):
    def frequencySort(self, s):
        """
        :type s: str
        :rtype: str
        """
        #数据装入字典中
        sd = dict()
        for i in s:
            sd[i] = sd.get(i,0) + 1
        #想办法对字典进行排序
        result = ""
        for i, n in sorted(sd.items(), key=lambda e: e[1], reverse=True):
            result += i*n

        return result
```

><span style="color:red;">结果：</span>
执行用时 : 64 ms, 在Sort Characters By Frequency的Python提交中击败了63.64% 的用户
内存消耗 : 14.2 MB, 在Sort Characters By Frequency的Python提交中击败了30.00% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>64 ms</td><td>14.2MB</td><td>python</td></tr></table>
代码虽然简化了，但是它执行的时间却略微增加了一点。所以，减不简化看自己喜好。


而且，这里的lambda函数还是值得学习的。

---

## lambda函数
匿名函数`lambda`，匿名函数顾名思义就是指：是指一类无需定义标识符（函数名）的函数或子程序。在`C++11`和`C#`中都有匿名函数的存在。下面看看在`python`中匿名函数的使用。
* `lambda`只是一个表达式，函数体比`def`简单很多；
* `lambda`的主体是一个表达式，而不是一个代码块。仅仅能在`lambda`表达式中封装有限的逻辑进去；
* `lambda`表达式是起到一个函数速写的作用，允许在代码内嵌入一个函数的定义。

<span style="color:red;font-weight: bold;">一个简单的lamdba函数：</span>

``` python
f = lambda x,y,z:x+y+z
    print(f(1,85,4))
```

><span>思绪回到上面代码中的情况：</span>
>key=lambda e: e[1]
>而key需要的是一个callable类型的对象，其实也就是一个函数。（或者是一个类实现了callable接口)
也就是说key需要的就是一个函数。
可以翻译成：`按照什么关键字从大到小排序？`
<span style="color:red;font-weight: bold;">等价写法：</span>
```python
def compare(self, sd):
    return sd[1]

#在应用出替换之前的key=lambda e:e[1]
key=self.compare
```

这里之前的`sorted(sd.items(), key=lambda e: e[1], reverse=True)`不妨这样理解：
匿名函数，使用的时候参数针对的是调用函数的对象，也就是我们的`sorted`函数调用它，就由`sorted`函数来隐式的传入参数，而`sorted`函数，我们传入的数据对象只有`sd.items()`得到的是一个列表。
不妨输出`sd.items()`验证一下，结果是：`dict_items([('e', 1), ('g', 2)])`
也就是一个`key`，`value`的元组组成的列表。
那么，也就前后呼应了。`lambda`中传入的参数e，`return`的是`e[1]`，也就是字母的次数。


