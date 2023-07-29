package com.xcrj.company.pass1;

/**
 * 给定字符串在字典中的位置
 */
public class A058DictOrder {

    /**
     * cdb，总共长度为7，请问cdb是第几个？
     * 第1位c
     * + 以a开头，剩下长度为(0~6)的所有可能性有几个
     * + 以b开头，剩下长度为(0~6)的所有可能性有几个
     * + 以c开头，剩下长度为(0)的所有可能性有1个
     * 第2位d
     * + 以ca开头，剩下长度为(0~5)的所有可能性有几个
     * + 以cb开头，剩下长度为(0~5)的所有可能性有几个
     * + 以cc开头，剩下长度为(0~5)的所有可能性有几个
     * + 以cd开头，剩下长度为(0)的所有可能性有1个
     * @param s
     * @param len
     * @return
     */
    public static int dictOrder(String s,int len){
        if(s==null||s.length()==0||s.length()>len){
            return -1;
        }
        int ans=0;
        char[]cs=s.toCharArray();
        for (int i = 0,restLen=cs.length-1; i < cs.length; i++,restLen--) {
            //例如，c
            //以a开头，f(6), 剩下长度为(0~6)的所有可能性有几个
            //以b开头，f(6), 剩下长度为(0~6)的所有可能性有几个
            //以c开头，剩下长度为(0)的所有可能性有1个
            ans+=(cs[i]-'a')*f(restLen)+1;
        }
        return ans;
    }

    /**
     * 26^0
     * 26^1
     * ...
     * 26^restLen
     * 从上到下字典序越来越大
     * @param restLen 剩余长度
     * @return
     */
    private static int f(int restLen){
        int ans=1;//0~restlen
        for (int i = 0,b=26; i < restLen; i++,b*=26) {
            ans+=b;
        }
        return ans;
    }
}
