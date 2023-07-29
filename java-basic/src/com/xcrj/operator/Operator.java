package com.xcrj.operator;

public class Operator {
    //赋值, 算数, 关系, 逻辑, 位, 三元, instanceof
    public static void main(String[] args) {
        //赋值, =, +=, -= ...
        int aint=10;
        int bint=20;
        bint+=1;
        bint-=1;

        //算数，+,-,*,/,%,++,--
        System.out.println(aint+bint);
        System.out.println(aint-bint);
        System.out.println(aint*bint);
        ///0
        System.out.println(aint/bint);
        ///10
        System.out.println(aint%bint);
        ///10, 后自增
        System.out.println(aint++);
        ///11, 后自减
        System.out.println(aint--);
        ///11, 先自增
        System.out.println(++aint);
        ///10, 后自减
        System.out.println(--aint);

        //关系, ==, !=, >, <, >=, <=
        System.out.println(aint==bint);
        System.out.println(aint!=bint);
        System.out.println(aint>bint);
        System.out.println(aint<bint);
        System.out.println(aint>=bint);
        System.out.println(aint<=bint);

        //逻辑, &&, ||, !
        boolean abool=true;
        boolean bbool=false;
        System.out.println(abool&&bbool);
        System.out.println(abool||bbool);
        System.out.println(!abool);

        //位, &, |, ~, ^, <<, >>, >>>
        int cint =10;
        int dint=20;
        System.out.println(cint&dint);
        System.out.println(cint|dint);
        System.out.println(~cint);//按位取反
        System.out.println(cint^dint);
        ///不改变符号位
        System.out.println(cint<<1);
        ///不改变原数值
        System.out.println(cint);
        ///不改变符号位
        System.out.println(cint>>1);
        ///不改变原数值
        System.out.println(cint);
        ///高位补0
        System.out.println(cint>>>1);
        ///不改变原数值
        System.out.println(cint);

        //三元, ?:
        int eint=true?10:20;
        System.out.println(eint);

        //instanceof
        Integer aInteger=10;
        boolean flag=aInteger instanceof Integer;
        System.out.println(flag);
    }
}
