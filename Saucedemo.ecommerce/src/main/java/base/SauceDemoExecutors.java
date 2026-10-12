
package base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

public class SauceDemoExecutors {

    private final SauceDemoImpl s = new SauceDemoImpl();

    public void executors(String keyword, String browser)
            throws IOException, InterruptedException {

        if ("LAUNCH_BROWSER".equalsIgnoreCase(keyword)) {
            s.launchbrowser(browser);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported keyword: " + keyword);
        }
    }

    public void executors(String keyword)
            throws IOException, InterruptedException {

        if ("LAUNCH_BROWSER".equalsIgnoreCase(keyword)) {
            s.launchbrowser();

        } else if ("OPEN_URL".equalsIgnoreCase(keyword)) {
            s.openurl();

        } else if ("USERNAME".equalsIgnoreCase(keyword)) {
            s.username();

        } else if ("PASSWORD".equalsIgnoreCase(keyword)) {
            s.password();

        } else if ("LOGIN_BUTTON".equalsIgnoreCase(keyword)) {
            s.loginbutton();

        } else if ("LOGOUT_BUTTON".equalsIgnoreCase(keyword)) {
            s.logoutbutton();

        } else if ("CLOSE_BROWSER".equalsIgnoreCase(keyword)) {
            s.closebrowser();

        } else {
            throw new IllegalArgumentException(
                    "Unsupported keyword: " + keyword);
        }
    }

    public WebDriver getDriver() {
        return s.getDriver();
    }
}
