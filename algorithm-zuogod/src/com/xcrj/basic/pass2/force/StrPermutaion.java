package com.xcrj.basic.pass2.force;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列，不重复
 * 把从i到size的元素依次放到第i个位置
 */
public class StrPermutaion {
    public static void main(String[] args) {
        String str = "xxxx";
        List<String> list = new ArrayList<>();
        strPermutaion(0, str.toCharArray(), list);
        list.forEach(System.out::println);
    }
    public static void strPermutaion(int i,char[]cs,List<String> ls) {
        if(i==cs.length){
            ls.add(String.valueOf(cs));
        }else{
            boolean[] bs=new boolean[26];
            // for(int j=i+1;j<cs.length;j++){
            for(int j=i;j<cs.length;j++){//自身原本就是一个排列
                if(bs[cs[j]-'a']) continue;
                bs[cs[j]-'a']=true;
                swap(cs,i,j);
                strPermutaion(i+1, cs, ls);
                swap(cs,i,j);
            }
        }
    }

    public static void swap(char[]cs,int i,int j) {
        char tmp=cs[i];
        cs[i]=cs[j];
        cs[j]=tmp;
    }
}
