package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {

    // Locator
    private final By txtUsername = By.xpath("//input[@id='user-name']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By btnLogin = By.xpath("//input[@id='login-button']");
    private final By divLogo = By.xpath("//div[@class='login_logo']");
    private final By imgMascot = By.xpath("//img[@class='bot_column']");
    private final By lblError = By.xpath("//h3[@data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void navigateToLoginPage() {
        String url = "https://www.saucedemo.com/v1/index.html";
        navigateToPage(url);
    }

    public void verifyPageLoaded() {
        verifyElementsAreVisible(txtPassword, txtUsername, divLogo, btnLogin, imgMascot);
    }

    public void verifyErrorMessage(String message){
        find(lblError).isDisplayed();
        String errorMessage = find(lblError).getText();
        Assert.assertTrue(errorMessage.contains(message));
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

    public void login(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
    }
}
