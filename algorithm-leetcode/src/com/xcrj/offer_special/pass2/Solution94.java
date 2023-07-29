package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 094. 最少回文分割
 * 给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 * 
 * 输入，字符串s
 * 输出，将s分割成多个回文子串的最少分割次数
 */
public class Solution94 {
    // 动态规划，动态规划数组，状态转移方程
    public int minCut(String s) {
        // s[i~j]是否为回文串
        boolean[][] palindromess=new boolean[s.length()][s.length()];
        // 单个字符也是回文串
        for(boolean[] palindromes:palindromess) Arrays.fill(palindromes, true);

        // 记录所有回文串
        for(int i=s.length()-1;i>=0;i--){
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j)&&palindromess[i+1][j-1]) palindromess[i][j]=true;
                else palindromess[i][j]=false;
            }
        }

        // fs[i],下标0~i的字符串=分割次数
        int[] fs=new int[s.length()];
        Arrays.fill(fs, Integer.MAX_VALUE);

        for(int i=0;i<s.length();i++){
            // s[0]~s[i]都是回文串，不需要分割
            if(palindromess[0][i]) fs[i]=0;
            // 需要分割
            else{
                // s[0]~s[i]的每个字符都尝试分割
                for(int j=0;j<i;j++){
                    // 0~j,j+1~i
                    if(palindromess[j+1][i]){
                        // min(旧值，0~j切的最小次数上 现在要再切一刀)
                        fs[i]=Math.min(fs[i],fs[j]+1);
                    }
                }
            }
        }

        return fs[s.length()-1];
    }
}
