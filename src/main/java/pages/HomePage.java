package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @AndroidFindBy(accessibility = "Home")
    private WebElement homeTab;

    @AndroidFindBy(accessibility = "Webview")
    private WebElement webViewTab;

    @AndroidFindBy(accessibility = "Login")
    private WebElement loginTab;

    @AndroidFindBy(accessibility = "Forms")
    private WebElement formsTab;

    @AndroidFindBy(accessibility = "Swipe")
    private WebElement swipeTab;

    @AndroidFindBy(accessibility = "Drag")
    private WebElement dragTab;

    public HomePage() {
        super();
    }


    public void clickLoginTab() {
        click(loginTab);
    }

    public void clickFormsTab() {
        click(formsTab);
    }

    public void clickSwipeTab() {
        click(swipeTab);
    }

    public boolean isHomePageDisplayed() {
        return isElementVisible(homeTab); // Or any other reliable element on the Home page
    }

}
