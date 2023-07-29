package com.xcrj.basic.pass1.greed;
//N皇后 位运算加速
/**
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
        int r=queen(8);
        System.out.println(r);
    }
    public static int queen(int n) {
        if(n<1||n>32) return 0;
        int limit=n==32?-1:(1<<n)-1;
        return proccess(limit, 0, 0, 0);
    }

    /**
     * 
     * @param lim n皇后
     * @param colLim 列限制
     * @param leftLim 左斜线限制
     * @param rightLim 右斜线限制
     * @return
     */
    public static int proccess(int lim,int colLim,int leftLim,int rightLim) {
        if(colLim==lim) return 1;
        //
        int r=0;
        //
        int pos=lim&(~(colLim|leftLim|rightLim));
        //
        while(pos!=0){
            int mostRight1=pos&(~pos+1);
            pos-=mostRight1;//取了最右侧的1，最右侧的1被放上皇后
            //最右侧的1被放上皇后不能再放皇后 >>> 32皇后 位移不管符号位
            r+=proccess(lim, colLim|mostRight1, (leftLim|mostRight1)<<1, (rightLim|mostRight1)>>>1);
        }
        //
        return r;
    }
}
