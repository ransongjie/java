package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 剑指 Offer II 031. 最近最少使用缓存
 * LRU (Least Recently Used，最近最少使用) 缓存
 * LRU：操作系统/内存管理/内存分配/离散分配/请求分页方式/页面置换算法/LRU(最近最少使用)
 * - 链表容量固定
 * - get：
 * -- 不存在返回-1；已存在移动到头部再返回值
 * - put：
 * -- 存在，移动到头部》更新值》更新map；
 * -- 不存在，创建新结点》移动到头部》放入map；
 * -- size>容量，移除map中这个结点》移除尾结点》size--
 */
public class Solution31 {
    /**
     * LinkedHashMap<Integer, Integer>
     * - accessOrder=true
     * - removeEldestEntry()
     */
    class LRUCache1 extends LinkedHashMap<Integer, Integer> {
        int capacity;

        /**
         * 构造函数
         * 
         * @param capacity
         */
        public LRUCache1(int capacity) {
            // capacity只是初始化容量
            // accessOrder=true将使用LRU(lest resently used)算法
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        /**
         * 在缓存中则返回值
         * 不再缓存中则返回-1
         * 
         * @param key
         * @return
         */
        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        /**
         * 关键字存在则更新值
         * 关键字不存在则插入值
         * 
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            super.put(key, value);
        }

        /**
         * 基于accessOrder移除最老的项，最老未被访问的项
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return super.size() > this.capacity;
        }
    }

    /**
     * 双向链表存结点
     * Map<值,结点>
     */
    class LRUCache2 {
        /**
         * 双向结点
         */
        class DNode {
            int key;
            int val;
            DNode pre;
            DNode next;

            public DNode() {

            }

            public DNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        // 容量
        int capacity;
        // 数量
        int size;
        // Map<值,结点>
        Map<Integer, DNode> map;
        // 虚伪头结点
        DNode fHead;
        // 虚伪尾结点
        DNode fTail;

        public LRUCache2(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.map = new HashMap<>(capacity);
            this.fHead = new DNode();
            this.fTail = new DNode();
            this.fHead.next = this.fTail;
            this.fTail.pre = fHead;
        }

        /**
         * 在缓存中先移动结点到头部再返回值
         * 不再缓存中则返回-1
         * 
         * @param key
         * @return
         */
        public int get(int key) {
            if (!this.map.containsKey(key)) {
                return -1;
            }
            return moveToHead(key).val;
        }

        /**
         * 删除结点，将结点添加到头部
         * 
         * @param key
         */
        private DNode moveToHead(int key) {
            DNode dNode = this.map.get(key);
            delDNode(dNode);
            addToHead(dNode);
            return dNode;
        }

        /**
         * 当前结点从双向链表中剥离
         * 
         * @param dNode
         */
        private void delDNode(DNode dNode) {
            dNode.pre.next = dNode.next;
            dNode.next.pre = dNode.pre;
        }

        /**
         * 将当前结点添加到虚伪头结点后面
         * 
         * @param dNode
         */
        private void addToHead(DNode dNode) {
            dNode.next = this.fHead.next;
            dNode.pre = this.fHead;
            this.fHead.next.pre = dNode;
            this.fHead.next = dNode;
        }

        /**
         * 关键字存在则变更其值
         * - 移动到双向链表头部
         * - 变化其值
         * - 添加到map中
         * 不存在则插入关键字和值
         * - 构建新DNode
         * - 插入双向链表头部
         * - 放入map中
         * - size++
         * - 如果size>capacity
         * -- 删除map中这个尾部结点
         * -- 删除双向链表尾部结点
         * -- size--
         * 
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            if (this.map.containsKey(key)) {
                DNode node = moveToHead(key);
                node.val = value;
                this.map.put(key, node);
                return;
            }

            DNode node = new DNode(key, value);
            addToHead(node);
            this.map.put(key, node);
            this.size++;

            if(this.size>this.capacity){
                this.map.remove(this.fTail.pre.key);
                removeTail();
                this.size--;
            }
        }

        /**
         * 移除真尾部结点
         */
        private void removeTail() {
            this.fTail.pre.pre.next=fTail;
            this.fTail.pre=this.fTail.pre.pre;
        }
    }
}
