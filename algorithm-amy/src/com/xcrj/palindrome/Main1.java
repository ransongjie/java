package com.xcrj.palindrome;

/**
 * 双指针
 * https://leetcode.cn/problems/XltzEq/description/
 * s是否为回文串
 * - 只考虑字母和数字字符
 * - 忽略字母的大小写
 */
public class Main1 {
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            //跳过非字母和数字
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            //忽略大小写
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }
}
