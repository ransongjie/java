package com.xcrj.basic.pass2.prefix_tree;


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

    static class TreeNode{
        int pass;
        int end;
        // Map<Character,TreeNode> nexts=new HashMap<>();
        // Map<Character,TreeNode> nexts=new TreeMap<>();
        TreeNode[] nexts;
        public TreeNode(){
            this.pass=0;
            this.end=0;
            this.nexts=new TreeNode[26];
        }
    }

    static TreeNode root=new TreeNode();

    public static int wordNum(String str) {
        if(str==null) return 0;
        TreeNode p=root;
        char[]cs=str.toCharArray();
        for (char c : cs) {
            if(p.nexts[c-'a']==null){
                return 0;
            }
            p=p.nexts[c-'a'];
        }
        return p.end;
    }

    public static int prefixNum(String str) {
        if(str==null) return 0;
        TreeNode p=root;
        char[]cs=str.toCharArray();
        for (char c : cs) {
            if(p.nexts[c-'a']==null){
                return 0;
            }
            p=p.nexts[c-'a'];
        }
        return p.pass;
    }

    public static void add(String str) {
        if(str==null) return;
        char[]cs=str.toCharArray();
        TreeNode p=root;
        p.pass++;
        for (char c : cs) {
            if(p.nexts[c-'a']==null){
                TreeNode t=new TreeNode();
                p.nexts[c-'a']=t;
            }
            p=p.nexts[c-'a'];
            p.pass++;
        }
        p.end++;
    }

    /**
     * 删除单词
     * @param str
     */
    public static void del(String str) {
        if(wordNum(str)==0) return;//
        if(str==null) return;
        char[]cs=str.toCharArray();
        TreeNode p=root;
        p.pass--;
        for (char c : cs) {
            p.nexts[c-'a'].pass--;
            if(p.nexts[c-'a'].pass==0){
                p.nexts[c-'a']=null;
                return;
            }
            p=p.nexts[c-'a'];
        }
        p.end--;
    }
}
