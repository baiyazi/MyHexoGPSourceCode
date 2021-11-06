---
title: leetcode-102 | 二叉树的层次遍历
date: 2019-9-13 17:13:11
comments: true
categories: "leetcode"
toc: false
tags: 
    - leetcode 中等难度
    - 二叉树
---
## 102 . 二叉树的层次遍历
### 题目描述
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: [3,9,20,null,null,15,7],
![](/images/201909/2019-09-13_171509.png)
返回其层次遍历结果：`[[3],[9,20],[15,7]]`

## 解答
### 思考
数据结构中也学过，用到队列这种数据结构来处理。
其过程就是访问根，将左右节点加入队列，然后直到队列为空。
由于每一层的节点需要封装到一个列表，故而我们需要有一个标记，来判断当前层是否遍历完毕，然后再开启下一层。


``` python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        que = [root]
        result = []
        while len(que) > 0:
            size = len(que)
            temp_que = []
            while size:
                root = que.pop(0)
                temp_que.append(root.val)
                if root.left is not None:
                    que.append(root.left)
                if root.right is not None:
                    que.append(root.right)
                size = size - 1
            result.append(temp_que)
        return result
```

结果：
![](/images/201909/2019-09-14_162221.png)

### 自定义一个新函数
做题的时候，总是局限于给定的函数，觉得就该在这个方法内完成。
的确，在处理层数的时候，用递归，并且传入level，层数是来自父节点，也就是递归的前一次调用。
``` python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        levels=[]
        if not root:
            return levels
        def helper(node,level):
            if len(levels)==level:
                levels.append([])
            levels[level].append(node.val)
            if node.left:
                helper(node.left,level+1)
            if node.right:
                helper(node.right,level+1)
        helper(root,0)
        return levels
```

### 方法三
也是记录层数信息，但是充分利用列表下标的特性
``` python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        levels = []
        if not root:
            return levels
        result = []
        que = [[root, 0]] # 第0层
        while len(que) > 0:
            el = que.pop(0)
            root = el[0]
            level = el[1]
            if len(result) == level:
                result.append([])
            result[level].append(root.val)
            if root.left is not None:
                que.append([root.left, level + 1])
            if root.right is not None:
                que.append([root.right, level + 1])
        return result
```

上面不仅下标处理很好，对于level的处理也是很妙。
类似临时变量level
结果：
![](/images/201909/2019-09-15_093029.png)

---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal