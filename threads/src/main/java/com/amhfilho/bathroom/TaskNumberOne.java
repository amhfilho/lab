package com.amhfilho.bathroom;

public class TaskNumberOne implements Runnable{
    private Bathroom bathroom;

    public TaskNumberOne(Bathroom bathroom) {
        this.bathroom = bathroom;
    }


    @Override
    public void run() {
        bathroom.number1();
    }
}
