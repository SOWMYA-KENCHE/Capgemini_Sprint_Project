package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Home_pg
{
	// initialization

	WebDriver driver;
	public Home_pg(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// declare 
	
	@FindBy(id ="react-burger-menu-btn")
	private WebElement menu;
	
	@FindBy(id = "logout_sidebar_link")
	private WebElement logout;
	
	@FindBy(id = "about_sidebar_link")
	private WebElement about;
	
	@FindBy(id="reset_sidebar_link") 
	private WebElement resetAppState;
	
	@FindBy(id="react-burger-cross-btn") 
	private WebElement closeButton;
	
	@FindBy(id="inventory_sidebar_link") 
	private WebElement allitems;
	
	@FindBy(id="dynamic_catalog_sidebar_link") 
	private WebElement dynamic_catalog;
	
	public WebElement getdynamiccatalog()
	{
		return dynamic_catalog;
	}
	
	public WebElement getallitems()
	{
		return allitems;
	}
	
	public WebElement getMenu()
	{
		return menu;
	}
	
	public WebElement getabout()
	{
		return about;
	}
	
	public WebElement getLogout()
	{
		return logout;
	}
	
    public WebElement resetAppState()
    { 
    	return resetAppState; 
    }
    
    public WebElement closeMenu()
    {
    	return closeButton;
    }
    
    public void LoginPageCheck() 
    {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),"Unexpected URL after accessing Cart without login");
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),"User was not redirected to application login" );
    }
    public boolean menudisplayed()
    {
    	 return menu.isDisplayed();
    }
	
}
