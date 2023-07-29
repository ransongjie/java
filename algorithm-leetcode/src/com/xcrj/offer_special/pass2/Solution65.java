package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 065. 最短的单词编码
 * 输入：words = ["time", "me", "bell"]
 * 输出：10
 * 解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
 * words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如括号内部分所示 "[time]#bell#"
 * words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如括号内部分所示 "ti[me]#bell#"
 * words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如括号内部分所示 "time#[bell]#"
 * 
 * 分析：
 * - 去掉后缀单词
 */
public class Solution65 {
    /**
     * 蛮力法+set
     * @param words
     * @return
     */
    public int minimumLengthEncoding1(String[] words) {
        Set<String> set=new HashSet<>(Arrays.asList(words));
        // 每个单词
        for(String word:words){
            // 1，从1开始必须是后缀子串
            for(int j=1;j<word.length();j++){
                // 的子串（后缀），是否是其它单词
                set.remove(word.substring(j));
            }
        }

        // 统计单词含有字母数量+# 单词
        int r=0;
        for(String str:set){
            r+=str.length()+1;
        }

        return r;
    }

    /**
     * xcrj 字典树中的字母都是唯一的
     * 使用后缀树保留非后缀单词：反序单词插入字典树，如果subWord是word的后缀，则从根结点到叶子结点的单词是word
     * 使用map记录叶子结点对应单词索引：可以到达叶子结点的单词的len+1(#符号)
     * added=false的结点就是后缀树的叶子结点
     */
    public int minimumLengthEncoding2(String[] words) {
        // xcrj map<结点，单词索引> 结点.added=false 表示该结点是叶子结点
        Map<Tire,Integer> map=new HashMap<>();

        // 后缀树，倒序建立字典树
        Tire root=new Tire();
        for (int i = 0; i < words.length; i++) {
            String word=words[i];
            Tire node=root;
            for(int j=word.length()-1;j>=0;j--){
                node=node.getNode(word.charAt(j));
            }
            map.put(node, i);
        }

        // 统计结点为叶子结点的单词的len+1
        int r=0;
        for(Map.Entry<Tire,Integer> entry:map.entrySet()){
            if(!entry.getKey().added){
                r+=words[entry.getValue()].length()+1;
            }
        }

        return r;
    }

    class Tire {
        Tire[] children;
        // 新加入的结点
        boolean added;

        Tire(){
            children=new Tire[26];
            added=false;
        }

        /**
         * 获取字母位置存储的结点
         * @param c
         * @return 
         */
        public Tire getNode(char c) {
            int idx=c-'a';
            // 不含有这个字母则创建新结点
            if(children[idx]==null){
               children[idx]=new Tire();
               added=true;
            }
            
            return children[idx];
        }
    }
}
