---
title: leetcode-438 | 找到字符串中所有字母异位词 
date: 2019-4-25 10:06:44
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 滑动窗口
---

## 438. 找到字符串中所有字母异位词（Find All Anagrams in a String）

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

><span>说明：</span>
字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。

><span>示例 1:</span>
输入:
s: "cbaebabacd" p: "abc"
输出:
[0, 6]
><span style="color:red;">解释:</span>
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

><span>示例 2:</span>
输入:
s: "abab" p: "ab"
输出:
[0, 1, 2]
><span style="color:red;">解释:</span>
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。






## 思路
### 方法一： 滑动窗口


``` python
class Solution(object):
    def findAnagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """
        #固定的滑动窗口，长度为len(p)
        #每次判断都是走一个滑动窗口的距离
        i,j=0,-1
        length = 0
        #结果列表
        result = []
        #暂时存放的数据字典，因为考虑到有重复字母
        temp = dict()
        #需要比较的数据，我们也封装到字典中
        dp = dict()
        k = 0
        while k<len(p):
            if p[k] not in dp:
                dp[p[k]] = 1
            else:
                dp[p[k]] += 1
            k+=1

        while j+1<len(s):
            #j移动到窗口中的最后一个字符
            while j+1<len(s) and length<len(p):
                #窗口右边界扩展1
                j+=1
                #窗口长度+1
                length += 1
                # 数据装入, 字典，我不删除元素，置数值为0模拟删除
                if s[j] not in temp or temp[s[j]]==0:
                    temp[s[j]] = 1
                else:
                    temp[s[j]] += 1
                


            # 退出循环，窗口长度满足条件
            if length == len(p):
                # 判断是否相等
                #这里我们将模拟删除的删除
                tks = []
                for tk, tv in temp.items():
                    if tv==0:
                        tks.append(tk)
                for mk in tks:
                    temp.pop(mk)

                

                if temp == dp:
                    result.append(i)

                # 本次固定大小窗口判断完毕
                # 窗口后移,i+=1,将原位置元素模拟删除
                if s[i] in temp:
                    if temp[s[i]]>0:
                        temp[s[i]] -= 1
                    else:
                        temp[s[i]] = 0
                
                i+=1
                length -= 1

        return result
        
        
```

><span>结果：</span>
执行用时 : 272 ms, 在Find All Anagrams in a String的Python提交中击败了6.45% 的用户
内存消耗 : 12.7 MB, 在Find All Anagrams in a String的Python提交中击败了38.57% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>272 ms</td><td>12.7MB</td><td>python</td></tr></table>
这里我编写的代码效率很不高，也很冗余。下面我思考看看能不能简化。
另：逐个加入，然后判断，会超时。

### 代码简化

数据封装的代码可以简化：
```python
#需要比较的数据，我们也封装到字典中
dp = dict()
k = 0
while k<len(p):
    if p[k] not in dp:
        dp[p[k]] = 1
    else:
        dp[p[k]] += 1
    k+=1
```

```python
#需要比较的数据，我们也封装到字典中
dp = dict()
for pv in p:
#get函数，如果不存在返回0
	dp[pv] = dp.get(pv, 0) + 1
```

虽然看似简化了代码，但是实际上效率还是没有较高的提升。

---

算了，二天再想。
