package com.xcrj.netty.tcp2;

public class MyMessage {
    private int len; //！！！
    private byte[] content;

    public int getLen() {
        return len;
    }
    public void setLen(int len) {
        this.len = len;
    }
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
}
