package com.xcrj.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * SimpleChannelInboundHandler：InboundHandler, channel>>pipeline
 * HttpObject：客户端和服务端相互通信的数据
 */
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 收到数据，执行此方法
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
        System.out.println("channel: "+channelHandlerContext.channel());
        System.out.println("channelPipeline: "+channelHandlerContext.pipeline());
        System.out.println("channelPipeline获取channel: "+channelHandlerContext.pipeline().channel());
        System.out.println("channelHandler: "+channelHandlerContext.handler());

        if(msg instanceof HttpRequest) {
            //HttpRequest
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if("/no_respond".equals(uri.getPath())) {
                System.out.println("请求了no_respond, 不做响应");
                return;
            }

            //HttpResponse
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务端", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
