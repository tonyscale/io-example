package com.sissi.io.example.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SocketChannelClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("0.0.0.0", 9000));

        int count = 0;
        while(true){
            System.out.println(count);
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                SocketChannel channel = (SocketChannel) selectionKey.channel();
                channel.configureBlocking(false);

                if(selectionKey.isConnectable()){
                    System.out.println(channel.finishConnect());
                    channel.register(selector,SelectionKey.OP_WRITE);
                }

                if(selectionKey.isWritable()){

                    count ++;
                    System.out.println("write count is " + count);
                    if(count >125){

                    }
                    channel.write(ByteBuffer.wrap(msg().getBytes()));
                    channel.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }

                if(selectionKey.isReadable()){

                    System.out.println("Readable now ");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                    channel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                }
            }
        }

    }



    public static String msg(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<100;i++){
            sb.append("dsdfsdddddddddddddddddddddddddddddddddddddddddddddd");
        }
        return sb.toString();
    }


}
