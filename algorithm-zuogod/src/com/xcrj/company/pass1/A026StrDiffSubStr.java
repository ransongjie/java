package com.xcrj.company.pass1;

import java.util.HashMap;

/**
 * 不同非空子序列的个数
 * 子序列可不连续
 * 类似全排列，去重
 *
 * https://leetcode.cn/problems/distinct-subsequences-ii
 *
 * 例如
 * aba
 * 6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"
 *
 */
public class A026StrDiffSubStr {
    public static int strDiffSubStr(String s){
        HashMap<Character,Integer> endCharCnt=new HashMap<>();
        int all=1;//空集
        int newAdd;
        for (char c:s.toCharArray()) {
            newAdd=all;
            //规律，-, 去重操作，重复的部分是之前以这个字符结尾的字符串
            all=all+newAdd-endCharCnt.getOrDefault(c,0);
            endCharCnt.put(c,newAdd);
        }
        return all;
    }

    public static int strDiffSubStr2(String s){
        HashMap<Character,Integer> endCharCnt=new HashMap<>();
        int m=1000000007;//leetcode原题要求 防止溢出 取模
        int all=1;//空集
        int newAdd,temp;
        for (char c:s.toCharArray()) {
            newAdd=all;
            temp=(all+newAdd)%m;
            //A-B可能小于0，为了获得正数结果，所以先+m
            all=(temp-endCharCnt.getOrDefault(c,0)+m)%m;
            endCharCnt.put(c,newAdd);
        }
        return all;
    }
}
