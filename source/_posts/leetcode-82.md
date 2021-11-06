---
title: leetcode-82 | 删除排序链表中的重复元素 II 
date: 2019-5-8 10:01:45
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

><span>示例 1：</span>
输入: 1->2->3->3->4->4->5
输出: 1->2->5

><span>示例 2：</span>
输入: 1->1->1->2->3
输出: 2->3



# 思路解答
以前做过类似的题目，也就是删除重复的结点，判断当前元素和下一个元素是否相等，相等就后移，然后到下一个不等的结点，设置链接即可。
这里有所不同，这里是只要重复就删除所有
看第二个示例，可以发现，我们需要虚拟头结点
这里我们不妨走两趟，第一趟，我们删除重复元素，并记录我们的重复元素是什么
第二趟我们删除记录的重复元素

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
        if head is None:
            return head
        virtualhead = ListNode(0)
        virtualhead.next = head

        cur = head
		#记录元素
        record = set()
        while cur and cur.next:
            next = cur.next
            if cur.val == next.val:
                temp = next.next
                cur.next = temp
                record.add(cur.val)              
                cur = temp
               
            else:
                cur = next

        #将记录的元素删除
        pre = virtualhead
        cur = pre.next
        while cur:
            next = cur.next

            if cur.val in record:
                pre.next = next
                cur = next
            else:
                pre = cur
                cur = next

        return virtualhead.next
```


<span class="title2">结果：</span>
>执行用时 : 44 ms, 在Remove Duplicates from Sorted List II的Python提交中击败了68.67% 的用户
内存消耗 : 11.8 MB, 在Remove Duplicates from Sorted List II的Python提交中击败了27.43% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>11.8MB</td><td>python</td></tr></table>
## 另一种方法
可以采用一趟循环解决问题
加入虚拟头结点，然后判断后两个节点的值是否一样，一样就都删除

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
        #我这里定义三个指针，如果结点小于2,我这里是不做循环的
        #0个或者1个的情况
        if head is None or head.next is None:
            return head
        #2个结点的情况
        if head.next.next is  None:
            if head.val == head.next.val:
                return None
            else:
                return head

        #其他情况
        virtualhead = ListNode(0)
        virtualhead.next = head
        pre = virtualhead
        cur = head
        flag = False
        while cur:

            next = cur.next
            #边界，最后一次next is None，没有val
            if next is None:
                #print((pre.val, cur.val, flag))
                if flag:
                    pre.next = next
                    cur = next
                break

            #非边界
            #判断cur和next，而pre是链接指针的
            if cur.val == next.val:
                #由于需要统一，这里我放置变量flag，标志上一趟相等
                flag = True
                cur = next
            else:
                #这里需要判断标志
                if flag:
                    #print((pre.val, cur.val, next.val, flag))
                    pre.next = next
                    cur = next
                    flag = False
                else:
                    #print((pre.val, cur.val, next.val, flag))
                    pre = cur
                    cur = next

        
        return virtualhead.next
```

<span class="title2">结果：</span>
>执行用时 : 60 ms, 在Remove Duplicates from Sorted List II的Python提交中击败了14.00% 的用户
内存消耗 : 11.7 MB, 在Remove Duplicates from Sorted List II的Python提交中击败了30.21% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>60 ms</td><td>11.7MB</td><td>python</td></tr></table>
