---
title: leetcode-92 | 反转链表II 
date: 2019-5-3 20:13:19
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述

反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

><span>说明:</span>
1 ≤ m ≤ n ≤ 链表长度。
><span>示例:</span>
输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL

# 解答思路
类似的使用取数据，组成数据正确集，生成格式

## 翻译如下：
``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverseBetween(self, head, m, n):
        """
        :type head: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        li = []
        p = head
        m, n = m-1, n-1
        while p:
            li.append(p.val)
            p = p.next

        i,j = m, n
        while i <= j:
            li[i], li[j] = li[j], li[i]
            i+=1
            j-=1

        head = ListNode(li[0])
        p = head
        for i in range(1,len(li)):
            s = ListNode(li[i])
            p.next = s
            p = p.next
        p.next = None

        return head
```

<span class="title2">结果：</span>
>执行用时 : 40 ms, 在Reverse Linked List II的Python提交中击败了27.61% 的用户
内存消耗 : 11.8 MB, 在Reverse Linked List II的Python提交中击败了36.22% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>40 ms</td><td>11.8MB</td><td>python</td></tr></table>


## 很老土的解法
遍历获取数据，翻转，构成数据格式

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None or head.next==None:
            return head
        #可以把数据全部整下来，在反转，在构成
        li = []
        p = head
        while p:
            li.append(p.val)
            p=p.next
        li.reverse()
        head = ListNode(li[0])
        p = head
        for i in range(1,len(li)):
            s = ListNode(li[i])
            p.next = s
            p=p.next
        
        p.next = None
        
        return head
```
<span class="title2">结果：</span>
>执行用时 : 44 ms, 在Reverse Linked List的Python提交中击败了36.40% 的用户
内存消耗 : 15.7 MB, 在Reverse Linked List的Python提交中击败了16.62% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>44 ms</td><td>15.7MB</td><td>python</td></tr></table>

## <span class="red">我们使用指针（推荐）</span>
图解：
![e](/images/201905/2019-05-03_195208.jpg "三个指针情况")

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None or head.next==None:
            return head
        pre = None
        cur = head
        
        while cur:
            #由于如果cur为None的时候，是没有next的，所以我们将它放置到了循环中，判断非None生效，故而在这里声明我们的饿next指针
            next = cur.next

            #我们的移动逻辑
            cur.next = pre
            pre = cur
            cur = next

        return pre  #cur一定是None

```
<span class="title2">结果：</span>
>执行用时 : 36 ms, 在Reverse Linked List的Python提交中击败了100.00% 的用户
内存消耗 : 13.3 MB, 在Reverse Linked List的Python提交中击败了50.21% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>13.3MB</td><td>python</td></tr></table>
<span class="title2">分析：</span>
如果用两个指针，如下图，是不好操作的。
![e](/images/201905/2019-05-03_200551.jpg "两个指针情况")

