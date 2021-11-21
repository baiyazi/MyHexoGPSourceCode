---
title: leetcode-509 | 斐波那契数  
date: 2019-6-3 16:56:19
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 509. 斐波那契数
斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

>F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.

给定 N，计算 F(N)。

><span>示例 1：</span>
输入：2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
><span>示例 2：</span>
输入：3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
><span>示例 3：</span>
输入：4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3.



><span>提示：</span>
0 ≤ N ≤ 30



## 思考
比较简单，按照规律操作。
第一种方法，我这里采用递归的方式。

``` python 
class Solution(object):
    def fib(self, N):
        """
        :type N: int
        :rtype: int
        """
        if N == 0:
            return 0
        if N==1:
            return 1
        return self.fib(N-1) + self.fib(N-2)
        
```

<span class="title2">结果：</span>
>执行用时 : 968 ms, 在Fibonacci Number的Python提交中击败了21.76% 的用户
内存消耗 : 11.5 MB, 在Fibonacci Number的Python提交中击败了43.87% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>968 ms</td><td>11.5MB</td><td>python</td></tr></table>
## 优化
递归的方式，虽然容易理解，但是效率确实不高，这里我们改用循环来实现。

``` python
class Solution(object):
    def fib(self, N):
        """
        :type N: int
        :rtype: int
        """
        if N == 0:
            return 0
        if N==1:
            return 1
        resu = [0,1]
        a , b = 0, 1
        switch = True
        i = 0
        while i <= N - 2:
            if switch:
                a = a + b
                resu.append(a)
                switch = False
            else:
                b = a + b
                resu.append(b)
                switch = True
            i+=1
        return resu[N]
```

<span class="title2">结果：</span>
>执行用时 : 28 ms, 在Fibonacci Number的Python提交中击败了79.77% 的用户
内存消耗 : 11.8 MB, 在Fibonacci Number的Python提交中击败了26.77% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>28 ms</td><td>11.8MB</td><td>python</td></tr></table>

## 再次优化
下面的方式更好，不用像我上面那样设置开关去判断这次计算的值应该放置在哪个位置，直接交换ab，就统一了计算，也就简化了代码。
同时不需要设置辅助的列表来存储数据，需要数列就是每一次b的值。

```python
class Solution(object):
    def fib(self, N):
        """
        :type N: int
        :rtype: int
        """
        if N < 2:
            return N

        a = 0
        b = 1
        for i in range(N - 1):
            a = a + b
            a, b = b, a
        return b
```

<span class="title2">结果：</span>
>执行用时 : 20 ms, 在Fibonacci Number的Python提交中击败了93.89% 的用户
内存消耗 : 11.6 MB, 在Fibonacci Number的Python提交中击败了40.15% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>20 ms</td><td>11.6MB</td><td>python</td></tr></table>
