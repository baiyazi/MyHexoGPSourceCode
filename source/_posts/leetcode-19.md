---
title: leetcode-19 | 删除链表的倒数第N个节点 中等难度
date: 2019-5-12 18:24:27
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

><span>示例 1：</span>
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.

><span>说明：</span>
给定的 n 保证是有效的。

><span>进阶：</span>
你能尝试使用一趟扫描实现吗？


# 思路解答
不妨使用n值来计算指针的位置，到尾指针到达末尾节点的时候，前面的指针说明到达删除节点的前一个节点位置，可以删除。

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        #直接翻译,如果只有一个元素，删除的肯定是本身
        if head is None or head.next is None:
            return None
            
        #不妨定义两个指针，per,end
        #由于n是有效的，所以下面我们大胆操作
        pre = None 
        cur = end = head
        while n:
            end = end.next
            n-=1
        
        #扫描链表
        while end:
            pre = cur
            cur = cur.next
            end = end.next
        
        #pre is None 说明删除的目标结点是第一个元素
        if pre is None:
            return head.next
        
        #退出后，end.next=None，也就是退出后end到达末尾，可以删除
        pre.next = pre.next.next
        
        return head
```


<span class="title2">结果：</span>
>执行用时 : 32 ms, 在Remove Nth Node From End of List的Python提交中击败了100.00% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了31.95% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.7MB</td><td>python</td></tr></table>
## 代码调整
当然，可以将找元素的两个while合并在一起

``` python
class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        #直接翻译,如果只有一个元素，删除的肯定是本身
        if head is None or head.next is None:
            return None
            
        #不妨定义两个指针，per,end
        #由于n是有效的，所以下面我们大胆操作
        pre = None 
        cur = end = head
            
        
        #扫描链表
        while end:
            if n:
                end = end.next
                n-=1
            else:
                pre = cur
                cur = cur.next
                end = end.next
        
        if pre is None:
            #说明删除的是第一个元素
            return head.next
        
        #退出后，end.next=None，也就是退出后end到达末尾，可以删除
        pre.next = pre.next.next
        
        return head
```



<span class="title2">结果：</span>
>执行用时 : 32 ms, 在Remove Nth Node From End of List的Python提交中击败了100.00% 的用户
内存消耗 : 11.9 MB, 在Remove Nth Node From End of List的Python提交中击败了8.43% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.9MB</td><td>python</td></tr></table>
## 传统做法
计算链表长度，计算顺数位置，然后遍历，得到位置元素的前一个元素，删除
``` python
class Solution(object):
    def calLength(self, head):
        p = head
        n = 0
        while p:
            n+=1
            p=p.next
        return n
    
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        #直接翻译,如果只有一个元素，删除的肯定是本身
        if head is None or head.next is None:
            return None
        
        length = self.calLength(head)
        #由于n是有效的，我们可以用length-n，计数不妨从1开始
        num = length - n
        pre, cur = None, head
        
        #num=0 , 说明是删除首结点
        if num==0:
            return head.next
        
        #否则的话扫描链表,定义计数变量
        count = 1
        #扫描链表
        while count<=num:
            pre = cur
            cur = cur.next
            count += 1
        
        #退出后，per到达指定位置
        
        #退出后，end.next=None，也就是退出后end到达末尾，可以删除
        pre.next = pre.next.next
        
        return head
```



<span class="title2">结果：</span>
>执行用时 : 36 ms, 在Remove Nth Node From End of List的Python提交中击败了51.05% 的用户
内存消耗 : 11.8 MB, 在Remove Nth Node From End of List的Python提交中击败了23.14% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.8MB</td><td>python</td></tr></table>