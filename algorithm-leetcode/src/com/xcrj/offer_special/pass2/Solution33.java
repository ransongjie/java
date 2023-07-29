package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 033. 变位词组
 * 相同变位词放到一起
 */
public class Solution33 {

    /**
     * 排序后的变位词是相同字符串
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char[] chars=str.toCharArray();
            //
            Arrays.sort(chars);
            String cstr=new String(chars);
            List<String> list=map.getOrDefault(cstr, new ArrayList<>());
            list.add(str);
            // 
            map.put(cstr, list);
        }

        // xcrj
        return new ArrayList<>(map.values());
    }

    /**
     * 变位词的key要一致
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            int[] cnts=new int[26];
            for(char c:str.toCharArray()){
                cnts[c-'a']++;
            }

            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                //
                sb.append('a'+i).append(cnts[i]);
            }

            List<String> list=map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(str);
            map.put(sb.toString(),list);
        }

        return new ArrayList<>(map.values());
    }
}
