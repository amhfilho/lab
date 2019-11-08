package com.amhfilho.chat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessageReceiver implements Runnable {
    private String nick;
    private Socket socket;

    public MessageReceiver(Socket socket,String nick) {
        this.nick = nick;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            while(in.hasNextLine()){
                String line = in.nextLine();
                Message message = parseJson(line);
                if(!message.getNick().equals(nick)) {
                    System.out.println(String.format("Message from %s: %s", message.getNick(), message.getText()));
                }
            }
            in.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Message parseJson(String input) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.readValue(input, Message.class);
    }
}
