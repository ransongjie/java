package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 109. 开密码锁
 * 一个密码锁由 4 个环形拨轮组成，每个拨轮都有 10 个数字(0~9)
 * - 初始数字 0000
 * - 结束数字 9999
 * - 9的下一个数字是0
 * - 0的上一个数字是9
 * <p>
 * deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定
 * target 代表可以解锁的数字，
 * - 请给出解锁需要的最小旋转次数
 * - 如果无论如何不能解锁，返回 -1
 */
public class Solution109 {
    /**
     * 广度优先遍历+建立树
     */
    public int openLock(String[] deadends, String target) {
        // 0000是目标密码，返回0，0次旋转次数
        if ("0000".equals(target)) return 0;

        // hash表记录死亡密码
        // 0000是死亡密码返回-1
        Set<String> deadendSet = new HashSet<>();
        for (String str : deadends) {
            deadendSet.add(str);
        }
        if (deadendSet.contains("0000")) return -1;

        // step记录广度优先遍历的步数
        int step = 0;
        // 广度优先所需队列
        Queue<String> que = new ArrayDeque<>();
        que.offer("0000");
        // hash表记录是否visited
        Set<String> visited = new HashSet<>();
        visited.add("0000");

        // 开始广度优先遍历
        while (!que.isEmpty()) {
            step++;
            // 记录que size
            int size = que.size();
            // 为什么使用for循环，因为广度优先遍历需要把邻接点全部遍历完
            // for (int i = 0; i < que.size(); i++) {  错误，只遍历队列中当前层的结点
            for (int i = 0; i < size; i++) {
                String currKey = que.poll();
                // 获取当前密码的下一批可能密码
                for (String nextKey : getNextKeys(currKey)) {
                    if (!visited.contains(nextKey) && !deadendSet.contains(nextKey)) {
                        if (target.equals(nextKey)) return step;
                        que.offer(nextKey);
                        visited.add(nextKey);
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 获取上一个数字
     * 0的上一个数字是9
     */
    private char getPrev(char c) {
        // ！字符 加上 ''
        return c == '0' ? '9' : (char) (c - 1);
    }

    /**
     * 获取下一个数字
     * 9的上一个数字是0
     */
    private char getNext(char c) {
        // ！字符 加上 ''
        return c == '9' ? '0' : (char) (c + 1);
    }

    /**
     * 枚举status的下一个可能密码
     * 4个转轮，每个转轮可往前可往后
     */
    private List<String> getNextKeys(String currKey) {
        List<String> nextKeys = new ArrayList<>();
        char[] chars = currKey.toCharArray();
        // 4个转轮
        for (int i = 0; i < 4; i++) {
            char temp = chars[i];
            // 获取前1个可能的密码
            chars[i] = getPrev(temp);
            nextKeys.add(new String(chars));
            // 获取后1个可能的密码
            chars[i] = getNext(temp);
            nextKeys.add(new String(chars));
            chars[i] = temp;
        }
        return nextKeys;
    }
}
