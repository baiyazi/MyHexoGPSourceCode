---
title: leetcode-234 | 回文链表  
date: 2019-5-13 18:10:07
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 链表
---
# 题目描述
请判断一个链表是否为回文链表。

><span>示例 1：</span>
输入: 1->2
输出: false

><span>示例 1：</span>
输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？


# 思路解答
回文判断，很简单的想到栈来判断
也不妨将数据加入列表中，然后用首尾指针遍历判断
也可以直接使用python中的链表反转，然后判断相等

``` python
class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None or head.next is None:
            return True
        
        p = head
        li = []
        while p:
            li.append(p.val)
            p = p.next
        rp = li.copy()
        li.reverse()
        
        return li==rp
```


<span class="title2">结果：</span>
>执行用时 : 96 ms, 在Palindrome Linked List的Python3提交中击败了94.91% 的用户
内存消耗 : 23.9 MB, 在Palindrome Linked List的Python3提交中击败了27.37% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>96 ms</td><td>23.9MB</td><td>python</td></tr></table>
## 直接操作链表
还是使用链表反向的思路。

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
            
            cur.next = pre
            pre = cur
            cur = next
        
        return pre
    def getlength(self, head):
        n = 0
        p = head
        while p:
            p = p.next
            n +=1
        
        return n
    
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if head is None or head.next is None:
            return True
        
        #类似的，反转链表，然后判断
        length = self.getlength(head)
        mid = length//2
        pre, cur = None, head
        while mid:
            pre = cur
            cur = cur.next
            mid -= 1
        
        #判断奇数偶数,奇数，再移动一次
        if length%2!=0:
            pre = cur
            cur = cur.next
        
        pre.next = None
        
        left = head
        right = self.reverse(cur)
        
        while left and right:
            if left.val != right.val:
                return False
        
            left = left.next
            right = right.next
        
        return True
        
```

<span class="title2">结果：</span>
>执行用时 : 104 ms, 在Palindrome Linked List的Python提交中击败了64.41% 的用户
内存消耗 : 31 MB, 在Palindrome Linked List的Python提交中击败了17.87% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>104 ms</td><td>31MB</td><td>python</td></tr></table>

## 简化
``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def getlength(self, head):
        n = 0
        p = head
        while p:
            p = p.next
            n +=1
        
        return n
    
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if head is None or head.next is None:
            return True
        
        #类似的，反转链表，然后判断
        length = self.getlength(head)
        mid = length//2
        cur = head
        stack = []
        #一次性开关
        flag = True
        while cur:
            if mid:
                stack.append(cur.val)
                cur = cur.next
                mid -= 1
            else:
                #判断奇数偶数,奇数，再移动一次
                if length%2!=0 and flag:
                    cur = cur.next
                    flag = False
                #取栈顶元素判断，当前的结点的值是否相等
                if cur.val != stack.pop():
                    return False
                cur = cur.next
        
        return True
```

<span class="title2">结果：</span>
>执行用时 : 76 ms, 在Palindrome Linked List的Python提交中击败了99.44% 的用户
内存消耗 : 30.9 MB, 在Palindrome Linked List的Python提交中击败了28.32% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>76 ms</td><td>30.9 MB</td><td>python</td></tr></table>
