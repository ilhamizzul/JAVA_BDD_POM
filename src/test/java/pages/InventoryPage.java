package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryPage extends MainPage {
    public InventoryPage(WebDriver driver) {
        super(driver);
        this.addedItemIndex = new ArrayList<>();
    }

    private final By cardItemsLocator = By.xpath("//div[@class='inventory_item']");
    private final String xpathProductName = "(//div[@class='inventory_item_name'])[position()=%d]";
    private final String xpathProductPrice = "(//div[@class='inventory_item_price'])[position()=%d]";
    private final String xpathItemButton = "(//button[contains(@class, 'btn_inventory')])[%d]";

    private final List<Integer> addedItemIndex;

    private int randomItem(int upperIndex) {
        Random random = new Random();
        return random.nextInt(upperIndex);
    }

    public boolean verifyIsButtonItemInAddToCartState(int index) {
        By button = By.xpath(String.format(xpathItemButton, index+1));
        System.out.println(GetText(button).equalsIgnoreCase("ADD TO CART"));
        return GetText(button).equalsIgnoreCase("ADD TO CART");
    }

    public boolean verifyIsButtonItemInRemoveState(int index) {
        By button = By.xpath(String.format(xpathItemButton, index+1));
        System.out.println(GetText(button).equalsIgnoreCase("REMOVE"));
        return GetText(button).equalsIgnoreCase("REMOVE");
    }

    public int clickButtonAddToCart() {
        List<WebElement> cardItems = driver.findElements(cardItemsLocator);

        // Get random index from List of card items
        int index = randomItem(cardItems.size());
        while (addedItemIndex.contains(index)) {
            index = randomItem(cardItems.size());
        }

        // button item locator
        By button = By.xpath(String.format(xpathItemButton, index+1));

        // verify if before click of button have an expected text which is "ADD TO CART"
        verifyIsButtonItemInAddToCartState(index);
        click(button);
        verifyIsButtonItemInRemoveState(index);
        addedItemIndex.add(index);
        return index;
    }

    public int clickButtonRemoveItem() {
        if (addedItemIndex.isEmpty()) {
            throw new IllegalStateException("No items to remove from cart.");
        }
        int index = randomItem(addedItemIndex.size());
        int actualIndex = addedItemIndex.get(index);
        verifyIsButtonItemInRemoveState(actualIndex+1);
        click(By.xpath(String.format(xpathItemButton, actualIndex + 1)));
        verifyIsButtonItemInAddToCartState(actualIndex + 1);
        addedItemIndex.remove(index);
        return actualIndex+1;
    }

    public String getProductName(int index) {
        return GetText(By.xpath(String.format(xpathProductName, index+1)));
    }

    public String getProductPrice(int index) {
        return GetText(By.xpath(String.format(xpathProductPrice, index+1)));
    }

    public void clickButtonProduct(int index) {
        click(By.xpath(String.format(xpathProductName, index+1)));
    }

}
