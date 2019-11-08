package com.amhfilho.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static int defaultPort = 12345;
    private List<PrintStream> streams = new ArrayList<>();

    public void run(int port) throws IOException {
        System.out.println("Initiating chat server on port " + port);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Chat server online");

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Accepted client connection on port: " + socket.getPort());
            streams.add(new PrintStream(socket.getOutputStream()));
            new Thread(new ChatHandler(this,socket)).start();
        }


    }

    public void broadcast(String message){
        streams.forEach(x->x.println(message));
    }

    public static void main(String[] args) {
        int port = Server.defaultPort;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }
        try {
            new Server().run(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
