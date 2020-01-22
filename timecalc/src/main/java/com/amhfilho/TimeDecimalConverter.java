package com.amhfilho;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeDecimalConverter {
    public float toDecimal(String sTime){
        LocalTime time = LocalTime.parse(sTime, DateTimeFormatter.ofPattern("HH:mm"));
        float decimal = time.getMinute()/60f;
        return time.getHour() + decimal;
    }
}
