package com.tuling.netty.chat;

import com.tuling.netty.User;
import com.tuling.netty.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/21
 * @Time: 9:30
 */
public class ObjectEncode extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, User user, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(ProtostuffUtil.serializer(user));
    }
}
