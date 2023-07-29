package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 033. 变位词组
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * !!! 使用map统计时确保key的唯一性
 */
public class Solution33 {

    /**
     * map<String,List<String>>, key为排序后的变位词
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        // 统计
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 获取排序后的字符串
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String strSort = new String(chars);
            // map中原来存储有list<变位词>
            List<String> list = map.getOrDefault(strSort, new ArrayList<>());
            // 加入新的变位刺
            list.add(str);
            // 更新list
            map.put(strSort, list);
        }
        // !!! ArrayList(多个list)构造函数
        return new ArrayList<List<String>>(map.values());
    }

    /**
     *  map<String,List<String>>, key为 字母+字母出现次数拼接成的字符串
     *
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        // 统计
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] chars=str.toCharArray();
            // 统计每个字母出现的次数
            int[] cnts=new int[26];
            for(char c:chars){
                cnts[c-'a']++;
            }

            // 构造map的key
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                if(cnts[i]!=0){
                    sb.append('a'+i).append(cnts[i]);
                }
            }

            // map中原来存储有list, map中有没有value，更新value
            List<String> list = map.getOrDefault(sb.toString(),new ArrayList<>());
            list.add(str);
            map.put(sb.toString(),list);
        }

        return new ArrayList<>(map.values());
    }
}
