package com.xcrj.netty.codec2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class NettyClientChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //随机的发送Student或者Workder对象
        int random = new Random().nextInt(2);
        MyMessagePOJO.MyMessage myMessage = null;
        if(0 == random) { //发送Student对象
            myMessage = MyMessagePOJO.MyMessage
                    .newBuilder()
                    .setDataType(MyMessagePOJO.MyMessage.DataType.StudentType)
                    .setStudent(
                            MyMessagePOJO.Student
                                    .newBuilder()
                                    .setId(5).setName("冉同学")
                                    .build())
                    .build();
        } else { //发送一个Worker对象
            myMessage = MyMessagePOJO.MyMessage
                    .newBuilder()
                    .setDataType(MyMessagePOJO.MyMessage.DataType.WorkerType)
                    .setWorker(
                            MyMessagePOJO.Worker
                                    .newBuilder()
                                    .setAge(20).setName("李工")
                                    .build())
                    .build();
        }
        ctx.writeAndFlush(myMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
