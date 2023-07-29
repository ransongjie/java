package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 113. 课程顺序，可行的修课顺序
 * 现在总共有 numCourses 门课需要选，记为 0 到 numCourses-1。
 * 给定一个数组prerequisites ，它的每一个元素prerequisites[i]表示两门课程之间的先修顺序。例如prerequisites[i] = [ai, bi]表示想要学习课程 ai，需要先完成课程 bi。
 * 请根据给出的总课程数  numCourses 和表示先修顺序的 prerequisites 得出一个可行的修课序列。
 * 可能会有多个正确的顺序，只要任意返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 */
public class Solution113 {
    // 邻接表，邻接点
    List<List<Integer>> adjNodes;
    // 结点访问状态状态：0=未搜索，1=搜索中，2=已完成
    int[] status;
    // 顺序栈，栈空top=n，入栈--top，栈满top=0，下标 n-1 为栈底，0 为栈顶
    int[] rs;
    // 栈下标
    int top;
    // 剪枝，有环及时退出
    boolean valid = true;

    /**
     * 拓扑图（有向图）+深度优先寻找可行拓扑排序序列
     * 结点状态：从u开始dfs到v
     * - v=0,未搜索：对u进行dfs时，发现相邻结点v（先修课程）是未搜索状态，对v进行dfs
     * - v=1,搜索中：对u进行dfs时，发现相邻结点v（先修课程）是搜索中状态，发现环，valid=true
     * - v=2,已完成：对u进行dfs时，发现相邻结点v（先修课程）是已完成状态，先修课程已完成，不对进行任何操作
     */
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        // 初始化
        this.status = new int[numCourses];
        this.rs = new int[numCourses];
        this.top = numCourses;
        // 构建邻接表
        this.adjNodes = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            this.adjNodes.add(new ArrayList<>());
        }
        // 处理prerequisites
        // 课程1修完再修课程0
        for (int[] prerequisite : prerequisites) {
            this.adjNodes.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 每次选一个 未搜索状态 的结点，进行深度优先搜索
        for (int i = 0; i < numCourses && valid; i++) {
            if (0 == this.status[i]) dfs(i);
        }

        // 存在环，没有可行的拓扑排序序列，没有可行的课程选修顺序，返回空数组
        if (!valid) return new int[0];

        // 没有环，是拓扑排序，返回结果
        return rs;
    }

    /**
     * 深度优先搜索回溯时将结点入结果栈
     */
    private void dfs(int u) {
        // 标记当前结点为搜索中状态
        this.status[u] = 1;
        // 搜索相邻节点
        // 发现有环，立即停止停止搜索
        for (Integer v : this.adjNodes.get(u)) {
            // 相邻结点是未搜索状态对相邻结点进行dfs
            if (0 == this.status[v]) {
                dfs(v);
                if (!this.valid) return;
            }

            // 相邻结点是搜索中状态找到了环
            if (1 == this.status[v]) {
                this.valid = false;
                return;
            }
        }
        // 结点标记为已完成状态
        this.status[u] = 2;
        // 回溯时将结点入结果栈
        this.rs[--this.top] = u;
    }

    // 存储每个结点的入度
    int[] inDegree;
    // 答案下标
    int idx;

    /**
     * 拓扑图（有向图）+广度优先遍历寻找可行拓扑排序序列
     * - 将入度为0的结点入队列，将相邻结点的入度-1，再将入度为0的结点入队
     * - 遍历完所有结点，队列中就是可选的拓扑排序序列，可行的修可顺序
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        // 初始化
        this.inDegree = new int[numCourses];
        this.rs = new int[numCourses];
        this.idx = 0;

        // 构建邻接表
        this.adjNodes = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            this.adjNodes.add(new ArrayList<>());
        }

        // 处理prerequisites
        // 课程1修完再修课程0
        // 课程0的入度+1
        for (int[] prerequisite : prerequisites) {
            this.adjNodes.get(prerequisite[1]).add(prerequisite[0]);
            ++this.inDegree[prerequisite[0]];
        }

        // 将所有入度为 0 的节点放入队列中
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < this.inDegree.length; i++) {
            if (0 == this.inDegree[i]) que.offer(i);
        }

        // 开始广度优先遍历
        while (!que.isEmpty()) {
            int u = que.poll();
            this.rs[idx++] = u;
            for (Integer v : this.adjNodes.get(u)) {
                this.inDegree[v]--;
                if (0 == this.inDegree[v]) que.offer(v);
            }
        }

        // 遍历完所有结点
        if (idx == numCourses) return this.rs;

        return new int[0];
    }
}
