package com.tuling.netty.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/18
 * @Time: 11:03
 */
public class ChatClientHandler extends SimpleChannelInboundHandler <MessageProtocol> {

    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol s) throws Exception {
        System.out.println(s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
