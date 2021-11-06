---
title: leetcode-150 | 逆波兰表达式求值
date: 2019-7-27 14:40:02
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 栈
---
# 题目描述
根据[逆波兰表示法](https://baike.baidu.com/item/%E9%80%86%E6%B3%A2%E5%85%B0%E5%BC%8F/128437)，求表达式的值。
有效的运算符包括 `+`, `-`,` *`, `/ `。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
说明：
* 整数除法只保留整数部分。
* 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

示例 1：
>输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9

示例 2：
>输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6

示例 3：
>输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

## 思路解答
逆波兰表示法，也即是后缀表达式。根据上面的几个案例，我们很容易的想到了栈的数据结构来辅助处理问题。
1. 遇到 `+`, `-`,` *`, `/ `，就顺序出栈两个数值，进行计算，然后压栈
2. 重复上面的操作，直到输入的列表跑完
3. 输入的列表中存储的是字符串里列表，所以还需要转换

## 代码实现

``` python
class Solution(object):
    def evalRPN(self, tokens):
        """
        :type tokens: List[str]
        :rtype: int
        """
        numbers = ["+","-","*","/"]
        dataStack = []
        for ele in tokens:
            if ele not in numbers:
                dataStack.append(int(ele))
            else:
                a = dataStack.pop(len(dataStack)-1)
                b = dataStack.pop(len(dataStack) - 1)
                if ele == '+':
                    dataStack.append(a+b)
                elif ele == '-':
                    dataStack.append(b-a)
                elif ele == '*':
                    dataStack.append(b*a)
                elif ele == '/':
                    dataStack.append(int(b / float(a)))
        return dataStack[0]
```

注意：
`python`的 `b / a` 会向下取整， 比如 `-1 / 132 = -1`。 题目要求是取整数部分，那么负数的时候，实际应该是向上取整， 解决方法： `int(b / float(a))`
python3 b / a 会转为小数计算，所以直接` int(b / a)`， 不能 `b // a`
### 运行结果

>执行用时 : 76 ms, 在Remove Nth Node From End of List的Python提交中击败了100.00% 的用户
内存消耗 : 13.5 MB, 在Remove Nth Node From End of List的Python提交中击败了31.95% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>76 ms</td><td>13.5 MB</td><td>python</td></tr></table>

---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
