package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 107. 矩阵中的距离
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，
 * 其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * <p>
 * 分析
 * - 0到自身的距离最短=0
 * - 将所有为0的元素视为癌细胞，每次尝试感染周围1圈正常的细胞，不能重复感染，被感染的细胞变成了癌细胞
 */
public class Solution107 {
    // 向四周扩展，向上一步，向下一步，向左一步，向右一步
    static final int[][] ijstep = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 广度优先，向四周感染一圈细胞，将被感染细胞放入队列中
     * <p>
     * 广度优先搜索
     * - 将所有为0的元素视为癌细胞，放入队列中
     * - 每次尝试感染周围1圈正常的细胞
     * - 被感染的细胞变成了癌细胞
     * <p>
     * 这种广度优先搜索的方法为什么能保证1到0的距离最短
     * - 因为广度优先每一步都是走的最短距离
     * - 每次都尝试从一个元素0向周围1圈感染
     */
    public int[][] updateMatrix1(int[][] mat) {
        // 结果
        int m = mat.length;
        int n = mat[0].length;
        int[][] rss = new int[m][n];
        // 是否被感染
        boolean[][] infected = new boolean[m][n];

        // 广度优先队列 存放被感染的细胞 int[] coordinate={i,j}
        Queue<int[]> que = new ArrayDeque<>();

        // 将所有的0添加进队列
        // 将癌细胞放入队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == mat[i][j]) {
                    que.offer(new int[]{i, j});
                    infected[i][j] = true;
                }
            }
        }

        // 队列非空
        while (!que.isEmpty()) {
            // 出队一个癌细胞
            int[] cancerCell = que.poll();
            int i = cancerCell[0];
            int j = cancerCell[1];

            // 向四周感染1圈细胞
            for (int k = 0; k < 4; k++) {
                // 下一个被感染的细胞
                int nextI = i + ijstep[k][0];
                int nextJ = j + ijstep[k][1];
                // 限制行&&限制列&&没有被感染
                if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && !infected[nextI][nextJ]) {
                    // 结果，癌细胞向外面一圈感染一个细胞
                    rss[nextI][nextJ] = rss[i][j] + 1;
                    // 队列中加入新癌细胞
                    que.offer(new int[]{nextI, nextJ});
                    // 标识已经被感染
                    infected[nextI][nextJ] = true;
                }

            }
        }
        return rss;
    }

    /**
     * 动态规划，从四个角开始移动寻找到0的最近距离
     * <p>
     * 动态规划
     * 问：只有 水平向左移动 和 竖直向上移动时，为什么i=0,j=0,初始位置在左上角
     * 答：因为动态规划是分阶段的，需要先计算子阶段
     * <p>
     * 动态规划数组
     * - f[i][j]表示位置i,j到最近的0的距离
     * 状态转移方程
     * - f[i][j]=0 , mat[i][j]=0
     * - f[i][j]=1+min(f[i-1][j],f[i][j-1]), mat[i][j]=1
     */
    public int[][] updateMatrix2(int[][] mat) {
        // 动态规划数组，f[i][j]表示(i,j)位置的元素到最近的0元素的距离
        int m = mat.length;
        int n = mat[0].length;
        int[][] f = new int[m][n];

        // 初始化为大值，因为后面要求min
        for (int[] f1 : f) {
            // 为什么要使用Integer.MAX_VALUE，后面再+1，就产生越界了
            Arrays.fill(f1, Integer.MAX_VALUE / 2);
        }

        // 初始化已经被感染的细胞，mat[i][j]=0,则f[i][j]=0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == mat[i][j]) {
                    f[i][j] = 0;
                }
            }
        }

        // 为什么i=0,j=0,初始位置在左上角,因为动态规划是分阶段的，需要先计算子阶段
        // 从左上角开始移动（水平向左移动 和 竖直向上移动）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i-1,j)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j-1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }

        // 从左下角开始移动（水平向左移动 和 竖直向下移动）
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i+1,j)位置的元素到最近的0元素的距离
                if (i + 1 <= m - 1) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j-1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }

        // 从右上角开始移动（水平向右移动 和 竖直向上移动）
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i-1,j)位置的元素到最近的0元素的距离
                if (i - 1 >= 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                }
                if (j + 1 <= n - 1) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j+1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                }
            }
        }

        // 从右下角开始移动（水平向右移动 和 竖直向下移动）
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i+1,j)位置的元素到最近的0元素的距离
                if (i + 1 <= m - 1) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                }
                if (j + 1 <= n - 1) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j+1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                }
            }
        }

        return f;
    }

    /**
     * 动态规划，从左上角和右下角开始移动寻找到0的最近距离
     * <p>
     * 上面的动态规划
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
     * <p>
     * 有一半重复的过程
     * 从左上角开始移动
     * - 往后：f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
     * - 往左：f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
     * 从右下角开始移动
     * - 往下：f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
     * - 往右：f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
     */
    public int[][] updateMatrix3(int[][] mat) {
        // 动态规划数组，f[i][j]表示(i,j)位置的元素到最近的0元素的距离
        int m = mat.length;
        int n = mat[0].length;
        int[][] f = new int[m][n];

        // 初始化为大值，因为后面要求min
        for (int[] f1 : f) {
            // 为什么要使用Integer.MAX_VALUE，后面再+1，就产生越界了
            Arrays.fill(f1, Integer.MAX_VALUE / 2);
        }

        // 初始化已经被感染的细胞，mat[i][j]=0,则f[i][j]=0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == mat[i][j]) {
                    f[i][j] = 0;
                }
            }
        }

        // 为什么i=0,j=0,初始位置在左上角,因为动态规划是分阶段的，需要先计算子阶段
        // 从左上角开始移动（水平向左移动 和 竖直向上移动）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i-1,j)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j-1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }

        // 从右下角开始移动（水平向右移动 和 竖直向下移动）
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i+1,j)位置的元素到最近的0元素的距离
                if (i + 1 <= m - 1) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                }
                if (j + 1 <= n - 1) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j+1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                }
            }
        }

        return f;
    }

    /**
     * 动态规划，从左下角和右上角开始移动寻找到0的最近距离
     * <p>
     * 上面的动态规划
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
     * <p>
     * 有一半重复的过程
     * 从左下角开始移动
     * - 往下：f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
     * - 往左：f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
     * 从右上角开始移动
     * - 往后：f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
     * - 往右：f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
     */
    public int[][] updateMatrix4(int[][] mat) {
        // 动态规划数组，f[i][j]表示(i,j)位置的元素到最近的0元素的距离
        int m = mat.length;
        int n = mat[0].length;
        int[][] f = new int[m][n];

        // 初始化为大值，因为后面要求min
        for (int[] f1 : f) {
            // 为什么要使用Integer.MAX_VALUE，后面再+1，就产生越界了
            Arrays.fill(f1, Integer.MAX_VALUE / 2);
        }

        // 初始化已经被感染的细胞，mat[i][j]=0,则f[i][j]=0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == mat[i][j]) {
                    f[i][j] = 0;
                }
            }
        }

        // 从左下角开始移动（水平向左移动 和 竖直向下移动）
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i+1,j)位置的元素到最近的0元素的距离
                if (i + 1 <= m - 1) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j-1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }

        // 从右上角开始移动（水平向右移动 和 竖直向上移动）
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i-1,j)位置的元素到最近的0元素的距离
                if (i - 1 >= 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                }
                if (j + 1 <= n - 1) {
                    // f[i][j]=(i,j)位置的元素到最近的0元素的距离=1+(i,j+1)位置的元素到最近的0元素的距离
                    f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                }
            }
        }

        return f;
    }
}
