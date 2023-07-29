package com.xcrj.basic.pass2.force;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串幂集
 */
public class StrPower {
    public static void main(String[] args) {
        String str="xcrj";

        strPower(0, str, new char[str.length()]);
        ls.forEach(System.out::println);

        ls.clear();
        System.out.println("================");

        strPower2(0,str.toCharArray());
        ls.forEach(System.out::println);
    }
    static List<String> ls=new ArrayList<>();
    public static void strPower(int i,String str,char[]cs) {
        if(i==str.length()){
            ls.add(String.valueOf(cs));
        }else{
            cs[i]=str.charAt(i);
            strPower(i+1, str, cs);
            cs[i]=0;//
            strPower(i+1, str, cs);
        }
    }

    public static void strPower2(int i,char[]cs) {
        if(i==cs.length){
            ls.add(String.valueOf(cs));
        }else{
            strPower2(i+1, cs);
            char tmp=cs[i];
            cs[i]=0;
            strPower2(i+1, cs);
            cs[i]=tmp;
        }
    }
}
