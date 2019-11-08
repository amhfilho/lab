package com.amhfilho;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class TimeCalculatorTest {
    @Test
    public void shouldReturnTimePlus9Hours48Minutes(){
        TimeCalculator calc = new TimeCalculator();
        LocalTime end = calc.calculate(LocalTime.of(9,0));
        String expected = "18:48";
        String real = end.format(DateTimeFormatter.ofPattern("HH:mm"));
        assertEquals(expected, real);
    }

    @Test
    public void shouldReturnTimeMinus9Hours48Minutes(){
        TimeCalculator calc = new TimeCalculator();
        LocalTime end = calc.calculate(LocalTime.of(18, 48));
        String real = end.format(DateTimeFormatter.ofPattern("HH:mm"));
        assertEquals("09:00", real);
    }

    @Test
    public void shouldReturn9HoursPlus2Overtime(){
        TimeCalculator calc = new TimeCalculator();
        LocalTime end = calc.calculate(LocalTime.of(9, 0), 2);
        String real = end.format(DateTimeFormatter.ofPattern("HH:mm"));
        assertEquals("20:00", real);
    }

    @Test
    public void shouldReturn9Hours2OvertimeLess(){
        TimeCalculator calc = new TimeCalculator();
        LocalTime end = calc.calculate(LocalTime.of(20, 0), 2);
        String real = end.format(DateTimeFormatter.ofPattern("HH:mm"));
        assertEquals("09:00", real);
    }

    @Test
    public void shouldReturn9Hours48MinutesNoOvertime(){
        LocalTime end = new TimeCalculator().calculate(LocalTime.of(9, 0), 0);
        assertEquals("18:48", end.format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}
