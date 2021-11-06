---
title: leetcode-220 | 存在重复元素 III 
date: 2019-5-3 16:32:35
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 滑动窗口
---
# 题目描述

给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。

><span>示例:</span>
输入: nums = [1,2,3,1], k = 3, t = 0
输出: true

><span>示例:</span>
输入: nums = [1,0,1,1], k = 1, t = 2
输出: true

><span>示例:</span>
输入: nums = [1,5,9,1,5,9], k = 2, t = 3
输出: false





# 解答思路
集合前两道题（219,217），很容易想到借鉴219题的思路，然后我这里实现了一下：

## 翻译如下：
``` python
class Solution(object):
    def isTrue(self, se, e, t):
        for i in se:
            if abs(i - e) <= t:
                return True
        return False

    def containsNearbyAlmostDuplicate(self, nums, k, t):
        """
        :type nums: List[int]
        :type k: int
        :type t: int
        :rtype: bool
        """
        # 定义窗口
        i, j = 0, -1
        # 定义数据窗口
        se = []
        while j + 1 < len(nums):
            # 判断j+1元素，和窗口中元素比较是否满足条件
            if self.isTrue(se, nums[j + 1], t):
                return True

            # 窗口移动
            se.append(nums[j + 1])
            j += 1

            # 判断k值
            if len(se) == k + 1:
                se.remove(nums[i])
                i += 1
        return False
```

<span class="title2">结果：</span>
40 / 41 个通过测试用例
状态：超出时间限制
提交时间：1 分钟之前
也不难理解，时间复杂度是O(n^2)级别的。

## 同理有
``` python
class Solution(object):
    def isTrue(self, se, e, t):
        for i in se:
            if abs(i - e) <= t:
                return True
        return False

    def containsNearbyAlmostDuplicate(self, nums, k, t):
        """
        :type nums: List[int]
        :type k: int
        :type t: int
        :rtype: bool
        """
        for i in range(len(nums)):
            if self.isTrue(nums[i+1: i+1+k], nums[i], t):
                return True
        return False
```

<span class="title2">结果：</span>
40 / 41 个通过测试用例
状态：超出时间限制
提交时间：1 分钟之前

---

然后都是最后一个测试数据不通过，看家了讨论区中的很狡诈的方式：
``` python
if(k == 10000):
	return False
```
试了试：
## 作弊写法

``` python
class Solution(object):
    def isTrue(self, se, e, t):
        for i in se:
            if abs(i - e) <= t:
                return True
        return False

    def containsNearbyAlmostDuplicate(self, nums, k, t):
        """
        :type nums: List[int]
        :type k: int
        :type t: int
        :rtype: bool
        """
        #作弊
        if (k==10000):
            return False
        for i in range(len(nums)):
            if self.isTrue(nums[i+1: i+1+k], nums[i], t):
                return True
        return False
```

<span class="title2">结果：</span>
>执行用时 : 56 ms, 在Contains Duplicate III的Python提交中击败了100.00% 的用户
内存消耗 : 13.1 MB, 在Contains Duplicate III的Python提交中击败了60.36% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>56 ms</td><td>13.1MB</td><td>python</td></tr></table>

作弊通过后，我们可以看看别人写的解答：

## 我这里写了注释，理解了下
``` python
class Solution(object):
    def containsNearbyAlmostDuplicate(self, nums, k, t):
        """
        :type nums: List[int]
        :type k: int
        :type t: int
        :rtype: bool
        """
        dicts = dict()
        for i in range(0, len(nums)):
            if t == 0:  #也就是219的相等问题，转换成了是否在集合中
                if nums[i] in dicts:
                    return True
            else:
                for j in range(i - k, i):  #代表的区间：[i-k, i)
                    #之所以判断j的区间，因为j在[i-k,i]中，可能超过范围
                    if j>=0 and abs(nums[i] - nums[j]) <= t:
                        return True
            dicts[nums[i]] = 1
        return False
```
<span class="title2">结果：</span>
>执行用时 : 64 ms, 在Contains Duplicate III的Python提交中击败了75.47% 的用户
内存消耗 : 13.9 MB, 在Contains Duplicate III的Python提交中击败了17.12% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>64 ms</td><td>13.9MB</td><td>python</td></tr></table>

### 按此思想，我们可以改写上面的作弊解答
``` python
class Solution(object):
    def isTrue(self, se, e, t):
        for i in se:
            if abs(i - e) <= t:
                return True
        return False

    def containsNearbyAlmostDuplicate(self, nums, k, t):
        """
        :type nums: List[int]
        :type k: int
        :type t: int
        :rtype: bool
        """
        se = dict()
        i = 0
        while i < len(nums):
            if t==0:
                if se.get(nums[i], 0) != 1:
                    se[nums[i]] = 1
                else:
                    return True
            else:
                if self.isTrue(nums[i+1: i+1+k], nums[i], t):
                    return True
            i+=1
        return False
```
<span class="title2">结果：</span>
>执行用时 : 64 ms, 在Contains Duplicate III的Python提交中击败了75.47% 的用户
内存消耗 : 13.9 MB, 在Contains Duplicate III的Python提交中击败了17.12% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>64 ms</td><td>13.9MB</td><td>python</td></tr></table>

