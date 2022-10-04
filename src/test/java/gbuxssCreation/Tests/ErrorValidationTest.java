package gbuxssCreation.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import gbuxssCreation.PageObject.CartPage;
import gbuxssCreation.PageObject.ProductCataloguePage;
import gbuxssCreation.ReusableTests.BaseTest;
import gbuxssCreation.ReusableTests.Retry;

public class ErrorValidationTest extends BaseTest{

	//String productName = "zara coat 3";

	@Test(groups= {"errorvalidation"},dataProvider="loginValidationData")
	public void loginValidation(HashMap<String, String> input) throws IOException {

		//login into the application with invalid credentials
		@SuppressWarnings("unused")
		ProductCataloguePage productCatObj =landingPageObj.loginApplication(input.get("email"), input.get("password"));
		String actualString =landingPageObj.loginValidation();
		AssertJUnit.assertEquals(actualString, "Incorrect email or password.");

	}

	@Test(groups= {"errorvalidation"}, dataProvider="validatecorrectPrdouctData")
	public void validatecorrectProdct(HashMap<String, String> input) {


		//negative test cases to verify that random product is not present in cart
		ProductCataloguePage productCatObj =landingPageObj.loginApplication(input.get("email"), input.get("password"));
		productCatObj.addProductToCart(input.get("productName"));
		CartPage cartPageObj =productCatObj.goToCartPage();		
		//verify added item is in cart		
		Boolean prodFound = cartPageObj.verifyCartProducts("ZARA COAT 33");
		Assert.assertFalse(prodFound);
	}

	@DataProvider(name="loginValidationData")
	public Object[][] loginValidationData() throws IOException {
		//provides data to submitOrder and verifyOrder test cases
		List<HashMap<String,String>> data=convertJsonToHashMap(System.getProperty("user.dir")+"/src/test/java/gbuxssCreation/Data/loginValidationData.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};				
	}

	@DataProvider(name="validatecorrectPrdouctData")
	public Object[][] validatecorrectPrdouctData() throws IOException {
		//provides data to submitOrder and verifyOrder test cases
		List<HashMap<String,String>> data=convertJsonToHashMap(System.getProperty("user.dir")+"/src/test/java/gbuxssCreation/Data/ValidateCorrectProductData.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};			
	}

}
