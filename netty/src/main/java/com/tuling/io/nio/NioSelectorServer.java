package com.tuling.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Liu Chang
 * @Description:
 * @Date: 2021/1/19
 * @Time: 10:02
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {
        // 创建NIO ServerSocketChannel,与BIO的serverSocket类似
        ServerSocketChannel serverSocket = ServerSocketChannel.open();

        // 设置ServerSocketChannel为非阻塞
        serverSocket.configureBlocking(false);
        // 打开Selector处理Channel,即创建epoll
        Selector selector = Selector.open();
        // 把ServerSocketChannel注册到selector上，并且selector对客户端accept连接操作感兴趣
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功");

        new Thread(() -> {
            try {
                Thread.sleep(10500);
                serverSocket.socket().bind(new InetSocketAddress(9000));
                System.out.println("绑定端口成功");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            // 阻塞等待需要处理的事情发生
            selector.select(100);

            // 获取selector中注册的全部事件的SelectorKey 实例
            Set <SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println(selectionKeys.size());
            Iterator <SelectionKey> iterator = selectionKeys.iterator();

            // 遍历SelectionKey对事件进行处理
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 如果时OP_ACCEPT事件，则进行连接获取和事件注册
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    // 这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (key.isReadable()) {
                    // 如果时OP_READ事件，则进行读取和打印
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    //  非阻塞模式read方法不会阻塞，否则会阻塞
                    int len = sc.read(byteBuffer);
                    // 如果有数据，把数据打印出来
                    if (len > 0) {
                        System.out.println("接收到消息:" + new String(byteBuffer.array()));
                    } else if (len == -1) {
                        // 如果客户端断开，把socket从集合中去掉
                        System.out.println("客户端断开连接");
                        sc.close();
                    }
                }
                // 从事件集合中删除本次处理的key,防止下次select重复处理
                iterator.remove();
            }

        }

    }


}
