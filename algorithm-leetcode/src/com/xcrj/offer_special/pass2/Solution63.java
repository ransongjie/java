package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 063. 替换单词
 * 在英语中，有一个叫做词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为继承词(successor)。例如，词根an，跟随着单词other(其他)，可以形成新的单词another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 需要输出替换之后的句子。
 * <p>
 * 继承词用词根替代
 * 继承词有多个词根，用最短的词根替代
 */
public class Solution63 {

    /**
     * 蛮力法+Set<dictionaryStrig>
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords1(List<String> dictionary, String sentence) {
        Set<String> set=new HashSet<>();
        for(String str:dictionary){
            set.add(str);
        }

        String[] words=sentence.split(" ");
        for(int i=0;i<words.length;i++){
            for(int j=0;j<words[i].length();j++){
                // 从(0,j+1)截取子串 首先获得的是最短的词根
                String subWord=words[i].substring(0, j+1);
                if(set.contains(subWord)){
                    words[i]=subWord;
                    // 找到1个最短的词根即可
                    break;
                }
            }
        }

        // xcrj
        return String.join(" ", words);
    }

    // 字典树的一个结点
    class Trie {
        // xcrj map<本结点值，下结点指针>
        Map<Character,Trie> map;

        public Trie(){
            map=new HashMap<>();
        }
    }

    /**
     * 字典树存储dictionary
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords2(List<String> dictionary, String sentence) {
        // 构建字典树
        Trie root=new Trie();
        for(String str:dictionary){
            Trie node=root;
            for(char c:str.toCharArray()){
                node.map.putIfAbsent(c, new Trie());
                node=node.map.get(c);
            }
            node.map.put('#', null);
        }

        String[] words=sentence.split(" ");
        for(int i=0;i<words.length;i++){
            words[i]=availableMinPrefix(root, words[i]);
        }

        return String.join(" ", words);
    }

    private String availableMinPrefix(Trie trie, String word) {
        StringBuilder sb = new StringBuilder();
        Trie node = trie;
        for (char c : word.toCharArray()) {
            // 最短词根
            if(node.map.containsKey('#')) return sb.toString();
            // 无此词根
            if(!node.map.containsKey(c)) return word;

            sb.append(c);
            node=node.map.get(c);
        }

        return sb.toString();
    }
}
