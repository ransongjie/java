package com.xcrj.company.pass1;

public class A065MinSwapTimesOrdered {
    /**
     * 知道，as[]中是0~n-1 且 不重复的值
     * 下标循环怼 下标和值不相等就交换
     * @param as
     * @return
     */
    public static int minSwapTimesOrdered(int[] as){
        int times=0;
        for (int i = 0; i < as.length; i++) {
            while(i!=as[i]){
                swap(as,i,as[i]);
                times++;
            }
        }
        return times;
    }

    private static void swap(int[]as,int a,int b){
        int tmp=as[a];
        as[a]=as[b];
        as[b]=tmp;
    }

    /**
     * 暴力
     * @param as
     * @return
     */
    public static int minSwapTimesOrdered2(int[] as){
        return f(as,as.length-1);
    }

    /**
     * 已知变有序最多交换n-1次，
     * 因为存在无效交换，所以达到最大交换次数后退出
     * @param as
     * @param times
     * @return
     */
    private static int f(int[]as,int times){
        //是否已经有序
        boolean isSorted=true;
        for (int i = 1; i < as.length; i++) {
            if(as[i-1]>as[i]){
                isSorted=false;
                break;
            }
        }
        //已经有序
        if(isSorted){
            return times;
        }
        //交换次数达到限制
        if(times>=as.length-1){
            return Integer.MAX_VALUE;
        }
        //无序，需要交换，求最少交换次数
        //！ 枚举所有可能的交换
        int ans=Integer.MAX_VALUE;
        for (int i = 0; i < as.length; i++) {
            for (int j = i+1; j < as.length; j++) {
                swap(as,i,j);
                //无效交换会因为达到最大交换次数而退出
                ans=Math.min(ans,f(as,times+1));
                swap(as,i,j);
            }
        }
        return ans;
    }
}
