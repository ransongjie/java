package com.xcrj.error;

/**
 * 结束程序 正常返回（return） 异常返回（throw 异常）
 * Throwable Error Exception
 * Exception RuntimeException
 * throw
 * throws
 * catch
 * finally
 */
public class Err1 {
    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        // test6();
//        test7();
//        test8();
//        test9();
        test10();
//        System.out.println(test11());
    }

    //Exception必须捕获
    //throw
    public static void test1() {
        try {
            throw new Exception("Exception必须捕获");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //throws
    public static void test2() {
        try {
            test2a();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void test2a() throws Exception {
        throw new Exception("Exception必须捕获");
    }

    // RuntimeException 不强制捕获
    // 不捕获就会报错，导致程序异常退出
    public static void test3() {
        throw new RuntimeException("RuntimeException不强制捕获");
    }

    //多个catch
    //把小范围的异常放到前面catch
    public static void test4() {
        try {
            int[] as = {1, 2, 3};
            System.out.println(as[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    //异常被catch后，程序可以继续执行，不会因为异常而退出，因为已经被catch
    public static void test5() {
        try {
            int[] as = {1, 2, 3};
            System.out.println(as[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }

        System.out.println("继续执行");
    }

    //finally 退出时执行（正常退出 异常退出）。
    public static void test6() {
        try {
            int[] as = {1, 2, 3};
            System.out.println(as[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } finally {
            System.out.println("退出时执行");
        }
    }

    //finally 与 return
    //try和finally中都没有return语句，修改变量的值起作用
    public static void test7() {
        int r = test7a();
        //120
        System.out.println("r=" + r);
    }

    public static int test7a() {
        int a = 10;
        int b = 20;
        try {
            a = 30;
            throw new RuntimeException("出现异常");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            a = 100;
        }

        return a + b;
    }

    //finally 与 return
    //try中有return finally中没有return, 修改变量的值不起作用
    //try 中 return a+b的值已经存储到了jvm创建的临时变量中，finally对这个临时变量不可见
    public static void test8() {
        int r = test8a();
        //30
        System.out.println("r=" + r);
    }

    public static int test8a() {
        int a = 10;
        // int b=20;
        try {
            // return a+b;
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            a = 100;
        }

        // return a+b;
        return a;
    }

    /**
     * try和finally中都有return, 忽略try中的return
     */
    public static void test9() {
        int r = test9a();
        //100
        System.out.println("r=" + r);
    }

    //return 100。忽略了try中的return
    public static int test9a() {
        int a = 10;
        try {
            return a;//忽略
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            a = 100;
            return a;
        }
    }

    /**
     * 只有try中有return a+b，返回值不能被finally中的操作改变
     * a+b的值已经存储到了jvm创建的临时变量中，finally对这个临时变量不可见
     */
    public static void test10() {
        int a = test10a();
        System.out.println(a);//45
    }

    // return 45。a+45暂存变量》finally》return
    public static int test10a() {
        int a = 20;
        try {
            return a + 25;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            a = a + 10;//不起作用
        }
        //代码不会运行到此处
        System.out.println("okok");
        return a;
    }

    //！！！ return 3。忽略try中return。不建议在finally中使用return
    public static int test11() {
        try {
            return 5 / 1;
        } catch (Exception e) {
            return 2 * 3;
        } finally {
            return 3;
        }
    }
}