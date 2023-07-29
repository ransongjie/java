package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 108. 单词演变，beginWord每次变化1个字母到endWord
 * 在字典（单词列表） wordList 中，从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * - 序列中第一个单词是 beginWord 。
 * - 序列中最后一个单词是 endWord 。
 * - 每次转换只能改变一个字母。
 * -转换过程中的中间单词必须是字典wordList 中的单词。
 * 给定两个长度相同但内容不同的单词 beginWord和 endWord 和一个字典 wordList ，找到从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * <p>
 * 分析：
 * - 求的是最短转换序列中的单词数目，每次转换只能改变一个字母,一个单词变化1个字母后等于另一个单词二者才能够转换
 * - endWord必须在wordList中，不在则一定不能转换成功，直接返回0
 */
public class Solution108 {
    /*！下面两个数据结构联合组成了 邻接表存储结构*/
    // ！Map<word,id>
    Map<String, Integer> wordId = new HashMap<>();
    // ！idx是点，List<邻接点>
    List<List<Integer>> edge = new ArrayList<>();
    // 结点初始编号
    int nodeNum = 0;

    /**
     * 广度优先搜索+优化建图
     * 建立图：只变化1个字母后相等的单词视为相连边
     * 优化建图：将单词的所有字母逐个变化为*，并将单词与这些变化后的单词相连，规避了需要判断两个单词变化那一个字母后相等，因为两个单词如果变化1个字母后相等，则两个单词都与变化加*单词相连
     * 广度优先搜索：从beginWord》中间word》变化word》endword。广度优先搜索可以求单源最短路径
     * beginWord》endWord的最短转换序列中的单词数目 转化为 从beginWord到endWord的最短路径
     * 最短路径=广度优先搜索走过的路径/2+1。/2因为走过了每个单词的变化单词，+1，加上beginWord。因为求的是最短转换序列中的单词数目
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 创建优化图
        this.addEdge(beginWord);
        for (String word : wordList) {
            this.addEdge(word);
        }

        // endWord不再wordList中 无法从beginWord转换到endWord
        if (!this.wordId.containsKey(endWord)) return 0;

        // 广度优先搜索记录起点到每个结点的最短距离
        int[] minDistance = new int[this.nodeNum];
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        // 获取广度优先搜索的起点和终点
        int beginWordNum = this.wordId.get(beginWord);
        int endWordNum = this.wordId.get(endWord);
        // 起点到起点的最短距离为0
        minDistance[beginWordNum] = 0;

        // 开始广度优先搜索
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(beginWordNum);
        while (!queue.isEmpty()) {
            int numI = queue.poll();
            // 到达endWord，转换成功
            if (endWordNum == numI) {
                return minDistance[endWordNum] / 2 + 1;
            }
            // 从边中获取结点所有邻接点
            for (Integer numJ : this.edge.get(numI)) {
                // 第1次广度优先遍历到的距离才是最短距离
                // 无向图，numI到numJ，numJ到numI
                // minDistance[2] = minDistance[1] + 1;
                // minDistance[1] = minDistance[2] + 1;
                if (Integer.MAX_VALUE == minDistance[numJ]) {
                    minDistance[numJ] = minDistance[numI] + 1;
                    queue.offer(numJ);
                }
            }
        }

        return 0;
    }

    /**
     * 处理无向图边的左端点和右端点(变化单词)
     *
     * @param word
     */
    private void addEdge(String word) {
        // 处理左端点
        this.addWord(word);
        // 处理右端点
        int nodeINum = this.wordId.get(word);
        // 遍历word每个字母将每个字母变成* 加入到hash表和边表
        char[] wordcs = word.toCharArray();
        for (int k = 0; k < word.length(); k++) {
            char temp = wordcs[k];
            wordcs[k] = '*';
            String changeWord = new String(wordcs);
            this.addWord(changeWord);
            int nodeJNum = this.wordId.get(changeWord);
            // 无向图
            this.edge.get(nodeINum).add(nodeJNum);
            this.edge.get(nodeJNum).add(nodeINum);
            wordcs[k] = temp;
        }
    }

    /**
     * 处理单词，将单词加入hash表并创建邻接点容器
     *
     * @param word
     */
    private void addWord(String word) {
        if (!this.wordId.containsKey(word)) {
            this.wordId.put(word, this.nodeNum++);
            this.edge.add(new ArrayList<>());
        }
    }

    /**
     * 双向广度优先搜索+优化建图，可以显著减小搜索空间大小
     * 从beginWord开始广度优先搜索。从endWord开始广度优先搜索。访问了同一结点就停止搜索
     * <p>
     * 如何判断访问了同一结点
     * - 当结果数组被另一个广度优先搜索赋值时退出搜索
     * - 另一个最短距离结果数组的第k个元素被赋值：Integer.MAX_VALUE != endMinDistance[nodeI]，Integer.MAX_VALUE != beginMinDistance[nodeJ]
     * <p>
     * 最终结果
     * - (beginWord到nodeI的距离+endWord到nodeI的距离)/2+1<=>(beginMinDistance[nodeI] + endMinDistance[nodeI]) / 2 + 1;
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 创建优化图
        this.addEdge(beginWord);
        for (String word : wordList) {
            this.addEdge(word);
        }

        // endWord不再wordList中 无法从beginWord转换到endWord
        if (!this.wordId.containsKey(endWord)) return 0;

        /*从beginWord开始的最短距离*/
        // 广度优先搜索记录起点到每个结点的最短距离
        int[] beginMinDistance = new int[this.nodeNum];
        Arrays.fill(beginMinDistance, Integer.MAX_VALUE);
        // 获取广度优先搜索的起点和终点
        int beginWordNum = this.wordId.get(beginWord);
        // 起点到起点的最短距离为0
        beginMinDistance[beginWordNum] = 0;
        // 广度优先搜索队列，从beginWord开始广度优先搜索
        Queue<Integer> beginQueue = new ArrayDeque<>();
        beginQueue.offer(beginWordNum);

        /*从endWord开始的最短距离*/
        // 广度优先搜索记录起点到每个结点的最短距离
        int[] endMinDistance = new int[this.nodeNum];
        Arrays.fill(endMinDistance, Integer.MAX_VALUE);
        // 获取广度优先搜索的起点和终点
        int endWordNum = this.wordId.get(endWord);
        // 起点到起点的最短距离为0
        endMinDistance[endWordNum] = 0;
        // 广度优先搜索队列，从endWord开始广度优先搜索
        Queue<Integer> endQueue = new ArrayDeque<>();
        endQueue.offer(endWordNum);

        // 开始广度优先搜索
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // 从beginWord开始的广度优先搜索
            int beginQueSize = beginQueue.size();
            for (int i = 0; i < beginQueSize; i++) {
                int nodeI = beginQueue.poll();
                // nodeI被另一个广度优先访问过(看endMinDistance数组),访问了同一顶点就停止搜索
                if (Integer.MAX_VALUE != endMinDistance[nodeI])
                    return (beginMinDistance[nodeI] + endMinDistance[nodeI]) / 2 + 1;
                // 从边中获取结点所有邻接点
                for (int nodeJ : this.edge.get(nodeI)) {
                    // 无向图 nodeI到nodeJ, nodeJ到nodeI
                    if (Integer.MAX_VALUE == beginMinDistance[nodeJ]) {
                        beginMinDistance[nodeJ] = beginMinDistance[nodeI] + 1;
                        beginQueue.offer(nodeJ);
                    }
                }
            }

            // 从endWord开始的广度优先搜索
            int endQueSize = endQueue.size();
            for (int j = 0; j < endQueSize; j++) {
                int nodeJ = endQueue.poll();
                // nodeJ被另一个广度优先访问过(看beginMinDistance数组),访问了同一顶点就停止搜索
                if (Integer.MAX_VALUE != beginMinDistance[nodeJ])
                    return (endMinDistance[nodeJ] + beginMinDistance[nodeJ]) / 2 + 1;
                // 从边中获取结点所有邻接点
                for (int nodeI : this.edge.get(nodeJ)) {
                    // 无向图 nodeI到nodeJ, nodeJ到nodeI
                    if (Integer.MAX_VALUE == endMinDistance[nodeI]) {
                        endMinDistance[nodeI] = endMinDistance[nodeJ] + 1;
                        endQueue.offer(nodeI);
                    }
                }
            }
        }
        return 0;
    }
}
