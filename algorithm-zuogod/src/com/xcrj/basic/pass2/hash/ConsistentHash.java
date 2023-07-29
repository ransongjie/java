package com.xcrj.basic.pass2.hash;
/**
 * 一致性hash
 * 顺时针分配数据环
 * 1. machine_hostname MD5_hash range
 * 2. sort range
 * 3. data hash_value, 二分 <=hash_value最左的 machine_range
 * 减少机器
 * - 交给顺时针下一台机器
 * 增加机器
 * - 要回顺时针下一台机器本该输入新增机器的数据
 * 问题
 * 1. 初始分配不均
 * 2. 增减机器分配不均
 * 解决
 * - 虚拟节点技术，machine(昵称1, 昵称2,...,昵称n)
 * - m1, m2, m3的所有昵称 MD5_hash range。昵称在环上均匀和离散分布
 * - 本质把蛋糕分的很小，每个昵称被分配的数据少，加上昵称也被hash，有均匀性，最终增减机器负载分配均匀
 * 优点
 * - 机器能力强，可以给这台机器更多的虚拟结点（昵称）
 */
public class ConsistentHash {
    
}
