package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

    // Locator
    private By txtUsername = By.xpath("//input[@id='user-name']");
    private By txtPassword = By.xpath("//input[@id='password']");
    private By btnLogin = By.xpath("//input[@id='login-button']");
    private By divLogo = By.xpath("//div[@class='login_logo']");
    private By imgMascot = By.xpath("//img[@class='bot_column']");
    private By lblError = By.xpath("//h3[@data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void verifyPageLoaded() {
        verifyElementsAreVisible(txtPassword, txtUsername, divLogo, btnLogin, imgMascot);
    }
    public void verifyErrorMessage(String message){
        find(lblError).isDisplayed();
        String ErrorMessage = find(lblError).getText();
        Assert.assertTrue(ErrorMessage.contains(message));
    }

    public void fillUsername(String keyword) {
        type(txtUsername, keyword);
    }

    public void fillPassword(String keyword) {
        type(txtPassword, keyword);
    }

    public void clickLoginButton() {
        click(btnLogin);
    }
}
