package com.xcrj.basic.pass2.dp;
/**
 * Bob存活概率
 * 5x6格子
 * 随机放到(a,b)
 * 上下左右随机等概率走k步
 * 越界即死亡
 * 生存方法数/4^k=生存概率
 * 4^k, 走k步每一步都有4种走法
 */
public class WayPBobSurvial {
    public static String wayPBobSurvial(int A,int B,int a,int b,int k) {
        long all=(long)Math.pow(4, k);
        long sur=bobSurvival(A, B, a, b, k);
        long gcd=gcd(all, sur);
        return (sur/gcd)+"/"+(all/gcd);
    }

    public static long bobSurvival(int A,int B,int a,int b,int k) {
        //长A,[0,A-1]
        if(a==A||a<0||b==B||b<0||k<0) return 0L;
        if(k==0) return 1L;

        return bobSurvival(A, B, a+1, b, k-1)
        +bobSurvival(A, B, a-1, b, k-1)
        +bobSurvival(A, B, a, b+1, k-1)
        +bobSurvival(A, B, a, b-1, k-1);
    }

    public static String wayPBobSurvial3(int M,int N,int a,int b,int k) {
        long all=(long)Math.pow(4, k);
        long sur=bobSurvival3(M, N, a, b, k);
        long gcd=gcd(all, sur);
        return (sur/gcd)+"/"+(all/gcd);
    }

    public static long bobSurvival3(int A,int B,int a,int b,int k) {
        long[][][]dp=new long[k+1][A][B];
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                dp[0][i][j]=1L;
            }
        }

        for (int i = 1; i < k+1; i++) {
            for (int j = 0; j < A; j++) {
                for (int j2 = 0; j2 < B; j2++) {
                    dp[i][j][j2]=getV(A, B, i-1, j+1, j2, dp)
                    +getV(A, B, i-1, j-1, j2, dp)
                    +getV(A, B, i-1, j, j2+1, dp)
                    +getV(A, B, i-1, j, j2-1, dp);
                }
            }
        }

        return dp[k][a][b];
    }

    private static long getV(int A,int B,int k,int a,int b,long[][][]dp){
        if(a==A||a<0||b==B||b<0) return 0L;
        return dp[k][a][b];
    }

    public static String wayPBobSurvial4(int M,int N,int a,int b,int k) {
        long all=(long)Math.pow(4, k);
        long sur=bobSurvival4(M, N, a, b, k);
        long gcd=gcd(all, sur);
        return (sur/gcd)+"/"+(all/gcd);
    }

    /**
     * 通过padding四周的方式 优化边界判断
     * 代替 if(a==A||a==0||b==B||b==0) return 0L;//dp[]padding=0
     * @param A
     * @param B
     * @param a
     * @param b
     * @param k
     * @return
     */
    public static long bobSurvival4(int A,int B,int a,int b,int k) {
        long[][][]dp=new long[k+1][A+2][B+2];
        for (int i = 1; i < A+1; i++) {
            for (int j = 1; j < B+1; j++) {
                dp[0][i][j]=1L;
            }
        }

        for (int i = 1; i < k+1; i++) {
            for (int j = 1; j < A+1; j++) {
                for (int j2 = 1; j2 < B+1; j2++) {
                    dp[i][j][j2]=dp[i-1][j+1][j2]
                    +dp[i-1][j-1][j2]
                    +dp[i-1][j][j2+1]
                    +dp[i-1][j][j2-1];
                }
            }
        }

        return dp[k][a+1][b+1];//
    }

    /**
     * 最大公约数
     * gcd(a,b)=gcd(b,a%b)
     * @param a
     * @param b
     * @return
     */
    private static long gcd(long a,long b) {
        return b==0?a:gcd(b,a%b);
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

    public static void main(String[] args) {
        int times=10000;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getE(maxV);
            String p1=wayPBobSurvial(element.M, element.N, element.a, element.b, element.k);
            String p3=wayPBobSurvial3(element.M, element.N, element.a, element.b, element.k);
            String p4=wayPBobSurvial4(element.M, element.N, element.a, element.b, element.k);
            if(!p1.equals(p3)||!p1.equals(p4)||!p3.endsWith(p4)){
                System.out.println("not good");
                System.out.println(element.M+", "+element.N
                +", "+element.a+", "+element.b+", "+element.k);
                System.out.println(p1);
                System.out.println(p3);
                System.out.println(p4);
                throw new RuntimeException();
            }
        }
    }
}
