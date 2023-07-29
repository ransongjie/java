package com.xcrj.company.pass1;

/**
 * 体力跳台阶的最少次数
 */
public class A019MinStep {
    public static int step(int[]as){
        int step=0,cur=0,nxt=0;
        for (int i = 0; i < as.length; i++) {
            //cur 跳step步最远能到哪儿
            if(cur<i){//能覆盖就不多跳
                step++;
                cur=nxt;
            }

            //nxt 多跳1步(step+1)最远能到哪里
            nxt=Math.max(nxt,i+as[i]);
        }
        return step;
    }
}
