package com.xcrj.basic.pass1.greed;

import java.util.PriorityQueue;

//以起始资金m做k个项目能获得的最大收益
public class ProjectMaxProfit{
    public static void main(String[] args) {
        CostProfit[] cps=new CostProfit[6];
        cps[0]=new CostProfit(1, 1);
        cps[1]=new CostProfit(1, 4);
        cps[2]=new CostProfit(2, 3);
        cps[3]=new CostProfit(2, 7);
        cps[4]=new CostProfit(3, 2);
        cps[5]=new CostProfit(4, 10);
        //
        int maxProfit=maxProfit(1, 4, cps);
        System.out.println(maxProfit);
    }
    /**
     * @param m 启动资金
     * @param k 项目上限
     * @param cps 项目花费 项目成本
     * @return 最大收益
     */
    public static int maxProfit(int m,int k,CostProfit[] cps) {
        int r=0;
        //
        PriorityQueue<CostProfit> smlQue=new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);//花费小根堆
        PriorityQueue<CostProfit> bigQue=new PriorityQueue<>((o1,o2)->o2.profit-o1.profit);//利润大根堆
        //
        for (CostProfit cp : cps) {
            smlQue.offer(cp);
        }
        //
        for (int i = 0; i < k; i++) {
            while(!smlQue.isEmpty()&&smlQue.peek().cost<=m){
                bigQue.offer(smlQue.poll());
            }
            if(bigQue.size()==0) return r;
            CostProfit big=bigQue.poll();
            r+=big.profit;
            m+=big.profit;
        }
        //
        return r;
    }
}

class CostProfit{
    int cost;
    int profit;
    public CostProfit(int cost,int profit) {
        this.cost=cost;
        this.profit=profit;
    }
}