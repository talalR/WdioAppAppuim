# Mobile Automation Framework for Android

This framework automates interactions with Android applications using Appium-Java and the Page Object Model. It's designed to be robust and maintainable, with a focus on clear code organization and data-driven testing.

## Prerequisites

Before setting up the project, ensure you have the following installed and configured:

1.  **Java Development Kit (JDK)**: JDK 8 or later is required. Verify your installation by opening a terminal or command prompt and running `java -version`.
2.  **Android Studio and SDK**: Install Android Studio to obtain the Android SDK, which includes necessary tools like `adb` (Android Debug Bridge) and emulators.
3.  **Appium Server**: You'll need the Appium server to communicate with Android devices/emulators. You can install it via npm (Node Package Manager) or use Appium Desktop.
4.  **Node.js and npm**: If you choose to install Appium via npm, you'll need Node.js and npm.
5.  **Maven**: This project uses Maven for dependency management and building. Download and install Maven. Verify by running `mvn -v`.
6.  **Android Device/Emulator**:
    * An Android emulator (created using Android Studio's AVD Manager) or a physical Android device connected to your computer.
    * For physical devices, enable USB debugging in the device's developer options.
7.  **wdiodemoapp.apk**: The Android application package file (`.apk`) for the application you want to test. Place this file in a convenient location.https://drive.google.com/file/d/1Gqs1eEZw7Wtmb4A4NyHFMXLs_xRhkoqP/view


## Project Structure
MobileAutomationFramework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           ├── base/
│   │           │   ├── BasePage.java       // Base class for Page Objects
│   │           │   └── DriverManager.java  // Manages Appium driver
│   │           ├── pages/
│   │           │   ├── FormsPage.java      // Page Object for Forms screen
│   │           │   ├── HomePage.java       // Page Object for Home screen
│   │           │   ├── LoginPage.java      // Page Object for Login screen
│   │           │   ├── SignUpPage.java     // Page Object for Sign Up screen
│   │           │   └── SwipePage.java      // Page Object for Swipe screen
│   │           └── utils/
│   │               ├── ConfigReader.java   // Reads configuration from properties file
│   │               └── JsonDataReader.java // Reads test data from JSON file
│   │   └── resources/
│   │       └── config.properties   // Configuration file for Appium
│   └── test/
│       └── java/
│           └── com/example/
│               └── tests/
│                   └── WdioAppTests.java   // Test classes
├── data/
│   └── test_data.json      // JSON file containing test data
├── README.md               // This file (instructions)
├── pom.xml                 // Maven project file
└── testng.xml             // TestNG suite configuration (optional)




## Setup Instructions

1.  **Clone the Repository:**
    ```bash
    git clone <repository_url>
    cd MobileAutomationFramework
    ```
    (Replace `<repository_url>` with the actual URL of your repository.)

2.  **Configure `config.properties`:**
    * Navigate to `src/main/resources/config.properties`.
    * Update the following properties:
        * `appium.server.url`: The URL of your Appium server (e.g., `http://127.0.0.1:4723/`).
        * `platformName`: Must be `Android`.
        * `deviceName`: The name of your connected device or emulator (find it using `adb devices`).
        * `appPackage`: The package name of your application (e.g., `com.wdiodemoapp`).
        * `appActivity`: The main activity of your application (e.g., `com.wdiodemoapp.MainActivity`).
        * `automationName`: (Optional, but recommended) `UiAutomator2`.
        * `platformVersion`: (Optional) The Android version of your device/emulator.

    Example `config.properties`:

    ```properties
    appium.server.url=[http://127.0.0.1:4723/](http://127.0.0.1:4723/)
    platformName=Android
    deviceName=emulator-5554
    appPackage=com.wdiodemoapp
    appActivity=com.wdiodemoapp.MainActivity
    automationName=UiAutomator2
    platformVersion=14
    ```

3.  **Place `test_data.json`:**
    * Ensure the `test_data.json` file is in the `data` directory.
    * The structure of this file should match the data sets used in your tests.

4.  **Install App (Optional):**
    * If you don't install the app manually, you can add the `app` capability to `config.properties`:
        * `app=/path/to/your/wdiodemoapp.apk`
    * Replace `/path/to/your/wdiodemoapp.apk` with the actual path to your APK file.
    * Alternatively, you can manually install the APK on your device/emulator using `adb install /path/to/your/wdiodemoapp.apk`.

## Running the Tests

1.  **Start Appium Server:**
    * Start the Appium server using your preferred method (Appium CLI or Appium Desktop).
    * If using the CLI, open a terminal or command prompt and type `appium`. Keep this window open.

2.  **Open a Terminal/Command Prompt:**
    * Navigate to the root directory of the project (`MobileAutomationFramework`).

3.  **Run Tests with Maven:**
    * Execute the following Maven command:
        ```bash
        mvn clean test
        ```
    * This will:
        * `clean`: Remove any previous build artifacts.
        * `test`: Compile the code and run the TestNG test suite.

4.  **View Results:**
    * Test results will be displayed in the console.
    * Detailed reports will be generated in the `target/surefire-reports` directory.

## TestNG Configuration (Optional)

* If you want to run specific tests or configure test execution, you can use the `testng.xml` file.
* You can run this file from your IDE or using Maven:
    * `mvn clean test -Dsurefire.suiteXmlFiles=testng.xml`

## Important Notes

* **Appium Server:** The Appium server *must* be running before you execute the tests.
* **Device Connection:** Ensure your device/emulator is properly connected and recognized by `adb`.
* **Locators:** The locators (e.g., XPath, accessibility IDs) in the Page Objects might need adjustments based on your application's UI structure. Use Appium Inspector to inspect elements and find the correct locators.
* **Logging:** Consider adding a logging framework (e.g., Log4j) for more robust logging.
* **Waits:** Use explicit waits (WebDriverWait) to handle dynamic elements and avoid flakiness.

This README provides a comprehensive guide to setting up and running the mobile automation framework. Remember to adapt the configuration and locators to your specific application.



By The Master
