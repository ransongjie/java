package com.xcrj.company.pass1;

import java.util.Arrays;

/**
 * 最长无重复字符的子串长度
 */
public class A005MaxLongestSubStr {
    /**
     *
     * @param str 字符串
     * @return
     */
    public static int longestSubStr(String str){
        char[]cs=str.toCharArray();
        int[]asciis=new int[256];//ascii [0,255]。asciis[i字符]=上次出现的位置
        Arrays.fill(asciis,-1);//初始条件 认为所有字符都在-1位置

        //第0位置的字符
        int ans=1;
        int pre=1;//前1个字符往左推的最大长度
        asciis[cs[0]]=0;

        for (int i = 1; i < cs.length; i++) {
            //两个条件
            int c1=i-asciis[cs[i]];//第i个字符上次出现的位置
            int c2=pre+1;//第i-1个字符往左推的最大长度+1(当前字符)
            int cur=Math.min(c1,c2);//当前字符往左推的最大长度
            ans=Math.max(ans,cur);
            //准备处理下一个字符
            pre=cur;
            asciis[cs[i]]=i;
        }

        return ans;
    }
}
