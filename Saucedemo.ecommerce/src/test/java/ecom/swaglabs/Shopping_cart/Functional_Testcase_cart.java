package ecom.swaglabs.Shopping_cart;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Login_pg;
import objRepo.Cart_pg;
import objRepo.Product_pg;

public class Functional_Testcase_cart extends BaseClassSauceDemo{
	
	@Test(groups = "functional")
	public void oneProduct() throws InterruptedException {
		
		//verify login
		
		Login_pg l = new Login_pg(driver);
		l.verify();
		Reporter.log("User logged in successfully with valid credentials", true);
		// add one product
		Product_pg pr = new Product_pg(driver);
		pr.getBackpackAdd().click();
		// open cart
		pr.getcarticon().click();
		//verify
		Cart_pg cp = new Cart_pg(driver);
		Assert.assertTrue(cp.containsProduct("Sauce Labs Backpack")); 
    	Assert.assertEquals(cp.getProductPrice("Sauce Labs Backpack"),"$29.99"); 
    	Assert.assertEquals(cp.getItemCount(),1);
    	Reporter.log("product 1 is displayed in cart -- F01 passed",true); 
	
	}
	
	@Test(groups = "functional")
	public void cartNavigate() throws InterruptedException 
    {
		//verify login
		Login_pg l = new Login_pg(driver);
		l.verify();
		Reporter.log("login is successful",true);
		// open cart
		Product_pg pr = new Product_pg(driver);
		pr.getcarticon().click();
		//verify
		Assert.assertEquals(driver.getCurrentUrl().contains("cart"), true);
		Reporter.log("User successfully navigated to cart section -- F02 Passed",true);
    }
	
	@Test(groups = "functional")
	public void oneProductRemove() throws InterruptedException 
    {
		//verify login
		Login_pg l = new Login_pg(driver);
		l.verify();
		Reporter.log("login is successful",true);
		// add one product
		Product_pg pr = new Product_pg(driver);
		pr.getBackpackAdd().click();
		// open cart
		pr.getcarticon().click();
		//verify
		Cart_pg cp = new Cart_pg(driver);
    	cp.removebutton().click();
    	Assert.assertEquals(cp.getItemCount(),0); 
    	Assert.assertFalse(cp.isBadgeDisplayed()); 
    	Reporter.log("product 1 is removed from the cart -- F03 passed",true); 
    }
	
    @Test(groups = "functional") 
    public void verifyOneProductInCaRt() throws InterruptedException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("login is successful",true);			
    	// add one product
    	Product_pg pr = new Product_pg(driver);
    	pr.getBackpackAdd().click();
    	// open cart
    	pr.getcarticon().click();
    	//verify
    	Cart_pg cp = new Cart_pg(driver);
    	cp.continueShopping().click();
    	Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html")); 
    	Assert.assertEquals(cp.getBadgeCount(),1);
    	Reporter.log("product 1 is still in cart -- F04 passed",true); 
    } 
    
    @Test(groups = "functional") 
    public void all6Items() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("login is successful",true);	
    	// product pg
    	Product_pg pr = new Product_pg(driver);
    	pr.getBackpackAdd().click();
    	pr.getBikeLightAdd().click();
    	pr.getBoltTshirtAdd().click();
    	pr.getRedTshirtAdd().click();
    	pr.getFleeceAdd().click();
    	pr.getOnesieAdd().click();
    	Assert.assertEquals(pr.getCartBadgeCount(),6);
    	// open cart
    	pr.getcarticon().click();
    	Cart_pg cart = new Cart_pg(driver);
    	Assert.assertEquals(cart.getItemCount(),6); 
    	Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"), "Backpack is missing from the cart");
    	Assert.assertTrue(cart.containsProduct("Sauce Labs Bike Light"), "Bike Light is missing from the cart");
    	Assert.assertTrue(cart.containsProduct("Sauce Labs Bolt T-Shirt"), "bolt t-shirt  is missing from the cart");
    	Assert.assertTrue(cart.containsProduct("Sauce Labs Fleece Jacket"), "fleece jacket is missing from the cart");
    	Assert.assertTrue(cart.containsProduct("Sauce Labs Onesie"), "onesie is missing from the cart");
    	Assert.assertTrue(cart.containsProduct("Test.allTheThings() T-Shirt (Red)"),"red t-shirt is missing from the cart");
    	Reporter.log("all 6 products added are displayed in cart -- F05 passed",true); 
    }
    
    @Test(groups = "functional") 
    public void addAndRemove() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("login is successful",true);					
    	// product pg
    	Product_pg pr = new Product_pg(driver);
    	pr.getBackpackAdd().click();
    	Cart_pg cart = new Cart_pg(driver);
    	Assert.assertTrue(pr.removebutton().isDisplayed()); 
    	pr.getcarticon().click();
    	cart.removebutton().click();
    	cart.continueShopping().click();
    	Assert.assertTrue(pr.addtocartbuttondisplay().isDisplayed()); 
    	pr.getBackpackAdd().click();
    	Assert.assertEquals(pr.getCartBadgeCount(),1); 
    	// open cart
    	pr.getcarticon().click();
    	Cart_pg cp = new Cart_pg(driver);
    	Assert.assertEquals(cp.getItemCount(),1); 
    	Reporter.log("F06 passed",true); 
    }
    
    @Test(groups = "functional") 
    public void productsInAndAfter() throws InterruptedException 
    { 

    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);				
    	// product pg
    	Product_pg pr = new Product_pg(driver);
    	pr.sortBy("Price (low to high)");
    	pr.getOnesieAdd().click();
    	pr.getBackpackAdd().click();
    	// open cart
    	pr.getcarticon().click();
    	Cart_pg cp =new Cart_pg(driver);
    	Assert.assertEquals(cp.getProductNames().get(0),"Sauce Labs Onesie"); 
    	Assert.assertEquals(cp.getProductNames().get(1),"Sauce Labs Backpack");
    	Reporter.log("F07 passed",true); 
    }
    @Test(groups = "functional")
    public void bigProductDetailsAreSame() throws InterruptedException
    { 
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);				
    	// product pg
    	Product_pg pr = new Product_pg(driver);
    	pr.getBackpackAdd().click();
    	// open cart
    	pr.getcarticon().click();
    	Cart_pg cart =new Cart_pg(driver); 
    	cart.getproduct().click();
    	Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html")); 
    	Assert.assertEquals(cart.getbigproductname(),"Sauce Labs Backpack");
    	Reporter.log("F08 passed",true); 
    }

}
