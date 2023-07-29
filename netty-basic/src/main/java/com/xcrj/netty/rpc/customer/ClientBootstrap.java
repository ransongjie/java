package com.xcrj.netty.rpc.customer;

import com.xcrj.netty.rpc.netty.NettyClient;
import com.xcrj.netty.rpc.public_interface.HelloService;

public class ClientBootstrap {
    //定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws  Exception{
        //创建消费者
        NettyClient nettyClient = new NettyClient();
        //创建代理对象
        HelloService helloService = (HelloService) nettyClient.getBean(HelloService.class, providerName);
        //每隔2S, Client(Customer)调用一次Server(Provider)
        while(true){
            Thread.sleep(2 * 1000);
            //通过代理对象调用服务端(Provider)的服务
            String result = helloService.hello("你好，Provider");
            System.out.println("调用响应的结果：" + result);
        }
    }
}
