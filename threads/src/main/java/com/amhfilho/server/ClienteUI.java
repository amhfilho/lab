package com.amhfilho.server;

import javax.swing.*;
import java.awt.*;

public class ClienteUI {
    private JFrame clientFrame;

    public void init(){
        clientFrame = new JFrame("Cliente Tarefas");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setSize(800,600);
        clientFrame.setLayout(new BorderLayout());
        clientFrame.add(new JTextArea(), BorderLayout.NORTH);
        clientFrame.add(new JButton("Enviar"), BorderLayout.CENTER);
        clientFrame.add(new JTextField(), BorderLayout.SOUTH);
        clientFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClienteUI().init();
    }
}
