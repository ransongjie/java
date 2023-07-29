package com.xcrj.company.pass1;

import java.util.HashMap;

public class A057AddDelGetRandom {
    static class RandomSet{
        HashMap<Integer,Integer> valIdx;
        HashMap<Integer,Integer> idxVal;
        int size;
        RandomSet(){
            valIdx=new HashMap<>();
            idxVal=new HashMap<>();
            size=0;
        }
        public void add(int val){
            valIdx.put(val,size);
            idxVal.put(size++,val);
        }
        //删除需要保证idx连续，否则产生idx空洞，可能多次rand才能返回值，不能等概率返回
        public void del(int val){
            if(!valIdx.containsKey(val)) return;
            Integer delIdx=valIdx.get(val);
            Integer lastVal=idxVal.get(size-1);
            valIdx.put(lastVal,delIdx);
            idxVal.put(delIdx,lastVal);
            valIdx.remove(val);
            idxVal.remove(--size);
        }
        public int getRand(){
            if(size==0) return -1;
            int idx=(int)(Math.random()*size);
            return idxVal.get(idx);
        }
    }

}
