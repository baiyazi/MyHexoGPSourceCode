---
title: leetcode-344 | 反转字符串 
date: 2019-4-24 14:40:40
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---

## 344. 反转字符串（ Reverse String）

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

><span>示例1：</span>
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
><span>示例2：</span>
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]



## 思路
### 方法一： reverse函数
`python`中有`reverse`函数，用于列表的操作。这里我们也直接使用这种方式。

``` python
class Solution(object):
    def reverseString(self, s):
        """
        :type s: List[str]
        :rtype: None Do not return anything, modify s in-place instead.
        """
        s.reverse()
```


><span>结果：</span>
执行用时 : 232 ms, 在Reverse String的Python提交中击败了65.80% 的用户
内存消耗 : 18.6 MB, 在Reverse String的Python提交中击败了79.84% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>232 ms</td><td>18.6MB</td><td>python</td></tr></table>

### 方法二：我们自己定义指针操作
定义`i`，`j`两个指针，分别指向首尾，在遍历的过程中，交换。

```python
class Solution(object):
    def reverseString(self, s):
        """
        :type s: List[str]
        :rtype: None Do not return anything, modify s in-place instead.
        """
        i,j = 0, len(s)-1
        while i<=j:
            s[i],s[j] = s[j], s[i]
            i+=1
            j-=1
        
```
><span>结果：</span>
执行用时 : 376 ms, 在Reverse String的Python提交中击败了13.38% 的用户
内存消耗 : 18.7 MB, 在Reverse String的Python提交中击败了60.28% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>376 ms</td><td>18.7MB</td><td>python</td></tr></table>

