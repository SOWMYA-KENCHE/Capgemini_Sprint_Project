package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout_OverviewPg 
{
	WebDriver driver;
    

	public Checkout_OverviewPg(WebDriver driver) 
    { 
    	this.driver=driver;
    	PageFactory.initElements(driver, this); 
    }

    // declare
	
	 @FindBy(id="cancel") 
	 private WebElement checkoutinfocancel;
	 
	@FindBy(xpath = "//div[@class='inventory_item_name']") 
	private WebElement productname;
	
    @FindBy(id="finish") 
    private WebElement finishButton;
    
	
    public WebElement finish()
    { 
    	return finishButton; 
    }
	
	public String getproductnamecheckout()
	{
		return productname.getText();
	}
	 
	    public WebElement cancelProduct()
	    {
	    	return checkoutinfocancel;
	    }
	
	 
	 	public String getItemTotal()
	    { 
	    	return driver.findElement(By.className("summary_subtotal_label")).getText(); 
	    }
	    
	    public String getTax()
	    { 
	    	return driver.findElement(By.className("summary_tax_label")).getText();
	    }
	    public String getTotal() 
	    { 
	    	return driver.findElement(By.className("summary_total_label")).getText(); 
	    }
	    
	    
	    public String gettitle()
	    {
	    	return driver.findElement(By.className("title")).getText();
	    }
	    
	    public String getcheckoutoverviewproductcount()
	    {
	    	return driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
	    }
	

}
