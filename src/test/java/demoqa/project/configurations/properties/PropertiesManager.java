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
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            LogManager.getLogger().error("An error occurred while loading the properties file", e);
        }
    }


    public static String getBrowser() {
        return PROPERTIES.getProperty("BROWSER");
    }


    public static String getProperty(String propertyName) {
        String propertyValue = PROPERTIES.getProperty(propertyName);
        if (propertyValue != null) {
            LogManager.getLogger().debug("Extracting the next value from the properties : {}", propertyValue);
            return propertyValue;
        } else {
            LogManager.getLogger().warn("{} is null or not found in the properties file", propertyName);
        }  return null;
    }

    public static Duration displayElementTimeout() {
        String propertyValue = PROPERTIES.getProperty("IMPLICIT_WAIT_TIMEOUT");
        try {
            return Duration.ofSeconds(Integer.parseInt(propertyValue));
        } catch (NumberFormatException | NullPointerException e) {
            LogManager.getLogger().warn("Invalid or missing displayedElementTimeout value in properties. Condition: Expected a valid integer but got an unparsable string. Using default value - 5 seconds.");
            return Duration.ofSeconds(5);
        }
    }

}
