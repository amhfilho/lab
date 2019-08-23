package com.amhfilho.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviaComandos implements Runnable {
    private Socket socket;
    private String nick;

    public EnviaComandos(Socket socket, String nick) {
        this.socket = socket;
        this.nick = nick;
    }

    @Override
    public void run() {
        try {
            System.out.println("Pode enviar comandos!");
            //PrintStream saida = new PrintStream(socket.getOutputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            Scanner teclado = new Scanner(System.in);
            while (teclado.hasNextLine()) {

                String linha = teclado.nextLine();

                if (linha.trim().equals("")) {
                    break;
                }

                //saida.println(linha);
                objectOutputStream.writeObject(new Mensagem(nick, linha));
            }

            //saida.close();
            //objectOutputStream.flush();
            teclado.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
