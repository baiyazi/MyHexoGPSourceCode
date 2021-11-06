---
title: leetcode-1 | Two Sum 简单难度
date: 2019-04-18 17:31:55
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 1. 两数之和（Two Sum）
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
><span>示例:</span>
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

解答区：
首先，不同于C或者Java中的数组，但也希望能够一次遍历获取列表的下标和值：

``` python
for i, j in range(len(nums)), nums:
```
但是，实际上是错误的语法。在Python中提供了这样的一个循环：
``` python
nums = [2, 7, 11, 15]
for i,j in enumerate(nums):
    print(i,j)
```
然后，我们可以编写解决问题的代码了：
## 解答

``` python
class Solution:
    """
    功能：找num在列表nums中的下标位置
          不存在，返回-1
    """
    def findIndex(self, num, nums):
        for i,j in enumerate(nums):
            if num == j:
                return i
        return -1

    def twoSum(self, nums, target):
        for i ,j in enumerate(nums):
             r = target - j
             if r in nums:
                 ##这里，需要考虑到不能重复
                 if self.findIndex(r, nums)!=-1 and i!=self.findIndex(r, nums):
                     return [i, self.findIndex(r, nums)]
        return []

if __name__ == '__main__':
    nums = [3,2,4]
    target = 6
    a = Solution()
    b = a.twoSum(nums=nums, target=target)
    print(b)
```

