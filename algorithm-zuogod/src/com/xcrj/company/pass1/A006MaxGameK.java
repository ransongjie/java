package com.xcrj.company.pass1;

import java.util.Arrays;

/**
 * 能力值差k的最大比赛场数
 */
public class A006MaxGameK {
    /**
     * @param as as[人id]=能力值
     * @param k 能力值差值范围
     * @return
     */
    public static int maxGameK(int[]as,int k){
        return process(as,k,0);
    }

    /**
     * 全排列 暴力求解
     * @param as
     * @param k
     * @param idx 后面的元素放到idx
     * @return
     */
    private static int process(int[]as,int k,int idx){
        int ans=0;
        if(idx!=as.length){
            //idx~len的每一个字母都尝试放到idx处，全排列
            for (int i = idx; i < as.length; i++) {
                swap(as,idx,i);
                ans=Math.max(ans,process(as,k,idx+1));
                swap(as,idx,i);
            }
        }else{
            //得到一种排列
            for (int i = 0; i+1 < as.length; i+=2) {
                if(as[i]-as[i+1]==k){
                    ans++;
                }
            }
        }

        return ans;
    }

    private static void swap(int[]as,int a,int b){
        int tmp=as[a];
        as[a]=as[b];
        as[b]=tmp;
    }

    //贪心，先让小值满足要求，成对
    public static int maxGameK2(int[]as,int k){
        Arrays.sort(as);
        //[l,r]窗口
        int l=0,r=0,ans=0,n=as.length;
        boolean[] isUsed=new boolean[n];
        while(r<n&&l<n){
            //特殊情况
            if(isUsed[l]){//
                l++;
                continue;
            }
            if(l>=r){//窗口中只有1个人，要求2人一对
                r++;
                continue;
            }
            //
            int distance=as[r]-as[l];
            if(distance==k){
                ans++;
                isUsed[r++]=true;
                l++;
            }else if(distance<k){
                r++;
            }else{
                l++;
            }
        }
        return ans;
    }
}
