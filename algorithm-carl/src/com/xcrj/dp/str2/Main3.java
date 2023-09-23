package com.xcrj.dp.str2;

/**
 * https://leetcode.cn/problems/delete-operation-for-two-strings/
 * 两个字符串的删除操作
 */
public class Main3 {
    // 删除word1或word2中任意单词，使得word1=word2的最少次数
    public int minDistance(String word1, String word2) {
        //dp[i][j]=长度为i的word1的子数组 要等于 长度为j的word2的子数组 的最少的删除次数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }
        dp[0][0] = 0;

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                // 相等则不用删除，不等则可以1删除word1[i-1] 或 删除word2[j-1] 或 都删除
                dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ?
                        dp[i - 1][j - 1] :
                        Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 2));
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
