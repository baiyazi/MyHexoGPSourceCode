---
title: leetcode-1051 | 高度检查器  
date: 2019-6-3 16:56:19
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 1051. 高度检查器
学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。

请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。

><span>示例：</span>
输入：[1,1,4,2,1,3]
输出：3
解释：
高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。

><span>提示：</span>
1 <= heights.length <= 100
1 <= heights[i] <= 100

## 思考
不妨将用例排序：[1，1，1，2，3，4]
移动最少的人数，使得排列的高度是递增的，故而找到不合法的元素，直接交换。
因为如果学生高度是递增的，在只考虑学生高度的时候，也就是判断是否在最终位置上。
如果在最终位置上，我们不需要移动元素，而移动的元素一定是不在最终位置上，也就是需要移动的人数。
这里我们亦可以使用排序后的列表，和原来的列表进行比较。
不同的位置就是需要移动的位置

``` python 
class Solution(object):
    def heightChecker(self, heights):
        """
        :type heights: List[int]
        :rtype: int
        """
        count = 0
        cop = heights.copy()
        cop.sort()
        j = 0
        for i in cop:
            if i!=heights[j]:
                count += 1
            j += 1
        return count
```

<span class="title2">结果：</span>
>执行用时 : 44 ms, 在Height Checker的Python3提交中击败了88.89% 的用户
内存消耗 : 13.1 MB, 在Height Checker的Python3提交中击败了100.00% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>13.1MB</td><td>python</td></tr></table>
