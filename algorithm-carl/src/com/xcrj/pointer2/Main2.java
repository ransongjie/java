package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
 * 替换空格
 * 其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。
 */
public class Main2 {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        // 遇到1个空格往sb中放入2个空格
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("  ");
            }
        }
        // 将sb拼接到s后面。先在数组后面扩充空间，再倒序填充
        String str = s + sb.toString();
        char[] cs = str.toCharArray();
        int fs = s.length() - 1, ss = cs.length - 1;
        while (fs >= 0) {
            if (s.charAt(fs) == ' ') {
                cs[ss--] = '0';
                cs[ss--] = '2';
                cs[ss] = '%';
            } else {
                cs[ss] = s.charAt(fs);
            }
            fs--;
            ss--;
        }
        return String.valueOf(cs);
    }
}
