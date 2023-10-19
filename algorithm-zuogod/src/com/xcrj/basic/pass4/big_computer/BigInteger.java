package com.xcrj.basic.pass4.big_computer;

/**
 * 这个类内部有两个成员变量，一个表示符号，另一个用字节数组表示数值的二进制数
 * 有一个构造方法，把一个包含有多位数值的字符串转换到内部的符号和字节数组中
 * 提供加减乘除的功能
 */
public class BigInteger {
    //符号
    private int sign;
    //值
    private byte[] val;

    /**
     * 字符串转byte[]val
     *
     * @param val
     */
    public BigInteger(byte[] val) {

    }

    /**
     * 加法
     *
     * @param other
     * @return
     */
    public BigInteger add(BigInteger other) {
        return other;
    }

    public BigInteger sub(BigInteger other) {
        return other;
    }

    public BigInteger mul(BigInteger other) {
        return other;
    }

    public BigInteger div(BigInteger other) {
        return other;
    }
}