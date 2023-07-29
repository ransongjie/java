package com.xcrj.error;
//自定义Exception子类
public class MyException extends Exception {
    public MyException() {
        // super();//默认调用
    }

    public MyException(String msg) {
        super(msg);
    }
}
