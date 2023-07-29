package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 112. 最长递增路径
 * 输入，m x n 整数矩阵 matrix，
 * 对于每个单元格，你可以往上，下，左，右四个方向移动
 * 输出，最长递增路径 的长度
 */
public class Solution112 {
    int mrow;
    int ncolumn;
    int[][] stepss = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 记忆搜索算法，记忆深度优先搜索
     * 搜索，以输入矩阵的每个单元格为起始点向四个方向进行深度优先搜索
     * 记忆，使用memo[][]记录每个单元格的递增路径长度
     * xcrj 记忆搜索优势，记录了搜索的中间结果
     */
    public int longestIncreasingPath1(int[][] matrix) {
        if(null==matrix)return 0;
        if(0==matrix.length)return 0;
        if(0==matrix[0].length)return 0;

        mrow=matrix.length;
        ncolumn=matrix[0].length;

        //记忆
        int[][] memoss=new int[mrow][ncolumn];

        //以输入矩阵的每个单元格为起始点
        int maxr=0;
        for(int i=0;i<mrow;i++){
            for(int j=0;j<ncolumn;j++){
                maxr=Math.max(maxr, dfs(matrix, i, j, memoss));
            }
        }

        return maxr;
    }

    //记忆深度优先搜索
    private int dfs(int[][] matrix, int i, int j, int[][] memoss) {
        if(0!=memoss[i][j])return memoss[i][j];

        //拓展一个新的单元格
        memoss[i][j]++;

        for(int k=0;k<4;k++){
            int nextI=i+stepss[k][0];
            int nextJ=j+stepss[k][1];

            //递增才继续深度
            if(nextI>=0&&nextI<mrow&&nextJ>=0&&nextJ<ncolumn
                &&matrix[nextI][nextJ]<matrix[i][j])
            //+1，加上上一个单元格，memoss[i][j]=memoss[nextI][nextJ]+1
                memoss[i][j]=Math.max(memoss[i][j], dfs(matrix, nextI, nextJ, memoss)+1);
            
        }

        return memoss[i][j];
    }

    //拓扑排序，广度优先拓扑排序，每次将队列中出度为0的结点处理完
    //将出度为0的结点放入队列，全部出队后将剩余结点中出度为0的结点放入队列
    //有出度，从此点出发有递增的值
    public int longestIncreasingPath2(int[][] matrix) {
        if(null==matrix)return 0;
        if(0==matrix.length)return 0;
        if(0==matrix[0].length)return 0;

        mrow=matrix.length;
        ncolumn=matrix[0].length;

        //记录每个格子的出度
        int[][] outDgreess=new int[mrow][ncolumn];
        for(int i=0;i<mrow;i++){
            for(int j=0;j<ncolumn;j++){
                for(int[] steps:stepss){
                    int nextI=i+steps[0];
                    int nextJ=j+steps[1];
                    if(nextI>=0&&nextI<mrow
                        &&nextJ>=0&&nextJ<ncolumn
                        &&matrix[nextI][nextJ]>matrix[i][j]){
                            outDgreess[i][j]++;
                    }
                }
            }
        }

        //广度优先队列，将出度为0的点全部入队
        Queue<int[]> que=new ArrayDeque<>();
        for(int i=0;i<mrow;i++){
            for(int j=0;j<ncolumn;j++){
                if(outDgreess[i][j]==0)que.offer(new int[]{i,j});
            }
        }

        //广度优先遍历
        int r=0;
        while(!que.isEmpty()){
            //一层为1步
            r++;
            //将队列中当前所有元素处理完
            int size=que.size();
            for(int k=0;k<size;k++){
                int[] ijs=que.poll();
                int i=ijs[0];
                int j=ijs[1];
                for(int[] steps:stepss){
                    int nextI=i+steps[0];
                    int nextJ=j+steps[1];
                    //处理下1个点的出度，(nextI,nextJ) 不能到 (i,j) 则下一个点的出度减1
                    if(nextI>=0&&nextI<mrow
                    &&nextJ>=0&&nextJ<ncolumn
                    &&matrix[nextI][nextJ]<matrix[i][j]){
                        outDgreess[nextI][nextJ]--;
                        if(outDgreess[nextI][nextJ]==0)que.offer(new int[]{nextI,nextJ});
                    }
                }
            }
        }

        return r;
    }
}
