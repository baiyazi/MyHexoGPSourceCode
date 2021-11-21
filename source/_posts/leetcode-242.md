---
title: leetcode-242 | 有效的字母异位词 
date: 2019-4-25 17:01:07
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 242. 有效的字母异位词（Valid Anagram）

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

><span>示例1：</span>
输入: s = "anagram", t = "nagaram"
输出: true
><span>示例2：</span>
输入: s = "rat", t = "car"
输出: false
><span>说明：</span>
你可以假设字符串只包含小写字母。

><span>进阶：</span>
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？





## 思路
### 方法一： 数据装入字典中，两个字符串对应的字典比较
长度不等，直接返回False

``` python
class Solution(object):
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        s_len, t_len = len(s), len(t)
        if s_len!=t_len:
            return False
        s_dict = dict()
        for i in s:
            s_dict[i] = s_dict.get(i,0) + 1
        t_dict = dict()
        for j in t:
            t_dict[j] = t_dict.get(j, 0) + 1

        if s_dict!=t_dict:
            return False
        return True 
```

><span>结果：</span>
执行用时 : 100 ms, 在Valid Anagram的Python提交中击败了17.09% 的用户
内存消耗 : 12.5 MB, 在Valid Anagram的Python提交中击败了43.88% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>100 ms</td><td>12.5MB</td><td>python</td></tr></table>

### 方法二：观察特色
如果两个字符串相等，那么我们对应的所有的字符集合应该是一样的。
这里我们使用count函数来统计。

```python 
class Solution(object):
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        s_len, t_len = len(s), len(t)
        if s_len!=t_len:
            return False
        se = set(s)
        for i in se:
            if s.count(i)!=t.count(i):
                return False
        return True 
```

><span>结果：</span>
执行用时 : 36 ms, 在Valid Anagram的Python提交中击败了99.50% 的用户
内存消耗 : 12.8 MB, 在Valid Anagram的Python提交中击败了29.64% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>12.8MB</td><td>python</td></tr></table>
