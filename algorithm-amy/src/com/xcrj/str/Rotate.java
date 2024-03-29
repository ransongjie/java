package com.xcrj.str;

/**
 * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * 左旋转字符串
 */
public class Rotate {
    public String dynamicPassword(String password, int target) {
        return reverseLeftWords(password, target);
    }

    /**
     * 反转0~n子串，反转n+1~Len字串，反转整个字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        reverse(sb, 0, n - 1);
        reverse(sb, n, sb.length() - 1);
        reverse(sb, 0, sb.length() - 1);
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int s, int e) {
        while (s < e) {
            char tmp = sb.charAt(s);
            sb.setCharAt(s, sb.charAt(e));
            sb.setCharAt(e, tmp);
            s++;
            e--;
        }
    }
}
