package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 */
public class Solution4 {
    /**
     * 散列表统计每个数字出现的次数
     */
    public int singleNumber1(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        return map.entrySet().stream().filter(entry->entry.getValue()==1).findFirst().get().getKey();
    }

    /**
     * 所有数字第i位之和%3=只出现1次元素的第i位值
     * 数组中只有出现1次或3次的数字，每1位取值为0或1
     */
    public int singleNumber2(int[] nums) {
        int r=0;
        // 处理每1位
        for(int i=0;i<32;i++){
            // 所有数字第i位之和
            int sum=0;
            for(int num:nums){
                // xcrj 获取第i位的值
                sum+=(num>>i)&1;
            }
            // 获取只出现1次元素的第i位值
            if(1==sum%3){
                // xcrj
                // (1<<i) 让第i位是1
                // 或运算拼接出全值
                r|=(1<<i);
            }
        }
        return r;
    }

    /**
     * 数字电路
     *
     * 上面是两个数的第i位进行运算，1次看1列
     * 这里是两个数的所有位同时进行运算，1次看所有列
     * - 第1个整数：0000,0000,0000,0000,0000,0000,0000,0000
     * - 第2个整数：0000,0000,0000,0000,0000,0000,0000,0001
     * - ...
     * - 第k个整数：0100,0000,0000,0000,0000,0000,0000,0000
     * 数字电路
     * - 前m个整数第i位之和%3的余数使用二进制编码后存储到变量$a_i$,$b_i$中
     * - 所有整数第i位之和%3的余数为0/1二进制编码为$(a_i,b_i)=(0,0)$/$(a_i,b_i)=(0,1)$，因此要知道“所有整数第i位之和%3的余数是0还是1，返回$b_i$即可“，最终的结果就是b
     */
    public int singleNumber3(int[] nums) {
        int a = 0;
        int b = 0;
        for (int num : nums) {
            int aNext = (~a & b & num) | (a & ~b & ~num);
            int bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }

    /**
     * 上面计算b的规则简单，a的规则复杂
     * 上面是同时计算a，b
     * 这里是分开计算a，b，先计算b再使用b计算a
     */
    public int singleNumber4(int[] nums) {
        int a = 0;
        int b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }
}
