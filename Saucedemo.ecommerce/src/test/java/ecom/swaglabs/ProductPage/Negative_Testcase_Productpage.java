
package ecom.swaglabs.ProductPage;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Product_pg;
import objRepo.Cart_pg;
import objRepo.Login_pg;
import objRepo.Product_detailspg;

public class Negative_Testcase_Productpage extends BaseClassSauceDemo 
{


	@Test(groups = {"negative"})
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
        Assert.assertFalse(cart.containsProduct("Sauce Labs Backpack"),"Cart should not contain an unselected product"
        );
        Reporter.log("added product to empty cart",true);
    }
}
