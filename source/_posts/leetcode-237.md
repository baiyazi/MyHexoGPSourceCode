---
title: leetcode-237 | 删除链表中的节点 
date: 2019-5-7 21:47:04
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 链表
---
# 题目描述
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]，它可以表示为:
![e](/images/201905/2019-5-7_example.png)

><span>示例 1：</span>
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

><span>示例 2：</span>
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.


><span>说明：</span>
链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。



# 思路解答
不妨取数据到列表中，然后根据题意操作，然后构成数据格式
和上一道题（86）类似

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def deleteNode(self, node):
        """
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        #值交换
        node.val, node.next.val = node.next.val, node.val
        node.next = node.next.next
```


<span class="title2">结果：</span>
>执行用时 : 40 ms, 在Delete Node in a Linked List的Python提交中击败了100.00% 的用户
内存消耗 : 12.2 MB, 在Delete Node in a Linked List的Python提交中击败了35.23% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>40 ms</td><td>12.2MB</td><td>python</td></tr></table>
