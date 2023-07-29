package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 018. 有效的回文
 * - s是否为回文串
 * - 只考虑字母和数字字符
 * - 忽略字母的大小写。
 */
public class Solution18 {
    /**
     * 双指针相向移动
     * 
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            // 只考虑字母和数字字符
            // xcrj Character.isLetterOrDigit
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            // 忽略字母的大小写。
            // xcrj Character.toLowerCase
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