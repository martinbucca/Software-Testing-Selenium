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


public class FirstSeleniumTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

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

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}