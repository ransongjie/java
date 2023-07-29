package com.xcrj.basic.pass1.hash;

import java.util.HashMap;
import java.util.Map;

//随机池, 相同key只保留首次key
public class RandomPool<T> {
    public static void main(String[] args) {
        RandomPool<String> randomPool=new RandomPool<>();
        randomPool.insert("xcrj");
        randomPool.insert("xcrj1");
        randomPool.insert("xcrj2");
        String str=randomPool.getRandom();
        System.out.println(str);
    }
    Map<T,Integer> map1;
    Map<Integer,T> map2;
    int size;
    public RandomPool(){
        this.map1=new HashMap<>();
        this.map2=new HashMap<>();
        this.size=0;
    }
    public T getRandom() {
        int ri=(int)(Math.random()*this.size);
        return this.map2.get(ri);//key不存在则返回null
    }
    public void insert(T key) {
        if(!map1.containsKey(key)){//
            map1.put(key, size);
            map2.put(size++, key);
        }
    }
    //目的：保证map2<Idx,>的连续性, getRandom时概率才能一致
    public void del(T key) {
        if(map1.containsKey(key)){
            int delIdx=map1.get(key);
            int lstIdx=--size;
            T lstStr=map2.get(lstIdx);
            map1.put(lstStr, delIdx);//
            map2.put(delIdx, lstStr);//
            map1.remove(key);//
            map2.remove(--size);//
        }
    }
}
