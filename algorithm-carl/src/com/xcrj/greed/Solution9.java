package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/candy/
 * 分发糖果
 */
public class Solution9{
    /*
     * 从左往右，相邻右侧孩子分数更高则多给一颗糖果，得到left[]
     * 从右往左，相邻左侧孩子分数更高则多给一颗糖果，得到right[]
     * 遍历left[]和right[]取对应位置最大值
     */
    public int candy(int[] ratings) {
        int[]left=new int[ratings.length];
        left[0]=1;
        for(int i=1;i<ratings.length;i++){
            left[i]=ratings[i]>ratings[i-1]?left[i-1]+1:1;//至少一颗糖果
        }

        int[]right=new int[ratings.length];
        right[ratings.length-1]=1;
        for(int i=ratings.length-2;i>=0;i--){
            right[i]=ratings[i]>ratings[i+1]?right[i+1]+1:1;
        }

        int ans=0;
        for(int i=0;i<ratings.length;i++){
            ans+=Math.max(left[i],right[i]);
        }
        return ans;
    }

    public int candy2(int[] ratings) {
        int[]candy=new int[ratings.length];
        candy[0]=1;
        for(int i=1;i<ratings.length;i++){
            candy[i]=ratings[i]>ratings[i-1]?candy[i-1]+1:1;
        }

        for(int i=ratings.length-2;i>=0;i--){
            if(ratings[i]>ratings[i+1]){
                candy[i]=Math.max(candy[i],candy[i+1]+1);
            }
        }

        int ans=0;
        for(int i=0;i<ratings.length;i++){
            ans+=candy[i];
        }
        return ans;
    }
}