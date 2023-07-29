package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 066. 单词之和
 * 实现一个 MapSum类，支持两个方法，insert和sum：
 * - MapSum() 初始化 MapSum 对象
 * - void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * - int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 * <p>
 * 分析：相同前缀单词值之和
 */
public class Solution66 {
    /**
     * 蛮力法，key.startsWith(prefix)
     */
    class MapSum1 {
        Map<String, Integer> map;

        public MapSum1() {
            this.map = new HashMap<>();
        }

        public void insert(String key, int val) {
            this.map.put(key, val);
        }

        public int sum(String prefix) {
            int r = 0;
            for (String key : this.map.keySet()) {
                if (key.startsWith(prefix)) {
                    r += this.map.get(key);
                }
            }
            return r;
        }
    }

    /**
     * prefixMap<prefixKey,sum>
     * - 相同key，相同前缀，diff=val-oldVal
     * - 不同key，相同前缀，diff=val
     * - prefixMap.put(prefixKey,prefixKeyValue+diff)
     */
    class MapSum2 {
        // 存储String key
        Map<String, Integer> map;
        // 相同前缀单词值之和map<prefixKey,sum>
        Map<String, Integer> prefixMap;

        public MapSum2() {
            this.map = new HashMap<>();
            this.prefixMap = new HashMap<>();
        }

        public void insert(String key, int val) {
            // 相同key，保留后一个单词，求差值；不同key diff=val
            int diff = val - this.map.getOrDefault(key, 0);
            // 操作key原来的val，再put新的val
            this.map.put(key, val);
            // 构建前缀map prefixMap
            // =key.length()：subString()前包后不包
            for (int i = 1; i <= key.length(); i++) {
                // 相同key，更新差值
                String prefixKey = key.substring(0, i);
                // 相同key的每个前缀都需要更新差值；不同key diff=val
                this.prefixMap.put(prefixKey, this.prefixMap.getOrDefault(prefixKey, 0) + diff);
            }
        }

        public int sum(String prefix) {
            return this.prefixMap.getOrDefault(prefix, 0);
        }
    }

    /**
     * 前缀树
     * - 相同key，相同前缀，diff=val-oldVal
     * - 不同key，相同前缀，diff=val
     * - node.sum+=diff
     */
    class MapSum3 {
        Trie root;
        Map<String, Integer> map;

        public MapSum3() {
            this.root = new Trie();
            this.map = new HashMap<>();
        }

        public void insert(String key, int val) {
            int diff = val - this.map.getOrDefault(key, 0);
            this.map.put(key, val);
            Trie node = this.root;
            for (char c : key.toCharArray()) {
                int idx = c - 'a';
                if (null == node.children[idx]) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
                node.sum += diff;
            }
        }

        public int sum(String prefix) {
            Trie node = this.root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (null == node.children[idx]) {
                    return 0;
                }
                node = node.children[idx];
            }

            return node.sum;
        }
    }

    class Trie {
        Trie[] children;
        int sum;

        public Trie() {
            this.children = new Trie[26];
            this.sum = 0;
        }
    }
}
