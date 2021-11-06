---
title: leetcode-20 | 有效的括号
date: 2019-7-27 14:04:07
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 栈
---
# 题目描述
给定一个只包括` '('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串，判断字符串是否有效。
有效字符串需满足：
1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。
示例 1:
>输入: "()"
输出: true

示例 2:
>输入: "()[]{}"
输出: true

示例 3:
>输入: "(]"
输出: false

示例 4:
>输入: "([)]"
输出: false

示例 5:
>输入: "{[]}"
输出: true

## 思路解答
很明显，需要使用栈的特性来解决，下面就来尝试一下。
1. 空串直接返回`True`
2. 由于匹配，必是两两配，也就是长度是偶数
3. 在循环判断中，遇到左括号，直接入栈；遇到右括号，判断栈顶和当前元素是否是一对
4. 结果返回栈的长度是否是`0`即可

## 代码实现

``` python
class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        if s=="":
            return True
        if len(s) % 2 != 0:
            return False
        stack = []
        for i in s:
            if i == '{' or i=='(' or i=='[':
                stack.append(i)
            else:
                if len(stack) == 0:
                    return False
                top = stack.pop(len(stack) - 1)
                if i == '}' and top != '{':
                    return False
                if i == ')' and top != '(':
                    return False
                if i == ']' and top != '[':
                    return False
        return len(stack) == 0
```

### 运行结果

>执行用时 : 24 ms, 在Remove Nth Node From End of List的Python提交中击败了100.00% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了31.95% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.7MB</td><td>python</td></tr></table>

---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parentheses