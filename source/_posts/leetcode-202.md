---
title: leetcode-202 | 快乐数 
date: 2019-4-25 18:11:42
comments: true
categories: "leetcode"
tags: 
    - leetcode 简单难度
---
## 202. 快乐数（Happy Number）

编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

><span>示例：</span>
输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1



## 思路
### 方法一： 按照题意编程
我们，需要编程尝试：

``` python
def getNumbers(self, n):
    li = []
    while n:
        li.append(n - n // 10 * 10)
        n = n // 10
    return li

def isHappy(self, n):
    """
    :type n: int
    :rtype: bool
    """
    #对n我们需要得到他的各个位
    li = self.getNumbers(n)
    print(li)
    while n!=1:
        sum = 0
        for i in li:
            sum += i*i
        li = self.getNumbers(sum)
        print(li)
        import time
        time.sleep(1)
```

然后输出的结果：
①当测试的数据等于189
>[9, 8, 1]
[6, 4, 1]
[3, 5]
[4, 3]
[5, 2]
[9, 2]
[5, 8]
[9, 8]
[5, 4, 1]
[2, 4]
[0, 2]
[4]
[6, 1]
[7, 3]
[8, 5]
[9, 8]
[5, 4, 1]
不难看出这是一个循环

②再次随机取一个数658
>[8, 5, 6]
[5, 2, 1]
[0, 3]
[9]
[1, 8]
[5, 6]
[1, 6]
[7, 3]
[8, 5]
[9, 8]
[5, 4, 1]

发现虽然是循环但是，取值的情况真的很多。所以我们不妨，将之加入到列表中，每次判断是否存在。

然后我们的代码如下：
```python
class Solution(object):
    def getNumbers(self, n):
        li = []
        while n:
            li.append(n - n // 10 * 10)
            n = n // 10
        return li

    def isHappy(self, n):
        """
        :type n: int
        :rtype: bool
        """
        #对n我们需要得到他的各个位
        li = self.getNumbers(n)
        result = []
        while n!=1:
            n = 0
            for i in li:
                n += i*i
            li = self.getNumbers(n)
            if li not in result:
                result.append(li)
            else:
                return False
        return True
        
```

><span>结果：</span>
执行用时 : 36 ms, 在Happy Number的Python提交中击败了80.61% 的用户
内存消耗 : 11.5 MB, 在Happy Number的Python提交中击败了36.44% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>36 ms</td><td>11.5MB</td><td>python</td></tr></table>

