---
title: leetcode-88 | 合并两个有序数组 
date: 2019-4-23 13:42:04
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 88. 合并两个有序数组（Merge Sorted Array）

给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

><span>说明：</span>
* 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
* 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
><span>示例：</span>
输入：
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
输出: [1,2,2,3,5,6]



## 思路
### 方法一： 暴力法
先将短的列表，并入长的，然后排序一下。

``` python
class Solution(object):
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: None Do not return anything, modify nums1 in-place instead.
        """
        j , i = 1, 0
        while j <= len(nums1):

            if j>m and i<len(nums2):
                nums1[j-1] = nums2[i]
                i+=1
            j+=1
        nums1.sort()
```


><span>结果：</span>
执行用时 : 36 ms, 在Merge Sorted Array的Python提交中击败了99.37% 的用户
内存消耗 : 11.8 MB, 在Merge Sorted Array的Python提交中击败了21.79% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.8MB</td><td>python</td></tr></table>
### 对上面的代码优化
上面的代码，不好之处在于：在 `while j <= len(nums1):`中，跑了空趟子。这里我们直接解决：

``` python
class Solution(object):
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: None Do not return anything, modify nums1 in-place instead.
        """
        i,j = m , 0
        while j<n:
            nums1[i] = nums2[j]
            i+=1
            j+=1

        nums1.sort()
```
><span style="color: green;">结果：</span>
执行用时 : 36 ms, 在Merge Sorted Array的Python提交中击败了99.37% 的用户
内存消耗 : 11.7 MB, 在Merge Sorted Array的Python提交中击败了30.51% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.7MB</td><td>python</td></tr></table>
