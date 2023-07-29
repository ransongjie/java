package com.xcrj.company.pass1;

import java.util.Arrays;

public class A050ExamMWays {
    /**
     *
     * @param as
     * @param m 前一道试题难度-后一道试题难度<=m pre in [cur,cur+m]
     * @return
     */
    public static int sequenceM(int[]as,int m){
        //构建索引树
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for (int a :
                as) {
            min=Math.min(min,a);
            max=Math.max(max,a);
        }
        //创建树状数组 二叉索引树
        IndexTree indexTree=new IndexTree(max-min+2);

        //排序
        Arrays.sort(as);
        //all=1 第0个题，1套卷子
        int a=0,b=0,all=1;
        //第0套试题在 indexTree中的 idx
        //第0套试题往右上 所有结点+1
        //结点值+1
        indexTree.add(as[0]-min+1,1);
        for (int i = 1; i < as.length; i++) {
            //第i套试题在 indexTree中的 idx
            a=as[i]-min+1;
            //[i]-m <= [0:i-1]有多少结点值; [i] in [old,old+m]
            //！i个数-([0:i-1]有多少结点值 < [i]-m); i-前缀和
            //a-m-1=0=idx, 没有左上
            b=i-(a-m-1>=1?indexTree.sum(a-m-1):0);
            //1：直接将x放到0~i中all套有效试题的后面
            all*=(b+1);
            //第i套试题往右上 所有结点+1
            //结点值+1；试题难度
            indexTree.add(a,1);
        }

        return all;
    }

    //树状数组 二叉索引树
    //O(logn)高效求前缀和
    static class IndexTree{
        int [] tree;
        int N;
        IndexTree(int size){
            N=size;
            tree=new int[N+1];//第0个位置不用
        }
        //求前缀和 往左上走 累加每个结点的值
        int sum(int idx){
            int ans=0;
            while(idx>0){
                ans+=tree[idx];
                //mostRight1 = x&(~x+1) = idx&-idx
                idx-=idx&-idx;//idx-=lowbot(idx)
            }
            return ans;
        }
        //更新树状数组 往右上走 每个结点的值都加上d
        void add(int idx,int d){
            while(idx<=N){
                tree[idx]+=d;
                idx+=idx&-idx;//idx+=lowbot(idx)
            }
        }
    }
}
