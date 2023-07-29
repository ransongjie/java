package com.xcrj.company.pass1;

/**
 * 阈值abc抽牌获胜概率
 */
public class A021PCard {
    public static double pCard(){
        return p(0);//从0开始
    }

    private static double p(int cur){
        if(cur>=17&&cur<21){
            return 1.0;
        }
        if(cur>21){
            return 0.0;
        }
        double w=0.0;
        for (int i = 1; i <=10; i++) {
            w+=p(cur+i);
        }
        return w/10.0;
    }

    public static double pCard2(int n,int a,int b){
        if(n<1||a<0||b<0||a>=b){
            return 0.0;
        }
        if(b>=a+n){//
            return 1.0;
        }
        return p2(0,n,a,b);
    }

    private static double p2(int cur,int n,int a,int b){
        if(cur>=a&&cur<b){
            return 1.0;
        }
        if(cur>b){
            return 0.0;
        }
        double w=0.0;
        for (int i = a; i <= b; i++) {
            w+=p2(cur+a,n,a,b);
        }
        return w/n;
    }

    public static double pCard3(int n,int a,int b){
        if(n<1||a<0||b<0||a>=b){
            return 0.0;
        }
        if(b>=a+n){//
            return 1.0;
        }
        return p3(0,n,a,b);
    }

    private static double p3(int cur,int n,int a,int b){
        if(cur>=a&&cur<b){
            return 1.0;
        }
        if(cur>b){
            return 0.0;
        }
        if(cur==a-1){
            return 1.0*(b-a)/n;//
        }
        double w=p3(cur+1,n,a,b)+p3(cur+1,n,a,b)*n;
        if (cur < b-n-1) {
            w-=p3(cur+n+1,n,a,b);
        }
        return w/n;
    }

    public static double pCard4(int n,int a,int b){
        if(n<1||a<0||b<0||a>=b){
            return 0.0;
        }
        if(b>=a+n){
            return 1.0;
        }
        double[] dp=new double[b];//dp[cur]=p
        //[a,b)已经算过
        for (int i = a; i < b; i++) {
            dp[i]=1.0;
        }
        //a-1
        if(a-1>=0){
            dp[a-1]=1.0*(b-a)/n;
        }
        //a-2
        for (int i = a-2; i >=0; i--) {
            double w=dp[i+1]+dp[i+1]*n;
            if(i<b-n-1){
                w-=dp[i+n+1];
            }
            dp[i]=w/n;
        }

        return dp[0];//dp[cur=0]=从0出发（一张牌都没抽）获胜的概率
    }
}
