package com.xcrj;

// java SimpleCompileRun.java
class SimpleCompileRun2{
    public static void main(String[] args) {
        // 使用了另外一个.java文件，简化编译运算将报错
        SimpleCR simpleCR=new SimpleCR();
        simpleCR.setName("xcrj");
        System.out.println(simpleCR.getName());
    }
}

class SimpleCompileRun1{
    public static void main(String[] args) {
        System.out.println("SimpleCompileRun1");
    }
}

public class SimpleCompileRun {
    public static void main(String[] args) {
        System.out.println("SimpleCompileRun");
    }
}
