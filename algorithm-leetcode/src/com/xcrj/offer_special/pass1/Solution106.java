package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 106. 二分图，成功遍历完所有结点
 * 无向图 ，
 * 是否连通，这个图可能不是连通图
 * 结点数目和结点编号，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号
 * 存储方式，graph[][] 行头是起始结点，行中是与行头直接相邻的结点，类似邻接表的表示方式
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * <p>
 * 二分图定义：
 * - 如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 * - 在遍历搜索过程中将相邻结点赋予不同的颜色（只有A和B两种颜色），成功遍历上色完所有结点，则该图是二分图
 */
public class Solution106 {
    /*结点状态*/
    // 无色状态
    private static final int UNCOLORED = 0;
    // 红色状态
    private static final int RED = 1;
    // 绿色状态
    private static final int GREEN = 2;
    // 记录结点被赋予的颜色 color[结点编号]=颜色
    // 这个数组的作用类似传统深度优先搜索图的visited[]
    int[] nodeColor;
    // 结果，是否为二分图
    boolean valid;

    /**
     * 深度优先向四周搜素，任选结点染成红儿，相邻结点上色为另一种颜色
     * 算法的流程：
     * - 我们任选一个节点开始，将其染成红色，并从该节点开始对整个无向图进行遍历
     * - 在遍历的过程中，如果我们通过节点u遍历到了节点v（即u和v在图中有一条边直接相连），那么会有两种情况：
     * -- 如果v未被染色，那么我们将其染成与u不同的颜色，并对v直接相连的节点进行深度优先
     * -- 如果v被染色，并且颜色与u相同，那么说明给定的无向图不是二分图。我们可以直接退出遍历并返回false作为答案。
     * - 当遍历完所有结点时，说明给定的无向图是二分图，返回true作为答案。
     */
    public boolean isBipartite1(int[][] graph) {
        /*初始化*/
        // 初始认为是有效的二分图
        this.valid = true;
        // 初始化颜色数组为无色
        this.nodeColor = new int[graph.length];
        Arrays.fill(nodeColor, UNCOLORED);
        // 可能是非连通图，寻找颜色数组中的无色结点进行深度优先
        // this.valid 剪枝
        for (int i = 0; i < graph.length && this.valid; i++) {
            if (UNCOLORED == nodeColor[i]) dfs(graph, i, RED);
        }

        return this.valid;
    }

    /**
     * @param graph
     * @param node  结点编号
     * @param color 结点颜色
     */
    private void dfs(int[][] graph, int node, int color) {
        // 给当前结点上色
        this.nodeColor[node] = color;
        // 相邻结点颜色
        int neighborColor = RED == color ? GREEN : RED;
        // 深度优先给相邻结点上色
        for (int neighbor : graph[node]) {
            // 未被上色，上色为不同颜色
            if (UNCOLORED == this.nodeColor[neighbor]) {
                dfs(graph, neighbor, neighborColor);
                // 剪枝
                if (!this.valid) return;
            }
            // 已经被上色，颜色相同，无效二分图
            else if (color == this.nodeColor[neighbor]) {
                valid = false;
                return;
            }
        }
    }

    /**
     * 广度优先向四周搜素，任选结点染成红儿，相邻结点上色为另一种颜色
     */
    public boolean isBipartite2(int[][] graph) {
        /*初始化*/
        // 初始认为是有效的二分图
        this.valid = true;
        // 初始化颜色数组为无色
        this.nodeColor = new int[graph.length];
        Arrays.fill(this.nodeColor, UNCOLORED);

        // 可能是非连通图，寻找颜色数组中的无色结点进行广度优先
        for (int i = 0; i < graph.length; i++) {
            if (UNCOLORED == this.nodeColor[i]) {
                bfs(graph, i, RED);
                if (!valid) return false;
            }
        }

        return true;
    }

    /**
     * @param graph
     * @param node  结点编号
     * @param color 结点颜色
     */
    private void bfs(int[][] graph, int node, int color) {
        // 入队后上色
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(node);
        this.nodeColor[node] = color;
        // 队列非空
        while (!que.isEmpty()) {
            // 出队结点
            int curNode = que.poll();
            // 获取相邻结点应该上的颜色
            int neighborColor = RED == this.nodeColor[curNode] ? GREEN : RED;

            for (int neighbor : graph[curNode]) {
                // 相邻未上色结点，入队后上色
                if (UNCOLORED == this.nodeColor[neighbor]) {
                    que.offer(neighbor);
                    this.nodeColor[neighbor] = neighborColor;
                }
                // 相邻同色结点，返回false，不是二分图
                else if (this.nodeColor[curNode] == this.nodeColor[neighbor]) {
                    valid = false;
                    return;
                }
            }
        }

        valid = true;
    }
}
