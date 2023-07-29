package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 096. 字符串交织
 * 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。
 * 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * - s = s1 + s2 + ... + sn
 * - t = t1 + t2 + ... + tm
 * - |n - m| <= 1
 * - 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 */
public class Solution96 {
    /**
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     * <p>
     * 多阶段化单阶段：f[s1的前i个元素][s2的前j个元素]=能否组成s3的前i+j个元素
     *
     * <p>
     * 状态转移方程
     * - 定义f[i][j]表示s1的前i个元素和s2的前j个元素是否能交织组成s3的前i+j个元素，f[s1的前i个元素][s2的前j个元素]=能否组成s3的前i+j个元素
     * - 若s1[i-1]=s3[i+j-1]，则f[i][j]取决于f[i-1][j]，s1[0到i]和s2[0到j]能否交织组成s3[0到i+j] 取决于 s1[0到i-1]和s2[0到j]能否交织组成s3[0到i+j-1]
     * - 若s2[j-1]=s3[i+j-1]，则f[i][j]取决于f[i][j-1]，s1[0到i]和s2[0到j]能否交织组成s3[0到i+j] 取决于 s1[0到i]和s2[0到j-1]能否交织组成s3[0到i+j-1]
     */
    public boolean isInterleave1(String s1, String s2, String s3) {
        // 长度不一致，s1和s3一定不能组成s3
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // 定义f[i][j]表示s1[前i个元素]和s2[前j个元素]是否能交织组成s3[前i+j个元素]
        // f[s1的前i个元素][s2的前j个元素]=能否组成s3的前i+j个元素
        // +1，因为初始f[0][0]=true
        boolean[][] f = new boolean[s1.length() + 1][s2.length() + 1];
        // 初始状态是没有元素的，认为s1[前0个元素]和s2[前0个元素]能够交织组成s3[前0个元素]
        f[0][0] = true;
        // =,f[s1的前i个元素][s2的前j个元素]=能否组成s3的前i+j个元素，前i个元素，前s1.len个元素就是取s1整个字符串
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                // 处理s1的下一个元素
                // s1[i-1]可能与s3[i+j-1]相等，s1的第i个元素可能和s3的第i+j个元素相等
                if (i > 0) {
                    // 若s1[i-1]=s3[i+j-1]，则f[i][j]取决于f[i-1][j]，s1[前i个元素]和s2[前j个元素]能否交织组成s3[前i+j个元素] 取决于 s1[前i-1个元素]和s2[前j个元素]能否交织组成s3[前i+j-1个元素]
                    f[i][j] = f[i][j] || (s1.charAt(i - 1) == s3.charAt(i + j - 1) && f[i - 1][j]);
                }

                // 处理s2的下一个元素
                // s2[j-1]可能与s3[i+j-1]相等，s2的第j个元素可能和s3的第i+j个元素相等
                if (j > 0) {
                    // 若s2[j-1]=s3[i+j-1]，则f[i][j]取决于f[i][j-1]，s1[前i个元素]和s2[前j个元素]能否交织组成s3[前i+j个元素] 取决于 s1[前i个元素]和s2[前j-1个元素]能否交织组成s3[前i+j-1个元素]
                    f[i][j] = f[i][j] || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && f[i][j - 1]);
                }
            }
        }

        return f[s1.length()][s2.length()];
    }

    /**
     * 动态规划+滚动数组
     *
     * 滚动数组
     * - 数组f的第i行只和第i−1行相关,使用滚动方式代替行
     * - 滚动，第i行取决于第i-1行，等式右侧的f[j]是代表f[s1的前i-1个元素][s2的前j个元素]，左侧f[j]代表f[s1的前i个元素][s2的前j个元素]
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        // 长度不一致，s1和s3一定不能组成s3
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // 数组f的第i行只和第i−1行相关，使用滚动方式代替行
        // 定义f[i][j]表示s1[前i个元素]和s2[前j个元素]是否能交织组成s3[前i+j个元素]
        // f[s1的前i个元素][s2的前j个元素]=能否组成s3的前i+j个元素
        // +1，因为初始f[0][0]=true
        boolean[] f = new boolean[s2.length() + 1];
        // 初始状态是没有元素的
        f[0] = true;
        // =, f[j]记录s1[前i个元素]和s2[前j个元素]是否能交织组成s3[前i+j个元素]，前s1.len个元素就是s1整个字符串
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                // 处理s1的下一个元素
                // s1[i-1]可能与s3[i+j-1]相等，s1的第i个元素可能和s3的第i+j个元素相等
                if (i > 0) {
                    // 若s1[i-1]=s3[i+j-1]，则f[i][j]取决于f[i-1][j]，s1[前i个元素]和s2[前j个元素]能否交织组成s3[前i+j个元素] 取决于 s1[前i-1个元素]和s2[前j个元素]能否交织组成s3[前i+j-1个元素]
                    // 滚动，第i行取决于第i-1行，等式右侧的f[j]是代表f[s1的前i-1个元素][s2的前j个元素]，左侧f[j]代表f[s1的前i个元素][s2的前j个元素]
                    f[j] = s1.charAt(i - 1) == s3.charAt(i + j - 1) && f[j];
                }

                // 处理s2的下一个元素
                // s2[j-1]可能与s3[i+j-1]相等，s2的第j个元素可能和s3的第i+j个元素相等
                if (j > 0) {
                    // 若s2[j-1]=s3[i+j-1]，则f[i][j]取决于f[i][j-1]，s1[前i个元素]和s2[前j个元素]能否交织组成s3[前i+j个元素] 取决于 s1[前i个元素]和s2[前j-1个元素]能否交织组成s3[前i+j-1个元素]
                    f[j] = f[j] || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && f[j - 1]);
                }
            }
        }

        return f[s2.length()];
    }
}
