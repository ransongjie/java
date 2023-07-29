package com.xcrj.company.pass1;

public class A024MinK {
    public static int minK(int[][]ass,int k){
        int ans=-1;
        int m=ass.length,n=ass[0].length;
        int l=ass[0][0],r=ass[m-1][n-1];
        while(l<=r){
            int mid=(l+r)>>1;
            Info info=leMid(ass,mid);
//            if(info.num>k){
//                r=mid-1;
//            }else {
//                ans=info.near;
//                l=mid+1;
//            }
            if(info.num<k){
                l=mid+1;
            }else {
                ans=info.near;
                r=mid-1;
            }
        }

        return ans;
    }

    static class Info{
        int num;//<=mid的数的数量
        int near;//最接近mid的数
        Info(int num,int near){
            this.num=num;
            this.near=near;
        }
    }

    private static Info leMid(int[][]ass,int mid){
        int m=ass.length,n=ass[0].length;
        int row=0,col=n-1;
        int num=0,near=Integer.MIN_VALUE;
        while (row<m&&col>=0){
            if(ass[row][col]>mid){
                col--;
            }else {
                near=Math.max(near,ass[row][col]);
                num+=col+1;
                row++;
            }
        }
        return new Info(num,near);
    }
}
