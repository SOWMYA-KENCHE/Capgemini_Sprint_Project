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

public class Integration_testcase_LoginPage extends BaseClassLogin
{
	@Test(groups = "integration")
    public void addProductToCartAfterLogin() throws InterruptedException, IOException
    {
    	// login 
    	Login_pg login = new Login_pg(driver);
        DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Sheet1",1, 0);
        String password = data.getDataFromExcel("Sheet1",1, 1);
        // Enter valid username
        login.getUsername(username);
        // Enter valid password
        login.getPassword(password);
        // Click Login
        login.getLogin_button();
        // Verify Products page
        Product_pg pr = new Product_pg(driver);
        pr.getBikeLightAdd().click();
        Assert.assertEquals(pr.gettitle(), "Products");
        // Verify cart icon
        Assert.assertEquals(pr.getCartBadgeCount(),1);
        Reporter.log("user is able to login and add a product to cart -- I01 Passed",true);
    }
	
	@Test(groups = "integration")
    public void menuIsVisibleAfterLogin() throws InterruptedException, IOException
    {
    	// login 
    	Login_pg login = new Login_pg(driver);
        DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Sheet1",1, 0);
        String password = data.getDataFromExcel("Sheet1",1, 1);
        // Enter valid username
        login.getUsername(username);
        // Enter valid password
        login.getPassword(password);
        // Click Login
        login.getLogin_button();
        // Verify Products page
        Product_pg pr = new Product_pg(driver);
        Assert.assertEquals(pr.gettitle(),"Products");
        // menu 
        Home_pg home = new Home_pg(driver);
        System.out.println(home.menudisplayed());
        Assert.assertTrue(home.menudisplayed(),"hamburger is nor displayed");
        Reporter.log("menu is accessible and visible  -- I02 Passed",true);
    }
}
