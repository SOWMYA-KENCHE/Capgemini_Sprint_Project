package ecom.swaglabs.Login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Home_pg;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class System_Testcase_Loginpage extends BaseClassLogin
{
	@Test(groups = "system")
	public void verifyCompleteLogin() throws IOException, InterruptedException
	{
		// login
		Login_pg login = new Login_pg(driver);
		DataFromExcelFile data = new DataFromExcelFile();
		login.getUsername(data.getDataFromExcel("Sheet1", 1, 0));
		login.getPassword(data.getDataFromExcel("Sheet1", 1, 1));
		login.getLogin_button();
		Assert.assertEquals(driver.getCurrentUrl().contains("inventory"), true);
		Reporter.log("User logged in successfully with valid credentials", true);
		Product_pg pr = new Product_pg(driver);
		pr.getBackpackAdd().click();
		Assert.assertEquals(pr.getCartBadgeCount(),1,"product page is not displayed");
		Reporter.log("Complete Login journey passed-- S01 Passed",true);
	}
	
	 @Test(groups = "system")
	    public void logoutFromMenu() throws InterruptedException, IOException 
	    {
	        Login_pg login = new Login_pg(driver);
	        // Enter username
	        DataFromExcelFile data = new DataFromExcelFile();
	        login.getUsername(data.getDataFromExcel("Sheet1",1, 0));
	        login.getPassword(data.getDataFromExcel("Sheet1",1, 1));
	        login.getLogin_button();
	        Assert.assertEquals(driver.getCurrentUrl().contains("inventory"), true);
			Reporter.log("User logged in successfully with valid credentials", true);
	        // Get error message
	        Home_pg home = new Home_pg(driver);
	        home.getMenu().click();
	        home.getLogout().click();
	        // Verify error
	        Assert.assertEquals(driver.getCurrentUrl(),login.url());
	        System.out.println(login.url());
	        Reporter.log("user is logged out succesfully from hamburger menu-- S02 Passed",true);
	    }

}
