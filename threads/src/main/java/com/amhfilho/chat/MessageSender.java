package com.amhfilho.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable {

    private Socket socket;
    private String nickname;

    public MessageSender(Socket socket, String nickname) {
        this.socket = socket;
        this.nickname = nickname;
    }

    @Override
    public void run() {

        try {
                ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
                Scanner keyboard = new Scanner(System.in);
                while (keyboard.hasNextLine()) {
                    String line = keyboard.nextLine();
                    stream.writeObject(new Message(nickname, line));
                }
                keyboard.close();
                stream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
