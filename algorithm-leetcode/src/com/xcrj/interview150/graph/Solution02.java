package com.xcrj.interview150.graph;

/**
 * https://leetcode.cn/problems/surrounded-regions
 * 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class Solution02 {
    int n, m;

    /**
     * 操作边界0
     * 与边界0直接或间接相连的0不能变为X，依然是0
     * 标记与边界0直接或间接相连的0，标记为A
     * 最后将2改回0，其它改为X
     *
     * @param board
     */
    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) return;
        m = board[0].length;
        //边界
        //第1列 最后1列
        for (int i = 0; i < n; i++) {
            f(board, i, 0);
            f(board, i, m - 1);
        }
        //第1行 最后1行
        for (int i = 1; i < m - 1; i++) {
            f(board, 0, i);
            f(board, n - 1, i);
        }

        //最后将2改回0，其它改为X
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '2') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void f(char[][] ass, int i, int j) {
        if (ass == null || ass.length == 0 || i >= n || i < 0 || j >= m || j < 0 || ass[i][j] != 'O') return;
        ass[i][j] = '2';
        f(ass, i + 1, j);
        f(ass, i - 1, j);
        f(ass, i, j + 1);
        f(ass, i, j - 1);
    }
}
