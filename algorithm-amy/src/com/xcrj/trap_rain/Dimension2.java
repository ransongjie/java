package com.xcrj.trap_rain;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/trapping-rain-water-ii/
 * 二维接雨水
 */
public class Dimension2 {
    static class Node {
        //高度
        int height;
        //位置
        int row;
        int col;

        public Node(int h, int r, int c) {
            height = h;
            row = r;
            col = c;
        }
    }

    /**
     * 从外圈走向内圈
     * 本题中水只能上下左右4个方向流动。8个方向流动也是本题一样的解法
     *
     * @param ass
     * @return
     */
    public static int trapRainWater(int[][] ass) {
        if (ass == null || ass.length == 0 || ass[0] == null || ass[0].length == 0) return 0;
        //将最外圈放入堆
        PriorityQueue<Node> pque = new PriorityQueue<>(Comparator.comparingInt(o -> o.height));
        int m = ass.length, n = ass[0].length;
        boolean[][] isEntered = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            pque.offer(new Node(ass[0][i], 0, i));
            isEntered[0][i] = true;
        }
        for (int i = 0; i < m; i++) {
            pque.offer(new Node(ass[i][n - 1], i, n - 1));
            isEntered[i][n - 1] = true;
        }
        for (int i = n - 1; i >= 0; i--) {
            pque.offer(new Node(ass[m - 1][i], m - 1, i));
            isEntered[m - 1][i] = true;
        }
        for (int i = m - 1; i >= 0; i--) {
            pque.offer(new Node(ass[i][0], i, 0));
            isEntered[i][0] = true;
        }

        int ans = 0;
        //薄弱点，水位高于薄弱点，水从薄弱点流出
        int max = 0;
        //从薄弱点，往上下左右四个方向尝试
        while (!pque.isEmpty()) {
            Node node = pque.poll();
            //！！！更新薄弱点
            max = Math.max(max, node.height);
            //往上走1步
            if (node.row > 0 && !isEntered[node.row - 1][node.col]) {
                ans += Math.max(0, max - ass[node.row - 1][node.col]);
                pque.offer(new Node(ass[node.row - 1][node.col], node.row - 1, node.col));
                isEntered[node.row - 1][node.col] = true;
            }
            //往左走1步
            if (node.col > 0 && !isEntered[node.row][node.col - 1]) {
                ans += Math.max(0, max - ass[node.row][node.col - 1]);
                pque.offer(new Node(ass[node.row][node.col - 1], node.row, node.col - 1));
                isEntered[node.row][node.col - 1] = true;
            }
            //往下走1步
            if (node.row < m - 1 && !isEntered[node.row + 1][node.col]) {
                ans += Math.max(0, max - ass[node.row + 1][node.col]);
                pque.offer(new Node(ass[node.row + 1][node.col], node.row + 1, node.col));
                isEntered[node.row + 1][node.col] = true;
            }
            //往右走1步
            if (node.col < n - 1 && !isEntered[node.row][node.col + 1]) {
                ans += Math.max(0, max - ass[node.row][node.col + 1]);
                pque.offer(new Node(ass[node.row][node.col + 1], node.row, node.col + 1));
                isEntered[node.row][node.col + 1] = true;
            }
        }
        return ans;
    }
}
