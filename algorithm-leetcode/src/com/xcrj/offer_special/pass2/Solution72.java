package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 072. 求平方根
 * - 给定一个非负整数 x ，计算并返回 x 的平方根，即实现int sqrt(int x)函数。
 * - 正数的平方根有两个，只输出其中的正数平方根。
 * - 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 */
public class Solution72 {
    /**
     * 袖珍计算器算法
     * 使用其他数学公式（使用语言内置的函数，计算速度快）
     */
    public int mySqrt1(int x) {
        int r=(int)Math.exp(0.5*Math.log(x));
        // ？进行下面的比较 因为上面取整了
        // xcrj long 类型提升防止越界
        return (long)(r+1)*(r+1)>x?r:r+1;
    }

    /**
     * 二分查找k^2仅小于x的k
     */
    public int mySqrt2(int x) {
        int l=0,r=x,o=-1;
        while(l<=r){
            int mid=(l+r)>>1;
            // long 类型提升 防止越界
            // xcrj =x也可以
            if((long)mid*mid<=x){
                o=mid;
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return o;
    }

    /**
     * 牛顿迭代法 浮点
     * - 本质是借助泰勒级数，从初始值开始快速向零点逼近，向真解逼近
     * 
     * y=m^2-c, m是变量
     * 初始c=x, m0=x
     * 切线与x轴的交点越来越平滑，越来越接近于横轴=sqrt(x)的点
     * 不停的求切线与x轴的交点，交点越来越逼近sqrt(x)
     * @param x
     * @return
     */
    public int mySqrt3(int x) {
        if(0==x)return 0;
        double c=x;
        double m0=x;
        double mpre=m0;
        while(true){
            // 求切线与x的交点mi
            double mi=0.5 * (mpre + c / mpre);
            // xcrj 前后两条切线与x轴的交点
            if(Math.abs(mi-mpre)<1e-7){
                return (int)mi;
            }
            mpre=mi;
        }
    }

    /**
     * 牛顿迭代法 整数
     */
    public int mySqrt4(int x) {
        if(0==x)return 0;
        int c=x;
        int m0=x;
        long mpre=m0;
        while(true){
            // 求切线与x的交点mi
            long mi=(long)(0.5 * (mpre + c / mpre));
            // xcrj 切线与x轴的交点与x比较，要求刚好小一点
            if(mi*mi<=x){
                return (int)mi;
            }
            mpre=mi;
        }
    }
}
