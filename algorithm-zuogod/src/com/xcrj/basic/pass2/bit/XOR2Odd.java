package com.xcrj.basic.pass2.bit;
/**
 * 数组中只有2个元素奇数次 且两个元素不等 其它偶数次
 * 获取a^b：num=0, num^整个数组=a^b 一定!=0 两个元素不等 至少1个bit相反
 * 获取a^b最右侧的1：r1=num&(~num+1)
 * 获取a：num2^整个数组(&1==0的数)=a。
 * 获取b：num^num2=b
 */
public class XOR2Odd {
    public static void main(String[] args) {
        int[] as={2, 2, 3, 6, 3, 8, 4, 4};

        int num=0;
        for (int a : as) {
            num^=a;
        }

        int r1=num&(~num+1);

        int num2=0;
        for (int a : as) {
            if((a&r1)==0) continue;
            // if((a&r1)==1) continue;//不能用1，因为r1是最右侧的1，不一定等于1
            num2^=a;
        }

        int a=num2;
        int b=num^num2;

        System.out.println(a+"--"+b);
    }
}
