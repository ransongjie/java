package com.xcrj.interview150.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/course-schedule
 * <p>
 * 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi]
 * ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
class Solution05 {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建邻接表
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] info : prerequisites) {
            // 学习课程0，需要先学习课程1。课程1需要先学习，课程1在课程0的前面
            edges.get(info[1]).add(info[0]);
        }

        // 记录学习过的课程，visited[i]=0 没有学习过这个课程 1学习i的前提课程中 2学习i课程
        visited = new int[numCourses];
        for (int i = 0; i < numCourses && valid; ++i) {
            // 课程没有学过则学习
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        return valid;
    }

    /**
     * 深度优先寻找环 产生环则不能把所有课程修完
     * valid false 产生环, true 无环
     * ß
     *
     * @param u
     */
    public void dfs(int u) {
        // 学习u前提课程中
        visited[u] = 1;
        // 遍历u的前提课程
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            // 产生环 互为前提课程
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 学习u课程
        visited[u] = 2;
    }
}
