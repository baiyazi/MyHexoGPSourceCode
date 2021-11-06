---
title: leetcode-75 | 颜色分类 
date: 2019-4-23 13:42:04
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---
## 75. 颜色分类（Sort Colors）

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
><span>注意：</span>
不能使用代码库中的排序函数来解决这道题。

><span>示例：</span>
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
><span>进阶：</span>
* 一个直观的解决方案是使用计数排序的两趟扫描算法。
* 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
* 你能想出一个仅使用常数空间的一趟扫描算法吗？


## 思路
### 方法一： 暴力法
也就是包含大量重复元素的列表（或数组）的排序。这里写一个冒泡排序以实现效果：

``` python
class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        #j是最终的元素的位置
        j = len(nums)-1
        while j>=0:  
            i = 0
            #每一趟，找最大值，放在最终的位置，冒泡
            while i<j:
                if nums[i]>nums[j]:
                    nums[j],nums[i] = nums[i],nums[j]
                i+=1
            j-=1
```


><span>结果：</span>
执行用时 : 44 ms, 在Sort Colors的Python提交中击败了21.21% 的用户
内存消耗 : 11.6 MB, 在Sort Colors的Python提交中击败了35.81% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>11.6MB</td><td>python</td></tr></table>
### 方法二：计数排序
由于数组中n个元素，元素是有限的，不妨用遍历的方式，分别记录数组中各个数的个数，然后我们还原原来数组即可。也就是计数排序。
``` python
class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        zc,oc,tc = 0,0,0
        for i in nums:
            if i==0:
                zc += 1
            elif i==1:
                oc += 1
            elif i==2:
                tc += 1
        i = 0
        while i<zc:
            nums[i] = 0
            i+=1
        while i<oc+zc:
            nums[i] = 1
            i+=1
        while i<tc+oc+zc:
            nums[i] = 2
            i += 1
```
><span style="color: green;">结果：</span>
执行用时 : 36 ms, 在Sort Colors的Python提交中击败了50.76% 的用户
内存消耗 : 11.7 MB, 在Sort Colors的Python提交中击败了25.62% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.7MB</td><td>python</td></tr></table>
### 方法三：‘三路快排’
定义两个有效的位置，然后遍历取有效数值，放置到有效位置
分为==1，大于1和小于1
排序的过程，不妨思考二路快排是如何实现的。
这里，我们使用i，k来表示元素前半段和后半段的有效位置，然后我们用j来循环遍历我们的数组，在循环中判断元素的类型，是==1还是大于1或者是小于1，然后分别放置到有效位置中，0放置在前半段，2放置在后半段

```python
class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        i, j, k = 0,0, len(nums)-1
        while j<=k:
            if nums[j]==1:
                j+=1
            elif nums[j]==2:
                nums[j],nums[k]=nums[k],nums[j]
                k-=1
            elif nums[j]==0:
                nums[i],nums[j] = nums[j], nums[i]
                i+=1
                j+=1
```

><span style="color: green;">结果：</span>
执行用时 : 36 ms, 在Sort Colors的Python提交中击败了50.76% 的用户
内存消耗 : 11.7 MB, 在Sort Colors的Python提交中击败了30.30% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.7MB</td><td>python</td></tr></table>

