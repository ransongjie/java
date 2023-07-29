package com.xcrj.offer_special.pass2;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 066. 单词之和
 * 实现一个 MapSum类，支持两个方法，insert和sum：
 * - MapSum() 初始化 MapSum 对象
 * - void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * - int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 * <p>
 * 分析：相同前缀单词 值之和
 */
public class Solution66 {
    /**
     * 蛮力法
     */
    class MapSum1 {
        Map<String,Integer> map;

        public MapSum1() {
            map=new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key,val);
        }

        public int sum(String prefix) {
            int r=0;
            for(Map.Entry<String,Integer> entry:map.entrySet()){
                if(entry.getKey().startsWith(prefix)){
                    r+=entry.getValue();
                }
            }

            return r;
        }
    }

    /**
     * Map<prefix,sum>
     */
    class MapSum2 {
        Map<String,Integer> keyValMap;
        Map<String,Integer> prefixSumMap;

        public MapSum2() {
            keyValMap=new HashMap<>();
            prefixSumMap=new HashMap<>();
        }

        public void insert(String key, int val) {
            // xcrj 记录差值
            int diff=val-keyValMap.getOrDefault(key,0);
            // 新值覆盖旧值
            keyValMap.put(key, val);

            for(int i=1;i<=key.length();i++){
                String prefix=key.substring(0,i);
                // xcrj oldSum+diff=newSum
                prefixSumMap.put(prefix, prefixSumMap.getOrDefault(prefix, 0)+diff);
            }
        }

        public int sum(String prefix) {
            return prefixSumMap.getOrDefault(prefix, 0);
        }
    }

    /**
     * 前缀树 前缀的sum记录在前缀树结点中
     */
    class MapSum3 {
        Trie root;
        Map<String,Integer> keyValMap;
        public MapSum3() {
            root=new Trie();
            keyValMap=new HashMap<>();
        }

        public void insert(String key, int val) {
            // 记录差值
            int diff=val-keyValMap.getOrDefault(key, 0);
            // 直接放入map
            keyValMap.put(key, val);

            Trie node=root;
            for(char c:key.toCharArray()){
                int idx=c-'a';
                if(node.children[idx]==null){
                    node.children[idx]=new Trie();
                }
                node=node.children[idx];
                // xcrj oldSum+diff=newSum
                node.sum+=diff;
            }
        }

        /**
         * 前缀树叶子结点的sum
         * @param prefix
         * @return
         */
        public int sum(String prefix) {
            Trie node=root;
            for(char c:prefix.toCharArray()){
                int idx=c-'a';
                if(node.children[idx]==null) return 0;
                node=node.children[idx];
            }
            return node.sum;
        }
    }

    class Trie{
        Trie[]children;
        int sum;
        Trie(){
            children=new Trie[26];
            sum=0;
        }
    }
}
