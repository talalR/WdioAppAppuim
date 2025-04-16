package tests;

import base.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.JsonDataReader;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WdioAppTests {

    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private FormsPage formsPage;
    private SwipePage swipePage;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        formsPage = new FormsPage();
        swipePage = new SwipePage();
        homePage = new HomePage();
    }

    @Test
    public void testClass() throws InterruptedException{

        homePage.clickSwipeTab();


        swipePage.swipeUntilSupportVideosVisible();
        Assert.assertTrue(swipePage.isElementVisible(swipePage.supportVideosCard), "Support Videos card not found after swiping.");





    }


    @Test(priority = 1)
    public void signUpTest() throws InterruptedException {
        homePage.clickLoginTab();
        loginPage.clickSignUpTab();
        Thread.sleep(500);
        Map<String, String> signUpData = JsonDataReader.getTestData("SignUpData", "SignUpValid");
        Assert.assertNotNull(signUpData, "Sign Up data not found.");

        signUpPage.enterEmail(signUpData.get("Email"));
        signUpPage.enterPassword(signUpData.get("Password"));
        signUpPage.enterRepeatPassword(signUpData.get("Password")); // Assuming repeat password is the same
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isSignUpSuccessful(), "Sign up failed.");
    }

    @Test(priority = 2, dependsOnMethods = "signUpTest")
    public void loginTest() {
        homePage.clickLoginTab();


        Map<String, String> loginData = JsonDataReader.getTestData("LoginData", "LoginValid");
        Assert.assertNotNull(loginData, "Login data not found.");

        loginPage.enterEmail(loginData.get("Username"));
        loginPage.enterPassword(loginData.get("Password"));
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed.");
    }

    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void formsTest() throws InterruptedException {

        homePage.clickFormsTab();

        // Section 1: Input Field
        Map<String, String> formsData1 = JsonDataReader.getTestData("FormsData", "FormsData1");
        Assert.assertNotNull(formsData1, "Forms data 1 not found.");
        formsPage.enterText(formsData1.get("Some Text"));
        Assert.assertEquals(formsPage.getEnteredText(), formsData1.get("Some Text"), "Input text assertion failed.");

        // Section 2: Switch
        String initialSwitchState = formsPage.getSwitchText();
        formsPage.toggleSwitch();
        String newSwitchState = formsPage.getSwitchText();
        Assert.assertNotEquals(initialSwitchState, newSwitchState, "Switch toggle failed: State did not change.");
        Assert.assertEquals(newSwitchState.equalsIgnoreCase(formsData1.get("switchNewStatus")), true,
                "Switch state does not match expected new status: " + formsData1.get("switchNewStatus"));

        // Section 3: Dropdown
        formsPage.openDropdown();
        // Get all the items
        List<String> items = formsPage.getDropDownItemsText();
        // Get a random index
        Random random = new Random();
        int randomIndex = random.nextInt(items.size());

        // Get the random item
        String randomItem = items.get(randomIndex);
        formsPage.selectDropdownItemByText(randomItem);
        Assert.assertEquals(formsPage.getDropdownFieldText(), randomItem,
                "Selected dropdown item '" + formsPage.getDropdownFieldText() + "' does not match expected item: '" + randomItem + "'");

        // Section 4: Active Button
        formsPage.clickActiveButton();
        Assert.assertEquals(formsPage.getAlertDialogTitle(), formsData1.get("AlertDialogTitle"),
                "Alert dialog title does not match expected title: " + formsData1.get("AlertDialogTitle"));
        Assert.assertEquals(formsPage.getAlertDialogMessage(), formsData1.get("AlertDialogMsg"),
                "Alert dialog message does not match expected message: " + formsData1.get("AlertDialogMsg"));

        formsPage.clickAlertDialogOkButton();

        // Section 5: inActive Button

        Assert.assertTrue(formsPage.isInactiveButtonDisplayed(), "Inactive button is not displayed.");
        Assert.assertFalse(formsPage.isInactiveButtonEnabled(), "Inactive button is enabled.");



    }

    @Test(priority = 4, dependsOnMethods = "formsTest")
    public void swipeTest() {
        homePage.clickSwipeTab();


        swipePage.swipeUntilSupportVideosVisible();
        Assert.assertTrue(swipePage.isElementVisible(swipePage.supportVideosCard), "Support Videos card not found after swiping.");


    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}