
package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class SauceDemoExecutors {

    private final SauceDemoImpl s = new SauceDemoImpl();

    // Executor method with browser parameter
    public void executors(String keyword, String browser)
            throws IOException, InterruptedException {

        if (keyword == null) {
            throw new IllegalArgumentException(
                "Keyword cannot be null"
            );
        }

        if (keyword.equalsIgnoreCase("LAUNCH_BROWSER")) {
            s.launchbrowser(browser);
        } else {
            throw new IllegalArgumentException(
                "Unsupported keyword: " + keyword
            );
        }
    }

    // Executor method using the property file
    public void executors(String keyword)
            throws IOException, InterruptedException {

        if (keyword == null) {
            throw new IllegalArgumentException(
                "Keyword cannot be null"
            );
        }

        if (keyword.equalsIgnoreCase("LAUNCH_BROWSER")) {
            s.launchbrowser();
        } else if (keyword.equalsIgnoreCase("OPEN_URL")) {
            s.openurl();
        } else if (keyword.equalsIgnoreCase("USERNAME")) {
            s.username();
        } else if (keyword.equalsIgnoreCase("PASSWORD")) {
            s.password();
        } else if (keyword.equalsIgnoreCase("LOGIN_BUTTON")) {
            s.loginbutton();
        } else if (keyword.equalsIgnoreCase("LOGOUT_BUTTON")) {
            s.logoutbutton();
        } else if (keyword.equalsIgnoreCase("CLOSE_BROWSER")) {
            s.closebrowser();
        } else {
            throw new IllegalArgumentException(
                "Unsupported keyword: " + keyword
            );
        }
    }

    // Return WebDriver for use in test classes
    public WebDriver getDriver() {
        return s.getDriver();
    }
}
