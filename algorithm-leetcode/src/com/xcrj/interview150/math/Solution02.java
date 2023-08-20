package com.xcrj.interview150.math;

/**
 * https://leetcode.cn/problems/powx-n
 * pow(x,n)
 */
public class Solution02 {
    /**
     * 递归 快速幂
     * x^64
     * =x*x*...*x 64次
     * =x^32*x^32
     * =(x^16*x^16)*(x^16*x^16)
     * =...
     * =(x^2)*...*(x^2)
     * =(x*x)*...*(x*x)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return n >= 0 ? f(x, (long) n) : 1.0 / f(x, -(long) n);
    }

    private double f(double x, long n) {
        //x^0=1.0
        if (n == 0) return 1.0;
        //x^(n/2)=y
        double y = f(x, n / 2);
        //n是偶数 y*y, 奇数 y*y*x
        return (n & 1) == 0 ? y * y : y * y * x;
    }

    /**
     * 迭代 快速幂
     * 递归 快速幂中 额外乘的x 与 n的二进制表示1有关
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        return n >= 0 ? g(x, (long) n) : 1.0 / g(x, -(long) n);
    }

    private double g(double x, long n) {
        double ans = 1.0;
        while (n > 0) {
            //n最后1位是1时，求1次结果
            /**
             * n=77= 1 0 0 1 1 0 1
             * x^64 x^32 x^16 x^8 x^4 x^2 x^1
             * x^77=n的二进制表示为1时的权值之积=x^1 * x^4 * x^8 * x^64
             */
            if ((n & 1) == 1) {
                ans *= x;
            }
            //
            x *= x;
            n /= 2;
        }

        return ans;
    }
}
