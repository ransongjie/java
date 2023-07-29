package com.xcrj.basic.pass1.force;
//背包问题, 所装货物最大收益
public class Knapsack {
    public static void main(String[] args) {
        int availableWeight=10;
        int[] weights={1,2,1,5,7};
        int[] values={2,4,3,6,5};
        System.out.println(knapsack2(0, availableWeight, weights, values));
    }

    public static int knapsack(int i,int availableWeight,int[] weights,int[] values) {
        if(i==weights.length){//从i往后选择货物, 无货物可选
            return 0;
        }
        if(weights[i]>availableWeight){
            return 0;
        }
        int v=0;
        //拿第i件货物或者不拿的最大收益
        v=Math.max(v+values[i]+knapsack(i+1, availableWeight-weights[i], weights, values), 
                   v+knapsack(i+1, availableWeight, weights, values));
        return v;
    }

    public static int knapsack2(int i,int availableWeight,int[] weights,int[] values) {
        if(i==weights.length){//从i往后选择货物, 无货物可选
            return 0;
        }
        if(weights[i]>availableWeight){
            return 0;
        }
        
        //拿第i件货物或者不拿的最大收益
        return Math.max(values[i]+knapsack2(i+1, availableWeight-weights[i], weights, values), 
                   knapsack2(i+1, availableWeight, weights, values));
    }
}
