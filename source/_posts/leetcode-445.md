---
title: leetcode-445 | 两数相加 II 
date: 2019-5-4 19:11:01
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---

# 题目描述

给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

<span>进阶：</span>
如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

><span>示例 1：</span>
输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出: 7 -> 8 -> 0 -> 7


# 思路解答
将链表反转，然后我们使用第二题的思路

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
	#反转链表，前面做到过
    def reverse(self, l1):
        pre = None
        cur = l1
        while cur:
            next = cur.next

            # 反转指针
            cur.next = pre
            pre = cur
            cur = next

        return pre

    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        l2 = self.reverse(l2)
        l1 = self.reverse(l1)

        #然后抓化成第二题中的求解
        carry = 0
        p, q = l1, l2
        resulthead = ListNode(0)
        rep = resulthead
        while p or q:
            x = p.val if p else 0
            y = q.val if q else 0
            sum = x + y + carry
            carry = sum // 10
            s = ListNode(sum % 10)
            rep.next = s
            rep = rep.next
            if p:
                p = p.next
            if q:
                q = q.next
        if carry==1:
            rep.next = ListNode(carry)

        #和第二题中不同的是，这里还需要反转一次
        return self.reverse(resulthead.next)
        
```


<span class="title2">结果：</span>
>执行用时 : 84 ms, 在Add Two Numbers II的Python提交中击败了78.43% 的用户
内存消耗 : 11.9 MB, 在Add Two Numbers II的Python提交中击败了23.61% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>84 ms</td><td>11.9MB</td><td>python</td></tr></table>
## 优化
我们可以考虑使用辅助数据结构-栈，来解决这个问题

``` python

```

<span class="title2">结果：</span>
>执行用时 : 48 ms, 在Odd Even Linked List的Python提交中击败了92.31% 的用户
内存消耗 : 15.1 MB, 在Odd Even Linked List的Python提交中击败了29.21% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>15.1MB</td><td>python</td></tr></table>
