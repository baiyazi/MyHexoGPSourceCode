---
title: leetcode-25 | k个一组翻转链表 
date: 2019-5-8 16:46:42
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述

给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

><span>示例：</span>
给定这个链表：1->2->3->4->5
当 k = 2 时，应当返回: 2->1->4->3->5
当 k = 3 时，应当返回: 3->2->1->4->5

><span>说明：</span>
* 你的算法只能使用常数的额外空间。
* 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



# 思路解答
不妨采用计数变量，当计数变量满足条件，我们就进行链表中子链表反转的操作，直到链表尾部。


``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverse(self, head):
        pre = None
        cur = head
        while cur:
            next = cur.next

            # 翻转链
            cur.next = pre
            pre = cur
            cur = next

        return pre

    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        #k个翻转，很容易想到链表的翻转
        if head is None or head.next is None or k==0:
            return head

        #正式处理
        #定义计数变量count
        virtualhead = ListNode(0)
        virtualhead.next = head
        vp = virtualhead
        count = 0
        phead = p = head
        while p:
            count += 1
            if count%k==0:
                #记录下一次交换的开始节点
                pnext = p.next
                #需要转换的子链表尾置空
                p.next = None
                #换后，头变尾，尾变头，记录头，也即是换后的尾
                switchtail = phead
                vp.next = self.reverse(phead)
                #移动vp到交换后的尾部，也就是保存的头部
                vp = switchtail
                #链接成一个整链表
                vp.next = pnext

                #移动指针，开始下一次
                phead = p = pnext
            else:
                p = p.next


        return virtualhead.next
        
```


<span class="title2">结果：</span>
>执行用时 : 56 ms, 在Reverse Nodes in k-Group的Python提交中击败了53.17% 的用户
内存消耗 : 13.2 MB, 在Reverse Nodes in k-Group的Python提交中击败了43.17% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>56 ms</td><td>13.2MB</td><td>python</td></tr></table>
