package com.xcrj.palindrome;

/**
 * https://leetcode-cn.com/problems/RQku0D
 * 最多删除一个字符得到回文
 * - 非空字符串s
 * - 从字符串中删除一个字符(删除0个字符或1个字符)
 * - 能否得到一个回文字符串。
 */
public class Main2 {
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                //跳过1次不相等的字符
                return f(s, l + 1, r) || f(s, l, r - 1);
            }
        }
        return true;
    }

    //s的l~r是否回文串
    private boolean f(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
