package com.amhfilho.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReceiver implements Runnable {

    private Socket socket;

    public MessageReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) inputStream.readObject();
                System.out.println(message);
                inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
