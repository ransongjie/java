package com.xcrj.company.pass1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class A032D2TrappingRain {
    static class Node{
        //高度
        int height;
        //位置
        int row;
        int col;
        Node(int h,int r,int c){
            height=h;
            row=r;
            col=c;
        }
    }

    // 水只能上下左右4个方向流动。8个方向流动也是本题一样的解法
    public static int trapRainWater(int[][] ass) {
        if(ass==null||ass.length==0||ass[0]==null||ass[0].length==0){
            return 0;
        }
        PriorityQueue<Node> heap=new PriorityQueue<>(Comparator.comparingInt(o -> o.height));
        //将最外圈放入堆
        int M=ass.length,N=ass[0].length;
        boolean[][]isEntered=new boolean[M][N];
        for (int i = 0; i < N; i++) {
            heap.add(new Node(ass[0][i],0,i));
            isEntered[0][i]=true;
        }
        for (int i = 0; i < M; i++) {
            heap.add(new Node(ass[i][N-1],i,N-1));
            isEntered[i][N-1]=true;
        }
        for (int i = 0; i < N; i++) {
            heap.add(new Node(ass[M-1][i],M-1,i));
            isEntered[M-1][i]=true;
        }
        for (int i = 0; i < M; i++) {
            heap.add(new Node(ass[i][0],i,0));
            isEntered[i][0]=true;
        }
        //
        int water=0;
        int max=0;//薄弱点，水位高于max，水从薄弱点流出
        while(!heap.isEmpty()){
            //更新薄弱点
            Node node=heap.poll();
            max=Math.max(max,node.height);
            //上下左右走
            int r=node.row,c=node.col;
            //能往上走
            if(r>0&&!isEntered[r-1][c]){
//                water+=Math.max(0,ass[r-1][c]-max);
                water+=Math.max(0,max-ass[r-1][c]);
                heap.add(new Node(ass[r-1][c],r-1,c));
                isEntered[r-1][c]=true;
            }
            //能往下走
            if(r<M-1&&!isEntered[r+1][c]){
                water+=Math.max(0,max-ass[r+1][c]);
                heap.add(new Node(ass[r+1][c],r+1,c));
                isEntered[r+1][c]=true;
            }
            //能往左走
            if(c>0&&!isEntered[r][c-1]){
                water+=Math.max(0,max-ass[r][c-1]);
                heap.add(new Node(ass[r][c-1],r,c-1));
                isEntered[r][c-1]=true;
            }
            //能往右走
            if(c<N-1&&!isEntered[r][c+1]){
                water+=Math.max(0,max-ass[r][c+1]);
                heap.add(new Node(ass[r][c+1],r,c+1));
                isEntered[r][c+1]=true;
            }
        }

        return water;
    }
}
