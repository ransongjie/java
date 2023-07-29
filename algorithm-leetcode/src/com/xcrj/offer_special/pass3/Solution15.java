package com.xcrj.offer_special.pass3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词的左索引
 * - p在s串内的变位词的做索引
 */
public class Solution15 {
    /**
     * 方法与剑指 Offer II 014. 字符串中的变位词 一致
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        String s1=p;
        String s2=s;
        List<Integer> list=new ArrayList<>();
        //
        int len1=s1.length();
        int len2=s2.length();
        // 特殊情况
        if(len1>len2)return list;
        // 初始化
        int[] cnt1=new int[26];
        int[] cnt2=new int[26];
        for(int i=0;i<len1;i++){
            cnt1[s1.charAt(i)-'a']++;
            cnt2[s2.charAt(i)-'a']++;
        }
        //
        if(Arrays.equals(cnt1, cnt2)) {
            list.add(0);
        }
        // 窗口滑动
        for(int i=len1;i<len2;i++){
            //i进入窗口
            cnt2[s2.charAt(i)-'a']++;
            //i-len1出窗口
            cnt2[s2.charAt(i-len1)-'a']--;
            //
            if(Arrays.equals(cnt1, cnt2)) {
                list.add(i-len1+1);
            }
        }
        
        return list;
    }
}
