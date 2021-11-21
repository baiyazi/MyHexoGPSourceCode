---
title: leetcode-167 | 两个数之和II-输入有序数组 
date: 2019-4-24 10:12:03
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 167. 两数之和 II - 输入有序数组（ Two Sum II - Input array is sorted）

给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

><span>说明：</span>
返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
><span>示例：</span>
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。



## 思路
### 方法一： 去重后直接查找
由于答案唯一，那么我们可以使用集合，去除重复元素，然后找到目标分解值，然后找位置，可以用二分查找。

``` python
class Solution(object):
    def find_ele(self, nums, k):
        low, high = 0, len(nums)-1
        while low<=high:
            # 0 1 2 3 4 5 6 7
            mid = (low+high)//2
            if nums[mid]==k:
                return mid
            elif nums[mid]>k:
                high = mid - 1
            elif nums[mid]<k:
                low = mid + 1


    def twoSum(self, numbers, target):
        """
        :type numbers: List[int]
        :type target: int
        :rtype: List[int]
        """
        se = set(numbers)
        n = 0
        for i in se:
            n = target - i
            if n in se:
                break
        one = self.find_ele(numbers, n)
        two = self.find_ele(numbers, i)
        [0,0]
        #如果可以拆分成：两个相等的数字
        if one==two:
            if one==0:
                return [one+1, two+2]
            elif one==len(numbers)-1:
                return [one, two+1]
            else:
                if numbers[one]==numbers[one-1]:
                    return [one,one+1]
                elif numbers[one]==numbers[one+1]:
                    return [one+1, one+2]
        #如果拆分的两个数不等
        else:
            if one > two:
                return [two+1, one+1]
            elif one < two:
                return [one+1, two+1]
            else:
                return [one+1, two + 2]
```


><span>结果：</span>
执行用时 : 72 ms, 在Two Sum II - Input array is sorted的Python提交中击败了96.83% 的用户
内存消耗 : 12 MB, 在Two Sum II - Input array is sorted的Python提交中击败了25.87% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>72 ms</td><td>12MB</td><td>python</td></tr></table>

### 方法二：使用两个指针，前后扫描
也就是定义`i`，`j`两个指针，在遍历的过程中，我们判断对应的数相加和`target`的关系。

```python
class Solution(object):
    def twoSum(self, numbers, target):
        """
        :type numbers: List[int]
        :type target: int
        :rtype: List[int]
        """
        i,j = 0, len(numbers)-1
        t = numbers[i]+numbers[j]
        while i<j and t!=target:
            if t > target:
                j-=1
            elif t < target:
                i+=1
            print(i, j)
            t = numbers[i] + numbers[j]
        return [i+1, j+1]
```
><span>结果：</span>
执行用时 : 372 ms, 在Two Sum II - Input array is sorted的Python提交中击败了8.99% 的用户
内存消耗 : 11.9 MB, 在Two Sum II - Input array is sorted的Python提交中击败了35.99% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>372 ms</td><td>11.9MB</td><td>python</td></tr></table>
虽然，代码减少了很多，但是效率不怎么高。
其实，也不难理解：这里采用一趟遍历，那么时间复杂度是`O(n)`，而二分查找的事件复杂度是
`O(log2^n)`，显然在数据比较多的时候，它们所需要的基本操作数是不同的。不在一个量级。

