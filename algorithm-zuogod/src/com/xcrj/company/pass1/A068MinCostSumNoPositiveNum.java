package com.xcrj.company.pass1;

import java.util.Arrays;

public class A068MinCostSumNoPositiveNum {
    /**
     * 动态规划方法
     * 时间消耗与数组sum相关，sum过大时间消耗大
     *
     * @param as
     * @param x  元素值变0 x代价
     * @param y  元素值变负 y代价
     * @return
     */
    public static int minCostSumNoPositiveNum(int[] as, int x, int y) {
        int sum = 0;
        for (int a :
                as) {
            sum += a;
        }
        return f(as, x, y, 0, sum);
    }

    /**
     * sum<=0 的最小代价
     *
     * @param as
     * @param x
     * @param y
     * @param i
     * @param sum
     * @return
     */
    private static int f(int[] as, int x, int y, int i, int sum) {
        //满足要求 sum<=0 无需再付出代价
        if (sum <= 0) return 0;
        //没有可以操作的元素
        if (i == as.length) {
            return Integer.MAX_VALUE;
        }
        //情况一，既不进行x操作也不进行y操作，sum无变化，操作下一个元素
        int p1 = f(as, x, y, i + 1, sum);
        //情况二，进行x操作，归0，sum=sum-as[i]
        int p2 = Integer.MAX_VALUE;
        int nxt2 = f(as, x, y, i + 1, sum - as[i]);
        if (nxt2 != Integer.MAX_VALUE) {
            //当前i元素 操作为x，i+1操作最低代价为nxt2
            p2 = x + nxt2;
        }
        //情况三，进行y操作，吧as[i]变成-1求sum，sum=sum-(as[i]<<1)
        int p3 = Integer.MAX_VALUE;
        int nxt3 = f(as, x, y, i + 1, sum - (as[i] << 1));
        if (nxt3 != Integer.MAX_VALUE) {
            p3 = y + nxt3;
        }
        //取3个代价中的最小代价
        return Math.min(Math.min(p1, p2), p3);
    }

    /**
     * 贪心方法
     * 当前如最优选择，可以得到全局最优解
     * 前面是y操作，中间是x操作，后面是无操作，是最优策略
     * 大的值不执行y操作，小的值不会执行y操作
     * 大的值不执行x操作，小的值不会执行x操作
     * @param as
     * @param x
     * @param y
     * @return
     */
    public static int minCostSumNoPositiveNum2(int[] as, int x, int y) {
        //先从大到小排序
        Arrays.sort(as);
        reverse(as);
        //x>=y，执行x操作的收益低&&代价大，当前最优选择不执行x操作，只执行y操作
        if (x >= y) {
            int sum = 0;
            for (int a : as) {
                sum += a;
            }
            int cost = 0;
            for (int i = 0; i < as.length && sum > 0; i++) {
                sum -= (as[i] << 1);
                cost += y;
            }
            return cost;
        } else {
            //设，执行0个y操作,只能全部元素执行x操作才能让sum<=0，底线成本
            int cost=as.length*x;
            //0~i个y操作，与n~j个无操作，尽可能抵消，使得sum<=0
            int iYBeNeg=0;//y操作让元素值变负值再求sum，让sum<=0
            int jNoBePos=0;//无操作
            for (int i = 0,j=as.length; i < j-1; i++) {//i<j 数组只有1个元素时会报错
                iYBeNeg+=as[i];
                //>=, sum<=0，尽可能多执行无操作，无操作没有代价
                while (i<j-1&&iYBeNeg>=jNoBePos+as[j-1]){
                    jNoBePos+=as[--j];
                }
                //！0,.y操作.,i,.x操作.,j,.无操作.,n
                //！执行k个y操作后，尽可能多执行无操作（无成本），k个y操作确定，无操作次数确定，自然确定q个x操作。
                cost = Math.min(cost, (i + 1) * y + (j - i - 1) * x);
            }
            return cost;
        }
    }

    /**
     * 贪心方法
     * 当前如何做出最优选择
     * 前面是y操作，中间是x操作，后面是无操作 是最优策略
     * 大的值不执行y操作，小的值不会执行y操作
     * 大的值不执行x操作，小的值不会执行x操作
     * @param as
     * @param x
     * @param y
     * @return
     */
    public static int minCostSumNoPositiveNum3(int[] as, int x, int y) {
        //先从大到小排序
        Arrays.sort(as);
        reverse(as);
        //x>=y，执行x操作的收益低&&代价大，当前最优选择不执行x操作，只执行y操作
        if (x >= y) {
            int sum = 0;
            for (int a : as) {
                sum += a;
            }
            int cost = 0;
            for (int i = 0; i < as.length && sum > 0; i++) {
                sum -= (as[i] << 1);
                cost += y;
            }
            return cost;
        } else {
            //将as[]变成后缀和
            for (int i = as.length-2; i >=0 ; i--) {
                as[i]+=as[i+1];
            }
            //！前面执行k个y操作，中间执行mostLeft个x操作，后面执行无操作
            //！k个y操作，结合后缀和，定下mostLeft
            //设，前面执行0个y操作，中间执行mostLeft个x操作，后面执行无操作
            int mostLeft=getMostLeft(as,0,0);
            int cost=mostLeft*x;
            //设，前面执行1~n-1个y操作，中间执行(mostLeft-i-1)个x操作，后面执行无操作
            //执行1个y操作，原来的as[0]=后缀和as[0]-后缀和as[1]
            //最多执行n-1个y操作，不会执行n个y操作，因为执行n个y操作sum必然<0，不会等于0，n个y操作收益不是最高的
            //! 后缀和将执行n个y操作导致元素值变成负值，尽可能抹掉
            int nYBeNeg=0;//执行n个y操作的收益=原as[0]+...+原as[i]
            for (int i = 0; i < as.length-1; i++) {
                nYBeNeg+=as[i]-as[i+1];
                mostLeft=getMostLeft(as,i+1,nYBeNeg);
                cost=Math.min(cost,(i+1)*y+(mostLeft-i-1)*x);
            }
            return cost;
        }
    }

    private static void reverse(int[] as) {
        int tmp;
        for (int l = 0, r = as.length - 1; l < r; l++, r--) {
            tmp = as[l];
            as[l] = as[r];
            as[r] = tmp;
        }
    }

    /**
     * 从l~n-1找到<=v的最左侧的位置
     * as[i]执行y操作变成负数，执行i个y操作后+后缀和 仍然能够<=0
     * @param as 后缀和
     * @param l
     * @param v
     * @return
     */
    public static int getMostLeft(int[] as, int l, int v) {
        int mostLeft=as.length;
        int mid=0;
        int r=as.length-1;
        while(l<r){
            mid=(l+r)>>1;
            if(as[mid]<=v){//需要记录<=v的位置，一直记录<=v的位置
                mostLeft=mid;
                r=mid-1;
            }else{
                l=mid+1;
            }
        }

        return mostLeft;
    }


}
