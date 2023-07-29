package com.xcrj.company.pass1;

public class A045SetMatrix0 {
    public static int[][] setMatrix0(int[][]mss){
        int m=mss.length,n=mss[0].length;
        boolean isRowContains0=false;
        for (int i = 0; i < n; i++) {
            if(mss[0][i]==0) isRowContains0=true;
        }
        boolean isColContains0=false;
        for (int i = 0; i < m; i++) {
            if(mss[i][0]==0) isColContains0=true;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(mss[i][j]==0){
                    //使用输入数组记录
                    mss[0][j]=0;
                    mss[i][0]=0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //只要行 或 列为0 则元素为0
                if(mss[i][0]==0||mss[0][j]==0){
                    mss[i][j]=0;
                }
            }
        }

        if(isRowContains0){
            for (int i = 0; i < n; i++) {
                mss[0][i]=0;
            }
        }
        if(isColContains0){
            for (int i = 0; i < m; i++) {
                mss[i][0]=0;
            }
        }

        return mss;
    }

    public static int[][] setMatrix02(int[][]mss){
        int m= mss.length,n=mss[0].length;
        boolean isColContains0=false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mss[i][j]==0){
                    mss[i][0]=0;//第0列记录某一行是否为0
                    if(j!=0){
                        mss[0][j]=0;//1~n列是否为0
                    }else{
                        isColContains0=true;//记录第0列是否为0
                    }
                }
            }
        }

        //第0列被单独考虑了。倒序所有行，遍历除开第0列的位置，第0列是否为0倍isColContains0记录
        for (int i = m-1; i >=0 ; i--) {
            for (int j = 1; j < n; j++) {
                if(mss[0][j]==0||mss[i][0]==0){
                    mss[i][j]=0;
                }
            }
        }
        if(isColContains0){
            for (int i = 0; i < m; i++) {
                mss[i][0]=0;
            }
        }
        return mss;
    }
}
