package com.xcrj.company.pass1;

public class A076EatingBananas {

    /**
     * 单调性 吃香蕉的速度越快 所划分的时间越少
     * @param piles
     * @return
     */
    public static int eatingBananas(int[] piles,int t) {
        int maxSpeed = 0;
        for (int pipe : piles) {
            maxSpeed = Math.max(maxSpeed, pipe);
        }
        int l=0,r=maxSpeed,ans=maxSpeed,mid=0;
        while(l<=r){
            mid=(l+r)>>1;
            if(needHours(piles,mid)<=t){
                //找能在t之间内吃完所有香蕉的速度
                ans=mid;
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return ans;
    }

    /**
     * 吃完所有堆的香蕉所需的时间
     *
     * @param piles
     * @param speed 速度
     * @return
     */
    private static int needHours(int[] piles, int speed) {
        int ans = 0;
        for (int pipe : piles) {
            //! 向上取整标准操作 +除数-1
            ans += (pipe + speed - 1) / speed;
        }
        return ans;
    }
}
