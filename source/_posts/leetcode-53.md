---
title: leetcode-53 | 最大子序和  
date: 2019-9-12 10:39:51
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 动态规划法
    - 分治法
---
## 题目描述
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

><span>示例：</span>
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

>><span>进阶：</span>
如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。


## 思路解答
我没有思路，然后百度了一下，看见[这篇博客](https://blog.csdn.net/weixin_42130471/article/details/81037849)中用的动态规划和分治法解决问题。这里就看动态规划法，思想这里就拷贝过来了，如下：
>动态规划：很简单，定义一个数组，dp[]，dp[i]以第i个元素为结尾的一段最大子序和。求dp[i]时，假设前面dp[0]~dp[i-1]都已经求出来了，dp[i-1]表示的是以i-1为结尾的最大子序和，若dp[i-1]小于0，则dp[i]加上前面的任意长度的序列和都会小于不加前面的序列（即自己本身一个元素是以自己为结尾的最大自序和）。举个例子：如-2，1，-3，4数组，dp[0]=-2；dp[1]=1(因为前一个dp[0]=-2<0，即（-2，1）子序和为-1，一个元素（1）子序和为1）；dp[2]=dp[1]+nums[2]=1+(-3)=-2；dp[3]=4，因为dp[2]<0；

然后用了python来实现，如下：

``` python
class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        current = before = nums[0]
        maxsum = nums[0]
        for i in range(1, len(nums)):
            if before < 0:
                before = current = nums[i] # 置于同一位置
            else:
                current = before + nums[i]
                before = current  # 下一次判断的before
            maxsum = max(current, maxsum)
        return maxsum
        
```


结果：
![](/images/201909/2019-09-12_103922.png)