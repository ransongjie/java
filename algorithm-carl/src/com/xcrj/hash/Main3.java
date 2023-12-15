package com.xcrj.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/happy-number/
 * 快乐数
 * 直到这个数变为 1（快乐数），也可能是 无限循环 但始终变不到 1（不是快乐数）
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class Main3 {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int sum = 0;
            // sum+=个位数^2
            while (n != 0) {
                int a = n % 10;//求个位
                sum += a * a;
                n = n / 10;//右移1位
            }
            if (sum == 1) return true;
            if (set.contains(sum)) return false;
            set.add(sum);
            n=sum;//
        }
    }
}
