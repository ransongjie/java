package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 113. 课程顺序，可行的修课顺序
 * 输入，
 * - numCourses 课程数量, 
 * - prerequisites[][] prerequisites[i] = [ai, bi]表示想要学习课程 ai，需要先完成课程 bi。
 * 输出，
 * int[] 可行的修课顺序。没有可行的修课顺序 返回空数组
 */
public class Solution113 {
    //邻接表
    List<List<Integer>> ijs;
    //结点访问状态：0=未搜索，1=搜索中(出现环)，2=已完成
    int[] status;
    // 顺序栈，栈空top=n，入栈--top，栈满top=0，下标 n-1 为栈底，0 为栈顶
    int[] rs;
    int top;
    //剪枝，有环及时退出
    boolean valid = true;

    //建图+深度优先拓扑排序
    //xcrj 深度优先 先将没有出度的结点入逆栈 正向栈依次是没有入度的结点
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        status=new int[numCourses];
        rs=new int[numCourses];
        top=numCourses;
        ijs=new ArrayList<>();
        for(int i=0;i<numCourses;i++)ijs.add(new ArrayList<>());

        //建图
        for(int[] pre:prerequisites)ijs.get(pre[1]).add(pre[0]);

        //深度优先搜索所有“未搜索结点”
        for(int i=0;i<numCourses;i++)if(status[i]==0)dfs(i);

        // 存在环，没有可行的拓扑排序序列，没有可行的课程选修顺序，返回空数组
        if (!valid) return new int[0];

        return rs;
    }

    private void dfs(int i) {
        //i 搜索中
        status[i]=1;

        for(int j:ijs.get(i)){
            if(0==status[j]){
                dfs(j);
                if(!valid)return;
            }

            if(1==status[j]){
                valid=false;
                return;
            }
        }

        //正常return
        //i 已完成
        status[i]=2;
        rs[--top]=i;
    }

    // 存储每个结点的入度
    int[] inDegree;
    int idx;
    
    //建图+广度优先拓扑排序
    //xcrj 广度优先 将没有入度的结点入数组
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        inDegree=new int[numCourses];
        rs=new int[numCourses];
        idx=0;

        ijs=new ArrayList<>();
        for(int i=0;i<numCourses;i++)ijs.add(new ArrayList<>());

        //建图
        for(int[] pre:prerequisites){
            ijs.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        //入度为0结点全部放入队列中
        Queue<Integer> que=new ArrayDeque<>();
        for(int i=0;i<numCourses;i++)if(inDegree[i]==0)que.offer(i);
        
        //广度优先遍历
        while(!que.isEmpty()){
            int i=que.poll();
            rs[idx++]=i;
            for(int j:ijs.get(i)){
                inDegree[j]--;
                if(inDegree[j]==0)que.offer(j);
            }
        }

        //遍历完所有结点
        if (idx == numCourses) return this.rs;

        return new int[0];
    }
}
