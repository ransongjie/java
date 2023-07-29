package com.xcrj.company.pass1;

public class A063Max01Value {
    public static int max01Value(String s){
        if(s==null||s.length()==0){
            return 0;
        }
        //转储
        int[] as=new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            as[i]=s.charAt(i)=='0'?0:1;
        }
        //暴力递归
        return process(as,0,as[0],1);
    }

    /**
     * 经过保留/删除字符的最大价值
     * @param as
     * @param idx 处理第idx个元素
     * @param lastNum 距离idx最近的数
     * @param lastValue 距离idx最近的数对应的价值
     * @return
     */
    private static int process(int[]as,int idx,int lastNum,int lastValue){
        if(idx==as.length){
            //越界没有价值
            return 0;
        }
        //保留第idx个字符
        int idxVal=lastNum==as[idx]?lastValue+1:1;
        int p1=process(as,idx+1,as[idx],idxVal);
        //删除第idx个字符
        int p2=process(as,idx+1,lastNum,lastValue);
        //idxVal+p1保留了第idx个字符，因此需要计算它的价值
        return Math.max(idxVal+p1,p2);
    }
}
