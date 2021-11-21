---
title: leetcode-86 | 分隔链表 
date: 2019-5-4 18:28:26
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述

给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

><span>示例 1：</span>
输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5


## 思路
不妨取数据到列表中，然后根据题意操作，然后构成数据格式

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        #空结点或者一个节点，直接返回
        if head==None or head.next==None:
            return head

        #虚拟结点
        virtualhead = ListNode(0)

        mi, ma = [], []
        while head:
            if head.val < x:
                mi.append(head.val)
            else:
                ma.append(head.val)
            head = head.next

        vm = mi+ma
        p = virtualhead
        for i in vm:
            s = ListNode(i)
            p.next = s
            p = p.next
        p.next = None

        return virtualhead.next
        
        
```


<span class="title2">结果：</span>
>执行用时 : 60 ms, 在Partition List的Python提交中击败了10.26% 的用户
内存消耗 : 11.8 MB, 在Partition List的Python提交中击败了22.78% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>60 ms</td><td>11.8MB</td><td>python</td></tr></table>
## 优化
上面的结点可以不必销毁后重建
``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        #空结点或者一个节点，直接返回
        if head==None or head.next==None:
            return head

        #虚拟结点
        virtualhead = ListNode(0)

        mi, ma = [], []
        while head:
            if head.val < x:
                mi.append(head)
            else:
                ma.append(head)
            head = head.next

        vm = mi+ma
        p = virtualhead
        for i in vm:
            p.next = i
            p = p.next
        p.next = None

        return virtualhead.next
```

<span class="title2">结果：</span>
>执行用时 : 36 ms, 在Partition List的Python提交中击败了91.45% 的用户
内存消耗 : 11.8 MB, 在Partition List的Python提交中击败了33.17% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.8MB</td><td>python</td></tr></table>
---

在讨论去看见的类似的处理：
``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        #空结点或者一个节点，直接返回
        if head==None or head.next==None:
            return head

        mi = ListNode(0)
        ma = ListNode(0)
        hmi = mi
        hma = ma

        p = head
        while p:
            if p.val < x:
                hmi.next = p
                p = p.next
                hmi = hmi.next
                hmi.next = None
            else:
                hma.next = p
                p = p.next
                hma = hma.next
                hma.next = None
        hmi.next = ma.next
        return mi.next
```

<span class="title2">结果：</span>
>执行用时 : 36 ms, 在Partition List的Python提交中击败了91.45% 的用户
内存消耗 : 11.8 MB, 在Partition List的Python提交中击败了33.17% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.7MB</td><td>python</td></tr></table> 

