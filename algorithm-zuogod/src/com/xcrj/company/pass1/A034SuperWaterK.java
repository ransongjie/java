package com.xcrj.company.pass1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//class23/Code04_FindKMajority
public class A034SuperWaterK {
    public static void superWaterK(int[]as ,int k){
        HashMap<Integer,Integer> candHP=new HashMap<>();
        for (int i = 0; i < as.length; i++) {
            if(candHP.containsKey(as[i])){
                candHP.put(as[i],candHP.get(as[i])+1);
            }else{
                if(candHP.size()==k-1){//候选表没有当前数 且 候选表满了
                    minusOne(candHP);
                }else{
                    candHP.put(as[i],1);
                }
            }
        }

        //
        if(candHP.size()==0){
            System.out.println("没有水王数");
            return;
        }

        //让HP当计数器
        for (Map.Entry<Integer, Integer> entry :candHP.entrySet()) {
            entry.setValue(0);
        }
        for (int i = 0; i < as.length; i++) {
            if(candHP.containsKey(as[i])){
                candHP.put(as[i],candHP.get(as[i])+1);
            }
        }

        boolean flag=false;
        for (Map.Entry<Integer, Integer> entry :candHP.entrySet()) {
            if(entry.getValue()>as.length/k){
                System.out.println("水王数: "+entry.getKey());
                flag=true;
            }
        }

        System.out.println(!flag?"没有水王数":"");
    }

    private static void minusOne(HashMap<Integer,Integer> candHP){
        ArrayList<Integer> candHP0=new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry :candHP.entrySet()) {
            if(entry.getValue()-1==0){
                candHP0.add(entry.getKey());
            }else{
                entry.setValue(entry.getValue()-1);
            }
        }
        for (Integer cand : candHP0) {
            candHP.remove(cand);
        }
    }
}
