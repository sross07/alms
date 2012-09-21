package org.alms.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig 
{
	private static Properties properties;
    private static ApplicationConfig config;
	
    static {
        config = new ApplicationConfig();
    }

    private ApplicationConfig ()  {

        try {

            InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");

            properties = new Properties();

            properties.load(in);

        } catch (IOException ex) 
        {
        	/* Don't have log4j setup yet*/
        }

    }

    public static ApplicationConfig getApplicationConfig () {
            return config;
    }

    public String getProperty (String propName) {
        return properties.getProperty(propName, "Null");
    }      

}
