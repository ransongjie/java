package com.xcrj.data_type;

public class TypeConvert {
    public static void main(String[] args) {
        // 整数转整数
        int aint = 10;
        short ashort = (short) aint;
        System.out.println(ashort);
        // 整数转浮点数
        int bint = 10;
        float bfloat = (float) bint;
        System.out.println(bfloat);
        // 浮点数转整数
        float cfloat = 3.3f;
        /// 截取整数
        int cint = (int) cfloat;
        System.out.println(cint);

        // 整数转string
        int dint = 3;
        String dString = String.valueOf(dint);
        System.out.println(dString);
        // string转整数
        String eString = new String("3");
        int eint = Integer.valueOf(eString);
        System.out.println(eint);

        // 浮点数转String
        float ffloat = 3.2f;
        String fString = String.valueOf(ffloat);
        // String转浮点数
        String gString = "3.2";
        float gfloat = Float.valueOf(gString);
    }
}
