package gbuxssCreation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gbuxssCreation.ReusableComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement username;
	
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement BtnLogin;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginToaster;
	
	public ProductCataloguePage loginApplication(String userEmail, String userPassword) {
		username.sendKeys(userEmail);
		password.sendKeys(userPassword);
		BtnLogin.click();
		ProductCataloguePage productCatObj = new ProductCataloguePage(driver);
		return productCatObj;
		
	}
	
	public void goToURL() {
		//navigate to URL
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String loginValidation() {
		
		waitForObjectVisibiity(loginToaster);
		return loginToaster.getText();
		
		//get text and return   .ng-tns-c4-15.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	}
	

}
