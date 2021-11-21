---
title: leetcode-27 | Remove Element 简单难度
date: 2019-4-23 12:04:56
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 27. 移除元素（Remove Element）

给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

><span>示例 1：</span>
给定 nums = [3,2,2,3], val = 3,
函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
你不需要考虑数组中超出新长度后面的元素。
><span>示例 2：</span>
给定 nums = [0,1,2,2,3,0,4,2], val = 2,
函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
><span>注意：</span>
这五个元素可为任意顺序。
你不需要考虑数组中超出新长度后面的元素。
><span>说明：</span>
为什么返回数值是整数，但输出的答案是数组呢?
请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
你可以想象内部操作如下:
// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);
// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}


## 思路
### 方法一： 定有效位置，放置有效元素
很容易就能想到，使用i，j两个下标指针，i表示当前的有效位置，j用来遍历扫描整个列表，直到不是目标元素，即是有效元素。然后将有效元素放入有效的位置即可，有效位置和有效元素之间可以直接交换。

``` python
class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        i, j = 0, 0
        while j<len(nums):
            if nums[j]!=val:
                nums[i], nums[j] = nums[j], nums[i]
                i+=1
            j+=1
        return i
        
```

><span>结果：</span>
执行用时 : 32 ms, 在Remove Element的Python提交中击败了100.00% 的用户
内存消耗 : 11.7 MB, 在Remove Element的Python提交中击败了36.52% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.7 MB</td><td>python</td></tr></table>

