---
title: leetcode-205 | 同构字符串
date: 2019-4-26 09:20:58
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 205. 同构字符串（Isomorphic Strings）

给定两个字符串 s 和 t，判断它们是否是同构的。
如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

><span>示例1：</span>
输入: s = "egg", t = "add"
输出: true
><span>示例2：</span>
输入: s = "foo", t = "bar"
输出: false
><span>示例3：</span>
输入: s = "paper", t = "title"
输出: true

><span>说明：</span>
你可以假设 s 和 t 具有相同的长度。




## 思路
### 方法一： 和上题一样的方式
按照空格分割字符串，存入列表中。
对于模式串：`pattern = "egg"` 和` str = "add"` 分别定义一个下标指针，同步移动。
`e-->a`
`g-->d`
存入后，判断下一个模式，如`g`，看`str`中指针指向的 是否是`d`
当然，也要防止出现`a-->d`  `b-->d`现象。
唯一的不同在于，对非空格分割字符串的列表封装：

``` python
def splitPattern(self, p):
        li = []
        for i in p:
            li.append(i)
        return li
```


``` python
class Solution(object):
    def isNotInValue(self, ps, s):
        for i in ps.values():
            if i==s:
                return False
        return True
    def splitPattern(self, p):
        li = []
        for i in p:
            li.append(i)
        return li

    def isIsomorphic(self, pattern, s):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        li = self.splitPattern(s)
        #print(li)
        p_len, s_len = len(pattern), len(li)
        if p_len != s_len:
            return False
        #定义同步指针i,j
        i,j = 0,0
        #定义字典存放模式字母对应的字符串
        ps = dict()
        while j<p_len:
            if pattern[i] not in ps:
                #防止出现a-->dog  b-->dog
                if self.isNotInValue(ps, li[j]):
                    ps.update({pattern[i]: li[j]})
                else:
                    return False

            if li[j] == ps.get(pattern[i]):
                j += 1
                i += 1
            else:
                return False
        return True
```

><span style="color:red;">结果：</span>
执行用时 : 48 ms, 在Isomorphic Strings的Python提交中击败了53.57% 的用户
内存消耗 : 12.4 MB, 在Isomorphic Strings的Python提交中击败了56.62% 的
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>12.4MB</td><td>python</td></tr></table>
### 方法二：更简单的方式
简化版，因为特色是这里可以直接用i，j同步指针。
不用像上一题那样，拆分成列表后才能用同步指针。

```python
class Solution(object):
    def isIsomorphic(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        i, j = 0, 0
        #长度不等返回False
        s_len, t_len = len(s), len(t)
        if s_len!=t_len:
            return False
        #定义一个结果集
        re = dict()
        while i<s_len:
            if s[i] not in re:
                #同样的可能有a-->t  b-->t出现，我们要避免这种情况
                if t[j] in re.values():
                    return False
                re.update({s[i]:t[j]})

            #当前的s[i]取出的对应的t[j]和t[j]进行判断
            if re[s[i]]!=t[j]:
                return False

            i+=1
            j+=1

        return True
```

><span style="color:red;">结果：</span>
执行用时 : 40 ms, 在Isomorphic Strings的Python提交中击败了96.81% 的用户
内存消耗 : 12.2 MB, 在Isomorphic Strings的Python提交中击败了64.71% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>40 ms</td><td>12.2MB</td><td>python</td></tr></table>