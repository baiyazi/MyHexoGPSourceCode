---
title: leetcode-442 | 数组中重复的数据  
date: 2019-6-2 18:09:23
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 数组
---
# 442. 数组中重复的数据
给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。

找到所有出现两次的元素。

你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

><span>例如</span>
输入:
[4,3,2,7,8,2,3,1]
输出:
[2,3]



## 思考
比较传统的解法，就是先排序，然后定义前后两个指针，然后判断两个指针的位置之间的距离，然后据此判断是否元素的个数是2.加入结果集合中即可。

``` python
class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        #边界处理
        if len(nums)<2:
            return []
        if len(nums) == 2:
            if nums[0] == nums[1]:
                return [nums[0]]
            else:
                return []

        nums.sort()
        resu = []
        #len > 2
        i = j = 0
        flag = False
        while i < len(nums) and j < len(nums) :
            if nums[i]==nums[j]:
                j+=1
                #由于两个数有可能在最后，所以这里需要一个开关
                flag = True
            else:
                flag = False
                step = j - i
                if step == 2:
                    resu.append(nums[i])
                i = j
        #边界，如：[1, 1, 2, 3, 4, 5, 7, 9, 10, 10]，i指向第一个10，j退出后指向len(nums)位置
        step = j - i
        if flag and step == 2:
            resu.append(nums[i])
        return resu
```

<span class="title2">结果：</span>
>执行用时 : 580 ms, 在Find All Duplicates in an Array的Python提交中击败了21.36% 的用户
内存消耗 : 18.8 MB, 在Find All Duplicates in an Array的Python提交中击败了29.35% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>580 ms</td><td>18.8MB</td><td>python</td></tr></table>
## 另一种思路
换种思路，我们还是使用两个指针，但是此时我们使用一个前，一个后，然后指针同步移动，搭配一个开关，加上一个计数变量，然后就可以做到，代码如下：
``` python
class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        #边界处理
        if len(nums)<2:
            return []
        if len(nums) == 2:
            if nums[0] == nums[1]:
                return [nums[0]]
            else:
                return []

        nums.sort()
        resu = []
        #不妨使用2个指针同步遍历
        i , j = 0, 1
        flag = False
        count = 0
        while j < len(nums):
            if nums[i] == nums[j]:
                flag = True
                count += 1
            else:
                if flag and count < 2:
                    resu.append(nums[i])
                flag = False
                count = 0
            #指针同步移动
            i = j
            j = j + 1

        #边界判断
        if flag and count < 2:
            resu.append(nums[i])

        return resu
```
<span class="title2">结果：</span>
>执行用时 : 500 ms, 在Find All Duplicates in an Array的Python提交中击败了48.54% 的用户
内存消耗 : 19 MB, 在Find All Duplicates in an Array的Python提交中击败了14.13% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>500 ms</td><td>19MB</td><td>python</td></tr></table>

