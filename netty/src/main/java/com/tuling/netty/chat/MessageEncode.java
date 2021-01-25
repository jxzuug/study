package com.tuling.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/21
 * @Time: 9:30
 */
public class MessageEncode extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol message,
                          ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(message.getLen());
        byteBuf.writeBytes(message.getContent());
    }
}
