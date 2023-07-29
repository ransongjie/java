package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 剑指 Offer II 105. 岛屿的最大面积
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */
public class Solution105 {
    //深度优先搜索四周, 以grid的每个元素为起点往上下左右4个方向深度优先搜索拓展为1元素
    public int maxAreaOfIsland1(int[][] grid) {
        int maxArea=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                maxArea=Math.max(maxArea,dfs(grid,i,j));
            }
        }

        return maxArea;
    }

    // 从grid[curI][curJ]土地向四周扩展，能够扩展出的最大面积
    private int dfs(int[][] grid, int curI, int curJ) {
        //防止越界
        if(curI<0)return 0;
        if(curJ<0)return 0;
        if(curI==grid.length)return 0;
        if(curJ==grid[0].length)return 0;
        //被访问过
        if(grid[curI][curJ]==0)return 0;

        //grid[curI][curJ] must=1,访问当前土地
        grid[curI][curJ]=0;
        int area=1;

        //从访问的当前土地往四周扩展. 右左上下
        int[] stepIs={0,0,1,-1};
        int[] stepJs={1,-1,0,0};

        for(int k=0;k<4;k++){
            int nextI=curI+stepIs[k];
            int nextJ=curJ+stepJs[k];
            area+=dfs(grid,nextI,nextJ);
        }

        return area;
    }

    //迭代式深度优先，以grid中每个元素为起点向四周拓展为1元素
    //使用栈记录未被访问的元素
    //栈非空则出栈元素向元素四周拓展可行元素
    public int maxAreaOfIsland2(int[][] grid) {
        int maxArea=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                Stack<Integer> stackI=new Stack<>();
                Stack<Integer> stackJ=new Stack<>();
                stackI.push(i);
                stackJ.push(j);

                int area=0;
                //以grid[i][j]为起点向四周拓展
                while(!stackI.isEmpty()&&!stackJ.isEmpty()){
                    int curI=stackI.pop();
                    int curJ=stackJ.pop();

                    //防止越界
                    if(curI<0)continue;
                    if(curJ<0)continue;
                    if(curI==grid.length)continue;
                    if(curJ==grid[0].length)continue;
                    //被访问过
                    if(grid[curI][curJ]==0)continue;
                    
                    //grid[curI][curJ] must=1,访问当前土地
                    grid[curI][curJ]=0;
                    area++;

                    int[] stepIs={0,0,1,-1};
                    int[] stepJs={1,-1,0,0};
                    for (int k = 0; k < 4; k++) {
                        int nextI = curI + stepIs[k];
                        int nextJ = curJ + stepJs[k];
                        stackI.push(nextI);
                        stackJ.push(nextJ);
                    }
                }
                maxArea=Math.max(maxArea,area);
            }
        }

        return maxArea;
    }

    //广度优先搜索，以grid中每个元素为起点向四周拓展为1元素
    //队尾放入未被访问的元素
    //队列非空从对头出队向元素四周拓展可行元素
    public int maxAreaOfIsland3(int[][] grid) {
        int maxArea=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                Queue<Integer> queI=new ArrayDeque<>();
                Queue<Integer> queJ=new ArrayDeque<>();
                queI.offer(i);
                queJ.offer(j);

                int area=0;
                while(!queI.isEmpty()&&!queJ.isEmpty()){
                    int curI=queI.poll();
                    int curJ=queJ.poll();

                    //防止越界
                    if(curI<0)continue;
                    if(curJ<0)continue;
                    if(curI==grid.length)continue;
                    if(curJ==grid[0].length)continue;
                    //被访问过
                    if(grid[curI][curJ]==0)continue;
                    
                    //grid[curI][curJ] must=1,访问当前土地
                    grid[curI][curJ]=0;
                    area++;

                    int[] stepIs={0,0,1,-1};
                    int[] stepJs={1,-1,0,0};
                    for (int k = 0; k < 4; k++) {
                        int nextI = curI + stepIs[k];
                        int nextJ = curJ + stepJs[k];
                        queI.offer(nextI);
                        queJ.offer(nextJ);
                    }
                }
                maxArea=Math.max(maxArea,area);
            }
        }

        return maxArea;
    }
}
