package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 118. 多余的边，树被多加上一条边，去掉导致环的最后一条边
 * 树是一个连通且无环的无向图，在树中多了一条边之后就会出现环，因此多余的边即为导致环出现的边。
 * 输入，edges 一行中存储了一条边的两个端点. edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * 输出，int[] 返回导致环的最后一条边
 * 注意，结点编号从1到n
 */
public class Solution118 {
    //并查集，同根则导致环
    public int[] findRedundantConnection(int[][] edges) {
        int nodeNum=edges.length;
        //+1 结点编号从1开始
        int[]roots=new int[nodeNum+1];
        for(int i=1;i<nodeNum+1;i++)roots[i]=i;

        for(int[] edge:edges){
            if(findRoot(roots, edge[0])==findRoot(roots, edge[1]))return edge;
            else union(roots, edge[0], edge[1]);
        }

        return new int[0];
    }

    private void union(int[] roots,int i,int j){
        int rooti=findRoot(roots, i);
        int rootj=findRoot(roots, j);
        roots[rooti]=roots[rootj];
    }

    private int findRoot(int[] roots,int node){
        int pnode=roots[node];
        if(node!=pnode){
            int ppnode=findRoot(roots, pnode);
            roots[node]=ppnode;
        }

        return roots[node];
    }
}
