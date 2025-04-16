package base;
// MobileAutomationFramework/src/main/java/com/example/base/BasePage.java
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Adjust timeout as needed
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitForVisibility(org.openqa.selenium.WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(org.openqa.selenium.WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(org.openqa.selenium.WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getText(org.openqa.selenium.WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    public boolean isElementVisible(org.openqa.selenium.WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}