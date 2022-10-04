package gbuxssCreation.PageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gbuxssCreation.ReusableComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".ng-star-inserted td:nth-child(3)")
	List<WebElement> orderProducts ;
	
		
	public Boolean verifyOrderProducts(String productName) {
		waitFormultipleObjectVisibiity(orderProducts);
		Boolean prodFound =orderProducts.stream().anyMatch(cartProd-> cartProd.getText().equalsIgnoreCase(productName));
		return prodFound;
		
	}
	
	
	
}
