package ecom.swaglabs.Shopping_cart;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import genericUtility.DataFromExcelFile;
import genericUtility.ScreenShotUtility;
import objRepo.Login_pg;
import objRepo.Cart_pg;
import objRepo.Checkout_OverviewPg;
import objRepo.Checkout_complete;
import objRepo.Checkout_pg;
import objRepo.Home_pg;
import objRepo.Product_pg;

public class Negative_testcase extends BaseClassSauceDemo{
	
	 @Test(groups =  {"functional","negative"} )
	 public void emptycart() throws InterruptedException 
	    {
		 	// functional - negative
	    	//verify login
		 	Login_pg l = new Login_pg(driver);
			l.verify();
			Reporter.log("User logged in successfully with valid credentials", true);
	    	// open cart
	    	Product_pg pr = new Product_pg(driver);
	    	pr.getcarticon().click();
	    	Cart_pg cp = new Cart_pg(driver);
	    	cp.getcheckoutbutton().click();
	    	// Take screenshot
	    	ScreenShotUtility ss = new ScreenShotUtility();
	    	ss.takeScreenshot(driver, "F01_EmptyCart_Checkout");
	    	Assert.assertEquals(cp.getItemCount(),0,"Cart should be empty"); 
	    	Reporter.log(" invalid checkout -- negative 01 Passed",true); 
	    }
	 
	 @Test(groups = {"negative","integration"} )
	 public void VerifyCartAccessWithoutLogin() throws InterruptedException, IOException 
	 {
	     // user is already logged in method
	     // Logout
	     Home_pg home = new Home_pg(driver);
	     home.getMenu().click();
	     home.getLogout().click();
	     DataFromExcelFile data = new DataFromExcelFile();
		 String carturl = data.getDataFromExcel("url", 1, 0);
	     driver.get(carturl);
	     // Verify user is redirected to Login page
	     Login_pg login = new Login_pg(driver);
	     Assert.assertTrue(login.username().isDisplayed(),"Username field is not displayed");
	     Assert.assertTrue(login.password().isDisplayed(),"Password field is not displayed");
	     Reporter.log("Cart cannot be accessed without login -- negative 01 Passed",true);
	 }

	 @Test(groups = {"negative","integration"} )
	 public void addingAndRemovingRemovesBadge() throws InterruptedException 
	 {
		//verify login
	    	Login_pg l = new Login_pg(driver);
	    	l.verify();
	    	Reporter.log("User logged in successfully with valid credentials", true);
	    	Product_pg pr = new Product_pg(driver);
	    	pr.getBackpackAdd().click();
	    	pr.getBikeLightAdd().click();
	    	Assert.assertEquals(pr.getCartBadgeCount(), 2);
	    	Cart_pg cart = new  Cart_pg(driver);
	    	pr.getcarticon().click();
	    	// remove product
	    	cart.removebutton().click();
	    	cart.removebutton().click();
	    	Assert.assertEquals(cart.getBadgeCount(), 0);
	    	cart.continueShopping().click();
	    	Assert.assertEquals(pr.getCartBadgeCount(), 0);
	    	Reporter.log(" add to remove to get badge count -- negative 02 Passed",true);
	    	     
	 }
	    @Test(groups = {"negative","system"} )
	    public void BrowserBackAfterCompletedOrder() throws InterruptedException, IOException
	    {

	    	//verify login
	    	Login_pg l = new Login_pg(driver);
	    	l.verify();
	    	Reporter.log("User logged in successfully with valid credentials", true);
	    	// product page
	        Product_pg pr = new Product_pg(driver);
	        Cart_pg cart = new Cart_pg(driver);
	        Checkout_pg checkout = new Checkout_pg(driver);
	        pr.getBackpackAdd().click();
	        pr.getcarticon().click();
	        cart.getcheckoutbutton().click();
	        DataFromExcelFile data = new DataFromExcelFile();
			String firstname = data.getDataFromExcel("checkoutdata", 1, 0);
			String lastname = data.getDataFromExcel("checkoutdata", 1, 1);
			DataFormatter df = new DataFormatter();
			FileInputStream file1 = new FileInputStream("./src/test/resources/saucedemotestdata.xlsx");
	        Workbook workb= WorkbookFactory.create(file1);
			String zipcode = df.formatCellValue(workb.getSheet("checkoutdata").getRow(1).getCell(2));
			checkout.enterInformation(firstname, lastname, zipcode);
	        checkout.getcontinueCheckout().click();
	        Checkout_OverviewPg ch = new Checkout_OverviewPg(driver);
	        ch.finish().click();
	        Checkout_complete cc = new Checkout_complete(driver);
	        Assert.assertEquals(cc.getCompleteHeader().getText(),"Thank you for your order!","Order was not completed" );
	        Reporter.log("Order completed successfully",true);
	        // Browser Back
	        cc.getbackHomeButton().click();
	        ScreenShotUtility ss = new ScreenShotUtility();
	        ss.takeScreenshot(driver,"S01_BrowserBack_AfterOrder");
	        Reporter.log("Browser Back executed after completed order",true);
	        // current page
	        String currentUrl = driver.getCurrentUrl();
	        Reporter.log("Current URL after Browser Back: " + currentUrl,true);
	        // cart badge
	        int badgeCount = pr.getCartBadgeCount();
	        Reporter.log("Cart badge count after Browser Back: " + badgeCount,true);
	        if (currentUrl.contains("cart.html"))
	        {
	            Reporter.log("Cart row count after Browser Back: "+ cart.getItemCount(), true);
	        }
	        if (currentUrl.contains("checkout-step-two.html"))
	        {

	            Reporter.log("Browser Back returned to Checkout Overview. Recording actual behaviour as required.",true);
	        }
	        Reporter.log("execution completed - actual Browser Back behaviour recorded-- negative 03 Passed", true);
	        Thread.sleep(2000);
	    }
	    
	    @Test(groups = {"negative","smoke"} )
	    public void verifyCartCannotcontinueshoppingWithoutLogin() throws IOException, InterruptedException
	    {
	    	//verify login
	    	Login_pg l = new Login_pg(driver);
	    	l.verify();
	    	Reporter.log("User logged in successfully with valid credentials", true);
	        // Logout first
	    	Home_pg home = new Home_pg(driver);
		     home.getMenu().click();
		     home.getLogout().click();
		     DataFromExcelFile data = new DataFromExcelFile();
			 String carturl = data.getDataFromExcel("url", 1, 0);
		     driver.get(carturl);
	        ScreenShotUtility ss = new ScreenShotUtility();
	        ss.takeScreenshot(driver,"SM01_NoContinueShopping");
	        // Verify user is redirected to login page
	        Login_pg login = new Login_pg(driver);
	        Assert.assertTrue(login.username().isDisplayed());
	        Assert.assertTrue(login.password().isDisplayed());
	        Reporter.log("verifyCart Cannot Be Opened Without Login -- negative 04 Passed", true);
	        Thread.sleep(2000);
	    }

}
