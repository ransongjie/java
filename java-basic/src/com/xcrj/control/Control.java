package com.xcrj.control;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

//判断（if, switch），循环(for, while, do while)
public class Control {
    public static void main(String[] args) {
        //判断 if
        int a = 10;
        if (a > 10) {
            System.out.println("a>10");
        } else if (a == 10) {
            System.out.println("a=10");
        } else {
            System.out.println("a<10");
        }

        //判断 switch
        /// 无case穿透
        int e = 1;
        switch (e) {
            case 1:
                System.out.println("e = 1");
                break;
            case 2:
                System.out.println("e = 2");
                break;
            case 3:
                System.out.println("e = 3");
                break;
            default:
                System.out.println("e=?");
        }
        /// 有case穿透，default也会被穿透
        System.out.println("有case穿透");
        switch (e) {
            case 1:
                System.out.println("e = 1");
            case 2:
                System.out.println("e = 2");
            case 3:
                System.out.println("e = 3");
            default:
                System.out.println("e=?");
        }

        //循环 for
        /// for-index
        int[] as = new int[] { 1, 2, 3 };
        for (int i = 0; i < as.length; i++) {
            System.out.println(as[i]);
        }
        /// for-each, 本质 iterator
        List<Integer> alist = new ArrayList<>();
        alist.add(1);
        alist.add(2);
        alist.add(3);
        for (Integer b : alist) {
            System.out.println(b);
        }

        //循环 while
        Set<Integer> aset = new HashSet<>();
        aset.add(1);
        aset.add(2);
        aset.add(3);
        Iterator<Integer> aIter = aset.iterator();
        while (aIter.hasNext()) {
            Integer c = aIter.next();
            System.out.println(c);
            ///
            while(true){
                ///跳出当前循环
                break;
            }
            //break后 继续执行下面的代码
            System.out.println("break 后的代码");
        }

        //循环 do while
        int d = 1;
        do {
            d++;
            ///不执行continue后面的语句，跳到下一次循环
            if(d>2) continue;
            d<<=1;
        } while (d < 10);
        System.out.println(d);
    }
}