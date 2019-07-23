package com.ibm.notifier;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PropertyReaderTest {

    @Test
    public void shouldReturnPropertyFromFile() throws IOException {
        String fileName = "test.properties";
        String property = PropertyReader.load(fileName).get("key");
        assertEquals("testvalue", property);
    }
}
