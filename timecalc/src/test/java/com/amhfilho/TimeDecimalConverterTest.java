package com.amhfilho;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeDecimalConverterTest {
    TimeDecimalConverter converter = new TimeDecimalConverter();
    @Test
    public void testConversion0900(){
        String input = "09:00";
        float output = converter.toDecimal(input);
        assertEquals("9.0", String.valueOf(output));
    }

    @Test
    public void testConversion0948(){
        float output = converter.toDecimal("09:48");
        assertEquals("9.8", String.valueOf(output));
    }

    @Test
    public void testConversion0115(){
        float output = converter.toDecimal("01:15");
        assertEquals("1.25", String.valueOf(output));
    }

    @Test
    public void testConversion1030(){
        float output = converter.toDecimal("10:30");
        assertEquals("10.5", String.valueOf(output));

    }

}
