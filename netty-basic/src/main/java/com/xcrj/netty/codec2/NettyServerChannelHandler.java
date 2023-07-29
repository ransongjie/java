package com.xcrj.netty.codec2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyServerChannelHandler extends SimpleChannelInboundHandler<MyMessagePOJO.MyMessage>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessagePOJO.MyMessage msg) throws Exception {
        //根据dataType来显示不同的信息
        MyMessagePOJO.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == MyMessagePOJO.MyMessage.DataType.StudentType) {
            MyMessagePOJO.Student student = msg.getStudent();
            System.out.println("学生id：" + student.getId() + "，学生名字：" + student.getName());
        } else if(dataType == MyMessagePOJO.MyMessage.DataType.WorkerType) {
            MyMessagePOJO.Worker worker = msg.getWorker();
            System.out.println("工人的名字：" + worker.getName() + "，年龄：" + worker.getAge());
        } else {
            System.out.println("传输的类型不正确");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好，客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
        cause.printStackTrace();
    }
}
