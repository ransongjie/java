package com.xcrj.interview150.divide_conquer;

/**
 * https://leetcode.cn/problems/construct-quad-tree
 * 矩阵代表四叉树，
 * 正方形区域的值全相同的时候，那么这个区域代表一个叶节点，否则不是叶节点，
 * 它的4个孩子结点就是四分对应矩阵区域所代表的的子树
 */
public class Solution02 {
    public Node construct(int[][] grid) {
        return f(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    /**
     * @param grid
     * @param u    上
     * @param b    下
     * @param l    左
     * @param r    右
     * @return
     */
    private Node f(int[][] grid, int u, int b, int l, int r) {
        if (isSame(grid, u, b, l, r)) {
            //val=true/false,true(is leaf)
            return new Node(grid[u][l] == 1 ? true : false, true);
        }
        // is not leaf
        int hmid = (u + b) >> 1;//横
        int vmid = (l + r) >> 1;//竖
        Node ul = f(grid, u, hmid, l, vmid);
        Node ur = f(grid, u, hmid, vmid + 1, r);//注意前后都包括
        Node bl = f(grid, hmid + 1, b, l, vmid);
        Node br = f(grid, hmid + 1, b, vmid + 1, r);
        // val, is not leaf
        return new Node(true, false, ul, ur, bl, br);
    }

    //区域内是否都是1或0
    private boolean isSame(int[][] grid, int u, int b, int l, int r) {
        for (int i = u; i <= b; i++) {
            for (int j = l; j <= r; j++) {
                //都等于同一个值
                if (grid[u][l] != grid[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * 前缀和优化 isSame
     * 某一部分均为 0 时，它的和为 0；某一部分均为 1 时，它的和为这一部分的面积大小。
     *
     * @param grid
     * @return
     */
    public Node construct2(int[][] grid) {
        // 2-dimension preSum
        int[][] preSum = new int[grid.length + 1][grid.length + 1];// gird整个是n*n
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid.length; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        return f2(grid, preSum, 0, grid.length, 0, grid.length);
    }

    /**
     * @param grid
     * @param u    上
     * @param b    下
     * @param l    左
     * @param r    右
     * @return
     */
    private Node f2(int[][] grid, int[][] preSum, int u, int b, int l, int r) {
        if (isSame2(grid, preSum, u, b, l, r)) {
            //val=true/false,true(is leaf)
            return new Node(grid[u][l] == 1 ? true : false, true);
        }
        // is not leaf
        int hmid = (u + b) >> 1;//横
        int vmid = (l + r) >> 1;//竖
        Node ul = f2(grid, preSum, u, hmid, l, vmid);
        Node ur = f2(grid, preSum, u, hmid, vmid, r);
        Node bl = f2(grid, preSum, hmid, b, l, vmid);
        Node br = f2(grid, preSum, hmid, b, vmid, r);
        // val, is not leaf
        return new Node(true, false, ul, ur, bl, br);
    }

    /**
     * @param grid
     * @param u
     * @param b
     * @param l
     * @param r
     * @return
     */
    private boolean isSame2(int[][] grid, int[][] preSum, int u, int b, int l, int r) {
        int area = preSum[b][r] - preSum[u][r] - preSum[b][l] + preSum[u][l];
        if (area == 0 || area == (b - u) * (r - l)) return true;
        return false;
    }
}
