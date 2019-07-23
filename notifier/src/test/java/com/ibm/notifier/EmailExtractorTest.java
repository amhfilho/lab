package com.ibm.notifier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailExtractorTest {

    @Test
    public void givenString_whenExtract_thenReturnValidEmail(){
        String s = "*** blablabla blblabla ^ test2@gmail.com((& ";
        String email = new EmailExtractor().extract(s);
        assertEquals("test2@gmail.com", email);
    }
}
