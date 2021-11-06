---
title: leetcode-3 | 无重复字符的最长子串 中等难度
date: 2019-4-19 21:22:09
comments: true
categories: "leetcode"
tags: 
    - leetcode 中等难度
    - 滑动窗口
---
# 3. 无重复字符的最长子串（Longest Substring Without Repeating Characters）
Given a string, find the length of the longest substring without repeating characters.

><span>Example 1:</span>
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
<span>Example 2:</span>
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
<span>Example 3:</span>
>Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
><span>示例 1:</span>
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

## 解决方案
### 方法一：暴力法
思路
逐个检查所有的子字符串，看它是否不含有重复的字符。
* 假设开始和结束的索引分别为 i 和 j。那么我们有 0 ≤ i < j ≤ n 。因此，使用 i 从0到 n - 1以及 j 从 i+1 到 n 这两个嵌套的循环，我们可以枚举出 s 的所有子字符串。
* 要检查一个字符串是否有重复字符，我们可以使用集合。我们遍历字符串中的所有字符，并将它们逐个放入 set 中。在放置一个字符之前，我们检查该集合是否已经包含它。如果包含，我们会返回 false。循环结束后，我们返回 true。

leetcode给的参考代码：
``` java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
}
```

仿造一个python的：
``` python
class Solution:
    def lengthOfLongestSubstring(self, s):
        def unique(s, i,j):
            se = set()
            while i<j:
                if s[i] in se:
                    return False
                se.add(s[i])
                i+=1
            return True

        res = 0
        for i in range(len(s)):
            j = i+1
            while j<=len(s):
                if unique(s,i,j):
                    res = max(res, j-i)
                j+=1

        return res
```

但是实际上，是运行超时的。`（986 / 987 个通过测试用例）`
时间复杂度：`O(n^3)` 。

### 方法二：滑动窗口
暴力法非常简单。但它太慢了。那么我们该如何优化它呢？

在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。如果从索引 i 到 j - 1之间的子字符串 sij已经被检查为没有重复字符。我们只需要检查 s[j] 对应的字符是否已经存在于子字符串 sij中。

要检查一个字符是否已经在子字符串中，我们可以检查整个子字符串，这将产生一个复杂度为 O(n^2) 的算法，但我们可以做得更好。

通过使用 HashSet 作为滑动窗口，我们可以用 O(1)的时间来完成对字符是否在当前的子字符串中的检查。
例如，我们将 [i, j)向右滑动 1 个元素，则它将变为 [i+1,j+1)（左闭，右开）。

我们使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。 然后我们向右侧滑动索引 j，如果它不在 HashSet 中，我们会继续滑动 j。直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。如果我们对所有的 ii 这样做，就可以得到答案。
``` java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
```

python仿写如下，思想很好，使用滑动窗口。从0开始判断，如果在集合中，我们除去首位，此时j指针并不后移，所以判断的还是上一次判断的位置，此时再次判断，存在集合中，还是除去首位，也就是缩减窗口大小。：
``` python
class Solution:
    def lengthOfLongestSubstring(self, s):
        se = set()
        ans, i,j=0,0,0
        while i<len(s) and j<len(s):
            if s[j] not in se:
                se.add(s[j])
                j+=1
                ans = max(ans, j-i)
            else:
                se.remove(s[i])
                i+=1   #每次i移动一位
        return ans

if __name__ == '__main__':
    b = Solution().lengthOfLongestSubstring("afabcad")
    print(b)
```

但是，还是不理想。
>执行用时 : 212 ms, 在Longest Substring Without Repeating Characters的Python3提交中击败了26.50% 的用户
内存消耗 : 13.2 MB, 在Longest Substring Without Repeating Characters的Python3提交中击败了59.81% 的用户
987 / 987 个通过测试用例
状态：通过
执行用时：212 ms

时间复杂度：O(2n) = O(n)

### 方法三：优化的滑动窗口
上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。 当我们找到重复的字符时，我们可以立即跳过该窗口。

也就是说，如果 s[j] 在 [i, j) 范围内有与 j'重复的字符，我们不需要逐渐增加 i 。 我们可以直接跳过 [i，j']范围内的所有元素，并将 i 变为 j' + 1 。
Java代码：
``` java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); 
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
```

python 仿造：
``` python
class Solution:
    def lengthOfLongestSubstring(self, s):
        se = dict()
        ans, i, j = 0, 0, 0
        while j<len(s):
            if s[j] in se:
                i = max(i, se[s[j]]) #i等于se[s[j]]时，也就是上一个j'，需要变成j'+1为下一个窗口的起始位置，这里也可以：i = max(i, se[s[j]]+1)
                #print(i)
            ans = max(ans, j-i+1) #当判断是“a”一个字符的时候，j-i=0-0=0，而实际上是1,故而加入j-i+1
            se.update({s[j]:j+1}) # j'+1放入了这里，做了统一处理，使字典中所有的元素都加1，使得下标从1开始
            #print(se)
            j+=1
        return ans
```


><span>结果：</span>
>执行用时 : 192 ms, 在Longest Substring Without Repeating Characters的Python3提交中击败了29.89% 的用户
内存消耗 : 13.1 MB, 在Longest Substring Without Repeating Characters的Python3提交中击败了96.90% 的用户

时间复杂度：O(n)
虽然比上一个执行效率要好一些，但这里的执行效率还是不怎么高，这就可能是我自己程序的问题了。

### 自己练习的滑动窗口
``` python
class Solution:
     def lengthOfLongestSubstring(self, s):
        #定义滑动窗口[i,j]  0 0
        i,j = 0,-1
        length = 0
        se = set()
        while i<len(s):
            if j+1<len(s) and s[j+1] not in se:
                j += 1
                se.add(s[j])
            else:
                se.remove(s[i])
                i+=1
            length = max(length, j-i+1)

        return length
```
><span>结果：</span>
执行用时 : 136 ms, 在Longest Substring Without Repeating Characters的Python3提交中击败了49.78% 的用户
内存消耗 : 13.3 MB, 在Longest Substring Without Repeating Characters的Python3提交中击败了45.10% 的用户

### 比较好的滑动窗口代码
``` python
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        #定义滑动窗口[i,j]  i=0,j=-1 表示当前滑动窗口无元素
        i,j = 0, -1
        #窗口的长度,初始长度是0,因为题设求最长，故而我们需要用max函数，所以初始值设置小点
        lenght = 0
        #定义集合存放数据
        se = set()
        #窗口的前端扫描到末尾为止
        while i<len(s):
            #如果窗口右端元素没有在窗口中，那么我们窗口右扩展1位
            # 当然，首次窗口右边j=-1，故而判断的是s[j+1]
            if j+1<len(s) and s[j+1] not in se:
                j+=1
                se.add(s[j])
            else:
                se.remove(s[i])
                i+=1
            lenght = max(lenght, len(se))
            
        return lenght
```

><span>结果：</span>
执行用时 : 104 ms, 在Longest Substring Without Repeating Characters的Python提交中击败了27.04% 的用户
内存消耗 : 12.1 MB, 在Longest Substring Without Repeating Characters的Python提交中击败了38.37% 的用户
<table><tr><td>提交时间</td><td>状态</td><td>执行用时</td><td>内存消耗</td><td>语言</td></tr><tr><td>几秒前</td><td>通过</td><td>104 ms</td><td>12.1MB</td><td>python</td></tr></table>
