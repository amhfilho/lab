package com.amhfilho.chat;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {
        String nickname = args[0];
        Socket socket = new Socket("127.0.0.1", PORT);
        System.out.println("Client connected to chat server");
        while(true) {
            Thread sender = new Thread(new MessageSender(socket, nickname));
            Thread receiver = new Thread(new MessageReceiver(socket));
            receiver.start();
            sender.start();
            sender.join();
        }

        //socket.close();
    }
}
