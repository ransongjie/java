package com.xcrj.interview150.matrix;

/**
 * https://leetcode.cn/problems/valid-sudoku
 * 有效的数独
 */
public class Solution18 {
    public boolean isValidSudoku(char[][] board) {
        //记录行上某个数字是否出现过
        boolean[][] row = new boolean[9][10];//9行，1~9数字
        //记录列上某个数字是否出现过
        boolean[][] col = new boolean[9][10];
        //记录桶中某个数字是否出现过
        boolean[][] buk = new boolean[9][10];//9桶
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int b = 3 * (i / 3) + (j / 3);//第b个桶
                    int num = board[i][j] - '0';
                    if (row[i][num] || col[j][num] || buk[b][num]) {
                        return false;
                    }
                    row[i][num] = true;
                    col[j][num] = true;
                    buk[b][num] = true;
                }
            }
        }
        return true;
    }
}
