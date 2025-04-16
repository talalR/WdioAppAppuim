package pages;
// MobileAutomationFramework/src/main/java/com/example/pages/FormsPage.java

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FormsPage extends BasePage {

    @AndroidFindBy(accessibility = "text-input")
    private WebElement inputTextField;

    @AndroidFindBy(accessibility = "input-text-result")
    private WebElement inputTextResult;

    @AndroidFindBy(accessibility = "switch")
    private WebElement switchElement;

    @AndroidFindBy(accessibility = "switch-text")
    private WebElement switchText;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"text_input\"]")
    private WebElement dropdown;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView")
    private List<WebElement> dropDownItems; // Changed to a List


    @AndroidFindBy(accessibility = "button-Active")
    private WebElement activeButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"button-Inactive\"]/android.view.ViewGroup")
    private WebElement inActiveButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/alertTitle\"]")
    private WebElement alertDialogTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/message\"]")
    private WebElement alertDialogMessage;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
    private WebElement alertDialogOkButton;

    public FormsPage() {
        super();
    }

    public void enterText(String text) {
        sendKeys(inputTextField, text);
    }
    public String getEnteredText() {
        return getText(inputTextResult);
    }
    public void toggleSwitch() {
        click(switchElement);
    }
    public String getSwitchText() {
        return getText(switchText);
    }
    public void openDropdown() {
        click(dropdown);
    }
    public List<String> getDropDownItemsText() {
        List<String> itemsText = new ArrayList<>();
        for (WebElement item : dropDownItems) {
            itemsText.add(getText(item));
        }
        return itemsText;
    }
    public void selectDropdownItemByText(String text) {
        for (WebElement item : dropDownItems) {
            if (getText(item).equalsIgnoreCase(text)) {
                click(item);
                return; // Exit after selecting the item
            }
        }
        throw new IllegalArgumentException("Dropdown item with text '" + text + "' not found");
    }
    public String getDropdownFieldText() {
        return getText(dropdown);
    }
    public void clickActiveButton() {
        click(activeButton);
    }
    public String getAlertDialogTitle() {
        waitForVisibility(alertDialogTitle);
        return getText(alertDialogTitle);
    }
    public String getAlertDialogMessage() {
        waitForVisibility(alertDialogMessage);
        return getText(alertDialogMessage);
    }
    public void clickAlertDialogOkButton() {
        click(alertDialogOkButton);
    }

    public boolean isInactiveButtonEnabled() {
        return inActiveButton.isEnabled();
    }
    public boolean isInactiveButtonDisplayed() {
        return isElementVisible(inActiveButton);
    }
}