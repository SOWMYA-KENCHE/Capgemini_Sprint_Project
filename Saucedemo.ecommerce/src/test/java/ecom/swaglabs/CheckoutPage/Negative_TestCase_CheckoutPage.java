package ecom.swaglabs.CheckoutPage;

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
import genericUtility.ScreenShotUtility;
import objRepo.Cart_pg;
import objRepo.Checkout_OverviewPg;
import objRepo.Checkout_pg;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class Negative_TestCase_CheckoutPage extends BaseClassSauceDemo
{
	// functional - negative
	@Test(groups =  {"functional","negative"} )
	public void checkoutPageInvalidDataOfAllData() throws IOException
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		// add product
		Product_pg product = new Product_pg(driver);
		product.getBikeLightAdd().click();
		product.getcarticon().click();
		// checkout button
		Cart_pg cart = new Cart_pg(driver);
		cart.getcheckoutbutton().click();
		// checkoutpage
		Checkout_pg checkout = new Checkout_pg(driver);
		checkout.getcontinueCheckout().click();
		ScreenShotUtility ss = new ScreenShotUtility();
		ss.takeScreenshot(driver, "F01_Invalid_Checkout_Information");
		Assert.assertEquals(checkout.getErrorMessage(), "Error: First Name is required");
		Reporter.log("Checkout page is open with invalid information -- F01 Passed",true);
	}
	
	@Test(groups =  {"functional","negative"} )
	public void checkoutpageinvalidDataOfOnlyOneField() throws IOException
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		// add product
		Product_pg product = new Product_pg(driver);
		product.getBikeLightAdd().click();
		product.getcarticon().click();
		// checkout button
		Cart_pg cart = new Cart_pg(driver);
		cart.getcheckoutbutton().click();
		DataFromExcelFile data = new DataFromExcelFile();
		String firstname = data.getDataFromExcel("checkoutdata", 1, 0);
		String lastname = data.getDataFromExcel("checkoutdata", 1, 1);
		DataFormatter df = new DataFormatter();
		FileInputStream file1 = new FileInputStream("./src/test/resources/saucedemotestdata.xlsx");
        Workbook workb= WorkbookFactory.create(file1);
		String zipcode = df.formatCellValue(workb.getSheet("checkoutdata").getRow(1).getCell(4));
		Checkout_pg checkout = new Checkout_pg(driver);
		checkout.enterInformation(firstname, lastname, zipcode);
		// checkoutpage
		checkout.getcontinueCheckout().click();
		ScreenShotUtility ss = new ScreenShotUtility();
		ss.takeScreenshot(driver, "I02_zipcode_missing");
		Assert.assertEquals(checkout.getErrorMessage(), "Error: Postal Code is required");
		Reporter.log("Checkout page is open with invalid zipcode information -- I02 Passed",true);
	}
	
	// system- negative 
	
	@Test(groups = {"system","negative"})
	public void cannotCompleteWithCancel() throws IOException
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		Product_pg product = new Product_pg(driver);
		product.getcarticon().click();
		// checkout button
		Cart_pg cart = new Cart_pg(driver);
		cart.getcheckoutbutton().click();
		// checkoutpage
		Checkout_pg checkout = new Checkout_pg(driver);
		DataFromExcelFile data = new DataFromExcelFile();
		String firstname = data.getDataFromExcel("checkoutdata", 1, 0);
		String lastname = data.getDataFromExcel("checkoutdata", 1, 1);
		DataFormatter df = new DataFormatter();
		FileInputStream file1 = new FileInputStream("./src/test/resources/saucedemotestdata.xlsx");
        Workbook workb= WorkbookFactory.create(file1);
		String zipcode = df.formatCellValue(workb.getSheet("checkoutdata").getRow(1).getCell(2));
		checkout.enterInformation(firstname, lastname, zipcode);
		checkout.getcontinueCheckout().click();
		Checkout_OverviewPg ch_overview = new Checkout_OverviewPg(driver);
		ch_overview.cancelProduct().click();
		Assert.assertEquals(driver.getCurrentUrl().contains("inventory"),true);
		Reporter.log("User cannot complete order after clicking cancel on checkout overview page -- S01 Passed",true);
	}

}
