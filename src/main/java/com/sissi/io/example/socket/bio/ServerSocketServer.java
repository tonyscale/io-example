package com.sissi.io.example.socket.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketServer {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9000));

        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        int read = inputStream.read();

        System.out.println(new String(new char[]{(char) read}));
        inputStream.close();

        OutputStream outputStream = socket.getOutputStream();

        while (true){
            outputStream.write("ddddd".getBytes());
            System.out.println("write");
            Thread.sleep(1000);
        }



    }
}
