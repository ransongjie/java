package com.xcrj.basic.pass2.greed;

import java.util.PriorityQueue;

/**
 * 以起始资金m做k个项目能获得的最大收益
 * 做能做项目（成本<启动资金）中的最大利润项目
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
     * 以启动资金M做K个项目的最大收益
     * @param M 启动资金
     * @param K 做K个项目
     * @return
     */
    public static int projectMaxProfit(int M,int K,CostProfit[] cps) {
        int r=0;
        // 成本小根堆
        PriorityQueue<CostProfit> smlq=new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
        // 利润大根堆
        PriorityQueue<CostProfit> bigq=new PriorityQueue<>((o1,o2)->o2.profit-o1.profit);

        for (CostProfit cp : cps) {
            smlq.offer(cp);
        }

        // K个项目
        for (int i = 0; i < K; i++) {
            // 能做的项目按利润从大到小排序
            while(!smlq.isEmpty()){
                // 能做的项目
                if(smlq.peek().cost<=M){
                    // 放入利润大根堆
                    bigq.offer(smlq.poll());
                }else break;//
            }
            // 大根堆为空，一个项目也做不了（最低成本的项目都做不了）
            if(bigq.isEmpty()) return r;
            // 做能做项目（成本<启动资金）中的最大利润项目
            int profit=bigq.poll().profit;
            M+=profit;
            r+=profit;
        }

        return r;
    }
}
