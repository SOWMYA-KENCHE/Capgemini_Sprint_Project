package ecom.swaglabs.Login;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import objRepo.Login_pg;

public class Smoke_TestCase_Loginpage extends BaseClassLogin 
{
	@Test(groups = "smoke")
	public void loginButtonClickable() throws InterruptedException 
	{
		// verify
	    Login_pg login = new Login_pg(driver);
	    // Verify Login but is displayed
	    Assert.assertTrue(login.login_button().isDisplayed(),"Login button is not displayed");
	    // Verify Login but is enabled
	    login.getLogin_button();
	    Assert.assertEquals(login.getErrorMessage(),"Epic sadface: Username is required");
	    Reporter.log("Login button is displayed and clickable -- SM01 Passed", true);
	}
	
	@Test(groups = "smoke")
	public void loginPage() 
	{
		// verify
	    Login_pg login = new Login_pg(driver);
	    // Verify Login but is displayed
	    Assert.assertTrue(login.username().isDisplayed());
	    // Verify Login but is enabled
	    Assert.assertTrue(login.password().isDisplayed());
	    // login button
	    Assert.assertTrue(login.login_button().isDisplayed(),"Login button is not displayed");
	    Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
	    Reporter.log("Login page is displayed -- SM01 Passed", true);
	}



}