---
title: leetcode-143 | 重排链表 
date: 2019-5-13 17:12:50
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

><span>示例 1：</span>
给定链表 1->2->3->4, 重新排列为 1->4->2->3.

><span>示例 2：</span>
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.


# 思路解答
观察示例，分别是偶数和奇数的案例，也就说明了各自的特色。
如果是奇数，中间的那个节点在末尾
所以就可以有如下的简单思路：
如果是偶数，从中间拆分成两个链表，右边的逆序，然后分别重构链表。
如果是奇数，5//2=2，右端的结点，忽略中间节点，最后在加入中间结点到末尾。

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverse(self, head):
        pre, cur = None, head
        while cur:
            next = cur.next

            cur.next = pre
            pre = cur
            cur = next

        return pre

    def getlength(self, head):
        p = head
        n = 0
        while p:
            p = p.next
            n += 1

        return n

    def reorderList(self, head):
        """
        :type head: ListNode
        :rtype: None Do not return anything, modify head in-place instead.
        """
        #[]或者[1]，情况，都返回Head就可以了
        if head is None or head.next is None:
            return head
       #得到链表的长度
        length = self.getlength(head)
        mid = length // 2
        pre, p = None, head
        count = 0
        # 最终结点
        endNone = None
        #统一移动，奇数偶数最后判断，处理
        while p and count < mid:
            pre = p
            p = p.next
            count += 1

        # 奇数,继续后移一位
        if length % 2 != 0:
            pre.next = None
            endNone = p
            p = p.next
            endNone.next = None
        else:#偶数，就直接划分成两个链表就可以了
            pre.next = None


        right = self.reverse(p)
		#虚拟节点，方便串成线
        virtualhead = ListNode(0)
        vp = virtualhead
        #为了直观，将左边的头结点指针定义为left
        left = head
        while left and right:
            vp.next = left
            vp = vp.next
            left = left.next
            vp.next = right
            vp = vp.next
            right = right.next

        if endNone:
            vp.next = endNone
            vp.next.next = None

        head = virtualhead.next
```


<span class="title2">结果：</span>
>执行用时 : 108 ms, 在Reorder List的Python提交中击败了100.00% 的用户
内存消耗 : 29.6 MB, 在Reorder List的Python提交中击败了28.67% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>108 ms</td><td>29.6MB</td><td>python</td></tr></table>
