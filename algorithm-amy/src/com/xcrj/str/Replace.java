package com.xcrj.str;

/**
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
 * 替换空格 将空格替换为%20
 * 其实很多数组填充类的问题，先扩充空间，再倒序填充
 */
public class Replace {
    public String replaceSpace(String s) {
        //扩容
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == ' ') {//遇到1个空格填充2个空格，总共3个空格
                sb.append("  ");
            }
        }
        //拼接
        String s2 = s + sb.toString();
        char[] cs2 = s2.toCharArray();
        for (int i = cs.length - 1, j = cs2.length - 1; i > -1; i--, j--) {
            if (cs[i] == ' ') {
                cs2[j--] = '0';
                cs2[j--] = '2';
                cs2[j] = '%';
            } else {
                cs2[j] = cs[i];
            }
        }
        return String.valueOf(cs2);
    }
}