package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.ExplicitWait;

public class Checkout_pg {
	
    WebDriver driver;
    
    public Checkout_pg(WebDriver driver) 
    { 
    	this.driver=driver;
    	this.driver=driver; PageFactory.initElements(driver, this); 
    }
    
    // declare
    
    @FindBy(id="first-name") 
    private WebElement firstName;
    
    @FindBy(id="last-name") 
    private WebElement lastName;
    
    @FindBy(id="postal-code")
    private WebElement postalCode;
    
    @FindBy(id="continue") 
    private WebElement continueButton;
    
    @FindBy(id="cancel") 
    private WebElement checkoutinfocancel;
    
    @FindBy(css="h3[data-test='error']") 
    private WebElement errorMessage;
    
    @FindBy(xpath = "//span[@class='title']")
    private WebElement checkoutinfo;
    
    public WebElement getcheckoutheaderinfo()
    {
    	return checkoutinfo;
    }
    public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getPostalCode() {
		return postalCode;
	}

	public void enterInformation(String first,String last,String zip)
    {
		ExplicitWait.waitForElementToVisible(driver, firstName).clear();
    	firstName.sendKeys(first);
    	lastName.clear();
    	lastName.sendKeys(last); 
    	postalCode.clear();
    	postalCode.sendKeys(zip); 
    }
	
    public WebElement getcontinueCheckout() 
    {
    	return continueButton;
    }
    
    public String getErrorMessage() 
    { 
    	return errorMessage.getText(); 
    }
    
    public WebElement getcheckoutinfocancel()
    {
    	return checkoutinfocancel;
    }
    
    public boolean isItemDisplayed(String name) 
    { 
    	return driver.findElements(By.xpath("//*[contains(@class,'inventory_item_name') and normalize-space()='"+name+"']")).size()>0; 
    }
    
}
