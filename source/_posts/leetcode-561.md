---
title: leetcode-561 }  数组拆分 I  
date: 2019-6-3 17:40:01
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 561. 数组拆分 I
给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。

><span>示例 3：</span>
输入: [1,4,3,2]
输出: 4
解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).

><span>提示：</span>
n 是正整数,范围在 [1, 10000].
数组中的元素范围在 [-10000, 10000].


## 思考
由于是取两个数的二元组中的最小值，需要求和的最大值，也就是说当我们需要值最大的时候，而整个2n数组，最大值是一定抛弃的（因为min(a, b)），那么次大值必在其中。
不妨将之排序，从后到前组成二元组，此时绝壁是最大的。

``` python 
class Solution(object):
    def arrayPairSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        if len(nums)<2:
            return sum(nums)

        nums.sort(reverse=True)
        i, j  = 0, 1
        sum = 0
        while j < len(nums):
            m = min(nums[i], nums[j])
            sum += m
            i = j + 1
            j = i + 1
        return sum
```

<span class="title2">结果：</span>
>执行用时 : 536 ms, 在Array Partition I的Python提交中击败了15.09% 的用户
内存消耗 : 14.1 MB, 在Array Partition I的Python提交中击败了35.37% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>536 ms</td><td>14.1MB</td><td>python</td></tr></table>
## 优化
由于我们已经确定了所取的位置的数据，所以我们可以直接取数

``` python
class Solution(object):
    def arrayPairSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        if len(nums)<2:
            return sum(nums)

        nums.sort(reverse=True)
        i =  1
        sum = 0
        while i < len(nums):
            sum += nums[i]
            i += 2
        return sum
```

<span class="title2">结果：</span>
>执行用时 : 384 ms, 在Array Partition I的Python提交中击败了29.24% 的用户
内存消耗 : 14 MB, 在Array Partition I的Python提交中击败了39.74% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>384 ms</td><td>14MB</td><td>python</td></tr></table>

## 再次优化
不难发现，解法还是不好，不难理解因为我们使用的是sort函数
我们不妨反过来理解一下，由于取次大，所以我们需要取最小
故而我们都不需要逆排序，直接排序然后计算就可以了

```python
class Solution(object):
    def arrayPairSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        if len(nums)<2:
            return sum(nums)

        nums.sort()
        i =  0
        sum = 0
        while i < len(nums):
            sum += nums[i]
            i += 2
        return sum
```

<span class="title2">结果：</span>
>执行用时 : 352 ms, 在Array Partition I的Python提交中击败了85.38% 的用户
内存消耗 : 14.1 MB, 在Array Partition I的Python提交中击败了32.31% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>352 ms</td><td>14.1MB</td><td>python</td></tr></table>

