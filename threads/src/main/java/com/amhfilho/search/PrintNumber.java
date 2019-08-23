package com.amhfilho.search;

public class PrintNumber implements Runnable{
    @Override
    public void run() {
        for(int i=1; i <= 1000; i++){
            System.out.println(Thread.currentThread().getId() + " - " + i);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintNumber());
        Thread t2 = new Thread(new PrintNumber());
        t1.start();
        t2.start();
    }
}
