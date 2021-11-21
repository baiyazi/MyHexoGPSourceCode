---
title: leetcode-16 | 最接近的三数之和  中等难度
date: 2019-5-19 17:50:20
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 数组
---
# 16. 最接近的三数之和

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

><span>例如</span>
>给定数组 nums = [-1，2，1，-4], 和 target = 1.
与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

## 思考
由于我们取的三个数肯定是不相同的，所以，我们可以思考用双重循环，使用三指针遍历
也就是，第二重循环中使用首尾指针，第一重循环遍历到数组尾部，就可以保证三个数是不同的。
i = 0
j = i+1
k = len(nums) - 1

``` python
class Solution(object):
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        if len(nums)<3:
            return
        if len(nums)==3:
            return nums[0]+nums[1]+nums[2]

        resu = nums[0]+nums[1]+nums[2]
        i = 0
        nums.sort()
        while i < len(nums):
            j = i+1
            k = len(nums) - 1
            while j < k:
                sum = nums[i] + nums[j] + nums[k]
                if (abs(sum - target) < abs(resu - target)):
                    resu = sum
                if sum < target:
                    j += 1
                elif sum > target:
                    k -= 1
                else:
                    #sum == target
                    return target
            i+=1
        return resu
```

<span class="title2">结果：</span>
>执行用时 : 160 ms, 在3Sum Closest的Python提交中击败了30.13% 的用户
内存消耗 : 11.5 MB, 在3Sum Closest的Python提交中击败了41.60% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>160 ms</td><td>11.5MB</td><td>python</td></tr></table>



