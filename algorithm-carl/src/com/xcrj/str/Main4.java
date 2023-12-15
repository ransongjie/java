package com.xcrj.str;

/**
 * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * 左旋转字符串
 */
public class Main4 {
    /**
     * 反转0~n子串，反转n+1~Len字串，反转整个字符串
     * 
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        reverseString(sb, 0, n - 1);
        reverseString(sb, n, sb.length() - 1);
        return sb.reverse().toString();
    }

    /**
     * 翻转字符串
     *
     * @param sb
     * @param l
     * @param r
     */
    public void reverseString(StringBuilder sb, int l, int r) {
        char tmp;
        while (l < r) {
            tmp = sb.charAt(l);
            sb.setCharAt(l, sb.charAt(r));
            sb.setCharAt(r, tmp);
            l++;
            r--;
        }
    }
}
