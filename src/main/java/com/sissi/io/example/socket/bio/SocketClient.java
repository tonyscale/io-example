package com.sissi.io.example.socket.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class SocketClient {


    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("0.0.0.0",9000));
        socket.setSendBufferSize(1);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        System.out.println(inputStream.available());
        try{
            while (true){
                StringBuilder str = new StringBuilder();
                for(int i=0;i<100;i++){
                    str.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                System.out.println("Start");
                outputStream.write(str.toString().getBytes());
                outputStream.flush();
                System.out.println("Send" + new Random().nextInt(1000));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        for(int read = inputStream.read();read != -1;read = inputStream.read()){
            System.out.println(new java.lang.String(new char[]{(char) read}));
        }
    }
}
