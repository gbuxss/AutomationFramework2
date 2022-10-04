package gbuxssCreation.PageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gbuxssCreation.ReusableComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts ;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn ;	
	
	public Boolean verifyCartProducts(String productName) {
		waitFormultipleObjectVisibiity(cartProducts);
		Boolean prodFound =cartProducts.stream().anyMatch(cartProd-> cartProd.getText().equalsIgnoreCase(productName));
		if (prodFound.equals(null)){
			return false;
		}else {
			return prodFound;
		}
		
		
	}
	
	public CheckOutPage goToCheckOut() {
		scrollTo();
		checkOutBtn.click();
		CheckOutPage checkoutObj = new CheckOutPage(driver);
		return checkoutObj;
	}
	
	
}
