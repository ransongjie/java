package com.xcrj.offer_special.pass2;
/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * - Trie() 初始化前缀树对象。
 * - void insert(String word) 向前缀树中插入字符串 word 。
 * - boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * - boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Solution62 {
    class Trie {
        Trie[] children;
        boolean end;

        public Trie() {
            // xcrj 索引代表字母，值指向child
            children=new Trie[26];
            end=false;
        }

        public void insert(String word) {
            char[] cs=word.toCharArray();
            Trie node=this;
            for(char c:cs){
                int idx=c-'a';
                if(node.children[idx]==null){
                    node.children[idx]=new Trie();
                }
                node=node.children[idx];
            }
            node.end=true;
        }

        /**
         * word 字典树
         * abc adc
         * abcd abc
         * abc abcd
         * abc abc
         * @param word
         * @return
         */
        public boolean search(String word) {
            char[] cs=word.toCharArray();
            Trie node=this;
            int i=0;
            for(;i<cs.length;i++){
                int idx=cs[i]-'a';
                if(node.children[idx]==null){
                    return false;
                }
                
                node=node.children[idx];
            }
            
            if(i!=cs.length) return false;
            if(!node.end)return false;

            return true;
        }

        /**
         * word 字典树
         * abc adc
         * abcd abc
         * abc abcd 不用管这个条件
         * abc abc
         * 
         * @param prefix
         * @return
         */
        public boolean startsWith(String prefix) {
            char[] cs=prefix.toCharArray();
            Trie node=this;
            int i=0;
            for(;i<cs.length;i++){
                int idx=cs[i]-'a';
                if(node.children[idx]==null){
                    return false;
                }
                
                node=node.children[idx];
            }
            
            if(i!=cs.length) return false;

            return true;
        }
    }
}
