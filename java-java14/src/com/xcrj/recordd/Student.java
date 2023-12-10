package com.xcrj.recordd;

// record 关键字，注意 属性属性在 括号中
record Student(Integer id, String name){
}

// 反编译结果
// final 修饰类
// final class Student extends java.lang.Record {
//    // final 常量
//    private final java.lang.Integer id;
//    private final java.lang.String name;
//
//    // 构造函数
//    public Student(java.lang.Integer id, java.lang.String name) { /* compiled code */ }
//
//    public java.lang.Integer id() { /* compiled code */ }
//
//    public java.lang.String name() { /* compiled code */ }
//
//    public java.lang.String toString() { /* compiled code */ }
//
//    public final int hashCode() { /* compiled code */ }
//
//    public final boolean equals(java.lang.Object o) { /* compiled code */ }
//}