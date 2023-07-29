package com.xcrj.company.pass1;

public class A073BallToHoleWay {
    /**
     * @param ass  ass[i][j]!=0 障碍 越界也是障碍
     * @param ball [0]=row [1]=col
     * @param hole [0]=row [1]=col
     * @return
     */
    public static String minPathBallHole(int[][] ass, int[] ball, int[] hole) {
        int m=ass.length,n=ass[0].length;
        Info[] curQue=new Info[m*n],nxtQue=new Info[m*n];
        int curNs=0,nxtNs=0;
        //相同点相同方向只能经过1次，避免出现死循环
        boolean[][][] visited = new boolean[m][n][4];
        //从初始点出发的下层结点入队
        curNs=spread(ass,m,n,new Info(ball[0],ball[1],4,""),curQue,curNs,visited);
        //还有下层结点
        while (curNs!=0){
            //！一次处理完一层，求当前层所有点能到的下层结点
            for (int i = 0; i < curNs; i++) {
                //出队结点
                Info cur=curQue[i];
                //判断进洞
                if(cur.r==hole[0]&&cur.c==hole[1]){
                    return cur.p;
                }
                //继续走下层结点
                nxtNs=spread(ass,m,n,cur,nxtQue,nxtNs,visited);
            }
            //交换que
            Info[]tmp=curQue;
            curQue=nxtQue;
            nxtQue=tmp;
            //更新ns
            curNs=nxtNs;
            nxtNs=0;
        }
        return "impossible";
    }

    /**
     * @param ass
     * @param m       ass行数 ass列数
     * @param n       ass列数
     * @param cur     当前结点
     * @param que     队列 宽度优先当前层所有结点
     * @param ns      next Start 上层结点拓展的下层结点位置，作为上层下个结点拓展下层结点的起始位置
     * @param visited 是否访问
     * @return ns next Start 上层结点拓展的下层结点位置，作为上层下个结点拓展下层结点的起始位置
     */
    private static int spread(int[][] ass, int m, int n, Info cur, Info[] que, int ns, boolean[][][] visited) {
        //尝试继续朝着这个方向走
        int d = cur.d;
        int r = cur.r + stepss[d][0];
        int c = cur.c + stepss[d][1];
        //初始状态还没走过||不能继续朝着这个方向走(撞墙或越界)
        if (d == 4 || r < 0 || r >= m || c < 0 || c >= n || ass[r][c] != 0) {
            //尝试继续朝着其它方向走
            for (int di = 0; di < 4; di++) {
                //=cur.d上面试过了走不了
                if (cur.d != di) {
                    r = cur.r + stepss[di][0];
                    c = cur.c + stepss[di][1];
                    //能走就走
                    if (r >= 0 && r < m && c >= 0 && c < n && ass[r][c] == 0 && !visited[r][c][di]) {
                        visited[r][c][di] = true;
                        Info info = new Info(r, c, di, cur.p + ps[di]);//变换移动方向了
                        que[ns++] = info;
                    }
                }
            }
        }
        //能够继续朝着这个方向走
        else {
            if (!visited[r][c][d]) {
                visited[r][c][d] = true;
                que[ns++] = new Info(r, c, d, cur.p);
            }
        }
        return ns;
    }

    static class Info {
        //行
        int r;
        //列
        int c;
        //从什么方向来，只有0下d，1左l，2右r，3上u，4(初始位置状态)
        int d;
        //方向
        String p;

        Info(int r, int c, int d, String p) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.p = p;
        }
    }

    /**
     * 下d，左l，右r，上u，不动
     */
    static final int[][] stepss = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}, {0, 0}};
    static final String[] ps = {"d", "l", "r", "u"};
}
