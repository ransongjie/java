package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 109. 开密码锁
 * 一个密码锁由 4 个环形拨轮组成，每个拨轮都有 10 个数字(0~9)
 * - 初始数字 0000
 * - 结束数字 9999
 * - 9的下一个数字是0
 * - 0的上一个数字是9
 * 输入，deadends死亡数字，target目标数字
 * 输出，不能解锁，返回 -1；可以解锁 返回最小旋转次数
 */
public class Solution109 {

    //广度优先遍历+建立树
    //xcrj 广度优先搜索可以求单源最短路径，从单源"0000"开始，每次转1位
    public int openLock(String[] deadends, String target) {
        if("0000".equals(target))return 0;

        //hash表存放死亡数字
        Set<String> deadSet=new HashSet<>();
        for(String str:deadends)deadSet.add(str);

        if(deadSet.contains("0000"))return -1;
        if(deadSet.contains(target))return -1;

        int step=0;
        Queue<String> que=new ArrayDeque<>();
        //密码从"0000"开始
        que.offer("0000");

        Set<String> visited=new HashSet<>();
        visited.add("0000");

        //广度优先搜索
        while(!que.isEmpty()){
            step++;

            //处理队列中的所有元素
            int queSize=que.size();
            for(int i=0;i<queSize;i++){
                String currKey=que.poll();
                //处理当前元素的所有下一个元素
                for(String nextKey:getNextKeys(currKey)){
                    if(!visited.contains(nextKey)&&!deadSet.contains(nextKey)){
                        //xcrj 尽量使用equals
                        if(nextKey.equals(target))return step;
                        que.offer(nextKey);
                        visited.add(nextKey);
                    }
                }
            }
        }

        return -1;
    }

    //4个转轮，currKey每个转轮都可以往前往后
    private List<String> getNextKeys(String currKey) {
        List<String> nextKeys=new ArrayList<>();
        char[] cs=currKey.toCharArray();
        for(int i=0;i<4;i++){
            char csi=cs[i];

            cs[i]=getNext(csi);
            nextKeys.add(new String(cs));

            cs[i]=getPrev(csi);
            nextKeys.add(new String(cs));

            cs[i]=csi;
        }

        return nextKeys;
    }

    private char getPrev(char c) {
        return c=='0'?'9':(char)(c-1);
    }

    private char getNext(char c) {
        return c=='9'?'0':(char)(c+1);
    }
}
