package com.xcrj.basic.pass4.union_set;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 */
public class UnionFind {
    //<结点,父结点>
    static Map<UNode, UNode> UNodeFather = new HashMap<>();
    //<根结点,根节点所在集合元素数量>
    static Map<UNode, Integer> rootNum = new HashMap<>();

    /**
     * 初始化并查集
     *
     * @param as
     */
    public static void init(int[] as) {
        if (as == null || as.length == 0) return;
        for (int a :
                as) {
            UNode n = new UNode(a);
            //初始，结点的父结点是自己
            UNodeFather.put(n, n);
            //初始，根节点所在集合元素数量为1，只有根节点
            rootNum.put(n, 1);
        }
    }

    /**
     * 两个结点是否在同一集合
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameSet(UNode a, UNode b) {
        return findRoot(a) == findRoot(b);
    }

    /**
     * 合并两个结点所在集合
     *
     * @param a
     * @param b
     */
    public static void union(UNode a, UNode b) {
        if (a == null || b == null) return;
        UNode aRoot = findRoot(a);
        UNode bRoot = findRoot(b);
        if (aRoot == bRoot) return;
        UNode bigRoot = rootNum.get(aRoot) > rootNum.get(bRoot) ? aRoot : bRoot;
        UNode smlRoot = bigRoot == aRoot ? bRoot : aRoot;
        //数量小的根节点挂到数量大的根节点下
        UNodeFather.put(smlRoot, bigRoot);
        rootNum.put(bigRoot, rootNum.get(smlRoot) + rootNum.get(bigRoot));
        rootNum.remove(smlRoot);
    }

    /**
     * 寻找结点的根节点
     *
     * @param n
     * @return
     */
    private static UNode findRoot(UNode n) {
        if (n == null) return null;
        Stack<UNode> stack = new Stack<>();
        stack.push(n);
        UNode father = UNodeFather.get(n);
        while (n != father) {
            n = father;
            father = findRoot(n);
            stack.push(n);
        }

        //优化，直接与根节点相连
        while (!stack.isEmpty()) {
            UNodeFather.put(stack.pop(), n);
        }

        return n;
    }
}

class UNode {
    int v;

    UNode(int v) {
        this.v = v;
    }
}
