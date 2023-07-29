package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 112. 最长递增路径
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 */
public class Solution112 {
    // 深度优先向四周搜索，上下左右
    int[][] steps = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 总行数
    int amountRow;
    // 总列数
    int amountColumn;

    /**
     * 记忆化深度优先搜索
     * 深度优先搜索
     * - 以输入矩阵的每个单元格为起始点进行深度优先搜索
     * - 向四周（四个方向）进行深度优先搜索
     * <p>
     * 记忆化
     * - 使用memo[][]记录每个单元格的递增路径长度
     * - 因为每个单元格的最长递增路径长度是不变的，所以可以使用memo[][]记录
     */
    public int longestIncreasingPath1(int[][] matrix) {
        // 特殊情况
        if (null == matrix) return 0;
        if (0 == matrix.length) return 0;
        if (0 == matrix[0].length) return 0;

        // 获取行列数量
        this.amountRow = matrix.length;
        this.amountColumn = matrix[0].length;
        // 构造记忆矩阵
        int[][] memories = new int[this.amountRow][this.amountColumn];
        // 结果
        int maxR = 0;
        // 以输入矩阵的每个单元格为起始点进行深度优先搜索，获取最长递增路径长度
        for (int i = 0; i < this.amountRow; i++) {
            for (int j = 0; j < this.amountColumn; j++) {
                maxR = Math.max(maxR, dfs(matrix, i, j, memories));
            }
        }

        return maxR;
    }

    /**
     * 深度优先搜索，获取从matrix[row][column]单元格出发的最长递增路径长度
     *
     * @param i        单元格所在行
     * @param j        单元格所在列
     * @param memories 记忆数组，记忆每个单元格的最长递增路径长度
     */
    private int dfs(int[][] matrix, int i, int j, int[][] memories) {
        // 单元格记忆数组不等于0，直接返回
        if (0 != memories[i][j]) return memories[i][j];
        // 单元格记忆数组等于0，向四周进行深度优先搜索
        memories[i][j]++;
        for (int k = 0; k < this.steps.length; k++) {
            int nextI = i + this.steps[k][0];
            int nextJ = j + this.steps[k][1];
            // 防止越界&&保证递增
            if (nextI >= 0 && nextI < this.amountRow
                    && nextJ >= 0 && nextJ < this.amountColumn
                    && matrix[nextI][nextJ] > matrix[i][j]) {
                // 深度优先搜索
                // +1，起点算1步
                memories[i][j] = Math.max(memories[i][j], dfs(matrix, nextI, nextJ, memories) + 1);
            }
        }

        return memories[i][j];
    }

    /**
     * 拓扑排序
     * - 结点一个出度代表，出度边邻接结点值>当前结点值
     * - 将出度为0的结点放入队列，全部出队后将剩余结点中出度为0的结点放入队列
     * - 广度优先遍历的次数即为最长递增路径长度
     * - 广度优先遍历下一层的值<当前层的值
     */
    public int longestIncreasingPath2(int[][] matrix) {
        // 特殊情况
        if (null == matrix) return 0;
        if (0 == matrix.length) return 0;
        if (0 == matrix[0].length) return 0;

        // 获取行列数量
        this.amountRow = matrix.length;
        this.amountColumn = matrix[0].length;

        // 构造出度数组
        int[][] outDegrees = new int[this.amountRow][this.amountColumn];
        // 统计每个结点的出度
        for (int i = 0; i < this.amountRow; i++) {
            for (int j = 0; j < this.amountColumn; j++) {
                for (int[] step : this.steps) {
                    int nextI = i + step[0];
                    int nextJ = j + step[1];
                    if (nextI >= 0 && nextI < this.amountRow
                            && nextJ >= 0 && nextJ < this.amountColumn
                            && matrix[nextI][nextJ] > matrix[i][j]) {
                        outDegrees[i][j]++;
                    }
                }
            }
        }

        // 建立广度优先遍历所需队列 Queue<int[]{row,column}>
        // 将出度为0的结点放入队列中
        Queue<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < this.amountRow; i++) {
            for (int j = 0; j < this.amountColumn; j++) {
                if (0 == outDegrees[i][j]) que.offer(new int[]{i, j});
            }
        }

        // 结果
        int r = 0;
        // 开始广度优先遍历
        while (!que.isEmpty()) {
            // 广度优先处理一层
            r++;
            // 处理当前层所有结点（队列中的结点）
            int size = que.size();
            for (int k = 0; k < size; k++) {
                int[] ij = que.poll();
                int i = ij[0];
                int j = ij[1];
                // 将下层出度为0的结点加入队列
                for (int[] step : steps) {
                    int nextI = i + step[0];
                    int nextJ = j + step[1];
                    if (nextI >= 0 && nextI < this.amountRow
                            && nextJ >= 0 && nextJ < this.amountColumn
                            && matrix[nextI][nextJ] < matrix[i][j]) {
                        outDegrees[nextI][nextJ]--;
                        if (0 == outDegrees[nextI][nextJ]) que.offer(new int[]{nextI, nextJ});
                    }
                }
            }
        }

        return r;
    }
}
