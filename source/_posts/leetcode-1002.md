---
title: leetcode-1002 | 查找常用字符 I  
date: 2019-6-3 18:05:28
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 1002. 查找常用字符
给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

你可以按任意顺序返回答案。
><span>示例 1：</span>
输入：["bella","label","roller"]
输出：["e","l","l"]
><span>示例 2：</span>
输入：["cool","lock","cook"]
输出：["c","o"]

><span>提示：</span>
*1 <= A.length <= 100
*1 <= A[i].length <= 100
*A[i][j] 是小写字母


## 思考
取的是每个字符串都含有的字符，那么我们可以用任意一个字符串，逐位统计每个字符的个数，然后在每次遍历其余的字符串的时候，减去没有出现的就可以了。
最红还在的就是都有的。

这里我们不使用这种传统的方式，我们考虑python特有的集合特性，我们将数据封装成集合，然后简单的可以得出相同的字符，并且在每次判断的过程中统计字符的个数，然后就可以达到条件。

``` python 
class Solution(object):
    def commonChars(self, A):
        """
        :type A: List[str]
        :rtype: List[str]
        """
        if len(A) == 0:
            return []
        if len(A) == 1:
            return [i for i in A]

        #从这里开始列表的长度至少是2，这里我们可以拼凑出基础的子串
        base = set(A[0]) - (set(A[0]) - set(A[1])) # 剩余的是相同的字符
        ele = ""
        for i in base:
            n = min(A[0].count(i), A[1].count(i))
            ele += n * i
        if len(A) == 2:
            return ele
            ## a = t | s # t 和 s的并集
            ##  b = t & s # t 和 s的交集
            ## c = t – s # 求差集（项在t中，但不在s中）
        #至少含有三项
        i = 2
        while i < len(A):
            base = base & set(A[i])
            sele = ""
            for j in base:
                n = min(A[i].count(j), ele.count(j))
                sele += j * n
            ele = sele
            i += 1
        return list(ele)
        
```

<span class="title2">结果：</span>
>执行用时 : 44 ms, 在Find Common Characters的Python提交中击败了97.06% 的用户
内存消耗 : 11.9 MB, 在Find Common Characters的Python提交中击败了25.82% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>11.9MB</td><td>python</td></tr></table>
---

## 优化

``` python
class Solution(object):
    def commonChars(self, A):
        """
        :type A: List[str]
        :rtype: List[str]
        """
        res=[]
        if not A:
            return res
        key=set(A[0])
        for k in key:
            minnum=min(a.count(k) for a in A)
            res+=minnum*k
        return res
```

<span class="title2">结果：</span>
>执行用时 : 44 ms, 在Find Common Characters的Python提交中击败了97.06% 的用户
内存消耗 : 11.9 MB, 在Find Common Characters的Python提交中击败了27.47% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>11.9MB</td><td>python</td></tr></table>

