package com.xcrj.basic.pass3.binary_idx_tree;

public class BinaryIdxTree {
    int[] tree;
    int N;

    BinaryIdxTree(int size) {
        N = size;
        tree = new int[N + 1];//第0个位置不用
    }

    //求前缀和 往左上走 累加每个结点的值
    int sum(int idx) {
        int ans = 0;
        while (idx > 0) {
            ans += tree[idx];
            //mostRight1 = x&(~x+1) = idx&-idx
            idx -= idx & -idx;//idx-=lowbot(idx)
        }
        return ans;
    }

    //更新树状数组 往右上走 每个结点的值都加上d
    void add(int idx, int d) {
        while (idx <= N) {
            tree[idx] += d;
            idx += idx & -idx;//idx+=lowbot(idx)
        }
    }
}
