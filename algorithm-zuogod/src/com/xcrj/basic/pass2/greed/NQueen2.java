package com.xcrj.basic.pass2.greed;

/**
 * 由于后效性 不能进一步优化时间复杂度 可以使用位运算进一步优化常数时间
 * 将列限制 左斜线限制 右斜线限制 累计到 3个int值中
 * 计算机中负数使用补码表示
 * 1=0000 0001
 * 0=0000 0000
 * -1
 * 原码=1000 0001
 * 补码=1111 1111
 * -2
 * 原码=1000 0010
 * 补码=1111 1110
 * -128
 * 补码=1000 0000
 */
public class NQueen2 {
    public static void main(String[] args) {
        System.out.println(nQueen(13));
    }

    public static int nQueen(int n) {
        if(n<1||n>32) return 0;
        int lim=n==32?-1:(1<<n)-1;
        return proccess(lim, 0, 0, 0);
    }

    /**
     * @param lim 总共能放皇后的位置
     * @param colLim 列限制
     * @param leftLim 左斜线限制
     * @param rightLim 右斜线限制
     */
    public static int proccess(int lim,int colLim,int leftLim,int rightLim) {
        if(lim==colLim) return 1;

        int r=0;
        //总共能放皇后的位置&(现在能放皇后的位置)
        //现在能放皇后的位置=~现在不能放皇后的位置
        int pos=lim&(~(colLim|leftLim|rightLim));
        while(pos!=0){//还能放皇后的位置
            //取最右侧的1放皇后
            int mostRight1=pos&(~pos+1);
            pos-=mostRight1;
            //>>>符号位也右移，高位补0
            r+=proccess(lim, colLim|mostRight1, (leftLim|mostRight1)<<1, (rightLim|mostRight1)>>>1);
        }

        return r;
    }
}
