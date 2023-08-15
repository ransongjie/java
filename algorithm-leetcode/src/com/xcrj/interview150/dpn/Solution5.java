package com.xcrj.interview150.dpn;

/**
 * https://leetcode.cn/problems/interleaving-string
 * <p>
 * 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接
 * <p>
 * si在前，ti在后，ti在前，si在后
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * <p>
 * 相对次序不变，交错组成即可
 */
public class Solution5 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        //dp[i][j], s1的0~i-1子串, s2的0~j-1子串 能否交叉组成s3的0~i+j-1子串
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        //无无组成无
        dp[0][0] = true;

        //s1组成s3
        for (int i = 1; i < s1.length() + 1; i++) {//
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else break;//
        }

        //s2组成s3
        for (int i = 1; i < s2.length() + 1; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[0][i] = true;
            } else break;
        }

        //s1或s2组成s3
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                //s1的第i-1个字符=s3的第i+j-1个字符 && s1(0~i-2)和s2(0~j-1)可以组成s3(i+j-2)
                //s2的第j-1个字符=s3的第i+j-1个字符 && s1(0~i-1)和s2(0~j-2)可以组成s3(i+j-2)
                if (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]
                        || s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
