package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 118. 多余的边，树被多加上一条边，去掉导致环的最后一条边
 * 树可以看成是一个连通且无环的无向图。
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。
 * 图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。
 * 
 * edges 一行中存储了一条边的两个端点
 */ 
public class Solution118 {
    /**
     * 并查集相连结点同根则存在环，不同根则不存在环
     * 只加了一条边，顺序处理发现环的边就是最后一条导致环的边
     * @param edges 一行中存储了一条边的两个端点
     * @return
     */ 
    public int[] findRedundantConnection(int[][] edges) {
        // 在树的基础上加了一条边，因此结点数量=边数量
        int numNode=edges.length;
        // 因为结点编号从1开始到n
        int[] roots=new int[numNode+1];

        // 初始化并查集
		// parent[结点编号]=父节点编号，i=1,第0个位置不使用
        for(int i=1;i<roots.length;i++){
            roots[i]=i;
        }

        // 并查集操作,edges中的每条相连的边寻找环
        // 为什么能够返回edges中最后出现的导致环的边，因为按照edges顺序遍历&&树中只加了一条边，
        // 顺序遍历中导致出现环的边就是加的那条边，后续不会有导致环的边出现了，只加了一条边
        for(int[] edge:edges){
            int i=edge[0];
            int j=edge[1];
            // 相连结点不同根则不存在环，联合两结点的根结点
            if(findRoot(roots, i)!=findRoot(roots, j)){
                this.union(roots, i, j);
            }
            // 相连结点同根则存在环，返回这个边即可
            else{
                return edge;
            }
        }
    
        // 返回空数组
        return new int[0];
    }

    /**
     * 联合根结点
     */ 
    private void union(int[] roots,int i,int j){
        roots[findRoot(roots, i)]=findRoot(roots, j);
    }

    /**
     * 寻找结点根结点
     */ 
    private int findRoot(int[] roots,int node){
        return roots[node]==node?node:findRoot(roots,roots[node]);
    }
}