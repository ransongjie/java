package com.xcrj.offer_special.pass4;

import java.util.HashMap;
import java.util.Map;

/**
 * 插入、删除和随机访问都是 O(1) 的容器
 */
public class Solution030 {
}

class RandomizedSet {
    Map<Integer, Integer> idxVal;
    Map<Integer, Integer> valIdx;
    int size;

    public RandomizedSet() {
        this.idxVal = new HashMap<>();
        this.valIdx = new HashMap<>();
        this.size = 0;
    }

    public boolean insert(int val) {
        if (valIdx.containsKey(val)) {
            return false;
        }
        this.valIdx.put(val, size);
        this.idxVal.put(size++, val);
        return true;
    }

    /**
     * 以交换的方式补上因为删除导致idxVal的空洞
     * getRandom()才能等概率返回
     *
     * @param val
     * @return
     */
    public boolean remove(int val) {
        if (!valIdx.containsKey(val)) {
            return false;
        }
        int delIdx = valIdx.get(val);
        int lstVal = idxVal.get(--size);
        idxVal.put(delIdx, lstVal);
        valIdx.put(lstVal, delIdx);
        valIdx.remove(val);
        idxVal.remove(size);
        return true;
    }

    public int getRandom() {
        if (size == 0) return -1;
        int idx = (int) (Math.random() * size);
        return idxVal.get(idx);
    }
}
