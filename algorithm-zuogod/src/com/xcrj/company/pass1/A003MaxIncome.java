package com.xcrj.company.pass1;

import java.util.Arrays;

/**
 * 平分司机到AB区域的最高收入
 */
public class A003MaxIncome {
    /**
     * income[i][0] 第i个司机去A区域的收入
     * income[i][1] 第i个司机去B区域的收入
     * @param income
     * @return
     */
    public static int maxIncome(int[][]income){
        //调往A和B区域的司机数量一样多
        if(income==null||income.length<2||(income.length&1)!=0){
            return 0;
        }
        int A=(income.length>>1);
        return process(income,0,A);
    }

    /**
     *
     * @param income
     * @param idx 第idx个司机
     * @param need A区域还需要的司机
     * @return
     */
    private static int process(int[][]income,int idx,int need){
        //无司机可调用了，自然没有收益了
        if(idx==income.length){
            return 0;
        }
        //只能调往A区域，剩下的司机=A区域还需要的司机
        if((income.length-idx)==need){
            return income[idx][0]+process(income,idx+1,need-1);
        }
        //只能调往B区域，A区域不需要司机了
        if(need==0){
            return income[idx][1]+process(income,idx+1,need);
        }

        //调往A或B区域都可以
        int aa=income[idx][0]+process(income,idx+1,need-1);
        int bb=income[idx][1]+process(income,idx+1,need);
        return Math.max(aa,bb);
    }

    public static int maxIncome2(int[][]income){
        //调往A和B区域的司机数量一样多
        if(income==null||income.length<2||(income.length&1)!=0){
            return 0;
        }
        int A=(income.length>>1);
        //边界条件
        int[][]mem=new int[income.length+1][A+1];
        for (int i = 0; i < income.length+1; i++) {
            Arrays.fill(mem[i],-1);
        }
        return process2(income,0,A,mem);
    }

    //缓存命中与否
    private static int process2(int[][]income,int idx,int need,int[][]mem){
        if(mem[idx][need]!=-1)return mem[idx][need];

        if(income.length==idx){
            mem[idx][need]=0;
            return mem[idx][need];
        }

        //只能调往A区域，剩下的司机=A区域还需要的司机
        if((income.length-idx)==need){
            mem[idx][need]=income[idx][0]+process(income,idx+1,need-1);
            return mem[idx][need];
        }
        //只能调往B区域，A区域不需要司机了
        if(need==0){
            mem[idx][need]=income[idx][1]+process(income,idx+1,need);
            return mem[idx][need];
        }

        //调往A或B区域都可以
        int aa=income[idx][0]+process(income,idx+1,need-1);
        int bb=income[idx][1]+process(income,idx+1,need);
        mem[idx][need]=Math.max(aa,bb);
        return mem[idx][need];
    }
}
