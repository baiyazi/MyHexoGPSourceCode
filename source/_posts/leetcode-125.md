---
title: leetcode-125 | 验证回文串 
date: 2019-4-24 12:12:02
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 125. 验证回文串（Valid Palindrome）

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。

><span>示例1：</span>
输入: "A man, a plan, a canal: Panama"
输出: true
><span>示例2：</span>
输入: "race a car"
输出: false


## 思路
### 方法一： 去除其余非字母，然后双指针判断
去除空格、逗号、冒号等，然后用两个指针`i`，`j`来遍历判断。

``` python
class Solution(object):
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        s = s.lower()
        li=''
        i, j= 0, 0
        while i<len(s):
            if s[i].isalpha() or s[i].isdigit():
               li+=s[i]
            i+=1
        k = len(li)-1
        while j<=k:
            if li[j]!=li[k]:
                return False
            else:
                j+=1
                k-=1
        return True
```


><span>结果：</span>
执行用时 : 396 ms, 在Valid Palindrome的Python提交中击败了13.90% 的用户
内存消耗 : 13.2 MB, 在Valid Palindrome的Python提交中击败了33.37% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>396 ms</td><td>13.2MB</td><td>python</td></tr></table>

### 方法二：优化方法一
方法一中是先得到无特殊字符的字符串，然后，我们定义两个指针`i`，`j`分别从前到后扫描处理后的字符串。
但是，其实可以采用更简单的方式判断。因为这是一个字符串，如果是回文串，那么正向和反向的字母相同，那么我们可以直接反向处理后的字符串，然后`==`判断即可。


```python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        i = 0
        li = ""
        while i<len(s):
            if s[i].isalnum():
                li+=s[i]
            i+=1
        li = li.lower()
        return li==li[::-1]
```
><span>结果：</span>
执行用时 : 88 ms, 在Valid Palindrome的Python3提交中击败了55.73% 的用户
内存消耗 : 13.4 MB, 在Valid Palindrome的Python3提交中击败了81.85% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>88 ms</td><td>13.4MB</td><td>python</td></tr></table>
### 方法三：优化方法一
使用python的内建函数filter

``` python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        t = "".join(filter(str.isalnum, s)).lower()
        return t == t[::-1]
```
><span>结果：</span>
执行用时 : 64 ms, 在Valid Palindrome的Python3提交中击败了98.88% 的用户
内存消耗 : 13.9 MB, 在Valid Palindrome的Python3提交中击败了51.55% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>64 ms</td><td>13.9MB</td><td>python</td></tr></table>
