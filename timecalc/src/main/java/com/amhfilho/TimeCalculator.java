package com.amhfilho;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeCalculator {
    public LocalTime calculate(LocalTime start) {
        if(start.isBefore(LocalTime.NOON)){
            return start.plus(9, ChronoUnit.HOURS).plus(48, ChronoUnit.MINUTES);
        }
        return start.minus(9, ChronoUnit.HOURS).minus(48, ChronoUnit.MINUTES);
    }

    public LocalTime calculate(LocalTime start, int overtimeHours) {
        if(overtimeHours == 0){
            return calculate(start);
        }
        if(start.isBefore(LocalTime.NOON)){
            return start.plus(9, ChronoUnit.HOURS).plus(overtimeHours, ChronoUnit.HOURS);
        }
        return start.minus(9, ChronoUnit.HOURS).minus(overtimeHours, ChronoUnit.HOURS);
    }
}
