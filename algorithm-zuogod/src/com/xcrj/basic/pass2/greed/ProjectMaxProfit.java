package com.xcrj.basic.pass2.greed;

import java.util.PriorityQueue;

/**
 * 以起始资金m做k个项目能获得的最大收益
 * 在自己能做的项目中选择利润最大的项目做
 * 成本小根堆（把成本<=M的项目）取出全部放到利润大根堆中，增大M，减少K，直到K个项目做完
 */
public class ProjectMaxProfit {
    public static void main(String[] args) {
        CostProfit[] cps=new CostProfit[6];
        cps[0]=new CostProfit(1, 1);
        cps[1]=new CostProfit(1, 4);
        cps[2]=new CostProfit(2, 3);
        cps[3]=new CostProfit(2, 7);
        cps[4]=new CostProfit(3, 2);
        cps[5]=new CostProfit(4, 10);
        //
        int maxProfit=projectMaxProfit(0, 4, cps);
        System.out.println(maxProfit);
    }
    static class CostProfit{
        int cost;
        int profit;
        public CostProfit(int cost,int profit) {
            this.cost=cost;
            this.profit=profit;
        }
    }
    /**
     * 
     * @param M 启动资金
     * @param K 做K个项目
     * @return
     */
    public static int projectMaxProfit(int M,int K,CostProfit[] cps) {
        int r=0;
        PriorityQueue<CostProfit> smlq=new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
        PriorityQueue<CostProfit> bigq=new PriorityQueue<>((o1,o2)->o2.profit-o1.profit);
        for (CostProfit cp : cps) {
            smlq.offer(cp);
        }

        for (int i = 0; i < K; i++) {
            while(!smlq.isEmpty()){
                if(smlq.peek().cost<=M){
                    bigq.offer(smlq.poll());
                }else break;//
            }
            if(bigq.isEmpty()) return r;//
            int profit=bigq.poll().profit;
            M+=profit;
            r+=profit;
        }

        return r;
    }
}
