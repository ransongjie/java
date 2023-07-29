package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 剑指 Offer II 031. 最近最少使用缓存
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 * <p>
 * LRU：操作系统/内存管理/内存分配/离散分配/请求分页方式/页面置换算法/LRU(最近最少使用)
 * - 队列数据结构，队列长度固定，队尾进入，对头出，如果队尾进入元素已经在队列中则把队列中的元素去掉再放入队列，队列满了对头才出1个元素再放入要进入的元素
 * <p>
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */
public class Solution31 {

    /**
     * 使用java提供的public LinkedHashMap(int initialCapacity,float loadFactor,boolean accessOrder)
     * accessOrder=true基于访问的顺序，使用LRU(lest resently used)算法
     * 本质上需要将hash表存储key和索引+list存储值结合起来
     */
    class LRUCache1 extends LinkedHashMap<Integer, Integer> {
        int capacity;

        /**
         * 初始化 LRU 缓存
         */
        public LRUCache1(int capacity) {
            // !!! accessOrder=true基于访问的顺序，使用LRU(lest resently used)算法
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        /**
         * 存在与缓存中则返回值
         * 不存在则返回-1
         */
        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        /**
         * 关键字存在则变更其值
         * 不存在则插入关键字和值
         */
        public void put(int key, int value) {
            super.put(key, value);
        }

        /**
         * 重新方法
         * 缓存达到容量上限，删除最久未使用的数据值
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /**
     * 自实现类LinkedHashMap功能
     * 多线程不安全 有待优化
     */
    class LRUCache2 {
        /**
         * 双向链表结点
         */
        class DNode {
            int key;
            int value;
            DNode pre;
            DNode next;

            public DNode() {
            }

            public DNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        // 容量
        int capacity;
        // 大小
        int size;
        Map<Integer, DNode> map;
        // fake head
        DNode fHead;
        // fake tail
        DNode fTail;

        public LRUCache2(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.map = new HashMap<>(capacity);
            this.fHead = new DNode();
            this.fTail = new DNode();
            this.fHead.next = this.fTail;
            this.fTail.pre = this.fHead;
        }

        /**
         * 存在与缓存中则返回值
         * - 移动到头部
         * 不存在则返回-1
         */
        public int get(int key) {
            if (!this.map.containsKey(key)) return -1;
            return moveToHead(key).value;
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
         * -- 删除双向链表尾部结点
         * -- 删除map中这个尾部结点
         * -- size--
         */
        public void put(int key, int value) {
            if (this.map.containsKey(key)) {
                DNode dNode = moveToHead(key);
                dNode.value = value;
                this.map.put(key, dNode);
                return;
            }

            DNode dNode = new DNode(key, value);
            addToHead(dNode);
            this.map.put(key, dNode);
            this.size++;

            if (size > capacity) {
                this.map.remove(this.fTail.pre.key);
                removeTail();
                this.size--;
            }
        }

        /**
         * 删除结点，将结点添加到头部
         */
        private DNode moveToHead(int key) {
            DNode dNode = this.map.get(key);
            delDNode(dNode);
            addToHead(dNode);
            return dNode;
        }

        private void delDNode(DNode dNode) {
            dNode.pre.next = dNode.next;
            dNode.next.pre = dNode.pre;
        }

        private void addToHead(DNode dNode) {
            // !! 先操作要插入的结点
            dNode.pre = this.fHead;
            dNode.next = this.fHead.next;
            // !! 再操作两边的结点
            this.fHead.next.pre = dNode;
            this.fHead.next = dNode;
        }

        private void removeTail() {
            this.fTail.pre.pre.next = fTail;
            this.fTail.pre = this.fTail.pre.pre;
        }
    }
}
