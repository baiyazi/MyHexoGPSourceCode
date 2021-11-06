---
title: leetcode-345 | 反转字符串中的元音字母 
date: 2019-4-24 14:48:52
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---

## 345. 反转字符串中的元音字母（Reverse Vowels of a String）

编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

><span>示例1：</span>
输入: "hello"
输出: "holle"
><span>示例2：</span>
输入: "leetcode"
输出: "leotcede"
><span>说明</span>
元音字母不包含字母"y"。



## 思路
### 方法一： reverse函数
`python`中有`reverse`函数，用于列表的操作。这里我们也直接使用这种方式。

``` python

```


><span>结果：</span>
执行用时 : 232 ms, 在Reverse String的Python提交中击败了65.80% 的用户
内存消耗 : 18.6 MB, 在Reverse String的Python提交中击败了79.84% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>232 ms</td><td>18.6MB</td><td>python</td></tr></table>

### 方法二：我们自己定义指针操作
定义`i`，`j`两个指针，分别指向首尾，在遍历的过程中，不是元音就`++`或者`--`，如果是，就等到两个都是元音的时候交换，知道`i==j`

```python
class Solution(object):
    def isY(self, s):
        if s=='a' or s=='e' or s=='i' or s=='o' or s=='u' or s=='A' or s=='E' or s=='I' or s=='O' or s=='U':
            return True
        return False

    def reverseVowels(self, s):
        """
        :type s: str
        :rtype: str
        """
        s = list(s)
        i,j = 0, len(s)-1
        while i<j:
            if self.isY(s[j]) and self.isY(s[i]):
                s[i],s[j] = s[j],s[i]
                i+=1
                j-=1
            if not self.isY(s[i]):
                i+=1
            if not self.isY(s[j]):
                j-=1

        return "".join(s)
        
```
这里需要注意的是：`while i<j:`而不是`while i<=j:`
这里有个例子可以说明：`s=".a"`
`i=0，j=1`
`s[i]`不是元音，`i+=1 i=1`
此时满足`i<=j`，循环继续，
此时`i`，`j`同位。那么交换后，`i++`，`j--`，但是这种方式很不好。
我自我感觉`while`中循环的逻辑不明，有错误一样。

><span>结果：</span>
执行用时 : 376 ms, 在Reverse String的Python提交中击败了13.38% 的用户
内存消耗 : 18.7 MB, 在Reverse String的Python提交中击败了60.28% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>376 ms</td><td>18.7MB</td><td>python</td></tr></table>

### 方法二：改进
还是`i`，`j`指针，但是，我这里不是一步一步的移动，我是一次性判断。
``` python
class Solution(object):
    def isY(self, s):
        if s=='a' or s=='e' or s=='i' or s=='o' or s=='u' or s=='A' or s=='E' or s=='I' or s=='O' or s=='U':
            return True
        return False

    def reverseVowels(self, s):
        """
        :type s: str
        :rtype: str
        """
        s = list(s)
        i,j = 0, len(s)-1
        while i<j:
            while i<j and not self.isY(s[i]):
                i+=1
            while i<j and not self.isY(s[j]):
                j-=1
            if i<j:
                s[i],s[j] = s[j],s[i]
                i+=1
                j-=1

        return "".join(s)

```

><span>结果：</span>
执行用时 : 180 ms, 在Reverse Vowels of a String的Python提交中击败了51.43% 的用户
内存消耗 : 13.9 MB, 在Reverse Vowels of a String的Python提交中击败了33.13% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>180 ms</td><td>13.9MB</td><td>python</td></tr></table>