---
title: leetcode-328 | 奇偶链表 
date: 2019-5-4 18:28:26
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---

# 题目描述

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

><span>示例 1：</span>
输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL

><span>示例 1：</span>
输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL

><span>说明：</span>
应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。


## 思路
不妨取数据到列表中，然后根据题意操作，然后构成数据格式
和上一道题（86）类似

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def oddEvenList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        #空结点或者一个节点，直接返回
        if head==None or head.next==None:
            return head
        virtualhead = ListNode(0)
        odd = []
        even = []
        p = head
        i = 1
        while p:
            if i%2==0:
                even.append(p)
            else:
                odd.append(p)
            i+=1
            p = p.next
        t = odd+even
        p = virtualhead
        for i in t:
            p.next = i
            p = p.next
        p.next = None

        return virtualhead.next

```


<span class="title2">结果：</span>
>执行用时 : 48 ms, 在Odd Even Linked List的Python提交中击败了92.31% 的用户
内存消耗 : 15.3 MB, 在Odd Even Linked List的Python提交中击败了6.37% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>15.3MB</td><td>python</td></tr></table>
## 优化
不必建立列表存储，直接加入

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def oddEvenList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        #空结点或者一个节点，直接返回
        if head==None or head.next==None:
            return head
        odd = ListNode(0)
        oddh = odd  #指针
        even = ListNode(0)
        evenh = even  #指针
        p = head     #指针
        i = 1
        while p:
            if i%2==0:
                evenh.next = p
                evenh = evenh.next
            else:
                oddh.next = p
                oddh = oddh.next

            i += 1
            p = p.next
        oddh.next = evenh.next = None
        oddh.next = even.next

        return odd.next
```

<span class="title2">结果：</span>
>执行用时 : 48 ms, 在Odd Even Linked List的Python提交中击败了92.31% 的用户
内存消耗 : 15.1 MB, 在Odd Even Linked List的Python提交中击败了29.21% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>48 ms</td><td>15.1MB</td><td>python</td></tr></table>
