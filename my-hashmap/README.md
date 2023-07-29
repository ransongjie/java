# 手写HashMap
![手写HashMap](img/self_hashmap.png)
- 散列表既是一种查找结构，也是一种存储结构
- 开散列表：拉链法，由关键字表和值存储结构构成
- capacity: 容量=数组长度，有默认容量
- size: 大小, 散列表中实际存储元素个数
- LOAD_FACTOR>size/capacity=size/length