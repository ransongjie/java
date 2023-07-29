package com.xcrj.offer_special.pass4;

/**
 * 字符串中的变位词
 * 第一个字符串的排列之一是第二个字符串的子串
 */
public class Solution014 {
    /**
     * cnts[26]统计+窗口
     * @param s1 短
     * @param s2 长
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int i=0,j=0,m=s1.length(),n=s2.length();
        int[]cnts=new int[26];
        char[]cs1=s1.toCharArray(),cs2=s2.toCharArray();
        if(m>n){
            return false;
        }
        //s1操作cnts[]为负数
        for (char c :
                cs1) {
            cnts[c - 'a']--;
        }
        //s2操作cnts[]归0
        //先多，再尽量少
        while(j<n){
            cnts[cs2[j]-'a']++;
            while(i<=j&&cnts[cs2[j]-'a']>0){
                cnts[cs2[i]-'a']--;
                i++;
            }
            if(j-i+1==m){
                return true;
            }
            j++;
        }
        return false;
    }
}
