package com.xcrj.offer_special.pass3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 112. 最长递增路径
 * 输入，m x n 整数矩阵 matrix，
 * 对于每个单元格，你可以往上，下，左，右四个方向移动
 * 输出，最长递增路径 的长度
 */
public class Solution112 {
    /**
     * 记忆深度优先搜索
     * 从矩阵的每个点出发 深度优先向四周拓展 寻找最大路径，使用矩阵记录中间过程
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int maxr=0;
        //
        if(matrix==null) return 0;
        if(matrix.length==0) return 0;
        if(matrix[0].length==0) return 0;
        //
        int[][] mem=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                maxr=Math.max(maxr, dfs(matrix, i, j, mem));
            }
        }
        //
        return maxr;
    }
    // xcrj java 初始化数组
    // 上下左右
    int[][] steps={{1,0},{-1,0},{0,-1},{0,1}};

    public int dfs(int[][] matrix,int i,int j,int[][] mem){
        //已经访问过
        if(mem[i][j]!=0) return mem[i][j];
        //没访问过
        mem[i][j]++;
        //继续向四周拓展
        for(int k=0;k<4;k++){
            int[] step=steps[k];
            int nextI=i+step[0];
            int nextJ=j+step[1];
            // 更大才能往下走
            if(nextI>=0&&nextI<matrix.length&&nextJ>=0&&nextJ<matrix[0].length
                &&matrix[nextI][nextJ]>matrix[i][j]){
                    int nextIJLen=dfs(matrix, nextI, nextJ, mem);
                    int ijLen=1+nextIJLen;
                    mem[i][j]=Math.max(mem[i][j], ijLen);
            }
        }
        //从i，j出发能拓展出的最深路径长度
        return mem[i][j];
    }

    /**
     * 最长递增路径=拓扑序列长度
     * 广度优先逆向拓扑排序
     * - 构建出度数组
     * - 将所有出度为0的结点放入队列
     * - 每次要将队列中的所有元素处理完
     */
    public int longestIncreasingPath2(int[][] matrix) {
        int r=0;
        //
        if(matrix==null) return 0;
        if(matrix.length==0) return 0;
        if(matrix[0].length==0) return 0;
        //
        int[][] out=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                // 检查四周更大的元素，则为一个出度
                for(int k=0;k<4;k++){
                    int[] step=steps[k];
                    int nextI=i+step[0];
                    int nextJ=j+step[1];
                    if(nextI>=0&&nextI<matrix.length&&nextJ>=0&&nextJ<matrix[0].length
                        &&matrix[nextI][nextJ]>matrix[i][j]){
                        out[i][j]++;
                    }
                }
            }
        }
        //
        Queue<int[]> que=new ArrayDeque<>();
        // 将所有出度为0 的结点入队
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(out[i][j]==0){
                    que.offer(new int[]{i,j});
                }
            }
        }
        //
        while(!que.isEmpty()){
            // 每次处理完一层拓扑序列, 把出度为0的结点全部去掉
            r++;
            int size=que.size();
            for(int m=0;m<size;m++){
                int[] ij=que.poll();
                int i=ij[0];
                int j=ij[1];
                // 剔除ij, 减少 preI,preJ 的初度
                for(int k=0;k<4;k++){
                    int[] step=steps[k];
                    int preI=i+step[0];
                    int preJ=j+step[1];
                    // 注意，matrix[preI][preJ]<matrix[i][j] 是小于
                    if(preI>=0&&preI<matrix.length&&preJ>=0&&preJ<matrix[0].length
                        &&matrix[preI][preJ]<matrix[i][j]){
                        out[preI][preJ]--;
                        if(out[preI][preJ]==0){
                            que.offer(new int[]{preI,preJ});
                        }
                    }
                }
            }
        }
        return r;
    }
}
