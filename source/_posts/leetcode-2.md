---
title: leetcode-2 | 两数相加  中等难度
date: 2019-4-19 09:22:09
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
---
# 2. 两数相加（Add Two Numbers）
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

><span>Example:</span>
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
## 思路
可能会有这种思想：将链表中的数据还原成为数值类型，然后做完加法操作，还原成为列表。但是，我们需要考虑数值类型所能表示的最大的范围，是不可取的。
><span>Python 支持三种不同的数值类型：</span>
* <span>整型(Int)</span> - 通常被称为是整型或整数，是正或负整数，不带小数点。Python3 整型是没有限制大小的，可以当作 Long 类型使用，所以 Python3 没有 Python2 的 Long 类型。
* <span>浮点型(float)</span> - 浮点型由整数部分与小数部分组成，浮点型也可以使用科学计数法表示（2.5e2 = 2.5 x 102 = 250）
* <span>复数( (complex))</span> - 复数由实数部分和虚数部分构成，可以用a + bj,或者complex(a,b)表示， 复数的实部a和虚部b都是浮点型。

><span>这里介绍一个取位的思想：</span>
a = 12345
取个位 : b = (a / 1) % 10 = a % 10
取十位： b = (a / 10) % 10
取百位： b = (a / 100) % 10

比较神奇的就是，python3整数没有大小的限制！但是，也不能采用。
* 按照出题人思路，应该是要考察链表的理解
* 如果采用，需要对数字完成逐个取值，很浪费时间
* 代码量也比较高

![想输入的提示名字，可不输入](/images/201904/2019-04-18_220932.jpg "图解")

``` python
# 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
# 输出：7 -> 0 -> 8
# 原因：342 + 465 = 807

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        p,q = l1, l2
        carry = 0
        resultnode = None
        #使用or判断+下面的‘三元运算符’统一化处理，一次性处理了长链和断链情况
        while p!=None or q!=None:
            a = p.val if p!=None else 0
            b = q.val if q!=None else 0
            #这里也是比较巧妙的统一化处理
            sum = (carry + a + b) % 10
            carry = (carry + a + b) // 10
            listn = ListNode(sum)

            if resultnode==None:
                resultnode = listn
                flagnode = resultnode
            else:
                flagnode.next = listn
                flagnode = flagnode.next

            # 加入判断是否为None，是因为为None时报错：'NoneType' object has no attribute 'next'
            if p!=None:
                p = p.next
            if q!=None:
                q = q.next

        if carry != 0:      #处理进位值，如最后值为7 + 8，则需要增加一个节点存储进位值
            listn = ListNode(carry)
            flagnode.next = listn

        return resultnode

```

><span>结果：</span>
>执行用时 : 80 ms, 在Add Two Numbers的Python提交中击败了78.72% 的用户
内存消耗 : 11.9 MB, 在Add Two Numbers的Python提交中击败了20.89% 的用户

---

网站给出的解答中，Java代码的解法很直观：

---

```
伪代码如下：
* 将当前结点初始化为返回列表的哑结点。
* 将进位 carry 初始化为 0。
* 将 p 和 q 分别初始化为列表 l1 和 l2 的头部。
* 遍历列表 l1 和 l2 直至到达它们的尾端。
	* 将 x 设为结点 p 的值。如果 p 已经到达 l1 的末尾，则将其值设置为 0。
	* 将 y 设为结点 q 的值。如果 q 已经到达 l2 的末尾，则将其值设置为 0。
	* 设定 sum = x + y + carry。
	* 更新进位的值，carry = sum / 10。
	* 创建一个数值为 (sum mod 10) 的新结点，
	并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
	* 同时，将 p 和 q 前进到下一个结点。
* 检查 carry = 1 是否成立，如果成立，则向返回列表追加一个含有数字 1 的新结点。
* 返回哑结点的下一个结点。

请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
```


``` java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
```