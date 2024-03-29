package com.xcrj.str;

/**
 * https://leetcode.cn/problems/delete-operation-for-two-strings/
 * 两个字符串的删除操作
 * 删除word1或word2中任意单词，使得word1=word2的最少次数
 */
public class MinDistance1 {
    public int minDistance(String word1, String word2) {
        //dp[w1Len][w2Len]=w1删除字符且w2删除字符后，w1=w2的最少次数
        //dp[i][j]=w1[0~i-1]删除字符且w2[0~j-1]删除字符后，w1[0~i-1]=w2[0~j-1]的最少次数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                //相等则无需操作
                /**
                 * 不相等
                 * 1. 删除w1的字符且删除w2的字符，看剩下字符串是否相等，共删除2次
                 * 2. 删除w1的字符，看剩下字符串是否相等，共删除1次
                 * 3. 删除w2的字符，看剩下字符串是否相等，共删除1次
                 * 求min删除次数
                 */
                dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ?
                        dp[i - 1][j - 1] :
                        Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
