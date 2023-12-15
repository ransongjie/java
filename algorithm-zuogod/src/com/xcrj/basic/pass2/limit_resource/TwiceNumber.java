package com.xcrj.basic.pass2.limit_resource;
/**
 * 40亿个无符号整数, 所有出现两次的数
 * ref: https://blog.csdn.net/aa5305123/article/details/83097006
 * 
 * v1: bit[] arr=new bit[UINT_MAX*2]
 * 
 * 步骤：
 * 1. bit[] arr=new bit[UINT_MAX*2]
 * 2. 遍历40亿个无符号整数, value数操作, bit[value*2], bit[value*2+1]
 * - 第0次value: bit[value*2]=0, bit[value*2+1]=0
 * - 第1次value: bit[value*2]=0, bit[value*2+1]=1
 * - 第2次value: bit[value*2]=1, bit[value*2+1]=0
 * - 第3次value: bit[value*2]=1, bit[value*2+1]=1
 * - 状态转变，顺序从上往下&&每次只能转变1步
 * 
 * 问题：
 * 1. bit[] arr=new bit[UINT_MAX*2]
 * - bit[], idx/2=value, 下标与值有关
 */
public class TwiceNumber {
    
}
