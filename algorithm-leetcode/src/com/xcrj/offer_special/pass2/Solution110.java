package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer II 110. 所有路径
 * 输入，有向图邻接矩阵 int[][] graph
 * 输出，从 0 到 n-1 的所有路径
 */
public class Solution110 {
    // 结果，记录所有0点到n-1点的路径
    List<List<Integer>> rss = new ArrayList<>();
    // 不直接使用Stack类原因，不能直接利用new ArrayList<Integer>(stack)
    //    Stack<Integer> stack=new Stack<>();
    Deque<Integer> stack = new ArrayDeque<>();

    //回溯法
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length-1);
        return rss;
    }

    /**
     * 有向图，不会重复访问，不使用visited记录访问过的点
     * @param graph 图
     * @param i     点索引
     * @param end   遍历终点n-1，graph.length-1就是第n-1个点
     */
    private void dfs(int[][] graph, int i, int end) {
        if(i==end)rss.add(new ArrayList<>(stack));

        for(int j:graph[i]){
            //深度
            stack.offerLast(j);
            dfs(graph, j, end);
            //回溯
            stack.pollLast();
        }
    }
}
