---
title: leetcode-905 | 按奇偶排序数组  
date: 2019-6-3 16:56:19
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 905. 按奇偶排序数组
给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。

你可以返回满足此条件的任何数组作为答案。


><span>示例：</span>
输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。


><span>提示：</span>
1 <= A.length <= 5000
0 <= A[i] <= 5000

## 思考
比较简单，不妨逐位判断，偶数前置。
为了操作方便，不妨将之分成两个部分，用两个列表存储。

``` python 
class Solution(object):
    def sortArrayByParity(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        ou = []
        ji = []
        for i in A:
            if i % 2 == 0:
                ou.append(i)
            else:
                ji.append(i)
        return ou+ji
```

<span class="title2">结果：</span>
>执行用时 : 136 ms, 在Sort Array By Parity的Python提交中击败了19.30% 的用户
内存消耗 : 12.2 MB, 在Sort Array By Parity的Python提交中击败了35.03% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>136 ms</td><td>12.2MB</td><td>python</td></tr></table>
## 简化
``` python
class Solution(object):
    def sortArrayByParity(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        return [i for i in A if i % 2 ==0] + [i for i in A if i % 2 != 0]
```

<span class="title2">结果：</span>
>执行用时 : 92 ms, 在Sort Array By Parity的Python提交中击败了79.82% 的用户
内存消耗 : 12.2 MB, 在Sort Array By Parity的Python提交中击败了36.06% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>92 ms</td><td>12.2MB</td><td>python</td></tr></table>
