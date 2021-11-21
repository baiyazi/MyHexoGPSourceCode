---
title: leetcode-11 | 盛最多水的容器 中等难度
date: 2019-4-24 15:32:46
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 数组
---
## 11. 盛最多水的容器（Container With Most Water）

给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

><span>说明：</span>
你不能倾斜容器，且 n 的值至少为 2。

![tu](/images/201904/question_11.jpg "11题图示")
图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

><span>示例：</span>
输入: [1,8,6,2,5,4,8,3,7]
输出: 49



## 思路
### 方法一： 暴力法
两重循环遍历所有元素，然后计算面积，保留最大面积。
```python
class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        i,j = len(height)-1, 0
        m = 0
        while i>0:
            j=0
            while j<=i:
                if height[i] > height[j]:
                    m = max((i - j) * height[j], m)
                else:
                    m=max((i - j) * height[i], m)
                j+=1
            i-=1

        return m
```


><span>结果：</span>
超时


### 方法二：双指针操作
定义`i`，`j`两个指针，分别指向首尾，在遍历的过程中计算面积。两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。

```python
class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        i,j = 0,len(height)-1
        m = 0
        while i<j:
            m = max(m, min(height[i], height[j])*(j-i))
            if height[i]<height[j]:
                i += 1
            else:
                j -= 1
        return m
        
```

><span>结果：</span>
执行用时 : 236 ms, 在Container With Most Water的Python提交中击败了7.53% 的用户
内存消耗 : 13 MB, 在Container With Most Water的Python提交中击败了31.82% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>236 ms</td><td>13MB</td><td>python</td></tr></table>
