---
title: leetcode-21 | 合并两个有序链表 简单难度
date: 2019-5-8 10:03:20
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 链表
---
# 题目描述

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

><span>示例 1：</span>
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4



# 思路解答
比较简单，遍历两个链表，比较大小，加入新链表即可。

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        
        #正式开始
        p, q = l1, l2
        #不妨设置虚拟头结点
        virtualhead = ListNode(0)
        vp = virtualhead
        while p or q:
            if p and q:
                if p.val <= q.val:
                    vp.next = p
                    #vp = vp.next
                    p = p.next
                else:
                    vp.next = q
                    #vp = vp.next
                    q = q.next
                
            elif p:
                vp.next = p
                #vp = vp.next
                p = p.next
            else:
                vp.next = q
                #vp = vp.next
                q = q.next
            vp = vp.next
        
        return virtualhead.next

```


<span class="title2">结果：</span>
>执行用时 : 44 ms, 在Merge Two Sorted Lists的Python提交中击败了23.88% 的用户
内存消耗 : 11.7 MB, 在Merge Two Sorted Lists的Python提交中击败了34.70% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>11.7MB</td><td>python</td></tr></table>
## 优化
不必建立列表存储，直接加入

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        
        #正式开始
        p, q = l1, l2
        #不妨设置虚拟头结点
        virtualhead = ListNode(0)
        vp = virtualhead
        while p and q:
            if p.val <= q.val:
                vp.next = p
                #vp = vp.next
                p = p.next
            else:
                vp.next = q
                #vp = vp.next
                q = q.next
            vp = vp.next
        if p:
            vp.next = p
        if q:
            vp.next = q
        
        return virtualhead.next


```

<span class="title2">结果：</span>
>执行用时 : 40 ms, 在Merge Two Sorted Lists的Python提交中击败了53.32% 的用户
内存消耗 : 11.7 MB, 在Merge Two Sorted Lists的Python提交中击败了34.09% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>40 ms</td><td>11.7MB</td><td>python</td></tr></table>

当然了，上面的两个`if`还可以简化：`vp.next = p if p else q`
不过，运算量是一样的，只是代码量减少了。