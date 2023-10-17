package com.xcrj.basic.pass4.prefix_tree;

/**
 * 前缀树
 */
public class PrefixTree {
    //统一根节点
    static Node root = new Node();

    /**
     * 增加字符串
     *
     * @param str
     */
    public static void add(String str) {
        if (str == null) return;
        Node p = root;
        p.pass++;
        for (char c : str.toCharArray()) {
            if (p.nodes[c - 'a'] == null) {
                p.nodes[c - 'a'] = new Node();
            }
            p = p.nodes[c - 'a'];
            p.pass++;
        }
        p.end++;
    }

    /**
     * 删除字符串
     *
     * @param str
     */
    public static void del(String str) {
        if (str == null) return;
        if (numWord(str) < 1) return;
        Node p = root;
        p.pass--;
        for (char c : str.toCharArray()) {
            if (--p.nodes[c - 'a'].pass == 0) {
                p.nodes[c - 'a'] = null;
                return;
            }
            p = p.nodes[c - 'a'];
        }
        p.end--;
    }

    /**
     * 单词数量
     *
     * @param word
     * @return
     */
    public static int numWord(String word) {
        if (word == null) return 0;
        Node p = root;
        for (char c : word.toCharArray()) {
            if (p.nodes[c - 'a'] == null) return 0;
            p = p.nodes[c - 'a'];
        }
        return p.end;
    }

    /**
     * 前缀数量
     *
     * @param prefix
     * @return
     */
    public static int numPrefix(String prefix) {
        if (prefix == null) return 0;
        Node p = root;
        for (char c : prefix.toCharArray()) {
            if (p.nodes[c - 'a'] == null) return 0;
            p = p.nodes[c - 'a'];
        }
        return p.pass;
    }

    public static void main(String[] args) {
        String s = "abc";
        String s2 = "abcd";
        add(s);
        add(s2);
        int wn = numWord(s);
        System.out.println(wn);
        int pn = numPrefix(s);
        System.out.println(pn);

        del(s);
        wn = numWord(s);
        System.out.println(wn);
        pn = numPrefix(s);
        System.out.println(pn);
    }
}

class Node {
    int pass;//经过
    int end;//结束
    Node[] nodes;

    Node() {
        pass = 0;
        end = 0;
        nodes = new Node[26];
    }
}