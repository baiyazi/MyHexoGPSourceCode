---
title: leetcode-832 | 翻转图像  
date: 2019-6-2 18:46:22
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 832. 翻转图像
给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。

水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。

反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

><span>例如</span>
输入: [[1,1,0],[1,0,1],[0,0,0]]
输出: [[1,0,0],[0,1,0],[1,1,1]]
解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]

><span>例如</span>
输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

><span>说明</span>
1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1


## 思考
按照题意操作就可以了。

``` python
 
class Solution(object):
    def flipAndInvertImage(self, A):
        """
        :type A: List[List[int]]
        :rtype: List[List[int]]
        """
        #遍历一次，得到列表
        i = 0
        resu = []
        while i < len(A):
            ele = A[i]
            ele.reverse()  # 反转行
            print(ele)
            j = 0
            while j < len(ele):
                ele[j] =  1 if ele[j] == 0 else 0
                j += 1

            # 写入进去
            resu.append(ele)
            i += 1
        return resu 
```

<span class="title2">结果：</span>
>执行用时 : 48 ms, 在Flipping an Image的Python提交中击败了88.93% 的用户
内存消耗 : 11.7 MB, 在Flipping an Image的Python提交中击败了30.61% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>11.7MB</td><td>python</td></tr></table>
## 简化

``` python
class Solution(object):
    def flipAndInvertImage(self, A):
        """
        :type A: List[List[int]]
        :rtype: List[List[int]]
        """
        for i in range(len(A)):
            A[i] = A[i][::-1]
            
            for j in range(len(A[i])):
                A[i][j] = A[i][j] ^ 1
        return A
```
<span class="title2">结果：</span>
>执行用时 : 48 ms, 在Flipping an Image的Python提交中击败了88.93% 的用户
内存消耗 : 11.5 MB, 在Flipping an Image的Python提交中击败了42.86% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>11.5MB</td><td>python</td></tr></table>

