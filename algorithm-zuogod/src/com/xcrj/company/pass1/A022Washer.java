package com.xcrj.company.pass1;

/**
 * 洗衣机相连转移所需最少轮数（一样多衣服）
 * arr[i洗衣机]=衣服数量
 * 每一轮，i洗衣机往左或右转移一件衣服。
 * 需要几轮所有洗衣机中的衣服数量一样多
 * 每一轮每一台洗衣机可以并发
 */
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
        int leftSum=0;//0~i左侧总共有几件衣服
        int leftRest;//0~i-1左侧还差几件衣服
        int rightRest;//i+1~n-1总共还差几件衣服
        for (int i = 0; i < size; i++) {
            leftRest=(i*avg)-leftSum;
            rightRest=sum-as[i]-leftSum-(size-i-1)*avg;
            if(leftRest<0&&rightRest<0){
                //左边和右边都差衣服，转移次数需要求和
//                ans=-leftRest+-rightRest;
                ans=Math.max(ans,Math.abs(leftRest)+Math.abs(rightRest));
            }else{
                //左边和右边一边多，一边少，转移次数求最大值（至少转移/中转最大值）
//                ans+=Math.max(Math.abs(leftRest),Math.abs(rightRest));
                ans=Math.max(ans,Math.max(Math.abs(leftRest),Math.abs(rightRest)));
            }
            leftSum+=as[i];
        }

        return ans;
    }
}
