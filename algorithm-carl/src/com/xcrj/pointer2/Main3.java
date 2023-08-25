package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 * 反转字符串中的单词
 */
public class Main3 {
    /**
     * 1.去除首尾以及中间多余空格
     * 2.翻转这个字符串
     * 3.反转各个单词
     */
    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverseString(sb, 0, sb.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }

    /**
     * 去除首尾以及中间多余空格
     *
     * @param s
     * @return
     */
    private StringBuilder removeSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        int n = cs.length, l = 0, r = n - 1;
        // 去掉了首尾的空格
        // 先尽可能过滤掉不需要的元素
        while (cs[l] == ' ') l++;
        while (cs[r] == ' ') r--;
        // 去掉中间空格
        // 不是空格则添加元素，是空格且sb末尾不是空格则添加空格
        for (int i = l; i <= r; i++) {
            if (cs[i] != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(cs[i]);
            }
        }
        return sb;
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

    private void reverseEachWord(StringBuilder sb) {
        int n = sb.length(), s = 0, e = 1;//至少1个字符
        while (s < n) {
            // 寻找字符串结束位置
            while (e < n && sb.charAt(e) != ' ') {
                e++;
            }
            reverseString(sb, s, e - 1);// e是空格
            s = e + 1;
            e = e + 2;//至少1个字符
        }
    }
}
