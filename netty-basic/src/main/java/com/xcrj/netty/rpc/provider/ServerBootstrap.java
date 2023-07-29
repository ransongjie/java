package com.xcrj.netty.rpc.provider;

import com.xcrj.netty.rpc.netty.NettyServer;

/**
 * Provider需要对外提供服务。因此，将Provider作为服务端
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("localhost", 7000);
    }
}
