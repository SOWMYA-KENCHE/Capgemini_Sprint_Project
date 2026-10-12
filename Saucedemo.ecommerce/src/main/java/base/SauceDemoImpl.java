
package base;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import genericUtility.DataFromPropertyFile;
import objRepo.Login_pg;
import objRepo.Home_pg;

public class SauceDemoImpl {

    protected WebDriver driver = null;

    DataFromPropertyFile commondata = new DataFromPropertyFile();

    // Browser launch with browser name
    public void launchbrowser(String browser) {

        if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            // Configuration for Jenkins execution
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
                false
            );

            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);

        } else {

            throw new RuntimeException(
                "Unsupported browser: " + browser
            );
        }

        configureBrowser();
    }

    // Browser launch using the property file
    public void launchbrowser() throws IOException {

        String browser =
            commondata.getDataFromPropertyFile("Browser");

        // Reuse the same configuration for both overloads
        launchbrowser(browser);
    }

    // Common browser configuration
    private void configureBrowser() {

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    // Open application URL
    public void openurl() throws IOException {

        String url =
            commondata.getDataFromPropertyFile("Url");

        driver.get(url);
    }

    // Return the current WebDriver instance
    public WebDriver getDriver() {
        return driver;
    }

    // Close the browser safely
    public void closebrowser() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /*
     * Keep your existing username(), password(),
     * loginbutton(), logoutbutton(), and other
     * page-object interaction methods here unchanged.
     *
     * Their exact implementations depend on the
     * methods and locators in your Login_pg and Home_pg
     * classes, which are not included in the code
     * available in this conversation.
     */
}
