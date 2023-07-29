package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 108. 单词演变，beginWord每次变化1个字母到endWord
 * 输入，beginWord，wordList，endWord
 * 输出，从beginWord到endWord变化单词的最短路径
 * beginWord每次只能变化1个字母，变化字母后形成的单词应该在wordList中
 * endWord必须在wordList中
 */
public class Solution108 {
    //邻接表
    //map<word,编号>
    Map<String,Integer> wordNumMap=new HashMap<>();
    List<List<Integer>> iJss=new ArrayList<>();
    int nodeNum=0;

    //广度优先搜索+优化建图
    //xcrj 广度优先搜索可以求单源最短路径，从单源beginWord开始，每次只能变化1个字母
    //beginWord<->加*word<->中间word(位于wordList中的单词)<->endword
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        //建图
        addEdge(beginWord);
        for(String middleWord:wordList) addEdge(middleWord);

        //endWord必须在wordList中
        if(!wordNumMap.containsKey(endWord))return 0;

        //广度优先搜索记录起点到每个结点的最短距离
        int[] minDists=new int[nodeNum];
        Arrays.fill(minDists, Integer.MAX_VALUE);

        int beginWordNum=wordNumMap.get(beginWord);
        //beginWord 到 beginWord 的最短距离为0
        minDists[beginWordNum]=0;

        int endWordNum=wordNumMap.get(endWord);
        //广度优先搜索
        Queue<Integer> que=new ArrayDeque<>();
        que.offer(beginWordNum);
        while(!que.isEmpty()){
            int numI=que.poll();
            //最短路径=广度优先搜索走过的路径/2+1。/2因为走过了每个单词的加*单词，+1，加上beginWord
            if(numI==endWordNum)return minDists[endWordNum]/2+1;
            for(Integer numJ:iJss.get(numI)){
                //xcrj 第1次广度优先遍历到的距离才是最短距离
                if(minDists[numJ]==Integer.MAX_VALUE){
                    minDists[numJ]=minDists[numI]+1;
                    que.offer(numJ);
                }
            }
        }

        return 0;
    }

    //word<->加*word
    private void addEdge(String word) {
        addWord(word);

        int wordI=wordNumMap.get(word);
        char[] cs=word.toCharArray();
        for(int i=0;i<cs.length;i++){
            char temp=cs[i];
            cs[i]='*';
            String starWord=new String(cs);
            addWord(starWord);

            int wordJ=wordNumMap.get(starWord);
            //无向图
            iJss.get(wordI).add(wordJ);
            iJss.get(wordJ).add(wordI);

            cs[i]=temp;
        }
    }

    //单词放入邻接表中
    private void addWord(String word) {
        if(!wordNumMap.containsKey(word)){
            wordNumMap.put(word, nodeNum++);
            iJss.add(new ArrayList<>());
        }
    }

    //双向广度优先搜索+优化建图，可以显著减小搜索空间大小
    //从beginWord开始广度优先搜索。从endWord开始广度优先搜索。访问了同一结点就停止搜索
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        //建图
        addEdge(beginWord);
        for(String middleWord:wordList) addEdge(middleWord);

        //endWord必须在wordList中
        if(!wordNumMap.containsKey(endWord))return 0;

        //从beginWord开始
        int beginNum=wordNumMap.get(beginWord);
        int[] beginMinDists=new int[nodeNum];
        Arrays.fill(beginMinDists, Integer.MAX_VALUE);
        beginMinDists[beginNum]=0;

        Queue<Integer> beginQue=new ArrayDeque<>();
        beginQue.offer(beginNum);

        //从endWord开始
        int endNum=wordNumMap.get(endWord);
        int[] endMinDists=new int[nodeNum];
        Arrays.fill(endMinDists, Integer.MAX_VALUE);
        endMinDists[endNum]=0;

        Queue<Integer> endQue=new ArrayDeque<>();
        endQue.offer(endNum);

        //双向广度优先搜索
        while(!beginQue.isEmpty()&&!endQue.isEmpty()){
            //从beginWord开始 广度优先遍历
            int beginQueSize=beginQue.size();
            //将此时 队列中的所有元素全部处理完
            for(int k=0;k<beginQueSize;k++){
                int numI=beginQue.poll();
                //xcrj 第1次广度优先遍历到的距离才是最短距离
                //nodeI被另一个广度优先访问过(看endMinDists数组),访问了同一顶点就停止搜索
                if(endMinDists[numI]!=Integer.MAX_VALUE)return (beginMinDists[numI]+endMinDists[numI])/2+1;
                for(Integer numJ:iJss.get(numI)){
                    //xcrj 第1次广度优先遍历到的距离才是最短距离
                    if(beginMinDists[numJ]==Integer.MAX_VALUE){
                        beginMinDists[numJ]=beginMinDists[numI]+1;
                        beginQue.offer(numJ);
                    }
                }
            }
            
            //从endWord开始 广度优先遍历
            int endQueSize=endQue.size();
            //将此时 队列中的所有元素全部处理完
            for(int k=0;k<endQueSize;k++){
                int numI=endQue.poll();
                //nodeI被另一个广度优先访问过(看beginMinDists数组),访问了同一顶点就停止搜索
                if(beginMinDists[numI]!=Integer.MAX_VALUE)return (beginMinDists[numI]+endMinDists[numI])/2+1;
                for(Integer numJ:iJss.get(numI)){
                    //xcrj 第1次广度优先遍历到的距离才是最短距离
                    if(endMinDists[numJ]==Integer.MAX_VALUE){
                        endMinDists[numJ]=endMinDists[numI]+1;
                        endQue.offer(numJ);
                    }
                }
            }
        }

        return 0;
    }
}
