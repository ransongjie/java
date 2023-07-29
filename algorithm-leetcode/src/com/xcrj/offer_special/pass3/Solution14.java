package com.xcrj.offer_special.pass3;

import java.util.Arrays;

/**
 * 剑指 Offer II 014. s1是s2字符串的变位词
 * - s1的变位词（每个排列）在s2中
 * - s1长度大于s2
 */
public class Solution14 {
    /**
     * 排列，每种字符的数量相等，次序可以不同
     * 固定滑动窗口+散列表统计
     * - int[] cnt1s=new int[26];
     * - 窗口长度为s1的长度
     * 
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1=s1.length();
        int len2=s2.length();
        // 特殊情况
        if(len1>len2)return false;
        // 初始化
        int[] cnt1=new int[26];
        int[] cnt2=new int[26];
        for(int i=0;i<len1;i++){
            cnt1[s1.charAt(i)-'a']++;
            cnt2[s2.charAt(i)-'a']++;
        }
        //
        if(Arrays.equals(cnt1, cnt2)) return true;
        // 窗口滑动
        for(int i=len1;i<len2;i++){
            //i进入窗口
            cnt2[s2.charAt(i)-'a']++;
            //i-len1出窗口
            cnt2[s2.charAt(i-len1)-'a']--;
            //
            if(Arrays.equals(cnt1, cnt2)) return true;
        }
        
        return false;
    }
}
