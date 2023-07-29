package com.xcrj.company.pass1;

public class A056MaxSubStrKDiffChar {
    /**
     *
     * @param s
     * @param k 最多包含k种字符
     * @return
     */
    public static int maxSubStrKDiffChar(String s, int k){
        int n=s.length();
        char[]cs=s.toCharArray();
        int ans=0;
        int[] charCnt=new int[256];
        int r=0;
        int diffCharNum=0;
        for (int l = 0; l < n; l++) {
            //！ r右移至不达标，第r个不达标 r内达标
            //不同字符数<k || (不同字符数=k&&当前字符已经被统计过)
            while(r<n&&diffCharNum<k||(diffCharNum==k&&charCnt[cs[r]]>0)){
                //是否新种类字符=以前没有统计过这个字符
                diffCharNum+=charCnt[cs[r]]==0?1:0;
                charCnt[cs[r++]]++;
            }
            //l左移一位
            ans=Math.max(ans,r-l);
            //字符种类=计数表中是否只有最后1个字符
            charCnt[cs[l]]--;
            diffCharNum-=charCnt[cs[l]]==0?1:0;
        }
        return ans;
    }
}
