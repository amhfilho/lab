package com.amhfilho.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatHandler implements Runnable {
    private Server server;
    private Socket socket;

    public ChatHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Handling chat for " + socket);

        try {
            Scanner in = new Scanner(socket.getInputStream());
            while(in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
                server.broadcast(line);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
