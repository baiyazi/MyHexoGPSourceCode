---
title: leetcode-215 | 数组中的第K个最大元素 
date: 2019-4-23 21:39:24
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---
## 215. 数组中的第K个最大元素（Kth Largest Element in an Array）

在未排序的数组中找到第` k` 个最大的元素。请注意，你需要找的是数组排序后的第` k `个最大的元素，而不是第` k `个不同的元素。

><span>示例 1：</span>
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
><span>示例 2：</span>
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
><span>说明：</span>
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。


## 思路
### 方法一： 大冒泡排序
采用大冒泡，加入计数变量，到了第`k`个就返回。

``` python
class Solution(object):
    def findKthLargest(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        i= len(nums)-1
        count = 0
        while i>=0:
            j=0
            while j<i:
                if nums[j]>nums[i]:
                    nums[i], nums[j] = nums[j], nums[i]
                j+=1
            i-=1
            count+=1
            if count==k:
                return nums[i+1]
        
```


><span>结果：</span>
执行用时 : 2180 ms, 在Kth Largest Element in an Array的Python提交中击败了7.34% 的用户
内存消耗 : 12.2 MB, 在Kth Largest Element in an Array的Python提交中击败了46.03% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>2180 ms</td><td>12.2MB</td><td>python</td></tr></table>
不难看出，效率很不好。

### 方法二：使用python排序函数
上面的代码时间复杂度是O(n^2)，也就是冒泡排序的时间复杂度。所以说，这里的排序算法的时间复杂度比较大，所以，我们可以采用比较好的排序算法，以提高效率。

```python
class Solution(object):
    def findKthLargest(self, nums, k):
        nums.sort()
        return nums[len(nums)-k]
```
><span>结果：</span>
执行用时 : 76 ms, 在Kth Largest Element in an Array的Python提交中击败了81.08% 的用户
内存消耗 : 12.1 MB, 在Kth Largest Element in an Array的Python提交中击败了47.57% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>76 ms</td><td>12.1MB</td><td>python</td></tr></table>
虽然，效率提高了。但是这可能并不是出题的目的。所以尽量自己思考还有什么排序算法。

### 方法三：利用快速排序思想
每一趟快速排序，中间轴值总是放在了最终的位置，比如：
`[3,2,3,1,2,4,5,5,6]`选取3为轴值，然后，我们可以划分一个序列，并得到3的最终的位置：
`[2,2,1,3,3,4,5,5,6]` 轴值为第一个3，然后第一个三的倒数位置是6，故而如果K=2，那么我们的序列就变成了`[3,4,5,5,6]` 
以这种思想，可以解决问题。

``` python
class Solution(object):
    #逆序划分：大的放左边，小的放右边
    def partition(self, nums, low, high):
        pivot = nums[low]
        while low<high:  #[3,4,5,6,2,1]
            while low<high and nums[high]<=pivot:
                high-=1
            nums[low] = nums[high]
            while low<high and nums[low]>=pivot:
                low+=1
            nums[high] = nums[low]
        nums[low] = pivot
        return low
    def quickSort(self, nums, low, high, k):
        if low<=high:
            pivot = self.partition(nums, low, high)
            if pivot+1==k:
                return nums[pivot]
            elif pivot+1>k:
                return self.quickSort(nums, low, pivot - 1, k)
            elif pivot+1<k:
                return self.quickSort(nums, pivot+1, high, k)
        return -1

    def findKthLargest(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        return self.quickSort(nums, 0, len(nums)-1, k)
```
><span>结果：</span>
执行用时 : 744 ms, 在Kth Largest Element in an Array的Python提交中击败了30.83% 的用户
内存消耗 : 16.2 MB, 在Kth Largest Element in an Array的Python提交中击败了10.52% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>744 ms</td><td>16.2MB</td><td>python</td></tr></table>

### 方法四：使用大根堆排序思想

这里先不做讨论。