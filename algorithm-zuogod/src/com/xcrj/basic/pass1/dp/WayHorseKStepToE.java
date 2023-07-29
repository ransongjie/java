package com.xcrj.basic.pass1.dp;
/**
 * 马走日走k步到达E
 * 走1个日算一步
 * 马从(0,0)必须走k步走日到(a,b)
 * 棋盘走法
 */
public class WayHorseKStepToE {
    public static void main(String[] args) {
        // int A=8;
        // int B=9;
        // int a=4;
        // int b=6;
        // int k=4;
        // System.out.println(horseKStepToE(A, B, a, b, k));
        // System.out.println(horseKStepToE3(A, B, a, b, k));

        int times=1000;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getE(maxV);
            int way1=horseKStepToE(element.A, element.B, element.a, element.b, element.k);
            int way2=horseKStepToE3(element.A, element.B, element.a, element.b, element.k);
            if(way1!=way2){
                System.out.println("not good");
                System.out.println(element.A+", "+element.B
                          +", "+element.a+", "+element.b+", "+element.k);
                throw new RuntimeException();
            }
        }
    }

    /**
     * 暴力递归
     * 意外
     * 目标
     * 尝试
     */
    public static int horseKStepToE(int A,int B,int a,int b,int k) {
        if(a>A||a<0||b>B||b<0||k<0) return 0;
        // if(a==0&&b==0){
        //     return k==0?1:0;
        // }
        if(k==0){//更容易满足
            return (a==0&&b==0)?1:0;
        }
        
        return horseKStepToE(A, B, a-2, b+1, k-1)
              +horseKStepToE(A, B, a-1, b+2, k-1)
              +horseKStepToE(A, B, a+1, b+2, k-1)
              +horseKStepToE(A, B, a+2, b+1, k-1)
              +horseKStepToE(A, B, a+2, b-1, k-1)
              +horseKStepToE(A, B, a+1, b-2, k-1)
              +horseKStepToE(A, B, a-1, b-2, k-1)
              +horseKStepToE(A, B, a-2, b-1, k-1);
    }

    /**
     * 动态规划
     * 拷贝暴力递归
     * 目标位置
     * 初始状态
     * 状态转移
     */
    public static int horseKStepToE3(int A,int B,int a,int b,int k) {
        if(a>A||a<0||b>B||b<0||k<0) return 0;

        int[][][]dp=new int[k+1][A+1][B+1];//
        dp[0][0][0]=1;

        for (int i = 1; i < k+1; i++) {
            for (int j = 0; j < A+1; j++) {
                for (int j2 = 0; j2 < B+1; j2++) {
                    dp[i][j][j2]=getValue(A, B,i-1,j-2,j2+1, dp)
                                +getValue(A, B,i-1,j-1,j2+2, dp)
                                +getValue(A, B,i-1,j+1,j2+2, dp)
                                +getValue(A, B,i-1,j+2,j2+1, dp)
                                +getValue(A, B,i-1,j+2,j2-1, dp)
                                +getValue(A, B,i-1,j+1,j2-2, dp)
                                +getValue(A, B,i-1,j-1,j2-2, dp)
                                +getValue(A, B,i-1,j-2,j2-1, dp);
                }
            }
        }

        return dp[k][a][b];//
    }

    public static int getValue(int A,int B,int i,int j,int j2,int[][][]dp) {
        if(j>A||j<0||j2>B||j2<0||i<0) return 0;
        return dp[i][j][j2];
    }

    static class Element{
        int A=8;
        int B=9;
        int a=4;
        int b=6;
        int k=4;
    }
    public static Element getE(int maxV) {
        Element e=new Element();
        int A=(int)(1+Math.random()*(maxV-1));
        int B=(int)(2+Math.random()*(maxV-2));
        int a=(int)(Math.random()*A);
        int b=(int)(Math.random()*B);
        int k=(int)(Math.random()*maxV);
        e.A=A;
        e.B=B;
        e.a=a;
        e.b=b;
        e.k=k;
        return e;
    }
}
