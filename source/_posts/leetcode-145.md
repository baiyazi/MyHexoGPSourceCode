---
title: leetcode-145 | 二叉树的后序遍历
date: 2019-7-28 15:13:38
comments: true
categories: "leetcode"
tags: 
    - leetcode 困难难度
    - 栈
    - 二叉树的遍历
---

# 题目描述
给定一个二叉树，返回它的 后序 遍历。

 示例:
>输入: [1,null,2,3]  
   `1`
    &nbsp;&nbsp;`\`
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`2`
    &nbsp;&nbsp;`/`
   `3` 
输出: [1,3,2]

## 思路解答
### 递归方式
先序遍历：根左右
中序遍历：左根右
后序遍历：左右根

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
    def postorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root:
            self.postorderTraversal(root.left)
            self.postorderTraversal(root.right)
            self.resu.append(root.val)
            
        return self.resu
        
```

### 运行结果
>执行用时 : 32 ms, 在Remove Nth Node From End of List的Python提交中击败了26.67% 的用户
内存消耗 : 11.8 MB, 在Remove Nth Node From End of List的Python提交中击败了25.12% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.8 MB</td><td>python</td></tr></table>

### 模拟系统栈
后序遍历：左右根
也即是：
``` Python
self.postorderTraversal(root.left)
self.postorderTraversal(root.right)
self.resu.append(root.val)
```

那么我们使用栈模拟依次压栈的顺序就是：根右左

``` Python
class Solution(object):
    def __init__(self):
        self.resu = []
    def postorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []
        stack = [('go', root)]
        # 后序： 左 右 根 访问  =》 压栈顺序： 根 右  左
        while len(stack):
            # 取栈顶元素
            ele = stack.pop()
            # 逻辑
            if ele[0] == 'print':
                self.resu.append(ele[1].val)
            else:
                stack.append(('print', ele[1]))  # 根
                if ele[1].right:                    # 右
                    stack.append(('go', ele[1].right))
                if ele[1].left:                    # 左
                    stack.append(('go', ele[1].left))


        return self.resu
```

### 运行结果
>执行用时 : 28 ms, 在Remove Nth Node From End of List的Python提交中击败了47.42% 的用户
内存消耗 : 11.6 MB, 在Remove Nth Node From End of List的Python提交中击败了40.55% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>28 ms</td><td>11.6 MB</td><td>python</td></tr></table>


### 栈
参考我的CSDN博客：[地址](https://blog.csdn.net/qq_26460841/article/details/84139711)
![](/images/201907/20181116151257109.png)
先序序列： `1 、2 、3 、5 、4`
后序序列：` 3 、5、2 、4 、1`
把后续序列逆序得，逆后序序列：`1 、4 、2 、5 、3`
观察，逆后序列和先序序列有一定联系，逆序后的序列可以看做：**先序序列中对其左右子树遍历顺序交换得到的结果。**
过程如下：
1. 根据根划分出左右的子树（先序序列）   ： `1  、 [2 、3 、5]  、 [4]`
2. 交换根的左右子树遍历序列       ：`1 、 [4]  、 [2 、 3  、 5]`
3. 交换以2为根的左右子树遍历序列   ：`1 、 [4]  、 [2 、 5  、 3]`
4. 整体逆序得到后序序列                                      ：      `3 、 5 、 2 、 4 、 1`

``` python
class Solution(object):
    def __init__(self):
        self.resu = []
    def postorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []
        stack = [root]
        while len(stack):
            root = stack.pop()
            self.resu.append(root.val)
            if root.left:
                stack.append(root.left)
            if root.right:
                stack.append(root.right)
        self.resu.reverse()
        return self.resu

```

### 运行结果
>执行用时 : 24 ms, 在Remove Nth Node From End of List的Python提交中击败了67.42% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了32.09% 的用户

<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>24 ms</td><td>11.7 MB</td><td>python</td></tr></table>

---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/submissions/