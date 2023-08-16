package com.xcrj.interview150.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/snakes-and-ladders
 * 蛇梯棋
 * 方格编号：1到n^2。第n行，正向，1~n；第n-1行，逆向，n+1~2*n；第n-2行，正向，2*n+1~3*n
 * 有蛇或梯子：board[i][j]>0
 * nxt：可以走6步，蛇，梯子，共同决定
 */
public class Solution07 {
    /**
     * 广度优先遍历 求图最短路径 最短移动次数
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        //visited[编号i]=是否被访问。编号从1~n^2，所以n*n+1
        boolean[] visited = new boolean[n * n + 1];
        //int[2]={编号,n步}
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{1, 0});
        int[] cs, ns;
        int nxt, target = n * n, step;
        while (!que.isEmpty()) {
            cs = que.poll();
            //可以走6步
            for (int i = 1; i <= 6; i++) {
                nxt = cs[0] + i;
                //越界, 后面的i更大所以直接break
                if (nxt > target) break;
                //走到nxt，是否遇到蛇或梯子，遇到了直接瞬移
                int[] rc = idToBoardRowColumn(nxt, n);
                if (board[rc[0]][rc[1]] > 0) {
                    nxt = board[rc[0]][rc[1]];
                }
                //当前走的步数 + nxt走了1步
                step = cs[1] + 1;
                if (nxt == target) return step;
                //访问nxt，再入队
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    que.offer(new int[]{nxt, step});
                }
            }
        }
        return -1;
    }

    /**
     * 编号 转 board[行号][列号]
     *
     * @param id
     * @param n
     * @return int[]{r,c}
     */
    private int[] idToBoardRowColumn(int id, int n) {
        //r c 从0开始
        int r = (id - 1) / n, c = (id - 1) % n;
        //奇数行 逆向编号
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        //board[行号][列号] 从上往下
        return new int[]{n - 1 - r, c};
    }
}
