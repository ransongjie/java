package com.xcrj.offer_special.pass4;

import java.util.HashMap;

/**
 * 最近最少使用缓存
 */
public class Solution031 {
}

class LRUCache {
    MyCache<Integer, Integer> myCache;

    /**
     * @param m 初始容量
     */
    public LRUCache(int m) {
        myCache = new MyCache<>(m);
    }

    public int get(int key) {
        Integer value = myCache.get(key);
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        myCache.put(key, value);
    }
}

/**
 * 真正cache的实现
 *
 * @param <K>
 * @param <V>
 */
class MyCache<K, V> {
    //容量
    final int capacity;
    //hash
    HashMap<K, Node0<K, V>> keyNode;
    //双向链表
    NodeDoubleLinkedList<K, V> nodeList;

    MyCache(int capacity) {
        this.capacity = capacity;
        keyNode = new HashMap<>();
        nodeList = new NodeDoubleLinkedList<>();
    }

    //所操作的结点会放到尾部
    V get(K k) {
        if (keyNode.containsKey(k)) {
            Node0<K, V> node = keyNode.get(k);
            nodeList.moveNodeToTail(node);//
            return node.value;
        }
        return null;
    }

    //所操作的结点会放到尾部
    void put(K k, V v) {
        if (keyNode.containsKey(k)) {
            Node0<K, V> node = keyNode.get(k);
            node.value = v;
            nodeList.moveNodeToTail(node);//
        } else {
            Node0<K, V> node = new Node0<>(k, v);
            keyNode.put(k, node);
            nodeList.addNode(node);//
            if (keyNode.size() == capacity + 1) {//
                removeMostUnused();
            }
        }
    }

    //到达容量限制，移除最近未使用的结点，头部结点
    void removeMostUnused() {
        Node0<K, V> h = nodeList.removeHead();
        keyNode.remove(h.key);
    }
}

/**
 * 双向链表
 *
 * @param <K>
 * @param <V>
 */
class NodeDoubleLinkedList<K, V> {
    Node0<K, V> head;
    Node0<K, V> tail;

    NodeDoubleLinkedList() {
        head = null;
        tail = null;
    }

    void addNode(Node0<K, V> newNode) {
        if (newNode == null) {
            return;
        }
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.nxt = newNode;
            newNode.pre = tail;
            tail = newNode;
        }
    }

    void moveNodeToTail(Node0<K, V> node) {
        if (tail == node) {
            return;
        }
        if (head == node) {
            head = head.nxt;
            head.pre = null;
        } else {
            node.pre.nxt = node.nxt;
            node.nxt.pre = node.pre;
        }
        tail.nxt = node;
        node.pre = tail;
        node.nxt = null;
        tail = node;
    }

    Node0<K, V> removeHead() {
        if (head == null) {
            return null;
        }
        Node0<K, V> h = head;
        if (head == tail) {//
            head = null;
            tail = null;
        } else {
            head = head.nxt;
            head.pre = null;
        }
        h.nxt = null;
        return h;
    }
}

/**
 * 结点
 *
 * @param <K>
 * @param <V>
 */
class Node0<K, V> {
    K key;
    V value;
    Node0<K, V> nxt;
    Node0<K, V> pre;

    Node0(K k, V v) {
        key = k;
        value = v;
    }
}