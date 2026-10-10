package objRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Product_pg 
{
	
    WebDriver driver;
    

	public Product_pg(WebDriver driver) 
    { 
    	this.driver=driver;
    	PageFactory.initElements(driver, this); 
    }

    // declare
    
	@FindBy(className="shopping_cart_link") 
    private WebElement cartIcon;
    
    @FindBy(className="shopping_cart_badge") 
    private WebElement cartBadge;
    
    @FindBy(css="select.product_sort_container")
    private WebElement sortDropdown;
    
    @FindBy(id="add-to-cart-sauce-labs-backpack") 
    private WebElement backpackAdd;
    
    @FindBy(id="add-to-cart-sauce-labs-bike-light") 
    private WebElement bikeLightAdd;
    
    @FindBy(id="add-to-cart-sauce-labs-bolt-t-shirt") 
    private WebElement boltTshirtAdd;
    
    @FindBy(id="add-to-cart-sauce-labs-fleece-jacket") 
    private WebElement fleeceAdd;
    
    @FindBy(id="add-to-cart-sauce-labs-onesie") 
    private WebElement onesieAdd;
    
    @FindBy(id="add-to-cart-test.allthethings()-t-shirt-(red)") 
    private WebElement redTshirtAdd;
    
	@FindBy(xpath = "//div[@class='inventory_item_name']") 
	private WebElement productname;
	
	@FindBy(xpath = "//div[@class='inventory_item_name ']") 
	private WebElement ztoaproductname;
	
	 @FindBy(xpath = "//button[text()='Remove']")
	 private WebElement removebutton;
		
	 @FindBy(xpath = "//button[text()='Add to cart']" )
	 private WebElement addtocartbutton;
		
	 @FindBy(id = "item_4_img_link")
	 private WebElement openProductDetailsname;
		
	 public WebElement getztoaproductname()
	{
			return ztoaproductname;
	}
	 
	 public WebElement getopenProductDetailsname()
	{
				return openProductDetailsname;
	}
	 
	 public WebElement addtocartbuttondisplay()
	{
			return addtocartbutton;
	}
	
		
	public WebElement removebutton()
	{
			return removebutton;
	}

    public WebElement getcarticon() 
    {
    	return cartIcon;
	}
    
    public WebElement getcartBadge() {
    	return cartBadge;
	}
    
	public WebElement getSortDropdown() {
		return sortDropdown;
	}

	public WebElement getBackpackAdd() {
		return backpackAdd;
	}

	public WebElement getBikeLightAdd() {
		return bikeLightAdd;
	}

	public WebElement getBoltTshirtAdd() {
		return boltTshirtAdd;
	}

	public WebElement getFleeceAdd() {
		return fleeceAdd;
	}

	public WebElement getOnesieAdd() {
		return onesieAdd;
	}

	public WebElement getRedTshirtAdd() {
		return redTshirtAdd;
	}
	
	public String getproductname()
	{
		return productname.getText();
	}
	
    public int getCartBadgeCount()
    {
        try 
        {
          return Integer.parseInt(cartBadge.getText()); 
        } catch (Exception e) 
        { 
        	return 0; 
        }
    }
    
    public void sortBy(String visibleText) 
    {
    	new Select(sortDropdown).selectByVisibleText(visibleText); 
    }
    
    public List<String> getProductNamesInDisplayOrder() 
    {
        List<String> names=new ArrayList<>();
        for(WebElement e:driver.findElements(By.className("inventory_item_name")))
        	names.add(e.getText());
        return names;
    }
    
    public List<String> getProductPricesInDisplayOrder()
    {
        List<String> prices=new ArrayList<>();
        for(WebElement e:driver.findElements(By.className("inventory_item_price"))) 
        	prices.add(e.getText());
        return prices;
    }
    public int getProductCount()
    { 
    	return driver.findElements(By.className("inventory_item")).size();
    }
    
    public String gettitle()
    {
    	return driver.findElement( By.className("title")).getText();
    }
}
    