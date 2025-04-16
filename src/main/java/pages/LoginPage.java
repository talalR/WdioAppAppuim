package pages;
// MobileAutomationFramework/src/main/java/com/example/pages/LoginPage.java

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "input-email")
    private WebElement emailTextField;

    @AndroidFindBy(accessibility = "input-password")
    private WebElement passwordTextField;

    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'You are logged in!')]")
    private WebElement loginSuccessMessage;


    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='button-sign-up-container']/android.view.ViewGroup")
    private WebElement signUpTab;

    public LoginPage() {
        super();
    }

    public void enterEmail(String email) {
        sendKeys(emailTextField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordTextField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public boolean isLoginSuccessful() {
        try {
            waitForVisibility(loginSuccessMessage);
            return loginSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignUpTab() {
        click(signUpTab);
    }
}