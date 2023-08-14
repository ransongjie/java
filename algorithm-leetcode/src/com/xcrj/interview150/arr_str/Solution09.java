package com.xcrj.interview150.arr_str;

/**
 * https://leetcode.cn/problems/gas-station
 * 加油站
 */
public class Solution09 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, i = 0, j, k, sumGas, sumCost;
        while (i < n) {
            //从i出发走过多少站点
            k = 0;
            sumCost = 0;
            sumGas = 0;
            //！从i出发尝试是否能走一圈
            while (k < n) {
                j = (i + k) % n;
                sumGas += gas[j];
                sumCost += cost[j];
                if (sumCost > sumGas) {
                    break;
                }
                k++;
            }
            if (k == n) {
                //找到了能完整走一圈的起点加油站
                return i;
            } else {
                //继续找能完整走一圈的起点加油站
                i = i + k + 1;//！i~k~i+k，从i出发不能到i+k，则从i~i+k中的任意一点出发不能到i+k，i~m~i+k, 剩余油量>=0
            }
        }

        //没找到能完整走一圈的起点加油站
        return -1;
    }
}
