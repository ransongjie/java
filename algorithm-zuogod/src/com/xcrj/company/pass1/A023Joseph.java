package com.xcrj.company.pass1;

/**
 * 约瑟夫环公式法
 */
public class A023Joseph {
    /**
     *
     * @param n n个结点
     * @param m 从1数到m 杀死m报数的人
     * @return
     */
    public static int joseph(int n,int m){
        return f(n,m)-1;//-1 编号从0开始
    }

    private static int f(int n,int m){
        //只剩1个人了，这个人的编号是1，杀之后的编号是1
        if(n==1){
            return 1;
        }
        //杀前编号 =（杀后编号+m-1）%n+1 。n是现在剩下的结点个数
        return (f(n-1,m)+m-1)%n+1;
    }
}
