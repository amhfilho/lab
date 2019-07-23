package com.ibm.notifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads properties from external file
 */
public class PropertyReader {

    private Properties properties;

    private PropertyReader(String fileName) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(fileName));
    }

    public static PropertyReader load(String fileName) throws IOException {
        PropertyReader instance = new PropertyReader(fileName);
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() { return this.properties; }


}
