---
title: leetcode-454 | 四数相加 II 
date: 2019-5-1 15:16:40
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---

# 题目描述
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

><span>例如:</span>
输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]
输出:
2
><span>解释:</span>
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

# 思路
由于有个数限制，而且输出的结果是有多少组，而不是返回下标，这里我们可以直接计算值，满足条件，累加计数。

# 解答
## 1. 错误解答
``` python
class Solution(object):
    def fourSumCount(self, A, B, C, D):
        """
        :type A: List[int]
        :type B: List[int]
        :type C: List[int]
        :type D: List[int]
        :rtype: int
        """
        #用列表接收数据
        di = dict()
        count = 0
        for i in A:
            for j in B:
                di[i+j] = di.get(i+j, 0) + 1

        for k in C:
            for l in D:
                if -k-l in di:
                    #因为上面li中可能有相同的值，而li中必是两个不同的i,j位置产生的值，需要统计相同数值个数
                    count += di[-k-l]

        return count

```

><span class="title2">结果：</span>
执行用时 : 360 ms, 在4Sum II的Python提交中击败了75.51% 的用户
内存消耗 : 34.2 MB, 在4Sum II的Python提交中击败了43.48% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>360 ms</td><td>34.2MB</td><td>python</td></tr></table>










