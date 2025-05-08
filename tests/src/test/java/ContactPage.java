import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPage extends BasePage {

    private By formFirstNameLocator = By.xpath("//*[@id=\"wpforms-161-field_0\"]");
    private By formLastNameLocator = By.xpath("//*[@id=\"wpforms-161-field_0-last\"]");
    private By formEmailLocator = By.xpath("//*[@id=\"wpforms-161-field_1\"]");
    private By textAreaLocator = By.xpath("//*[@id=\"wpforms-161-field_2\"]");
    private By captchaLocator = By.xpath("//*[@id=\"wpforms-161-field_3\"]");
    private By formSubmitLocator = By.xpath("//*[@id=\"wpforms-submit-161\"]");

    private By captchaErrorMessageLocator = By.xpath("//*[@id=\"wpforms-form-161\"]/div[1]/p");
    private By captchaErrorLabelLocator = By.xpath("//*[@id=\"wpforms-field_recaptcha-error\"]");

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://practicetestautomation.com/contact/");
    }

    public void enterFirstName(String firstName) {
        WebElement nameInputElement = waitAndReturnElement(formFirstNameLocator);
        nameInputElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement nameInputElement = waitAndReturnElement(formLastNameLocator);
        nameInputElement.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailInputElement = waitAndReturnElement(formEmailLocator);
        emailInputElement.sendKeys(email);
    }

    public void enterMessage(String message) {
        WebElement messageInputElement = waitAndReturnElement(textAreaLocator);
        messageInputElement.sendKeys(message);
    }

    public void submitForm() {
        WebElement submitButton = waitAndReturnElement(formSubmitLocator);
        submitButton.click();
    }

    public void waitForCaptchaErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(captchaErrorMessageLocator));
    }

    public void waitForCaptchaErrorLabel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(captchaErrorLabelLocator));
    }

    public String getCaptchaErrorMessage() {
        WebElement errorMessageElement = waitAndReturnElement(captchaErrorMessageLocator);
        return errorMessageElement.getText();
    }

    public String getCaptchaErrorLabel() {
        WebElement errorLabelElement = waitAndReturnElement(captchaErrorLabelLocator);
        return errorLabelElement.getText();
    }

}


