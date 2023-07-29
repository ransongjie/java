package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 002. 二进制加法
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * 进制加法：
 * 二进制逢二进一，商为进位，余数为当前值
 */
public class Solution2 {

    /**
     * 错误写法
     */
    public static String addBinary(String a, String b) {
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        if (as[0] == '0') {
            return String.valueOf(bs);
        }
        if (bs[0] == '0') {
            return String.valueOf(as);
        }

        int len = a.length() > b.length() ? a.length() + 1 : b.length() + 1;
        // char默认值‘u0000’(数值0)，如果最高位为0表示没有进位
        char[] result = new char[len];
        for (int i = 0; i < result.length; i++) result[i] = '0';
        int r = len - 1;

        // ‘0’ ASCII码 48，'1' ASCII码 49
        for (int i = as.length - 1, j = bs.length - 1; i >= 0 && j >= 0; i--, j--, r--) {
            // ‘2’ ASCII码 50
            // 余数为值
            result[r] = (char) (((int) result[r] + (int) as[i] + (int) bs[j]) % 50);
            // 商为进位
            result[r - 1] = (char) (((int) as[i] + (int) bs[j]) / 50);
        }

        return String.valueOf(result);
    }

    /**
     * 内存消耗过高
     */
    public static String addBinary2(String a, String b) {
        // 特殊情况处理
        char[] ats = a.toCharArray();
        char[] bts = b.toCharArray();
        if (ats[0] == '0') {
            return String.valueOf(bts);
        }
        if (bts[0] == '0') {
            return String.valueOf(ats);
        }

        // 保证字符长度一致
        char[] as = null;
        if (ats.length < bts.length) {
            as = new char[bts.length];
            for (int i = 0; i < bts.length; i++) {
                int c = bts.length - ats.length;
                if (i < c) {
                    as[i] = '0';
                } else {
                    as[i] = ats[i - c];
                }
            }
        } else {
            as = ats;
        }
        char[] bs = null;
        if (bts.length < ats.length) {
            bs = new char[ats.length];
            for (int i = 0; i < ats.length; i++) {
                int c = ats.length - bts.length;
                if (i < c) {
                    bs[i] = '0';
                } else {
                    bs[i] = bts[i - c];
                }
            }
        } else {
            bs = bts;
        }

        // 字符转数字
        int[] ais = new int[as.length];
        for (int i = 0; i < as.length; i++) {
            switch (as[i]) {
                case '0':
                    ais[i] = 0;
                    break;
                case '1':
                    ais[i] = 1;
                    break;
            }
        }
        int[] bis = new int[bs.length];
        for (int i = 0; i < bs.length; i++) {
            switch (bs[i]) {
                case '0':
                    bis[i] = 0;
                    break;
                case '1':
                    bis[i] = 1;
                    break;
            }
        }

        // 结果
        int len = as.length + 1;
        int k = len - 1;
        int[] rs = new int[len];
        for (int i = 0; i < rs.length; i++) rs[i] = 0;

        // 2进制运算，逢二进一
        for (int i = ais.length - 1, j = bis.length - 1; i >= 0 && j >= 0; i--, j--, k--) {
            // 商为进位
            rs[k - 1] = (rs[k] + ais[i] + bis[j]) / 2;
            // 余数为值
            rs[k] = (rs[k] + ais[i] + bis[j]) % 2;
        }

        // 最高位为0表示没有进位
        int m = 0;
        if (rs[0] == 0) {
            m = 1;
        }
        StringBuilder result = new StringBuilder(10);
        for (int i = 0; m < rs.length; m++, i++) {
            switch (rs[m]) {
                case 0:
                    result.append('0');
                    break;
                case 1:
                    result.append('1');
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary2("1", "111"));
    }
}
