package utils;
// MobileAutomationFramework/src/main/java/com/example/utils/ConfigReader.java

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Configuration config;

    static {
        try {
            Configurations configs = new Configurations();
            config = configs.properties(new File("src/main/resources/config.properties"));
            logger.info("Configuration file loaded successfully.");
        } catch (ConfigurationException ex) {
            logger.error("Error loading configuration file: {}", ex.getMessage());
        }
    }

    public static String getProperty(String key) {
        return config.getString(key);
    }
}