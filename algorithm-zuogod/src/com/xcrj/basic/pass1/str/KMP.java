package com.xcrj.basic.pass1.str;

public class KMP {
    public static void main(String[] args) {
        // String str1="abcabc";
        // String str2="cabc";
        // System.out.println(getIdx(str1, str2));

        int times=1000000;
        int maxLen=100;
        for (int i = 0; i < times; i++) {
            Element e=getStr12(maxLen);
            int idx1=cp(e.str1, e.str2);
            int idx2=getIdx(e.str1, e.str2);
            if(idx1!=idx2){
                System.out.println("not good");
                System.out.println(e.str1);
                System.out.println(e.str2);
            }
        }
    }

    public static int getIdx(String str1,String str2) {
        if(str1==null||str2==null||str2.length()<1||str2.length()>str1.length()) return -1;

        int[] next=getNext(str2);
        //
        char[]cs1=str1.toCharArray();
        char[]cs2=str2.toCharArray();
        int i1=0,i2=0;
        while(i1<str1.length()&&i2<str2.length()){
            // if(cs1[i1++]==cs2[i2++]) {//继续比较
            if(cs1[i1]==cs2[i2]){
                i1++;i2++;
                continue;
            }
            if(next[i2]==-1){//等价于i2==0. 换个开头
                i1++;
                continue;
            }
            i2=next[i2];
        }
        //
        return i2==str2.length()?i1-i2:-1;//
    }

    //构造部分匹配表
    private static int[] getNext(String str) {
        if(str.length()==1) return new int[]{-1};
        //
        char[] cs=str.toCharArray();
        //长度为i的子串的最长部分匹配长度
        int[] next=new int[str.length()];
        next[0]=-1;
        next[1]=0;
        int j=2;//i
        int i=0;//cn
        while(j<next.length){
            if(cs[i]==cs[j-1]){
                next[j++]=++i;
                continue;
            }
            if(i>0){
                i=next[i];
                continue;
            }
            next[j++]=0;
        }
        return next;
    }

    static class Element{
        String str1;
        String str2;
    }

    public static Element getStr12(int maxLen) {
        int len=(int)(Math.random()*maxLen);
        while(len==0){
            len=(int)(Math.random()*maxLen);
        }
        //
        char[] cs=new char[len];
        for (int i = 0; i < len; i++) {
            //20~126的字符 ASCII
            int v=20+(int)(Math.random()*(126-20+1));
            char c=(char)v;
            cs[i]=c;
        }
        //
        String str1=String.valueOf(cs);
        int start=(int)(Math.random()*(cs.length));//
        int end=(int)(Math.random()*(cs.length+1));
        while(!(end>start)){
            end=(int)(Math.random()*(cs.length+1));//
        }
        String str2=String.copyValueOf(cs, start, (end-start));

        Element e=new Element();
        e.str1=str1;
        e.str2=str2;
        return e;
    }

    public static int cp(String str1,String str2) {
        return str1.indexOf(str2);
    }
}
