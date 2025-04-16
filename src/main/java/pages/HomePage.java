package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidFindBy(accessibility = "Home")
    private WebElement homeTab; // Declares a WebElement for the Home tab, located by accessibility ID.

    @AndroidFindBy(accessibility = "Webview")
    private WebElement webViewTab; // Declares a WebElement for the Webview tab, located by accessibility ID.

    @AndroidFindBy(accessibility = "Login")
    private WebElement loginTab; // Declares a WebElement for the Login tab, located by accessibility ID.

    @AndroidFindBy(accessibility = "Forms")
    private WebElement formsTab; // Declares a WebElement for the Forms tab, located by accessibility ID.

    @AndroidFindBy(accessibility = "Swipe")
    private WebElement swipeTab; // Declares a WebElement for the Swipe tab, located by accessibility ID.

    @AndroidFindBy(accessibility = "Drag")
    private WebElement dragTab; // Declares a WebElement for the Drag tab, located by accessibility ID.

    public HomePage() {
        super(); // Calls the constructor of the BasePage class.
    }

    public void clickLoginTab() {
        click(loginTab); // Clicks the Login tab.
    }

    public void clickFormsTab() {
        click(formsTab); // Clicks the Forms tab.
    }

    public void clickSwipeTab() {
        click(swipeTab); // Clicks the Swipe tab.
    }

    public boolean isHomePageDisplayed() {
        return isElementVisible(homeTab); // Checks if the Home tab (or another reliable element) is visible to determine if the Home page is displayed.
    }
}