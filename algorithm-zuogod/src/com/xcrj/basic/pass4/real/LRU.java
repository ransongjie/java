package com.xcrj.basic.pass4.real;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用缓存
 * 双向链表尾部是最近使用过的页面，头部最近最少使用的页面
 * get和put操作将页面放到双向链表尾部
 */
public class LRU {
    MyCache<Integer, Integer> myCache;

    /**
     * @param cap 初始容量
     */
    LRU(int cap) {
        myCache = new MyCache<>(cap);
    }

    /**
     * 获取page
     *
     * @param key
     * @return
     */
    int get(int key) {
        Integer value = myCache.getPage(key);
        return value == null ? -1 : value;
    }

    /**
     * 添加page
     *
     * @param key
     * @param value
     */
    void put(int key, int value) {
        myCache.putPage(key, value);
    }
}

/**
 * 双向链表结点
 *
 * @param <K>
 * @param <V>
 */
class Node<K, V> {
    //page key
    K key;
    //page value
    V val;
    Node<K, V> pre;
    Node<K, V> nxt;

    Node(K k, V v) {
        key = k;
        val = v;
    }
}

/**
 * 双向链表
 *
 * @param <K>
 * @param <V>
 */
class DLinkedList<K, V> {
    //双向链表 只需要记录头结点和尾结点
    Node<K, V> head;
    Node<K, V> tail;

    DLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * 增加结点到尾部
     *
     * @param node
     */
    void addNodeToTail(Node<K, V> node) {
        if (node == null) return;
        if (head == tail) {
            head = node;
            tail = node;
        } else {
            tail.nxt = node;
            node.pre = tail;
            tail = node;
        }
        node.nxt = null;
    }

    /**
     * 删除头结点
     */
    void rmHead() {
        if (head == null) return;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.nxt;
            head.pre = null;
        }
    }

    /**
     * 移动结点到尾部
     *
     * @param node
     */
    void mvNodeToTail(Node<K, V> node) {
        if (tail == node) return;
        if (head == node) {
            head = head.nxt;
            head.pre = null;
        } else {
            node.pre.nxt = node.nxt;
            node.nxt.pre = node.pre;
        }
        addNodeToTail(node);
    }
}

class MyCache<K, V> {
    //容量
    final int capacity;
    //页面是否存在 <pageKey,node>
    Map<K, Node<K, V>> map;
    //双向链表
    DLinkedList<K, V> dLinkedList;

    MyCache(int cap) {
        capacity = cap;
        map = new HashMap<>();
        dLinkedList = new DLinkedList<>();
    }

    /**
     * 获取page
     *
     * @param k
     * @return
     */
    V getPage(K k) {
        if (!map.containsKey(k)) return null;
        Node<K, V> node = map.get(k);
        dLinkedList.mvNodeToTail(node);
        return node.val;
    }

    /**
     * 添加page
     *
     * @param k page key
     * @param v page val
     */
    void putPage(K k, V v) {
        if (map.containsKey(k)) {
            //更新 node.val
            Node<K, V> node = map.get(k);
            node.val = v;
            dLinkedList.mvNodeToTail(node);
        } else {
            //添加 新node
            Node<K, V> node = new Node<>(k, v);
            map.put(k, node);
            dLinkedList.addNodeToTail(node);
            //容量限制
            if (map.size() == capacity + 1) {
                map.remove(dLinkedList.head);
                dLinkedList.rmHead();
            }
        }
    }
}