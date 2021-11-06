---
title: leetcode-23. 合并K个排序链表
date: 2019-9-16 19:43:12
comments: true
categories: "leetcode"
tags: 
    - leetcode 困难难度
    - 分治法
---
## 23. 合并K个排序链表
### 题目描述
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
>输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6

### 思路分析
先看看分治法解决问题：
``` python
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def mergeKLists(self, lists):
        # lists: List[ListNode]
        # rtype: ListNode
        if len(lists) == 0:
            return None
        elif len(lists) == 1:
            return lists[0]
        else:
            mid = len(lists) // 2
            u, v = lists[:mid], lists[mid:]
            left = self.mergeKLists(u)
            right = self.mergeKLists(v)
            if left  is None:
                return right
            if right is None:
                return left
            return self.merge(left, right)


    def merge(self, a, b):
        i = left_list = a
        j = right_list = b
        temp_node = ptr = ListNode(0)
        while i and j:
            if i.val < j.val:
                ptr.next = i
                i = i.next
                ptr = ptr.next
            else:
                ptr.next = j
                j = j.next
                ptr = ptr.next
        while i is not None:
            ptr.next = i
            i = i.next
            ptr = ptr.next
        while j is not None:
            ptr.next = j
            j = j.next
            ptr = ptr.next
        return temp_node.next

    def printNode(self, node):
        i = node
        result = []
        while i:
            result.append(i.val)
            i = i.next
        print(result)

if __name__ == '__main__':
    a = ListNode(1)
    b = ListNode(0)
    c = ListNode(5)
    d = ListNode(1)
    e = ListNode(3)
    f = ListNode(4)
    g = ListNode(2)
    h = ListNode(6)

    list = [a, b]

    # Solution().printNode(Solution().mergeKLists(list))
    Solution().printNode(Solution().merge(a, b))
```

结果：
![](/images/201909/2019-09-16_194338.png)


### 暴力法
看了分治法解决问题，这里也可以使用暴力法来解决问题。
先将所有的数据读取到一个列表中，然后排序该列表，最后分装成需要的形式。
``` python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeKLists(self, lists):
        # test
        node_list = []
        for node in lists:
            while node:
                node_list.append(node.val)
                node = node.next
        node_list.sort()
        dummy_head = ptr = ListNode(0)
        for i in node_list:
            a = ListNode(i)
            ptr.next = a
            ptr = ptr.next
        return dummy_head.next
```

结果：
![](/images/201909/2019-09-16_200113.png)

---

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
