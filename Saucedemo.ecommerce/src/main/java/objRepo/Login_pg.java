package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login_pg 
{
	// initialization

	WebDriver driver;
	public Login_pg(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(id = "user-name")
	private WebElement username;
	
	@FindBy(id= "password")
	private WebElement password;
	
	@FindBy(id = "login-button")
	private WebElement login_button;
	
    @FindBy(css="h3[data-test='error']") 
    private WebElement errorMessage;
    
	// getters
	
    public WebElement username() {
		return username;
	}
    
    public WebElement password() {
		return password;
	}
    
    public WebElement login_button() {
		return login_button;
	}
    
	public void getUsername(String value) {
		username.clear(); 
		username.sendKeys(value);
	}

	public void getPassword(String value) {
		password.clear(); 
		password.sendKeys(value);
	}

	public void getLogin_button() throws InterruptedException {
		login_button.click();
		Thread.sleep(1000);
	}
	
	public void verify()
	{
		String text = driver.findElement(By.xpath("//span[@class='title']")).getText();
		Assert.assertEquals(text, "Products");
	}
	
	public String getErrorMessage() 
	{ 
		return errorMessage.getText();
	}
	public String url()
	{
		 return driver.getCurrentUrl();
	}
}
