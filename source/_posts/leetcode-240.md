---
title: leetcode-240. 搜索二维矩阵 II 
date: 2019-9-21 15:00:25
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
---
## 240. 搜索二维矩阵 II
### 题目描述
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：
>[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

给定 target = 5，返回 true。
给定 target = 20，返回 false。

## 解答
### 思路一
思路也就是每次遍历首行和首列，如果矩阵为空或者矩阵首个元素大于target就返回False，如果在首行或者首列中找到了，就返回True。
当然，边界条件比较多，我是提交了几次，然后处理的边界。代码如下：
``` python
class Solution:
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        # 暴力法
        # 预防[[]]这种情况
        if len(matrix) == 0:
            return False
        # [[],[1,2,3]] 预防这种情况的矩阵
        if len(matrix[0]) == 0:
            subMatrix = []
            for i in range(1, len(matrix)):  # 不包括首行
                temp = matrix[i][1:]
                if len(temp) > 0:
                    subMatrix.append(temp)
            return self.searchMatrix(subMatrix, target)
        # 找顶部的一行和最左边的一列
        for i in matrix[0]:
            if i == target:
                return True
        for i in range(len(matrix)):
            print(matrix[i][0], target)
            if matrix[i][0] == target:
                return True
        # 截取矩阵，也就是去掉第一行、第一列后的矩阵
        subMatrix = []
        for i in range(1, len(matrix)): # 不包括首行
            temp = matrix[i][1:]
            if len(temp) > 0:
                subMatrix.append(temp)
        # 边界条件 ①矩阵为空，没找到，就返回False
        if len(subMatrix) == 0:
            return False
        # 边界条件 ②矩阵的第一个元素大于target，必然返回False
        elif subMatrix[0][0] > target:
            return False
        return self.searchMatrix(subMatrix, target)
```

结果：
![](/images/201909/2019-09-21_153026.png)

### 思路二
当然还有更加暴力的解法，因为是矩阵，不妨一个元素一个元素的取出来，然后判断。
``` python
class Solution:
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        for row in matrix: # row
            for ele in row:
                if ele == target:
                    return True
        return False
```

我们看结果：
![](/images/201909/2019-09-21_153451.png)

#### 两种方法对比
![](/images/201909/2019-09-21_153540.png)
虽然前一种暴力法，使用了数据本身有序的特点，做了边界处理，避免了额外的计算。
但是，由于使用的是递归的访问方式，也就导致了对内存消耗的加剧。
这里也不难记起，以前数据结构中学过的：“递归调用，最好用栈来改进。能用循环解决的就不要用递归。”

### 思路三
由于这个题目所处的位置是“分治法”，故而也企图找到一种思路用分治来解决。
这里我画了一个简略的图来解释这个过程：
![](/images/201909/2019-09-21_154536.png)
但是实现比较复杂，这里就不写代码实现了。

### 思路四
在leetcode网站的解答区，看见了这么一个思路，如下图：
<img src="/images/201909/2019921 160525.gif">

<img src="/images/201909/2019921 160535.gif">

相信，看图也就明白了这个思路的巧妙之处！
代码实现也比较简单：
``` python
class Solution:
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        # 以左下角开始
        if len(matrix) == 0:
            return False
        row = len(matrix)
        col = len(matrix[0])
        finded = False
        i, j = row - 1, 0
        while not finded and (i >= 0 and j < col):
            if matrix[i][j] == target:
                return True
            elif matrix[i][j] > target:
                i = i - 1
            elif matrix[i][j] < target:
                j = j + 1
        return False
```

结果：
<img src="/images/201909/2019-09-21_161547.png">


---


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii