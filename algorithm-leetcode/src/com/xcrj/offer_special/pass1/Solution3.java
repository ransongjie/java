package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 * <p>
 * 考点：
 * 发现规律（动态规划）
 * r&1奇偶特性：r为奇数结果为1，r为偶数结果为0
 * r>>1奇偶特性：r为奇数或偶数结果相等，结果都是除以2取整
 */
public class Solution3 {
    // 内存和耗时都大
    // % /运算符
    public int[] countBits(int n) {
        int[] result = new int[n + 1];

        // t负责运算，r负责索引
        for (int r = 0; r < n + 1; r++) {
            int t = r;
            while (true) {
                int remainder = t % 2;
                if (1 == remainder) result[r]++;
                int quotient = t / 2;
                if (1 == quotient) {
                    result[r]++;
                }
                if (quotient < 2) {
                    break;
                }
                t = quotient;
            }
        }

        return result;
    }

    // 内存和耗时都大
    // >> 运算符
    public int[] countBits2(int n) {
        int[] result = new int[n + 1];

        // t负责运算，r负责索引
        for (int r = 0; r < n + 1; r++) {
            int t = r;
            while (true) {
                int remainder = t % 2;
                if (1 == remainder) result[r]++;
                int quotient = t >> 1;
                if (1 == quotient) {
                    result[r]++;
                }
                if (quotient < 2) {
                    break;
                }
                t = quotient;
            }
        }

        return result;
    }

    // 内存和耗时都大
    // & 按位与 << 左移
    public int[] countBits3(int n) {
        int[] result = new int[n + 1];

        // t负责运算，r负责索引
        for (int r = 0; r < n + 1; r++) {
            int t = r;
            for (int i = 0; i < 32; i++) {
                if (0 != (t & 1 << i)) {
                    result[r]++;
                }
            }
        }
        return result;
    }

    /**
     * 动态规划+位运算
     * dp[2n+1]-1=dp[2n]：奇数一定比前一个数（偶数）多1，二进制表示末尾的1
     * dp[2n]=dp[n]：偶数一定比此偶数小一半的数，1的个数一样多。2n是偶数，2n/2=n是偶数，偶数的二进制表示最后1位都是0，除2相当于右移1位
     */
    public int[] countBits4(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int r = 1; r < n + 1; r++) {
            // 偶数
            if (0 == r % 2) {
                result[r] = result[r >> 1];
            } else {
                /**
                 *  r为奇数时
                 *  result[r] = result[r - 1] + 1;
                 *  r-1就是偶数
                 *  result[r - 1]=result[(r - 1)>>1]
                 *  (r - 1)>>1等于r>>1
                 *  result[(r - 1)>>1]=result[r>>1]
                 *  所以
                 *  result[r] = result[r >>1]+1
                 */
                result[r] = result[r >> 1] + 1;
            }
        }
        return result;
    }

    /**
     * 动态规划+位运算
     * dp[2n+1]-1=dp[2n]：奇数一定比前一个数（偶数）多1，二进制表示末尾的1
     * dp[2n]=dp[n]：偶数一定比此偶数小一半的数，1的个数一样多。2n是偶数，2n/2=n是偶数，偶数的二进制表示最后1位都是0，除2相当于右移1位
     */
    public int[] countBits5(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int r = 1; r < n + 1; r++) {
            /**
             *  统一 result[r >> 1]和result[r - 1]
             *
             *  r为奇数时
             *  result[r] = result[r - 1] + 1;
             *  r-1就是偶数
             *  result[r - 1]=result[(r - 1)>>1]
             *  (r - 1)>>1等于r>>1
             *  result[(r - 1)>>1]=result[r>>1]
             *  所以
             *  result[r] = result[r >>1]+1
             */
//            if (0 == r % 2) {
//                result[r] = result[r >> 1];
//            } else {
//                result[r] = result[r >> 1] + 1;
//            }

            /**
             * 去掉奇偶判断
             * 当r是偶数，不+1
             * 当r是奇数，要+1
             *
             * r是偶数时，r&1=0
             * r是奇数时，r&1=1
             */
            result[r] = result[r >> 1] + (r & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(Arrays.toString(solution3.countBits5(5)));
    }
}
