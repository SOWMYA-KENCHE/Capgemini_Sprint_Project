package objRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Cart_pg {
	
	WebDriver driver;
	public Cart_pg(WebDriver driver) 
    { 
    	this.driver=driver;
    	PageFactory.initElements(driver, this); 
    }
    // declare
	
	@FindBy(id="checkout") 
	private WebElement checkoutButton;
	
    @FindBy(id="continue-shopping") 
    private WebElement continueShoppingButton;
    
    @FindBy(className="shopping_cart_badge") 
    private WebElement cartBadge;
    
    @FindBy(className="title") 
    private WebElement title;
    
    @FindBy(xpath = "//div[@class='inventory_item_name']") 
	private WebElement productname;
    
    @FindBy(xpath = "//div[@class='inventory_details_name large_size']")
    private WebElement getbigproductname;
    
    @FindBy(className = "inventory_item_price")
    private WebElement productprice;
    
    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement removebutton;
	
    
    public String getbigproductname()
	{
		return getbigproductname.getText();
	}
    
	public String getproductname()
	{
		return productname.getText();
	}
	
	public WebElement getproduct()
	{
		return productname;
	}
	
	public WebElement removebutton()
	{
		return removebutton;
	}
    
	
	public int getItemCount()
	{
		return driver.findElements(By.className("cart_item")).size(); 
	}
	
	public int getBadgeCount() 
	{ 
		try 
		{ 
			return Integer.parseInt(cartBadge.getText()); 
		} catch(Exception e) 
		{
			return 0; 
		}
	}
	public boolean isBadgeDisplayed() 
	{ 
		return driver.findElements(By.className("shopping_cart_badge")).size()>0; 
	}
	
	public boolean containsProduct(String productName) 
	{
	    for(WebElement e : driver.findElements(By.className("inventory_item_name"))) 
	    {
	        if(e.getText().equals(productName)) 
	        {
	            return true;
	        }
	    }

	    return false;
	}
	
	
	public String getProductPrice(String productName)
	{
	        List<WebElement> rows=driver.findElements(By.className("cart_item"));
	        for(WebElement row:rows) 
	        {
	            if(row.findElement(By.className("inventory_item_name")).getText().equals(productName))
	                return row.findElement(By.className("inventory_item_price")).getText();
	        }
	        return null;
	}
	
	public String getProductDescription(String productName) 
	{
	        List<WebElement> rows=driver.findElements(By.className("cart_item"));
	        for(WebElement row:rows) {
	            if(row.findElement(By.className("inventory_item_name")).getText().equals(productName))
	                return row.findElement(By.className("inventory_item_desc")).getText();
	        }
	        return null;
	 }
	 public List<String> getProductNames() 
	 {
	        List<String> names=new ArrayList<>();
	        for(WebElement e:driver.findElements(By.className("inventory_item_name"))) 
	        	names.add(e.getText());
	        return names;
	 }
	 
	 public WebElement continueShopping() 
	 { 
		 return continueShoppingButton;
	  }
	 
	  public WebElement getcheckoutbutton()
	  { 
		  return checkoutButton;
	  }
	  
	  public String getTitle()
	  { 
		  return title.getText(); 
	  }
	  
	  public void clickProduct(String productName) 
	  {
	        for(WebElement e:driver.findElements(By.className("inventory_item_name"))) 
	        	if(e.getText().equals(productName)) 
	        	{ 
	        		e.click(); 
	        		return; 
	        	}
	        throw new RuntimeException("Product not found in cart: "+productName);
	    }
}
