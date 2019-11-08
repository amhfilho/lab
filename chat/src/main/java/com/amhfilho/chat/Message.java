package com.amhfilho.chat;

public class Message {
    private String nick;
    private String text;

    protected Message(){}

    public Message(String nick, String text) {
        this.nick = nick;
        this.text = text;
    }

    public String getNick() {
        return nick;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nick='" + nick + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
