---
title: leetcode-977 | 有序数组的平方  
date: 2019-6-3 16:36:46
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 977. 有序数组的平方
给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

><span>例如</span>
输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]
><span>例如</span>
输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]

><span>提示：</span>
* 1 <= A.length <= 10000
* -10000 <= A[i] <= 10000
* A 已按非递减顺序排序。

## 思考
按照题意操作就可以了。

``` python 
class Solution(object):
    def sortedSquares(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        resu = [i*i for i in A]
        resu.sort()
        return resu
```

<span class="title2">结果：</span>
>执行用时 : 240 ms, 在Squares of a Sorted Array的Python提交中击败了97.70% 的用户
内存消耗 : 13.8 MB, 在Squares of a Sorted Array的Python提交中击败了20.62% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>240 ms</td><td>13.8MB</td><td>python</td></tr></table>
## 简化

``` python
class Solution(object):
    def sortedSquares(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        return sorted([x*x for x in A])
        
```
<span class="title2">结果：</span>
>执行用时 : 244 ms, 在Squares of a Sorted Array的Python提交中击败了95.74% 的用户
内存消耗 : 13.7 MB, 在Squares of a Sorted Array的Python提交中击败了27.20% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>244 ms</td><td>13.7MB</td><td>python</td></tr></table>

