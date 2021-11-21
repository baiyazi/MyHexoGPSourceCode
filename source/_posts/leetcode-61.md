---
title: leetcode-61 |  旋转链表 
date: 2019-5-12 20:46:19
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

><span>示例 1：</span>
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

><span>示例 2：</span>
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL


# 思路解答
初一看，和删除倒数第k个结点有些类似，但是却实际不同，因为k值可以大于链表的长度
不妨将它链接成一个环形链表，但是由于链表指向的是下一个节点，这里我们可以采用两次翻转
不妨看看图解：
![e](/images/201905/2019-05-12_204458.jpg)


``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def getlength(self, head):
        p = head
        n = 0
        while p:
            n+=1
            p = p.next
        return n
    
    def reversel(self, head):

        pre = None
        cur = head
        while cur:
            next = cur.next

            cur.next = pre
            pre = cur
            cur = next

        return pre, head

    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if head is None or head.next is None:
            return head
        length = self.getlength(head)
        # 翻转链表
        newhead, oldhead = self.reversel(head)
        # 链接成环
        oldhead.next = newhead
        # 从老节点的位置处，移动k个位置
        p = oldhead
        
        #比如当链表长度是3的时候，我们移动3次就会回到原链表，故而我们可以使用
        k = k % length
        
        while k:
            p = p.next
            k -= 1

        # 到达最终头，需要断链，然后翻转
        end = p.next
        p.next = None
        newhead, oldhead = self.reversel(end)
        
        return newhead
```

<span class="title2">结果：</span>
>执行用时 : 40 ms, 在Rotate List的Python提交中击败了52.38% 的用户
内存消耗 : 11.7 MB, 在Rotate List的Python提交中击败了31.41% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>40 ms</td><td>11.7MB</td><td>python</td></tr></table>
## 优化
始终是末尾节点放置到头结点，所以这种移动可以看做是循环链表中头结点的改变，看下图：
![e](/images/201905/2019-05-13_165909.jpg)
不妨采用和删除倒数第K个结点类似的处理方式，就是用双指针，next和cur的距离刚好是需要移动的次数的余数，K = K % length，以减少计算次数。

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def getlength(self, head):
        p = head
        n = 0
        while p:
            n+=1
            p = p.next
        return n
    
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if head is None or head.next is None:
            return head
        length = self.getlength(head)
        
        k = k % length
        #k==0，说明不用移动
        if k==0:
            return head
        
        #nextpre记录next的前一个结点，也就是在while循环退出后，指向最后一个结点
        pre,cur, nextpre, next = None,head, head, head
        while next:
            if k:
                next = next.next
                k-=1
            else:
                pre = cur
                cur = cur.next
                nextpre = next
                next = next.next
        
        #循环完毕，短链
        pre.next = None
        #尾首相连
        nextpre.next = head
        
        return cur
```

<span class="title2">结果：</span>
>执行用时 : 28 ms, 在Rotate List的Python提交中击败了100.00% 的用户
内存消耗 : 11.8 MB, 在Rotate List的Python提交中击败了28.27% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>28 ms</td><td>11.8MB</td><td>python</td></tr></table>
