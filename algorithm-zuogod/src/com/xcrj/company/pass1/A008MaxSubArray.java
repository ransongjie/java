package com.xcrj.company.pass1;

/**
 * 连续子数组最大累加和
 */
public class A008MaxSubArray {
    public static int maxSubArray(int[]as){
        int pre=as[0];//第i-1个元素往左扩的最大值
        int max=as[0];
        for (int i = 1; i < as.length; i++) {
            int c1=as[i];
            int c2=as[i]+pre;
            int cur=Math.max(c1,c2);
            max=Math.max(max,cur);
            pre=cur;
        }
        return max;
    }

    public static int maxSubArray2(int[]as) {
        int pre = as[0];//第i-1个元素往左扩的最大值
        int max = as[0];
        for (int i = 1; i < as.length; i++) {
            pre=Math.max(as[i],as[i]+pre);//
            max=Math.max(max,pre);
        }
        return max;
    }
}
