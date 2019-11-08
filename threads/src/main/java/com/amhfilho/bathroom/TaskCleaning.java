package com.amhfilho.bathroom;

public class TaskCleaning implements Runnable {
    private Bathroom bathroom;
    public TaskCleaning(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    @Override
    public void run() {
        while(true) {
            bathroom.clean();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
