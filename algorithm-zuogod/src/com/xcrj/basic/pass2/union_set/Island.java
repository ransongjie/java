package com.xcrj.basic.pass2.union_set;
/**
 * 小岛数量，细胞感染
 */
public class Island {
    public static void main(String[] args) {
        //expect 3
        int[][] ass={{0,0,1,0,1,0},
                     {1,1,1,0,1,0},
                     {1,0,0,1,0,0},
                     {0,0,0,0,0,0}};
        System.out.println(island(ass));
    }

    public static int island(int[][]ass) {
        int m=ass.length;
        int n=ass[0].length;
        int r=0;
        // 遍历每个位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(ass[i][j]==1){
                    r++;
                    inflict(ass, i, j, m, n);
                }
            }
        }
        return r;
    }

    private static void inflict(int[][]ass,int i,int j,int m,int n) {
        if(i<0||i>m-1||j<0||j>n-1||ass[i][j]!=1) return;
        ass[i][j]=2;
        // 走上下左右去感染
        inflict(ass, i+1, j, m, n);
        inflict(ass, i-1, j, m, n);
        inflict(ass, i, j+1, m, n);
        inflict(ass, i, j-1, m, n);
    }

    //todo(并行算法)
    
}
