package com.xcrj.test;

public class Test2 {
    public static void main(String[] args) {
        synchronized (Test2.class){
            Object obj=new Object();
            //param1=milliseconds, param2=nanos
//            obj.wait(100,1);
        }
    }
}
