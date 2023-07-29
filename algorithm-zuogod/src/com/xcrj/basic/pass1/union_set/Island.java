package com.xcrj.basic.pass1.union_set;
//小岛数量，癌细胞感染
public class Island {
    public static void main(String[] args) {
        //expect 3
        int[][] ass={{0,0,1,0,1,0},
                     {1,1,1,0,1,0},
                     {1,0,0,1,0,0},
                     {0,0,0,0,0,0}};
        int m=ass.length;
        int n=ass[0].length;
        int r=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(ass[i][j]==1){
                    r++;
                    infect(ass, i, j,m,n);
                }
            }
        }
        
        System.out.println(r);
    }

    public static void infect(int[][]ass,int i,int j,int m,int n) {
        if(ass==null||ass.length==0||i>=m||i<0||j>=n||j<0||ass[i][j]!=1) return;
        ass[i][j]=2;
        infect(ass, i+1, j,m,n);
        infect(ass, i-1, j,m,n);
        infect(ass, i, j+1,m,n);
        infect(ass, i, j-1,m,n);
    }

    //todo 并行算法
}
