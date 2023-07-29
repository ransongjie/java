package com.xcrj.basic.pass1.greed;
//N皇后 第一种方法
//不能进一步优化时间复杂度的原因在于 后效性
//所有行 任意两个皇后 不共行 列 斜线
public class NQueen1 {
    public static void main(String[] args) {
        int n=8;
        int r=queen(0, n, new int[n]);
        System.out.println(r);
        // System.out.println(sum);
    }
    // static int sum=0;
    /**
     * 
     * @param i 第i行
     * @param n n皇后
     * @param record 0~i-1行皇后放在那一列
     * @return
     */
    public static int queen(int i,int n,int[] record) {
        if(i==n) {
            // sum++;
            return 1;
        }
        int r=0;//
        for (int j = 0; j < n; j++) {//每一行都有n种放法
            if(confict(i,j,record)) continue;
            record[i]=j;
            r+=queen(i+1, n, record);
        }
        return r;
    }

    private static boolean confict(int i,int j,int[]record) {
        for(int k=0;k<i;k++) {
            if(record[k]==j){
                return true;
            }
            if(Math.abs(k-i)==Math.abs(record[k]-j)){
                return true;
            }
        }
        return false;
    }
}
