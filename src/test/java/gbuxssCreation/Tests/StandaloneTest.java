package gbuxssCreation.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		
		//initiate driver
		WebDriver driver ;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//navigate to URL
		
		driver.get("https://rahulshettyacademy.com/client");
		//login into the application
		driver.findElement(By.id("userEmail")).sendKeys("jeevandhamala095@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Nepal@12345");
		driver.findElement(By.id("login")).click();
		
		//adding wait conditions to let page load all the objects
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		
		//select item and add to cart
		
		List<WebElement> products=driver.findElements(By.cssSelector(".card"));
		WebElement prod =products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
		//adding the items to cart	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//adding wait for spinner and "added to cart" msg elements 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//goto cart page
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));

		//verify added item is in cart
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean prodFound =cartProducts.stream().anyMatch(cartProd-> cartProd.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(prodFound);
		
		//goto checkout page
		Actions mouseAction= new Actions(driver);
		mouseAction.scrollByAmount(0, 25).build().perform();
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		//select country
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("United States");
		//mouseAction.scrollByAmount(0, 25).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group button:first-of-type")));

		driver.findElement(By.cssSelector(".list-group button:first-of-type")).click();
		
		mouseAction.scrollByAmount(0, 100).build().perform();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//goto confirmation page and validate order is placed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));

		Boolean confirmOrder=driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(confirmOrder);
		
		//close driver
		driver.close();

	}

}
