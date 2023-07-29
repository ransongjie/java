package com.xcrj.company.pass1;

import java.util.Arrays;

public class A059MagicStone {
    public static int magicStone(int[][]stones){
        //石头数量必须是偶数
        int n=stones.length;
        if((n&1)==1) return -1;
        //差值倒序, [0](石头种类)正序
        Arrays.sort(stones,(a,b)->(a[0]==0&&b[0]==0)?(b[1]-b[2])-(a[1]-a[2]):a[0]-b[0]);
        //统计无色，红色，蓝色石头数量和无色石头变红色总成本
        int noColorNum=0;//无色石头数量
        int redNum=0;//红色石头数量
        int blueNum=0;//蓝色石头数量
        int costNoColorToRed=0;//
        for (int i = 0; i < n; i++) {
            if(stones[i][0]==0){
                noColorNum++;
                costNoColorToRed+=stones[i][1];
            }else if(stones[i][0]==1){
                redNum++;
            }else{
                blueNum++;
            }
        }
        //红色或蓝色石头数量大于总量一半
        if((blueNum>>1)>n/2||(redNum>>1)>n/2){
            return -1;
        }
        //需要变成蓝色的石头=无色石头-还需变多少个红色
        blueNum=noColorNum-((n>>1)-redNum);
        //上面已经排过序
        int cost=costNoColorToRed;
        for (int i = 0; i < blueNum; i++) {
            //无色的都变成了红色，某些石头变成蓝色成本更低，直接变成红色提高了成本，减去红蓝成本差值
            cost-=stones[i][1]-stones[i][2];
        }
        return cost;
    }
}
