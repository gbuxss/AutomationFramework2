package gbuxssCreation.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gbuxssCreation.ReusableComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css=".mb-3")
	List<WebElement> Products ;

	@FindBy(css="#toast-container")
	WebElement toast ;

	@FindBy(css=".ng-animating")
	WebElement spinner ;

	By selectedProduct= By.cssSelector("b");

	By addToCart= By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductList() {
		waitFormultipleObjectVisibiity(Products);
		return Products;

	}

	public WebElement getProductByName(String productName) {
		WebElement prod =getProductList().stream().filter(product-> 
		product.findElement(selectedProduct).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
		
	}

	public void addProductToCart(String productName) {
		WebElement prod =getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForObjectVisibiity(toast);
		waitForObjectInVisibiity(spinner);
	}
	
	
}
