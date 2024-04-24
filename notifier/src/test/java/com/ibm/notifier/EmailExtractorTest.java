package com.ibm.notifier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailExtractorTest {

    @Test
    public void givenString_whenExtract_thenReturnValidEmail(){
        String s = "C:\\Users\\username\\My Documents\\SametimeTranscripts\\xxx@xxx.com\\ChatHistory.properties\n" +
                "Fri Sep 06 10:21:36 BRT 2019";
        String email = new EmailExtractor().extract(s);
        assertEquals("xxx@xxx.com", email);
    }
}
