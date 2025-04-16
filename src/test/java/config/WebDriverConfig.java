package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverConfig {
    static WebDriver driver;
    public static WebDriver initChromeDriver() {


        // Setup WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Set Chrome preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Disable credential service
        prefs.put("profile.password_manager_enabled", false); // Disable password manager
        prefs.put("safebrowsing.enabled", false); // Disable Safe Browsing (to avoid "Change your password" popup)

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito"); // Optional: start in incognito

        // Launch browser
        driver = new ChromeDriver(options);

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
}
