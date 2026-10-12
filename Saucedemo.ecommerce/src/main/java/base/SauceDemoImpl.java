
package base;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import genericUtility.DataFromPropertyFile;

public class SauceDemoImpl {

    protected WebDriver driver;

    private final DataFromPropertyFile commondata =
            new DataFromPropertyFile();

    // Launch browser by name
    public void launchbrowser(String browser) {

        if (browser == null || browser.isBlank()) {
            throw new IllegalArgumentException(
                    "Browser name cannot be empty");
        }

        if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-first-run");
            options.addArguments("--disable-extensions");

            driver = new EdgeDriver(options);

        } else if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put(
                    "profile.password_manager_leak_detection",
                    false);

            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    // Launch browser using property file
    public void launchbrowser() throws IOException {
        String browser =
                commondata.getDataFromPropertyFile("Browser");

        launchbrowser(browser);
    }

    // Open application URL
    public void openurl() throws IOException {
        String url =
                commondata.getDataFromPropertyFile("Url");

        if (driver == null) {
            throw new IllegalStateException(
                    "Launch browser before opening URL");
        }

        driver.get(url);
    }

    // Enter username
    public void username() throws IOException {
        String username =
                commondata.getDataFromPropertyFile("Username");

        driver.findElement(By.id("user-name"))
                .sendKeys(username);
    }

    // Enter password
    public void password() throws IOException {
        String password =
                commondata.getDataFromPropertyFile("Password");

        driver.findElement(By.id("password"))
                .sendKeys(password);
    }

    // Click login button
    public void loginbutton() {
        driver.findElement(By.id("login-button")).click();
    }

    // Open menu and click logout
    public void logoutbutton() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    // Close browser
    public void closebrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // Return driver
    public WebDriver getDriver() {
        return driver;
    }
}
