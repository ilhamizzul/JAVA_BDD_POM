package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends MainPage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private final By btnBack = By.xpath("//button[@class='inventory_details_back_button']");
    private final By lblProductName = By.xpath("//div[@class='inventory_details_name']");
    private final By lblProductPrice = By.xpath("//div[@class='inventory_details_price']");
    private final By btnAddtoCart = By.xpath("//div[@class='inventory_details_desc_container']//button");

    @Override
    public void verifyPageLoaded() {
        verifyElementsAreVisible(btnBack, lblProductName, lblProductPrice, btnAddtoCart);
        verifyButtonAddtoCartisToggled(true);
    }

    public void clickButtonBack() {
        click(btnBack);
    }

    public void clickButtonAddtoCart() {
        click(btnAddtoCart);
    }

    public void verifyButtonAddtoCartisToggled(boolean isToggledActive) {
        if (isToggledActive) {
            assert find(btnAddtoCart).getText().equals("REMOVE");
        } else {
            assert find(btnAddtoCart).getText().equals("ADD TO CART");
        }
    }

    public void verifyProductName(String name) {
        String productName = GetText(lblProductName);
        assert productName.equals(name);
    }

    public void verifyProductPrice(String price) {
        String productPrice = GetText(lblProductPrice);
        assert productPrice.equals(price);
    }
}
