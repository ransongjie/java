package com.xcrj.company.pass1;

import java.util.HashMap;

/**
 * +-获得target的方法数
 */
public class A002TargetSum {
    /**
     *
     * @param as 元素前添加+或-
     * @param target 目标值
     * @return
     */
    public static int targetSum(int[]as,int target){
        return process(as,target,0);
    }

    private static int process(int[]as,int rest,int i){
        if(i==as.length){
            return rest==0?1:0;
        }

        return process(as,rest-as[i],i+1)
                +process(as,rest+as[i],i+1);
    }

    public static int targetSum2(int[]as,int target){
        return process2(as,target,0,new HashMap<>());
    }

    //缓存命中与否
    private static int process2(int[]as, int rest, int i
            , HashMap<Integer,HashMap<Integer,Integer>> dp){
        if(dp.containsKey(i)&&dp.get(i).containsKey(rest)){
            return dp.get(i).get(rest);
        }

        int r=0;
        if(i==as.length){
            r=rest==0?1:0;
        }else {
            r=process2(as,rest-as[i],i+1,dp)
                    +process2(as,rest+as[i],i+1,dp);
        }

        if(!dp.containsKey(i)){
            dp.put(i,new HashMap<>());
        }
        dp.get(i).put(rest,r);
        return r;
    }

    //优化 认为as中的所有元素都是非负
    public static int targetSum3(int[]as,int target){
       int sum= 0;
        for (int a:
             as) {
            sum+=a;
        }
        //第2个条件奇偶性不一致。第2个条件成了纯纯的背包问题
        return target>sum||((sum^1)^(target^1))!=0?0:process3(as,(target+sum)>>1);
    }

    //2维动态规划 优化为1维动态规划 空间压缩技巧
    private static int process3(int[]as,int p){
        int[]dp=new int[p+1];
        dp[0]=1;
        for(int a:as){
            //target - current
            for(int i=p;i>=a;i--){
                dp[i]+=dp[i-a];
            }
        }
        return dp[p];
    }
}
