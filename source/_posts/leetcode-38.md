---
title: leetcode-38 | 报数  简单难度
date: 2019-5-16 08:27:30
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 字符串
---
# 题目描述
报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：

>1.     1
2.     11
3.     21
4.     1211
5.     111221


1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
注意：整数顺序将表示为一个字符串。


# 思路解答
采用循环计数，然后取当前的值和数字的个数，组成这一组相同的元素的报数，如：
11112
当前的数值是1，然后循环计数1有4个，故而我们组成：41
当前的数值是2，然后循环计数2有1个，故而我们组成：12
也即是：4112

``` python
class Solution(object):
    def calc(self, s):
        i, j = 0, 0
        count = 0
        temp = ""
        while j < len(s):
            while j < len(s) and s[i] == s[j]:
                count += 1
                j += 1
            temp += str(count) + "" + s[i]
            i = j
            count = 0
        return temp
    
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        s = "1"
        for i in range(n-1): #n-1是因为我的初始值是1，此时n=1是进入循环，变成了11，很明显这是第二项的值。
            s = self.calc(s)
        return s
```


<span class="title2">结果：</span>
>执行用时 : 24 ms, 在Count and Say的Python提交中击败了100.00% 的用户
内存消耗 : 11.5 MB, 在Count and Say的Python提交中击败了36.80% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.5MB</td><td>python</td></tr></table>
