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
        if(args.length == 2){
            if(isInteger(args[1])){
                extra = Integer.parseInt(args[1]);
                LocalTime end = new TimeCalculator().calculate(begin, extra);
                System.out.println(end);
            }
            else if(args[1].equals("-c")){
                TimeDecimalConverter converter = new TimeDecimalConverter();
                System.out.println(converter.toDecimal(sBegin));
            }
        }

    }

    private static boolean isInteger(String arg) {
        if(arg == null) return false;
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
