package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductPage;

public class TS02_Inventory extends TestBase {

    private InventoryPage LoginPrecondition() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        return new InventoryPage(driver);
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

    @Test
    public void TS0203_SeeProductDetail() {
        InventoryPage inventoryPage = LoginPrecondition();
        ProductPage productPage = new ProductPage(driver);
        int productIndex = inventoryPage.clickButtonAddToCart();
        String productName = inventoryPage.getProductName(productIndex);
        String productPrice = inventoryPage.getProductPrice(productIndex);
        inventoryPage.clickButtonProduct(productIndex);
        productPage.verifyPageLoaded();
        productPage.verifyProductName(productName);
        productPage.verifyProductPrice(productPrice);
    }

    @Test
    public void TS0204_ToggleButtonAddToCartInsideDetailProduct() {
        TS0203_SeeProductDetail();
        ProductPage productPage = new ProductPage(driver);
        productPage.clickButtonAddtoCart();
        productPage.verifyButtonAddtoCartisToggled(false);
    }

}
