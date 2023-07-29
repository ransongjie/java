package com.xcrj.offer_special.pass3;

/**
 * 剑指 Offer II 019. 最多删除一个字符得到回文
 * - 从字符串中最多删除一个字符(删除0个字符或1个字符)
 * - 能否得到一个回文字符串
 */
public class Solution19 {
    /**
     * 双指针相向移动
     * - 相等继续移动
     * - 不相等跳过左字符判断相等回文串||跳过右字符判断回文串
     */
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return (validPalindrome(s, i + 1, j) || validPalindrome(s, i, j - 1));
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
