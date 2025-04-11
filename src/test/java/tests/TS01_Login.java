package tests;

import config.WebDriverConfig;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class TS01_Login extends TestBase {
    
    @Test
    public void TS0101_LoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        InventoryPage inventoryPage = loginPage.loginValidUser("standard_user", "secret_sauce");
        inventoryPage.verifyPageLoaded();
    }

    @Test
    public void TS0102_LoginFailed() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.loginInvalidUser("random", "yhedujiks");
        loginPage.verifyErrorMessage("Username and password do not match any user in this service");
    }

}
