package com.tuling.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/19
 * @Time: 9:52
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        // 向服务端发送数据
        socket.getOutputStream().write("HellowServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        // 接收服务器回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据:" + new String(bytes));
        socket.close();
    }
}
