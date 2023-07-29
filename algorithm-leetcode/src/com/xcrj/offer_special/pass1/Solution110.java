package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer II 110. 所有路径
 * 给定一个有 n 个节点的有向无环图，用二维数组 graph 表示，请找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
 * graph的第 i 个数组中的单元都表示有向图中 i号节点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ），若为空，就是没有下一个节点了。
 * <p>
 * 分析：
 * - int[][] graph是邻接表的存储结构
 * - 回溯法深度优先，由于是有向图，所以不会重复遍历点，不用visited记录访问过的点，路径点也就不会重复
 */
public class Solution110 {
    // 结果，记录所有0点到n-1点的路径
    List<List<Integer>> rss = new ArrayList<>();
    // 不直接使用Stack类原因，不能直接利用new ArrayList<Integer>(stack)
//    Stack<Integer> stack=new Stack<>();
    Deque<Integer> stack = new ArrayDeque<>();

    /**
     * 回溯法：确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 初始时将0点放入栈中
        this.stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return this.rss;
    }

    /**
     * @param graph 图
     * @param i     点索引
     * @param end   遍历终点n-1，graph.length-1就是第n-1个点
     */
    private void dfs(int[][] graph, int i, int end) {
        // 遍历到终点n-1
        if (i == end) {
            this.rss.add(new ArrayList<>(this.stack));
            return;
        }

        // 未遍历到终点n-1，深度优先
        for (int j : graph[i]) {
            // 往下入栈
            this.stack.offerLast(j);
            dfs(graph, j, end);
            // 回溯出栈
            this.stack.pollLast();
        }
    }
}
