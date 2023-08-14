package com.xcrj.basic.pass3.bit;

/**
 * 2个不等的元素出现奇数次，其它元素出现偶数次
 * 先排除2个不等元素中的其中1个
 */
public class YiHuo2Odd {
    public static void main(String[] args) {
        //7 1
        int[] as={2, 2, 3, 7, 3, 1, 4, 4};
        //num=a^b
        int num=0;
        for (int a :
                as) {
            num ^= a;
        }
        //取出最右侧1
        int flag=num&(~num+1);
        //
        int num2=0;
        for (int a :
                as) {
            //排除最右侧是flag的元素，排除了2个不等元素中的1个，排除了出现偶数次的某些元素
            if ((flag & a) == 0) {
                num2^=a;
            }
        }
        //
        System.out.println(num2);
        System.out.println(num2^num);//b^a^b
    }
}
