package base;
// MobileAutomationFramework/src/main/java/com/example/base/DriverManager.java
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    public static void createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", ConfigReader.getProperty("platformName"));
        capabilities.setCapability("deviceName", ConfigReader.getProperty("deviceName"));
        capabilities.setCapability("appPackage", ConfigReader.getProperty("appPackage"));
        capabilities.setCapability("appActivity", ConfigReader.getProperty("appActivity"));
        capabilities.setCapability("platformVersion", ConfigReader.getProperty("platformVersion"));
        capabilities.setCapability("automationName", ConfigReader.getProperty("automationName"));
        String appiumServerUrl = ConfigReader.getProperty("appium.server.url");
        try {
            driver = new AndroidDriver(new URL(appiumServerUrl), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Adjust as needed
            logger.info("Appium driver initialized successfully.");
        } catch (MalformedURLException e) {
            logger.error("Error creating Appium driver: {}", e.getMessage());
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Appium driver quit successfully.");
        }
    }
}