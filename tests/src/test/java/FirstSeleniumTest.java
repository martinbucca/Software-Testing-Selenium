import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;

import java.net.URL;
import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;


public class FirstSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("submit");
    private By postTitle = By.className("post-title");

    @Before
    public void Setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 10);
    }

    private WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void testLoginWithValidCredentials() {
        this.driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputElement = waitAndReturnElement(usernameLocator);
        usernameInputElement.sendKeys("student");

        WebElement passwordInputElement = waitAndReturnElement(passwordLocator);
        passwordInputElement.sendKeys("Password123");

        WebElement loginButton = this.driver.findElement(loginButtonLocator);
        loginButton.click();

        WebElement postTitleMessage = waitAndReturnElement(postTitle);

        assertTrue(postTitleMessage.getText().contains("Logged In Successfully"));

        //WebElement logoutButton = waitAndReturnElement(logoutButtonLocator);
        //logoutButton.click();

    }

    /*
    @Test
    public void testLoginWithInvalidUsername() {
        this.driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputElement = waitAndReturnElement(usernameLocator);
        usernameInputElement.sendKeys("martinbucca");

        WebElement passwordInputElement = waitAndReturnElement(passwordLocator);
        passwordInputElement.sendKeys("Password123");

        WebElement loginButton = this.driver.findElement(loginButtonLocator);
        loginButton.click();


        WebElement flashMessageElement = waitAndReturnElement(flashMessageLocator);
        assertTrue(flashMessageElement.getText().contains("Your username is invalid!"));

    }

    @Test
    public void testLoginWithInvalidPassword() {
        this.driver.get("http://the-internet.herokuapp.com/login");

        WebElement usernameInputElement = waitAndReturnElement(usernameLocator);
        usernameInputElement.sendKeys("tomsmith");

        WebElement passwordInputElement = waitAndReturnElement(passwordLocator);
        passwordInputElement.sendKeys("invalidpassword");

        WebElement loginButton = this.driver.findElement(loginButtonLocator);
        loginButton.click();

        WebElement flashMessageElement = waitAndReturnElement(flashMessageLocator);
        assertTrue(flashMessageElement.getText().contains("Your password is invalid!"));
    }
    */


    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

}
