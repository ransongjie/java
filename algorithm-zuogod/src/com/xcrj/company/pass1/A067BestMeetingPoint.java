package com.xcrj.company.pass1;

public class A067BestMeetingPoint {
    public static int bestMeetingPointDistance(int[][]ass){
        //统计每一行有多少个1
        //统计每一列有多少个1
        int m=ass.length,n=ass[0].length;
        int[]row1s=new int[m];
        int[]col1s=new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(ass[i][j]==1){
                    row1s[i]++;
                    col1s[i]++;
                }
            }
        }
        //1移动到最优行
        int up=0,dn=m-1;
        int sum=0,upsum=0,dnsum=0;
        while(up<dn){
            //成本小移动一行
            if(row1s[up]+upsum<=row1s[dn]+dnsum){
                upsum+=row1s[up++];
                sum+=upsum;
            }else{
                dnsum+=row1s[dn--];
                sum+=dnsum;
            }
        }
        //1移动最优列
        int lf=0,rt=n-1;
        int lfsum=0,rtsum=0;
        while (lf<rt){
            //成本小的移动一列
            if(col1s[lf]+lfsum<=col1s[rt]+rtsum){
                rtsum+=col1s[lf++];
                sum+=rtsum;
            }else{
                rtsum+=col1s[rt--];
                sum+=rtsum;
            }
        }
        //行列都移动到最优位置 则移动到了最优聚集点，注意只能横向或者纵向移动
        // 所有1聚集的最小距离
        return sum;
    }
}
