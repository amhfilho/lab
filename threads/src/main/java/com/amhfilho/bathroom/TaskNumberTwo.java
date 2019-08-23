package com.amhfilho.bathroom;

public class TaskNumberTwo implements Runnable{
    private Bathroom bathroom;

    public TaskNumberTwo(Bathroom bathroom) {
        this.bathroom = bathroom;
    }


    @Override
    public void run() {
        bathroom.number2();
    }
}
