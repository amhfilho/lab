package com.amhfilho.server;

import java.io.IOException;
import java.net.Socket;

public class ClienteTarefas {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost",12345);
        System.out.println("Conexao estabelecida para o cliente ");

        Thread recebeResposta = new Thread(new RecebeResposta(socket));
        Thread enviaComandos = new Thread(new EnviaComandos(socket));

        recebeResposta.start();
        enviaComandos.start();
        enviaComandos.join();

        socket.close();
    }
}
