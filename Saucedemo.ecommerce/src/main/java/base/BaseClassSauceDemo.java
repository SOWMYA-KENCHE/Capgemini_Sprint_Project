package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClassSauceDemo 
{
	    protected WebDriver driver;
	    protected SauceDemoExecutors s;

	    @BeforeMethod(groups =  {"smoke","functional","integration","system","negative"} )
	    @Parameters("browser")
	    public void beforemethod(@Optional("chrome")String browser) throws IOException, InterruptedException
	    {
	        s = new SauceDemoExecutors();
	        s.executors("LAUNCH_BROWSER", browser);
	        driver = s.getDriver();
	        s.executors("OPEN_URL");
	        s.executors("USERNAME");
	        s.executors("PASSWORD");
	        s.executors("LOGIN_BUTTON");
	        Reporter.log("Login successful on " + browser, true);
	    }

	    @AfterMethod(groups =  {"smoke","functional","integration","system","negative"} )
	    public void aftermethod() throws IOException, InterruptedException 
	    {
	        try {
	            if (driver != null &&
	                driver.getCurrentUrl().contains("inventory.html")) {

	                s.executors("LOGOUT_BUTTON");
	                Reporter.log("logout successful", true);
	            }
	        }
	        catch (Exception e) {
	            Reporter.log("Logout skipped: " + e.getMessage(), true);
	        }
	        finally {
	            if (driver != null) {
	                s.executors("CLOSE_BROWSER");
	                Reporter.log("closing browser", true);
	            }
	        }
	    }
}
