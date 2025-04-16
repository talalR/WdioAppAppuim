package base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class BasePage {

    protected AndroidDriver driver; // Declares a protected AndroidDriver instance for interacting with the app.
    protected WebDriverWait wait; // Declares a protected WebDriverWait instance for implementing explicit waits.

    public BasePage() { // Defines the constructor for the BasePage class.
        this.driver = DriverManager.getDriver(); // Initializes the driver using the DriverManager class.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Initializes the wait with a 15-second timeout.
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // Initializes page elements using PageFactory.
    }

    public void waitForVisibility(org.openqa.selenium.WebElement element) { // Defines a method to wait for an element to be visible.
        wait.until(ExpectedConditions.visibilityOf(element)); // Waits until the specified element is visible.
    }

    public void click(org.openqa.selenium.WebElement element) { // Defines a method to click on an element.
        waitForVisibility(element); // Waits for the element to be visible before clicking.
        element.click(); // Clicks the element.
    }

    public void sendKeys(org.openqa.selenium.WebElement element, String text) { // Defines a method to send keys to an element.
        waitForVisibility(element); // Waits for the element to be visible before sending keys.
        element.sendKeys(text); // Sends the specified text to the element.
    }

    public String getText(org.openqa.selenium.WebElement element) { // Defines a method to get the text of an element.
        waitForVisibility(element); // Waits for the element to be visible before getting its text.
        return element.getText(); // Returns the text of the element.
    }

    public boolean isElementVisible(org.openqa.selenium.WebElement element) { // Defines a method to check if an element is visible.
        try {
            return element.isDisplayed(); // Returns true if the element is displayed, false otherwise.
        } catch (org.openqa.selenium.NoSuchElementException e) { // Catches NoSuchElementException if the element is not found.
            return false; // Returns false if the element is not found.
        }
    }
}