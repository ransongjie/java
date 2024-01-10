package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/gas-station/
 * 加油站
 * 环形加油站
 */
public class Solution8{
    /*
     * 暴力
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //遍历起点站
        for(int i=0;i<gas.length;i++){
            // 剩余燃料
            int rest=gas[i]-cost[i];
            int j=(i+1)%gas.length;// 下一站
            // 从起点站出发模拟走一圈
            while(j!=i&&rest>=0){
                rest+=gas[j]-cost[j];
                j=(j+1)%gas.length;
            }
            // 回到起点
            if(j==i&&rest>=0){
                return j;
            }
        }
        return -1;
    }
    /*
     情况一：如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
     情况二：rest[i] = gas[i]-cost[i]为一天剩下的油，i从0开始计算累加到最后一站，如果累加没有出现负数，说明从0出发，油就没有断过，那么0就是起点。
     情况三：如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能把这个负数填平，能把这个负数填平的节点就是出发节点。
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // 从起点出发，求油箱中的 最小剩余油量
        int min=Integer.MAX_VALUE;
        // 从起点出发，求 剩余总油量 
        int sum=0; 
        for(int i=0;i<gas.length;i++){
            sum+=gas[i]-cost[i];
            if(sum<min){
                min=sum;
            }
        }

        // 剩余总油量<0, 一定跑不了一圈
        if(sum<0) return -1;
        // 最小剩余油量>=0, 可以从起点出发跑一圈
        if(min>=0) return 0;
        // 剩余总油量>=0 && 最小剩余油量<0，可以从非起点出发跑一圈
        for(int i=gas.length-1;i>=0;i--){
            min+=gas[i]-cost[i];
            // 从i出发min>=0
            if(min>=0){
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuit3(int[] gas, int[] cost) {
        //从0出发，剩余总油量
        int total=0;
        //从i出发，剩余总油量
        int sum=0;
        int ans=0;
        for(int i=0;i<gas.length;i++){
            total+=gas[i]-cost[i];
            sum+=gas[i]-cost[i];
            if(sum<0){
                ans=i+1;
                sum=0;
            }
        }
        //gas总和<cost总和
        if(total<0) return -1;
        return ans;
    }
}