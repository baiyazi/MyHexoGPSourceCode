---
title: leetcode-999 |  车的可用捕获量  
date: 2019-6-3 18:35:21
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
    - 数组
---
# 999. 车的可用捕获量
在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。

车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。

返回车能够在一次移动中捕获到的卒的数量。

<span>示例 1：</span>
![e](/images/201906/1253_example_1_improved.PNG)
>输入：
[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释：
在本例中，车能够捕获所有的卒。

<span>示例 2：</span>
![e](/images/201906/1253_example_2_improved.PNG)
>输入：
[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：0
解释：
象阻止了车捕获任何卒。

<span>示例 3：</span>
![e](/images/201906/1253_example_3_improved.PNG)
>输入：
[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释： 
车可以捕获位置 b5，d6 和 f5 的卒。

><span>提示：</span>
board.length == board[i].length == 8
board[i][j] 可以是 'R'，'.'，'B' 或 'p'
只有一个格子上存在 board[i][j] == 'R'

## 思考
看着题目挺难得，认真读一下题就会发现其实就是判断白色车所在行列中，可以吃几个黑色的卒。

``` python 
class Solution(object):
    def numRookCaptures(self, board):
        """
        :type board: List[List[str]]
        :rtype: int
        """
        #首先定位R所在的位置
        i = j = 0
        posx , posy = 0,0
        flag = False
        while i < len(board):
            j = 0
            while j < len(board[i]):
                if board[i][j] == 'R':
                    flag = True
                    posx = i
                    posy = j
                j += 1
            if flag:
                break
            i += 1

        #找到后R的位置就是[i][j]
        #然后需要判断所在行列
        #行方向
        i , j = posx, posy
        lcol = j - 1
        count = 0
        while lcol >= 0:
            if board[i][lcol] == 'B':
                break
            if board[i][lcol] == "p":
                count+=1
                break
            lcol-=1

        rcol = j + 1
        while rcol < 8:
            if board[i][rcol] == 'B':
                break
            if board[i][rcol] == "p":
                count += 1
                break
            rcol += 1

        #列方向
        trow = i - 1
        while trow >= 0:
            if board[trow][j] == 'B':
                break
            if board[trow][j] == "p":
                count+=1
                break
            trow -= 1

        brow = i + 1
        while brow < 8:
            if board[brow][j] == 'B':
                break
            elif board[brow][j] == "p":
                count+=1
                break
            brow += 1
        return count
```

<span class="title2">结果：</span>
>执行用时 : 32 ms, 在Available Captures for Rook的Python提交中击败了88.14% 的用户
内存消耗 : 11.6 MB, 在Available Captures for Rook的Python提交中击败了21.05% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.6MB</td><td>python</td></tr></table>
