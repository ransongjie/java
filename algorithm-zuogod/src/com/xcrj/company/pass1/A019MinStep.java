package com.xcrj.company.pass1;

/**
 * 体力跳台阶的最少次数
 * 达到目标的跳的最少次数
 * 从0跳到n-1
 * 4个体力可以一次跳4步，跳一次，可以一次跳4步，可以跳4步以内
 * 7个体力可以一次跳7步，跳一次，可以一次跳7步，可以跳7步以内
 *
 * 一次可以随便跳体力内任意距离
 * 体力内能覆盖的范围就不多跳，体力内不能覆盖的范围需要多跳一步，
 * 记录能跳的最大范围，作为下一次跳能覆盖的范围
 */
public class A019MinStep {
    public static int step(int[]as){

        int step=0,cur=0,nxt=0;
        for (int i = 0; i < as.length; i++) {
            //cur：跳step步最远能到哪儿
            if(cur<i){//能覆盖就不多跳
                step++;
                cur=nxt;
            }

            //nxt：多跳1步(step+1)最远能到哪里
            nxt=Math.max(nxt,i+as[i]);
        }
        return step;
    }
}
