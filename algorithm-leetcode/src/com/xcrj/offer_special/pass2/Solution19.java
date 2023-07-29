package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 019. 最多删除一个字符得到回文
 * - 非空字符串s
 * - 从字符串中删除一个字符(删除0个字符或1个字符)
 * - 能否得到一个回文字符串。
 */
public class Solution19 {
    /**
     * 双指针相向移动+跳过不相等字符
     * 
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                // 跳过不相等字符
                return validPalindrome(s, l + 1, r) || validPalindrome(s, l, r - 1);
            }
        }
        return true;
    }

    /**
     * 判断s的start和end的子字符串是否为回文串
     * 
     * @param s
     * @param start
     * @param end
     * @return
     */
    public boolean validPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}