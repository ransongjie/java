package com.xcrj.company.pass1;

/**
 * AB字符串（相对次序不变）能否交错组成字符串C
 */
public class A011BoolStr12Sum {
    /**
     *
     * @param s1
     * @param s2
     * @param s3 目标字符串
     * @return
     */
    public static boolean str12Sum(String s1,String s2,String s3){
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        //dp[i][j], s1的0~i-1子串, s2的0~j-1子串 能否交叉组成s3的0~i+j-1子串
        boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        dp[0][0]=true;
        for (int i = 1; i < s1.length()+1; i++) {//
            if(s1.charAt(i-1)==s3.charAt(i-1)){
                dp[i][0]=true;
            }else break;//
        }
        for (int i = 1; i < s2.length()+1; i++) {
            if(s2.charAt(i-1)==s3.charAt(i-1)){
                dp[0][i]=true;
            }else break;
        }

        for (int i = 1; i < s1.length()+1; i++) {
            for (int j = 1; j < s2.length()+1; j++) {
                if(s1.charAt(i-1)==s3.charAt(i+j-1)&&dp[i-1][j]
                ||s2.charAt(j-1)==s3.charAt(i+j-1)&&dp[i][j-1]){
                    dp[i][j]=true;
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
