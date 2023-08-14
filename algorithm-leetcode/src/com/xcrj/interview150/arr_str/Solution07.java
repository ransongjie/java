package com.xcrj.interview150.arr_str;

import java.util.*;

/**
 * https://leetcode.cn/problems/insert-delete-getrandom-o1
 * O(1) 时间插入、删除和获取随机元素
 */
public class Solution07 {

    static class RandomizedSet {
        //<idx,val>
        List<Integer> idxVals;
        //<val,idx>
        Map<Integer, Integer> valIdx;
        Random random;

        public RandomizedSet() {
            idxVals = new ArrayList<>();
            valIdx = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            //已经存在则不进行插入
            if (valIdx.containsKey(val)) return false;
            idxVals.add(val);
            valIdx.put(val, idxVals.size() - 1);//
            return true;
        }

        public boolean remove(int val) {
            if (!valIdx.containsKey(val)) return false;
            //将要删除的元素与最后一个元素交换
            int delIdx = valIdx.get(val);
            int lstIdx = idxVals.size() - 1;
            int lstVal = idxVals.get(lstIdx);
            //list和map都要修改
            idxVals.set(delIdx, lstVal);
            valIdx.put(lstVal, delIdx);
            idxVals.remove(lstIdx);
            valIdx.remove(val);
            return true;
        }

        public int getRandom() {
            int idx = random.nextInt(idxVals.size());
            return idxVals.get(idx);
        }
    }

    static class RandomizedSet2 {
        HashMap<Integer, Integer> valIdx;
        HashMap<Integer, Integer> idxVal;
        int size;

        public RandomizedSet2() {
            valIdx = new HashMap<>();
            idxVal = new HashMap<>();
            size = 0;
        }

        public boolean insert(int val) {
            if (valIdx.containsKey(val)) return false;
            valIdx.put(val, size);
            idxVal.put(size++, val);
            return true;
        }

        public boolean remove(int val) {
            if (!valIdx.containsKey(val)) return false;
            Integer delIdx = valIdx.get(val);
            Integer lastVal = idxVal.get(size - 1);
            valIdx.put(lastVal, delIdx);
            idxVal.put(delIdx, lastVal);
            valIdx.remove(val);
            idxVal.remove(--size);
            return true;
        }

        public int getRandom() {
            if (size == 0) return -1;
            int idx = (int) (Math.random() * size);
            return idxVal.get(idx);
        }
    }

}
