package demoqa.project.configurations.properties;

import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class PropertiesManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = PropertiesManager.class.getClassLoader().getResourceAsStream("properties/config.properties")) {
            if (input != null) {
                PROPERTIES.load(input);
            } else {
                LogManager.getLogger().error("Error: Unable to find properties file");
            }
        } catch (IOException e) {
            LogManager.getLogger().error("An error occurred while loading the properties file", e);
        }
    }

    private PropertiesManager() {}


    public static String getBrowser() {
        return PROPERTIES.getProperty("BROWSER");
    }


    public static String getProperty(String propertyName) {
        String propertyValue = PROPERTIES.getProperty(propertyName);
        if (propertyValue != null) {
            LogManager.getLogger().info("Extracting the next {} value from the properties.", propertyValue);
            return propertyValue;
        } else {
            LogManager.getLogger().warn("{} is null or not found in the properties file", propertyName);
        }  return null;
    }

    public static Duration displayElementTimeout() {
        String value = PROPERTIES.getProperty(("IMPLICIT_WAIT_TIMEOUT"));
        try {
            return Duration.ofSeconds(Integer.parseInt(value));
        } catch (NumberFormatException | NullPointerException e) {
            LogManager.getLogger().warn("Invalid or missing displayedElementTimeout value in properties. Using default value - 5 seconds.");
            return Duration.ofSeconds(5);
        }
    }

}