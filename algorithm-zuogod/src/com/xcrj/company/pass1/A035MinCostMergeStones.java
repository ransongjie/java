package com.xcrj.company.pass1;

public class A035MinCostMergeStones {
    public int MinCostMergeStones(int[]as ,int k){
        if((as.length-1)%(k-1)>0){//n堆石子，一趟合并连续的k堆
            return -1;
        }
        //从0开始求前缀和
        int[]presum=new int[as.length+1];
        presum[0]=0;
        for (int i = 0; i < as.length; i++) {
            presum[i+1]=presum[i]+as[i];
        }
        //将从0~n-1的石堆合成1堆，每次合并连续的k堆，的最小成本
        return process(as,k,presum,0,as.length-1,1);
    }

    /**
     *
     * @param as 石头堆
     * @param k 每次合并连续的k堆
     * @param presum 前缀和
     * @param l 合并开始位置
     * @param r 合并结束位置
     * @param p 合并成p堆
     * @return
     */
    private int process(int[]as,int k,int[]presum,int l,int r,int p){
        //只有1堆，不用移动石碓中的石子
        if(l==r){
            return p==1?0:-1;
        }
        //把k堆合成1堆
        if(p==1){
            //从l~r合并成k堆的成本
            int costk=process(as,k,presum,l,r,k);
            //从l~r合并成k堆的成本+把k堆合成1堆的成本
            if(costk==-1){
                return -1;
            }else{
                //！ 把k堆合成1堆的成本=sum(第l堆~第r堆的和)=presum[r+1]-presum[l]
                return costk+presum[r+1]-presum[l];
            }
        }else {
            //l~r, 合并成p堆的最小成本
            int costp=Integer.MAX_VALUE;
            //！ l~l+m*(k-1)才能由连续的k堆合并为1堆
            for (int i = l; i < r; i+=k-1) {
                //l~i，合并成1堆的最小成本
                int cost1=process(as,k,presum,l,i,1);
                //i+1~r, 合并成p-1堆的最小成本
                int costp_1=process(as,k,presum,i+1,r,p-1);
                //只要有1堆凑不成，则p堆凑不成
                if(cost1!=-1&&costp_1!=-1){
                    costp=Math.min(costp,cost1+costp_1);
                }
            }
            return costp;
        }
    }

    public int MinCostMergeStones2(int[]as ,int k){
        if((as.length-1)%(k-1)>0){
            return -1;
        }

        int[]presum=new int[as.length+1];
        presum[0]=0;
        for (int i = 0; i < as.length; i++) {
            presum[i+1]=presum[i]+as[i];
        }
        //l~r，一趟合并连续的k堆
        int[][][]dp=new int[as.length+1][as.length+1][k+1];
        return process2(as,k,presum,0,as.length-1,1,dp);
    }

    private int process2(int[]as,int k,int[]presum,int l,int r,int p,int[][][]dp){
        if(dp[l][r][p]!=-1){
            return dp[l][r][p];
        }
        if(l==r){
            dp[l][r][p]=p==1?0:-1;
            return dp[l][r][p];
        }
        if(p==1){
            int costk=process(as,k,presum,l,r,k);
            if(costk==-1){
                dp[l][r][p]=-1;
                return dp[l][r][p];
            }else{
                dp[l][r][p]=costk+presum[r+1]-presum[l];
                return dp[l][r][p];
            }
        }else {
            int costp=Integer.MAX_VALUE;
            for (int i = l; i < r; i+=k-1) {
                int cost1=process(as,k,presum,l,i,1);
                int costp_1=process(as,k,presum,i+1,r,p-1);
                if(cost1!=-1&&costp_1!=-1){
                    costp=Math.min(costp,cost1+costp_1);
                }else{
                    dp[l][r][p]=-1;
                    return dp[l][r][p];
                }
            }
            dp[l][r][p]=costp;
            return dp[l][r][p];
        }
    }
}
