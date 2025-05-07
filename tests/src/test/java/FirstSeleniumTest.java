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

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}