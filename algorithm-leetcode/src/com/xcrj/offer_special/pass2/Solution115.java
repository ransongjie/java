package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer II 115. 重建序列
 * 输入，nums[] in [1,n], sequences[][]
 * 输出，true, nums 是否是唯一的最短超序列
 * 最短超序列
 * sequences = [[1,2],[1,3]]，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 
 * sequences = [[1,2],[1,3],[1,2,3]]，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
 * 子序列：是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
 */
public class Solution115 {

    //建图+广度优先拓扑排序
    //问题转换：求唯一最短超序列 转化为 求唯一拓扑序列
    //建图：sequences中子序列相邻元素表示一条有向边
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        //邻接表
        //+1, 因为 nums[] in [1,n]
        Set<Integer>[] adjTable=new Set[nums.length+1];
        for(int i=1;i<nums.length+1;i++)adjTable[i]=new HashSet<>();

        //入度
        int[] inDegree=new int[nums.length+1];

        //建图
        for(int i=0;i<sequences.length;i++){
            for(int j=1;j<sequences[i].length;j++){
                int pre=sequences[i][j-1];
                int cur=sequences[i][j];
                //set中不存在add之后返回true，存在返回false
                //xcrj 相同结点 pre和cur 只记录1次入度
                if(adjTable[pre].add(cur))inDegree[cur]++;
            }
        }

        //入度为0的结点放入队列中
        Queue<Integer> que=new ArrayDeque<>();
        for(int i=1;i<nums.length+1;i++)if(0==inDegree[i])que.offer(i);

        //开始广度优先遍历
        while(!que.isEmpty()){
            //xcrj 若队列中结点数量大于1，表示拓扑排序不唯一，返回false
            if(que.size()>1)return false;
            int u=que.poll();
            for(int v:adjTable[u])if(0==--inDegree[v])que.offer(v);
        }
        
        return true;
    }
}
