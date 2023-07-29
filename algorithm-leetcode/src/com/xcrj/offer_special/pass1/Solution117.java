package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 117. 相似的字符串
 * 入参strs中的字符串的字母都相同，字母顺序不同
 * 两个字符串相同位置字母不同的次数不大于2 则视为相似
 */ 
public class Solution117 {
    // 定义并查集
    int[] roots;

    /**
     * 使用并查集
     * 已知入参strs中的字符串字母都相同，只是排列不同
     * 构建图 先判断两个字符串是否已经相连，没有相连则检查两个字符串是否是相似的，如果相似则连通
     * 问题转换 相似字符串组数量 = 连通分量数量 = 同根数量（f[i]=i的数量）
     */
    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        // strs中的字符串的长度是一样的
        int sLen = strs[0].length();

        // 初始化并查集
        this.roots = new int[len];
        for (int i = 0; i < len; i++) {
            this.roots[i] = i;
        }

        // 两个两个字符串检查是否已经相连和相似
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                // 先判断是否已经相连
                int iRoot=findRoot(i);
                int jRoot=findRoot(j);
                if(iRoot==jRoot) continue;
                // 没有相连则检查两个字符是否相似，相似则连接到一起
                if(checkSimilarity(strs[i], strs[j], sLen)){
                    // attention 把已经找到的根连接
                    // 合并两个城市
                    // 下面一行等价于 this.roots[iRoot]=jRoot;
                    union(i,j);
                }
            }
        }

        // 同根数量=连通分量数据=相似字符串组数量
        int r=0;
        for(int i=0;i<this.roots.length;i++){
            if(this.roots[i]==i) r++;
        }

        return r;
    }

    /**
     * 合并两个结点
     */ 
    private void union(int i,int j){
        this.roots[findRoot(i)]=findRoot(j);
    }

    /**
     * 寻找并更新结点的根结点
     */
    private int findRoot(int node) {
        return this.roots[node] == node ? node : (this.roots[node] = findRoot(this.roots[node]));
    }

    /**
     * 检查两个字符串是否相似，相同位置字符不同次数不大于2则相似
     * @param sLen 字符串长度
     */ 
    private boolean checkSimilarity(String str1,String str2,int sLen){
        int count=0;
        for(int i=0;i<sLen;i++){
            if(str1.charAt(i)!=str2.charAt(i)) count++;
            if(count>2) return false;
        }

        return true;
    }
}