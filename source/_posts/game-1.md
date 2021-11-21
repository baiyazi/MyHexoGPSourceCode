---
title: 贪吃蛇
date: 2019-9-29 14:31:53
author: 无涯明月
comments: true
categories: "Android"
tags: 
    - 贪吃蛇
---
暑假就看见了贪吃蛇吃满屏的图片。如下图：
<img src="/images/201909/20140228083107155.gif">
以前写过，然后没成功。
昨天用了一个下午写了一个简单的自动判断路径的贪吃蛇，姑且命名为1.0版本。
这里简单记录一下。
## 计算每个点到食物的距离
### 思考1-用轴计算每个格子的代价
当然，蛇身体节点就不用计算到食物的距离，因为蛇不允许走自己的身体节点位置。
下面是简易的图示：
<img src="/images/201909/2019-09-29_142915.png">
如上图，计算了关于食物点的横向和纵向的每个格子到食物的代价。
然后，无论是左上角、左下角还是右上角、右下角的计算都是在其前一个格子的代价的基础上加1即可。
这里简单举个例子，计算右下角的部分：
<img src="/images/201909/2019-09-29_150556.png">
当然这种情况是比较好的，此时无论是什么方向上的计算，都是一致的。
即基于我们的十字轴，从上往下，以及从右往左，每个节点的代价是一样的。
我们接下来看看比较极端的情况：
<img src="/images/201909/2019-09-29_152615.png">
在这种情况下，十字轴没有拉通，被蛇给阻挡住了。导致了红色区域节点值得二义性。
如何计算红色方框中的面积成为了焦点。因为可以从上往下加一，亦可以从下往上加一或者减一。
>假设：
>我们不妨反过来思考一下，既然红色方框中的难以计算，那么不妨不计算。
因为蛇是走最佳路径，总不可能红色方框是最优的（图中直观感觉），假设红色方框在本次中不可达。

画图验证假设，如下图：
<img src="/images/201909/2019929182108.png">
通过上图，最后产生的新的食物节点，但是由于假设不计算红色方框的节点代价，故而此时蛇是找不到该如何走的。故而上面的假设不成立。
也就是说用"轴"的方式来计算每个格子的代价是行不通的。
**Fail**

### 绘制地图
到了这里其实我还是没有想出来该如何表示代价格子。
为了不在这上面浪费比较多的时间，这里还是用昨天晚上直接坐标计算的方式，忽略二义性，直接计算某个点到食物的距离。这里就不妨绘制出来，姑且叫做地图。
计算方式：
``` python
def calc_ele(globa, food):
    for i in range(len(globa)):
        for j in range(len(globa[0])):
            globa[i][j] = (abs(food[0] - i) + abs(food[1] - j))
```

### 详细代码
``` python
def printG(g):
    for i in g:
        for j in i:
            if j == '*':
                print("%2s" % '■', end="")
            elif j == 0:
                print("%2s" % '●', end="")
            else:
                print("%2s" % "□", end="")
            # print("%4s" % j, end="")
        print()

def calc_ele(globa, food):
    for i in range(len(globa)):
        for j in range(len(globa[0])):
            globa[i][j] = (abs(food[0] - i) + abs(food[1] - j))

def load_sanke(globa, snake):
    for i in snake:
        globa[i[0]][i[1]] = "*"

def snake_move(globa, snake, food):
    if eatfood(snake, food):
        food[0] = random_food(globa, snake, food)[0]
        food[1] = random_food(globa, snake, food)[1]
    else:
        snake.pop()
    head = snake[0]
    x, y = head[0], head[1]
    if x - 1 >= 0:
        x1 = x-1
    else:
        x1 = len(globa) - 1
    if x + 1 < len(globa):
        x2 = x + 1
    else:
        x2 = 0

    if y - 1 >= 0:
        y1 = y - 1
    else:
        y1 = len(globa) - 1
    if y + 1 < len(globa):
        y2 = y + 1
    else:
        y2 = 0

    min_cost = 99
    if not globa[x1][y] == '*':
        if min_cost > globa[x1][y]:
            min_cost = globa[x1][y]
            point = [x1, y]
    if not globa[x2][y] == '*':
        if min_cost > globa[x2][y]:
            min_cost = globa[x2][y]
            point = [x2, y]
    if not globa[x][y1] == '*':
        if min_cost > globa[x][y1]:
            min_cost = globa[x][y1]
            point = [x, y1]
    if not globa[x][y2] == '*':
        if min_cost > globa[x][y2]:
            min_cost = globa[x][y2]
            point = [x, y2]

    snake.insert(0, point)

def eatfood(snake, food):
    if snake[0] == food:
        return True
    return False

def random_food(globa, snake, food):
    import random
    x = random.randint(1, len(globa) - 1)
    y = random.randint(1, len(globa) - 1)
    for node in snake:
        if [x, y] == node:
            x = random.randint(1, len(globa) - 1)
            y = random.randint(1, len(globa) - 1)
            continue
    return [x, y]

def eatself(snake):
    body = snake[1:]
    for node in body:
        if node == snake[0]:
            return True
    return False


if __name__ == '__main__':
    globa = [
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    ]
    food = [15,9]  # 食物的下标
    # 假定:食物Food,头部Head,尾部Tail
    snake = [[4,9],[3,9],[3,10]]
    # 计算每个元素的距离,可以以食物为中心，
    while 1:
        import time,os
        calc_ele(globa, food)
        print("Score:" , len(snake) - 3)
        load_sanke(globa, snake)
        snake_move(globa, snake, food)
        printG(globa)
        time.sleep(0.11)
        os.system('cls')
```

在命令行输入：python a.py就可以运行成功了。
运行算是成功了吧，大致可以吃50个左右的食物。


---
后记：
本来是想今天优化一下的，但是现在都没有解决如何绘制在蛇存在时候每个格子的代价问题。
主要想的是表示了地图，然后用A*、BFS或者DFS任意一种算法来计算，得到最优的前进路线。
但是，地图都没有表示出来！！！







