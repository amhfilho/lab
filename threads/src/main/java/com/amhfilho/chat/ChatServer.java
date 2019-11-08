package com.amhfilho.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int PORT = 12345;
    private List<ObjectOutputStream> streams = new ArrayList<>();

    public void init() throws IOException {
        System.out.println("Initiating chat server on port " + PORT);
        ServerSocket serverSocket = new ServerSocket(PORT);

        while(true){
            Socket socket = serverSocket.accept();
            streams.add(new ObjectOutputStream(socket.getOutputStream()));
            System.out.println("New client connection " + socket.getInetAddress().getHostAddress());
            Thread chatThread = new Thread(new ChatThread(this,socket));
            chatThread.start();

        }

    }

    public void broadcast(Message message){
        streams.forEach(x-> {
            try {
                x.writeObject(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().init();
    }
}
