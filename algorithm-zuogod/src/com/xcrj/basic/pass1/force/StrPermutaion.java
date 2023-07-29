package com.xcrj.basic.pass1.force;

import java.util.ArrayList;
import java.util.List;

//字符串排列，不重复
public class StrPermutaion {
    public static void main(String[] args) {
        String str = "xxxx";
        List<String> list = new ArrayList<>();
        strPermutaion(0, str.toCharArray(), list);
        list.forEach(System.out::println);
    }

    // static int[] cnt=new int[26];
    // i之后的所有字符尝试交换到第i个位置
    public static void strPermutaion(int i, char[] cs, List<String> ls) {
        if (i == cs.length) {
            ls.add(String.valueOf(cs));
        } else {
            boolean[] cnt = new boolean[26];//i位置已经有a字符了则...
            for (int j = i; j < cs.length; j++) {
                if (cnt[cs[j] - 'a']) continue;
                cnt[cs[j] - 'a'] = true;
                swap(cs, i, j);
                strPermutaion(i + 1, cs, ls);
                swap(cs, i, j);
            }
        }
    }

    public static void swap(char[] cs, int i, int j) {
        char tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
    }
}
