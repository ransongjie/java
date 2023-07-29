package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 106. 二分图
 * 无向图 
 * 可能不是连通图
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
    //结点颜色
    private static final int UNCOLORED=0;
    private static final int RED=1;
    private static final int GREEN=2;
    // 记录结点被赋予的颜色 color[结点编号]=颜色
    // 这个数组的作用类似传统深度优先搜索图的visited[]
    private int[] nodeColors;
    private boolean valid;

    //深度优先向邻居搜素
    public boolean isBipartite1(int[][] graph) {
        nodeColors=new int[graph.length];
        Arrays.fill(nodeColors, UNCOLORED);
        valid=true;

        //非联通图
        for(int i=0;i<graph.length&&valid;i++){
            if(UNCOLORED==nodeColors[i]) dfs(graph, i, RED);
        }

        return valid;
    }

    //深度优先为结点上色
    private void dfs(int[][] graph, int node, int color) {
        nodeColors[node]=color;
        int neighborColor=RED==color?GREEN:RED;
        for(int neighbor:graph[node]){
            //相邻结点未上色
            if(UNCOLORED==nodeColors[neighbor]){
                dfs(graph, neighbor, neighborColor);
                if(!valid) return;
            }
            //相邻结点上色同色
            else if(color==nodeColors[neighbor]){
                valid=false;
                return;
            }
            //相邻结点上色不同色
        }
    }

    //广度优先向邻居搜素
    public boolean isBipartite2(int[][] graph) {
        nodeColors=new int[graph.length];
        Arrays.fill(nodeColors, UNCOLORED);
        valid=true;

        //非联通图
        for(int i=0;i<graph.length&&valid;i++){
            if(UNCOLORED==nodeColors[i]) bfs(graph, i, RED);
        }

        return valid;
    }

    //广度优先为结点上色
    private void bfs(int[][] graph, int node, int color) {
        Queue<Integer> que=new ArrayDeque<>();
        que.offer(node);
        nodeColors[node]=color;

        while(!que.isEmpty()){
            int currNode=que.poll();
            int neighborColor=nodeColors[currNode]==RED?GREEN:RED;
            //处理相邻结点
            for(int neighbor:graph[currNode]){
                //相邻结点未上色
                if(UNCOLORED==nodeColors[neighbor]){
                    que.offer(neighbor);
                    nodeColors[neighbor]=neighborColor;
                }
                //相邻结点同色
                else if(nodeColors[neighbor]==nodeColors[currNode]){
                    valid=false;
                    return;
                }
                //相邻结点不同色
            }
        }
    }
}
