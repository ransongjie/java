package com.xcrj.company.pass1;

import java.util.HashMap;

public class A029LRUCache {
    MyCache<Integer,Integer> myCache;
    /**
     * @param m 初始容量
     */
    public A029LRUCache(int m){
        myCache=new MyCache<>(m);
    }

    public int get(int key){
        Integer value=myCache.get(key);
        return value==null?-1:value;
    }

    public void put(int key,int value){
        myCache.put(key,value);
    }

    /**
     * 真正cache的实现，容量，hash表，value的双向链表
     * get
     * set
     * removeMostUnused 移除最近未使用的
     */
    static class MyCache<K,V>{
        final int capacity;
        // 判断页面是否存在
        HashMap<K,Node<K,V>> keyNode;
        // 最久为使用的页面在head，最近使用的页面（包括get和put）在tail
        NodeDoubleLinkedList<K,V> nodeList;

        MyCache(int capacity){
            this.capacity=capacity;
            keyNode=new HashMap<>();
            nodeList=new NodeDoubleLinkedList<>();
        }

        //所操作的结点会放到尾部
        V get(K k){
            if(keyNode.containsKey(k)){
                Node<K,V> node= keyNode.get(k);
                nodeList.moveNodeToTail(node);//
                return node.value;
            }
            return null;
        }

        //所操作的结点会放到尾部
        void put(K k,V v){
            if(keyNode.containsKey(k)){
                Node<K,V> node=keyNode.get(k);
                node.value=v;
                nodeList.moveNodeToTail(node);//
            }else{
                Node<K,V> node=new Node<>(k,v);
                keyNode.put(k,node);
                nodeList.addNode(node);//
                if(keyNode.size()==capacity+1){//
                    removeMostUnused();
                }
            }
        }

        //到达容量限制，移除最近未使用的结点，头部结点
        void removeMostUnused(){
            Node<K,V> h=nodeList.removeHead();
            keyNode.remove(h.key);
        }
    }

    /**
     * 双向链表中的结点，记录k-v，下一个结点是谁，上一个结点是谁
     */
    static class Node<K,V>{
        K key;
        V value;
        Node<K,V> nxt;
        Node<K,V> pre;
        Node(K k,V v){
            key=k;
            value=v;
        }
    }

    /**
     * 双向链表 记录头结点和尾部结点
     * addNode
     * moveNodeToTail
     * removeHead
     */
    static class NodeDoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;
        NodeDoubleLinkedList(){
            head=null;
            tail=null;
        }
        void addNode(Node<K,V> newNode){
            if(newNode==null){
                return;
            }
            if(head==null){
                head=newNode;
                tail=newNode;
            }else{
                tail.nxt=newNode;
                newNode.pre=tail;
                tail=newNode;
            }
        }
        void moveNodeToTail(Node<K,V> node){
            if(tail==node){
                return;
            }
            if(head==node){
                head=head.nxt;
                head.pre=null;
            }else {
                node.pre.nxt=node.nxt;
                node.nxt.pre=node.pre;
            }
            tail.nxt=node;
            node.pre=tail;
            node.nxt=null;
            tail=node;
        }
        Node<K,V> removeHead(){
            if(head==null){
                return null;
            }
            Node<K,V> h=head;
            if(head==tail){//
                head=null;
                tail=null;
            }else{
                head=head.nxt;
                head.pre=null;
            }
            h.nxt=null;
            return h;
        }
    }
}
