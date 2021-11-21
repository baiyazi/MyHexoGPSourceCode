---
title: leetcode-283 | 移动零 
date: 2019-4-23 11:03:14
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 283.移动零（Move Zeroes）

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

><span>示例：</span>
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
<span>Note：</span>
* You must do this in-place without making a copy of the array.
* Minimize the total number of operations.

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
><span>示例：</span>
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
><span>说明：</span>
1.必须在原数组上操作，不能拷贝额外的数组。
2.尽量减少操作次数。

## 思路
### 一、暴力法
采用冒泡思想，如果是0，就向后面冒泡。

``` python
class Solution(object):
    def moveZeroes(self, nums):
        #冒泡
        for i in range(len(nums)):
            j = i+1
            while j<len(nums) and nums[i]==0:
                nums[i],nums[j] = nums[j], nums[i]
                j+=1
```

><span>结果：</span>
成功  
执行用时 : 752 ms, 在Move Zeroes的Python提交中击败了5.43% 的用户
内存消耗 : 12.7 MB, 在Move Zeroes的Python提交中击败了35.84% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>752 ms</td><td>12.7 MB</td><td>python</td></tr></table>
不难看出效果很差。

### 二、使用辅助空间
先将所有的非0元素放置到一个空间中，然后再统一存放，后置填0。
``` python
def moveZeroes(self, nums):
        li = []
        count = 0
        for i in nums:
            if i!=0:
                li.append(i)
            else :
                count+=1
        i = 0
        while i<count:
            li.append(0)
            i+=1
        nums=li
```

但是，原题目要求的是不能拷贝到额外的数组中，故而是不能通过的（这里只做思想的扩展）。

### 二、使用下标，定位判数法
使用两个下标指针i,j，i表示现在需要填充的有效的数据位置，j表示从前到后，扫描到的非0元素。

``` python
class Solution(object):
    def moveZeroes(self, nums):
        i,j = 0,0
        while j<len(nums):
            if nums[j]!=0:
                nums[i] = nums[j]
                i+=1
            j+=1

        while i<len(nums):
            nums[i]=0
            i+=1
```
><span>结果：</span>
执行用时 : 56 ms, 在Move Zeroes的Python提交中击败了71.74% 的用户
内存消耗 : 12.6 MB, 在Move Zeroes的Python提交中击败了37.76% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>56 ms</td><td>12.6 MB</td><td>python</td></tr></table>
简单衍生：
思想还是定插入的位置，然后，将后端j扫描的元素和原来位置中元素交换位置，其实也就是交换0（因为0是导致位置交换的本质原因）。
``` python
class Solution(object):
    def moveZeroes(self, nums):
        i,j = 0,0
        while j<len(nums):
            if nums[j]!=0:
                nums[i],nums[j] = nums[j], nums[i]
                i+=1
            j+=1
```

><span>结果：</span>
执行用时 : 52 ms, 在Move Zeroes的Python提交中击败了99.46% 的用户
内存消耗 : 12.5 MB, 在Move Zeroes的Python提交中击败了39.08% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>52 ms</td><td>12.5 MB</td><td>python</td></tr></table>

发现，提高了一点点。

