import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import org.junit.*;
import java.util.List;

import org.json.simple.JSONObject;



public class FirstSeleniumTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactPage contactPage;

    @Before
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginWithValidCredentials() {
        loginPage = new LoginPage(driver);

        loginPage.enterUsername("student");
        loginPage.enterPassword("Password123");
        LoggedInSuccesfulPage loggedInSuccesfulPage = loginPage.clickSubmit();

        String welcomeMessage = loggedInSuccesfulPage.getWelcomeMessage();
        assertTrue("Expected welcome message to contain 'Logged In Successfully' but was: " + welcomeMessage, welcomeMessage.contains("Logged In Successfully"));

    }

    @Test
    public void testLogout() {
        loginPage = new LoginPage(driver);

        loginPage.enterUsername("student");
        loginPage.enterPassword("Password123");
        LoggedInSuccesfulPage loggedInSuccesfulPage = loginPage.clickSubmit();

        LoginPage returnedLoginPage = loggedInSuccesfulPage.clickLogout();

        String pageTitle = returnedLoginPage.getPageTitle();
        assertEquals("Page title after logout should match login page title", "Test Login | Practice Test Automation", pageTitle);
    }

    @Test
    public void testSendNewsletterForm() {
        homePage = new HomePage(driver);

        homePage.enterName("John Doe");
        homePage.enterEmail("johndoe@gmail.com");
        CaptchaPage captchaPage = homePage.clickSubmit();

        String captchaText = captchaPage.getCaptchaMessage();
        // TODO: Check message for the captcha ("...you're...'")
        assertTrue("Expected captcha text to contain 'Captcha' but was: " + captchaText,
                captchaText.contains("not a robot"));

    }

    @Test
    public void testHomePageIsOpenedCorrectly() {
        homePage = new HomePage(driver);
        String pageTitle = homePage.getPageTitle();
        assertEquals("Page title should match the expected title", "Practice Test Automation | Learn Selenium WebDriver", pageTitle);
    }

    @Test
    public void testContactForm() {
        contactPage = new ContactPage(driver);

        contactPage.enterFirstName("John");
        contactPage.enterLastName("Doe");
        contactPage.enterEmail("johndoe@gmail.com");
        contactPage.enterMessage("This is a test message.");

        contactPage.submitForm();

        contactPage.waitForCaptchaErrorMessage();
        contactPage.waitForCaptchaErrorLabel();

        String errorMessage = contactPage.getCaptchaErrorMessage();
        String errorLabel = contactPage.getCaptchaErrorLabel();

        assertTrue("Expected error message to contain 'Captcha' but was: " + errorMessage,
                errorMessage.contains("Form has not been submitted, please see the errors below."));
        assertTrue("Expected error label to contain 'Captcha' but was: " + errorLabel,
                errorLabel.contains("Google reCAPTCHA verification failed, please try again later."));
    }

    @Test
    public void testStaticPagesFromConfig() {
        String baseUrl = ConfigReader.getBaseUrl();
        List<JSONObject> pages = ConfigReader.getPagesToTest();

        for (JSONObject page : pages) {
            String pageName = (String) page.get("name");
            String path = (String) page.get("path");
            String expectedTitle = (String) page.get("expectedTitle");
            String pageUrl = baseUrl + path;

            driver.get(pageUrl);

            String actualTitle = driver.getTitle();

            assertEquals(
                    "Page title mismatch for " + pageName + " (" + pageUrl + ")",
                    expectedTitle,
                    actualTitle
            );
        }
    }





    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}