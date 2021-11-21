---
title: leetcode-26 | 删除排序数组中的重复项
date: 2019-4-23 13:22:29
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 26. 删除排序数组中的重复项（Remove Duplicates from Sorted Array）

给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
><span>示例 1：</span>
给定数组 nums = [1,1,2], 
函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
你不需要考虑数组中超出新长度后面的元素。
><span>示例 2：</span>
给定 nums = [0,0,1,1,1,2,2,3,3,4],
函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
你不需要考虑数组中超出新长度后面的元素。
><span>说明</span>
为什么返回数值是整数，但输出的答案是数组呢?
请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
你可以想象内部操作如下:
// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);
// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}



## 思路
### 方法一： 定有效位置，放置有效元素
很容易就能想到，使用i，j两个下标指针，i表示当前的有效位置，j用来遍历扫描整个列表，不是重复元素。然后将有效位置后移动一个位置（即，不重复元素放置的位置），将有效元素放入有效的位置即可。

``` python
class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        i,j = 0, 0
        while j<len(nums):
            if nums[j]!=nums[i]:
                i+=1
                nums[i] = nums[j]
            j+=1
        return i+1
        
```

><span>结果：</span>
执行用时 : 124 ms, 在Remove Duplicates from Sorted Array的Python提交中击败了41.36% 的用户
内存消耗 : 13.6 MB, 在Remove Duplicates from Sorted Array的Python提交中击败了22.21% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>124 ms</td><td>13.6MB</td><td>python</td></tr></table>
但是，自我感觉这个程序还是有问题，如果列表是空的时候，返回1显然不合适，所以我这里添加了边界判断：

```python
class Solution(object):
    def removeDuplicates(self, nums):
        i=0
        if len(nums)==0: 
            return 0
        elif len(nums)==1:
            return 1
        else:
            j=1
            while j<len(nums):
                if nums[i]!=nums[j]:
                    i+=1
                    nums[i]=nums[j]
                j+=1
                
        return i+1
```
><span>结果：</span>
执行用时 : 100 ms, 在Remove Duplicates from Sorted Array的Python提交中击败了58.41% 的用户
内存消耗 : 13.5 MB, 在Remove Duplicates from Sorted Array的Python提交中击败了28.79% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>100 ms</td><td>13.5MB</td><td>python</td></tr></table>
