---
title: leetcode-118 | 杨辉三角 
date: 2019-6-1 18:30:04
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 118. 杨辉三角
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

在杨辉三角中，每个数是它左上方和右上方的数的和。

><span>例如</span>
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]


## 思考
不妨使用二维数组存放数据，初始化的数据均是0，然后我们按照规则填入数据
当h=4，行4，列7=2*4-1，首个1在7/2=3，然后开始计算

``` python
class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        if numRows == 0:
            return []
        if numRows == 1:
            return [[1]]
        cols = numRows * 2 - 1
        rows = numRows
        i = 0
        li = []
        while i<rows:
            j = 0
            cli = []
            while j<cols:
                cli.append(0)
                j+=1
            li.append(cli)
            i+=1

        li[0][numRows-1]=1
        li[numRows - 1][0] = 1
        li[numRows - 1][cols-1] = 1

        col = row = 1
        while row < rows:
            col = 0
            while col < cols-1:
                li[row][col] = li[row-1][col-1] + li[row-1][col + 1]
                col += 1
            row += 1

        resu = []
        col = row = 0
        while row < rows:
            col = 0
            sr = []
            while col < cols:
                if li[row][col]!=0:
                    sr.append(li[row][col])
                col += 1
            resu.append(sr)
            row += 1


        return resu
```

<span class="title2">结果：</span>
>执行用时 : 24 ms, 在Pascal's Triangle的Python提交中击败了85.93% 的用户
内存消耗 : 11.7 MB, 在Pascal's Triangle的Python提交中击败了23.85% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.7MB</td><td>python</td></tr></table>
---
上面我自己用的比较low，是以前使用C语言数组方式来操作的。
我们看看别个的代码：
``` python
class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        res=[]
        if numRows == 0:
            return res

        for i in range(1,numRows+1):
            if i == 1:
                res.append([1])
            if i == 2:
                res.append([1,1])
            if i > 2:
                m = [1]
                for j in range(1,i-1):
                    m.append(res[i-2][j-1]+res[i-2][j])
                m.append(1)
                res.append(m)
        return res
```

上面的程序很容易理解，下标从1开始的。不过我还是模拟了一个，如下：
小标开始从0开始计算，然后做了注释，比较容易理解。

``` python
class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        #基础情况
        if numRows == 0:
            return []
        elif numRows == 1:
            return [[1]]
        elif numRows == 2:
            return [[1],[1,1]]

        resu = [[1],[1,1]]
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
            resu.append(base)
            i+=1

        return resu
```

<span class="title2">结果：</span>
>执行用时 : 20 ms, 在Pascal's Triangle的Python提交中击败了93.62% 的用户
内存消耗 : 11.7 MB, 在Pascal's Triangle的Python提交中击败了26.70% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>20 ms</td><td>11.7MB</td><td>python</td></tr></table>
