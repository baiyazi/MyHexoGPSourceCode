---
title: leetcode-103 | 二叉树的锯齿形层次遍历
date: 2019-9-15 13:03:58
comments: true
categories: "leetcode"
toc: false
tags: 
    - leetcode 中等难度
    - 二叉树
---
## 103. 二叉树的锯齿形层次遍历
### 题目描述
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
例如:
给定二叉树: `[3,9,20,null,null,15,7]`,
![](/images/201909/2019-09-13_171509.png)
返回其自底向上的层次遍历为：：`[[3],[20,9],[15,7]]`

## 解答
### 思考
解答了102题和107题，这一题，思路就来了。
还是位置变化。我们需要一个标记，确定是头插还是尾插。

``` python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def zigzagLevelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        result = []
        flag = False
        que = [root]
        while len(que) > 0:
            size = len(que)
            temp_list = []
            flag = not flag
            while size > 0:
                root = que.pop(0)
                if flag:
                    temp_list.append(root.val)
                else:
                    temp_list.insert(0, root.val)
                if root.left is not None:
                    que.append(root.left)
                if root.right is not None:
                    que.append(root.right)
                size = size - 1
            result.append(temp_list)

        return result
```

结果：
![](/images/201909/2019-09-15_131248.png)

### 方法二
和上面的思想一样，殊途同归。

``` python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def zigzagLevelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        result = []
        que = [[root, 0]]
        flag = False
        while len(que) > 0:
            ele = que.pop(0)
            root = ele[0]
            level = ele[1]
            if len(result) == level:
                result.append([])
                flag = not flag
            if flag:
                result[level].append(root.val)
            else:
                result[level].insert(0, root.val)
            if root.left is not None:
                que.append([root.left, level+1])
            if root.right is not None:
                que.append([root.right, level+1])
        return result
```

结果：
![](/images/201909/2019-09-15_132101.png)

---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/submissions/