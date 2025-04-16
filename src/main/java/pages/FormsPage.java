package pages;
import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;
public class FormsPage extends BasePage { // Defines the FormsPage class, extending BasePage.

    @AndroidFindBy(accessibility = "text-input") // Locates the text input field using accessibility ID.
    private WebElement inputTextField; // Declares a WebElement for the text input field.

    @AndroidFindBy(accessibility = "input-text-result") // Locates the element displaying the input text result.
    private WebElement inputTextResult; // Declares a WebElement for the input text result.

    @AndroidFindBy(accessibility = "switch") // Locates the switch element.
    private WebElement switchElement; // Declares a WebElement for the switch.

    @AndroidFindBy(accessibility = "switch-text") // Locates the element displaying the switch text.
    private WebElement switchText; // Declares a WebElement for the switch text.

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"text_input\"]") // Locates the dropdown (which is likely an EditText) using XPath and resource ID.
    private WebElement dropdown; // Declares a WebElement for the dropdown.

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView") // Locates all checked text views within the dropdown (likely the options).
    private List<WebElement> dropDownItems; // Declares a List of WebElements for the dropdown items.

    @AndroidFindBy(accessibility = "button-Active") // Locates the active button.
    private WebElement activeButton; // Declares a WebElement for the active button.

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"button-Inactive\"]/android.view.ViewGroup") // Locates the inactive button using XPath and content-desc of the parent.
    private WebElement inActiveButton; // Declares a WebElement for the inactive button.

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/alertTitle\"]") // Locates the alert dialog title.
    private WebElement alertDialogTitle; // Declares a WebElement for the alert dialog title.

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/message\"]") // Locates the alert dialog message.
    private WebElement alertDialogMessage; // Declares a WebElement for the alert dialog message.

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']") // Locates the OK button in the alert dialog.
    private WebElement alertDialogOkButton; // Declares a WebElement for the alert dialog OK button.

    public FormsPage() { // Defines the constructor for the FormsPage class.
        super(); // Calls the constructor of the BasePage class.
    }

    public void enterText(String text) { // Defines a method to enter text into the input field.
        sendKeys(inputTextField, text); // Calls the sendKeys method (likely from BasePage) to send text to the input field.
    }

    public String getEnteredText() { // Defines a method to get the entered text.
        return getText(inputTextResult); // Calls the getText method (likely from BasePage) to retrieve the text.
    }

    public void toggleSwitch() { // Defines a method to toggle the switch.
        click(switchElement); // Calls the click method (likely from BasePage) to click the switch.
    }

    public String getSwitchText() { // Defines a method to get the switch text.
        return getText(switchText); // Calls the getText method (likely from BasePage) to retrieve the switch text.
    }

    public void openDropdown() { // Defines a method to open the dropdown.
        click(dropdown); // Calls the click method (likely from BasePage) to click the dropdown.
    }

    public List<String> getDropDownItemsText() { // Defines a method to get the text of all dropdown items.
        List<String> itemsText = new ArrayList<>(); // Creates a new ArrayList to store the text of the items.
        for (WebElement item : dropDownItems) { // Iterates through each WebElement in the dropDownItems list.
            itemsText.add(getText(item)); // Gets the text of the current item and adds it to the itemsText list.
        }
        return itemsText; // Returns the list of item texts.
    }

    public void selectDropdownItemByText(String text) { // Defines a method to select a dropdown item by its text.
        for (WebElement item : dropDownItems) { // Iterates through each WebElement in the dropDownItems list.
            if (getText(item).equalsIgnoreCase(text)) { // Checks if the text of the current item is equal to the provided text (case-insensitive).
                click(item); // Clicks the item if the text matches.
                return; // Exits the method after selecting the item.
            }
        }
        throw new IllegalArgumentException("Dropdown item with text '" + text + "' not found"); // Throws an exception if no matching item is found.
    }

    public String getDropdownFieldText() { // Defines a method to get the text of the dropdown field.
        return getText(dropdown); // Calls the getText method (likely from BasePage) to retrieve the dropdown field text.
    }

    public void clickActiveButton() { // Defines a method to click the active button.
        click(activeButton); // Calls the click method (likely from BasePage) to click the active button.
    }

    public String getAlertDialogTitle() { // Defines a method to get the title of the alert dialog.
        waitForVisibility(alertDialogTitle); // Calls the waitForVisibility method (likely from BasePage) to wait for the title to be visible.
        return getText(alertDialogTitle); // Returns the text of the alert dialog title.
    }

    public String getAlertDialogMessage() { // Defines a method to get the message of the alert dialog.
        waitForVisibility(alertDialogMessage); // Calls the waitForVisibility method (likely from BasePage) to wait for the message to be visible.
        return getText(alertDialogMessage); // Returns the text of the alert dialog message.
    }

    public void clickAlertDialogOkButton() { // Defines a method to click the OK button in the alert dialog.
        click(alertDialogOkButton); // Calls the click method (likely from BasePage) to click the OK button.
    }

    public boolean isInactiveButtonEnabled() { // Defines a method to check if the inactive button is enabled.
        return inActiveButton.isEnabled(); // Returns true if the inactive button is enabled, false otherwise.
    }

    public boolean isInactiveButtonDisplayed() { // Defines a method to check if the inactive button is displayed.
        return isElementVisible(inActiveButton); // Calls the isElementVisible method (likely from BasePage) to check if the button is displayed.
    }
}