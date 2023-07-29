package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 * 下面的操作时间复杂度都是O(1)：
 * - insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * - remove(val)：当元素 val 存在时返回 true，并从集合中移除该项，否则返回 false。
 * - getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 */
public class Solution30 {
    class RandomizedSet {
        // List<值>
        List<Integer> vals;
        // Map<值,索引>
        Map<Integer,Integer> valIdxMap;
        // 随机对象
        Random random;

        public RandomizedSet(){
            this.vals=new ArrayList<>();
            this.valIdxMap=new HashMap<>();
            this.random=new Random();
        }

        /**
         * 存在则返回false
         * 不存在则插入
         * - 放入list末尾
         * - 放入map值和索引
         * @param val
         * @return
         */
        public boolean insert(int val) {
            if(this.valIdxMap.containsKey(val)){
                return false;
            }
            this.vals.add(val);
            this.valIdxMap.put(val, this.vals.size()-1);
            return true;
        }

        /**
         * 不存在则返回false
         * 存在则删除
         * @param val
         * @return
         */
        public boolean remove(int val) {
            if(!this.valIdxMap.containsKey(val)){
                return false;
            }

            /* 查询 */
            // 从map中获取val的索引
            int idx=this.valIdxMap.get(val);
            // 从list中获取最后1个结点的值
            int lastIdx=this.vals.size()-1;
            int lastVal=this.vals.get(lastIdx);
            
            /* 覆盖 */
            // 使用lastVal覆盖val
            this.vals.set(idx,lastVal);
            // 更新索引lastVal的索引
            this.valIdxMap.put(lastVal,idx);

            /* 删除 */
            // 删除list中最后1个结点
            this.vals.remove(lastIdx);
            // 删除map中指定key
            this.valIdxMap.remove(val);
            return true;
        }

        /**
         * 从list中随机获取1个值
         * @return
         */
        public int getRandom() {
            return this.vals.get(this.random.nextInt(this.vals.size()));
        }
    }
}
