package com.xcrj.offer_special.pass3;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 017. s含有t所有字符的连续最短字符串
 * - 变位词+字符数量可以更多
 */
public class Solution17 {
    // Map<字符,次数>
    Map<Character, Integer> tmap = new HashMap<>();
    Map<Character, Integer> smap = new HashMap<>();

    public String minWindow(String s, String t) {
        // 特殊情况
        if (s.length() < t.length()) {
            return "";
        }
        // 统计t中字符
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }

        // 再尝试移除字符以保证s子串和t字符数相等
        int i = 0;
        int j = -1;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        while (j < s.length()) {
            // 先保证s子串包含t中所有字符（包括重复字符）
            j++;
            if (j < s.length() && tmap.containsKey(s.charAt(j))) {
                smap.put(s.charAt(j), smap.getOrDefault(s.charAt(j), 0) + 1);
            }
            // 再尝试移除字符以保证s子串和t字符数相等
            while(i<=j&&ge()){
                if(j-i+1<minLen){
                    minLen=j-i+1;
                    left=i;
                }
                if(smap.containsKey(s.charAt(i))){
                    smap.put(s.charAt(i), smap.get(s.charAt(i))-1);
                }
                i++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(left, left+minLen);
    }

    public boolean ge(){
        // 只要s子串有1个字符的数量更小 return false
        for(Map.Entry<Character,Integer> entry:tmap.entrySet()){
            if(smap.getOrDefault(entry.getKey(), 0)<entry.getValue()){
                return false;
            }
        }
        return true;
    }

}
