package com.xcrj.basic.pass1.prefix_tree;

public class PrefixTree {
    public static void main(String[] args) {
        String s="abc";
        add(s);
        int wn=wordNum(s);
        System.out.println(wn);
        int pn=prefixNum(s);
        System.out.println(pn);
        
        del(s);
        wn=wordNum(s);
        System.out.println(wn);
        pn=prefixNum(s);
        System.out.println(pn);
    }

    static class TireNode{
        int pass;//经过
        int end;//结尾
        // 散列表 HashMap<Character,TireNode> nextMap;
        // 有序表 TreeMap<Character,TireNode> nextMap;
        TireNode[] nexts;

        public TireNode(){
            pass=0;
            end=0;
            nexts=new TireNode[26];
        }
    }
    static TireNode root=new TireNode();
    //添加单词
    public static void add(String word) {
        if(word==null) return;
        TireNode p=root;
        p.pass++;
        char[] cs=word.toCharArray();
        for (char c : cs) {//循环中月应该操作同一个结点
            int idx=c-'a';
            if(p.nexts[idx]==null){
                TireNode n=new TireNode();
                p.nexts[idx]=n;
            }
            p.nexts[idx].pass++;//
            p=p.nexts[idx];
        }
        p.end++;
    }

    //删除单词, 不是删除前缀
    public static void del(String word){
        if(wordNum(word)==0) return;
        //
        TireNode p=root;
        p.pass--;
        char[] cs=word.toCharArray();
        for (char c : cs) {
            int idx=c-'a';
            p.nexts[idx].pass--;
            if(p.nexts[idx].pass==0){
                p.nexts[idx]=null;
                return;
            }
            p=p.nexts[idx];
        }
        p.end--;
    }

    //前缀个数
    public static int prefixNum(String prefix) {
        if(prefix==null) return 0;
        TireNode p=root;
        char[] cs=prefix.toCharArray();
        for (char c : cs) {
            int idx=c-'a';
            if(p.nexts[idx]==null) return 0;
            p=p.nexts[idx];
        }
        return p.pass;
    }

    //单词个数
    public static int wordNum(String word) {
        if(word==null) return 0;
        TireNode p=root;
        char[] cs=word.toCharArray();
        for (char c : cs) {
            int idx=c-'a';
            if(p.nexts[idx]==null) return 0;
            p=p.nexts[idx];
        }
        return p.end;
    }
}
