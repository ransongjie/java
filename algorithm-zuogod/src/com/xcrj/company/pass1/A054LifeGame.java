package com.xcrj.company.pass1;

public class A054LifeGame {
    public static int[][] gameLife(int[][]ass){
        int m=ass.length,n=ass[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int k=get1Num(ass,i,j);
                //存活则下一轮变成1，这个1记录在第2位上
                if(k==3||(k==2&&ass[i][j]==1)){
                    ass[i][j]|=2;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ass[i][j]>>=1;
            }
        }

        return ass;
    }

    //获取i,j 8个方向有多少个1
    private static int get1Num(int[][]ass,int i,int j){
        return f(ass,i-1,j)+f(ass,i-1,j+1)+f(ass,i,j+1)+f(ass,i+1,j+1)
                +f(ass,i+1,j)+f(ass,i+1,j-1)+f(ass,i,j-1)+f(ass,i-1,j-1);
    }
    private static int f(int[][]ass,int i,int j){
        return (i>=0&&i< ass.length&&j>=0&&j<ass[0].length&&ass[i][j]==1)?1:0;
    }
}
