package com.xcrj.basic.pass3.str;

/**
 * 字符串匹配
 * 无法全部匹配则尝试部分匹配
 */
public class KMP {
    /**
     * 构造部分匹配表
     * next[len]=i=前后缀匹配最长长度
     * @param str
     * @return
     */
    public static int[] getNext(String str){
        if(str==null) return null;
        if(str.length()==0) return new int[]{-1};
        if(str.length()==1) return new int[]{-1,0};

        char[]cs=str.toCharArray();
        //长度从0开始到len-1, 共长len
        int[]next=new int[cs.length];
        int i=0,len=0;
        next[len]=-1;
        len=1;
        next[len]=0;
        len=2;
        while(len<cs.length){
            if(cs[len-1]==cs[i]){
                /**
                 * a1 a2 a3
                 * a1 a2 横向之前比较过了
                 * a2 a3 纵向a1 a2比较过了
                 * 只需要比较a2 a3
                 */
                next[len++]=++i;
                continue;
            }
            //前后缀匹配无法达到最长，选择次长
            if(i>0){
                i=next[i];
                continue;
            }
            next[len++]=i;//i=0, len++求下一个长度的前后缀匹配最长长度
        }

        return next;
    }

    /**
     * s2在s1中的起点索引
     * @param s1 长串
     * @param s2 短串
     * @return s2在s1中，返回s2在s1中的起点索引；s2不在s1中，返回-1
     */
    public static int getIdx(String s1,String s2){
        if(s1.length()<s2.length()) return -1;
        int[]next=getNext(s2);
        char[]cs1=s1.toCharArray();
        char[]cs2=s2.toCharArray();
        int i1=0,i2=0;
        while(i1<cs1.length&&i2<cs2.length){
            if(cs1[i1]==cs2[i2]){
                i1++;
                i2++;
                continue;
            }
            //从i1开始，i1和i2无法部分匹配，i1++换1个开头
            if(next[i2]==-1){
                i1++;
                continue;
            }
            //从i1开始，i1和i2可以部分匹配
            i2=next[i2];
        }

        return i2==s2.length()?i1-i2:-1;
    }

    public static void main(String[] args) {
        int times=10000;
        int maxLen=100;
        for (int i = 0; i < times; i++) {
            Element e=getStr12(maxLen);
            int idx1=cp(e.str1, e.str2);
            int idx2=getIdx(e.str1, e.str2);
            if(idx1!=idx2){
                System.out.println("not good");
                System.out.println(e.str1);
                System.out.println(e.str2);
                throw new RuntimeException();
            }
        }
    }
    static class Element{
        String str1;
        String str2;
    }
    public static int cp(String str1,String str2) {
        return str1.indexOf(str2);
    }
    public static Element getStr12(int maxLen) {
        int len=(int)(Math.random()*(maxLen-1)+1);
        char[] cs=new char[len];
        for (int i = 0; i < len; i++) {
            //20~126的字符 ASCII
            int v=20+(int)(Math.random()*(126-20+1));
            cs[i]=(char)v;
        }
        String str1=String.valueOf(cs);

        int start=(int)(Math.random()*(cs.length));
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
}
