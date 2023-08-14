package com.xcrj.interview150.matrix;

/**
 * https://leetcode.cn/problems/game-of-life
 * 生命游戏
 * 最少空间
 * 使用位运算节省空间
 */
public class Solution22 {
    /**
     * @param ass
     * @return 返回ass的下一个状态
     */
    public void gameOfLife(int[][] ass) {
        int m = ass.length, n = ass[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //获取i,j 8个方向有多少个1
                int k = get1Num(ass, i, j);
                //存活则下一轮变成1，这个1记录在第2位上
                //0周围3个1，下一轮变1；1周围3个1，下一轮依然1；我是1 并且 周围2个1；把这个数的第2位变成1.
                //其它情况该位置的细胞，下一轮都是死，变成0
                if (k == 3 || (k == 2 && ass[i][j] == 1)) {
                    ass[i][j] |= 2;
                }
            }
        }

        //ass 下一次状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //ass的下一个状态记录在 第2位上（从右往左）
                ass[i][j] >>= 1;
            }
        }
    }

    //获取i,j 8个方向有多少个1
    private int get1Num(int[][] ass, int i, int j) {
        return f(ass, i - 1, j) + f(ass, i - 1, j + 1) + f(ass, i, j + 1) + f(ass, i + 1, j + 1)
                + f(ass, i + 1, j) + f(ass, i + 1, j - 1) + f(ass, i, j - 1) + f(ass, i - 1, j - 1);
    }

    private int f(int[][] ass, int i, int j) {
        //! (ass[i][j] & 1) == 1 ，第1位是初始状态
        return (i >= 0 && i < ass.length && j >= 0 && j < ass[0].length && (ass[i][j] & 1) == 1) ? 1 : 0;
    }
}
