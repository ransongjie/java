package com.xcrj.basic.pass2.bit;
/**
 * 加减乘除，只能使用位运算符
 */
public class AddSubMulDiv {
    public static void main(String[] args) {
        System.out.println(add(2, 3));//5
        System.out.println(sub(3, 3));//0
        System.out.println(mul(2, 3));//6
        System.out.println(div(Integer.MIN_VALUE, -2));//1
    }

    /**
     * 无进位相加 与 进位单独考虑
     * @param a
     * @param b
     * @return
     */
    public static int add(int a,int b) {
        int a1=(a^b);
        int b1=(a&b)<<1;
        while(b1!=0){
            a=a1^b1;//无进位相加结果
            b=(a1&b1)<<1;//进位结果
            a1=a;
            b1=b;
        }

        return a1;
    }

    /**
     * a-b=a+(-b)
     * @param a
     * @param b
     * @return
     */
    public static int sub(int a,int b) {
        return add(a, opp(b));
    }

    /**
     * 相反数
     * -a=(~a+1)
     * @param a
     * @return
     */
    private static int opp(int a) {
        return add(~a, 1);
    }

    /**
     * a*b=a*2^i=a<<k次1
     * b转化为多个2相乘，a*2，左移1位
     * @param a
     * @param b
     * @return
     */
    public static int mul(int a,int b) {
        int r=0;
        while(b!=0){
            if((b&opp(b))==1){//最右侧的1是否等于1
                r=add(r, a);
            }
            a=a<<1;
            b=b>>>1;
        }

        return r;
    }

    /**
     * 二进制除法，先把大的先减掉，一直到余数<除数
     * - b尽可能左移<=a 等价于 a尽可能右移>=b
     * - 余：a=a-(b<<i)
     * - 商：r|=(1<<i)
     * @param a
     * @param b
     * @return
     */
    public static int div(int a,int b) {
        if(b==0) throw new IllegalArgumentException("b=0");
        if(a==Integer.MIN_VALUE&&b==Integer.MIN_VALUE) return 1;
        if(a!=Integer.MIN_VALUE&&b==Integer.MIN_VALUE) return 0;
        //divide中是转为正数计算的, -Integer.MIN_VALUE 将越界溢出
        //(a-((a+1)/b*b))/b+(a+1)/b
        if(a==Integer.MIN_VALUE&&b!=Integer.MIN_VALUE) {
            int m1=divide((add(a, 1)), b);
            int m2=mul(m1, b);
            int m3=sub(a, m2);
            int m4=div(m3, b);
            return add(m4, m1);
        }

        return divide(a, b);
    }

    /**
     * a/b=2^i(商)...余数
     * @param x
     * @param y
     * @return
     */
    private static int divide(int x,int y) {
        //转为正数计算
        int a=x<0?opp(x):x;
        int b=y<0?opp(y):y;

        int r=0;
        for (int i = 31; i >-1; i=sub(i, 1)) {
            if((a>>i)>=b){//a/b=2^i(商)...余数
                a=sub(a,b<<i);//余数 a=a-(b<<i)
                r|=(1<<i);//商
            }
        }

        /**
         * a b 一正一负 结果为负
         * a b 符号相同 结果为正
         */
        return (a<0)^(b<0)?opp(r):r;
    }
}
