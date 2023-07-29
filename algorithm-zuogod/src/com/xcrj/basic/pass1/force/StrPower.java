package com.xcrj.basic.pass1.force;

import java.util.ArrayList;
import java.util.List;

//字符串幂集
public class StrPower {
    public static void main(String[] args) {
        String str="xcrj";
        strPower(0, str, new char[str.length()]);
        list.forEach(System.out::println);
        list.clear();
        strPower2(0,str.toCharArray());
        list.forEach(System.out::println);
    }
    static List<String> list=new ArrayList<>();
    public static void strPower(int i,String str,char[]cs) {
        if(i==str.length()){
            list.add(String.valueOf(cs));
        }else{
            cs[i]=str.charAt(i);
            strPower(i+1,str,cs);
            cs[i]=0;//
            strPower(i+1,str,cs);
        }
    }

    //直接在原字符串上操作
    public static void strPower2(int i,char[]cs) {
        if(i==cs.length){
            list.add(String.valueOf(cs));
        }else{
            strPower2(i+1,cs);
            char tmp=cs[i];
            cs[i]=0;
            strPower2(i+1, cs);
            cs[i]=tmp;
        }
    }
}
