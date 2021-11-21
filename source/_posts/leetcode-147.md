---
title: leetcode-147 | 对链表进行插入排序  
date: 2019-5-8 17:48:41
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 题目描述
对链表进行插入排序。
![e](/images/201905/Insertion-sort-example-300px.gif)

插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

插入排序算法：
* 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
* 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
* 重复直到所有输入数据插入完为止。

><span>示例 1：</span>
输入: 4->2->1->3
输出: 1->2->3->4

><span>示例 1：</span>
输入: -1->5->3->4->0
输出: -1->0->3->4->5



# 思路解答
思路比较简单，由于链表是从前到后，故而我们的插入排序也是从前到后，这里和图示的不一样。
然后就是比较。
编程的过程中，忘记了当前待插入结点值比有序链表中所有值都大的情况，调试了半天，终于发现了
不得不说边界很重要。

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def insertionSortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None or head.next is None:
            return head
        #正式开始
        #设置虚拟头结点
        virturalhead = ListNode(0)
        virturalhead.next = None
        vp = virturalhead
        p = cur = head
        #由于链表不是双向链表，这里我们从前到后扫描
        while cur:
            #按照要求，需要先移除元素，然后插入
            #记录下一趟链表头结点
            p = cur.next
            # 将当前结点从链表中删除
            cur.next = None
            if vp.next is None:
                vp.next = cur
                cur = p
                continue
            else:
                while vp:
                    pre = vp
                    temp = vp.next
                    #最后一个，比有序链表中所有的都大，自然，无法比较
                    if temp is None:
                        #到了最终位置，直接插入
                        pre.next = cur
                        break


                    # 定义指针同步移动
                    # 判断大小
                    if temp.val < cur.val:
                        # 大了，说明在后面，就同步移动指针
                        pre = temp
                        temp = temp.next
                    else:
                        # 小于等于，说明是插入位置
                        cur.next = temp
                        pre.next = cur
                        #插入完成，跳出循环
                        break
                    vp = vp.next

            # 恢复vp
            vp = virturalhead
            #由于递归的条件我这里设置成cur，故而，重新赋值cur为下一趟开始的链表头结点
            cur = p

        return virturalhead.next
        
```


<span class="title2">结果：</span>
>执行用时 : 3012 ms, 在Insertion Sort List的Python提交中击败了13.48% 的用户
内存消耗 : 15.1 MB, 在Insertion Sort List的Python提交中击败了35.71% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>3012 ms</td><td>15.1MB</td><td>python</td></tr></table>
## 专空子
因为leetcode官网中，提交数据，谁管你是怎么实现的，只要结果对，时间空间合适就可以。
使用列表存储，然后排序，然后组成相应的数据结构

``` python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def insertionSortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        #边界
        if head is None or head.next is None:
            return head
        #正式处理
        li = []
        p = head
        while p:
            li.append(p)
            p = p.next

        #排序
        li.sort(key=lambda x:x.val)
        virtualhead = ListNode(0)
        vp = virtualhead
        for i in li:
            vp.next = i
            vp = vp.next
        vp.next = None

        return virtualhead.next
        
```

<span class="title2">结果：</span>
>执行用时 : 100 ms, 在Insertion Sort List的Python提交中击败了84.27% 的用户
内存消耗 : 15.3 MB, 在Insertion Sort List的Python提交中击败了6.43% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>100 ms</td><td>15.3MB</td><td>python</td></tr></table>
当然了，使用辅助的数据结构排序和重构的方式不止这一种，这只是一个引例。其余的都差不多。
