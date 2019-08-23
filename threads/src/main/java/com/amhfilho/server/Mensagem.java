package com.amhfilho.server;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private String nick;
    private String message;

    public Mensagem(String nick, String message) {
        this.nick = nick;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "nick='" + nick + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
