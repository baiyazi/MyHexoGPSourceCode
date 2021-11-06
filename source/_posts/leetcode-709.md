---
title: leetcode-709 | 转换成小写字母  
date: 2019-6-3 16:26:06
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 字符串
---
# 709. 转换成小写字母
实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。


><span>例如</span>
输入: "Hello"
输出: "hello"

><span>例如</span>
输入: "here"
输出: "here"

><span>例如</span>
输入: "LOVELY"
输出: "lovely"


## 思考
按照题意操作就可以了。

``` python
 
class Solution(object):
    def toLowerCase(self, str):
        """
        :type str: str
        :rtype: str
        """
        resu = ""
        for i in str:

            if not i.islower():
               i = i.lower()
            resu += i
        return resu
```

<span class="title2">结果：</span>
>执行用时 : 24 ms, 在To Lower Case的Python提交中击败了86.17% 的用户
内存消耗 : 11.7 MB, 在To Lower Case的Python提交中击败了28.09% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.7MB</td><td>python</td></tr></table>
## 简化

``` python
class Solution(object):
    def toLowerCase(self, str):
        """
        :type str: str
        :rtype: str
        """
        return str.lower()
        
```
<span class="title2">结果：</span>
>执行用时 : 16 ms, 在To Lower Case的Python提交中击败了99.21% 的用户
内存消耗 : 11.9 MB, 在To Lower Case的Python提交中击败了5.06% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>16 ms</td><td>11.9MB</td><td>python</td></tr></table>

