package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClassLogin 
{
    protected WebDriver driver = null;

    protected SauceDemoExecutors s = new SauceDemoExecutors();
    @BeforeMethod(groups =  {"smoke","functional","integration","system","negative"} )
    @Parameters("browser")
    public void beforemethod(@Optional("edge")String browser) throws IOException, InterruptedException
    {
        // launch browser
        s.executors("LAUNCH_BROWSER");
        // get driver
        driver = s.getDriver();
        // application
        s.executors("OPEN_URL");
    }

    @AfterMethod(groups =  {"smoke","functional","integration","system","negative"} )
    public void afterMethod() throws IOException, InterruptedException {
        // close browser
        if (driver != null) {
            s.executors("CLOSE_BROWSER");
        }
    }
}