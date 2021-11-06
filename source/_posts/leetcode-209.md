---
title: leetcode-209 | 长度最小的子数组
date: 2019-4-24 16:37:05
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 滑动窗口
---
# 209. 长度最小的子数组（Minimum Size Subarray Sum）
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

><span>Example:</span> 
输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
><span>进阶:</span>
如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。


## 方法一：滑动窗口
这里的窗口就是数组中连续子数组的和>=s，记录窗口大小，然后滑动窗口。找最小窗口。


``` python
class Solution(object):
    def minSubArrayLen(self, s, nums):
        """
        :type s: int
        :type nums: List[int]
        :rtype: int
        """
        #定义窗口[i,j]
        i,j = 0 ,-1 #因为初始窗口应该不包含任何值，故而j=-1,表示当前窗口为None
        sum  = 0 # 记录当前窗口的元素的和
        length = len(nums)+1 #记录当前窗口的长度，初始化为最大值+1，实际取不到

        #只要滑动窗口的左窗口能够取值，右边就能取值，大不了一样
        while i<len(nums):
            if sum < s:
                #[2,3,1,2,4,3]
                if  j+1<len(nums):
                    j+=1
                    sum += nums[j]
                #否则，也就是最后的窗口sum<s，而我们循环退出的条件是i==len(nums),故而我们操作i，使他退出
                else:
                    break
            else:
                length = min(length, j - i + 1)
                #print("i= %s, j= %s, length = %s ,nums[%s]=%s, sum=%s" % (i, j, length, i, nums[i], sum))
                sum -= nums[i]
                i+=1
                
        if length==len(nums)+1:
            return 0
        return length
        
```

><span>结果：</span>
执行用时 : 88 ms, 在Minimum Size Subarray Sum的Python提交中击败了82.19% 的用户
内存消耗 : 13.8 MB, 在Minimum Size Subarray Sum的Python提交中击败了40.00% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>88 ms</td><td>13.8MB</td><td>python</td></tr></table>
但是，上面的代码的滑动窗口的可理解性并不好。我们这里使用使用另一种实现方式滑动窗口。
### 方法二：滑动窗口

<span style="color:red;font-size:20px;border:1px solid #ddd;background-color:#eee;padding:3px;">这里是比较好的滑动窗口的解决方法的案例代码!</span>
```python
class Solution(object):
    def minSubArrayLen(self, s, nums):
        """
        :type s: int
        :type nums: List[int]
        :rtype: int
        """
       #定义滑动窗口[i,j]  i=0,j=-1 表示当前滑动窗口无元素
        i,j = 0, -1
        #窗口的长度,初始长度是len(s)+1，取不到
        lenght = len(nums)+1
        #窗口中数据的和
        sum = 0
        #窗口的前端扫描到末尾为止
        while i<len(nums):
            if j+1<len(nums) and sum < s:
                j+=1 #加入一个元素到窗口中
                sum += nums[j]
            else:
                sum -= nums[i]
                i+=1
            #窗口移动完成，判断是否满足sum>=s，满足就记录长度
            if sum >= s:
                #j-i+1是因为，如果当前窗口是[0],那么窗口大小是1，即j-i+1
                lenght = min(lenght, j-i+1)
        if lenght==len(nums)+1:
            return 0
        return lenght

```
><span>结果：</span>
执行用时 : 160 ms, 在Minimum Size Subarray Sum的Python提交中击败了13.70% 的用户
内存消耗 : 14 MB, 在Minimum Size Subarray Sum的Python提交中击败了18.24% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>160 ms</td><td>14MB</td><td>python</td></tr></table>
