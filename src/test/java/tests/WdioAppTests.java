package tests;

import base.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*; // Imports all classes from the 'pages' package
import utils.JsonDataReader; // Imports the JsonDataReader utility class

import java.util.List; // Imports the List interface
import java.util.Map; // Imports the Map interface
import java.util.Random; // Imports the Random class

public class WdioAppTests {

    private LoginPage loginPage; // Declares a LoginPage object
    private SignUpPage signUpPage; // Declares a SignUpPage object
    private FormsPage formsPage; // Declares a FormsPage object
    private SwipePage swipePage; // Declares a SwipePage object
    private HomePage homePage; // Declares a HomePage object

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(); // Initializes the LoginPage object
        signUpPage = new SignUpPage(); // Initializes the SignUpPage object
        formsPage = new FormsPage(); // Initializes the FormsPage object
        swipePage = new SwipePage(); // Initializes the SwipePage object
        homePage = new HomePage(); // Initializes the HomePage object
    }

    @Test
    public void testClass() throws InterruptedException {
        // This test method is currently empty. It's a placeholder for potential future test logic.
    }

    @Test(priority = 1)
    public void signUpTest() throws InterruptedException {
        homePage.isHomePageDisplayed();
        homePage.clickLoginTab(); // Clicks the Login tab on the HomePage
        loginPage.clickSignUpTab(); // Clicks the Sign Up tab on the LoginPage
        Thread.sleep(500); // Waits for 500 milliseconds (0.5 seconds).  **Note:** Using Thread.sleep is generally discouraged in favor of explicit waits.
        Map<String, String> signUpData = JsonDataReader.getTestData("SignUpData", "SignUpValid"); // Retrieves sign-up data from the JsonDataReader
        Assert.assertNotNull(signUpData, "Sign Up data not found."); // Asserts that the sign-up data is not null

        signUpPage.enterEmail(signUpData.get("Email")); // Enters the email in the SignUpPage
        signUpPage.enterPassword(signUpData.get("Password")); // Enters the password in the SignUpPage
        signUpPage.enterRepeatPassword(signUpData.get("Password")); // Enters the repeated password in the SignUpPage
        signUpPage.clickSignUpButton(); // Clicks the Sign Up button
        Assert.assertTrue(signUpPage.isSignUpSuccessful(), "Sign up failed."); // Asserts that the sign-up was successful
    }

    @Test(priority = 2, dependsOnMethods = "signUpTest")
    public void loginTest() {
        homePage.clickLoginTab(); // Clicks the Login tab on the HomePage

        Map<String, String> loginData = JsonDataReader.getTestData("LoginData", "LoginValid"); // Retrieves login data from the JsonDataReader
        Assert.assertNotNull(loginData, "Login data not found."); // Asserts that the login data is not null

        loginPage.enterEmail(loginData.get("Username")); // Enters the username in the LoginPage
        loginPage.enterPassword(loginData.get("Password")); // Enters the password in the LoginPage
        loginPage.clickLoginButton(); // Clicks the Login button
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed."); // Asserts that the login was successful
    }

    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void formsTest() throws InterruptedException {
        homePage.clickFormsTab(); // Clicks the Forms tab on the HomePage

        // Section 1: Input Field
        Map<String, String> formsData1 = JsonDataReader.getTestData("FormsData", "FormsData1"); // Retrieves forms data from the JsonDataReader
        Assert.assertNotNull(formsData1, "Forms data 1 not found."); // Asserts that the forms data is not null
        formsPage.enterText(formsData1.get("Some Text")); // Enters text in the input field
        Assert.assertEquals(formsPage.getEnteredText(), formsData1.get("Some Text"), "Input text assertion failed."); // Asserts that the entered text matches the expected text

        // Section 2: Switch
        String initialSwitchState = formsPage.getSwitchText(); // Gets the initial state of the switch
        formsPage.toggleSwitch(); // Toggles the switch
        String newSwitchState = formsPage.getSwitchText(); // Gets the new state of the switch
        Assert.assertNotEquals(initialSwitchState, newSwitchState, "Switch toggle failed: State did not change."); // Asserts that the switch state has changed
        Assert.assertEquals(newSwitchState.equalsIgnoreCase(formsData1.get("switchNewStatus")), true, // Asserts that the new switch state matches the expected new status (case-insensitive)
                "Switch state does not match expected new status: " + formsData1.get("switchNewStatus"));

        // Section 3: Dropdown
        formsPage.openDropdown(); // Opens the dropdown
        List<String> items = formsPage.getDropDownItemsText(); // Gets all items from the dropdown
        Random random = new Random(); // Creates a Random object
        int randomIndex = random.nextInt(items.size()); // Generates a random index within the size of the items list

        // Get the random item
        String randomItem = items.get(randomIndex); // Gets the item at the random index
        formsPage.selectDropdownItemByText(randomItem); // Selects the random item
        Assert.assertEquals(formsPage.getDropdownFieldText(), randomItem, // Asserts that the selected dropdown field text matches the randomly selected item
                "The selected dropdown item doesn't match the selected dropdown item.");

        // Section 4: Active Button
        formsPage.clickActiveButton(); // Clicks the Active button
        Assert.assertEquals(formsPage.getAlertDialogTitle(), formsData1.get("AlertDialogTitle"), // Asserts that the alert dialog title matches the expected title
                "Alert dialog title does not match expected title: " + formsData1.get("AlertDialogTitle"));
        Assert.assertEquals(formsPage.getAlertDialogMessage(), formsData1.get("AlertDialogMsg"), // Asserts that the alert dialog message matches the expected message
                "Alert dialog message does not match expected message: " + formsData1.get("AlertDialogMsg"));
        formsPage.clickAlertDialogOkButton(); // Clicks the OK button on the alert dialog

        // Section 5: inActive Button
        System.out.println(formsPage.isInactiveButtonDisplayed()); // Prints whether the inactive button is displayed
        System.out.println(formsPage.isInactiveButtonEnabled()); // Prints whether the inactive button is enabled

        Assert.assertTrue(formsPage.isInactiveButtonDisplayed(), "Inactive button is not displayed."); // Asserts that the inactive button is displayed
        Assert.assertTrue(formsPage.isInactiveButtonDisplayed(), "Inactive button is Displayed."); // Asserts that the inactive button is displayed (duplicate assertion)
    }

    @Test(priority = 4, dependsOnMethods = "formsTest")
    public void swipeTest() {
        homePage.clickSwipeTab(); // Clicks the Swipe tab on the HomePage

        swipePage.swipeUntilSupportVideosVisible(5); // Swipes until the support videos are visible (with a 5-second timeout)
        Assert.assertTrue(swipePage.isElementVisible(swipePage.supportVideosCard), "Support Videos card not found after swiping."); // Asserts that the support videos card is visible
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver(); // Quits the Appium driver after each test method
    }
}