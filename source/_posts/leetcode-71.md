---
title: leetcode-71 | 简化路径
date: 2019-7-27 15:11:33
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 栈
---
# 题目描述
以 `Unix` 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
在 `Unix` 风格的文件系统中，一个点`（.）`表示当前目录本身；此外，两个点 `（..）` 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：`Linux / Unix`中的绝对路径 vs 相对路径
请注意，返回的规范路径必须始终以斜杠 `/` 开头，并且两个目录名之间必须只有一个斜杠 `/`。最后一个目录名（如果存在）不能以 `/` 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
示例 1：
>输入："/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。

示例 2：
>输入："/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。

示例 3：
>输入："/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。

示例 4：
>输入："/a/./b/../../c/"
输出："/c"

示例 5：
>输入："/a/../../b/../c//.//"
输出："/c"

示例 6：
>输入："/a//b////c/d//././/.."
输出："/a/b/c"

## 思路解答
我们观察例子：
1. 给定字符串如果最后有`/`，那么删除掉
2. 遇到`../`，就退到上一级`/*`，没得退的就到根
3. 遇到`//`，就简化成`/`
4. 遇到`./`，也就是直接删除掉

也不放简化一下，为了方便存储，也就是，不考虑/
如：`"/a//b////c/d//././/.."`
`a`，入栈；`b`，入栈；`c`，入栈；`d`，入栈
一个点，跳过
遇到两个点，`d`出栈

## 代码实现

``` python
class Solution(object):
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        dataStack = []
        # 不妨整成列表
        path_list = path.split('/')
        for i in path_list:
            # ['', 'a', '..', '..', 'b', '..', 'c', '', '.', '', '']
            if i == '' or i=='.':
                pass
            elif i == '..':
                if len(dataStack) >= 2:
                    dataStack.pop(len(dataStack)-1)
                else:
                    if len(dataStack)==1:
                        dataStack[0] = '/'
                    else:
                        dataStack.append('/')
            else:
                dataStack.append(i)
        # print(dataStack)
        # 下面就是对结果数据的处理
        if len(dataStack)==0:
            return '/'
        if dataStack[0] != '/':
            return '/' + "/".join(dataStack)
        else:
            return '/' + "/".join(dataStack[1::])  # 截取字符串
```

### 运行结果

>执行用时 : 32 ms, 在Remove Nth Node From End of List的Python提交中击败了100.00% 的用户
内存消耗 : 11.7 MB, 在Remove Nth Node From End of List的Python提交中击败了31.95% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>32 ms</td><td>11.7 MB</td><td>python</td></tr></table>

---
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/simplify-path