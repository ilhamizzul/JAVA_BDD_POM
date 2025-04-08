package tests;

import config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class TS01_Login {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverConfig.initChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://www.saucedemo.com/v1/index.html");
    }

    @Test
    public void TS0101_LoginFailed() {
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
