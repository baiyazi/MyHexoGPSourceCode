---
title: leetcode-83 | 删除排序链表中的重复元素 
date: 2019-5-4 16:19:44
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
# 题目描述

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

><span>示例 1：</span>
输入: 1->1->2
输出: 1->2

><span>示例 2：</span>
输入: 1->1->2->3->3
输出: 1->2->3


## 思路
指针扫描一遍即可

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None:
            return head
        #定义指针cur, p
        cur = head
        p = cur.next
        while p:
            #判断是否相等
            if p.val==cur.val:
            	#q是临时指针变量
                q = p.next
                cur.next = q
                p = q
            else:
                cur = cur.next
                p = p.next
        return head
        
```


<span class="title2">结果：</span>
>执行用时 : 40 ms, 在Remove Duplicates from Sorted List的Python提交中击败了99.64% 的用户
内存消耗 : 11.8 MB, 在Remove Duplicates from Sorted List的Python提交中击败了29.12% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>40 ms</td><td>11.8MB</td><td>python</td></tr></table>
