package ecom.swaglabs.Shopping_cart;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Login_pg;
import objRepo.Cart_pg;
import objRepo.Checkout_pg;
import objRepo.Home_pg;
import objRepo.Product_pg;

public class Smoke_Testcase_cart extends BaseClassSauceDemo {

    @Test(groups = "smoke")
    public void verifyLoginAndProductsPage() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	Product_pg pr = new Product_pg(driver);
    	Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html")); 
        // product page
        Assert.assertTrue(pr.getcarticon().isDisplayed());
        Reporter.log("cart is displayed -- SM01 passed",true); 
    }

    @Test(groups = "smoke")
    public void verifyProductCanBeAddedToCart() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
        Product_pg pr = new Product_pg(driver);
        pr.getBackpackAdd().click();
        Assert.assertEquals(pr.getCartBadgeCount(), 1);
        Reporter.log("product can be added -- SM02 passed",true); 
    }

    @Test(groups = "smoke")
    public void verifyCartPageShowsAddedProduct() throws InterruptedException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
        Product_pg pr = new Product_pg(driver);
        pr.getBackpackAdd().click();
        Cart_pg cart = new Cart_pg(driver);
        pr.getcarticon().click();
        Assert.assertEquals(cart.getItemCount(), 1);
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"));
        Reporter.log("cart shows added product -- SM03 passed",true); 
    }

    @Test(groups = "smoke")
    public void verifyCartToCheckout() throws InterruptedException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
        Product_pg pr = new Product_pg(driver);
        pr.getBackpackAdd().click();
        Cart_pg cart = new Cart_pg(driver);
        pr.getcarticon().click();
        cart.getcheckoutbutton().click();
        Checkout_pg checkout = new Checkout_pg(driver);
        Assert.assertEquals(checkout.getcheckoutheaderinfo().getText(),"Checkout: Your Information");
        Reporter.log("cart to checkout-- SM04 passed",true); 
    }

    @Test(groups = "smoke")
    public void verifyProductCanBeRemovedFromCart() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
        Product_pg pr = new Product_pg(driver);
        pr.getBackpackAdd().click();
        Assert.assertEquals(pr.getCartBadgeCount(), 1);
        pr.getcarticon().click();
        Cart_pg cart = new Cart_pg(driver);
        Assert.assertEquals(cart.getItemCount(), 1);
        cart.removebutton().click();
        Assert.assertEquals(cart.getItemCount(), 0);
        Assert.assertFalse(cart.isBadgeDisplayed());
        Reporter.log("product can be removed from cart-- SM05 passed",true); 
    }
}