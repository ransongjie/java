package com.xcrj.offer_special.pass3;

/**
 * 剑指 Offer II 013. 二维子矩阵的和
 * - 子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)
 */
public class Solution13 {
    /**
     * 一维前缀和，一行的和
     */
    class NumMatrix1 {
        int[][] preSum;

        public NumMatrix1(int[][] matrix) {
            //定义 前缀和数组
            preSum=new int[matrix.length][matrix[0].length+1];
            //初始化
            for(int i=0;i<matrix.length;i++){
                // 初始化每一行的前缀和
                preSum[i][0]=0;
            }
            //构建
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    preSum[i][j+1]=preSum[i][j]+matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int r=0;
            for(int i=row1;i<=row2;i++){
                r+=preSum[i][col2+1]-preSum[i][col1];
            }
            return r;
        }
    }

    /**
     * 二维前缀和，方块的和 (0,0)~(i,j)方块的和
     * - preSum[m][n]=preSum[m][j]+preSum[i][n]-preSum[i][j]+matrix[i][j]
     */
    class NumMatrix2 {
        int[][] preSum;
        public NumMatrix2(int[][] matrix) {
            // 定义
            preSum=new int[matrix.length+1][matrix[0].length+1];
            // 初始化
            preSum[0][0]=0;
            // 
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[i].length;j++){
                    preSum[i+1][j+1]=preSum[i][j+1]+preSum[i+1][j]-preSum[i][j]+matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2+1][col2+1]-preSum[row1][col2+1]-preSum[row2+1][col1]+preSum[row1][col1];
        }
    }
}
