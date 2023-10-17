package com.xcrj.chaos;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        test04();
    }

    /**
     * 虚拟机当加载静态内部类时如果是确定的属性值时，虚拟机会直接将值存放到元存储中(静态方法区)
     */
    static class Inner {
        static int p1 = 5;
        static final int p2 = 5;
        static final int p3 = new Integer(5);
    }

    private static void test01() {
        System.out.println(Main3.Inner.p1);//5
        System.out.println(Main3.Inner.p2);//5
        System.out.println(Main3.Inner.p3);//5
    }

    /**
     * j=j++：暂存区,变量区,暂存区>变量区
     * -先将j的值0存入堆栈（暂存区OS）中
     * -变量区（LV）中j自加1，这时j的值确实是1，
     * -将堆栈中的值赋给变量区的j，所以最后j=0
     * j=++j：变量区,暂存区,暂存区>变量区
     * -先对变量区中的j加1
     * -将变量区中的j存入堆栈
     * -将堆栈中的值赋给自变量区的j，所以j=100
     */
    private static void test02() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            // 每次j都赋值为0
            j = j++;
        }
        System.out.println(j);//0
    }

    private static void test02a() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = ++j;
        }
        System.out.println(j);//100
    }

    /**
     * Arrays.asList(基本数据类型数组) 整个数组被视为1个对象
     */
    private static void test03() {
        int[] as = new int[]{1, 2, 3, 4};
//        List<Integer> list= Arrays.asList(as);// 编译异常，必须是包装类
        List list = Arrays.asList(as);
        System.out.println(list.size());// 1
    }

    /**
     * Arrays.asList(包装类型数组) 单个元素被视为1个对象
     */
    private static void test03a() {
        Integer[] as = new Integer[]{1, 2, 3, 4};
        List list = Arrays.asList(as);
        System.out.println(list.size());// 4
    }

    /**
     * List和Vector类都重写了equals()
     * 比较长度和每个元素
     */
    private static void test04() {
        List<String> list = new ArrayList<>();
        list.add("xcrj");
        Vector<String> vector = new Vector<>();
        vector.add("xcrj");
        System.out.println(list.equals(vector));//true

        Vector<String> vector2 = new Vector<>();
        vector2.add("xcrj");
        System.out.println(vector.equals(vector2));//true

        Vector<String> vector3 = new Vector<>();
        vector3.add(new String("xcrj"));
        System.out.println(vector.equals(vector3));//true
    }

    /**
     * 1970年1月1日0时0分0秒到现在的毫秒数
     */
    private static void test05() {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * 获取java类字节码的方法
     */
    private static void test06() {
        // 类获取
        System.out.println(Main3.class);
        // 对象获取
        System.out.println(new Main3().getClass());
        try {
            // 反射获取
            Class clazz=Class.forName("com.xcrj.chaos.Main3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
