package com.xcrj.basic.pass2.bit;
/**
 * 数组中只有1个元素奇数次 其它偶数次
 * 
 */
public class XOR1Odd {
    public static void main(String[] args) {
        int[] as={2, 2, 3, 3, 1, 4, 4};
        int num=0;
        //交换律 结合律
        for (int a : as) {
            num^=a;
        }
        
        System.out.println(num);
    }
}
