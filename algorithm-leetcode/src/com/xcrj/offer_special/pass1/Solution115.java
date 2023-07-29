package com.xcrj.offer_special.pass1;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer II 115. 重建序列
 * 给定一个长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。还提供了一个 2D 整数数组sequences，其中sequences[i]是nums的子序列。
 * 检查 nums 是否是唯一的最短超序列 。最短 超序列 是 长度最短 的序列，并且所有序列sequences[i]都是它的子序列。对于给定的数组sequences，可能存在多个有效的 超序列 。
 * - 例如，对于sequences = [[1,2],[1,3]]，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
 * - 而对于sequences = [[1,2],[1,3],[1,2,3]]，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
 * 如果 nums 是序列的唯一最短 超序列 ，则返回 true ，否则返回 false 。
 * 子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
 */
public class Solution115 {

    /**
     * 广度优先拓扑排序
     * 问题转化：求最短超序列 转化为 求唯一拓扑序列
     * 构建有向图：数字1到n是n个结点，sequences中子序列相邻元素表示一条有向边
     * 广度优先：不停将入度为0的结点入队，队列中存在多个结点时，拓扑排序不唯一，返回false
     */
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        // 构建邻接表
        Set<Integer>[] adjTable = new HashSet[nums.length + 1];
        // i=1，第0个位置未使用，不用构建邻接边
        for (int i = 1; i < nums.length + 1; i++) {
            adjTable[i] = new HashSet<>();
        }
        // 记录结点入度。n+1第0个位置未使用，已知 sequences[i][j] in [1,n]，结点编号从1开始的n，存储结构长度自然是n+1，第0个位置不使用
        int[] inDegrees = new int[nums.length + 1];
        // 为邻接表填充内容
        for (int i = 0; i < sequences.length; i++) {
            for (int j = 1; j < sequences[i].length; j++) {
                int prev = sequences[i][j - 1];
                int curr = sequences[i][j];
                // ！set中不存在add之后返回true，存在返回false
                // 相同结点只记录1次入度
                if (adjTable[prev].add(curr))
                    inDegrees[curr]++;
            }
        }

        // 广度优先队列
        Queue<Integer> que = new ArrayDeque<>();
        // 将入度为0的结点放入队列
        // i=1，第0个位置未使用
        for (int i = 1; i < inDegrees.length; i++) {
            if (0 == inDegrees[i])
                que.offer(i);
        }

        // 开始广度优先遍历
        while (!que.isEmpty()) {
            // 若队列中结点数量大于1，表示拓扑排序不唯一，返回false
            if (1 < que.size()) {
                return false;
            }
            // 队列中结点数量唯一，继续将入度为0的结点入队
            int u = que.poll();
            for (int v : adjTable[u]) {
                if (0 == --inDegrees[v]) {
                    que.offer(v);
                }
            }
        }

        // 广度优先遍历完，队列中始终只存在1个结点，拓扑排序唯一，返回true
        return true;
    }
}
