
package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout_complete 
{
	WebDriver driver;
    

	public Checkout_complete(WebDriver driver) 
    { 
    	this.driver=driver;
    	PageFactory.initElements(driver, this); 
    }

    // declare
	
	@FindBy(className = "complete-header")
	private WebElement completeorder;
	
	@FindBy(id="back-to-products") 
	private WebElement backHomeButton;
    
	@FindBy(xpath = "//span[@class='title']") 
	private WebElement completetext;
	
	public WebElement getCompleteHeader() 
    { 
    	return completeorder;
    }
	
	public WebElement getbackHomeButton()
	{
		return backHomeButton;
	}
	
	public WebElement getcompletetext() 
    { 
    	return completetext;
    }

}
