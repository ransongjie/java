package com.xcrj.str;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 * 反转字符串中的单词
 * "the sky is blue" 》 "blue is sky the"
 */
public class Reverse3 {
    /**
     * 1.去除首尾以及中间多余空格
     * 2.翻转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverse(sb, 0, sb.length() - 1);
        reverseWord(sb);
        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int l = 0, r = cs.length - 1;
        //去除首位空格
        while (cs[l] == ' ') l++;
        while (cs[r] == ' ') r--;
        //去除中间多余空格，中间只保留1个空格
        for (int i = l; i <= r; i++) {
            if (cs[i] != ' ') {//字母
                sb.append(cs[i]);
            } else if (sb.charAt(sb.length() - 1) != ' ') {//保留1个空格
                sb.append(cs[i]);
            }
        }
        return sb;
    }

    //闭区间
    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start++;
            end--;
        }
    }

    private void reverseWord(StringBuilder sb) {
        int n = sb.length(), s = 0, e = 0;
        //找到每个单词的开始和结尾
        while (e < n) {
            //找到单词的结尾
            while (e < n && sb.charAt(e) != ' ') {
                e++;
            }
            //反转单词
            reverse(sb, s, e - 1);

            e = e + 1;
            s = e;
        }
    }
}
