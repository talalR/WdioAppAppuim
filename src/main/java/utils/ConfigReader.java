package utils;
// MobileAutomationFramework/src/main/java/com/example/utils/ConfigReader.java

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class); // Declares and initializes a Logger for the ConfigReader class.
    private static Configuration config; // Declares a static Configuration object to store configuration properties.

    static { // Static initializer block, executed only once when the class is loaded.
        try {
            Configurations configs = new Configurations(); // Creates a new Configurations object.
            config = configs.properties(new File("src/main/resources/config.properties")); // Loads configuration properties from the specified file.
            logger.info("Configuration file loaded successfully."); // Logs an informational message indicating successful loading.
        } catch (ConfigurationException ex) { // Catches ConfigurationException if an error occurs during file loading.
            logger.error("Error loading configuration file: {}", ex.getMessage()); // Logs an error message with the exception details.
        }
    }

    public static String getProperty(String key) { // Defines a method to retrieve a property value by its key.
        return config.getString(key); // Retrieves the string value associated with the given key from the loaded configuration.
    }
}