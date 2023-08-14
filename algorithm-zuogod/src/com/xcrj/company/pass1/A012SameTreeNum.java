package com.xcrj.company.pass1;

/**
 * 相等子树数量
 */
public class A012SameTreeNum {
    /**
     * 树的相等子树个数=左子树相等子树个数+右子树相等子树个数+当前树是否为相等子树
     * 暴力递归
     * O(nlogn)。越平衡时间越久
     * @param node 根节点
     * @return
     */
    public static int sameTree(Node node){
        if(node==null) return 0;
        return sameTree(node.left)+sameTree(node.right)+(isSame(node.left,node.right)?1:0);
    }

    private static boolean isSame(Node a,Node b){
        if(a==null^b==null){//
            return false;
        }
        if(a==null&&b==null){//
            return true;
        }
        return a.value==b.value
                &&isSame(a.left,b.left)
                &&isSame(a.right,b.right);
    }

    /**
     *
     * @param node
     * @return
     */
    public static int sameTree2(Node node){
        Hash hash=new Hash("SHA-256");
        return process(node,hash).ans;
    }
    static class Info{
        int ans;
        String hsh;
        Info(int ans,String hsh){
            this.ans=ans;
            this.hsh=hsh;
        }
    }

    /**
     * 树的相等子树个数=左子树相等子树个数+右子树相等子树个数+当前树是否为相等子树
     * 将isSame() O(n)优化为 O(1)
     * 序列化
     * 防止树太大，造成序列化字符串过大，信息摘要算法
     * @param node
     * @param hash
     * @return
     */
    private static Info process(Node node,Hash hash){
        if(node==null){
            return new Info(0,hash.hashCode("#,"));
        }
        Info l=process(node.left,hash);
        Info r=process(node.right,hash);
        return new Info(
                l.ans+r.ans+(l.hsh.equals(r.hsh)?1:0)
                ,hash.hashCode(node.value +","+l.hsh+r.hsh));
    }
}
