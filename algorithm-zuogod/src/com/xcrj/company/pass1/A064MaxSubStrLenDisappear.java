package com.xcrj.company.pass1;

public class A064MaxSubStrLenDisappear {
    public static int maxSubStrLenDisappear(String s) {
        if (s == null || s.length() == 0) return 0;
        //0~n-1 能够消掉的最长子序列
        return erasePair(s.toCharArray(), 0, s.length() - 1);
    }

    /**
     * [l,r] 能够消掉的最长子序列
     *
     * @param cs
     * @param l
     * @param r
     * @return
     */
    private static int erasePair(char[] cs, int l, int r) {
        //2个字符才能消除
        if (l >= r) return 0;
        //仅仅剩余2个字符
        if (l + 1 == r) {
            //能够消掉2个字符吗
            return (cs[l] == '0' && cs[r] == '1') || (cs[l] == '2' && cs[r] == '3') ? 2 : 0;
        }
        //可能1，不考虑cs[l]
        int p1 = erasePair(cs, l + 1, r);
        //可能2，必考虑cs[r]。
        //可能2.1, l左侧无值，'1' '3'无法和右侧合并 等价于 p1
        if (cs[l] == '1' || cs[l] == '3') return p1;
        //可能2.2, cs[l]='0' 或 '2'
        int p2 = 0;
        //'0'配对'1'。'2'配对'3'
        char pair = cs[l] == '0' ? '1' : '3';
        //例如，cs[l]='0': l('0')...i('1')i+1...k('1')....r
        //cs[l]='0'和所有的cs[i]='1'配对，求最大值
        for (int i = l + 1; i <= r; i++) {
            if (cs[i] == pair) {
                //+2: cs[l]一定要和cs[i]配对消掉
                p2 = Math.max(p2, erasePair(cs, l + 1, i - 1) + 2 + erasePair(cs, i + 1, r));
            }
        }
        //
        return Math.max(p1, p2);
    }
}
