package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/monotone-increasing-digits/submissions/494807617/
 * 监控二叉树
 * 贪心策略：
 * 局部最优：让叶子节点的父节点安摄像头，所用摄像头最少，整体最优：全部摄像头数量所用最少！
 */
public class Solution17 {
    int ans=0;
    /*
     * 思路：先给叶子节点父节点放个摄像头，然后隔两个节点放一个摄像头，直至到二叉树头结点。
     */
    public int minCameraCover(TreeNode root) {
        //root无覆盖，root需要设置摄像头
        if(minCame(root)==0) ans++;
        return ans;
    }

    /**
    后序遍历，根据左右节点的情况,来判断自己的状态
    @return: 节点的状态值
    0 表示无覆盖
    1 表示有摄像头
    2 表示有覆盖
     */
    public int minCame(TreeNode node){
        // 遇到空结点返回有覆盖，防止叶子结点被放置摄像头
        if(node==null)return 2;
        int left=minCame(node.left);
        int right=minCame(node.right);

        /*
         * 左右结点都被覆盖，可以覆盖node结点，node结点无覆盖
         * (2,2)
         */
        if(left==2&&right==2){
            return 0;
        }
        /*
         * 左右结点有1个无覆盖，node要放1个摄像头
         * (0,0) (0,1) (0,2) (1,0) (2,0)
         */
        else if(left==0||right==0){
            ans++;
            return 1;
        }
        /*
         * 左右节点至少存在1个摄像头，node被覆盖
         * (1,1) (1,2) (2,1)
         */
        else{
            return 2;
        }
    }
}