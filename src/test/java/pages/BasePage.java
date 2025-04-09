package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method to check visibility of multiple elements
    public boolean verifyElementsAreVisible(By... elements) {
        for (By element : elements) {
            WebElement webElement = find(element);
            if (!webElement.isDisplayed()) {
                throw new AssertionError("Element not visible: " + element.toString());
            }
        }
        return true;
    }

    public void scrollDownByPixel(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy({ top: arguments[0], behavior: 'smooth' })", pixels);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected String GetText(By locator) {
        return find(locator).getText().trim();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }
}
