package com.xcrj.interview150.graph;

/**
 * https://leetcode.cn/problems/number-of-islands
 * <p>
 * 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class Solution01 {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int r = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //深度优先把每片岛感染完
                if (grid[i][j] == '1') {
                    r++;
                    f(grid, i, j, m, n);
                }
            }
        }
        return r;
    }

    private void f(char[][] ass, int i, int j, int m, int n) {
        if (ass == null || ass.length == 0 || i >= m || i < 0 || j >= n || j < 0 || ass[i][j] != '1') return;
        ass[i][j] = '2';
        f(ass, i + 1, j, m, n);
        f(ass, i - 1, j, m, n);
        f(ass, i, j + 1, m, n);
        f(ass, i, j - 1, m, n);
    }
}
