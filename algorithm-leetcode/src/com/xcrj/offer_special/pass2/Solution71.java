package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 071. 按权重生成随机数
 * - 从权重之和中随机获取权重部分和，根据权重部分和获取下标
 */
public class Solution71 {
    class Solution {
        int len;
        int total;
        int[] preSumW;
        /**
         * 求w前缀和
         */
        public Solution(int[] w) {
            len=w.length;
            total=Arrays.stream(w).sum();
            preSumW=new int[w.length];
            // 初值 xcrj  w[0] 不是0
            preSumW[0]=w[0];
            for(int i=1;i<w.length;i++){
                preSumW[i]=preSumW[i-1]+w[i];
            }
        }

        /**
         * 权重随机选取下标i
         * @return
         */
        public int pickIndex() {
            // 随机获取1个权重
            // +1为了获取到len-1的下标
            int x=(int)(Math.random()*total)+1;
            return binarySearch(x);
        }

        private int binarySearch(int x) {
            // xcrj 下标0~len-1
            int l=0,r=len-1;
            while(l<r){
                int mid=(l+r)>>1;
                if(preSumW[mid]<x){
                    l=mid+1;
                }else{
                    r=mid;
                }
            }
            return l;
        }
    }
}
