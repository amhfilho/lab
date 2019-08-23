package com.amhfilho.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        List<String> lista = new Vector();

        for(int i = 0 ; i < 10; i++){
            new Thread(new Task(lista,i)).start();
        }

        Thread.sleep(2000);

        lista.forEach(System.out::println);

    }
}
