---
title: leetcode-94 | 二叉树的中序遍历
date: 2019-7-28 14:28:25
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 栈
    - 二叉树的遍历
---
# 题目描述
给定一个二叉树，返回它的 中序 遍历。

 示例:
>输入: [1,null,2,3]  
   `1`
    &nbsp;&nbsp;`\`
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`2`
    &nbsp;&nbsp;`/`
   `3` 
输出: [1,3,2]

## 思路解答
上一题是先序遍历，中序遍历的递归算法很简单，也就是调整代码的逻辑就可以了，先来实现以下。
### 递归方式
先序遍历：根左右
中序遍历：左根右

``` python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def __init__(self):
        self.resu = []
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root:
            self.inorderTraversal(root.left)
            self.resu.append(root.val)
            self.inorderTraversal(root.right)
            
        return self.resu
```

### 运行结果
>执行用时 : 16 ms, 在Remove Nth Node From End of List的Python提交中击败了94.75% 的用户
内存消耗 : 11.6 MB, 在Remove Nth Node From End of List的Python提交中击败了39.25% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>16 ms</td><td>11.6 MB</td><td>python</td></tr></table>

### 模拟系统栈
模拟系统栈
中序调用：左根右

``` Python
self.preorderTraversal(root.left)
self.resu.append(root.val)
self.preorderTraversal(root.right)
```

我们如果用栈模拟,由于执行的时候是左、根、右的顺序。
那么我们用栈模拟，的压栈顺序就是：右根左


``` python
class Solution(object):
    def __init__(self):
        self.resu = []
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []
        stack = [('go', root)]
        # 中序：左 根 右 访问  =》 压栈顺序：右 根 左
        while len(stack):
            # 取栈顶元素
            ele = stack.pop()
            # 逻辑
            if ele[0] == 'print':
                self.resu.append(ele[1].val)
            else:
                if ele[1].right:                    # 右
                    stack.append(('go', ele[1].right))
                stack.append(('print', ele[1]))    # 根
                if ele[1].left:                    # 左
                    stack.append(('go', ele[1].left))

        return self.resu
``` 

### 运行结果
>执行用时 : 20 ms, 在Remove Nth Node From End of List的Python提交中击败了84.51% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了31.70% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>20 ms</td><td>11.7 MB</td><td>python</td></tr></table>


### 栈

``` python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def __init__(self):
        self.resu = []
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if not root:
            return []
        stack = [root]
        root = root.left  # 上面已经root入栈，故而接下来不能重复入栈
        while len(stack) or root:
            if root:
                stack.append(root)
                root = root.left
            else:
                root = stack.pop()
                self.resu.append(root.val)
                root = root.right
        return self.resu
```

### 运行结果
>执行用时 : 28 ms, 在Remove Nth Node From End of List的Python提交中击败了45.48% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了30.19% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>28 ms</td><td>11.7 MB</td><td>python</td></tr></table>


### 栈-另一种实现

``` Python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def __init__(self):
        self.resu = []
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []
        stack = [root]
        root = root.right
        while len(stack) or root:
            if root:
                stack.append(root)
                root = root.right
            else:
                root = stack.pop()
                self.resu.append(root.val)
                root = root.left
        self.resu.reverse()
        return self.resu
```

### 运行结果

>执行用时 : 24 ms, 在Remove Nth Node From End of List的Python提交中击败了66.49% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了34.59% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.9 MB</td><td>python</td></tr></table>


---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/