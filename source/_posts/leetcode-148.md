---
title: leetcode-148 | 排序链表  
date: 2019-5-8 17:48:41
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 链表
    - 暂时没有解答
---
# 题目描述
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

><span>示例 1：</span>
输入: 4->2->1->3
输出: 1->2->3->4

><span>示例 2：</span>
输入: -1->5->3->4->0
输出: -1->0->3->4->5




# 思路解答
由于题目要求空间复杂度是 O(1)，因此不能使用递归。因此这里使用 bottom-to-up 的算法来解决。

bottom-to-up 的归并思路是这样的：先两个两个的 merge，完成一趟后，再 4 个4个的 merge，直到结束。举个简单的例子：
`[4,3,1,7,8,9,2,11,5,6]`

>step=1: (3->4)->(1->7)->(8->9)->(2->11)->(5->6)
step=2: (1->3->4->7)->(2->8->9->11)->(5->6)
step=4: (1->2->3->4->7->8->9->11)->5->6
step=8: (1->2->3->4->5->6->7->8->9->11)

以上的思想来自评论区，看到这里，觉得很厉害。
然后，就想到了前面的k个k个一组翻转链表

``` python
        
```


<span class="title2">结果：</span>
>执行用时 : 3012 ms, 在Insertion Sort List的Python提交中击败了13.48% 的用户
内存消耗 : 15.1 MB, 在Insertion Sort List的Python提交中击败了35.71% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>3012 ms</td><td>15.1MB</td><td>python</td></tr></table>
## 专空子


``` python
        
```

<span class="title2">结果：</span>
>执行用时 : 100 ms, 在Insertion Sort List的Python提交中击败了84.27% 的用户
内存消耗 : 15.3 MB, 在Insertion Sort List的Python提交中击败了6.43% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>100 ms</td><td>15.3MB</td><td>python</td></tr></table>
