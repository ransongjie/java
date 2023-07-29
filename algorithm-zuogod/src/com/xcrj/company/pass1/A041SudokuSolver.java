package com.xcrj.company.pass1;

public class A041SudokuSolver {
    public static char[][] sudokuSolver(char[][]bss){
        boolean[][]row=new boolean[9][10];
        boolean[][]col=new boolean[9][10];
        boolean[][]bkt=new boolean[9][10];
        initRecord(bss,row,col,bkt);
        addNum(0,0,bss,row,col,bkt);
        return bss;
    }

    private static void initRecord(char[][]bss,boolean[][]row,boolean[][]col,boolean[][]bkt){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(bss[i][j]!='.'){
                    int num=bss[i][j]-'0';
                    int b=3*(i/3)+(j/3);
                    row[i][num]=true;
                    col[i][num]=true;
                    bkt[b][num]=true;
                }
            }
        }
    }

    private static boolean addNum(int i,int j,char[][]bss,boolean[][]row,boolean[][]col,boolean[][]bkt){
        if(i==9){//0~8
            //能到最后一行 找到解
            return true;
        }

        int nxti=j==8?i+1:i;
        int nxtj=j==8?0:j+1;

        //当前位置不是.直接去下一个位置
        if(bss[i][j]!='.'){
            return addNum(nxti,nxtj,bss,row,col,bkt);
        }
        int b=3*(i/3)+(j/3);
        //当前位置是., 尝试填写 1~9的数子
        for (int num = 1; num <= 9; num++) {
            //没填过这个数字才可以填
            if(!row[i][num]&&!col[j][num]&&!bkt[b][num]){//
                bss[i][j]=(char)(num+'0');
                row[i][num]=true;
                col[i][num]=true;
                bkt[b][num]=true;
                //深度优先下一个位置
                if(addNum(nxti,nxtj,bss,row,col,bkt)){
                    //有一个解直接返回。题目讲 只会有一个解
                    return true;
                }
                //回溯尝试填入下一个数字
                bss[i][j]='.';
                row[i][num]=false;
                col[i][num]=false;
                bkt[b][num]=false;
            }
        }
        return false;
    }
}
