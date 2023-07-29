package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true，并从集合中移除该项，否则返回 false。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 */
public class Solution30 {
    class RandomizedSet {
        // 存放值
        List<Integer> vals;
        // <值,索引>
        Map<Integer, Integer> idxMap;
        // 随机对象
        Random random;

        public RandomizedSet() {
            vals = new ArrayList<>();
            idxMap = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (idxMap.containsKey(val)) {
                return false;
            }
            vals.add(val);
            idxMap.put(val, vals.size() - 1);
            return true;
        }

        /**
         * 直接从list中删除元素 会让后面的元素依次前移时间复杂度不是O(1)
         * 因此，把要删除的位置填充，用最后1个元素去填充
         */
        public boolean remove(int val) {
            if (!idxMap.containsKey(val)) {
                return false;
            }

            int idx = idxMap.get(val);
            // 获取最后一个元素值和索引
            int lastIdx = vals.size() - 1;
            int lastVal = vals.get(lastIdx);
            // 最后1个元素去填充
            vals.set(idx, lastVal);
            idxMap.put(lastVal, idx);
            // 删除最后1个元素
            vals.remove(lastIdx);
            // 删除key=val的元素
            idxMap.remove(val);
            return true;
        }

        public int getRandom() {
            return vals.get(random.nextInt(vals.size()));
        }

    }
}
