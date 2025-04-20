package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static utils.Dictionary.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryPage extends MainPage {
    public InventoryPage(WebDriver driver) {
        super(driver);
        this.addedItemIndex = new ArrayList<>();
    }

    private final By cardItemsLocator = By.xpath("//div[@class='inventory_item']");
    private final By drpSortBy = By.xpath("//select[@class='product_sort_container']");
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

    public void verifyItemsAreSorted(String sortBy, String orderBy) {
        List<WebElement> cardItems = driver.findElements(cardItemsLocator);
        for (int i = 0; i < cardItems.size() - 1; i++) {
            if (orderBy.equalsIgnoreCase(OrderBy.NAME.toString())) {
                String productName1 = GetText(By.xpath(String.format(xpathProductName, i+1)));
                String productName2 = GetText(By.xpath(String.format(xpathProductName, i+2)));
                assert sortBy.equalsIgnoreCase(SortBy.ASC.toString()) ?
                        productName1.compareTo(productName2) <= 0 :
                        productName1.compareTo(productName2) >= 0;
            } else {
                String productPrice1Text = GetText(By.xpath(String.format(xpathProductPrice, i+1))).replace("$", "");
                String productPrice2Text = GetText(By.xpath(String.format(xpathProductPrice, i+2))).replace("$", "");
                double productPrice1 = Double.parseDouble(productPrice1Text);
                double productPrice2 = Double.parseDouble(productPrice2Text);

                if (sortBy.equalsIgnoreCase(SortBy.ASC.toString())) {
                    assert productPrice1 <= productPrice2 : "Prices not sorted ascending at index " + i;
                } else {
                    assert productPrice1 >= productPrice2 : "Prices not sorted descending at index " + i;
                }
            }

        }
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
        // Get a random item's index from the added items list
        int relativeIndex = randomItem(addedItemIndex.size());
        int actualIndex = addedItemIndex.get(relativeIndex);

        // Verify the item's button is in "Remove" state
        if (!verifyIsButtonItemInRemoveState(actualIndex)) {
            throw new IllegalStateException("Item at index " + actualIndex + " is not in the 'Remove' state.");
        }

        click(By.xpath(String.format(xpathItemButton, actualIndex + 1)));

        if (!verifyIsButtonItemInAddToCartState(actualIndex)) {
            throw new IllegalStateException("Item at index " + actualIndex + " did not switch back to 'Add to Cart' state.");
        }

        addedItemIndex.remove(relativeIndex);
        return actualIndex;
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

    public void clickButtonSortBy(String sortBy) {
        Select selectSortBy = new Select(find(drpSortBy));
        selectSortBy.selectByValue(sortBy);
    }

}
