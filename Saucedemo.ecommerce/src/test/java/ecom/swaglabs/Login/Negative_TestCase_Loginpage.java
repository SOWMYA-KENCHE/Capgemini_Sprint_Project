package ecom.swaglabs.Login;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class Negative_TestCase_Loginpage extends BaseClassLogin
{
	// functional - negative
	 	@Test(groups =  {"functional","negative"} )
	    public void invalidPassword() throws InterruptedException, IOException 
	    {
	        Login_pg login = new Login_pg(driver);
	        // Enter username
	        DataFromExcelFile data = new DataFromExcelFile();
	        login.getUsername(data.getDataFromExcel("Sheet2", 1, 0));
	        // Enter password
	        login.getPassword(data.getDataFromExcel("Sheet2", 1, 1));
	        // Click login
	        login.getLogin_button();
	        // Get error message
	        String actualError = login.getErrorMessage();
	        // Verify error
	        String expectedError = data.getDataFromExcel("Sheet2", 1, 2);
	        Assert.assertEquals(actualError,expectedError);
	        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"));
	        Reporter.log("user not logged in beacause of inavlid password-- F01 Passed",true);
	    }
	 	
	 	@Test(groups =  {"functional","negative"} )
	    public void invalidUsername() throws InterruptedException, IOException 
	    {
	        Login_pg login = new Login_pg(driver);
	        // Enter username
	        DataFromExcelFile data = new DataFromExcelFile();
	        login.getUsername(data.getDataFromExcel("Sheet2", 2, 0));
	        // Enter password
	        login.getPassword(data.getDataFromExcel("Sheet2", 2, 1));
	        // Click login
	        login.getLogin_button();
	        // Get error message
	        String actualError = login.getErrorMessage();
	        // Verify error
	        String expectedError = data.getDataFromExcel("Sheet2", 2, 2);
	        Assert.assertEquals(actualError,expectedError);
	        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"));
	        Reporter.log("user not logged in because of invalid username -- F02 Passed",true);
	    }
	 	
	 	@Test(groups =  {"functional","negative"} )
	    public void invalidblankspace() throws InterruptedException, IOException 
	    {
	        Login_pg login = new Login_pg(driver);
	        // Enter username
	        Actions actions = new Actions(driver);
	        actions.moveToElement(login.username()).click().sendKeys("    ").perform();
	        actions.moveToElement(login.password()).click().sendKeys("    ").perform();
	        login.getLogin_button();
	        // Verify error
	        Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
	        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"));
	        Reporter.log("user not logged in because of blank username and password -- F03 Passed",true);
	        
	    }
	 	
	 	@Test(groups =  {"functional","negative"} )
	    public void verifylocked_user() throws InterruptedException, IOException 
	    {
	 		Login_pg login = new Login_pg(driver);
	        // Enter username
	        DataFromExcelFile data = new DataFromExcelFile();
	        login.getUsername(data.getDataFromExcel("invaliddata", 1, 0));
	        // Enter password
	        login.getPassword(data.getDataFromExcel("invaliddata", 1, 1));
	        // Click login
	        login.getLogin_button();
	        // Get error message
	        String actualError = login.getErrorMessage();
	        // Verify error
	        String expectedError = data.getDataFromExcel("invaliddata", 1, 2);
	        Assert.assertEquals(actualError,expectedError);
	        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"));
	        Reporter.log("user not logged in because of locked_user-- F04 Passed",true);
	    }
	 	
		// integration - negative
	 	
	 	@Test(groups = {"integration","negative"})
	    public void productPageNotAccessWithoutLogin() throws InterruptedException, IOException 
	    {
	 		Login_pg login = new Login_pg(driver);
	        driver.get("https://www.saucedemo.com/inventory.html");
	        // Verify user is not allowed to access Products page
	        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),"User should not access Products page without login");
	        // Verify Login page is displayed
	        Assert.assertTrue(login.username().isDisplayed(),"Username field is not displayed");
	        Assert.assertTrue(login.password().isDisplayed(),"Password field is not displayed" );
	        // Verify error message
	        Assert.assertEquals(login.getErrorMessage(), "Epic sadface: You can only access '/inventory.html' when you are logged in.");
	        DataFromExcelFile data = new DataFromExcelFile();
	        String username = data.getDataFromExcel("Sheet1",1, 0);
	        String password = data.getDataFromExcel("Sheet1",1, 1);
	        // Enter valid username
	        login.getUsername(username);
	        // Enter valid password
	        login.getPassword(password);
	        // Click Login
	        login.getLogin_button();
	        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Valid user should be able to access Products page");
	        Reporter.log("Product page not accessable without login-- F05 Passed",true);
	    }
	
	 	// system- negative
	 	
	 	@Test(groups = {"system","negative"} )
	 	public void invalidLoginJourney()throws InterruptedException, IOException
	 	{
	 	    // Login page
	 	    Login_pg login = new Login_pg(driver);
	 	    DataFromExcelFile data = new DataFromExcelFile();
	 	    String username =data.getDataFromExcel("Sheet2", 1, 0);
	 	    // Use wrong password
	 	    String wrongPassword = data.getDataFromExcel("Sheet2", 1, 2);
	 	    // Enter username
	 	    login.getUsername(username);
	 	    // Enter invalid password
	 	    login.getPassword(wrongPassword);
	 	    // Click Login
	 	    login.getLogin_button();
	 	    // Verify error message
	 	    Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service",
	 	            "Incorrect error message displayed");
	 	    Assert.assertFalse( driver.getCurrentUrl().contains("inventory.html"),"Invalid user should not access Products page" );
	 	    Assert.assertTrue(login.username().isDisplayed(), "Username field is not displayed");
	 	    Assert.assertTrue(login.password().isDisplayed(),"Password field is not displayed");
	 	    Reporter.log("Negative Login journey passed--S01 Passed", true);
	 	}
	 	
	 	// smoke - negative 
	 	
	 	 @Test(groups = {"smoke","negative"} )
	     public void invalidLogin() throws InterruptedException, IOException 
	     {

	     	// Create Login Page object
	         Login_pg login = new Login_pg(driver);
	         DataFromExcelFile data = new DataFromExcelFile();
	         // Enter valid username
	         login.getUsername(data.getDataFromExcel("invaliddata", 2, 0));
	         // Enter valid password
	         login.getPassword(data.getDataFromExcel("invaliddata", 2, 1));
	         // Click Login
	         login.getLogin_button();
	         // Verify error message
	         Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
	         // Verify user is still on Login page
	         Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"), "User should not access Products page with invalid credentials");
	         Assert.assertTrue(login.username().isDisplayed(),"Username field is not displayed" );
	         Assert.assertTrue(login.password().isDisplayed(),"Password field is not displayed");
	         Reporter.log("Invalid login rejected--SM01 Passed", true);
	     }

}
