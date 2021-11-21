---
title: leetcode-350 | 两个数组的交集II  
date: 2019-4-25 16:47:28
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---

## 350. 两个数组的交集 II（Intersection of Two Arrays II）

给定两个数组，编写一个函数来计算它们的交集。

><span>示例 1：</span>
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
><span>示例 2：</span>
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]

><span>说明：</span>
输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
><span>进阶:</span>
如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？


## 思路
### 方法一： 和上题类似
这里不同的是，我们需要考虑到数据出现的频次。上一题中是单纯的求交集，而不考虑相同的元素的情况。

``` python
class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        di = dict()
        re = []
        #将数据nums1中的数据，全部封装到字典中
        for i in nums1:
            di[i] = di.get(i, 0) + 1

        #遍历找相同
        for j in nums2:
            if j in di and di[j]>0:
                di[j] -= 1
                re.append(j)

        return re
```

><span>结果：</span>
执行用时 : 52 ms, 在Intersection of Two Arrays II的Python提交中击败了95.21% 的用户
内存消耗 : 11.8 MB, 在Intersection of Two Arrays II的Python提交中击败了24.69% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>52 ms</td><td>11.8MB</td><td>python</td></tr></table>

### 方法二：在评论区找到的骚操作


```python 
class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        #先集合化，就两个集合的交集
        inter = set(nums1) & set(nums2)
        l = []
        for i in inter:
            #计数，记录最小的
            l += [i] * min(nums1.count(i), nums2.count(i))  
        return l
   
```

><span>结果：</span>
执行用时 : 72 ms, 在Intersection of Two Arrays II的Python提交中击败了52.66% 的用户
内存消耗 : 11.9 MB, 在Intersection of Two Arrays II的Python提交中击败了17.79% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>72 ms</td><td>11.9MB</td><td>python</td></tr></table>
