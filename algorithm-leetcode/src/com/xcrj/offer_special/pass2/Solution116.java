package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 116. 省份数量
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市
 * 省份包含城市，同一个省份的城市彼此相连，不同省份的城市不相连
 * 省份数量等于连通分量数量
 */
public class Solution116 {
    //深度优先，每次深度优先可以遍历完一个连通分量（1个省份）
    public int findCircleNum1(int[][] isConnected) {
        int numCity=isConnected.length;
        boolean[] visited=new boolean[numCity];
        int province=0;
        for(int i=0;i<numCity;i++){
            if(!visited[i]){
               dfs(isConnected, visited, numCity, i);
               province++;
            }
        }
        return province;
    }

    /**
     * 
     * @param isConnected 
     * @param visited
     * @param numCity 城市数量
     * @param i 遍历第几个城市
     */
    private void dfs(int[][] isConnected,boolean[] visited,int numCity,int i){
        for(int j=0;j<isConnected[i].length;j++){
            if(1==isConnected[i][j]&&!visited[j]){
                visited[j]=true;
                dfs(isConnected, visited, numCity, j);
            }
        }
    }

    //广度优先，每次广度优先可以遍历完一个连通分量（1个省份）
    public int findCircleNum2(int[][] isConnected) {
        int numCity=isConnected.length;
        boolean[] visited=new boolean[numCity];
        int province=0;

        Queue<Integer> que=new ArrayDeque<>();
        for(int i=0;i<numCity;i++){
            if(!visited[i]){
                que.offer(i);
                while(!que.isEmpty()){
                    int nodeI=que.poll();
                    for(int j=0;j<isConnected[nodeI].length;j++){
                        if(isConnected[nodeI][j]==1&&!visited[j]){
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

    //xcrj 并查集，合并根节点，根节点数量等于省份数量
    public int findCircleNum3(int[][] isConnected) {
        int numCity=isConnected.length;
        //并查集
        int[] roots=new int[numCity];
        for(int i=0;i<numCity;i++)roots[i]=i;

        //合并根节点
        for(int i=0;i<numCity;i++){
            //邻接矩阵是对称阵
            for(int j=i+1;j<numCity;j++){
                if(isConnected[i][j]==1)union(roots, i, j);
            }
        }

        //根节点数量, xcrj 下标等于值的是根节点
        //roots[0]=1, roots[1]=2, roots[2]=2
        int province=0;
        for(int i=0;i<numCity;i++)if(i==roots[i])province++;

        System.out.println(Arrays.toString(roots));
        System.out.println(province);

        return province;
    }

    private void union(int[] roots,int city1,int city2){
        int rootCity1=findRoot(roots, city1);
        int rootCity2=findRoot(roots, city2);
        roots[rootCity1]=roots[rootCity2];
    }

    //寻找city的根节点
    private int findRoot(int[] roots,int city){
        int pcity=roots[city];
        if(city!=pcity){
            int ppcity=findRoot(roots, pcity);
            roots[city]=ppcity;
        }

        return roots[city];
    }

    public static void main(String[] args) {
        Solution116 solution116=new Solution116();
        int[][] isConnected=new int[3][];
        isConnected[0]=new int[]{1,1,1};
        isConnected[1]=new int[]{1,1,0};
        isConnected[2]=new int[]{1,0,1};
        solution116.findCircleNum3(isConnected);
    }
}
