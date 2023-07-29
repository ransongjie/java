package com.xcrj.pass1;

public class SelfHashMap<K, V> {
    /**
     * 内部类
     * xcrj 泛型，类型检查，类型擦除
     * 使用：传入具体类型的位置
     */
    class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // 头插法
        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * capacity: 容量=数组长度，有默认容量
     * size: 大小, 散列表中实际存储元素个数
     * LOAD_FACTOR>size/capacity=size/length
     */
    // 数组
    private Node<K, V>[] nodes;
    // 默认容量，数组容量
    private final int DEFAULT_CAPACITY = 16;
    // 装填因子=实际元素个数/散列表容量>size/capacity
    // 因此扩容条件 size>=length*LOAD_FACTOR, 数组length=capacity
    private final float LOAD_FACTOR = 0.75f;
    // 大小，实际元素个数
    private int size;

    /**
     * 无参构造，默认容量
     */
    public SelfHashMap() {
        this.nodes = new Node[this.DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * 有参构造，指定容量
     * 
     * @param capacity 指定容量
     */
    public SelfHashMap(int capacity) {
        this.nodes = new Node[capacity];
        size = 0;
    }

    /**
     * 大小，散列表中实际存储元素个数
     * @return size
     */
    public int size(){
        return this.size;
    }

    /**
     * 获取节点存储位置
     * 散列函数，除留余数法
     * 
     * @param key    键
     * @param length 散列表容量，数组（数组）长度
     * @return 存储位置
     */
    private int getIdx(K key, int length) {
        int hashCode = key.hashCode();
        // hashCode可能为负数
        int idx = Math.abs(hashCode % length);
        return idx;
    }

    /**
     * put方法
     * - 是否需要扩容
     * - 添加值
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (size >= this.nodes.length * this.LOAD_FACTOR)
            resize();
        putValue(key, value, this.nodes);
    }

    /**
     * 根据key获取value
     * - 判断数组是否为空
     * - 计算hash，获取idx位置的结点
     * - 结点为空，返回null
     * - 结点非空，遍历链表，获取值
     * 
     * @param key
     * @return value
     */
    public V get(K key) {
        //
        if (this.nodes == null)
            return null;
        //
        int idx = getIdx(key, this.nodes.length);
        Node<K, V> node = this.nodes[idx];
        if (node == null)
            return null;
        //
        while (node != null) {
            if ((node.key.hashCode() == key.hashCode())
                    && (node.key == key || node.key.equals(key))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 扩容
     * - 创建新数组，长度增大2倍
     * - 转移结点，重新hash
     * - 新数组替换旧数组
     */
    private void resize() {
        Node<K, V>[] nodes = new Node[this.nodes.length * 2];
        rehash(nodes);
        this.nodes = nodes;
    }

    /**
     * 转移结点，重新hash
     * - size=0，因为putValue中会重新计算size
     * - 遍历旧数组
     * -- 结点为空，跳过继续
     * -- 结点非空，遍历链表，putValue(key,value,newNodes)
     * 
     * @param newNodes 新数组
     */
    private void rehash(Node<K, V>[] newNodes) {
        this.size = 0;
        for (Node<K, V> node : this.nodes) {
            if (node == null)
                continue;
            while (node != null) {
                putValue(node.key, node.value, newNodes);
                node = node.next;
            }
        }
    }

    /**
     * 存储元素
     * - 获取插入位置
     * - 插入位置为空，创建新结点
     * - 插入位置非空，遍历链表，相同key使用新value覆盖，否则插入到头部
     * 
     * @param key
     * @param value
     * @param nodes 数组
     */
    private void putValue(K key, V value, Node<K, V>[] nodes) {
        //
        int idx = getIdx(key, nodes.length);
        Node node = nodes[idx];
        if (node == null) {
            nodes[idx] = new Node(key, value);
            this.size++;
            return;
        }
        //
        while (node != null) {
            if ((key.hashCode() == node.key.hashCode())
                    && (key == node.key || key.equals(node.key))) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        //
        Node head = new Node(key, value, nodes[idx]);
        nodes[idx] = head;
        size++;
        return;
    }

}
