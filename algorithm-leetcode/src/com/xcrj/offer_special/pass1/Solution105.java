package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 剑指 Offer II 105. 岛屿的最大面积
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */
public class Solution105 {
    /**
     * 深度优先搜索四周以grid的每个元素为起点往上下左右4个方向深度优先搜索拓展为1元素
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int maxR = 0;
        // 以grid的每个元素为起点往上下左右4个方向深度优先搜索
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxR = Math.max(maxR, dfs(grid, i, j));
            }
        }

        return maxR;
    }

    private int dfs(int[][] grid, int curI, int curJ) {
        // 特殊情况
        // 行值不能小于0
        if (curI < 0) return 0;
        // 列值不能小于0
        if (curJ < 0) return 0;
        // 行不能大于len-1
        if (curI == grid.length) return 0;
        // 列不能大于grid[0].len-1
        if (curJ == grid[0].length) return 0;
        // 当前元素值不能为0
        if (0 == grid[curI][curJ]) return 0;

        // 特殊情况已经处理完毕，grid[cur_i][cur_j]一定为1，访问置0
        grid[curI][curJ] = 0;

        // 往右一步，往左一步，往下一步，往上一步
        int[] stepI = {0, 0, 1, -1};
        int[] stepJ = {1, -1, 0, 0};

        // 上面grid[cur_i][cur_j]=1
        int r = 1;
        // 往四个方向上下左右四种情况
        for (int k = 0; k < 4; k++) {
            int nextI = curI + stepI[k];
            int nextJ = curJ + stepJ[k];
            // 分情况讨论用+法
            r += dfs(grid, nextI, nextJ);
        }
        return r;
    }

    /**
     * 深度优先搜索迭代方式以grid中每个元素为起点向四周拓展为1元素
     * - 使用栈记录未被访问的元素
     * - 栈非空则出栈元素向元素四周拓展可行元素
     */
    public int maxAreaOfIsland2(int[][] grid) {
        // 存储结果
        int r = 0;
        // 遍历gird中的每个元素
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 存储从grid[i][j]向四周出发能找到的最大面积
                int cr = 0;

                // 定义按行移动栈和按列移动栈
                Stack<Integer> stackI = new Stack<>();
                Stack<Integer> stackJ = new Stack<>();
                // 初始时将未访问的元素i和j放入栈中
                stackI.push(i);
                stackJ.push(j);

                // 栈中还有未访问的元素继续访问，stackI和stackJ中元素一样多
                while (!stackI.isEmpty()) {
                    // 出栈元素准备操作
                    int curI = stackI.pop();
                    int curJ = stackJ.pop();

                    // 特殊情况
                    // 行值不能小于0
                    if (curI < 0) continue;
                    // 列值不能小于0
                    if (curJ < 0) continue;
                    // 行不能大于len-1
                    if (curI == grid.length) continue;
                    // 列不能大于grid[0].len-1
                    if (curJ == grid[0].length) continue;
                    // 当前元素值不能为0
                    if (0 == grid[curI][curJ]) continue;

                    // 处理完特殊情况，grid[i][j]一定等于1，cur+1;
                    cr++;
                    // 访问后置0
                    grid[curI][curJ] = 0;

                    // 往右一步，往左一步，往下一步，往上一步
                    int[] stepI = {0, 0, 1, -1};
                    int[] stepJ = {1, -1, 0, 0};
                    // 往四个方向上下左右四种情况
                    for (int k = 0; k < 4; k++) {
                        int nextI = curI + stepI[k];
                        int nextJ = curJ + stepJ[k];
                        stackI.push(nextI);
                        stackJ.push(nextJ);
                    }
                }
                r = Math.max(r, cr);
            }
        }

        return r;
    }

    /**
     * 广度优先搜索以grid中每个元素为起点向四周拓展为1元素
     * - 队尾放入未被访问的元素
     * - 队列非空从对头出队向元素四周拓展可行元素
     */
    public int maxAreaOfIsland3(int[][] grid) {
        // 存储结果
        int r = 0;
        // 遍历gird中的每个元素
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 存储从grid[i][j]向四周出发能找到的最大面积
                int cr = 0;

                // 定义按行移动队列和按列移动队列
                Queue<Integer> queueI = new ArrayDeque<>();
                Queue<Integer> queueJ = new ArrayDeque<>();
                // 初始时将未访问的元素i和j放入队尾
                queueI.offer(i);
                queueJ.offer(j);

                // 队列中还有未访问的元素继续访问，queueI和queueJ中元素一样多
                while (!queueI.isEmpty()) {
                    // 出队头元素准备操作
                    int curI = queueI.poll();
                    int curJ = queueJ.poll();

                    // 特殊情况
                    // 行值不能小于0
                    if (curI < 0) continue;
                    // 列值不能小于0
                    if (curJ < 0) continue;
                    // 行不能大于len-1
                    if (curI == grid.length) continue;
                    // 列不能大于grid[0].len-1
                    if (curJ == grid[0].length) continue;
                    // 当前元素值不能为0
                    if (0 == grid[curI][curJ]) continue;

                    // 处理完特殊情况，grid[i][j]一定等于1，cur+1;
                    cr++;
                    // 访问后置0
                    grid[curI][curJ] = 0;

                    // 往右一步，往左一步，往下一步，往上一步
                    int[] stepI = {0, 0, 1, -1};
                    int[] stepJ = {1, -1, 0, 0};
                    // 往四个方向上下左右四种情况
                    for (int k = 0; k < 4; k++) {
                        int nextI = curI + stepI[k];
                        int nextJ = curJ + stepJ[k];
                        queueI.offer(nextI);
                        queueJ.offer(nextJ);
                    }
                }
                r = Math.max(r, cr);
            }
        }

        return r;
    }
}
