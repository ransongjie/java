package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 字符串中的变位词
 * s1字符串的某个排列是s2的子串
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 */
public class Solution14 {

    /**
     * 超过时间限制
     * <p>
     * 增量蛮力法获取排列
     * 排列kmp算法在s2中
     */
    public boolean checkInclusion1(String s1, String s2) {
        Set<String> result = fullPermutation(s1);
        for (String str : result) {
            if (-1 != kmp(s2, str)) return true;
        }
        return false;
    }

    /**
     * 增量蛮力法：很耗时
     * i=1 set<List1>:   1
     * i=2 set<List1*2>: 21 | 12
     * i=3 set<List2*3>: 321 231 213 | 312 132 123
     *
     * @param s1
     * @return s1数组的全排列
     */
    private Set<String> fullPermutation(String s1) {
        Set<String> result = new HashSet<>(6);
        Set<List<Integer>> resultIdx = new HashSet<>(6);
        // 元素个数
        int n = s1.length();
        // 初始化 加入1
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        resultIdx.add(list1);
        // 循环加入2 3 ... n等
        for (int i = 2; i <= n; i++) {
            Set<List<Integer>> tempResultIdx = new HashSet<>(6);
            // 操作resultIdx中的每个list
            for (List<Integer> list : resultIdx) {
                for (int j = 0; j < i; j++) {
                    List<Integer> tempList = new ArrayList<>(list.size());
                    // 拷贝list到tempList
                    tempList.addAll(list);
                    // 给每个排列list的不同下标下增加i
                    tempList.add(j, i);
                    tempResultIdx.add(tempList);
                }
            }
            // 清空原resultIdx，tempResultIdx copy resultIdx
            resultIdx.clear();
            for (List<Integer> temp : tempResultIdx) {
                List<Integer> one = new ArrayList<>(temp.size());
                one.addAll(temp);
                resultIdx.add(one);
            }
        }
        // 根据索引获取元素
        for (List<Integer> list : resultIdx) {
            char[] chars = new char[s1.length()];
            for (int i = 0; i < list.size(); i++) {
                int idx = list.get(i);
                chars[i] = s1.charAt(idx - 1);
            }
            result.add(new String(chars));
        }

        return result;
    }

    /**
     * 部分匹配表：！！！前缀字符和后缀字符的匹配数量
     * 1个字符作字符串的部分匹配值，2个字符作字符串的部分匹配值，...，n字符作字符串的部分匹配值 构成部分匹配表
     * i从第2个字符到第n个字符，j从第1个字符到第n-1个字符
     * 1个字符作字符串部分匹配值为0
     *
     * @param pattern 模式串 待匹配串 s1的某个排列
     * @return 部分匹配表
     */
    private int[] partialMatchingTable(String pattern) {
        int[] next = new int[pattern.length()];
        // 1个字符作字符串部分匹配值为0
        next[0] = 0;
        // i从第2个字符到第n个字符，j从第1个字符到第n-1个字符
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            // 前缀字符和后缀字符不匹配，进行部分匹配，回到上1次部分匹配的位置
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 前缀字符和后缀字符匹配，i和j后移比较一下个字符
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 0~i的子串的部分匹配值=j
            next[i] = j;
        }

        return next;
    }

    /**
     * kmp算法 模式匹配算法
     * 不能全部匹配就选择部分匹配铱减少下标回退的距离
     * 回退多少由部分匹配表决定
     * 部分匹配表由 字符串的 最长前缀字符串=最长后缀字符串中 的字符数量
     *
     * @param match   匹配串 s2
     * @param pattern 模式串 s1的某个排列
     * @ruturn 模式串在匹配串中首次出现的位置
     */
    public int kmp(String match, String pattern) {
        int[] next = partialMatchingTable(pattern);
        // i匹配串下标，j模式串下标
        for (int i = 0, j = 0; i < match.length(); i++) {
            // i和j所指向的字符不匹配，进行部分匹配，回到上1次部分匹配的位置
            while (j > 0 && match.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // i和j所指向的字符匹配，i和j都后移 比较下1个字符
            if (match.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 全部匹配，返回模式串在匹配串中的起始位置
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    /**
     * 滑动窗口
     * 题目不关心s1中的字符在s2中的顺序，值关心s2中子序列中字符和s1中字符出现次数是否相等
     * 转化为判断 统计字符出现次数的散列表是否相等
     * 数组散列表，散列函数 idx=c-26
     *
     * @param s1 短字符串
     * @param s2 长字符串
     */
    public boolean checkInclusion2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // s1比s2更长
        if (m > n) {
            return false;
        }

        // 散列表, 26个字母
        int[] cnts1 = new int[26];
        int[] cnts2 = new int[26];
        // 统计0~s1.length()-1长度s1中字符出现的次数，s2中字符出现的次数
        for (int i = 0; i < s1.length(); i++) {
            ++cnts1[s1.charAt(i) - 'a'];
            ++cnts2[s2.charAt(i) - 'a'];
        }
        // s1的某个顺序的序列是s2的前缀
        // 比较1次数组散列表
        if (Arrays.equals(cnts1, cnts2)) {
            return true;
        }
        // 固定滑动窗口的长度为m，因为s1的长度为m
        for (int i = m; i < n; i++) {
            // 窗口往右滑动，窗口右侧进入一个字符
            ++cnts2[s2.charAt(i) - 'a'];
            // 窗口往右滑动，窗口左侧出去一个字符
            --cnts2[s2.charAt(i - m) - 'a'];
            if (Arrays.equals(cnts1, cnts2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 将原来的cnts1和cnts2合并为1个cnts，并使用diff统计cnts[i]!=0的元素个数
     */
    public boolean checkInclusion3(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        // 散列表, 26个字母
        int[] cnts = new int[26];
        // 统计0~s1.length()-1长度s1中字符出现的次数，s2中字符出现的次数
        for (int i = 0; i < s1.length(); i++) {
            // 窗口中进入新元素 s1减法
            --cnts[s1.charAt(i) - 'a'];
            // 窗口中进入新元素 s2加法
            ++cnts[s2.charAt(i) - 'a'];
        }
        // 记录差异
        int diff = 0;
        for (int c : cnts) {
            if (c != 0) {
                diff++;
            }
        }
        // s1的某个顺序的序列是s2的前缀
        if (0 == diff) return true;
        // 滑动窗口
        /**
         * diff记录差异：
         * - diff+1：cnts[x]!=0 cnts[y]!=0
         * - diff-1：cnts[x]=0 cnts[y]=0
         * 新进出的字符相同：
         * - ++cnts[x]再--cnts[x]，没有变化直接看下一个字符
         * 新进出的字符相同不同：
         * - 进入x，一定++cnts[x]。++cnts[x]之前，若cnts[x]=0，则diff+1；++cnts[x]之后，若cnts[x]=0，则diff-1.
         * - 退出y，一定--cnts[y]。--cnts[y]之前，若cnts[y]=0，则diff+1; --cnts[y]之后，若cnts[y]=0, 则diff-1.
         */
        for (int i = m; i < n; ++i) {
            // 进入的字符散列值
            int x = s2.charAt(i) - 'a';
            // 出去的字符散列值
            int y = s2.charAt(i - m) - 'a';
            // 新进出的字符相同：++cnts[x]再--cnts[x]，没有变化直接看下一个字符
            if (x == y) {
                continue;
            }
            // 修改cnts[]之前，若cnts1[x]=cnts2[x]，则将增加差异 diff+1
            if (cnts[x] == 0) {
                ++diff;
            }
            // 窗口中进入新元素 s2加法
            ++cnts[x];
            // 修改cnts[]之后，若cnts1[x]=cnts2[x]，则将减少差异 diff-1
            if (cnts[x] == 0) {
                --diff;
            }
            if (cnts[y] == 0) {
                ++diff;
            }
            --cnts[y];
            if (cnts[y] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    /***
     * 在cnts[]元素值不为正数的情况下，s2中有没有长度为m的连续子数组
     * 连续子数组，使用双指针
     */
    public boolean checkInclusion4(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        // 散列表, 26个字母
        int[] cnts = new int[26];
        // 统计0~s1.length()-1长度s1中字符出现的次数
        // ！！！cnts[]元素之和为-m
        for (int i = 0; i < s1.length(); i++) {
            // 窗口中进入新元素 s1减法
            --cnts[s1.charAt(i) - 'a'];
        }
        // 在cnts[]元素值不为正数的情况下，s2中有没有长度为m的子数组
        // ！！！right-left+1，长度每增加1，cnts[]的元素之和就增加1
        for (int left = 0, right = 0; right < n; right++) {
            // 右移++
            ++cnts[s2.charAt(right) - 'a'];
            // 保证cnts[]元素值不为正数，遇到正数左移left
            while (cnts[s2.charAt(right) - 'a'] > 0) {
                // 左移--
                --cnts[s2.charAt(left) - 'a'];
                left++;
            }
            // s2中有没有长度为m的子数组
            // !!! 当right - left + 1 长度等于 m时，cnts[]元素之和为0
            if (right - left + 1 == m) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution14 solution14 = new Solution14();
        System.out.println(solution14.checkInclusion3("pro", "properties"));
    }
}
