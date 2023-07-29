package com.xcrj.basic.pass1.bit;

//数组中只有2个元素奇数次 且两个元素不等 其它偶数次
//{2, 2, 3, 7, 3, 1, 4, 4}
public class Yihuo2Odd {
    public static void main(String[] args) {
        //num=0, num^整个数组=a^b 一定!=0 两个元素不等 至少1个bit相反
        //提取 a^b 最右侧不等的 bit 1 (假设第8个bit 是1). num&(~num+1) 原码&(补码)
        //r1&as[i]!=0：第8个bit是1则^0=a^0^偶数个为1的数=a
        //b=(a^b)^a
        int[] as={2, 2, 3, 7, 3, 1, 4, 4};
        int num=0;
        int n=as.length;
        for(int i=0;i<n;i++){
            num^=as[i];
        }

        int num2=0;
        int r1=num&(~num+1);
        for(int i=0;i<n;i++){
            if((r1&as[i])==0){
            // if((r1&as[i])!=0){
                num2^=as[i];
            }
        }
        int a=num2;
        int b=num2^num;

        System.out.println(a);
        System.out.println(b);
    }
    
}
