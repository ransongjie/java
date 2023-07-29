package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 032. 有效的变位词
 * 必须是变位词，s和t的字符顺序都一致时，是相同字符串，不是变位词
 * 参考 剑指 Offer II 014. 字符串中的变位词
 */
public class Solution32 {
    /**
     * 使用int cnts[26]统计每个字母出现的次数
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        if(s.equals(t)) return false;

        // 统计每个字母出现的次数
        int[] cnts=new int[26];
        for(char c:s.toCharArray()){
            cnts[c-'a']++;
        }

        for(char c:t.toCharArray()){
            cnts[c-'a']--;
        }

        for(int a:cnts){
            if(a!=0) return false;
        }

        return true;
    }

    /**
     * 优化isAnagram1
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        // 长度一致
        if(s.length()!=t.length()) return false;

        if(s.equals(t)) return false;

        int[] cnts=new int[26];
        for(char c:s.toCharArray()){
            cnts[c-'a']++;
        }

        for(char c:t.toCharArray()){
            cnts[c-'a']--;
            // 字符数量
            if(cnts[c-'a']<0) return false;
        }

        return true;
    }

    /**
     * 优化isAnagram2
     * 可以处理unicode字符
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram3(String s, String t) {
        if(s.length()!=t.length()) return false;

        if(s.equals(t)) return false;

        Map<Character,Integer> map=new HashMap<>();
        for(char c:s.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }

        for(char c:t.toCharArray()){
            map.put(c, map.getOrDefault(c,0)-1);
            if(map.get(c)<0) return false;
        }

        return true;
    }

}
