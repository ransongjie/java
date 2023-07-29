package com.xcrj.netty.rpc.provider;

import com.xcrj.netty.rpc.public_interface.HelloService;

public class HelloServiceImpl implements HelloService {
    private static int count = 0;
    @Override
    public String hello(String msg) {
        if(msg != null) {
            return "你好客户端, 我已经收到你的消息，[" + msg + "]" + (++count) + " 次";
        } else {
            return "你好客户端, 我已经收到你的消息";
        }
    }
}
