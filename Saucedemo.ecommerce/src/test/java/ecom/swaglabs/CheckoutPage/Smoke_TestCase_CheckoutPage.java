package ecom.swaglabs.CheckoutPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Cart_pg;
import objRepo.Checkout_pg;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class Smoke_TestCase_CheckoutPage  extends BaseClassSauceDemo
{
	@Test(groups = "smoke")
	public void verifyAllFiledsAredisplayed()
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		// add product
		Product_pg product = new Product_pg(driver);
		product.getBackpackAdd().click();
		product.getcarticon().click();
		// checkout button
		Cart_pg cart = new Cart_pg(driver);
		cart.getcheckoutbutton().click();
		Checkout_pg checkout = new Checkout_pg(driver);
		Assert.assertTrue(checkout.getFirstName().isDisplayed()," first name is displayed");
		Assert.assertTrue(checkout.getLastName().isDisplayed()," last name is displayed");
		Assert.assertTrue(checkout.getPostalCode().isDisplayed()," postal code is displayed");
		Assert.assertEquals(driver.getCurrentUrl().contains("checkout"), true);
		Reporter.log("All the fields are displayed -- SM01 Passed",true);
	}

}
