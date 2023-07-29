package com.xcrj.basic.pass2.greed;
/**
 * O(n^n)
 * N皇后 第一种方法
 * 不能进一步优化时间复杂度的原因在于 后效性
 * 所有行 任意两个皇后 不共行 列 斜线
 */
public class NQueen1 {
    public static void main(String[] args) {
        int n=13;
        int[] rocord=new int[n];
        System.out.println(nQueen(0, n, rocord));
    }

    public static int nQueen(int i,int n,int[] record) {
        if(i==n) return 1;
        int r=0;
        for (int j = 0; j < n; j++) {//同一行的每个位置都会被尝试
            if(conflict(i, j, record)) continue;
            record[i]=j;
            r+=nQueen(i+1, n, record);
        }
        return r;
    }

    /**
     * 
     * @param i 第i行
     * @param j 第j列
     * @param record 
     * @return
     */
    private static boolean conflict(int i,int j,int[] record) {
        //不同列 不同斜线
        for (int k = 0; k < i; k++) {
            if(record[k]==j) return true;
            if(Math.abs(record[k]-j)==Math.abs(i-k)) return true;
        }

        return false;
    }
}
