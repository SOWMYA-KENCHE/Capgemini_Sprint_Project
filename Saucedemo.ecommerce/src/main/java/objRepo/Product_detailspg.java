package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_detailspg {
	
	// initialization

	WebDriver driver;
	public Product_detailspg(WebDriver driver)
	{
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
		
	// declare 

	@FindBy(id = "add-to-cart")
	private WebElement add;
	
	@FindBy(id= "back-to-products")
	private WebElement goback;
	
	public WebElement getadd()
	{
		return add;
	}

	
    public String getProductName() 
    {
    	return driver.findElement(By.className("inventory_details_name")).getText(); 
    }
    public WebElement goBack()
    { 
    	return goback; 
    }

}
