
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
import objRepo.Product_detailspg;

public class Smoke_Testcase_Productpage extends BaseClassSauceDemo 
{

    @Test(groups = {"smoke"})
    public void VerifyProductsDisplayed()
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        Assert.assertEquals(pr.getProductCount(), 6,"All six products should be displayed");
        Reporter.log("product values are displayed- SM01 Passed",true);
        
    }
    
    @Test(groups = {"smoke"})
    public void sortFilterVisible()
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        Assert.assertTrue(pr.getSortDropdown().isDisplayed());
        Reporter.log("sort is diaplayed- SM02 Passed",true);
        
    }
    
    @Test(groups = {"smoke"})
    public void SortProducts() 
    {
    	// verify product page
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("product page is displayed",true);
    	Product_pg pr = new Product_pg(driver);
        pr.sortBy("Name (A to Z)");
        List<String> actual = pr.getProductNamesInDisplayOrder();
        List<String> expected = new ArrayList<String>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual,expected,"Products should be sorted alphabetically");
        Reporter.log("Products are sorted- SM03 Passed",true);
    }

    
}
