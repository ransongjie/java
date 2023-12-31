package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 097. 子序列的数目
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 分析：
 * - s删除n个元素后等于t，n可以为0。
 * - t作为s的子序列在s中出现的次数
 * - s[i:]的子序列只可以删除部分元素后形成的序列
 */
public class Solution97 {
    /**
     * dp[s[i:]子串][t[j:]子串] = 在s[i:]的子序列中t[j:]出现的次数
     *
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     *
     *  动态规划数组：dp[i][j]表示在s[i:]的子序列中t[j:]出现的次数
     *
     * 状态转移方程：
     * - s[i]=t[j]时，dp[j][j]=s[i:]的子序列中t[j:]出现的出现次数=s[i:]包含s[i]的子串等于t[j:]的次数+s[i:]不包含s[i]的子串等于t[j:]的次数
     * - s[i]!=t[j]时，包含s[i]的s的子串一定不等于t[j:]，考虑更短的s子串，考虑dp[i+1][j]，即在s[i+1:]的子序列中t[j:]出现的次数
     *
     * <p>
     * ！解法套路
     * 1. 定义动态规划数组
     * 2. 动态规划数组的边界条件
     * 3. 动态规划数组的状态转移方程
     * 4. 使用滚动数组进行空间压缩
     */
    public int numDistinct(String s, String t) {
        // s.len<t.len，s的子序列中一定不存在t
        if (s.length() < t.length()) {
            return 0;
        }

        // dp[i][j]表示在s[i:]的子序列中t[j:]出现的次数
        //当i=s.len时，s[i:]为空串，空串不是非空字符串的子串，dp[s.len][j]=0
        // +1,空串，s[s.len:]，存储在最后位置
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // 当j=t.len时，t[j:]为空串，空串是任何字符串的子串，dp[i][t.len]=1
        // =s.length(),空串
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }

        /**
         * 当i<s.len&&j<t.len时
         * 倒序，dp[s[i:]子串][t[j:]子串] = 在s[i:]的子序列中t[j:]出现的次数
         * -1，空串在上面已经处理
         * =0，整个s字符串
         */
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                //s[i]=t[j]时，dp[j][j]，s[i:]的子序列中t[j:]出现的出现次数=s[i:]包含s[i]的子串等于t[j:]的次数+s[i:]不包含s[i]的子串等于t[j:]的次数
                //- 若s的子串包含s[i]，已知t[j:]包含t[j]=s[i]，s[i:]和t[j:]固定了头部字符，比较剩下的字符即可，则dp[i][j]=dp[i+1][j+1]
                //- 若s的子串不包含s[i]，看更短的s子串中含有t[j:]的次数，dp[i][j]=d[i+1][j]
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                }

                // s[i]!=t[j]时，包含s[i]的s的子串一定不等于t[j:]，考虑更短的s子串，考虑dp[i+1][j]，即在s[i+1:]的子序列中t[j:]出现的次数
                //
                else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        // 倒序，所以0是结果
        return dp[0][0];
    }
}
