package com.xcrj.company.pass1;

/**
 * 递增子序列的最大长度
 */
public class A017MaxIncSub {
    public static int maxIncSub(int[]as){
        int[]end=new int[as.length];
        end[0]=as[0];
        int max=1;
        int l=0,r=0,m=0,right=0;
        for (int i = 1; i < as.length; i++) {
            l=0;//end[]已经有值区间左边界
            r=right;//右边界
            while(l<=r){//在end[]已知区域中求>=as[i]的最小值
                m=(l+r)>>1;
                if(as[i]>end[m]){
                    l=m+1;
                }else{
                    r=m-1;
                }
            }
            end[l]=as[i];//l经过二分后 记录了 >=as[i]的最小值 或 右边界+1
            right=Math.max(right,l);//right记录了右边界
            max=Math.max(max,right+1);
            //max=Math.max(max,l+1);
        }
        return max;
    }

}
