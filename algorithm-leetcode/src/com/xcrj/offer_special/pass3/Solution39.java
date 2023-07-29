package com.xcrj.offer_special.pass3;

import java.util.Arrays;

/**
 * 剑指 Offer II 039. 直方图最大矩形面积
 * heights[]，数组中的数字用来表示柱状图中各个柱子的高度，每个柱子彼此相邻且宽度为1
 */
public class Solution39 {
    /**
     * 枚举宽度
     * - 双指针 i=0, j=i
     * - 始终使用最小高度
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        //
        int maxa=Integer.MIN_VALUE;
        //
        for(int i=0;i<heights.length;i++){
            int minh=Integer.MAX_VALUE;
            for(int j=i;j<heights.length;j++){
                minh=Math.min(minh, heights[j]);
                maxa=Math.max(maxa, minh*(j-i+1));
            }
        }
        //
        return maxa;
    }

    /**
     * 枚举高度: 
     * - 以每个高度为中心 双指针向外扩展 高度更高则向外扩展
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int maxa=Integer.MIN_VALUE;
        //
        for(int i=0;i<heights.length;i++){
            int hi=heights[i];
            int l=i;
            int r=i;
            // xcrj heights[l-1], heights[r+1]
            while(l>=1&&heights[l-1]>=hi) l--;
            while(r<heights.length-1&&heights[r+1]>=hi) r++;
            //
            maxa=Math.max(maxa, hi*(r-l));
        }
        //
        return maxa;
    }

    /**
     * 历史单调栈
     * - 单增栈, 放入比栈顶大的值, 可求最近更小的值
     * - 单减栈, 放入比栈顶小的值, 可求最近更大的值
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        //
        int maxa=Integer.MIN_VALUE;
        // ls[i]=l, 第i个直方图 最多向左移动到 l
        // rs[i]=r, 第i个直方图 最多向右移动到 r
        int[] ls=new int[heights.length];
        int[] rs=new int[heights.length];
        // 最近更小的值的下标，单增栈
        int top=-1;
        int[] stack=new int[heights.length];
        //
        for(int i=0;i<heights.length;i++){
            while(top!=-1&&heights[stack[top]]>=heights[i]){
                top--;
            }
            // ls[i]=stack[top];
            ls[i]=top==-1?-1:stack[top];
            //
            stack[++top]=i;
        }
        //
        top=-1;
        Arrays.fill(stack, 0);
        //
        for(int j=heights.length-1;j>=0;j--){
            while(top!=-1&&heights[stack[top]]>=heights[j]){
                top--;
            }
            // rs[j]=stack[top];
            rs[j]=top==-1?heights.length:stack[top];
            //
            stack[++top]=j;
        }

        //
        for(int k=0;k<heights.length;k++){
            maxa=Math.max(maxa, (rs[k]-ls[k]-1)*heights[k]);
        }
        return maxa;
    }

    /**
     * 出栈t，确定i的左边界。反之，被出栈t的伪右边界 是i
     * 
     * 比如所有柱子都一样高
     * - 和i同高的最右侧柱子可以得到真右边界
     * - 最终求的是maxArea，有1个真右边界即可
     * - 因此初始时右边界默认为len
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        //
        int maxa=Integer.MIN_VALUE;
        // ls[i]=l, 第i个直方图 最多向左移动到 l
        // rs[i]=r, 第i个直方图 最多向右移动到 r
        // rs[]=heights.length
        int[] ls=new int[heights.length];
        int[] rs=new int[heights.length];
        Arrays.fill(rs, heights.length);
        // 最近更小的值的下标，单增栈
        int top=-1;
        int[] stack=new int[heights.length];
        //
        for(int i=0;i<heights.length;i++){
            while(top!=-1&&heights[stack[top]]>=heights[i]){
                //被出栈元素t的虚伪右边界 是 i
                rs[stack[top]]=i;
                //i的左边界 是 被出栈元素t
                top--;
            }
            // ls[i]=stack[top];
            ls[i]=top==-1?-1:stack[top];
            //
            stack[++top]=i;
        }

        //
        for(int k=0;k<heights.length;k++){
            maxa=Math.max(maxa, (rs[k]-ls[k]-1)*heights[k]);
        }
        return maxa;
    }
}
