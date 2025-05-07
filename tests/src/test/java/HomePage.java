import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By formNameLocator = By.xpath("//*[@id=\"form_first_name_7\"]");
    private By formEmailLocator = By.xpath("//*[@id=\"form_email_7\"]");
    private By formSubmitLocator = By.xpath("//*[@id=\"mp_form_below_posts7\"]/form/div[3]/input");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://practicetestautomation.com/");
    }

    public void enterName(String name) {
        WebElement nameInputElement = waitAndReturnElement(formNameLocator);
        nameInputElement.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement emailInputElement = waitAndReturnElement(formEmailLocator);
        emailInputElement.sendKeys(email);
    }

    public CaptchaPage clickSubmit() {
        WebElement submitButton = waitAndReturnElement(formSubmitLocator);
        submitButton.click();
        return new CaptchaPage(driver);
    }



}