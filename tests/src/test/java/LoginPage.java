import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("submit");
    private By errorMessageLocator = By.xpath("//*[@id=\"error\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    public void enterUsername(String username) {
        WebElement usernameInputElement = waitAndReturnElement(usernameLocator);
        usernameInputElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInputElement = waitAndReturnElement(passwordLocator);
        passwordInputElement.sendKeys(password);
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = waitAndReturnElement(errorMessageLocator);
        String errorMessage = errorMessageElement.getText();
        return errorMessage;
    }

    public LoggedInSuccesfulPage clickSubmit() {
        WebElement loginButton = waitAndReturnElement(loginButtonLocator);
        loginButton.click();
        return new LoggedInSuccesfulPage(driver);
    }


}