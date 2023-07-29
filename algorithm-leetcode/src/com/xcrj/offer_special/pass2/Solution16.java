package com.xcrj.offer_special.pass2;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 016. 不含重复字符的最长连续子字符串
 * - 子字符串连续
 * - 子字符串中不含有重复字符
 */
public class Solution16{
    /**
     * 双指针夹逼+set去重
     * - i j开始都指向0
     * - 2个while循环夹击
     * while(j<len){
     *   while(i<=j&&条件){
     *     // i向右不满足条件
     *     i++;
     *   }
     *   // j向右满足条件
     *   j++;
     * }
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<>();
        int i=0;
        int j=0;
        int maxLen=0;
        while(j<s.length()){
            while(i<j&&set.contains(s.charAt(j))){
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