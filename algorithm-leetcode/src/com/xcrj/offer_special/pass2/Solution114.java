package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 114. 外星文字典
 * 输入，words 词典
 * 输出，String 字母顺序
 * - 根据 words 还原此语言中已知的字母顺序，并 按字母递增顺序 排列。
 * - 若不存在合法字母顺序，返回 "" 
 * - 若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 * 
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * - 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * - 如果前面 minLen=min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 */
public class Solution114 {
    // 结点访问状态状态：在states中不存在=未搜索，1=搜索中，2=已完成
    Map<Character,Integer> state=new HashMap<>();
    //邻接表
    Map<Character,List<Character>> ccs=new HashMap<>();
    // 顺序栈，栈空top=n，入栈--top，栈满top=0，下标 n-1 为栈底，0 为栈顶
    char[] rs;
    // 栈下标
    int top;
    // 剪枝，有环及时退出
    boolean valid = true;

    //建图+深度优先拓扑排序
    //为什么可以使用拓扑排序？前1个字母顺序<后1个字母顺序
    //xcrj 深度优先 先将没有出度的结点入逆栈 正向栈依次是没有入度的结点
    public String alienOrder1(String[] words) {
        //初始化
        for(String word:words)for(char c:word.toCharArray())ccs.putIfAbsent(c, new ArrayList<>());
        for(String word:words)for(char c:word.toCharArray())state.putIfAbsent(c, 0);
        rs=new char[ccs.size()];
        top=ccs.size();

        //建图，构造邻接表
        for(int i=1;i<words.length;i++)buildAdjTable(words[i-1], words[i]);
        if(!valid)return "";

        //深度优先搜索所有“未搜索结点”
        state.forEach((k,v)->{if(v==0)dfs(k);});

        //存在环，没有可行的拓扑排序序列，没有可行的字母顺序，返回空字符串
        if(!valid)return "";
        
        return new String(rs);
    }

    private void buildAdjTable(String before, String after) {
        //取两个单词的最小长度
        int beforeLen=before.length();
        int afterLen=after.length();
        int minLen=Math.min(beforeLen,afterLen);

        //同时遍历前后两个单词的相同位置
        //找到首个不相同字母即可（因为是按字典序）
        int idx=0;
        while(idx<minLen){
            char beforeC=before.charAt(idx);
            char afterC=after.charAt(idx);
            if(beforeC!=afterC){
                ccs.get(beforeC).add(afterC);
                break;
            }
            idx++;
        }

        if(idx==minLen&&afterLen<beforeLen)valid=false;
    }

    private void dfs(char u) {
        //u 搜索中
        state.put(u,1);
        
        for(char v:ccs.get(u)){
            if(state.get(v)==0){
                dfs(v);
                if(!valid)return;
            }

            if(state.get(v)==1){
                valid=false;
                return;
            }
        }

        //u 已完成
        state.put(u, 2);
        rs[--top]=u;
    }

    //map<字母,入度>
    Map<Character, Integer> inDegree = new HashMap<>();

    //建图+广度优先拓扑排序
    //xcrj 广度优先 将没有入度的结点入数组
    public String alienOrder2(String[] words) {
        //初始化
        for(String word:words)for(char c:word.toCharArray())ccs.putIfAbsent(c, new ArrayList<>());
        for(String word:words)for(char c:word.toCharArray())inDegree.putIfAbsent(c, 0);

        //建图，构造邻接表
        for(int i=1;i<words.length;i++)buildAdjTable2(words[i-1], words[i]);
        if(!valid)return "";

        //将所有入度为 0 的节点放入队列中
        Queue<Character> que=new ArrayDeque<>();
        inDegree.forEach((k,v)->{if(v==0)que.offer(k);});

        //开始广度优先遍历
        StringBuilder sb=new StringBuilder();
        while(!que.isEmpty()){
            char u=que.poll();
            sb.append(u);
            for(char v:ccs.get(u)){
                inDegree.put(v, inDegree.get(v)-1);
                if(inDegree.get(v)==0)que.offer(v);
            }
        }

        //遍历完所有结点
        if(sb.length()==ccs.size())return sb.toString();

        return "";
    }

    private void buildAdjTable2(String before, String after) {
        //取两个单词的最小长度
        int beforeLen=before.length();
        int afterLen=after.length();
        int minLen=Math.min(beforeLen,afterLen);

        //同时遍历前后两个单词的相同位置
        //找到首个不相同字母即可（因为是按字典序）
        int idx=0;
        while(idx<minLen){
            char beforeC=before.charAt(idx);
            char afterC=after.charAt(idx);
            if(beforeC!=afterC){
                ccs.get(beforeC).add(afterC);
                inDegree.put(afterC,inDegree.get(afterC)+1);
                break;
            }
            idx++;
        }

        if(idx==minLen&&afterLen<beforeLen)valid=false;
    }
}
