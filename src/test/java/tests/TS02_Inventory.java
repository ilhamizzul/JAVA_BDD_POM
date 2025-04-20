package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductPage;
import static utils.Dictionary.*;

public class TS02_Inventory extends TestBase {

    private InventoryPage LoginPrecondition() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        return new InventoryPage(driver);
    }

    @Test
    public void TS0201_VerifyPageLoaded() {
        InventoryPage inventoryPage = LoginPrecondition();
        inventoryPage.verifyPageLoaded();
        inventoryPage.verifyItemsAreSorted(SortBy.ASC.toString(), OrderBy.NAME.toString());
    }

    @Test
    public void TS0202_AddItemToCart() {
        InventoryPage inventoryPage = LoginPrecondition();
        int index = inventoryPage.clickButtonAddToCart();
        Assert.assertTrue(inventoryPage.verifyIsButtonItemInRemoveState(index));
    }

    @Test
    public void TS0203_RemoveItemFromCart() {
        InventoryPage inventoryPage = LoginPrecondition();
        inventoryPage.clickButtonAddToCart();
        inventoryPage.clickButtonAddToCart();
        int index = inventoryPage.clickButtonRemoveItem();

        Assert.assertTrue(inventoryPage.verifyIsButtonItemInAddToCartState(index));
    }

    @Test
    public void TS0204_SeeProductDetail() {
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
    public void TS0205_ToggleButtonAddToCartInsideDetailProduct() {
        TS0204_SeeProductDetail();
        ProductPage productPage = new ProductPage(driver);
        productPage.clickButtonAddtoCart();
        productPage.verifyButtonAddtoCartisToggled(false);
    }

    @DataProvider(name = "sortData")
    public Object[] sortData() {
        return new Object[][] {
                {"az", SortBy.ASC.toString(), OrderBy.NAME.toString() },
                {"za", SortBy.DESC.toString(), OrderBy.NAME.toString()},
                {"lohi", SortBy.ASC.toString(), OrderBy.PRICE.toString() },
                {"hilo", SortBy.DESC.toString(), OrderBy.PRICE.toString()}
        };
    }

    @Test(dataProvider = "sortData")
    public void TS0206_SortItems(String selectSort, String sortBy, String orderBy) {
        InventoryPage inventoryPage = LoginPrecondition();
        inventoryPage.clickButtonSortBy(selectSort);
        inventoryPage.verifyItemsAreSorted(sortBy, orderBy);
    }

}
