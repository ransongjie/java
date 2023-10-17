# 介绍

|包名|说明||
|---|---|---|
|offer_special|剑指offer_专项突破版|`https://leetcode.cn/problem-list/e8X3pBZi/`|
|interview150|面试经典 150 题|`https://leetcode.cn/studyplan/top-interview-150/`|
|||

# 技巧

|名称|题目|
|---|---|
|多路归并 多指针|heap/Solution02|
|并查集|graph/Solution04|
|字典树|dict_tree|
|二维前缀和|divide_conquer/Solution02|

# 题目

1. 子数组和子串都是连续的

# offer_special/bit
```text
微机原理：原码 反码 补码
原码就是人使用的二进制
计算机内部计算都是按照补码进行计算
正数：原码=反码=补码
负数：原码=符号位取1，反码=符号位取1+数值位取反，补码=反码+末位加1=原码的取反加1
<p>
有符号数的补码二进制表示：byte为例
Integer.toHexString(v) 输出的是补码
-128~127
0x80->0x7f
1000,0000-+1->0111,1111
<p>
java位运算符
位移运算符会不会导致变量原值变化
<< 左移，低位补0。相当于*2
>> 右移，高位补符号位。相当于/2
>>> 右移 高位补0
<p>
运算符优先级
<< >> >>>的运算符优先级比算数运算符（包括累加累减）都低。比比较运算符优先级都高。+= -=组合运算符的优先级最低
```