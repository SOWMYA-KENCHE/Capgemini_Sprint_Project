package base;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtility.DataFromPropertyFile;
import objRepo.Login_pg;
import objRepo.Home_pg;

public class SauceDemoImpl {
	
	protected WebDriver driver = null;
	DataFromPropertyFile commondata = new DataFromPropertyFile();
	// cross-browser
	public void launchbrowser(String browser) {

	    if (browser.equalsIgnoreCase("edge")) {

	        driver = new EdgeDriver();

	    } 
	    else if (browser.equalsIgnoreCase("chrome"))
	    {
	    	// Avoid Change Password popup
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", prefs);
	        driver = new ChromeDriver(settings);

	    } 
	    else {

	        throw new RuntimeException(
	            "Unsupported browser: " + browser
	        );
	    }
	}
	// normal browser
	
	public void launchbrowser() throws IOException {

        String browser = commondata.getDataFromPropertyFile("Browser");

        if (browser.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else {

            throw new RuntimeException(
                    "Unsupported browser: " + browser
            );
        }
    }
	
	
	public void openurl() throws IOException
	{
		String Url = commondata.getDataFromPropertyFile("Url");
		//maximize
		driver.manage().window().maximize();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(Url);
	}
	
	public void username() throws IOException, InterruptedException
	{

		String Username = commondata.getDataFromPropertyFile("Username");
		
		// login page
		Login_pg l = new Login_pg(driver);
		l.getUsername(Username);
	}
	
	public void password() throws IOException, InterruptedException
	{
		String Password = commondata.getDataFromPropertyFile("Password");
		
		// login page
		Login_pg l = new Login_pg(driver);
		l.getPassword(Password);
		Thread.sleep(1000);
	}
	
	public void loginbutton() throws IOException, InterruptedException
	{	
		Login_pg l = new Login_pg(driver);
		l.getLogin_button();
	}
	
	public void logoutbutton() throws IOException, InterruptedException
	{	
		Home_pg p = new Home_pg(driver);
		p.getMenu();
		Thread.sleep(1000);
		p.getLogout();
	}
	
	public void closebrowser()
	{
		driver.quit();
	}
	
	public WebDriver getDriver()
	{
	    return driver;
	}

}
