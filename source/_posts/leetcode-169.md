---
title: leetcode-169. 求众数
date: 2019-9-18 20:21:07
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 分治法
---
## 169. 求众数
### 题目描述
给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于` ⌊ n/2 ⌋` 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:
>输入: [3,2,3]
输出: 3

示例 2:
>输入: [2,2,1,1,1,2,2]
输出: 2

### 思考
我的确靠着分治，是没有想出来该如何解决这个问题，主要是最后的基本的比较该如何写。
然后看了网上的，比较厉害就是。在进入函数的列表，即使有递归调用，但是函数返回后其本身的数组，在此时是一个东西，也就是可以用遍历比较左右两边哪个众数出现的次数更多。
给出博客地址：[地址](https://blog.csdn.net/qq_38595487/article/details/79435799)
``` python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        #
        if len(nums) == 0:
            return None
        if len(nums) == 1:
            return nums[0]
        else:
            mid = len(nums) // 2
            u = nums[:mid]
            v = nums[mid:]
            left = self.majorityElement(u)
            right = self.majorityElement(v)
            if left == right:
                return left
            else:
                left_count = right_count = 0
                for i in nums:
                    if i == left:
                        left_count = left_count + 1
                    elif i == right:
                        right_count = right_count + 1
                if left_count > right_count:
                    return left
                else:
                    return right
```

结果：
![](/images/201909/2019-09-18_202929.png)


### 方法二
提交后，可以查看别人提交过得代码。这里我随便看了一个。
比较好，就是使用python的集合来封装，然后判断出现的次数是否过半。
``` python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return None
        if len(nums) == 1:
            return nums[0]
        else:
            dic = {}
            # 封装
            for i in nums:
                dic[i] = dic.get(i, 0) + 1 # 如果没有，返回次数value=0，否则返回value
            # 求次数最多的
            for i in dic:
                if dic[i] > len(nums) // 2:
                    return i
            return None
```

结果：
![](/images/201909/2019-09-18_203632.png)


----
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element

