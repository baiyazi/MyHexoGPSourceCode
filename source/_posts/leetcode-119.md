---
title: leetcode-119 | 杨辉三角II  
date: 2019-6-2 17:10:35
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 119. 杨辉三角II
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

><span>例如</span>
输入: 4
输出:
[1,4,6,4,1]


## 思考
不难发现，是从0开始的。
其实这个问题我们在118中已经解决了，因为当时我用的是一个base的列表，然后直接返回该列表就可以了。我们不妨修改一下上一题的程序。

``` python
class Solution(object):
    def getRow(self, rowIndex):
        """
        :type rowIndex: int
        :rtype: List[int]
        """
        numRows = 1 + rowIndex
        #基础情况
        if numRows == 0:
            return []
        elif numRows == 1:
            return [1]
        elif numRows == 2:
            return [1,1]

        #其他情况，以[1,1]为基础计算
        base = [1,1]
        #不妨假设是3，此时，中间计算一次，故而从第三行开始，每一行计算的次数是：1 2 3 4 5 依次增加
        #而需要计算的行数row = numrows - 2
        row = numRows - 2
        i = 1
        while i <= row:
            #按照行计算，循环的次数一次增加
            #第一行计算一次，第二行计算两次，刚好对应
            j = 0
            temp = [1]
            while j < i:
                ele = base[j] + base[j+1]
                temp.append(ele)
                j+=1
            temp.append(1)
            base = temp
            i+=1

        return base
```

<span class="title2">结果：</span>
>执行用时 : 16 ms, 在Pascal's Triangle II的Python提交中击败了98.96% 的用户
内存消耗 : 11.6 MB, 在Pascal's Triangle II的Python提交中击败了30.99% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>16 ms</td><td>11.6MB</td><td>python</td></tr></table>
