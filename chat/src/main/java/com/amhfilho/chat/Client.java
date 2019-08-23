package com.amhfilho.chat;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static int defaultPort = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {
        checkNickname(args);
        System.out.println("Opening chat client for " + args[0] + " on port " + defaultPort);
        Socket socket = new Socket("localhost", defaultPort);
        System.out.println("Client ready for " + args[0]);
        Thread sender = new Thread(new MessageSender(socket,args[0]));
        Thread receiver = new Thread(new MessageReceiver(socket,args[0]));
        receiver.start();
        sender.start();
        sender.join();
    }

    private static void checkNickname(String[] args){
        if(args == null || args.length == 0){
            System.err.println("Please enter nickname");
            throw new IllegalArgumentException("Nickname not set");
        }
    }


}
