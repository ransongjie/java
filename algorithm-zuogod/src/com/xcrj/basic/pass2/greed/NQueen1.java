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
        // 从第0行开始
        System.out.println(nQueen(0, n, rocord));
    }

    public static int nQueen(int i,int n,int[] record) {
        if(i==n) return 1;
        int r=0;
        // i控制行，j控制列，每个位置都会被尝试
        for (int j = 0; j < n; j++) {
            // 冲突continue
            if(conflict(i, j, record)) continue;
            // 放入
            record[i]=j;
            // 继续
            r+=nQueen(i+1, n, record);
        }
        return r;
    }

    /**
     * 准备放的位置(i,j)是否和record[]中已有的冲突
     * @param i 第i行
     * @param j 第j列
     * @param record 
     * @return
     */
    private static boolean conflict(int i,int j,int[] record) {
        //第i行之后的还没有遍历到
        for (int k = 0; k < i; k++) {
            //不同列
            if(record[k]==j) return true;
            //不同斜线
            if(Math.abs(record[k]-j)==Math.abs(i-k)) return true;
        }

        return false;
    }
}
