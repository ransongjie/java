package com.xcrj.interview150.hash;

import java.util.*;

/**
 * https://leetcode.cn/problems/group-anagrams
 * 字母异位词分组
 */
public class Solution23 {
    /**
     * Map<String,List<String>>
     * 异位词排序 做key
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strCnt = new HashMap<>();
        char[] cs;
        String key;
        List<String> ls;
        for (String str :
                strs) {
            //排序
            cs = str.toCharArray();
            Arrays.sort(cs);
            //统计
            key = new String(cs);
            ls = strCnt.getOrDefault(key, new ArrayList<>());
            ls.add(str);
            strCnt.put(key, ls);
        }
        //
        return new ArrayList<List<String>>(strCnt.values());
    }

    /**
     * 异位词统计 做key
     * a1b3
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> strCnt = new HashMap<>();
        char[] cnts;
        char[] cs;
        StringBuilder sb;
        String key;
        List<String> ls;
        for (String str :
                strs) {
            //统计字母
            cnts = new char[26];
            cs = str.toCharArray();
            for (char c :
                    cs) {
                cnts[c - 'a']++;
            }
            //构建key
            sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('a' + i).append(cnts[i]);
            }
            //统计
            key = sb.toString();
            ls = strCnt.getOrDefault(key, new ArrayList<>());
            ls.add(str);
            strCnt.put(key, ls);
        }

        return new ArrayList<List<String>>(strCnt.values());
    }
}
