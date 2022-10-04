package gbuxssCreation.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import gbuxssCreation.PageObject.CartPage;
import gbuxssCreation.PageObject.CheckOutPage;
import gbuxssCreation.PageObject.ConfirmPage;
import gbuxssCreation.PageObject.OrdersPage;
import gbuxssCreation.PageObject.ProductCataloguePage;
import gbuxssCreation.ReusableTests.BaseTest;
import gbuxssCreation.ReusableTests.Retry;

public class SubmitOrderTest extends BaseTest{


	//String productName= "ZARA COAT 3";
	//String countryName = "United States";
		
		@Test(dataProvider="SubmitOrderData")
		public void submitOrder(HashMap<String, String> input) throws IOException {
		
		String productName=input.get("productName");
		//login into the application
		ProductCataloguePage productCatObj =landingPageObj.loginApplication(input.get("email"), input.get("password"));
		productCatObj.addProductToCart(productName);
		CartPage cartPageObj =productCatObj.goToCartPage();
		
		//verify added item is in cart
		Boolean prodFound = cartPageObj.verifyCartProducts(productName);
		Assert.assertTrue(prodFound);
		CheckOutPage checkoutObj =cartPageObj.goToCheckOut();
		
		
		checkoutObj.getCountryName(input.get("countryName"));
		
		ConfirmPage confirmPageObj=checkoutObj.goToConfirmPage();
		
		Boolean confirmOrder=confirmPageObj.verifyOrderConfirm();
		Assert.assertTrue(confirmOrder);
		
		
	
	}
		@Test(dependsOnMethods={"submitOrder"}, dataProvider="VerifyOrderData",retryAnalyzer=Retry.class) 
		public void verifyOrders(HashMap <String, String> input) throws IOException  {
			ProductCataloguePage productCatObj =landingPageObj.loginApplication(input.get("email"), input.get("password"));
			
			OrdersPage orderPageObj=productCatObj.goToOrdersPage();
			Boolean prodFound =orderPageObj.verifyOrderProducts(input.get("productName"));
			Assert.assertTrue(prodFound);
			
		}
		@DataProvider(name="SubmitOrderData")
		public Object[][] getSubmitOrderData() throws IOException {
			//provides data to submitOrder and verifyOrder test cases
			
			List<HashMap<String,String>> data=convertJsonToHashMap(System.getProperty("user.dir")+"/src/test/java/gbuxssCreation/Data/SubmitOrderData.json");
			return new Object [][] {{data.get(0)},{data.get(1)}};			
		}
		
		@DataProvider(name="VerifyOrderData")
		public Object[][] getVerifyOrdersData() throws IOException {
			//provides data to submitOrder and verifyOrder test cases
			List<HashMap<String,String>> data=convertJsonToHashMap(System.getProperty("user.dir")+"/src/test/java/gbuxssCreation/Data/VerifyOrderData.json");
			return new Object [][] {{data.get(0)},{data.get(1)}};				
		}

}
