package com.amhfilho.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviaComandos implements Runnable {
    private Socket socket;

    public EnviaComandos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Pode enviar comandos!");
            PrintStream saida = new PrintStream(socket.getOutputStream());

            Scanner teclado = new Scanner(System.in);
            while (teclado.hasNextLine()) {
                String linha = teclado.nextLine();
                if (linha.trim().equals("")) {
                    break;
                }
                saida.println(linha);
            }
            saida.close();
            teclado.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
