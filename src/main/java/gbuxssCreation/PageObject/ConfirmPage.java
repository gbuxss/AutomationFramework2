package gbuxssCreation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gbuxssCreation.ReusableComponents.AbstractComponents;

public class ConfirmPage extends AbstractComponents {
	WebDriver driver;

	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css=".hero-primary")
	WebElement verificationText ;

	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountryOption ;	

	@FindBy(css=".action__submit")
	WebElement BtnSubmit ;

	public Boolean verifyOrderConfirm() {
		waitForObjectVisibiity(verificationText);
		Boolean confirmOrder=verificationText.getText().equalsIgnoreCase("Thankyou for the order.");
		return confirmOrder;

	}



}
