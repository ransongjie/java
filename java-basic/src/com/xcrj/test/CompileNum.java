package com.xcrj.test;

/**
 * 编译优化 针对数字类型
 * 常用&&简单的会进行编译优化
 * 1. java中整型字面量是int类型，赋值时，不超过 byte short char时自动进行类型转换
 * 2. java中整型byte short char都提升为int计算，
 *    只使用字面量，四则运算结果不超过 byte short char时自动进行类型转换
 * 3. final修饰变量，会进行编译优化
 * 4. -128~127之间的数直接赋值时, 不会装箱为包装类
 */
public class CompileNum {
    public static void main(String[] args) {
        
    }

    //byte short char 不超范围赋值，编译优化
    //java中整型字面量是int类型，不超过 byte short char时自动进行类型转换
    public static void test1() {
        byte b=10;
        short s=2000;
        char c=2390;
    }

    /**
     * 包装类编译优化
     * 除非需要判断引用相等，否则一律使用equals
     * 包装类判断相等最好使用equals
     * -128~127的装箱 编译优化
     * -128~127之间的数, 自动装箱时不会在堆中创建包装类对象, 直接使用常量池
     */
    public static void test2() {
        Integer b1=100;
        Integer b2=100;
        //true
        System.out.println(b1==b2);

        Integer a1=new Integer(100);
        Integer a2=new Integer(100);
        //false
        System.out.println(a1==a2);

        Integer i1=Integer.valueOf(100);
        Integer i2=Integer.valueOf(100);
        //true
        System.out.println(i1==i2);

        Long c1=100L;
        Long c2=100L;
        //true
        System.out.println(c1==c2);

        Long d1=128L;
        Long d2=128L;
        //false
        System.out.println(d1==d2);
    }

    /**
     * byte + - * / 编译优化
     * 都是字面量才会进行编译优化
     * 只要有一个计算量是变量都不会进行编译优化
     */
    public static void test3() {
        //字面量计算，编译优化
        byte b1=10+2;
        //java中, 整形变量计算，将提升为int类型计算，因此可能越界
        byte b2=10;
        byte b3=2;
        // byte b4=b2+b3;//Type mismatch: cannot convert from int to byte 
        // byte b5=b2+3;//Type mismatch: cannot convert from int to byte 

        byte d1=10-2;
        byte d2=10;
        byte d3=2;
        // byte d4=d3-d2;//Type mismatch: cannot convert from int to byte 
        // byte d5=d3-2;//Type mismatch: cannot convert from int to byte 

        byte a1=2*10;
        byte a2=2;
        byte a3=10;
        // byte a4=a2*a3;//Type mismatch: cannot convert from int to byte 
        // byte a5=a2*10;//Type mismatch: cannot convert from int to byte 

        byte c1=10/2;
        byte c2=10;
        byte c3=2;
        // byte c4=c2/c3;//Type mismatch: cannot convert from int to byte 
        // byte c5=c2/2;//Type mismatch: cannot convert from int to byte 
    }

    /**
     * short + - * / 编译优化
     */
    public static void test4() {
        short a1=100+100;
        short a2=100;
        short a3=100;
        // short a4=a2+a3;//Type mismatch: cannot convert from int to byte 
    }

    /**
     * char + - * / 编译优化
     */
    public static void test5() {
        char a1=100+100;
        char a2=100;
        char a3=100;
        // char a4=a2+a3;//Type mismatch: cannot convert from int to byte 
    }

    /**
     * final 修饰编译优化
     */
    public static void test6() {
        byte a1=10;
        byte a2=2;
        // byte a3=a1+a2;//Type mismatch: cannot convert from int to byte 
        
        final byte b1=10;
        final byte b2=2;
        byte b3=b1+b2;

        final byte c1=10;
        byte c3=c1+2;
    }

    
    
}
