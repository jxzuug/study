package com.tuling.netty.chat;

import com.tuling.netty.User;
import com.tuling.netty.util.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/21
 * @Time: 9:30
 */
public class ObjectDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List <Object> list) throws Exception {
        list.add(ProtostuffUtil.deserializer(byteBuf.array(), User.class));
    }
}
