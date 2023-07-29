package com.xcrj.company.pass1;

import java.util.HashMap;

/**
 * 01等比例划分的 最大部分数
 * 01不能等比例划分 只有1个部分
 * 输入: str = "010100001"
 * 输出: ans = [1, 1, 1, 2, 1, 2, 1, 1, 3]
 * 思路：部分比例 恒等 整体比例
 */
public class A060Ratio01Split {
    public static int[] ratio01Split(int[]as){
        //map<比例分子,map<比例分母,这样比例的前缀出现次数>>
        HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
        //
        int n=as.length;
        //0~i，01能够等比例划分的最大部分数
        int[]ans=new int[n];
        //0,1出现的次数
        int zeroNum=0,oneNum=0;
        for (int i = 0; i < n; i++) {
            if(as[i]==1){
                oneNum++;
            }else{
                zeroNum++;
            }
            //0或1的数量为0，不能等比例划分 只有1个部分
            if(oneNum==0||zeroNum==0){
                ans[i]=i+1;
            }else{
                int g=gcd(zeroNum,oneNum);
                int zero=zeroNum/g;
                int one=oneNum/g;
                if(!map.containsKey(zero)){
                    map.put(zero,new HashMap<>());
                }
                if(!map.get(zero).containsKey(one)){
                    map.get(zero).put(one,1);
                }else{
                    map.get(zero).put(one,map.get(zero).get(one)+1);
                }
                ans[i]=map.get(zero).get(one);
            }
        }
        return ans;
    }

    //最大公约数 辗转相除法 除数=0时停止
    private static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
}
