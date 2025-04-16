package base;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class); // Logger for this class.
    private static AndroidDriver driver; // Static driver instance.

    public static AndroidDriver getDriver() { // Gets the driver instance.
        if (driver == null) {
            createDriver(); // Creates driver if it's null.
        }
        return driver;
    }

    public static void createDriver() { // Creates the AndroidDriver.
        DesiredCapabilities capabilities = new DesiredCapabilities(); // Sets up device capabilities.
        capabilities.setCapability("platformName", ConfigReader.getProperty("platformName")); // Reads platform name.
        capabilities.setCapability("deviceName", ConfigReader.getProperty("deviceName")); // Reads device name.
        capabilities.setCapability("appPackage", ConfigReader.getProperty("appPackage")); // Reads app package.
        capabilities.setCapability("appActivity", ConfigReader.getProperty("appActivity")); // Reads app activity.
        capabilities.setCapability("platformVersion", ConfigReader.getProperty("platformVersion")); // Reads platform version.
        capabilities.setCapability("automationName", ConfigReader.getProperty("automationName")); // Reads automation name.
        String appiumServerUrl = ConfigReader.getProperty("appium.server.url"); // Reads Appium server URL.
        try {
            driver = new AndroidDriver(new URL(appiumServerUrl), capabilities); // Initializes AndroidDriver.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Sets implicit wait.
            logger.info("Appium driver initialized successfully."); // Logs success.
        } catch (MalformedURLException e) {
            logger.error("Error creating Appium driver: {}", e.getMessage()); // Logs error.
            throw new RuntimeException("Failed to initialize Appium driver", e); // Throws exception.
        }
    }

    public static void quitDriver() { // Quits the driver.
        if (driver != null) {
            driver.quit(); // Quits the driver if it's not null.
            driver = null; // Sets driver to null.
            logger.info("Appium driver quit successfully."); // Logs success.
        }
    }
}