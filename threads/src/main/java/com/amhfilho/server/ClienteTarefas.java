package com.amhfilho.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost",12345);
        String nick = args[0];
        System.out.println("Conexao estabelecida para o cliente " + nick);

        //PrintStream saida = new PrintStream(socket.getOutputStream());
        //Scanner teclado = new Scanner(System.in);

        Thread recebeResposta = new Thread(new RecebeResposta(socket));
        Thread enviaComandos = new Thread(new EnviaComandos(socket,nick));

        recebeResposta.start();
        enviaComandos.start();
        enviaComandos.join();
//        while(teclado.hasNextLine()) {
//            String linha = teclado.nextLine();
//            if(linha.trim().equals("exit")){
//                break;
//            }
//            if(!linha.trim().equals("")) {
//                saida.println(linha);
//            }
//            Scanner respostaServidor = new Scanner(socket.getInputStream());
//            while(respostaServidor.hasNextLine()){
//                String linhaDoServidor = respostaServidor.nextLine();
//                System.out.println("Resposta: " + linhaDoServidor);
//            }
//            respostaServidor.close();
//        }

        //teclado.close();
        //saida.close();
        socket.close();
    }
}
