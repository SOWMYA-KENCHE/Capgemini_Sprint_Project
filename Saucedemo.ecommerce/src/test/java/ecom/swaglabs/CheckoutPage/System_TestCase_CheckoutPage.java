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
import objRepo.Checkout_OverviewPg;
import objRepo.Checkout_complete;
import objRepo.Checkout_pg;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class System_TestCase_CheckoutPage extends BaseClassSauceDemo 
{
	@Test(groups = "system")
	public void fullCompleeteOrder() throws IOException
	{
		// verify login
		Login_pg login = new Login_pg(driver);
		login.verify();
		Reporter.log("User is successfully logged in with valid credentials",true);
		// add product
		Product_pg product = new Product_pg(driver);
		product.getBikeLightAdd().click();;
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
		ch_overview.finish().click();
		// complete
		Checkout_complete cc = new Checkout_complete(driver);
		Assert.assertEquals(cc.getCompleteHeader().getText(),"Thank you for your order!");
		Reporter.log("complete product purchase is done  -- S01 Passed",true);
	}
	

}
