package com.xcrj.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Example1 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
    }

    /**
     * Optional.empty()
     * 返回Optional对象，内部value=null
     */
    public static void test1() {
        // java.util.NoSuchElementException: No value present
        // Optional.empty().get();
    }

    /**
     * Optional.of(t)
     * 要求入参非null
     */
    public static void test2() {
        Teacher t = new Teacher();
        // t is not be null
        Teacher t2 = Optional.of(t).get();
        //
        Teacher t3 = null;
        // java.lang.NullPointerException
        // Teacher t4 = Optional.of(t3).get();
    }

    /**
     * Optional.ofNullable(t)
     * 入参为null返回empty Optional
     */
    public static void test3() {
        Teacher t = new Teacher();
        // t is not be null
        Teacher t2 = Optional.ofNullable(t).get();
        //
        Teacher t3 = null;
        // java.util.NoSuchElementException: No value present
        // Teacher t4 = Optional.ofNullable(t3).get();
    }

    /**
     * .get()
     * 获取Optional对象内部属性value，value仍然可能为null，后续调用出现nullPointerException
     */
    public static void test4() {
        Teacher teacher = null;
        // java.util.NoSuchElementException: No value present
        // Teacher t = Optional.ofNullable(teacher).get();
    }

    /**
     * opt.isPresent()
     * 判断Optional对象内部value是否为null，
     * 可以先isPresent()再get()
     */
    public static void test5() {
        // teacher !=null
        Teacher teacher = new Teacher();
        Optional<Teacher> opt = Optional.ofNullable(teacher);
        if (opt.isPresent()) {
            Teacher t = opt.get();
        }
        // teacher2 = null
        Teacher teacher2 = null;
        Optional<Teacher> opt2 = Optional.ofNullable(teacher2);
        if (opt2.isPresent()) {
            Teacher t2 = opt2.get();
        }
    }

    /**
     * ifPresent()
     * Optional对象内部value不为null时，执行入参customer
     */
    public static void test6() {
        Teacher teacher = new Teacher();
        Optional.ofNullable(teacher).ifPresent(o -> {
            System.out.println(o.getName());
        });
    }

    /**
     * orElse(T other) 入参是 对象
     * value不为null，返回value；为null则返回other对象；
     * 不论value是否为bull，另一个对象都会创建
     */
    public static void test7() {
        Teacher teacher = new Teacher("xcrj", 0);
        Teacher t = Optional.ofNullable(teacher).orElse(new Teacher("xcrj1", 1));
        System.out.println(t.getName());
    }

    /**
     * orElseGet(Supplier<? extends T> other) 入参是lambda表达式
     * value不为null，返回value，不会执行supplier；
     * 为null，则执行supplier，返回同value类型对象
     */
    public static void test8() {
        Teacher teacher = new Teacher("xcrj", 0);
        Teacher t = Optional.ofNullable(teacher).orElseGet(() -> {
            return new Teacher("xcrj1", 1);
        });
        System.out.println(t.getName());
    }

    /**
     * orElseThrow(Supplier<? extends X> exceptionSupplier)
     * value不为null，返回value，不会执行supplier；
     * 为null，则执行supplier，返回异常对象；
     * 可以将nullPointerException转换为自定义异常
     * @throws Exception
     */
    public static void test9() {
        Teacher teacher = null;
        Optional.ofNullable(teacher).orElseThrow(RuntimeException::new);
    }
}
