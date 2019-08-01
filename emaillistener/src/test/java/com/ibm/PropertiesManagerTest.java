package com.ibm;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManagerTest {

    @Test
    public void shouldLoadPropertiesFromFile() throws IOException {
        PropertiesManager propertiesManager = new PropertiesManager();
        Properties properties = propertiesManager.loadFrom("test.properties");
        String value = properties.getProperty("property");
        Assert.assertEquals("test",value);
    }

}
