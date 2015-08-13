package com.almundo.automation.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final String PATH = "com/almundo/automation/";

    public String getPropertiesValues(String key, String propertyFile) {
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            String propLocation = PATH + "/" + propertyFile;

            inputStream = this.getClass().getClassLoader().getResourceAsStream(propLocation);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException(propLocation + " was not found in the classpath");
            }

            return properties.getProperty(key);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error");
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean getBooleanFromProperties(String key, String propertyFile) {
    	return Boolean.parseBoolean(this.getPropertiesValues(key, propertyFile));
    }


}
