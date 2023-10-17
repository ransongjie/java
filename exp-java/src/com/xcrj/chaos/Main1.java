package com.xcrj.chaos;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        test14();
    }

    /**
     *
     */
    private static final String realName = "xcrj";

    private static void test01() {
        f01(null);
    }

    private static void f01(String name) {
//        System.out.println(name.equals(realName));// NPE
        System.out.println(realName.equals(name));// false
    }

    /**
     *
     */
    private static void test02() {
        Integer total = 128;
        f02(total);
        System.out.println(total);//128
    }

    private static void f02(Integer total) {
        if (total < 1) {
            total += 1;
        }
        total += 10;
    }

    /**
     * 结论
     * Arrays.asList(as)返回的list是java.util.Arrays.ArrayList部署java.util.ArrayList
     */
    private static void test03() {
        String[] as = new String[]{"xcrj", "xcrj01", "xcrj02"};
        List<String> list = Arrays.asList(as);
//        list.add("xcrj03");// java.lang.UnsupportedOperationException
//        list.remove(0);// java.lang.UnsupportedOperationException
//        list.clear();// java.lang.UnsupportedOperationException
        System.out.println(list.size());
    }

    /**
     * 问题
     * java.util.ConcurrentModificationException
     * 结论
     * 单线程循环删除使用 iterator.remove
     * 多线程循环删除使用 iterator.remove+锁
     * 多线程循环删除使用 CopyOnWriteArrayList
     * 多线程高并发使用 ConcurrentLinkedQueue
     * 原理
     * for-each本质使用iterator
     * iterator.next()会调用 checkForComodification(); 判断modCount != expectedModCount
     * iterator.remove()会重置expectedModCount=modCount
     * arrayList.remove(Object o)不会重置expectedModCount=modCount
     */
    private static void test04() {
        List<String> list = new ArrayList<>();
        list.add("xcrj");
        list.add("xcrj01");
        list.add("xcrj02");
        list.add("xcrj03");
        for (String name : list) {// java.util.ConcurrentModificationException
            if ("xcrj01".equals(name)) {
                list.remove(name);
            }
        }
    }

    /**
     * ?没有问题
     */
    private static void test05() {
        List<String> list = new ArrayList<>();
        list.add("x1");
        list.add("x2");
        list.add("x3");
        list.add("x4");
        /**
         * 错误，删除不干净
         * 错误输出[x2, y]，x2并未删除
         */
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if ("x2".equals(str)) {
                list.remove(i);
            }
        }

        for (String name :
                list) {
            System.out.print(name + ", ");
        }
    }

    /**
     * 结论
     * 操作系统创建单级目录和多级目录的方式不同
     * 解决
     * 只使用mkdirs()
     */
    private static void test06() {
//        String path = "D:/testm/testm1/testm2";
        String path = "D:/testm";
//        new File(path).mkdir();// 没有创建目录
        new File(path).mkdirs();// 创建了目录
    }

    /**
     * 结论
     * float相减会丢失精度
     * 解决
     * 使用BigDecimal
     */
    private static void test07() {
        // 金钱操作
        Float total = 200000.8f;
        Float own = 170000.5f;
        Float left = total - own;
        System.out.println(left);// 30000.297 金额显示很奇怪

        BigDecimal t = new BigDecimal("200000.8");
        BigDecimal o = new BigDecimal("170000.5");
        BigDecimal l = t.subtract(o);
        System.out.println(l);// 30000.3
    }

    /**
     * 问题
     * 死循环
     * 结论
     * 最后1次i++越界再判断i<=END
     */
    private static final int END = Integer.MAX_VALUE;
    private static final int START = END - 2;

    private static void test08() {
        int cnt = 0;
        for (int i = START; i <= END; i++) {
            cnt++;
        }
        // 期待3，实际 死循环
        System.out.println(cnt);
    }

    /**
     * try catch finally return总结
     * -finally中有return则只执行finally中的return
     * -表达式按声明顺序执行
     * -return a, 暂存变量》finally a=2》return
     */

    /**
     *
     */
    private static void test09() {
        System.out.println(f09());// false
    }

    private static boolean f09() {
        try {
//            throw new Exception();
            return true;
        } catch (Exception e) {
            return true;
        } finally {
            return false;
        }
    }

    /**
     *
     */
    private static void test10() {
        System.out.println(f10());// 4
    }

    private static Integer f10() {
        Integer a = 1;
        try {
            a = 2;
            return a;
        } catch (Exception e) {
            a = 3;
            return a;
        } finally {
            a = 4;
            return a;
        }
    }

    /**
     *
     */
    public static void test13() {
        System.out.println(f13());//13
    }

    public static int f13() {
        int a = 1;
        try {
            return a;
        } catch (Exception e) {
//            a = 2;
//            return a;
        } finally {
            a = 3;
            return a + 10;
        }
//        return a + 10;
    }

    /**
     *
     */
    public static void test14() {
        System.out.println(f14());//1
    }

    public static int f14() {
        int a = 1;
        try {
            return a;
        } catch (Exception e) {
            a = 2;
            return a;
        } finally {
            a=3;
        }
//        return a + 10;
    }

    /**
     *
     */
    public static void test11() {
        System.out.println(f11());//13
    }

    public static int f11() {
        int a = 1;
        try {
            a = 2;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            a = 3;
        }
        System.out.println(a + 10);// 13
        return a + 10;
    }

    /**
     *
     */
    public static void test12() {
        System.out.println(f12());//11
    }

    public static int f12() {
        int a = 1;
        try {
            return a + 10;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            a = 3;
        }
        System.out.println("dddd");// 不会执行到这里，上面已经return
        return a + 20;
    }
}
