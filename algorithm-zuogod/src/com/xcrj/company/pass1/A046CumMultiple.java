package com.xcrj.company.pass1;

public class A046CumMultiple {

    public static double accMultiple(double[]as){
        double preMax=as[0];
        double preMin=as[0];
        double ans=as[0];
        for (int i = 1; i < as.length; i++) {
            double c1=as[i];
            double c2=as[i]*preMax;
            double c3=as[i]*preMin;
            double curMax=Math.max(Math.max(c1,c2),c3);
            double curMin=Math.min(Math.min(c1,c2),c3);
            ans=Math.max(ans,curMax);
            preMax=curMax;
            preMin=curMin;
        }
        return ans;
    }

    public static double accMultiple2(double[]as){
        double preMax=as[0];
        double preMin=as[0];
        double ans=as[0];
        for (int i = 1; i < as.length; i++) {
            preMax=Math.max(Math.max(as[i],as[i]*preMax),as[i]*preMin);
            preMin=Math.min(Math.min(as[i],as[i]*preMax),as[i]*preMin);
            ans=Math.max(ans,preMax);
        }
        return ans;
    }
}
