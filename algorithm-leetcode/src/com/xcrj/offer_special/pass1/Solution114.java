package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 114. 外星文字典，根据字典还原按字典顺序递增字母列表
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * - 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * - 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 */
public class Solution114 {
    // 结点访问状态状态：在states中不存在=未搜索，1=搜索中，2=已完成
    Map<Character, Integer> stateMap = new HashMap<>();
    // 邻接表
    Map<Character, List<Character>> adjTable = new HashMap<>();
    // 顺序栈，栈空top=n，入栈--top，栈满top=0，下标 n-1 为栈底，0 为栈顶
    char[] rs;
    // 栈下标
    int top;
    // 剪枝，有环及时退出
    boolean valid = true;
    /**
     * 拓扑排序题眼：存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
     * words[]中每个字符串按照外星语字母顺序进行了排序，相邻单词的相同位置的不同字母按字典序递增
     * 字符串 s 字典顺序小于 字符串 t 有两种情况
     * - 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
     * - 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
     * -- 若s是after单词，t是before单词，则after单词字典顺序小于before单词字典顺序，已知words中的前一个字符串字典顺序>后一个字符串字典顺序，因此不存在合法字母顺序
     *
     * 前缀单词，after是before的前缀单词，不存在合法字母顺序。因为，before单词中的字母无法邻接到after单词中的字母，无法完成拓扑排序
     * 字母之间的顺序关系存在由至少2个字母组成的环，例如words=['a','b','a','b']
     * 其余情况存在合法字母顺序
     */
    /**
     * 拓扑图（有向图）+深度优先寻找可行拓扑排序序列
     * 结点状态：从u开始dfs到v
     * - v=0,未搜索：对u进行dfs时，发现相邻结点v是未搜索状态，对v进行dfs
     * - v=1,搜索中：对u进行dfs时，发现相邻结点v是搜索中状态，发现环，valid=true
     * - v=2,已完成：对u进行dfs时，发现相邻结点v是已完成状态，不对进行任何操作
     */
    public String alienOrder1(String[] words) {
        // 构造每个字母的邻接表
        for (String word : words) {
            for (char c : word.toCharArray()) {
                this.adjTable.putIfAbsent(c, new ArrayList<>());
            }
        }

        // 初始化
        this.rs = new char[this.adjTable.size()];
        this.top = this.rs.length;

        // 同时处理前后2个单词构造完整邻接表
        for (int i = 1; i < words.length; i++) {
            buildAdjTable(words[i - 1], words[i]);
        }

        if (!valid) return "";

        // 每次选一个 未搜索状态 的结点，进行深度优先搜索
        for (char k : this.adjTable.keySet()) {
            if (!this.stateMap.containsKey(k))
                dfs(k);
        }

        // 存在环，没有可行的拓扑排序序列，没有可行的课程选修顺序，返回空字符串
        if (!this.valid) return "";

        return new String(this.rs);
    }

    /**
     * 构造完整邻接表
     *
     * @param before 前一个单词
     * @param after  后一个单词
     */
    private void buildAdjTable(String before, String after) {
        // 取前后2个单词的最小长度
        int beforeLen = before.length();
        int afterLen = after.length();
        int minLen = Math.min(beforeLen, afterLen);

        /**
         * 同时遍历前后2个单词的相同位置
         * 找到前后两个单词第1个不同字母即break的原因
         * - 因为字符串s字典顺序小于字符串t，只需要在第一个不同字母处，如果s中的字母在这门外星语言的字母顺序中位于t中字母之前
         * - 也就是2个字符串的顺序是通过相同位置第1个不同字符的字典顺序决定的，不需要看后续字符
         */
        int idx = 0;
        while (idx < minLen) {
            char beforeC = before.charAt(idx);
            char afterC = after.charAt(idx);
            // 字母不等即为邻接点（相邻单词的相同位置的不同字母按字典序递增）
            if (beforeC != afterC) {
                this.adjTable.get(beforeC).add(afterC);
                break;
            }
            idx++;
        }

        // 前缀单词，after是before的前缀单词，不存在合法字母顺序
        if (idx == minLen && afterLen < beforeLen) valid = false;
    }

    /**
     * 深度优先
     * 深度优先搜索回溯时将结点入结果栈
     */
    private void dfs(char u) {
        // 标记当前结点为搜索中状态
        this.stateMap.put(u, 1);
        // 搜索未搜索状态相邻节点
        // 发现有环，立即停止停止搜索
        for (char v : this.adjTable.get(u)) {
            if (!this.stateMap.containsKey(v)) {
                dfs(v);
                if (!this.valid) return;
            }
            // 相邻结点是搜索中状态找到了环
            if (1 == this.stateMap.get(v)) {
                this.valid = false;
                return;
            }
        }

        // 结点标记为已完成状态
        this.stateMap.put(u, 2);
        // 回溯时将结点入结果栈
        this.rs[--this.top] = u;
    }

    // 每个字符的入度，不包含则入度为0
    Map<Character, Integer> inDegreeMap = new HashMap<>();

    /**
     * 拓扑图（有向图）+广度优先遍历寻找可行拓扑排序序列
     * - 将入度为0的结点入队列，将相邻结点的入度-1，再将入度为0的结点入队
     * - 遍历完所有结点，队列中就是可选的拓扑排序序列
     */
    public String alienOrder2(String[] words) {
        // 构造每个字母的邻接表
        for (String word : words) {
            for (char c : word.toCharArray()) {
                this.adjTable.putIfAbsent(c, new ArrayList<>());
            }
        }

        // 同时处理前后2个单词构造完整邻接表
        for (int i = 1; i < words.length; i++) {
            buildAdjTable2(words[i - 1], words[i]);
        }

        if (!this.valid) return "";

        // 将所有入度为 0 的节点放入队列中
        Queue<Character> que = new ArrayDeque<>();
        for (char c : this.adjTable.keySet()) {
            // 不包含则入度为0
            if (!this.inDegreeMap.containsKey(c))
                que.offer(c);
        }

        // 开始广度优先遍历
        StringBuilder rsb = new StringBuilder();
        while (!que.isEmpty()) {
            char u = que.poll();
            rsb.append(u);
            for (char v : this.adjTable.get(u)) {
                this.inDegreeMap.put(v, this.inDegreeMap.get(v) - 1);
                if (0 == this.inDegreeMap.get(v))
                    que.offer(v);
            }
        }

        // 遍历完所有结点
        if (this.adjTable.size() == rsb.length())
            return rsb.toString();

        return "";
    }

    /**
     * 构造完整邻接表
     *
     * @param before 前一个单词
     * @param after  后一个单词
     */
    private void buildAdjTable2(String before, String after) {
        // 取前后2个单词的最小长度
        int beforeLen = before.length();
        int afterLen = after.length();
        int minLen = Math.min(beforeLen, afterLen);

        /**
         * 同时遍历前后2个单词的相同位置
         * 找到前后两个单词第1个不同字母即break的原因
         * - 因为字符串s字典顺序小于字符串t，只需要在第一个不同字母处，如果s中的字母在这门外星语言的字母顺序中位于t中字母之前
         * - 也就是2个字符串的顺序是通过相同位置第1个不同字符的字典顺序决定的，不需要看后续字符
         */
        int idx = 0;
        while (idx < minLen) {
            char beforeC = before.charAt(idx);
            char afterC = after.charAt(idx);
            // 字母不等即为邻接点（相邻单词的相同位置的不同字母按字典序递增）
            if (beforeC != afterC) {
                this.adjTable.get(beforeC).add(afterC);
                this.inDegreeMap.put(afterC, this.inDegreeMap.getOrDefault(afterC, 0) + 1);
                break;
            }
            idx++;
        }

        // 前缀单词，after是before的前缀单词，不存在合法字母顺序
        if (idx == minLen && afterLen < beforeLen) valid = false;
    }
}
