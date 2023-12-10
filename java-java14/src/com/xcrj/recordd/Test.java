package com.xcrj.recordd;

public class Test {
    public static void main(String[] args) {
        Student stu = new Student(1001, "张三");
        System.out.println(stu); // Student[id=1001, name=张三]
        System.out.println(stu.getClass()); // class com.xcrj.recordd.Student
        System.out.println(stu.id()); // 1001
        System.out.println(stu.name()); // 张三
    }
}
