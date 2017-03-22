package com.seeyon.nio.socket;

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
 * Created by yangyu on 2017/2/22.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(8088));
        serverChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    System.out.println("accept");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    int readKeys = socketChannel.read(byteBuffer);
                    if (readKeys > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, readKeys));
                    }
                    socketChannel.write(ByteBuffer.wrap("接收到了".getBytes()));

                } else if (key.isReadable()) {
                    System.out.println("read");

                } else if (key.isWritable()) {
                    System.out.println("write");

                }
            }
        }
    }
}
