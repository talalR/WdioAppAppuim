package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage { // Defines the LoginPage class, extending BasePage.

    @AndroidFindBy(accessibility = "input-email")
    private WebElement emailTextField; // Declares a WebElement for the email text field, located by accessibility ID.

    @AndroidFindBy(accessibility = "input-password")
    private WebElement passwordTextField; // Declares a WebElement for the password text field, located by accessibility ID.

    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement loginButton; // Declares a WebElement for the login button, located by accessibility ID.

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'You are logged in!')]")
    private WebElement loginSuccessMessage; // Declares a WebElement for the login success message, located by XPath.

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='button-sign-up-container']/android.view.ViewGroup")
    private WebElement signUpTab; // Declares a WebElement for the sign-up tab, located by a complex XPath.

    public LoginPage() {
        super(); // Calls the constructor of the BasePage class.
    }

    public void enterEmail(String email) {
        sendKeys(emailTextField, email); // Enters the provided email into the email text field.
    }

    public void enterPassword(String password) {
        sendKeys(passwordTextField, password); // Enters the provided password into the password text field.
    }

    public void clickLoginButton() {
        click(loginButton); // Clicks the login button.
    }

    public boolean isLoginSuccessful() {
        try {
            waitForVisibility(loginSuccessMessage); // Waits for the login success message to be visible.
            return loginSuccessMessage.isDisplayed(); // Returns true if the login success message is displayed, false otherwise.
        } catch (Exception e) {
            return false; // Returns false if any exception occurs (e.g., message not found).
        }
    }

    public void clickSignUpTab() {
        click(signUpTab); // Clicks the sign-up tab.
    }
}