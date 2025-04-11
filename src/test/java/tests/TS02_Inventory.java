package tests;

import config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class TS02_Inventory extends TestBase {

    private InventoryPage LoginPrecondition() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        InventoryPage inventory = loginPage.loginValidUser("standard_user", "secret_sauce");
        return inventory;
    }

    @Test
    public void TS0201_AddItemToCart() {
        InventoryPage inventoryPage = LoginPrecondition();
        int index = inventoryPage.clickButtonAddToCart();
        Assert.assertTrue(inventoryPage.verifyIsButtonItemInRemoveState(index));
    }

    @Test
    public void TS0202_RemoveItemFromCart() {
        InventoryPage inventoryPage = LoginPrecondition();
        inventoryPage.clickButtonAddToCart();
        inventoryPage.clickButtonAddToCart();
        int index = inventoryPage.clickButtonRemoveItem();

        Assert.assertTrue(inventoryPage.verifyIsButtonItemInAddToCartState(index));
    }

}
