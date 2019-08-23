package com.amhfilho.bathroom;

public class Main {
    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();

        Thread t1 = new Thread(new TaskNumberOne(bathroom),"Antonio");
        Thread t2 = new Thread(new TaskNumberTwo(bathroom), "Miguel");
        Thread cleaner = new Thread(new TaskCleaning(bathroom), "Cleaner");
        t1.start();
        t2.start();
        cleaner.setDaemon(true);
        cleaner.start();
    }
}
