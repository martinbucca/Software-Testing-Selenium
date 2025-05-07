import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptchaPage extends BasePage {
    private By postTitleLocator = By.className("post-title");

    public CaptchaPage(WebDriver driver) {
        super(driver);
    }

    public String getCaptchaMessage() {
        return waitAndReturnElement(postTitleLocator).getText();
    }



}