package tests;

import config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class TS02_Inventory {
    private WebDriver driver;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private TS01_Login login = new TS01_Login();
    @BeforeMethod
    public void setUp() {
        driver = WebDriverConfig.initChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com/v1/index.html");
    }
    @Test
    public void TS0201_AddItemToCart() {
        int index = inventoryPage.clickButtonAddToCart();
        Assert.assertTrue(inventoryPage.verifyIsButtonItemInRemoveState(index));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
