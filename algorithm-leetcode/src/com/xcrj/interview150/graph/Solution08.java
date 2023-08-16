package com.xcrj.interview150.graph;

import java.util.*;

/**
 * https://leetcode.cn/problems/minimum-genetic-mutation
 * 1条基因序列(染色体)由8个字符组成，每个字符可选'A', 'C', 'G', 'T'
 * 1次基因变化，1个基因变化，1个字符变化
 * 基因库bank，有效的基因序列
 * <p>
 * start-最少变化次数&&变化是否可行(bank)->end
 * 最少变化次数 且 变化被限制到了bank中
 */
public class Solution08 {
    int ans = Integer.MAX_VALUE;

    /**
     * 回溯
     *
     * @param startGene
     * @param endGene
     * @param bank
     * @return
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        //变化次数为0
        backtrack(startGene, endGene, bank, new boolean[bank.length], 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * start>.变1基因.>bank[i]>.变1基因.>bank[j]>.变1基因.>end
     *
     * @param start
     * @param end
     * @param bank
     * @param used
     * @param t     变化次数
     */
    private void backtrack(String start, String end, String[] bank, boolean[] used, int t) {
        // t变化方式的变化次数 > ans变化方式的变化次数 直接返回 减枝
        if (t > ans) return;
        // 找到1种新的可行变化
        if (start.equals(end)) {
            ans = Math.min(ans, t);
        } else {
            //遍历基因库
            for (int i = 0, diff = 0; i < bank.length; i++, diff = 0) {
                // bank[i]染色体已经使用过
                if (used[i]) continue;
                // 获取start染色体 与 bank[i]染色体 的基因差值
                for (int j = 0; j < start.length(); j++) {
                    diff += start.charAt(j) != bank[i].charAt(j) ? 1 : 0;
                }
                //差值为1，则从start>bank[i]
                if (diff == 1) {
                    used[i] = true;
                    backtrack(bank[i], end, bank, used, t + 1);
                    used[i] = false;
                }
            }
        }
    }


    static char[] items = new char[]{'A', 'C', 'G', 'T'};

    /**
     * BFS 广度优先
     *
     * @param startGene
     * @param endGene
     * @param bank
     * @return
     */
    public int minMutation2(String startGene, String endGene, String[] bank) {
        //bank转储
        Set<String> ableStr = new HashSet<>();
        for (String b : bank) ableStr.add(b);
        //map<到达string,所需步数>。相当于 visited
        Map<String, Integer> strStep = new HashMap<>();
        strStep.put(startGene, 0);
        //广度优先所需队列
        Queue<String> que = new LinkedList<>();
        que.offer(startGene);
        //广度优先遍历
        while (!que.isEmpty()) {
            int size = que.size();//队列str数量
            //广度优先遍历，把一层遍历完，一层的step都相等
            //操作que中的所有str
            while (size-- > 0) {
                String str = que.poll();
                char[] cs = str.toCharArray();
                int step = strStep.get(str);
                //str 8个字符的每个字符 尝试变化4种字符
                for (int i = 0; i < 8; i++) {
                    for (char c : items) {
                        //str[i]=c
                        if (cs[i] == c) continue;
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String str1 = String.valueOf(clone);
                        //基因库中没有str1，str>不可>str1
                        if (!ableStr.contains(str1)) continue;
                        //str>之前已经到过>str1。相当于 visited
                        if (strStep.containsKey(str1)) continue;
                        if (str1.equals(endGene)) return step + 1;
                        strStep.put(str1, step + 1);
                        que.offer(str1);
                    }
                }
            }
        }

        return -1;
    }
}
