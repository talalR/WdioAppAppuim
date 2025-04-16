package pages;
// MobileAutomationFramework/src/main/java/com/example/pages/SignUpPage.java

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends BasePage {

    @AndroidFindBy(accessibility = "input-email")
    private WebElement emailTextField;

    @AndroidFindBy(accessibility = "input-password")
    private WebElement passwordTextField;

    @AndroidFindBy(accessibility = "input-repeat-password")
    private WebElement repeatPasswordTextField;

    @AndroidFindBy(accessibility = "button-SIGN UP")
    private WebElement signUpButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'You successfully signed up!')]")
    private WebElement signUpSuccessMessage;

    public SignUpPage() {
        super();
    }

    public void enterEmail(String email) {
        sendKeys(emailTextField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordTextField, password);
    }

    public void enterRepeatPassword(String repeatPassword) {
        sendKeys(repeatPasswordTextField, repeatPassword);
    }

    public void clickSignUpButton() {
        click(signUpButton);
    }

    public boolean isSignUpSuccessful() {
        try {
            waitForVisibility(signUpSuccessMessage);
            return signUpSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}