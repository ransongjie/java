package com.xcrj.basic.pass2.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 随机池, 相同key只保留首次key
 */
public class RandomPool<T> {
    public static void main(String[] args) {
        RandomPool<String> randomPool=new RandomPool<>();
        randomPool.insert("xcrj");
        randomPool.insert("xcrj1");
        randomPool.insert("xcrj2");
        String str=randomPool.getRandom();
        System.out.println(str);
        
        randomPool.delete("xcrj");
        str=randomPool.getRandom();
        System.out.println(str);
    }

    private Map<T,Integer> tIdx;
    private Map<Integer,T> idxT;
    private int size;
    public RandomPool(){
        this.tIdx=new HashMap<>();
        this.idxT=new HashMap<>();
        this.size=0;
    }

    public T getRandom() {
        int idx=(int)(Math.random()*this.size);
        return idxT.get(idx);
    }

    public void insert(T t){
        if(!tIdx.containsKey(t)){
            tIdx.put(t, size);
            idxT.put(size, t);
            size++;
        }
    }

    //要连续不能出现空洞，否则不是等概率的
    //使用末尾的元素填充想删除元素的idx
    public void delete(T t){
        if(tIdx.containsKey(t)){
            int delIdx=tIdx.get(t);
            int lstIdx=size-1;
            T lst=idxT.get(lstIdx);
            tIdx.put(lst,delIdx);
            idxT.put(delIdx, lst);
            tIdx.remove(t);
            idxT.remove(lstIdx);
            size--;
        }
    }
}
