package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/monotone-increasing-digits/
 * 单调递增的数字
 */
public class Solution16 {
    /*
     * 998
     * 799
     */
    public int monotoneIncreasingDigits(int n) {
        String s=String.valueOf(n);
        char[]cs=s.toCharArray();
        //记录--数字的后1位索引
        int idx=cs.length;
        //从后往前遍历，前1个数字>后1个数字，前1个数字--
        for(int i=cs.length-2;i>=0;i--){
            if(cs[i]>cs[i+1]){
                cs[i]--;
                idx=i+1;
            }
        }
        //从idx开始变成9
        for(int i=idx;i<cs.length;i++){
            cs[i]='9';
        }
        return Integer.parseInt(String.valueOf(cs));
    }
}