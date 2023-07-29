package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 考察：
 * 散列表统计
 * 或运算拼接 result |= (1 << i);
 */
public class Solution4 {
    /**
     * 普通蛮力法 遍历
     */
    public int singleNumber(int[] nums) {
        int count = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = nums[i];

            for (int j = 0; j < nums.length; j++) {
                if (result == nums[j]) {
                    count++;
                }
                if (count > 1) {
                    break;
                }
            }

            if (count == 1) {
                return result;
            }

            count = 0;
        }
        return result;
    }

    /**
     * 散列表
     */
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // findFirst可能有多个只出现1次的数
        return map.entrySet().stream().filter(entry -> entry.getValue() == 1).findFirst().get().getKey();
    }

    /**
     * 依次计算出现1次元素的每一个二进制位，再使用或运算拼接出出现1次元素
     * 方法三：求出了只出现1次的整数的每个二进制位
     * - 所有整数的第i位之和total=m*3*1+n*3*0+1*0/1
     */
    public int singleNumber3(int[] nums) {
        int result = 0;
        // 32因为int是32位的
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            /**
             * 求数组中所有元素第i个二进制位的和total
             * 因为数组中只有出现3次或1次的元素
             * 因此total=3*m*1+3*n*0+0/1，m第i位为1的出现3次的元素的个数，n第i位为0的出现3次的元素的个数，出现1次的元素的第i位是0或者1
             * 因此出现1次的元素的第i位=total%3
             */
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            // 已知 出现1次元素的每一位 通过或运算拼接出出现1次的元素。0左移之后还是0不用或运算拼接
            if (total % 3 == 1) {
                result |= (1 << i);
            }
        }
        return result;
    }

    /**
     * 心得：所有位同时进行运算
     * 数字电路可以对新加入的十进制数的所有位同时进行运算
     *
     * <p>
     * 方法四：考点，数字电路设计（编码，真值表）
     * - 前m个整数第i位之和%3的余数使用二进制编码后存储到变量$a_i$,$b_i$中
     * - 所有整数第i位之和%3的余数为0/1二进制编码为$(a_i,b_i)=(0,0)$/$(a_i,b_i)=(0,1)$，因此要知道“所有整数第i位之和%3的余数是0还是1，返回$b_i$即可“，最终的结果就是b
     * - 使用数字电路同时计算前m个整数的所有位（第1位，第2位，... ，第32位），只需要循环m（整数个数）即可，不需要循环i（第i位）
     * - 能使用数字电路求解的前提是位运算结果能够转换为真值表
     * - 简图：数字电路可以，同时按列对所有位进行位运算，$a_{1~32位},b_{1~32位}$同时计算得出
     * - 第1个整数：0000,0000,0000,0000,0000,0000,0000,0000
     * - 第2个整数：0000,0000,0000,0000,0000,0000,0000,0001
     * - ...
     * - 第k个整数：0100,0000,0000,0000,0000,0000,0000,0000
     * - 循环增加新的整数，再同时按列对所有位进行位运算
     */
    public int singleNumber4(int[] nums) {
        // 0个整数按列对所有位进行位运算（数字电路运算）
        int a = 0;
        int b = 0;
        for (int num : nums) {
            // 对先输入的num按列对所有位进行位运算（数字电路运算）
            int aNext = (~a & b & num) | (a & ~b & ~num);
            int bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }

    /**
     * 方法五：
     * - 方法四中计算b的规则简单，a的规则复杂。
     * - 方法四是同时计算a，b。方法五改为分开计算，先计算b再使用b计算a
     */
    public int singleNumber5(int[] nums) {
        int a = 0;
        int b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.singleNumber5(new int[]{2, 2, 0, 1, 2, 1, 1}));
    }
}
