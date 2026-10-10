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
import objRepo.Cart_pg;
import objRepo.Checkout_pg;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class Functional_Testcase_CheckoutPage extends BaseClassSauceDemo
{
	@Test(groups = "functional")
	public void checkoutPageVisible()
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		// add product
		Product_pg product = new Product_pg(driver);
		product.getBackpackAdd().click();
		// cart
		product.getcarticon().click();
		// checkout button
		Cart_pg cart = new Cart_pg(driver);
		cart.getcheckoutbutton().click();
		Assert.assertEquals(driver.getCurrentUrl().contains("checkout"), true);
		Reporter.log("checkout page is displayed -- F01 Passed",true);
	}
	
	@Test(groups = "functional")
	public void checkoutPageVisibleAfterData() throws IOException
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		// add product
		Product_pg product = new Product_pg(driver);
		product.getBikeLightAdd().click();
		product.getcarticon().click();
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
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
		Reporter.log("Checkout page is open with valid information -- F02 Passed",true);
	}

}
