package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class TS01_Login extends TestBase {

    @DataProvider(name = "loginTestData")
    public Object[][] loginTestData() {
        return new Object[][]{
                {"random", "yhedujiks", "Username and password do not match any user in this service"},
                {"", "yhedujiks", "Username is required"},
                {"random", "", "Password is required"},
                {"", "", "Username is required"},
                {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out."}
        };
    }

    @Test
    public void TS0101_LoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.verifyPageLoaded();
    }

    @Test( dataProvider = "loginTestData")
    public void TS0102_TestLoginFailures(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.verifyPageLoaded();
        loginPage.login(username, password);
        loginPage.verifyErrorMessage(expectedErrorMessage);

    }

}
