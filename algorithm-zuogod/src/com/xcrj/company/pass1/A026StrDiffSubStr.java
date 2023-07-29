package com.xcrj.company.pass1;

import java.util.HashMap;

public class A026StrDiffSubStr {
    public static int strDiffSubStr(String s){
        HashMap<Character,Integer> endCharCnt=new HashMap<>();
        int all=1;//空集
        int newAdd;
        for (char c:s.toCharArray()) {
            newAdd=all;
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
            all=(temp-endCharCnt.getOrDefault(c,0)+m)%m;
            endCharCnt.put(c,newAdd);
        }
        return all;
    }
}
