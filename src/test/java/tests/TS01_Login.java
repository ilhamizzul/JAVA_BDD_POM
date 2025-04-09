package tests;

import config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class TS01_Login {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverConfig.initChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com/v1/index.html");

    }

    @Test
    public void TS0101_LoginSuccess() {
        loginPage.verifyPageLoaded();
        loginPage.fillUsername("standard_user");
        loginPage.fillPassword("secret_sauce");
        loginPage.clickLoginButton();
        inventoryPage.verifyPageLoaded();
        //loginPage.verifyErrorMessage("Username and password do not match any user in this service");
    }

    @Test
    public void TS0102_LoginFailed() {
        loginPage.verifyPageLoaded();
        loginPage.fillUsername("random");
        loginPage.fillPassword("fgrvdbhejnk");
        loginPage.clickLoginButton();
        loginPage.verifyErrorMessage("Username and password do not match any user in this service");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
