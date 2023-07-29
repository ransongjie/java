package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 086. 分割回文子字符串
 * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
 */
public class Solution86 {

    /**
     * 回溯法+动态规划
     * 回溯法：深度优先遍历解空间树（结点扩展规则+剪枝函数）
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     */
    public String[][] partition1(String s) {
        this.len = s.length();
        this.f = new boolean[s.length()][s.length()];
        // 初始化动态规划数组为全true
        for (boolean[] bs : this.f) {
            Arrays.fill(bs, true);
        }

        // 预处理回文串f[i][j]是否为回文串
        // i往左
        for (int i = len - 1; i >= 0; i--) {
            // j往右
            for (int j = i + 1; j < len; j++) {
                // f[i][j]表示s[i到j]是否为回文串
                // 当前字符是否相等&&前一个子串是否为回文串
                // 可简化为 f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
                if ((s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1]) f[i][j] = true;
                else f[i][j] = false;
            }
        }

        // 回溯法处理以i为开始的回文串
        dfs(s, 0);

        // 处理结果
        String[][] rs = new String[this.lList.size()][];
        for (int i = 0; i < this.lList.size(); i++) {
            rs[i] = new String[this.lList.get(i).size()];
            for (int j = 0; j < this.lList.get(i).size(); j++) {
                rs[i][j] = this.lList.get(i).get(j);
            }
        }

        return rs;
    }

    // 动态规划数组，f[i][j]表示s[i到j]是否为回文串
    boolean[][] f;
    // 入参字符串长度
    int len;
    List<List<String>> lList = new ArrayList<>();
    List<String> list = new ArrayList<>();

    /**
     * 处理0到1,0到2,...,0到n-1 的回文串
     * 处理1到2,1到3,...,1到n-1 的回文串
     * 处理2到3,2到4,...,2到n-1 的回文串
     * 处理j到j+1,j到j+2,...,j+1到n-1 的回文串
     * ...
     * 处理n-2到n-1 的回文串
     * 处理n-1 的回文串
     * i=n存储处理结果
     *
     * @param s 字符串s
     * @param i 从i开始到n-1的回文串
     */
    private void dfs(String s, int i) {
        // i=n存储处理结果
        if (i == this.len) {
            this.lList.add(new ArrayList(this.list));
            return;
        }

        // 处理j到j+1,j到j+2,...,j+1到n-1 的回文串
        for (int j = i; j < this.len; j++) {
            // s[i到j]是否为回文串
            if (f[i][j]) {
                // 是回文串则添加到结果中
                // j+1前包后不包
                this.list.add(s.substring(i, j + 1));
                // 处理j+1到n-1的回文串
                dfs(s, j + 1);
                // 回溯，删除已经添加的回文串
                this.list.remove(this.list.size() - 1);
            }
        }
    }

    /**
     * 回溯法+记忆搜索
     * 使用记忆化搜索替代动态规划数组对s[i到j]是否为回文串的判断
     */
    public String[][] partition2(String s) {
        this.len = s.length();
        this.fis = new int[s.length()][s.length()];

        // 回溯法
        backTrack(s, 0);

        // 处理结果
        String[][] rs = new String[this.lList.size()][];
        for (int i = 0; i < this.lList.size(); i++) {
            rs[i] = new String[this.lList.get(i).size()];
            for (int j = 0; j < this.lList.get(i).size(); j++) {
                rs[i][j] = this.lList.get(i).get(j);
            }
        }

        return rs;
    }

    int[][] fis;

    private void backTrack(String s, int i) {
        // i=n存储处理结果
        if (i == this.len) {
            this.lList.add(new ArrayList(this.list));
            return;
        }

        // 处理j到j+1,j到j+2,...,j+1到n-1 的回文串
        for (int j = i; j < this.len; j++) {
            // s[i到j]是否为回文串
            if (1 == searchPalindrome(s, i, j)) {
                // 是回文串则添加到结果中
                // j+1前包后不包
                this.list.add(s.substring(i, j + 1));
                // 处理j+1到n-1的回文串
                backTrack(s, j + 1);
                // 回溯，删除已经添加的回文串
                this.list.remove(this.list.size() - 1);
            }
        }
    }

    /**
     * 记忆搜索回文串
     * 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
     */
    private int searchPalindrome(String s, int i, int j) {
        // 已经搜索过，不再进行搜索
        if (0 != this.fis[i][j]) return this.fis[i][j];

        // 没有搜索过
        // 下面的递归调用可能造成i>j，i>j表示之前的字符是否相等，之前的字符已经判断过了是回文串所以是1
        if (i >= j) {
            this.fis[i][j] = 1;
            return this.fis[i][j];
        }
        // s[i到j]是否为回文串，ij指向字符相等 和 f[i+1][j-1]是否为回文串
        if (s.charAt(i) == s.charAt(j)) {
            // 此处递归可能造成i>j
            this.fis[i][j] = searchPalindrome(s, i + 1, j - 1);
            return this.fis[i][j];
        }
        // -1 表示不是回文串
        this.fis[i][j] = -1;
        return this.fis[i][j];
    }
}
