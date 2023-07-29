package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 107. 矩阵中的距离, 癌细胞感染
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，
 * 其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * <p>
 * 分析
 * - 1到0最近 转换为 0到1最近
 * - 0到自身的距离最短=0
 * - 将所有为0的元素视为癌细胞，每次尝试感染周围1圈正常的细胞，不能重复感染，被感染的细胞变成了癌细胞
 */
public class Solution107 {
    // 向四周扩展，向上一步，向下一步，向左一步，向右一步
    static final int[][] ijstep = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //广度优先，向四周感染一圈细胞，将被感染细胞放入队列中
    //xcrj 这种广度优先搜索的方法为什么能保证1到0的距离最短? 每次都尝试从0感染外面的一圈
    public int[][] updateMatrix1(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        //mat[m][n]=1 到最近0的距离
        int[][]rss=new int[m][n];
        //被感染
        boolean[][]infectedss=new boolean[m][n];
        //队列 存放被感染的细胞的坐标 int[] coordinate={i,j}
        Queue<int[]>que=new ArrayDeque<>();

        //初始化，将所有癌细胞放入队列
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(0==mat[i][j]){
                    que.offer(new int[]{i,j});
                    infectedss[i][j]=true;
                }
            }
        }

        //每个癌细胞向外感染一圈
        while(!que.isEmpty()){
            int[] cancerCell=que.poll();
            int i=cancerCell[0];
            int j=cancerCell[1];

            //向四周感染1圈细胞
            for(int k=0;k<4;k++){
                int nextI=i+ijstep[k][0];
                int nextJ=j+ijstep[k][1];
                
                if(nextI>=0&&nextJ>=0&&nextI<m&&nextJ<n&&!infectedss[nextI][nextJ]){
                    //感染
                    infectedss[nextI][nextJ]=true;
                    que.offer(new int[]{nextI,nextJ});
                    rss[nextI][nextJ]=rss[i][j]+1;
                }
            }
        }

        return rss;
    }

    /**
     * xcrj 动态规划, 从周围所有方向到f[i][j]的最短距离作为f[i][j]到0的最终最短距离
     * 
     * 动态规划，从左上角, 左下角, 右上角, 右下角 开始移动寻找到0的最近距离
     * 
     * 从左上角开始移动
     * - 往后：f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
     * - 往左：f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
     * 从左下角开始移动
     * - 往下：f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
     * - 往左：f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
     * 从右上角开始移动
     * - 往后：f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
     * - 往右：f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
     * 从右下角开始移动
     * - 往下：f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
     * - 往右：f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
     * @param mat
     * @return
     */
    public int[][] updateMatrix2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        //动态规划数组。f[i][j]表示(i,j)位置的元素到最近的0元素的距离
        //xcrj 动态规划分阶段，从f[i-1][j], f[i][j-1] 到 f[i][j]
        //xcrj 将从周围所有方向到f[i][j]的最短距离作为f[i][j]到0的最终最短距离
        int[][] fss = new int[m][n];

        // 初始化为大值，因为后面要求min
        // 为什么要使用Integer.MAX_VALUE，后面再+1，就产生越界了
        for(int[] fs : fss)Arrays.fill(fs, Integer.MAX_VALUE / 2);

        // 初始化，癌细胞自己到自己的距离为0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == mat[i][j]) {
                    fss[i][j] = 0;
                }
            }
        }

        //从左上角到f[i][j]
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i>=1)fss[i][j]=Math.min(fss[i][j],fss[i-1][j]+1);
                if(j>=1)fss[i][j]=Math.min(fss[i][j],fss[i][j-1]+1);
            }
        }

        //从左下角到f[i][j]
        for(int i=m-1;i>=0;i--){
            for(int j=0;j<n;j++){
                if(i<=m-2)fss[i][j]=Math.min(fss[i][j],fss[i+1][j]+1);
                if(j>=1)fss[i][j]=Math.min(fss[i][j],fss[i][j-1]+1);
            }
        }

        //从右下角到f[i][j]
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i<=m-2)fss[i][j]=Math.min(fss[i][j],fss[i+1][j]+1);
                if(j<=n-2)fss[i][j]=Math.min(fss[i][j],fss[i][j+1]+1);
            }
        }

        //从右上角到f[i][j]
        for(int i=0;i<m;i++){
            for(int j=n-1;j>=0;j--){
                if(i>=1)fss[i][j]=Math.min(fss[i][j],fss[i-1][j]+1);
                if(j<=n-2)fss[i][j]=Math.min(fss[i][j],fss[i][j+1]+1);
            }
        }

        return fss;
    }

    /**
     * 动态规划，从左上角和右下角开始移动寻找到0的最近距离
     * 
     * 上面的 有一半重复的过程
     * 从左上角开始移动
     * - 往后：f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
     * - 往左：f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
     * 从右下角开始移动
     * - 往下：f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
     * - 往右：f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
     * @param mat
     * @return
     */
    public int[][] updateMatrix3(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        //动态规划数组。f[i][j]表示(i,j)位置的元素到最近的0元素的距离
        //xcrj 动态规划分阶段，从f[i-1][j], f[i][j-1] 到 f[i][j]
        //xcrj 将从周围所有方向到f[i][j]的最短距离作为f[i][j]到0的最终最短距离
        int[][] fss = new int[m][n];

        // 初始化为大值，因为后面要求min
        // 为什么要使用Integer.MAX_VALUE，后面再+1，就产生越界了
        for(int[] fs : fss)Arrays.fill(fs, Integer.MAX_VALUE / 2);

        // 初始化，癌细胞自己到自己的距离为0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == mat[i][j]) {
                    fss[i][j] = 0;
                }
            }
        }

        //从左上角到f[i][j]
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i>=1)fss[i][j]=Math.min(fss[i][j],fss[i-1][j]+1);
                if(j>=1)fss[i][j]=Math.min(fss[i][j],fss[i][j-1]+1);
            }
        }

        //从右下角到f[i][j]
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i<=m-2)fss[i][j]=Math.min(fss[i][j],fss[i+1][j]+1);
                if(j<=n-2)fss[i][j]=Math.min(fss[i][j],fss[i][j+1]+1);
            }
        }

        return fss;
    }
}
