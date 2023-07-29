package com.xcrj.company.pass1;

//class23/Code04_FindKMajority
public class A033SuperWater {
    public static void superWater(int[]as){
        int cand=0,HP=0;
        for (int i = 0; i < as.length; i++) {
            if(HP==0){
                cand=as[i];
            }else if(cand==as[i]){
                HP++;
            }else{
                HP--;
            }
        }
        if(HP==0){
            System.out.println("没有水王数");
            return;
        }

        //HP变成计数变量
        HP=0;
        for (int i = 0; i < as.length; i++) {
            if(as[i]==cand){
                HP++;
            }
        }
        if(HP>(as.length/2)){
            System.out.println("水王数: "+cand);
        }else{
            System.out.println("没有水王数");
        }
    }
}
