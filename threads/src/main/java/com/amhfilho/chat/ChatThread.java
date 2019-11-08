package com.amhfilho.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ChatThread implements Runnable {
    private Socket socket;
    private ChatServer server;

    public ChatThread(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message message = (Message)is.readObject();
            System.out.println(message.toString());
            server.broadcast(message);
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
