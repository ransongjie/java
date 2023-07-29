package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 096. 字符串交织
 * 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。
 * 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干子串
 * 
 * 输入，s1, s2, s3
 * s1和s2的子串交叉=s3，子串可以是空串
 * 输出，s1和s2的交织能否构成s3
 */
public class Solution96 {
    // 动态规划，动态规划数组（边界状态），状态转移方程
    // xcrj 动态规划，记录了不同阶段的状态
    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;

        // fss[i][j]表示s1的前i个元素，s2的前j个元素 能否构成s3的前i+j个元素
        // xcrj 为什么要构建这样的动态规划数组，最后状态是s1+s2=s3，因此先从他们的子串开始
        boolean[][] fss=new boolean[s1.length()+1][s2.length()+1];

        // 边界状态。空字符串
        fss[0][0]=true;

        // 状态转移方程。
        // s1在前，fss[i][j]=fss[i][j]||(s1的第i个字符是否等于s3的第i+j个字符&&前一个状态)
        // s2在前，fss[i][j]=fss[i][j]||(s2的第j个字符是否等于s3的第i+j个字符&&前一个状态)
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                if(i>0) fss[i][j]=fss[i][j]||(s1.charAt(i-1)==s3.charAt(i+j-1)&&fss[i-1][j]);
                if(j>0) fss[i][j]=fss[i][j]||(s2.charAt(j-1)==s3.charAt(i+j-1)&&fss[i][j-1]);
            }
        }

        return fss[s1.length()][s2.length()];
    }

    /**
     * 动态规划+滚动数组
     * xcrj 只有前后相关性 就可以使用滚动数组
     * 滚动数组
     * - 数组f的第i行只和第i−1行相关,去掉i这一维度,使用滚动方式代替行
     * - 滚动，第i行取决于第i-1行，等式右侧的f[j]是代表f[s1的前i-1个元素][s2的前j个元素]，左侧f[j]代表f[s1的前i个元素][s2的前j个元素]
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;

        boolean[] fs=new boolean[s2.length()+1];

        fs[0]=true;

        // xcrj 先处理j再处理i，先处理列再处理行
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                // fs[j]=fss[i-1][j]
                if(i>0) fs[j]=(s1.charAt(i-1)==s3.charAt(i+j-1)&&fs[j]);
                if(j>0) fs[j]=fs[j]||(s2.charAt(j-1)==s3.charAt(i+j-1)&&fs[j-1]);
            }
        }

        return fs[s2.length()];
    }
}
