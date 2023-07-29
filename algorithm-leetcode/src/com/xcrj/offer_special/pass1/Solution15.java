package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串中的所有变位词
 * 给定两个字符串s和p，找到s中所有 p 的变位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 注意，只涉及小写字母
 * <p>
 * 变位词 指字母相同，但排列不同的字符串。
 */
public class Solution15 {

    /**
     * 数组散列表 散列函数 c-'a'
     * 滑动窗口 窗口固定为p.length
     *
     * @param s 长字符串
     * @param p 短字符串
     */
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> list = new ArrayList<>(3);
        String s1 = p;
        String s2 = s;

        int m = s1.length();
        int n = s2.length();
        // s1比s2更长
        if (m > n) {
            return list;
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
            list.add(0);
        }
        // 固定滑动窗口的长度为m，因为s1的长度为m
        for (int i = m; i < n; i++) {
            // 窗口往右滑动，窗口右侧进入一个字符
            ++cnts2[s2.charAt(i) - 'a'];
            // 窗口往右滑动，窗口左侧出去一个字符
            --cnts2[s2.charAt(i - m) - 'a'];
            if (Arrays.equals(cnts1, cnts2)) {
                list.add(i - m + 1);
            }
        }

        return list;
    }

    /**
     * 两个数组散列表cnts1[]和cnts2[]变为一个数组散列表cnts[]
     * Arrays.equals(cnts1, cnts2)变为diff=0
     * <p>
     * cnts[x]!=0则diff+1，cnts[x]=0则diff-1
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>(3);
        String s1 = p;
        String s2 = s;

        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return list;
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
        if (0 == diff) list.add(0);
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
//            if (x == y) {
//                continue;
//            }
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
                list.add(i - m + 1);
            }
        }
        return list;
    }

    /**
     * 双指针
     * 开始left和right都等于0
     * 使用p字符串将cnts[]全部赋值为负数
     * right移动+1：right每右移1个字符cnts[right字符]+1
     * left移动-1：若cnts[right字符]>0, left左移1个字符，cnts[left字符]-1, 直到cnts[left字符]=0
     * <p>
     * diff=0变为 right移动+1&&left移动-1后cnts[]元素和为0 且 left和right指针移动长度为m
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> list = new ArrayList<>(3);

        String s1 = p;
        String s2 = s;
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return list;
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
                // 左移-1
                --cnts[s2.charAt(left) - 'a'];
                left++;
            }
            // s2中有没有长度为m的子数组
            // !!! 当right - left + 1 长度等于 m时，cnts[]元素之和为0
            if (right - left + 1 == m) {
                list.add(left);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        System.out.println(solution15.findAnagrams2("abab", "ab"));
    }
}
