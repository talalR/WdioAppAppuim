package pages;


import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class SwipePage extends BasePage {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SUPPORT VIDEOS']")
    public WebElement supportVideosCard;

    public SwipePage() {
        super();
    }

    public void swipe(int startX, int startY, int endX, int endY, int durationMs) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMs), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(0));
        ((AndroidDriver) driver).perform(Collections.singletonList(swipe));
    }

    public void swipeRightToLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.9);
        int endX = (int) (size.width * 0.1);
        int centerY = size.height / 2;
        swipe(startX, centerY, endX, centerY, 500);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public WebElement findCarouselItem3(int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement carouselItem3 = null;
        WebElement carouselItem4 = null;

        while (carouselItem3 == null) {
            try {
                carouselItem4 = driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"__CAROUSEL_ITEM_4_READY__\"]"));
                wait.until(ExpectedConditions.visibilityOf(carouselItem4)); // Wait for item 4 to be visible
                carouselItem3 = driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"__CAROUSEL_ITEM_3_READY__\"]")); // Try to find item 3

                break; // Exit the loop if both are found
            } catch (org.openqa.selenium.NoSuchElementException e) {
                // Element not found, swipe and try again
                swipeRightToLeft();
            } catch (org.openqa.selenium.TimeoutException e) {
                // Element found but not visible within timeout
                System.out.println("Carousel item 4 found but not visible within timeout.");
                break;
            }
        }

        return carouselItem3; // Return item 3 (even if null)
    }

    public void swipeUntilSupportVideosVisible(int timeoutSeconds) {
        findCarouselItem3(timeoutSeconds); // Ensure carousel item 3 is found (or attempted to be found)

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        while (!isElementVisible(supportVideosCard)) {
            swipeRightToLeft();
            try {
                wait.until(ExpectedConditions.visibilityOf(supportVideosCard));
                break;
            } catch (org.openqa.selenium.TimeoutException e) {
                // Continue swiping
            }
        }
    }}