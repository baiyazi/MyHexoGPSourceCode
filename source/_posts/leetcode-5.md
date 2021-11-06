---
title: leetcode-5 | 最长回文子串 中等难度
date: 2019-4-21 14:52:16
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---
## 5.最长回文子串（Longest Palindromic Substring）

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
>输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
>输入: "cbbd"
输出: "bb"

## 思路
### 一、暴力法
将选出所有子字符串可能的开始和结束位置，并检验它是不是回文。

``` python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        #判断某个字符串是否是回文串
        def isPalin(ss):
            i,j = 0, len(ss)-1
            while i<j:
                if ss[i]!=ss[j]:
                    return False
                i+=1
                j-=1

            return True

        result = []

        for i in range(len(s)):
            for j in range(len(s)+1):
                ss = s[i:j]
                if isPalin(ss):
                    result.append(ss)
                j+=1
            i+=1
		#处理，输入的是“”
        if len(result)==0:
            return ""
        else :
            return max(result,key=len)
```
><span>结果：</span>
状态：超出时间限制
`89 / 103 `个通过测试用例
时间复杂度：`O(n^3)`  空间复杂度：`O(1)`。

### 二、反转字符串
反转 S，使之变成 S'找到 S 和 S'之间最长的公共子串：
><span>例如：</span>
s = "caba"， s' = "abac"  ==> "aba"
<span>但是有例外：</span>
s = "abacdfgdcaba"  s' = "abacdgfdcaba"  ==>  "abacd" 但是，显然不是回文。
为了纠正这一点，每当我们找到最长的公共子串的候选项时，都需要检查子串的索引是否与反向子串的原始索引相同。如果相同，那么我们尝试更新目前为止找到的最长回文子串；如果不是，我们就跳过这个候选项并继续寻找下一个候选。
后面的下标，是-1 -2 -3 -4 -5 -6   +` len(s)` ==> 还原

><span>Python 字符串反转</span>
<span style="color:red;">方法一：切片</span>
&gt;&gt;&gt;&nbsp; ss = "abc"
&gt;&gt;&gt;&nbsp; ss[::-1]
'cba'
<span style="color:red;">方法二：使用列表中的reverse方法</span>
&gt;&gt;&gt;&nbsp; ss = "abc"
&gt;&gt;&gt;&nbsp; li = list(ss)
&gt;&gt;&gt;&nbsp; li
['a', 'b', 'c']
&gt;&gt;&gt;&nbsp; li.reverse()
&gt;&gt;&gt;&nbsp; "".join(li)
'cba'
<span style="color:red;">方法三：使用递归函数</span>
&gt;&gt;&gt;&nbsp; def fun(s):
...     if len(s)<1:
...             return s
...     return fun(s[1:])+s[0]
...
&gt;&gt;&gt;&nbsp; fun(ss)
'cba'
<span style="color:red;">方法四：使用栈</span>
&gt;&gt;&gt;&nbsp; def func(s):
...     l = list(s)  #模拟栈
...     result = ""
...     while len(l)>0:
...             result += l.pop()
...     return result
...
&gt;&gt;&gt;&nbsp; func(ss)
'cba'
<span style="color:red;">方法五：使用循环</span>
&gt;&gt;&gt;&nbsp; def funb(s):
...     result=""
...     max_index = len(s) - 1
...     for i, v in enumerate(s):              #enumerate
...             result += s[max_index-i]
...     return result
...
&gt;&gt;&gt;&nbsp; funb(ss)
'cba'
`enumerate()` 函数用于将一个可遍历的数据对象(<span style="color:red;">如列表、元组或字符串</span>)组合为一个索引序列，同时列出数据和数据下标，一般用在 `for` 循环当中。

但是，需要判别两个字符中的相同子字符串，操作性不高。

### 思路三





---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring

