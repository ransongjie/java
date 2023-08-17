package com.xcrj.interview150.backtrack;

/**
 * https://leetcode.cn/problems/word-search
 * 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Solution06 {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        //遍历每个位置，尝试寻找字符串
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (f(board, visited, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    static int[][] steps = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * @param board
     * @param visited
     * @param i       board[i][j]
     * @param j
     * @param s
     * @param k       s[k]
     * @return
     */
    private boolean f(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) return false;
        //k从0出发，相等才会走到这里
        if (k == s.length() - 1) return true;
        //访问(i,j)位置后尝试往4个方向走1步
        visited[i][j] = true;
        int nxti, nxtj;
        for (int[] step : steps) {
            nxti = i + step[0];
            nxtj = j + step[1];
            //未越界且没有访问过
            if (nxti < board.length && nxti >= 0 && nxtj < board[0].length && nxtj >= 0) {
                if (!visited[nxti][nxtj]) {
                    if (f(board, visited, nxti, nxtj, s, k + 1)) {
                        //回溯
                        visited[i][j] = false;
                        return true;
                    }
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
}
