package com.xcrj.company.pass1;

/**
 * 纵横联通两个岛的桥的最短距离
 *
 */
public class A028IslandBridge {
    /**
     * 从初始岛屿出发向外走1圈构成环岛
     * 从新的环岛出发向外走1圈构成环岛
     * ...
     * 直到不能再往外走
     * @param ass 海洋 海洋中有岛屿。ass[][]=1 表示是岛屿 ass[][]=2表示处理过的岛 ass[][]=0 海洋
     * @return
     */
    public static int islandBridge(int[][]ass){
        int m=ass.length,n=ass[0].length;
        int all=m*n;
        int islandi=0;//先处理第一个岛屿
        //二维岛屿一维化
        int curs[]=new int[all];//curs[原岛屿大小]=原岛屿一维位置
        int nxts[]=new int[all];//nxts[新岛屿大小]=新岛屿一维位置
        int distance[][]=new int[2][all];//distance[islandi][新岛一维位置]=出发岛屿到新岛的距离
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //找到出发岛屿
                if(ass[i][j]==1){
                    int islandSize=island(ass,i,j,m,n,curs,0,distance[islandi]);
                    int d=1;//出发岛屿自己到自己距离为1
                    while (islandSize!=0){//处理完所有的岛屿
                        d++;//出发岛屿到新环岛的距离
                        islandSize=bfsCircle(m,n,all,d,curs,islandSize,nxts,distance[islandi]);
                        //交换
                        int[]tmp=curs;
                        curs=nxts;
                        nxts=tmp;
                    }
                    islandi++;//处理下一个岛
                }
            }
        }

        int min=Integer.MAX_VALUE;
        for (int i = 0; i < all; i++) {
            //min(A岛到B岛的距离，B岛到A岛的距离)
            min=Math.min(min,distance[0][i]+distance[1][i]);
        }
        //两个岛屿之前的最短距离
        return min-3;
    }

    /**
     * 获取岛屿 curs[有效长度]=岛屿位置
     *
     * @param ass 海洋 海洋中有岛屿
     * @param i ass[i][j]
     * @param j ass[i][j]
     * @param m 行数
     * @param n 列数
     * @param curs curs[有效长度]=岛屿一维位置
     * @param curi 有效长度
     * @param distance distance[岛屿一维位置]=1 自己到自己距离为1
     * @return 岛屿大小，curs[]的有效长度
     */
    private static int island(int[][]ass,int i,int j,int m,int n,int[]curs,int curi,int[]distance){
        //越界 或 当前位置不是岛屿
        if(i<0||i==m||j<0||j==n||ass[i][j]!=1){
            return curi;
        }
        //当前位置是岛屿且一定等于1
        ass[i][j]=2;//标识已经处理过
        int dem1Idx=i*n+j;//二维地址(i,h)一维化
        distance[dem1Idx]=1;//自己到自己距离为1
        curs[curi++]=dem1Idx;//原岛屿一维位置
        //(i,j) 上下左右
        curi=island(ass,i+1,j,m,n,curs,curi,distance);
        //上一个curi会传入的下一个island()
        curi=island(ass,i-1,j,m,n,curs,curi,distance);
        curi=island(ass,i,j+1,m,n,curs,curi,distance);
        curi=island(ass,i,j-1,m,n,curs,curi,distance);
        return curi;
    }

    /**
     * 从当前岛屿出发 感染下一圈。距离为d
     * distance[从原来的岛屿拓展1圈(环岛)一维位置]=d 距离
     * nxts[环岛大小]=新的环岛的一维位置
     * @param m 行数
     * @param n 列数
     * @param d 距离
     * @param curs curs[]=岛屿的位置
     * @param size 岛屿的大小，curs[]的有效长度
     * @param nxts nxts[index]=新环岛的位置
     * @param distance distance[新环岛的位置]=d
     * @return 新环岛的大小，nxts[]的有效长度
     */
    private static int bfsCircle(int m,int n,int all,int d,int[]curs,int size,int[]nxts,int[] distance){
        int nxti=0;
        for (int i = 0; i < size; i++) {
            int up=curs[i]<n?-1:curs[i]-n;//能不能往上走
            int down=curs[i]+n>=all?-1:curs[i]+n;
            int left=curs[i]%n==0?-1:curs[i]-1;
            int right=curs[i]%n==n-1?-1:curs[i]+1;
            // 可以往上走
            if(up!=-1&&distance[up]==0){
                distance[up]=d;
                nxts[nxti++]=up;
            }
            if(down!=-1&&distance[down]==0){
                distance[down]=d;
                nxts[nxti++]=down;
            }
            if(left!=-1&&distance[left]==0){
                distance[left]=d;
                nxts[nxti++]=left;
            }
            if(right!=-1&&distance[right]==0){
                distance[right]=d;
                nxts[nxti++]=right;
            }
        }
        return nxti;
    }
}
