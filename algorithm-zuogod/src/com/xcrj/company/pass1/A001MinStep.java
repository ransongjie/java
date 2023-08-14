package com.xcrj.company.pass1;

/**
 * 分开GB最小相邻交换次数
 * G全在左边，或B全在左边
 * 窗口
 * O(n)
 */
public class A001MinStep {
    /**
     *
     * @param str GB字符串
     * @return
     */
    public static int minStep(String str){
        char[]cs= str.toCharArray();
        //l1，下一个G可以放置的位置
        //m1，左侧全是G的交换次数
        int l1=0,m1=0,n=cs.length;
        for (int i = 0; i < n; i++) {
            if(cs[i]=='G'){
                m1+=i-(l1++);
            }
        }

        //l1，下一个B可以放置的位置
        //m2，左侧全是B的交换次数
        int l2=0,m2=0;
        for (int i = 0; i < n; i++) {
            if(cs[i]=='B'){
                m2+=i-(l2++);//
            }
        }

        return Math.max(m1,m2);
    }

    public static int minStep2(String str){
        char[]cs= str.toCharArray();
        int l1=0,m1=0,n=cs.length;
        int l2=0,m2=0;
        for (int i = 0; i < n; i++) {
            if(cs[i]=='B'){
                m2+=i-(l2++);//l1是下一个B可以放置的位置
            }else{
                m1+=i-(l1++);
            }
        }

        return Math.max(m1,m2);
    }
}
