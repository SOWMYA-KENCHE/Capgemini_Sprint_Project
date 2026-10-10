package ecom.swaglabs.ProductPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import objRepo.Product_pg;
import objRepo.Cart_pg;
import objRepo.Login_pg;

public class Functional_TestCase_Productpage extends BaseClassSauceDemo
{
    @Test(groups = {"functional"})
    public void SortNameAscending() 
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
        Product_pg pr = new Product_pg(driver);
        pr.sortBy("Name (Z to A)");
        Assert.assertEquals(pr.getztoaproductname().getText(), "Test.allTheThings() T-Shirt (Red)");
        Reporter.log("sorting from z to A --F01 Passed",true);
    }

    @Test(groups = {"functional"})
    public void ValidProductDetails() 
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
    	pr.getopenProductDetailsname().click();
    	Cart_pg cart = new Cart_pg(driver);
        Assert.assertEquals(cart.getbigproductname(),"Sauce Labs Backpack");
        Reporter.log("products details are validated--F02 Passed",true);
    }

    @Test(groups = {"functional"})
    public void EmptyCartZero()
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        Assert.assertEquals(pr.getCartBadgeCount(), 0);
        Reporter.log("empty cart displayed 0 count on product page-- F03 Passed",true);
    }

    @Test(groups = {"functional"})
    public void CartCountOneToTwo() 
    {

    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        pr.getBackpackAdd().click();
        Assert.assertEquals(pr.getCartBadgeCount(), 1);
        pr.getBikeLightAdd().click();
        Assert.assertEquals(pr.getCartBadgeCount(), 2);
        Reporter.log("Cart value change-- F04 Passed",true);
    }
    
    @Test(groups = {"functional"})
    public void verifyaddtocartbuttononpage() 
    {

    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        Assert.assertTrue(pr.addtocartbuttondisplay().isDisplayed());
        Reporter.log("add to cart button id diaplyed-- F05 Passed",true);
    }


    @Test(groups = {"functional"})
    public void RefreshPage()
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        driver.navigate().refresh();
        Assert.assertEquals(pr.gettitle(), "Products");
        Assert.assertEquals(pr.getProductCount(), 6);
        Reporter.log("after refresh product page is displayed-- F06 Passed",true);
    }
}