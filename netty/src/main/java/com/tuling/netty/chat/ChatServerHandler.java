package com.tuling.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/18
 * @Time: 10:59
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush(new MessageProtocol("客户端:" + channel.remoteAddress() + "上线了!" + SDF.format(System.currentTimeMillis())));
        CHANNEL_GROUP.add(channel);
        System.out.println("客户端:" + channel.remoteAddress() + "上线了!");
    }

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel，管道pipeline
     * @param s 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol s) throws Exception {
        System.out.println("====服务端接收到消息如下====");
        System.out.println(s);
        CHANNEL_GROUP.forEach(channel -> {
            // 如果是自己
            if (ctx.channel().equals(channel)) {
                channel.writeAndFlush(new MessageProtocol("自己:" + SDF.format(System.currentTimeMillis()) + "\n" + new String(s.getContent(), CharsetUtil.UTF_8)));
            } else {
                channel.writeAndFlush(new MessageProtocol("客户端:" + channel.remoteAddress() + SDF.format(System.currentTimeMillis()) + "\n" + new String(s.getContent(), CharsetUtil.UTF_8)));
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush(new MessageProtocol("客户端:" + channel.remoteAddress() + "下线了!" + SDF.format(System.currentTimeMillis())));
    }

    /**
     * 处理异常, 一般是需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
