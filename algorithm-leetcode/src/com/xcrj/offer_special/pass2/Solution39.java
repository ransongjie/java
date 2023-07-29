package com.xcrj.offer_special.pass2;

import java.util.Arrays;

/**
 * 剑指 Offer II 039. 直方图最大矩形面积
 * heights[]，数组中的数字用来表示柱状图中各个柱子的高度，每个柱子彼此相邻且宽度为1
 */
public class Solution39 {
    /**
     * 枚举宽度
     * - 定左边界
     * - 从左边界开始定右边界
     * - 在左右边界内寻找最矮直方图
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int maxArea=Integer.MIN_VALUE;
        for(int l=0;l<heights.length;l++){
            int minHeight=Integer.MAX_VALUE;
            for(int r=l;r<heights.length;r++){
                // 往右拓展的过程中始终使用更小高度
                minHeight=Math.min(minHeight,heights[r]);
                maxArea=Math.max(maxArea,(r-l+1)*minHeight);
            }
        }
        return maxArea;
    }

    /**
     * 枚举高度: 
     * - 先确定扩展中心
     * - 从中心向左右扩展（高度更高则可以继续扩展）
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int maxArea=Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            int heighti=heights[i];
            int l=i;
            int r=i;
            while(l-1>=0&&heights[l-1]>=heighti) l--;
            while(r+1<heights.length&&heights[r+1]>=heighti) r++;
            maxArea=Math.max(maxArea, (r-l+1)*heighti);
        }
        return maxArea;
    }

    /**
     * 单调栈：第i个滑块能左右滑动的最远距离
     * - 扩展左边界：从左往右遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明左边界不能再继续扩展
     * - 扩展右边界：从右往左遍历线性表，heights[i]<=栈顶元素则出栈 直到小于情况发生，证明右边界不能再继续扩展
     */
    public int largestRectangleArea3(int[] heights) {
        int[]ls=new int[heights.length];
        int[]rs=new int[heights.length];

        int top=-1;
        int[] stack=new int[heights.length];

        for(int i=0;i<heights.length;i++){
            while(top!=-1&&heights[i]<=heights[stack[top]]){
                top--;
            }
            // xcrj -1
            ls[i]=top==-1?-1:stack[top];
            // 高度更高的矩形的下标
            stack[++top]=i;
        }

        top=-1;
        Arrays.fill(stack, 0);

        for(int i=heights.length-1;i>=0;i--){
            while(top!=-1&&heights[i]<=heights[stack[top]]){
                top--;
            }
            // xcrj heights.length
            rs[i]=top==-1?heights.length:stack[top];
            stack[++top]=i;
        }

        int maxArea=Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            maxArea=Math.max(maxArea, (rs[i]-ls[i]-1)*heights[i]);
        }

        return maxArea;
    }

    /**
     * i入栈时确定了i的左边界
     * i出栈时确定了i的伪右边界
     * 比如所有柱子都一样高
     * - 和i同高的最右侧柱子可以得到真右边界
     * - 最终求的是maxArea，有1个真右边界即可
     * - 因此初始时右边界默认为len
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        int[]ls=new int[heights.length];
        int[]rs=new int[heights.length];
        // xcrj 默认i的右边界为len
        Arrays.fill(rs,heights.length);

        int top=-1;
        int[] stack=new int[heights.length];

        for(int i=0;i<heights.length;i++){
            while(top!=-1&&heights[i]<=heights[stack[top]]){
                // stack[top]的右边界是i
                rs[stack[top]]=i;
                top--;
            }

            // i的左边界是stack[top]
            ls[i]=top==-1?-1:stack[top];
            stack[++top]=i;
        }

        int maxArea=Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            maxArea=Math.max(maxArea, (rs[i]-ls[i]-1)*heights[i]);
        }

        return maxArea;
    }

}
