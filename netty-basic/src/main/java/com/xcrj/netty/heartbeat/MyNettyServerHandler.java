package com.xcrj.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyNettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 用户事件触发时，调用此方法
     * @param channelHandlerContext
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println("客户端："+channelHandlerContext.channel().remoteAddress() + ", " + eventType);
            System.out.println("服务器做处理空闲通道..");
            //如果发生空闲，关闭通道
            // ctx.channel().close();
        }
    }
}
