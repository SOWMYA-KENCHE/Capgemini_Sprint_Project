
package ecom.swaglabs.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Product_pg;
import objRepo.Cart_pg;
import objRepo.Login_pg;

public class System_Testcase_Productpage extends BaseClassSauceDemo 
{

    @Test(groups = {"system"})
    public void VerifyProductSelectionAndCart() 
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
        Product_pg pr = new Product_pg(driver);
        Assert.assertEquals(pr.gettitle(),"Products", "Products page is not displayed");
        Assert.assertEquals(pr.getProductCount(), 6,"Six products should be displayed");
        pr.getBackpackAdd().click();
        Assert.assertEquals(pr.getCartBadgeCount(),1,"Cart badge should display 1");
        // open cart
        pr.getcarticon().click();
        Cart_pg cart = new Cart_pg(driver);
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"),  "Sauce Labs Backpack should be displayed in the cart");
        Reporter.log("complete product page testing -- S01",true);
    }
}
