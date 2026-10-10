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
import objRepo.Login_pg;
import objRepo.Cart_pg;
import objRepo.Checkout_OverviewPg;
import objRepo.Checkout_complete;
import objRepo.Checkout_pg;
import objRepo.Product_pg;

public class System_Testcase_cart extends BaseClassSauceDemo
{
    @Test(groups = "system")
    public void singleProductPurchase() throws InterruptedException, IOException 
    {

    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        Checkout_pg checkout = new Checkout_pg(driver);
        // Add 
        pr.getBackpackAdd().click();
        // Verify cart badge
        Assert.assertEquals(pr.getCartBadgeCount(),1,"Cart badge count is not 1");
        // Open Cart
        pr.getcarticon().click();
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"),"Backpack is not present in Cart");
        // Verify price
        Assert.assertEquals(cart.getProductPrice("Sauce Labs Backpack"),"$29.99","Backpack price is incorrect");
        cart.getcheckoutbutton().click();
        // Enter customer information
        DataFromExcelFile data = new DataFromExcelFile();
		String firstname = data.getDataFromExcel("checkoutdata", 1, 0);
		String lastname = data.getDataFromExcel("checkoutdata", 1, 1);
		DataFormatter df = new DataFormatter();
		FileInputStream file1 = new FileInputStream("./src/test/resources/saucedemotestdata.xlsx");
        Workbook workb= WorkbookFactory.create(file1);
		String zipcode = df.formatCellValue(workb.getSheet("checkoutdata").getRow(1).getCell(2));
		checkout.enterInformation(firstname, lastname, zipcode);
        // Continue
        checkout.getcontinueCheckout().click();
        Checkout_OverviewPg coverview = new Checkout_OverviewPg(driver);
        // Verify 
        Assert.assertEquals(coverview.getItemTotal(), "Item total: $29.99","Item total is incorrect");
        Assert.assertEquals(coverview.getTax(),"Tax: $2.40","Tax is incorrect");
        Assert.assertEquals(coverview.getTotal(),"Total: $32.39","Total amount is incorrect" );
        // Finish order
        coverview.finish().click();
        Checkout_complete cc = new Checkout_complete(driver);
        Assert.assertEquals(cc.getCompleteHeader().getText(), "Thank you for your order!","Order confirmation message is incorrect");
        Reporter.log("Single product purchase completed successfully -- S01 Passed",true);
        // Back Home
        cc.getbackHomeButton().click();
        // Verify Products page
        Assert.assertEquals(pr.getProductCount(),6,"Products page is not displayed correctly");
        Assert.assertEquals(pr.getCartBadgeCount(),0,"Cart badge is still displayed after purchase");
        Reporter.log("Cart is empty after purchase",true);
        Thread.sleep(2000);
    }

    @Test(groups = "system")
    public void multipleProductPurchase() throws InterruptedException, IOException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        Checkout_pg checkout = new Checkout_pg(driver);
        // Add three products
        pr.getBackpackAdd().click();
        pr.getBikeLightAdd().click();
        pr.getBoltTshirtAdd().click();
        // Verify badge = 3
        Assert.assertEquals(pr.getCartBadgeCount(),3,"Cart badge count is not 3");
        // Open Cart
        pr.getcarticon().click();
        Assert.assertEquals(cart.getItemCount(),3,"Cart does not contain 3 products");
        cart.getcheckoutbutton().click();
        //  customer information
        DataFromExcelFile data = new DataFromExcelFile();
		String firstname = data.getDataFromExcel("checkoutdata", 1, 0);
		String lastname = data.getDataFromExcel("checkoutdata", 1, 1);
		DataFormatter df = new DataFormatter();
		FileInputStream file1 = new FileInputStream("./src/test/resources/saucedemotestdata.xlsx");
        Workbook workb= WorkbookFactory.create(file1);
		String zipcode = df.formatCellValue(workb.getSheet("checkoutdata").getRow(1).getCell(2));
		checkout.enterInformation(firstname, lastname, zipcode);
        checkout.getcontinueCheckout().click();
        // Verify totals
        Checkout_OverviewPg ch = new Checkout_OverviewPg(driver);
        Assert.assertEquals(ch.getItemTotal(),"Item total: $55.97","Item total is incorrect");
        Assert.assertEquals(ch.getTax(),"Tax: $4.48","Tax is incorrect");
        Assert.assertEquals(ch.getTotal(),"Total: $60.45","Total is incorrect");
        ch.finish().click();
        Checkout_complete cc = new Checkout_complete(driver);
        Assert.assertEquals(cc.getCompleteHeader().getText(),"Thank you for your order!", "Order confirmation is incorrect");
        Reporter.log("Multiple product purchase completed successfully -- S02 Passed",true);
        // Back Home
        cc.getbackHomeButton().click();
        // Verify 
        Assert.assertEquals(pr.getCartBadgeCount(),0,"Cart badge is not cleared after purchase");
    }


    @Test(groups = "system")
    public void removeAndPurchaseDifferentProduct() throws InterruptedException, IOException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        Checkout_pg checkout = new Checkout_pg(driver);
        // Add product
        pr.getBackpackAdd().click();
        pr.getcarticon().click();
        cart.removebutton().click();
        // Verify Cart is empty
        Assert.assertEquals(cart.getItemCount(),0,"Cart is not empty after removing Backpack");
        cart.continueShopping().click();
        // Add 
        pr.getBoltTshirtAdd().click();
        // Verify badge
        Assert.assertEquals(pr.getCartBadgeCount(),1,"Cart badge is not 1");
        pr.getcarticon().click();
        Assert.assertEquals(cart.getItemCount(),1,"Cart should contain only one product");
        Assert.assertTrue(cart.containsProduct("Sauce Labs Bolt T-Shirt"),"Bolt T-Shirt is not present");
        Assert.assertFalse(cart.containsProduct("Sauce Labs Backpack"),"Removed Backpack is present again");
        // Checkout
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
        // Verify only Bolt T-Shirt
        Assert.assertTrue(checkout.isItemDisplayed("Sauce Labs Bolt T-Shirt"), "Bolt T-Shirt is not present in Overview");
        // Verify totals
        Checkout_OverviewPg ch = new Checkout_OverviewPg(driver);
        Assert.assertEquals(ch.getItemTotal(),"Item total: $15.99","Item total is incorrect");
        Assert.assertEquals(ch.getTax(),"Tax: $1.28","Tax is incorrect");
        Assert.assertEquals(ch.getTotal(), "Total: $17.27", "Total is incorrect");
        // Finish
        ch.finish().click();
        // Verify confirmation
        Checkout_complete cc = new Checkout_complete(driver);
        Assert.assertEquals(cc.getcompletetext().getText(),"Checkout: Complete!");
        Reporter.log("Removed product was not purchased and different product was successfully purchased-- S03 Passed",true );
        // Back Home
        cc.getbackHomeButton().click();
    }
}