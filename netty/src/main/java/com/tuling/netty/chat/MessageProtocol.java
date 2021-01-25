package com.tuling.netty.chat;

import io.netty.util.CharsetUtil;

/**
 * @Author: Liu Chang
 * @Description: 自定义协议包
 * @Date: 2021/1/21
 * @Time: 10:28
 */
public class MessageProtocol {

    //定义一次发送包体长度
    private int len;
    //一次发送包体内容
    private byte[] content;

    public MessageProtocol(String content) {
        this.len = content.getBytes(CharsetUtil.UTF_8).length;
        this.content = content.getBytes(CharsetUtil.UTF_8);
    }

    public MessageProtocol() {

    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageProtocol{" +
                "len=" + len +
                ", content=" + new String(content, CharsetUtil.UTF_8) +
                '}';
    }
}
