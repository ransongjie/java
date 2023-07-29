package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 018. 有效的回文串，含有其他字符
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 */
public class Solution18 {

    /**
     * 双指针相向移动
     */
    public boolean isPalindrome1(String s) {
        int l = 0, r = s.length() - 1;
        // ！！！双指针相向移动模板
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (l < r) {
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
                l++;
                r--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution18 solution18 = new Solution18();
        System.out.println(solution18.isPalindrome1("A--a"));
    }
}
