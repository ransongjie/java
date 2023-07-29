package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 087. 复原 IP
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 */
public class Solution87 {
    // ipv4总共有4段
    static final int SEGMENT_NUM = 4;
    List<String> rs = new ArrayList<>();
    // segments[第i段]=第i段对应的地址
    int[] segments = new int[SEGMENT_NUM];

    /**
     * 回溯法：深度优先遍历解空间树（结点扩展规则+剪枝函数）
     * - 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
     * - 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
     * - 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
     * - 一般情况，枚举每一种可能性并递归
     *
     * 回溯法+分治
     */
    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return this.rs;
    }

    /**
     *
     * @param s
     * @param segmentI     第i段
     * @param segmentStart 第i段的起始搜索地址
     */
    private void dfs(String s, int segmentI, int segmentStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (SEGMENT_NUM == segmentI && s.length() == segmentStart) {
            StringBuilder sb = new StringBuilder(SEGMENT_NUM);
            for (int seg : this.segments) {
                sb.append(seg).append('.');
            }
            // 删除最后1个多出来的.
            sb.deleteCharAt(sb.length() - 1);
            this.rs.add(sb.toString());
            return;
        }

        // 如果找到了 4 段 IP 地址但没有遍历完字符串，那么提前回溯
        if (SEGMENT_NUM == segmentI && s.length() != segmentStart) {
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (SEGMENT_NUM != segmentI && s.length() == segmentStart) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if ('0' == s.charAt(segmentStart)) {
            this.segments[segmentI] = 0;
            // 处理下一段，下一个开始位置
            dfs(s, segmentI + 1, segmentStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addrd = 0;
        for (int i = segmentStart; i < s.length(); i++) {
            // ！括号
            addrd = addrd * 10 + (s.charAt(i) - '0');
            // 0xFF=255
            if (0 < addrd && 0xFF >= addrd) {
                // 添加第i段的地址
                this.segments[segmentI] = addrd;
                // 处理下一段，下一个开始位置
                // ！i+1
                dfs(s, segmentI + 1, i + 1);
            }
            // 不在(0,255]之间，再继续也不会满足，退出循环
            else break;
        }
    }
}
