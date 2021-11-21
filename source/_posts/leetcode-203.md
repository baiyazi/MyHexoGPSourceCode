---
title: leetcode-203 | 移除链表元素 
date: 2019-5-7 21:31:24
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 链表
---
# 题目描述

删除链表中等于给定值 val 的所有节点。

><span>示例 1：</span>
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5


# 思路解答
很简单，不妨加一个虚拟头节点，然后统一判断，删除。

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def removeElements(self, head, val):
        """
        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        if head is None:
            return head

        virtualhead = ListNode(0)
        virtualhead.next = head
        pre = virtualhead
        cur = head
        #循环删除
        while cur:
            next = cur.next

            if cur.val == val:
                pre.next = next
                cur = next
            else:
                pre = cur
                cur = next

        return virtualhead.next
```


<span class="title2">结果：</span>
>执行用时 : 84 ms, 在Remove Linked List Elements的Python提交中击败了94.74% 的用户
内存消耗 : 18.5 MB, 在Remove Linked List Elements的Python提交中击败了38.04% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>84 ms</td><td>18.5MB</td><td>python</td></tr></table>
## 额外空间
列表存储数据，然后删除，最后重构

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def removeElements(self, head, val):
        """
        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        if head is None:
            return head

        li = []
        p = head
        while p:
            if p.val != val:
                li.append(p)
            p = p.next

        vitrualhead = ListNode(0)
        vp = vitrualhead
        for i in li:
            vp.next = i
            vp = vp.next
        vp.next = None
        
        return vitrualhead.next
```

<span class="title2">结果：</span>
>执行用时 : 92 ms, 在Remove Linked List Elements的Python提交中击败了29.70% 的用户
内存消耗 : 18.6 MB, 在Remove Linked List Elements的Python提交中击败了34.36% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>92 ms</td><td>18.6MB</td><td>python</td></tr></table>
