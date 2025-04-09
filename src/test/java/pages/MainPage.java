package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // navbar
    private By divLogo = By.xpath("//div[@class='app_logo']");
    private By btnCart = By.xpath("//a[@href='./cart.html']");
    private By lblCartBadge = By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']");
    private By btnBurger = By.xpath("//div[@class='bm-burger-button']");
    // sidebar
    private By divSidebarMenu = By.xpath("//div[@class='bm-menu']");
    private By lnkAllItems = By.xpath("//a[@id='inventory_sidebar_link']");
    private By lnkAbout = By.xpath("//a[@id='about_sidebar_link']");
    private By lnkLogout = By.xpath("//a[@id='logout_sidebar_link']");
    private By btnClose = By.xpath("//button[normalize-space()='Close Menu']");

    //Footer
    private By Footer = By.xpath("//footer[@class='footer']");
    private By divFooter = By.xpath("//div[@class='footer_copy']");

    public void verifyPageLoaded() {
        verifyElementsAreVisible(divLogo, btnCart, btnBurger);
        scrollDownByPixel(driver, 250);
        verifyElementsAreVisible(Footer, divFooter);
    }

    public void clickButtonCart() {
        click(btnCart);
    }

    public void clickButtonBurger() {
        click(btnBurger);
    }

    public void clickButtonCloseSidebar() {
        click(btnClose);
    }

    public void clickLinkAllItems() {
        if (verifyElementsAreVisible(divSidebarMenu)) {
            click(lnkAllItems);
        } else {
            clickButtonBurger();
            click(lnkAllItems);
        }
    }

    public void clickLinkAbout() {
        if (verifyElementsAreVisible(divSidebarMenu)) {
            click(lnkAbout);
        } else {
            clickButtonBurger();
            click(lnkAbout);
        }
    }

    public void clickLinkLogout() {
        if (verifyElementsAreVisible(divSidebarMenu)) {
            click(lnkLogout);
        } else {
            clickButtonBurger();
            click(lnkLogout);
        }
    }


}
