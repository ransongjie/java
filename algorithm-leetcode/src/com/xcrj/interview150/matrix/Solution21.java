package com.xcrj.interview150.matrix;

/**
 * https://leetcode.cn/problems/set-matrix-zeroes
 * 矩阵置零
 * 最少空间
 * 使用原空间
 * 最终整行列为0，提前为0记录结果0
 */
public class Solution21 {
    public void setZeroes(int[][] mss) {
        int m = mss.length, n = mss[0].length;
        //第0列是否为0
        boolean isColContains0 = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mss[i][j] == 0) {
                    mss[i][0] = 0;//第0列记录某一行是否为0
                    if (j != 0) {
                        mss[0][j] = 0;//1~n列是否为0
                    } else {
                        isColContains0 = true;
                    }
                }
            }
        }

        //第0列被单独考虑了，倒序所有行，遍历除开第0列的位置，第0列是否为0倍isColContains0记录
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                //某列为0，某行为0
                if (mss[0][j] == 0 || mss[i][0] == 0) {
                    mss[i][j] = 0;
                }
            }
        }

        if (isColContains0) {
            //第0列全部被赋值为0
            for (int i = 0; i < m; i++) {
                mss[i][0] = 0;
            }
        }
    }
}
