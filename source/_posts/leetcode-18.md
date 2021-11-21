---
title: leetcode-18 | 四数之和  中等难度
date: 2019-5-19 17:50:20
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 数组
    - 没做出来
---
# 16. 最接近的三数之和
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

<span>注意：</span>
答案中不可以包含重复的四元组。

><span>例如</span>
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

## 思考
类似16，我们考虑到不重复，所以还是使用首尾指针方式

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



