package com.xcrj.company.pass1;

public class A022Washer {
    public static int washer(int[]as){
        int size=as.length;
        int sum=0;
        for (int a :
                as) {
            sum+=a;
        }
        if(sum%size!=0) return -1;

        int ans=0;
        int avg=sum/size;
        int leftSum=0;
        int leftRest;
        int rightRest;
        for (int i = 0; i < size; i++) {
            leftRest=(i*avg)-leftSum;
            rightRest=sum-as[i]-leftSum-(size-i-1)*avg;
            if(leftRest<0&&rightRest<0){
//                ans=-leftRest+-rightRest;
                ans=Math.max(ans,Math.abs(leftRest)+Math.abs(rightRest));
            }else{
//                ans+=Math.max(Math.abs(leftRest),Math.abs(rightRest));
                ans=Math.max(ans,Math.max(Math.abs(leftRest),Math.abs(rightRest)));
            }
            leftSum+=as[i];
        }

        return ans;
    }
}
