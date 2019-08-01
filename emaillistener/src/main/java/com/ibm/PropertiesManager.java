package com.ibm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    public static final String SMTP_PROPERTIES = "smtp.properties";


    public Properties loadFrom(String file) throws IOException{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(file)) {
            props.load(resourceStream);
        }
        return props;
    }
}
