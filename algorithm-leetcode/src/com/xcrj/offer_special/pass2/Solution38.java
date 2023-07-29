package com.xcrj.offer_special.pass2;
import java.util.Arrays;

/**
 * 剑指 Offer II 038. 每日温度
 * int[] temperatures
 * rs[temperature对应位置]=往后&&更大温度 的等待天数
 */
public class Solution38 {
    /**
     * times[温度]=日期
     * 倒序temperatures[]寻找温度更高的更大日期的最小日期间隔
     * - 温度更高：temperatures[i] + 1~100
     * - 更大日期：倒序保证更大日期
     * - 最小日期间隔：times[温度]=日期 记录温度对应的日期 times[更高温度]-i=日期间隔
     * - 温度 in [30,100]
     * - temperatures[日期]=温度
     * 
     * xcrj temperatures[日期]=温度
     * xcrj times[温度]=日期
     * xcrj 先求日期 再求最小日期间隔
     * xcrj 更大日期 倒序temperatures[]
     * xcrj 温度更高 temperatures[i]+1~100
     * 
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] rs=new int[temperatures.length];
        // 0~100度
        int[] times=new int[101];
        Arrays.fill(times,Integer.MAX_VALUE);

        // 倒序求更大日期
        for(int i=temperatures.length-1;i>=0;i--){
            int minTime=Integer.MAX_VALUE;
            // +1到100求更高温度
            for(int j=temperatures[i]+1;j<=100;j++){
                // 最小日期
                if(times[j]<minTime){
                    minTime=times[j];
                }
            }
            if(minTime!=Integer.MAX_VALUE) rs[i]=minTime-i;
            times[temperatures[i]]=i;
        }
        return rs;
    }

    /**
     * 单调栈，单调递减栈
     * 
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int[]rs=new int[temperatures.length];
        int top=-1;
        int[]stack=new int[temperatures.length];
        for(int i=0;i<temperatures.length;i++){
            // 遍历的当前温度不比栈顶温度高则放入栈中
            // 栈中存储以前的更小温度的日期
            // xcrj 当前遍历 比 栈中 日期更大 温度更高 日期更近(最小间隔)
            while(-1!=top&&temperatures[i]>temperatures[stack[top]]){
                int j=stack[top--];
                rs[j]=i-j;
            }
            stack[++top]=i;
        }
        return rs;
    }
}
