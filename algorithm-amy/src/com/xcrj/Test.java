package com.xcrj;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

    }

    //List<List<String>> 转 String[][]
    public static void test1() {
        List<List<String>> lss = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        ls.add("abc");
        ls.add("defg");
        List<String> ls2 = new ArrayList<>();
        ls2.add("hij");
        ls2.add("klmn");
        lss.add(ls);
        lss.add(ls2);
        String[][] ans = new String[lss.size()][];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = lss.get(i).toArray(new String[lss.get(i).size()]);
        }
        System.out.println();
    }
}
