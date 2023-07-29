package com.xcrj.basic.pass1.dp;
/**
 * Bob存活概率
 * 5x6格子
 * 随机放到(a,b)
 * 上下左右随机等概率走k步
 * 越界即死亡
 * 生存方法数/4^k=生存概率
 */
public class WayPBobSurvial {
    public static void main(String[] args) {
        int M=9;
        int N=3;
        int a=8;
        int b=0;
        int k=3;
        System.out.println(wayPBobSurvial(M, N, a, b, k));
        System.out.println(wayPBobSurvial3(M, N, a, b, k));
        System.out.println(wayPBobSurvial4(M, N, a, b, k));

        // int times=10000;
        // int maxV=10;
        // for (int i = 0; i < times; i++) {
        //     Element element=getE(maxV);
        //     String p1=wayPBobSurvial(element.M, element.N, element.a, element.b, element.k);
        //     String p2=wayPBobSurvial4(element.M, element.N, element.a, element.b, element.k);
        //     if(!p1.equals(p2)){
        //         System.out.println("not good");
        //         System.out.println(element.M+", "+element.N
        //         +", "+element.a+", "+element.b+", "+element.k);
        //         System.out.println(p1);
        //         System.out.println(p2);
        //         throw new RuntimeException();
        //     }
        // }
    }

    /**
     * 暴力递归
     * 目标
     * 尝试
     */
    public static String wayPBobSurvial(int M,int N,int a,int b,int k) {
        long all=(long)Math.pow(4, k);
        long alive=pBobSurvial(M,N,a,b,k);
        long g=gcd(all, alive);
        return String.valueOf(alive/g)+"/"+String.valueOf(all/g);
    }

    public static long pBobSurvial(int M,int N,int a,int b,int k) {
        if(a==M||a<0||b==N||b<0||k<0) return 0L;
        if(k==0){
            return 1L;
        }

        return (long)pBobSurvial(M, N, a+1, b, k-1)
              +pBobSurvial(M, N, a-1, b, k-1)
              +pBobSurvial(M, N, a, b+1, k-1)
              +pBobSurvial(M, N, a, b-1, k-1);
    }

    /**
     * 最大公约数
     * 辗转相除法 gcd(a,b)=gcd(b,a%b), param2=0 return param1
     */
    private static long gcd(long a,long b) {
        return b==0?a:gcd(b,a%b);
    }
    /**
     * 动态规划
     * 拷贝暴力递归
     * 目标位置
     * 初始状态
     * 状态转移
     */
    public static String wayPBobSurvial3(int M,int N,int a,int b,int k) {
        long all=(long)Math.pow(4, k);
        long alive=pBobSurvial3(M,N,a,b,k);
        long g=gcd(all, alive);
        return String.valueOf(alive/g)+"/"+String.valueOf(all/g);
    }

    public static long pBobSurvial3(int M,int N,int a,int b,int k) {
        if(a==M||a<0||b==N||b<0||k<0) return 0L;
        long[][][] dp=new long[k+1][M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[0][i][j]=1L;
            }
        }

        for (int i = 1; i < k+1; i++) {
            for (int j = 0; j < M; j++) {
                for (int j2 = 0; j2 < N; j2++) {
                    dp[i][j][j2]=getV(M,N,j+1,j2,i-1,dp)
                                +getV(M,N,j-1,j2,i-1,dp)
                                +getV(M,N,j,j2+1,i-1,dp)
                                +getV(M,N,j,j2-1,i-1,dp);
                }
            }
        }
        
        return dp[k][a][b];
    }

    private static long getV(int M,int N,int a,int b,int k,long[][][]dp) {
        if(a==M||a<0||b==N||b<0||k<0) return 0L;
        return dp[k][a][b];
    }

    /**
     * 动态规划 优化边界判断
     */
    public static String wayPBobSurvial4(int M,int N,int a,int b,int k) {
        long all=(long)Math.pow(4, k);
        long alive=pBobSurvial4(M,N,a,b,k);
        long g=gcd(all, alive);
        return String.valueOf(alive/g)+"/"+String.valueOf(all/g);
    }
    public static long pBobSurvial4(int M,int N,int a,int b,int k) {
        if(a==M||a<0||b==N||b<0||k<0) return 0L;
        long[][][] dp=new long[k+1][M+2][N+2];// getV的return 0, dp四周padding 0
        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < N+1; j++) {
                dp[0][i][j]=1L;
            }
        }

        for (int i = 1; i < k+1; i++) {
            for (int j = 1; j < M+1; j++) {
                for (int j2 = 1; j2 < N+1; j2++) {
                    dp[i][j][j2]=dp[i-1][j+1][j2]
                                +dp[i-1][j-1][j2]
                                +dp[i-1][j][j2+1]
                                +dp[i-1][j][j2-1];
                }
            }
        }
        
        return dp[k][a+1][b+1];//
    }

    static class Element{
        int M=8;
        int N=9;
        int a=4;
        int b=6;
        int k=4;
    }
    public static Element getE(int maxV) {
        Element e=new Element();
        int M=(int)(1+Math.random()*(maxV-1));
        int N=(int)(2+Math.random()*(maxV-2));
        int a=(int)(Math.random()*M);
        int b=(int)(Math.random()*N);
        int k=(int)(Math.random()*maxV);
        e.M=M;
        e.N=N;
        e.a=a;
        e.b=b;
        e.k=k;
        return e;
    }
}
