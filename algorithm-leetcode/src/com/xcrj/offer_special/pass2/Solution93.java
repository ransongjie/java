package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 093. 最长斐波那契数列
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * - n >= 3
 * - 对于所有 i + 2 <= n，都有 X_k + X_{j+1} = X_{i+2}
 * 
 * 输入，给定一个严格递增的正整数数组形成序列 arr
 * 输出，找到 arr 中最长的斐波那契式的子序列的长度; 如果一个不存在，返回  0 。
 */
public class Solution93 {
    public int lenLongestFibSubseq(int[] arr) {
        // map<值,下标>
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++)map.put(arr[i],i);

        // 动态规划数组，dp[j][i]表示 （...,arr[k],arr[j],arr[i]） 斐波那契子序列的长度
        // 动态规划数组记录了 下标表示当前斐波那契子序列的最后两个值的下标，值表示斐波那契子序列的长度
        int[][] dp=new int[arr.length][arr.length];

        int r=0;
        for(int i=0;i<arr.length;i++){
            // k,j,i arr[k]+arr[j]=arr[i]，已知严格递增，因此当2*arr[j]<=arr[i]时，找不到arr[k]
            for(int j=i;j>=0&&2*arr[j]>arr[i];j--){
                int k=map.getOrDefault(arr[i]-arr[j], -1);
                if(k>=0){
                    // 3最短斐波那契子序列的长度为3
                    // xcrj 斐波那契序列 arr[p],arr[k],arr[j],arr[i]
                    dp[j][i]=Math.max(3,dp[k][j]+1);
                }
                r=Math.max(r, dp[j][i]);
            }
        }
        
        return r;
    }
}
