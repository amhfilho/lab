package com.ibm.notifier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailExtractorTest {

    @Test
    public void givenString_whenExtract_thenReturnValidEmail(){
        String s = "C:\\Users\\AntonioMarioHenrique\\My Documents\\SametimeTranscripts\\amhfilho@br.ibm.com\\ChatHistory.properties\n" +
                "Fri Sep 06 10:21:36 BRT 2019";
        String email = new EmailExtractor().extract(s);
        assertEquals("amhfilho@br.ibm.com", email);
    }
}
