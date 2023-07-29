package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 116. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * <br/>
 * 分析：
 * - 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市
 * - 省份包含城市，同一个省份的城市彼此相连，不同省份的城市不相连
 */ 
public class Solution116 {
    /**
     * 深度优先遍历
     * isConnected视为邻接矩阵
     * 省份数量，连通分量个数
     */ 
    public int findCircleNum1(int[][] isConnected) {
        // 省份数量
        int province=0;
        // 城市数量
        int numCity=isConnected.length;
        // 城市访问标识
        boolean[] visited=new boolean[numCity];

        // 遍历每一列
        for(int i=0;i<numCity;i++){
            if(!visited[i]){
                dfs(isConnected,visited,numCity,i);
                province++;
            }
        }

        return province;
    }

    /**
     * @param numCity 城市数量
     * @param i 遍历第几个城市
     */ 
    private void dfs(int[][] isConnected,boolean[] visited,int numCity,int i){
        // 深度优先遍历每一行
        for(int j=0;j<numCity;j++){
            if(1==isConnected[i][j]&&!visited[j]){
                visited[j]=true;
                dfs(isConnected,visited,numCity,j);
            }
        }
    }

    /**
     * 广度优先遍历
     */ 
    public int findCircleNum2(int[][] isConnected) {
        int numCity=isConnected.length;
        // 记录结点是否被访问
        boolean[] visited=new boolean[numCity];
        // 广度优先所需队列
        Queue<Integer> que=new ArrayDeque();
        // 遍历列
        int province=0;
        for(int i=0;i<numCity;i++){
            if(!visited[i]){
                que.offer(i);
                while(!que.isEmpty()){
                    int k=que.poll();
                    // 广度遍历邻接矩阵一行
                    for(int j=0;j<numCity;j++){
                        if(1==isConnected[k][j]&&!visited[j]){
                            visited[j]=true;
                            que.offer(j);
                        }
                    }
                }
                province++;
            }
        }
        
        return province;
    }

    /**
     * 并查集，连通图结点通过并查集找到同根，合并根结点，同根个数等于省份数量
     */ 
    public int findCircleNum3(int[][] isConnected) {
        int numCity=isConnected.length;
        // 创建并查集
        int[] roots=new int[numCity];
        // 初始化并查集
        for(int i=0;i<numCity;i++){
            roots[i]=i;
        }

        // 合并相连城市并查集
        for(int i=0;i<numCity;i++){
            // 邻接矩阵是一个主对称阵，取上半部分即可
            for(int j=i+1;j<numCity;j++){
                // 合并相连城市
                if(1==isConnected[i][j])
                    this.union(roots,i,j);
            }
        }

        // 统计连通分量数量
        int province=0;
        for(int i=0;i<numCity;i++){
            if(i==roots[i]) province++;
        }

        return province;
    }

    /**
     * 合并结点的根结点 parent[index1Root]=index2Root
     * 若index1和index2同根，则parent[index1Root]等于index2Root
     */ 
    private void union(int[] roots,int city1,int city2){
        roots[findRoot(roots,city1)]=findRoot(roots,city2);
    }

    /**
     * 寻找结点的根结点
     */ 
    private int findRoot(int[] roots,int city){
        // 相等则找到了根结点
        if(city!=roots[city]){
            // 找父亲的根结点
            // 将父亲的根结点更新为我的根结点
            // 所有的连通图中的结点的根结点都会被统一为一个结点
            roots[city]=findRoot(roots,roots[city]);
        }

        return roots[city];
    }
}