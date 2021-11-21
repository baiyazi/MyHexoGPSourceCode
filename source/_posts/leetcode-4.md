---
title: leetcode-4 | 寻找两个有序数组的中位数 中等难度
date: 2019-4-20 09:22:09
comments: true
categories: "leetcode"
tags: 
    - leetcode 困难难度
    - 数组
---
## 4.寻找两个有序数组的中位数（Median of Two Sorted Arrays）

><span>前言</span>
由于，我感觉代码框不怎么好看，然后想自定义一个背景。然后就试着去写了下：
＜link href="/css/blockquote.css" rel="stylesheet" type="text/css" ＞
在source创建了css文件夹，然后创建了相应的css文件。
在引用中加入span标签，写结果，就可以用span的样式（加粗）效果。

There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.



><span>Example 1:</span>
nums1 = [1, 3]
nums2 = [2]
The median is 2.0
><span>Example 2:</span>
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5


给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。

><span>示例 1:</span>
nums1 = [1, 3]
nums2 = [2]
则中位数是 2.0

## 思路
直接用将两个列表合并，排序后，找到下标位置。然后返回。
><span>列表合并有两种方式：</span>
* nums = nums1 + nums2 生成新的对象nums
*  nums1.extend(nums2)  在原有的nums1上扩充


``` python
def findMedianSortedArrays(self, nums1, nums2):
        
        #列表合并
        nums = sorted(nums1+nums2)
        if len(nums)%2 ==0:
            answer = (nums[int(len(nums)/2-1)]+nums[int(len(nums)/2)])/2
        else:
            answer = nums[int((len(nums)+1)/2)-1]
        return answer
```

leetcode官网上给了一个解决问题的思路，还没怎么看懂，以后再看。



