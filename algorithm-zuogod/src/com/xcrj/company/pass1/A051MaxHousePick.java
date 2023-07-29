package com.xcrj.company.pass1;

public class A051MaxHousePick {
    //不是环形
    public static int maxPick(int[]as){
        int[]dp=new int[as.length];
        dp[0]=as[0];
        dp[1]=Math.max(as[0],as[1]);
        for (int i = 2; i < as.length; i++) {
            int p1=as[i];//都是正数 则这个不需要
            int p2=as[i]+dp[i-2];
            int p3=dp[i-1];
            dp[i]=Math.max(Math.max(p1,p2),p3);
        }

        return dp[as.length-1];
    }

    public static int maxPickCircle(int[]as){
        //0~n-1
        int pre2=as[0];
        int pre1=Math.max(as[0],as[1]);
        for (int i = 2; i < as.length-1; i++) {
            int cur=Math.max(pre1,pre2+as[i]);
            pre2=pre1;
            pre1=cur;
        }
        int ans1=pre1;

        //1~n
        pre2=as[1];
        pre1=Math.max(as[1],as[2]);
        for (int i = 3; i < as.length; i++) {
            int cur=Math.max(pre1,pre2+as[i]);
            pre2=pre1;
            pre1=cur;
        }
        int ans2=pre1;

        return Math.max(ans1,ans2);
    }
}
