package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 001. 整数除法
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 * 整数除法的结果应当截去（truncate）其小数部分
 * 假设我们的环境只能存储 32 位有符号整数 数值范围是 [−2^31, 2^31−1]。本题中，如果除法结果溢出，则返回 2^31 − 1
 * <p>
 * 微机原理：原码 反码 补码
 * 原码就是人使用的二进制
 * 计算机内部计算都是按照补码进行计算
 * 正数：原码=反码=补码
 * 负数：原码=符号位取1，反码=符号位取1+数值位取反，补码=反码+末位加1=原码的取反加1
 * <p>
 * 有符号数的补码二进制表示：byte为例
 * Integer.toHexString(v) 输出的是补码
 * -128~127
 * 0x80->0x7f
 * 1000,0000-+1->0111,1111
 * <p>
 * java位运算符
 * 位移运算符会不会导致变量原值变化
 * << 左移，低位补0。相当于*2
 * >> 右移，高位补符号位。相当于/2
 * >>> 右移 高位补0
 * <p>
 * 运算符优先级
 * << >> >>>的运算符优先级比算数运算符（包括累加累减）都低。比比较运算符优先级都高。+= -=组合运算符的优先级最低
 */
public class Solution1 {
    /**
     * 加法运算符
     */
    public static int divide(int a, int b) {
        int temp = 0;
        int quotient = 0;
        if (a > 0 && b > 0) {
            while (true) {
                temp += b;
                if (temp <= a) {
                    quotient++;
                } else {
                    return quotient;
                }
            }
        }

        if (a < 0 && b < 0) {
            while (true) {
                temp += b;
                if (temp >= a) {
                    quotient++;
                } else {
                    return quotient;
                }
            }
        }

        if (a > 0 && b < 0) {
            while (true) {
                a += b;
                if (a >= 0) {
                    quotient--;
                } else {
                    return quotient;
                }
            }
        }

        if (a < 0 && b > 0) {
            while (true) {
                a += b;
                if (a <= 0) {
                    quotient--;
                } else {
                    return quotient;
                }
            }
        }

        return quotient;
    }

    /**
     * 左移运算符
     */
    public static int divide2(int a, int b) {
        // Integer.MIN_VALUE/-1=-Integer.MIN_VALUE 超出表示范围了
        // 先处理特殊情况
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        // 同号判断
        boolean flag = false;
        if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
            flag = true;
        }
        // a, b取负数，负数比整数表示范围大1
        long dividend = a > 0 ? -a : a;
        long divisor = b > 0 ? -b : b;
        // -被除数>-除数，被除数<除数
        // 先处理特殊情况
        if (dividend > divisor) {
            return 0;
        }
        int result = 0;
        // ！！！使用shift记录除数小于被除数所需要的位移次数。后面让1也位移shift次数即可得到结果
        int shift = 31;
        // -被除数<=-除数，被除数>=除数
        // 除数什么时候小于被除数
        while (dividend <= divisor) {
            // 被除数<除数，不停左移
            // 除数称多少个2才比被除数更小
            while (dividend > divisor << shift) {
                shift--;
            }
            // 被除数-除数的部分继续
            dividend -= divisor << shift;
            // 获取商
            result += 1 << shift;
        }
        return flag ? result : -result;
    }

    public static void main(String[] args) {
        System.out.println(divide(9, 1));
    }
}
