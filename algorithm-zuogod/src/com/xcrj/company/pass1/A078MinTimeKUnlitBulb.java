package com.xcrj.company.pass1;

public class A078MinTimeKUnlitBulb {

    public static int minTimeKUnlitBulb(int[] bulbs, int k) {
        int n = bulbs.length;
        //[点亮时刻]=灯泡编号 转化 [灯泡编号]=点亮时刻
        //灯泡编号从1开始 转化 灯泡编号从0开始
        //点亮时刻从0开始 转化 点亮时刻从1开始
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = i + 1;
        }
        //满足l~r间隔k，间隔端点都亮，间隔内都不亮的最小时刻
        int ans = Integer.MAX_VALUE;
        //有l和r端点，m在l+1~r移动 要求[m]>max([l],[r])
        for (int l = 0, r = k + 1, m = l + 1; r < n; m++) {
            //情况1，m从l+1~r都满足[m]>max([l],[r]), m一直走到了r
            //情况2，m从l+1~r有不满足[m]>max([l],[r])，m没有走到r
            if (days[m] <= Math.min(days[l], days[r])) {
                if (m == r) {
                    ans = Math.min(ans, Math.max(days[l], days[r]));
                }
                l = m;
                r = m + k + 1;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 滑动窗口
     * 窗口内最小值的更新结构
     *
     * @param bulbs
     * @param k
     * @return
     */
    public static int minTimeKUnlitBulb2(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = i + 1;
        }
        int ans = Integer.MAX_VALUE;
        if (k == 0) {
            for (int i = 1; i < n; i++) {
                ans = Math.min(ans, Math.max(days[i - 1], days[i]));
            }
        } else {
            /**
             * 0,[1,k-1],k；minq[0]=次窗口的最小值的索引
             * 1,[2,k],k+1；minq[1]=次窗口的最小值的索引
             * 2,[3,k+1],k+1；minq[2]=次窗口的最小值的索引
             */
            //！ 第1个窗口最小值索引，窗口大小k，窗口[1,k-1]内最小值的索引
            //单增栈记录递增的值的索引，l记录了窗口[1,k-1]内最小值
            int[] minq = new int[n];
            int l = 0, r = -1;
            for (int i = 1; i < k && i < n; i++) {
                //窗口[1,k-1]内的下个值是否更小
                while(l<=r&&days[minq[r]]>=days[i]){
                    r--;
                }
                //初始假设窗口内的最小值的索引是1，端点需要剔除
                minq[++r]=i;
            }
            //! 继续比较确定下一个窗口的最小值的索引
            //！ l记录了当前窗口的最小值的索引
            //j<n-1, j是窗口索引，n是最后1个窗口的右端点
            for (int i = 1,j=k; j < n-1; i++,j++) {
                //记录下一个窗口[2,k]内的最小值的索引
                while(l<=r&&days[minq[r]]>=days[j]){
                    r--;
                }
                minq[++r]=j;
                //窗口端点值
                int cur=Math.max(days[i-1],days[j+1]);
                //窗口内最小值>端点最大值
                if(days[minq[l]]>cur){
                    ans=Math.min(ans,cur);
                }
                //窗口最小值索引是左端点l++,处理下一个窗口
                //窗口最小值索引在窗口内部，不变化窗口最小值索引，直到新窗口不包含这个索引（在左端点上）
                if(i==minq[l]){
                    l++;
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
