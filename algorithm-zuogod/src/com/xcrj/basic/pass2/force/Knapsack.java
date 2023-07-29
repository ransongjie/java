package com.xcrj.basic.pass2.force;
/**
 * 背包问题, 所装货物最大价值
 * availableWeight 背包可承受重量
 * weights 货物重量
 * values 货物价值
 */
public class Knapsack {
    public static void main(String[] args) {
        int availableWeight=10;
        int[] weights={1,2,1,5,7};
        int[] values={2,4,3,6,5};
        System.out.println(knapsack(0, availableWeight, weights, values));
    }

    public static int knapsack(int i,int availableWeight,int[] weights,int[] values) {
        if(i==weights.length) return 0;//无货可选
        if(availableWeight<weights[i]) return 0;
        int v=0;
        return Math.max(v+values[i]+knapsack(i+1, availableWeight-weights[i], weights, values), 
                        v+knapsack(i+1, availableWeight, weights, values));
    }
}
