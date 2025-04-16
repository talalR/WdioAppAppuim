package pages;


import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Collections;

public class SwipePage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SUPPORT VIDEOS']")
    public WebElement supportVideosCard;

    public SwipePage() {
        super();
    }

    public void swipe(int startX, int startY, int endX, int endY, int durationMs) {
        PointerInput finger = new PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, org.openqa.selenium.interactions.PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMs), org.openqa.selenium.interactions.PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(0));
        ((AndroidDriver) driver).perform(Collections.singletonList(swipe));
    }



    public void swipeRightToLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.9);
        int endX = (int) (size.width * 0.1);
        int centerY = size.height / 2;
        swipe(startX, centerY, endX, centerY, 500); // Adjust duration as needed
    }

    public void swipeUntilSupportVideosVisible() {
        while (!isElementVisible(supportVideosCard)) {
            swipeRightToLeft(); // Or swipeRightToLeft depending on the initial screen
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}