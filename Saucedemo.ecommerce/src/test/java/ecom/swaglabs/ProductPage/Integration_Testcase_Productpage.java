
package ecom.swaglabs.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Product_pg;
import objRepo.Cart_pg;
import objRepo.Login_pg;
import objRepo.Product_detailspg;

public class Integration_Testcase_Productpage extends BaseClassSauceDemo
{
	
    @Test(groups = {"integration"})
    public void AddValidProductToCart() 
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
        Product_pg p = new Product_pg(driver);
        p.getBackpackAdd().click();
        p.getcarticon().click();
        Cart_pg cart = new Cart_pg(driver);
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"),"Added product is not displayed in the cart" );
        Reporter.log("product is added to cart",true);
    }


    @Test(groups = {"integration"})
    public void Add3Products()
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
        Product_pg p = new Product_pg(driver);

        p.getBackpackAdd().click();
        p.getBikeLightAdd().click();
        p.getBoltTshirtAdd().click();
        Assert.assertEquals(p.getCartBadgeCount(),3,"Cart badge should display 3" );
        p.getcarticon().click();
        Cart_pg cart = new Cart_pg(driver);
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"));
        Assert.assertTrue(cart.containsProduct("Sauce Labs Bike Light"));
        Assert.assertTrue(cart.containsProduct("Sauce Labs Bolt T-Shirt"));
        Reporter.log("3 product is added to cart",true);
    }


    @Test(groups = {"integration"})
    public void NoProductAddedCartEmpty()
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
        Product_pg p = new Product_pg(driver);
        Assert.assertEquals(p.getCartBadgeCount(), 0, "Cart should initially be empty" );
        p.getcarticon().click();
        Cart_pg cart = new Cart_pg(driver);
        Assert.assertFalse(cart.containsProduct("Sauce Labs Backpack"),"Cart should not contain an unselected product");
        Reporter.log("added product to empty cart",true);
    }


}
