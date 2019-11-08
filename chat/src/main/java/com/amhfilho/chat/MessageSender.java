package com.amhfilho.chat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private Socket socket;
    private String nick;

    public MessageSender(Socket socket, String nick) {
        this.socket = socket;
        this.nick = nick;
    }

    @Override
    public void run() {
        try {
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner in = new Scanner(System.in);
            while(in.hasNextLine()){
                String entry = in.nextLine();
                out.println(jsonString(new Message(nick, entry)));
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String jsonString(Message message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(message);
    }
}
