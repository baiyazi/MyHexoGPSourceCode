---
title: leetcode-219 | 存在重复元素 II 
date: 2019-5-3 15:09:16
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 滑动窗口典型例子
---

# 题目描述

给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

><span>示例:</span>
输入: nums = [1,2,3,1], k = 3
输出: true

><span>示例:</span>
输入: nums = [1,0,1,1], k = 1
输出: true

><span>示例:</span>
输入: nums = [1,2,3,1,2,3], k = 2
输出: false





# 解答思路
按照题目要求翻译就好。
定义长度为k的窗口，那么我们判断下一个元素是否在窗口中，如果在说明成功；
如果不在窗口中，我们移动窗口。

## 翻译如下：
``` python
class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        #定义滑动窗口，窗口的长度最长是k
        i,j = 0,-1
        se = set()
        while j+1 < len(nums):
            
            #判断nums[j+1]是否在集合中
            if nums[j+1] in se:
                return True
            
            se.add(nums[j+1])
            j+=1
            
            if len(se)==k+1:
                se.remove(nums[i])
                i+=1

        return False
```

<span class="title2">结果：</span>
>执行用时 : 124 ms, 在Contains Duplicate II的Python提交中击败了30.52% 的用户
内存消耗 : 15.8 MB, 在Contains Duplicate II的Python提交中击败了45.39% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>124 ms</td><td>15.8MB</td><td>python</td></tr></table>
---

## 优化
在讨论区看见了这个解法，脑洞很好。
和上面的解法略微不同，判断的是前一个元素是否在窗口中
定义的窗口在后面，判断的元素在后面；和前一种解法正好相反。
``` python
class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        if len(set(nums))==len(nums):
            return False
        for i in range(len(nums)):
            if nums[i] in nums[i+1:i+k+1]:
                return True
        return False

```

<span class="title2">结果：</span>
>执行用时 : 108 ms, 在Contains Duplicate II的Python提交中击败了99.35% 的用户
内存消耗 : 15.8 MB, 在Contains Duplicate II的Python提交中击败了45.39% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>108 ms</td><td>15.8MB</td><td>python</td></tr></table>
