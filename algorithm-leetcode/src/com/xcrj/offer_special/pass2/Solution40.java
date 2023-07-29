package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 040. 矩阵中最大的矩形
 * 给定一个由 0 和 1 组成的矩阵 matrix ，找中只包含 1 的最大矩形，并返回其面积。
 */
public class Solution40 {
    /**
     * 左横向直方图最大矩形面积：
     * - 统计第i行的高度，连续1的个数（得到直方图的所有高度）
     * - 以(i,j)为下端点向上寻找上端点 能滑动的最大距离（得到直方图的宽度）
     * @param matrix
     * @return
     */
    public int maximalRectangle1(String[] matrix) {
        if(matrix.length==0){
            return 0;
        }

        int[][]heights=new int[matrix.length][matrix[0].length()];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length();j++){
                if(matrix[i].charAt(j)=='1'){
                    heights[i][j]=j==0?1:heights[i][j-1]+1;
                }
            }
        }

        int maxArea=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length();j++){
                if(matrix[i].charAt(j)=='0') continue;
                int minH=heights[i][j];
                for(int k=i;k>=0;k--){
                    minH=Math.min(minH, heights[k][j]);
                    maxArea=Math.max(maxArea, (i-k+1)*minH);
                }
            }
        }
        return maxArea;
    }

    /**
     * 左横向直方图最大矩形面积
     * - 统计第i行的高度，连续1的个数（得到直方图的所有高度）
     * - 每1列为底应用单调栈方法：第i个滑块能左右滑动的最远距离
     * -- 扩展上边界：每一个横向直方体向上移动的截止点
     * -- 扩展下边界：每一个横向直方体向下移动的截止点
     */
    public int maximalRectangle2(String[] matrix) {
        if(matrix.length==0){
            return 0;
        }

        int[][]heights=new int[matrix.length][matrix[0].length()];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length();j++){
                if(matrix[i].charAt(j)=='1'){
                    heights[i][j]=j==0?1:heights[i][j-1]+1;
                }
            }
        }

        int maxArea=0;
        for(int j=0;j<matrix[0].length();j++){
            int[] us=new int[matrix.length];
            int[] bs=new int[matrix.length];
            // xcrj 单调栈 记录以前更高的直方图 用于现在和以前比较
            int[]stack=new int[matrix.length];
            int top=-1;

            // 每一个横向直方体向上移动的截止点
            // xcrj 为什么是上边界 因为此时stack[]存储以前的上面的边界点
            for(int i=0;i<matrix.length;i++){
                while(top!=-1&&heights[i][j]<=heights[stack[top]][j]){
                    top--;
                }
                us[i]=top==-1?-1:stack[top];
                stack[++top]=i;
            }

            Arrays.fill(stack, 0);
            top=-1;

            // 每一个横向直方体向下移动的截止点
            for(int i=matrix.length-1;i>=0;i--){
                while(top!=-1&&heights[i][j]<=heights[stack[top]][j]){
                    top--;
                }
                bs[i]=top==-1?matrix.length:stack[top];
                stack[++top]=i;
            }

            int maxAreaJ=0;
            for(int i=0;i<matrix.length;i++){
                maxAreaJ=Math.max(maxAreaJ, (bs[i]-us[i]-1)*heights[i][j]);
            }
            // xcrj 和max AreaJ比较1次即可
            maxArea=Math.max(maxAreaJ, maxArea);
        }
        return maxArea;
    }

    /**
     * 虚伪下边界
     * 横向同高最下方的直方体能找到 真实下边界
     * 防止所有横向直方体高度都一致 下边界数组初始值都为len
     * @param matrix
     * @return
     */
    public int maximalRectangle3(String[] matrix) {
        if(matrix.length==0){
            return 0;
        }

        int[][]heights=new int[matrix.length][matrix[0].length()];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length();j++){
                if(matrix[i].charAt(j)=='1'){
                    heights[i][j]=j==0?1:heights[i][j-1]+1;
                }
            }
        }

        int maxArea=0;
        for(int j=0;j<matrix[0].length();j++){
            int[] us=new int[matrix.length];
            int[] bs=new int[matrix.length];
            //
            Arrays.fill(bs, matrix.length);
            int[]stack=new int[matrix.length];
            int top=-1;

            for(int i=0;i<matrix.length;i++){
                while(top!=-1&&heights[i][j]<=heights[stack[top]][j]){
                    //
                    bs[stack[top]]=i;
                    top--;
                }
                us[i]=top==-1?-1:stack[top];
                //
                stack[++top]=i;
            }

            int maxAreaJ=0;
            for(int i=0;i<matrix.length;i++){
                maxAreaJ=Math.max(maxAreaJ, (bs[i]-us[i]-1)*heights[i][j]);
            }
            // xcrj 和max AreaJ比较1次即可
            maxArea=Math.max(maxAreaJ, maxArea);
        }
        return maxArea;
    }
}
