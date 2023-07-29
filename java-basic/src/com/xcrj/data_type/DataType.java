package com.xcrj.data_type;

import java.util.Date;
/*
 变量，常量
 类型分类, 类型范围, 字面量, 默认值, 类型转换
 */
public class DataType {
    public static void main(String[] args) {
        //整数
        byte abyte=127;
        System.out.println(abyte);
        short ashort=300;
        System.out.println(ashort);
        int aint=333;
        System.out.println(aint);
        long along=3333;
        System.out.println(along);
        //浮点数
        float afloat=30.1f;
        System.out.println(afloat);
        double adouble=30.2;
        System.out.println(adouble);
        //字符
        char achar='e';
        System.out.println(achar);
        //布尔
        boolean aboolean=true;
        System.out.println(aboolean);
        //String
        String aString="abc";
        System.out.println(aString);
        //时间
        Date aDate=new Date();
        System.out.println(aDate);
        //数组
        int[] aintarr=new int[3];
        aintarr[0]=1;
        aintarr[1]=2;
        aintarr[2]=3;
        System.out.println(aintarr);
        int[] bintarr=new int[]{1,2,3};
        System.out.println(bintarr);
    }
}
