---
title: leetcode-290 | 单词模式
date: 2019-4-26 09:20:58
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---

## 290. 单词模式（Word Pattern）

给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。
这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。

><span>示例1：</span>
输入: pattern = "abba", str = "dog cat cat dog"
输出: true

><span>示例2：</span>
输入:pattern = "abba", str = "dog cat cat fish"
输出: false

><span>示例3：</span>
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false

><span>示例4：</span>
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
><span>说明：</span>
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    



## 思路
### 方法一： 分割字符串，然后对应位置判断
按照空格分割字符串，存入列表中。
对于模式串：`pattern = "abba"` 和` str = "dog cat cat dog"` 分别定义一个下标指针，同步移动。
`a-->dog`
`b-->cat`
存入后，判断下一个模式，如`b`，看`str`中指针指向的 是否是`cat`
当然，也要防止出现`a-->dog`  `b-->dog`现象。

``` python
class Solution(object):
	#防止出现a-->dog  b-->dog
    def isNotInValue(self, ps, s):
        for i in ps.values():
            if i==s:
                return False
        return True
    def wordPattern(self, pattern, s):
        """
        :type pattern: str
        :type str: str
        :rtype: bool
        """
        li = s.split(" ")
        p_len, s_len = len(pattern), len(li)
        if p_len != s_len:
            return False
        #定义同步指针i,j
        i,j = 0,0
        #定义字典存放模式字母对应的字符串
        ps = dict()
        while j<p_len:
        	#每次一进来就判断当前的下标i对应的模式字母是否在字典中
            if pattern[i] not in ps:
                #防止出现a-->dog  b-->dog
                if self.isNotInValue(ps, li[j]):
                    ps.update({pattern[i]: li[j]})
                else:
                    return False
			#这里判断的还是当前下标i对应的模式字母
            if li[j] == ps.get(pattern[i]):
            	#匹配就同步移动指针
                j += 1
                i += 1
            else:
                return False
        return True
```

><span>结果：</span>
执行用时 : 28 ms, 在Word Pattern的Python提交中击败了98.04% 的用户
内存消耗 : 11.7 MB, 在Word Pattern的Python提交中击败了29.14% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>28 ms</td><td>11.7MB</td><td>python</td></tr></table>

