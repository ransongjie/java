package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 117. 相似的字符串
 * 入参strs中的字符串的字母都相同，字母顺序不同
 * 两个字符串相同位置字母不同的次数不大于2 则视为相似
 * 输入，strs[] 字符串数组
 * 输出，不相似字符串的数量
 */ 
public class Solution117 {
    // 定义并查集
    int[] roots;

    //并查集，合并根节点（相似单词），根节点数量等于不相似字符串数量
    public int numSimilarGroups(String[] strs) {
        int strNum=strs.length;
        //字符数量，每个单词的字符数量相同
        int charNum=strs[0].length();
        roots=new int[strNum];
        for(int i=0;i<strNum;i++)roots[i]=i;

        //两两字符串检查是否相似
        for(int i=0;i<strNum;i++){
            for(int j=i+1;j<strNum;j++){
                int rootI=findRoot(i);
                int rootJ=findRoot(j);
                //同根则相似
                if(rootI==rootJ)continue;
                //相似则合并根节点
                if(checkSimilarity(strs[i], strs[j], charNum))union(i, j);
            }
        }

        //根节点数量, xcrj 下标等于值的是根节点
        //roots[0]=1, roots[1]=2, roots[2]=2
        int r=0;
        for(int i=0;i<strNum;i++)if(i==roots[i])r++;

        return r;
    }

    private void union(int i,int j){
        int rootI=findRoot(i);
        int rootJ=findRoot(j);
        roots[rootI]=roots[rootJ];
    }

    private int findRoot(int node) {
        int pNode=roots[node];
        if(node!=pNode){
            int ppNode=findRoot(pNode);
            roots[node]=ppNode;
        }

        return roots[node];
    }

    //检查两个字符串是否相似，相同位置字符不同次数不大于2则相似
    private boolean checkSimilarity(String str1,String str2,int sLen){
        int count=0;
        for(int i=0;i<sLen;i++){
            if(str1.charAt(i)!=str2.charAt(i)) count++;
            if(count>2) return false;
        }

        return true;
    }
}
