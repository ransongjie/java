package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 014. 字符串中的变位词
 * - s1和s2两个字符串
 * - s1的变位词（每个排列）在s2中
 */
public class Solution14 {
    /**
     * 变位词，固定滑动窗口散列表统计
     * - int[] cnt1s=new int[26];
     * 
     * 固定长度的滑动窗口
     * - 窗口长度为s1的长度
     * - 只关心窗口内每种字符的出现的数量，不关心次序
     */
    public boolean checkInclusion(String s1, String s2) {
        int m=s1.length();
        int n=s2.length();
        // s2的长度要更长
        if(m>n){
            return false;
        }

        // 构建固定滑动窗口
        int[] cnt1s=new int[26];
        int[] cnt2s=new int[26];
        for(int i=0;i<m;i++){
            ++cnt1s[s1.charAt(i)-'a'];
            ++cnt2s[s2.charAt(i)-'a'];
        }

        // xcrj 数组元素值比较
        if(Arrays.equals(cnt1s, cnt2s)){
            return true;
        }

        // 移动固定滑动窗口
        for(int i=m;i<n;i++){
            // 固定滑动窗口右侧入1个，左侧出1个
            ++cnt2s[s2.charAt(i)-'a'];
            --cnt2s[s2.charAt(i-m)-'a'];
            if(Arrays.equals(cnt1s, cnt2s)){
                return true;
            }
        }
        return false;
    }
}