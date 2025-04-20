package tests;

import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class TS01_Login extends TestBase {

    @Test
    public void TS0101_LoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.verifyPageLoaded();
    }

    @Test
    public void TS0102_LoginFailed() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("random", "yhedujiks");
        loginPage.verifyErrorMessage("Username and password do not match any user in this service");
    }

}
