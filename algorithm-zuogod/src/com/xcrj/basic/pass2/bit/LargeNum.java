package com.xcrj.basic.pass2.bit;
/**
 * 更大的数, 不能使用比较运算符
 * - 使用标志位代替if-else判断
 * - 分步骤用乘法
 * - 相等的数也能操作
 */
public class LargeNum {
    public static void main(String[] args) {
        int times=100000;
        int maxV=100;
        int minV=-300;
        for (int i = 0; i < times; i++) {
            Element e=getE(minV, maxV);
            int large1=cp(e.a, e.b);
            int large2=largeNum2(e.a, e.b);
            if(large1!=large2){
                System.out.println("not good");
                System.out.println(e.a+", "+e.b);
                System.out.println(large1);
                System.out.println(large2);
            }
        }
    }

    /**
     * 获取a的符号
     * @param a
     * @return a>0 return 1, a<0 return 0
     */
    private static int sign(int a) {
        return flip((a>>31)&1);
    }

    /**
     * 0变1，1变0
     * @param a
     * @return 1 return 0, 0 return 1
     */
    private static int flip(int a) {
        return a^1;
    }
    
    /**
     * a和b都是正整数》a-b>0则a>b
     * @param a
     * @param b
     * @return
     */
    public static int largeNum(int a,int b) {
        int c=a-b;
        int sc=sign(c);
        int fsc=flip(sc);
        /**
         * 使用标志位代替if-else判断
         * c>0 return a, 标志位, return a, sc=1, fsc=0
         * c<0 return b, 标志位, return b, sc=0, fsc=1
         */
        return sc*a+fsc*b;
    }

    /**
     * a b符号相同，取决于c=a-b的符号, c>0 return a, c<0 return b
     * a b符号不同，取决于a的符号, a>0 return a, a<0 return b
     * 分步骤用乘法
     * @param a
     * @param b
     * @return
     */
    public static int largeNum2(int a,int b) {
        int sa=sign(a);
        int sb=sign(b);
        int sc=sign(a-b);
        // a和b符号相同为0，不同为1
        int diffab=sa^sb;// 符号不同取决于a的符号
        int sameab=flip(diffab);// 符号不同取决于a-b的符号
        int returnA=diffab*sa+sameab*sc;
        int returnB=flip(returnA);
        return returnA*a+returnB*b;
    }
    public static int cp(int a,int b) {
        return a>=b?a:b;
    }
    static class Element{
        int a;
        int b;
    }
    public static Element getE(int minV,int maxV) {
        int a=(int)(minV+Math.random()*(maxV-minV));
        int b=(int)(minV+Math.random()*(maxV-minV));

        Element e=new Element();
        e.a=a;
        e.b=b;
        return e;
    }
}
