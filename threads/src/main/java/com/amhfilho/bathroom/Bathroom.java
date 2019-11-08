package com.amhfilho.bathroom;

public class Bathroom {
    private boolean sujo=true;

    public void number1(){
        String name = Thread.currentThread().getName();
        System.out.println(name + " knocking");
        synchronized (this) {
            System.out.println(name + " entered bathroom");
            while(sujo){
                waitOutside(name);
            }
            System.out.println(name + " doing fast thing");
            sleep(5000);
            sujo=true;
            System.out.println(name + " flushing");
            System.out.println(name + " washing hands");
            System.out.println(name + " finished");

        }
    }



    public void clean(){
        String nome = Thread.currentThread().getName();
        System.out.println(nome + " knocking");

        synchronized(this) {
            System.out.println(nome + " entered bathroom");

            if(!sujo){
                System.out.println(nome+ " bathroom is already clean, getting out");
                return;
            }
            System.out.println(nome + " entering bathroom");
            System.out.println(nome + " cleaning bathroom");
            this.sujo=false;
            sleep(12000);
            notifyAll();
            System.out.println("bathroom cleaned!");
        }
    }


    public void number2() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " knocking");
        synchronized (this) {
            System.out.println(name + " entered bathroom");

            while(sujo){
                waitOutside(name);
            }
            System.out.println(name + " doing slow thing");
            sleep(10000);
            sujo=true;
            System.out.println(name + " flushing");
            System.out.println(name + " washing hands");
            System.out.println(name + " finished");

        }
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitOutside(String name) {
        System.out.println(name + " disse: Eca");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
