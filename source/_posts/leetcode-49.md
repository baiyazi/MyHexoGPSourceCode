---
title: leetcode-49 | 字母异位词分组 
date: 2019-5-1 16:17:50
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---
# 题目描述
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

><span>示例:</span>
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
<span>说明：</span>
* 所有输入均为小写字母。
* 不考虑答案输出的顺序。


# 解答思路
由于是判断字符串，故而一趟遍历就可以了。
然后，考察点就落在了如何封装数据上了。

``` python
class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        re= {}
        for i in strs:
            s = "".join(sorted(i))
            if s in re:
                re[s].append(i)
            else:
                re[s] = [i]   
        return list(re.values())
        
```

---

上面的数据封装，我是在讨论区中看见的，很好的案例。

><span>结果：</span>
执行用时 : 116 ms, 在Group Anagrams的Python提交中击败了95.39% 的用户
内存消耗 : 15.5 MB, 在Group Anagrams的Python提交中击败了42.94% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>116 ms</td><td>15.5MB</td><td>python</td></tr></table>

