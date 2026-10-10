package ecom.swaglabs.Login;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Home_pg;
import objRepo.Login_pg;
import objRepo.Product_pg;

public class Functional_testcase_login extends BaseClassLogin {

	
    @Test(groups = "functional")
    public void validLoginCredentials() throws InterruptedException, IOException
    {
    	
    	Login_pg login = new Login_pg(driver);
        
        DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Sheet1",1, 0);
        String password = data.getDataFromExcel("Sheet1",1, 1);
        // Enter valid username
        login.getUsername(username);
        // Enter valid password
        login.getPassword(password);
        // Click Login
        login.getLogin_button();
        // Verify Products page
        Product_pg pr = new Product_pg(driver);
        Assert.assertEquals(pr.gettitle(), "Products");
        // Verify URL
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        Assert.assertTrue(pr.getcarticon().isDisplayed());
        Reporter.log("valid login is succesfully logged in -- F01 Passed",true);
    }
    
    
    
    @DataProvider
    public Object[][] validusers() throws EncryptedDocumentException, IOException
    {
    	FileInputStream file = new FileInputStream("./src/test/resources/saucedemotestdata.xlsx");
    	
    	Workbook wb = WorkbookFactory.create(file);
    	Sheet sh = wb.getSheet("Sheet1");
    	int rowcount =sh.getLastRowNum();
    	int cellcount = sh.getRow(0).getLastCellNum();
    	
    	Object[][] obj = new Object[rowcount][cellcount];
    	
    	for(int row =1;row<=rowcount;row++)
    	{
    		obj[row -1][0]= sh.getRow(row).getCell(0).getStringCellValue();
    		obj[row -1][1]= sh.getRow(row).getCell(1).getStringCellValue();
    	}
    	wb.close();
    	file.close();
    	return obj;
    }
    
    @Test(dataProvider ="validusers",groups = "functional")
    public void differentLoginCredentials(String username,String Password) throws InterruptedException, IOException 
    {
        Login_pg login = new Login_pg(driver);
        // Enter username
        login.getUsername(username);
        // enter password
        login.getPassword(Password);
        // login button
        login.getLogin_button();
        // Verify error
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),"Valid user was not redirected to the Products page");
        Reporter.log("valid users logged in -- F02 Passed",true);
    }

}