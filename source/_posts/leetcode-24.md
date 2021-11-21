---
title: leetcode-24 |  两两交换链表中的节点 中等难度
date: 2019-5-8 10:19:38
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

><span>示例 1：</span>
给定 1->2->3->4, 你应该返回 2->1->4->3.



# 思路解答
不妨看成是奇数和偶数链表，的拆分，然后交换位置。
所以我们就回到了之前我们做过的奇偶链表的拆分问题上（leetcode-328 | 奇偶链表）

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def split(self, odd, even, head):
        op = odd
        ep = even
        # 定义计数变量
        i = 1
        p = head
        while p:
            if i % 2 == 0:
                ep.next = p
                ep = ep.next
            else:
                op.next = p
                op = op.next
            i += 1
            p = p.next

        op.next = ep.next = None
        return odd, even, i-1

    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None or head.next is None:
            return head

        #正式处理
        odd = ListNode(0)
        even = ListNode(0)
        #返回奇偶链表的头指针，和head链表的总长度
        op, ep, length = self.split(odd, even, head)

        #重新赋值，指针后移
        op, ep = odd.next, even.next
        virtualhead = ListNode(0)
        vp = virtualhead
		#两次，就不用设置变量，来分奇偶，一次循环加入两次，也减少了循环次数
        while op and ep:
            vp.next = ep
            ep = ep.next
            vp = vp.next
            vp.next = op
            op = op.next
            vp = vp.next
		#最后可能剩余奇数链表的最后一个结点，需要另做判断，操作结点
        if length % 2 != 0:
            vp.next = op
            vp = vp.next
        vp.next = None

        return virtualhead.next
```


<span class="title2">结果：</span>
>执行用时 : 32 ms, 在Swap Nodes in Pairs的Python提交中击败了92.25% 的用户
内存消耗 : 11.8 MB, 在Swap Nodes in Pairs的Python提交中击败了32.62% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.8MB</td><td>python</td></tr></table>
# 优化
类似的，我们可以考虑使用一趟遍历解决问题，不妨试试。
不过这里有一点需要注意，也就是我们的指针node1和node2交换过后，我们更新结点应当注意
![e](/images/201905/2019-05-08_160649.jpg "图解注意事项")
如图所示，下一次更新的时候，我们的pre指向的结点是node1，而不是不经过大脑的Node2

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None or head.next is None:
            return head

        #正式处理
        #需要定义四个指针
        virtualhead = ListNode(0)
        virtualhead.next = head

        vp = virtualhead
        while vp.next and vp.next.next:
            node1 = vp.next
            node2 = node1.next
            next = node2.next
            #开始
            node2.next = node1
            node1.next = next
            vp.next = node2

            #移动指针,不是node2
            vp = node1
            

        return virtualhead.next
```


<span class="title2">结果：</span>
>执行用时 : 32 ms, 在Swap Nodes in Pairs的Python提交中击败了92.25% 的用户
内存消耗 : 11.7 MB, 在Swap Nodes in Pairs的Python提交中击败了37.11% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.7MB</td><td>python</td></tr></table>
