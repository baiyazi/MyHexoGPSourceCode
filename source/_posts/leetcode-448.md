---
title: leetcode-448 | 找到所有数组中消失的数字  
date: 2019-6-2 18:10:15
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 448. 找到所有数组中消失的数字
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。
您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

><span>例如</span>
输入:
[4,3,2,7,8,2,3,1]
输出:
[5,6]



## 思考
很简单的题目，但是关键是不要使用额外的空间。
在解答区，看见了下面两个比较好的解题方法。值得学习。

``` python
class Solution(object):
    def findDisappearedNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        s = set(nums)
        return [i for i in range(1, len(nums) + 1) if i not in s]
        
```
<span class="title2">结果：</span>
>执行用时 : 416 ms, 在Find All Numbers Disappeared in an Array的Python提交中击败了98.48% 的用户
内存消耗 : 21.2 MB, 在Find All Numbers Disappeared in an Array的Python提交中击败了8.02% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>416 ms</td><td>21.2MB</td><td>python</td></tr></table>
## 方式二
集合特性，很牛皮的解答方式。

``` python
class Solution(object):
    def findDisappearedNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        return list(set(range(1, len(nums) + 1)) - set(nums))    
```
<span class="title2">结果：</span>
>执行用时 : 412 ms, 在Find All Numbers Disappeared in an Array的Python提交中击败了99.49% 的用户
内存消耗 : 20.5 MB, 在Find All Numbers Disappeared in an Array的Python提交中击败了17.90% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>412 ms</td><td>20.5MB</td><td>python</td></tr></table>

