package com.xcrj.offer_special.pass3;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 016. 不含重复字符的最长连续子字符串
 * - 子字符串连续
 * - 子字符串中不含有重复字符
 */
public class Solution16 {
    /**
     * 双while夹击+set去重，
     * 先往set中添加元素，
     * 再判断set中是否有重复元素，
     * 有从左往右依次丢弃
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen=0;
        Set<Character> set=new HashSet<>();
        int i=0;
        int j=0;
        while(j<s.length()){
            // 第j个元素是否已经添加过
            while(i<j&&set.contains(s.charAt(j))){
                // 一直位移到不重复为止
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            j++;
            maxLen=Math.max(maxLen, j-i);
        }
        return maxLen;
    }
}
