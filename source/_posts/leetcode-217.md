---
title: leetcode-217 | 存在重复元素 
date: 2019-5-3 16:06:51
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 滑动窗口
---

# 题目描述

给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

><span>示例:</span>
输入: [1,2,3,1]
输出: true

><span>示例:</span>
输入: [1,2,3,4]
输出: false

><span>示例:</span>
输入: [1,1,1,3,3,4,3,2,4,2]
输出: true




# 解答思路
转数据成不含重复元素的集合，如果长度不变，无重复元素；反之有重复元素。

## 翻译如下：
``` python
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(set(nums))==len(nums):
            return False
        return True
```

<span class="title2">结果：</span>
>执行用时 : 156 ms, 在Contains Duplicate的Python提交中击败了59.00% 的用户
内存消耗 : 17.2 MB, 在Contains Duplicate的Python提交中击败了21.95% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>156 ms</td><td>17.2MB</td><td>python</td></tr></table>

## 优化解答
``` python
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        return not len(set(nums))==len(nums)
```

<span class="title2">结果：</span>
>执行用时 : 152 ms, 在Contains Duplicate的Python提交中击败了95.73% 的用户
内存消耗 : 17.1 MB, 在Contains Duplicate的Python提交中击败了31.28% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>152 ms</td><td>17.1MB</td><td>python</td></tr></table>

---

下面是一些尝试：
## 无限长度滑动窗口

``` python
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        #定义滑动窗口
        se = set()
        i, j = 0, -1
        while j+1 < len(nums):
            #是否在窗口中
            if nums[j+1] in se:
                return True
            #窗口后移
            se.add(nums[j+1])
            j+=1
        return False

```

<span class="title2">结果：</span>
>执行用时 : 164 ms, 在Contains Duplicate的Python提交中击败了29.62% 的用户
内存消耗 : 17.2 MB, 在Contains Duplicate的Python提交中击败了27.70% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>164 ms</td><td>17.2MB</td><td>python</td></tr></table>
## 使用集合判断个数
``` python
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        se = set()
        for i in nums:
            if i not in se:
                se.add(i)
            else:
                return True
        return False
```
<span class="title2">结果：</span>
>执行用时 : 160 ms, 在Contains Duplicate的Python提交中击败了36.97% 的用户
内存消耗 : 17 MB, 在Contains Duplicate的Python提交中击败了34.77% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>160 ms</td><td>17MB</td><td>python</td></tr></table>

