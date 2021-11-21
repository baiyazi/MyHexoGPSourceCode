---
title: 二项式定理
date: 2019-9-23 08:59:54
comments: true
categories: "数学"
tags: 
    - 数学
    - 动态规划
---
## 二项式定理
>二项式定理（英语：binomial theorem），又称牛顿二项式定理，由艾萨克·牛顿于1664年、1665年间提出。该定理给出两个数之和的整数次幂诸如展开为类似项之和的恒等式。二项式定理可以推广到任意实数次幂，即广义二项式定理。

<img src="/images/201909/2019-09-23_091040.png">
<img src="/images/201909/2019-09-23_091124.png">
<img src="/images/201909/2019-09-23_091201.png">
注：以上图片均来自<a href="https://www.cnblogs.com/wanghai0666/p/10429916.html">cnblogs-静雅斋数学</a>

### 案例
计算二项式系数<span style="font-weight:bolder;font-size:24px;">C<sub>n</sub><sup style="margin-left:-10px;margin-bottom:-2px;">k</sup></span>
``` python
def calc_bin(n, k):
    if k == 0 or k == n :
        return 1
    else:
        return calc_bin(n - 1, k) + calc_bin(n - 1, k - 1)


if __name__ == '__main__':
    print(calc_bin(3, 2))  # 3
    print(calc_bin(5, 3))  # 10
```

### 动态规划
那么，该如何使用动态规划？



