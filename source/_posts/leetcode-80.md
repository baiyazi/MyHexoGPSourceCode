---
title: leetcode-80 | 删除排序数组中的重复项 II 
date: 2019-4-23 13:42:04
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---
## 26. 删除排序数组中的重复项II（Remove Duplicates from Sorted Array II）

给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
><span>示例 1：</span>
给定 nums = [1,1,1,2,2,3],
函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
你不需要考虑数组中超出新长度后面的元素。
><span>示例 2：</span>
给定 nums = [0,0,1,1,1,1,2,3,3],
函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
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
还是使用两个下标，i表示有效位置，j表示扫描过程中，有效的值。
使用count作为计数变量，count<2  可取值0 1 也即是，同一个数最多出现两次。
和26题有一定的相似度，但是，不同之处在于判别的方式。
<span style="color:red;">这里的判断是判别这个元素和相邻的前一个元素是否相同</span>，相同则考察计数变量count的值，而有效值
始终都在有效的位置猫着。

![hello](/images/201904/2019-04-23_162634.jpg "初始位置图示")
``` python
class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        i ,j = 1, 1
        count = 0
        while j < len(nums):
            if nums[j] == nums[j - 1]:
                count += 1
                if count < 2:
                	#有效位置=有效数值
                    nums[i] = nums[j]
                    i += 1
            else:
                count = 0
                nums[i] = nums[j]
                i += 1
            j += 1
        
        return i
```


><span>结果：</span>
执行用时 : 60 ms, 在Remove Duplicates from Sorted Array II的Python提交中击败了93.33% 的用户
内存消耗 : 11.8 MB, 在Remove Duplicates from Sorted Array II的Python提交中击败了33.47% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>60 ms</td><td>11.8MB</td><td>python</td></tr></table>
