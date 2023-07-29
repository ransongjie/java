package com.xcrj.company.pass1;

import java.util.HashSet;

public class A075RoomRobotCleaner {
    //第三方提供的接口
    interface Robot{
        public boolean move();
        public void turnLeft();
        public void turnRight();
        public void clean();
    }

    public static void cleanRoom(Robot robot){
        clean(robot,0,0,0,new HashSet<>());
    }

    /**
     * 朝当前方向移动一步
     * 当前方向，下一个x，下一个y
     * d, nx, ny
     * 上：0, x-1, y+0
     * 右：1, x+0, y+1
     * 下：2, x+1, y+0
     * 左：3, x+0, y-1
     */
    private static final int[][]stepss={{-1,0},{0,1},{1,0},{0,-1}};

    /**
     * @param robot
     * @param d 当前朝向
     * @param x 当前位置
     * @param y 当前位置
     * @param visited 是否访问过
     */
    public static void clean(Robot robot, int d, int x, int y, HashSet<String> visited){
        //机器人打扫当前位置
        robot.clean();
        //记录当前位置已来过
        visited.add(x+"_"+y);
        //机器人转向并移动一步，初始方向直接移动
        for (int i = 0; i < 4; i++) {
            //准备走的方向
            int nd=(i+d)%4;
            //准备往这个方向走一步，将到达的位置
            int nx=x+stepss[nd][0];
            int ny=y+stepss[nd][1];
            //准备去的位置没有去过&&机器人成功走到(nx,ny)，则从(nx,ny)继续走
            if(visited.contains(nx + "_" + ny)&&robot.move()){
                clean(robot,nd,nx,ny,visited);
            }
            //转向，准备朝新方向移动一步
            robot.turnRight();
        }
        //从(x,y)移动到了(nx,ny)。需要从(nx,ny)移动回(x,y) 且 朝向已知

    }
}
