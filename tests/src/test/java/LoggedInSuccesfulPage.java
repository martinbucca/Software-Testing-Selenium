
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedInSuccesfulPage extends BasePage {
    private By postTitleLocator = By.className("post-title");
    private By logoutButtonLocator = By.xpath("//a[contains(@class,'wp-block-button__link')]");

    public LoggedInSuccesfulPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        return waitAndReturnElement(postTitleLocator).getText();
    }

    public LoginPage clickLogout() {
        WebElement logoutButton = waitAndReturnElement(logoutButtonLocator);
        logoutButton.click();
        return new LoginPage(driver);
    }


}