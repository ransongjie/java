package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 019. 最多删除一个字符得到回文
 * 最多删除1个字符，0个或1个字符
 * s由全小写英文字母构成
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 */
public class Solution19 {
    /**
     * 双指针+递归思想
     */
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        // 最多删除1个字符，0个或1个字符
        int count = 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                // l或r指向字符可能是特殊字符
                return validPalindrome(s, l + 1, r) || validPalindrome(s, l, r - 1);
            }
        }
        return true;
    }

    /**
     * 如果l和r有1个指向特殊字符
     * 情况1：l指向特殊字符，则s[l+1,r] 剩下的是不是回文数
     * 情况2：r指向特殊字符，则s[l,r-1] 剩下的是不是回文数
     *
     * @param s
     * @param start 字符串开始指针
     * @param end   字符串结束指针
     */
    public boolean validPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution19 solution19 = new Solution19();
        System.out.println(solution19.validPalindrome("eeccccbebaeeabebccceea"));
    }
}
