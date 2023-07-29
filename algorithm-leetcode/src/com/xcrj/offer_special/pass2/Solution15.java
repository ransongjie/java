package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词
 * - s1和s2两个字符串
 * - s1的变位词（每个排列）在s2中
 * - s1在s2中所有变位词的左索引
 */
public class Solution15 {
    /**
     * 方法与剑指 Offer II 014. 字符串中的变位词 一致
     * - 固定滑动窗口的移动范围
     * for(int i=m;i<n;i++){}
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        String s1=p;
        String s2=s;
        // 统计 s1在s2中所有变位词的左索引
        List<Integer> rList=new ArrayList<>();
        // s2的长度要更长
        int m=s1.length();
        int n=s2.length();
        if(n<m){
            return rList; 
        }
        // 构建固定滑动窗口
        int[] cnt1s=new int[26];
        int[] cnt2s=new int[26];
        for(int i=0;i<m;i++){
            ++cnt1s[s1.charAt(i)-'a'];
            ++cnt2s[s2.charAt(i)-'a'];
        }

        if(Arrays.equals(cnt1s, cnt2s)){
            rList.add(0);
        }

        // 移动固定滑动窗口
        for(int i=m;i<n;i++){
            // 右边进入1个，左边出去1个
            ++cnt2s[s2.charAt(i)-'a'];
            --cnt2s[s2.charAt(i-m)-'a'];
            if(Arrays.equals(cnt1s, cnt2s)){
                rList.add(i-m+1);
            }
        }
        return rList;
    }
}