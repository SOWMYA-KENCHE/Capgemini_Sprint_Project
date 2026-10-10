package ecom.swaglabs.Shopping_cart;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClassSauceDemo;
import genericUtility.DataFromExcelFile;
import objRepo.Login_pg;
import objRepo.Cart_pg;
import objRepo.Checkout_OverviewPg;
import objRepo.Checkout_pg;
import objRepo.Home_pg;
import objRepo.Product_detailspg;
import objRepo.Product_pg;

public class Integration_Testcase_cart extends BaseClassSauceDemo {

    @Test(groups = "integration")
    public void VerifyCartBadgeAndCartRows() throws InterruptedException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        // Add two products from Products page
        pr.getBackpackAdd().click();
        pr.getBikeLightAdd().click();
        // badge count
        Assert.assertEquals(pr.getCartBadgeCount(),2,"Cart badge count is incorrect");
        pr.getcarticon().click();
        Assert.assertEquals(cart.getItemCount(), 2,"Cart row count does not match badge count");
        Reporter.log("Cart badge count matches Cart row count -- I01 Passed",true);
    }

    @Test(groups = "integration")
    public void VerifyProductDetailsInCart() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        pr.getBackpackAdd().click();
        // Open Cart
        pr.getcarticon().click();
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"),"Backpack is not present in Cart");
        Assert.assertEquals(cart.getProductPrice("Sauce Labs Backpack"),"$29.99","Product price is incorrect");
        Assert.assertEquals(cart.getProductDescription("Sauce Labs Backpack"),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
         "Product description is incorrect");
        Reporter.log("Product name, description and price are correctly displayed in Cart -- I02 Passed",true);
    }

    @Test(groups = "integration")
    public void VerifyProductDetailsToCart() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        Product_detailspg details = new Product_detailspg(driver);
        pr.getopenProductDetailsname().click();
        Assert.assertEquals(details.getProductName(),"Sauce Labs Backpack","Wrong product details page opened");
        details.getadd().click();
        Assert.assertEquals(pr.getCartBadgeCount(),1,"Cart badge was not updated");
        pr.getcarticon().click();
        // Verify product in Cart
        Assert.assertTrue(cart.containsProduct("Sauce Labs Backpack"),"Product added from Product Details is not present in Cart");
        Reporter.log("Product Details page successfully updated Cart-- I03 Passed",true);
    }

    @Test(groups = "integration")
    public void VerifyRemoveItemUpdatesProductsPage() throws InterruptedException 
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        // Add Backpack
        pr.getBackpackAdd().click();
        pr.getcarticon().click();
        cart.removebutton().click();
        // Continue Shopping click
        cart.continueShopping().click();
        Assert.assertTrue(pr.addtocartbuttondisplay().isDisplayed(),"Add to Cart button is not displayed after removing product");
        Reporter.log("Removing item resets Products page button -- I04 Passed",true);
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {

        DataFromExcelFile data = new DataFromExcelFile();

        String username = data.getDataFromExcel("Sheet1", 1, 0);
        String password = data.getDataFromExcel("Sheet1", 1, 1);

        System.out.println("Username from Excel: " + username);
        System.out.println("Password from Excel: " + password);
        return new Object[][] {
            {username, password}
        };
    }
    
    
    @Test(dataProvider = "loginData",groups = "integration")
    public void VerifyCartAfterLogoutAndLogin(String username,String password) throws Exception
    {
    	
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        // Add product
        pr.getBackpackAdd().click();
        Assert.assertEquals(pr.getCartBadgeCount(),1,"Product was not added");
        // Logout
        Home_pg home = new Home_pg(driver);
        home.getMenu().click();
        home.getLogout().click();
        // Login again
        Login_pg login = new Login_pg(driver);
        login.getUsername(username);
        login.getPassword(password);
        login.getLogin_button();
        // Open cart
        pr.getcarticon().click();
        boolean productPresent = cart.containsProduct("Sauce Labs Backpack");
        Reporter.log("I05 Passed -- Cart product present after logout and again login: " + productPresent,true);
    }

    @Test(groups = "integration")
    public void VerifyCancelCheckoutPreservesCart() throws InterruptedException, IOException
    {
    	//verify login
    	Login_pg l = new Login_pg(driver);
    	l.verify();
    	Reporter.log("User logged in successfully with valid credentials", true);
    	// product page
        Product_pg pr = new Product_pg(driver);
        Cart_pg cart = new Cart_pg(driver);
        Checkout_pg checkout = new Checkout_pg(driver);
        // Add Backpack
        pr.getBackpackAdd().click();
        // Open Cart
        pr.getcarticon().click();
        Assert.assertEquals(cart.getItemCount(),1,"Product was not added to Cart");
        // Click Checkout
        cart.getcheckoutbutton().click();
        // Enter checkout information
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
        ch.cancelProduct().click();
        Assert.assertEquals(pr.getCartBadgeCount(),1,"Cart item was lost after Cancel");
        Reporter.log("Cancel from Checkout preserves Cart items-- I06 Passed",true);
    }

}