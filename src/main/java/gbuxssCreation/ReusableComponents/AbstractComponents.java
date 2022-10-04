package gbuxssCreation.ReusableComponents;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gbuxssCreation.PageObject.CartPage;
import gbuxssCreation.PageObject.OrdersPage;

public class AbstractComponents {
	private WebDriver driver;

	public AbstractComponents(WebDriver driver) {

		this.driver= driver;
		PageFactory.initElements(driver, this);


	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartPage;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement myOrders;
	
	
	public void waitForObjectVisibiity(WebElement selector) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(selector));
	}

	public void waitForObjectInVisibiity(WebElement selector) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(selector));
	}

	public void waitFormultipleObjectVisibiity(List<WebElement> selector) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(selector));

	}
	
	public void scrollTo() {
		Actions mouseAction= new Actions(driver);
		mouseAction.scrollByAmount(0, 50).build().perform();
		
	}

	public CartPage goToCartPage() {
		cartPage.click();
		CartPage cartPageObj =new CartPage(driver);
		return cartPageObj;

	}
	
	public OrdersPage goToOrdersPage() {
		waitForObjectVisibiity(myOrders);
		myOrders.click();
		OrdersPage orderPageObj= new OrdersPage(driver);
		return orderPageObj;
		
	}

}
