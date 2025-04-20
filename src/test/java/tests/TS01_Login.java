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
    public void TS0102_LoginFailedInvalidUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("random", "yhedujiks");
        loginPage.verifyErrorMessage("Username and password do not match any user in this service");
    }

    @Test
    public void TS0103_LoginFailedEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("", "yhedujiks");
        loginPage.verifyErrorMessage("Username is required");
    }

    @Test
    public void TS0104_LoginFailedEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("random", "");
        loginPage.verifyErrorMessage("Password is required");
    }

    @Test
    public void TS0105_LoginFailedEmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("", "");
        loginPage.verifyErrorMessage("Username is required");
    }

    @Test
    public void TS0106_LoginFailedLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMessage("Sorry, this user has been locked out.");
    }

}
