package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SignUpPage extends BasePage { // Defines the SignUpPage class, extending BasePage.

    @AndroidFindBy(accessibility = "input-email")
    private WebElement emailTextField; // Declares a WebElement for the email text field, located by accessibility ID.

    @AndroidFindBy(accessibility = "input-password")
    private WebElement passwordTextField; // Declares a WebElement for the password text field, located by accessibility ID.

    @AndroidFindBy(accessibility = "input-repeat-password")
    private WebElement repeatPasswordTextField; // Declares a WebElement for the repeat password text field, located by accessibility ID.

    @AndroidFindBy(accessibility = "button-SIGN UP")
    private WebElement signUpButton; // Declares a WebElement for the sign-up button, located by accessibility ID.

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'You successfully signed up!')]")
    private WebElement signUpSuccessMessage; // Declares a WebElement for the sign-up success message, located by XPath.

    public SignUpPage() { // Defines the constructor for the SignUpPage class.
        super(); // Calls the constructor of the BasePage class.
    }

    public void enterEmail(String email) { // Defines a method to enter the email.
        sendKeys(emailTextField, email); // Calls the sendKeys method (likely from BasePage) to enter the email into the email text field.
    }

    public void enterPassword(String password) { // Defines a method to enter the password.
        sendKeys(passwordTextField, password); // Calls the sendKeys method (likely from BasePage) to enter the password into the password text field.
    }

    public void enterRepeatPassword(String repeatPassword) { // Defines a method to enter the repeated password.
        sendKeys(repeatPasswordTextField, repeatPassword); // Calls the sendKeys method (likely from BasePage) to enter the repeated password into the repeat password text field.
    }

    public void clickSignUpButton() { // Defines a method to click the sign-up button.
        click(signUpButton); // Calls the click method (likely from BasePage) to click the sign-up button.
    }

    public boolean isSignUpSuccessful() { // Defines a method to check if the sign-up was successful.
        try {
            waitForVisibility(signUpSuccessMessage); // Calls the waitForVisibility method (likely from BasePage) to wait for the success message to be visible.
            return signUpSuccessMessage.isDisplayed(); // Returns true if the success message is displayed, false otherwise.
        } catch (Exception e) {
            return false; // Returns false if any exception occurs (e.g., message not found).
        }
    }
}