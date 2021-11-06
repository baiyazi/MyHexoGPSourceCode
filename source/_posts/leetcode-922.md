---
title: leetcode-922 | 按奇偶排序数组 II  
date: 2019-6-3 22:49:15
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 922. 按奇偶排序数组 II
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。

><span>示例 1：</span>
输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。


><span>提示：</span>
2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000

## 思考
转化一下就是奇数位置放置奇数，偶数位置放置偶数的列表。

``` python 
class Solution(object):
    def sortArrayByParityII(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        ou = [i for i in A if i % 2 == 0]
        ji = [i for i in A if i % 2 != 0]
        resu = []
        i = 0
        oui = jii = 0
        while i < len(A):
            if i % 2 == 0:
                if oui < len(ou):
                    resu.append(ou[oui])
                    oui += 1
            else:
                if jii < len(ji):
                    resu.append(ji[jii])
                    jii+=1
            i += 1
        return resu
```

<span class="title2">结果：</span>
>执行用时 : 252 ms, 在Sort Array By Parity II的Python提交中击败了69.54% 的用户
内存消耗 : 13.6 MB, 在Sort Array By Parity II的Python提交中击败了37.28% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>252 ms</td><td>13.6MB</td><td>python</td></tr></table>
## 优化


