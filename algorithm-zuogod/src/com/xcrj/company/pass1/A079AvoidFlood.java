package com.xcrj.company.pass1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 避免洪水泛滥
 * rains[i] = j 第i天轮到j号湖泊下雨
 * 下雨日，啥也不干: -1
 * 不下雨日，没有湖泊可抽: 1
 */
public class A079AvoidFlood {
    /**
     * lakeRainDay 记录湖泊在哪些天下雨
     * noDrainedLake 记录下过雨没被抽干的湖泊
     * noDrainedNxtRain 根据没被抽干的湖泊下次下雨的时间形成小根堆
     * 没有抽干的湖泊，在干活日，抽干后面最近将下雨的湖泊，留出盛雨空间
     * 过程
     * - 遍历数组，值不是0，代表下雨，记录到noDrainedLake，更新lakeRainDay，记录下次该湖泊下雨noDrainedNxtRain
     * - 遍历数组，值是0，代表干活，从noDrainedNxtRain中获取最近将下雨的湖泊，抽干该湖泊noDrainedLake
     * @param as as[天]=湖泊编号 编号为0就要干活
     * @return
     */
    public static int[] avoidFlood(int[]as){
        int n=as.length;
        int[]ans=new int[n];
        //构建记录表 <lake,LinkedList<rainDay>>
        HashMap<Integer, LinkedList<Integer>> lakeRainDay=new HashMap<>();
        for (int i = 0; i < n; i++) {
            if(!lakeRainDay.containsKey(as[i])){
                lakeRainDay.put(as[i],new LinkedList<>());
            }
            lakeRainDay.get(as[i]).addLast(i);//
        }
        //没抽干的湖泊
        HashSet<Integer> noDrainedLake=new HashSet<>();
        //已经有水没抽干的湖下次下雨的时间
        PriorityQueue<Info> noDrainedNxtRain=new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            //下雨
            if(as[i]!=0){
                //下雨干不了活
                ans[i]=-1;
                //已经有水的湖，再下雨，洪水，无能为力返回空数组
                if(noDrainedLake.contains(as[i])) return new int[0];
                //下雨，有水
                noDrainedLake.add(as[i]);
                //更新，下过雨的湖泊不记录
                lakeRainDay.get(as[i]).pollFirst();
                //该湖下次下雨的时间
                if(!lakeRainDay.get(as[i]).isEmpty()){
                    noDrainedNxtRain.add(new Info(as[i],lakeRainDay.get(as[i]).peekFirst()));
                }
            }
            //不下雨，干活
            else{
                //没有湖泊会再下雨
                if(noDrainedNxtRain.isEmpty()){
                    ans[i]=1;
                }
                //干活，抽干最近将下雨的湖泊
                else{
                    Info nearest= noDrainedNxtRain.poll();
                    ans[i]= nearest.lake;
                    noDrainedLake.remove(nearest.lake);
                }
            }
        }
        return ans;
    }

    static class Info implements Comparable<Info>{
        int lake;//湖泊编号
        int nxtRainDay;//下次下雨的时间
        Info(int lake,int nxtRainDay){
            this.lake=lake;
            this.nxtRainDay=nxtRainDay;
        }

        @Override
        public int compareTo(Info o) {
            return nxtRainDay-o.nxtRainDay;
        }
    }
}
