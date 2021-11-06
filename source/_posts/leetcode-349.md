---
title: leetcode-349 | 两个数组的交集
date: 2019-4-25 16:19:06
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---

## 349. 两个数组的交集（Intersection of Two Arrays）

给定两个数组，编写一个函数来计算它们的交集。

><span>示例1：</span>
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
><span>示例2：</span>
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
><span>说明：</span>
输出结果中的每个元素一定是唯一的。
我们可以不考虑输出结果的顺序。



## 思路
### 方法一： 去重，然后判断是否在另一个容器中



``` python
class Solution(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        s1 = set(nums1)
        re = []
        for i in nums2:
            if i in s1:
               re.append(i)
        return list(set(re))   
```

><span>结果：</span>
执行用时 : 80 ms, 在Intersection of Two Arrays的Python提交中击败了18.32% 的用户
内存消耗 : 12 MB, 在Intersection of Two Arrays的Python提交中击败了15.75% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>80 ms</td><td>12MB</td><td>python</td></tr></table>

### 方法二：使用集合特性，取交集


```python 
class Solution(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        return list(set(nums1) & set(nums2))
        
```

><span>结果：</span>
执行用时 : 48 ms, 在Intersection of Two Arrays的Python提交中击败了100.00% 的用户
内存消耗 : 12 MB, 在Intersection of Two Arrays的Python提交中击败了15.34% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>12MB</td><td>python</td></tr></table>
