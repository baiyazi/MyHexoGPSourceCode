---
title: leetcode-144 | 二叉树的前序遍历
date: 2019-7-27 15:47:22
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 栈
    - 二叉树的遍历
---

# 题目描述
给定一个二叉树，返回它的 前序 遍历。

 示例:
>输入: [1,null,2,3]  
   `1`
    &nbsp;&nbsp;`\`
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`2`
    &nbsp;&nbsp;`/`
   `3` 
输出: [1,2,3]

## 思路解答
以前写过，不过忘记了，在我的`CSDN`博客中有：[地址](https://blog.csdn.net/qq_26460841/article/details/84137802)
很明显，学过数据结构的都直到最简单的是使用一个递归的函数来轻松解决问题。
这里我们不妨用栈来模拟一下，毕竟递归也是栈的应用。
两种方式都写一写
其实，观察例子，是具有欺骗性的。例子中输入的是列表，我们看看代码提示：
``` python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
```

也就是说，传入的是节点，不难知晓一定是根节点。

## 代码实现
### 递归方式
首先，看看递归的实现：

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
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root!=None:
            self.resu.append(root.val)
            self.preorderTraversal(root.left)
            self.preorderTraversal(root.right)
        return self.resu
```

### 运行结果

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.8 MB</td><td>python</td></tr></table>


### 模拟系统栈
模拟系统栈
先序遍历：根左右

``` Python
self.resu.append(root.val)
self.preorderTraversal(root.left)
self.preorderTraversal(root.right)
```

我们如果用栈模拟,由于执行的时候是根左右的顺序
那么我们用栈模拟，的压栈顺序就是：右左根

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
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []
        stack = [('go', root)]
        # 先序： 根 左 右 访问  =》 压栈顺序：右  左  根
        while len(stack):
            # 取栈顶元素
            ele = stack.pop()
            # 逻辑
            if ele[0] == 'print':
                self.resu.append(ele[1].val)
            else:
                if ele[1].right:                    # 右
                    stack.append(('go', ele[1].right))
                if ele[1].left:                    # 左
                    stack.append(('go', ele[1].left))
                stack.append(('print', ele[1]))  # 根


        return self.resu
```
### 运行结果

>执行用时 : 24 ms, 在Remove Nth Node From End of List的Python提交中击败了69.00% 的用户
内存消耗 : 11.8 MB, 在Remove Nth Node From End of List的Python提交中击败了30.76% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.8 MB</td><td>python</td></tr></table>

### 栈改进
上面提交成功了，说明的确是根节点。下面就用栈来改进。
按照先序的思想，可以转化为循环控制。
1. 入栈前，先输出本节点信息；
2. 先左子树依次入栈，直到没有左子树的结点为止；
3. 出栈栈顶元素，然后，找到右子树，继续①②操作，直到栈为空 且 结点为空。


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
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        stack = []
        p = root
        while p or len(stack)!=0:
            if p:
                self.resu.append(p.val)
                stack.append(p)
                p=p.left
            else:
                p = stack.pop(len(stack)-1)
                p = p.right
        
        return self.resu

```

### 运行结果

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>20 ms</td><td>11.9 MB</td><td>python</td></tr></table>


### 非递归-另一种实现

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
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        p = root
        if p is None:
            return []
        stack = [p]
        while len(stack):
            # 取栈顶
            p = stack.pop()
            self.resu.append(p.val)
            # right
            if p.right:
                stack.append(p.right)
            if p.left:
                stack.append(p.left)
        return self.resu

```

### 运行结果

>执行用时 : 28 ms, 在Remove Nth Node From End of List的Python提交中击败了48.97% 的用户
内存消耗 : 11.8 MB, 在Remove Nth Node From End of List的Python提交中击败了29.88% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>28 ms</td><td>11.8 MB</td><td>python</td></tr></table>


---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal