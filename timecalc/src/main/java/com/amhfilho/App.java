package com.amhfilho;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class App {
    public static void main(String[] args) {
        String sBegin = args[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime begin = LocalTime.parse(sBegin, formatter);

        int extra = 0;
        int minutes = 48;
        if(args.length > 1){
            extra = Integer.parseInt(args[1]);
            minutes = 0;
        }
        LocalTime end = begin.plus(9, ChronoUnit.HOURS)
                .plus(minutes, ChronoUnit.MINUTES)
                .plus(extra, ChronoUnit.HOURS);

        System.out.println(end);
    }
}
