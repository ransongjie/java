package com.xcrj.company.pass1;

import java.util.HashMap;

/**
 * 数据结构设计
 * O(1)时间put,get,setAll的HashMap
 */
public class A004SetAll {

    static class VT{
        Integer value;
        Long time;
        VT(){
        }
        void put(Integer v,Long t){
            value=v;
            time=t;
        }
    }

    static class EH{
        HashMap<Integer,VT> hh;
        Long time=0L;
        Long allTime=Long.MAX_VALUE;
        Integer all=null;
        EH(){
            hh=new HashMap<>();
        }

        void set(Integer k,Integer v){
            if(!hh.containsKey(k)){
                hh.put(k,new VT());
            }
            hh.get(k).put(v,time++);
        }

        //将所有entry的值设置为v。要求 O(1)
        void setAll(Integer v){
            all=v;
            allTime=time;
            time++;
        }

        Integer get(Integer k){
            if(!hh.containsKey(k)) return null;
            if(hh.get(k).time<allTime) return all;
            return hh.get(k).value;
        }
    }
}
