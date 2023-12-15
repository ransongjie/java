package com.xcrj.basic.pass2.dp;
/**
 * 马走日
 * 从(0,0)走到(a,b), 走k步
 */
public class WayHorseKStepToE {
    public static int wayHorseKStepToE(int A,int B,int a,int b,int k) {
        if(a>A||a<0||b>B||b<0||k<0) return 0;
        if(k==0){
            return (a==0&&b==0)?1:0;
        }
        
        return wayHorseKStepToE(A, B, a-2, b+1, k-1)
        +wayHorseKStepToE(A, B, a-1, b+2, k-1)
        +wayHorseKStepToE(A, B, a+1, b+2, k-1)
        +wayHorseKStepToE(A, B, a+2, b+1, k-1)
        +wayHorseKStepToE(A, B, a+2, b-1, k-1)
        +wayHorseKStepToE(A, B, a+1, b-2, k-1)
        +wayHorseKStepToE(A, B, a-1, b-2, k-1)
        +wayHorseKStepToE(A, B, a-2, b-1, k-1);
    }

    /**
     * 动态规划
     * @param A
     * @param B
     * @param a
     * @param b
     * @param k
     * @return
     */
    public static int wayHorseKStepToE3(int A,int B,int a,int b,int k) {
        //[k步][行数][列数]
        int[][][]dp=new int[k+1][A+1][B+1];
        dp[0][0][0]=1;

        for (int i = 1; i < k+1; i++) {
            for (int j = 0; j < A+1; j++) {
                for (int j2 = 0; j2 < B+1; j2++) {
                    dp[i][j][j2]=getV(A, B,i-1,j-2,j2+1, dp)
                    +getV(A, B, i-1,j-1,j2+2, dp)
                    +getV(A, B, i-1,j+1,j2+2, dp)
                    +getV(A, B, i-1,j+2,j2+1, dp)
                    +getV(A, B, i-1,j+2,j2-1, dp)
                    +getV(A, B, i-1,j+1,j2-2, dp)
                    +getV(A, B, i-1,j-1,j2-2, dp)
                    +getV(A, B, i-1,j-2,j2-1, dp);
                }
            }
        }

        return dp[k][a][b];
    }

    private static int getV(int A,int B,int k,int a,int b,int[][][]dp){
        if(a>A||a<0||b>B||b<0) return 0;
        return dp[k][a][b];
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

    public static void main(String[] args) {
        int times=1000;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            Element element=getE(maxV);
            int way1=wayHorseKStepToE(element.A, element.B, element.a, element.b, element.k);
            int way2=wayHorseKStepToE3(element.A, element.B, element.a, element.b, element.k);
            if(way1!=way2){
                System.out.println("not good");
                System.out.println(element.A+", "+element.B
                          +", "+element.a+", "+element.b+", "+element.k);
                throw new RuntimeException();
            }
        }
    }
}
