package gbuxssCreation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gbuxssCreation.ReusableComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".list-group button:first-of-type")
	WebElement countryselected ;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountryOption ;	
	
	@FindBy(css=".action__submit")
	WebElement BtnSubmit ;
	
	public void getCountryName(String countryName) {
		selectCountryOption.sendKeys(countryName);
		waitForObjectVisibiity(countryselected);
		countryselected.click();
		
	}
	
	public ConfirmPage goToConfirmPage() {
		scrollTo();
		BtnSubmit.click();
		ConfirmPage confirmPageObj = new ConfirmPage(driver);
		return confirmPageObj;
		
	}
	
	
}
