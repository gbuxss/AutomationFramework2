package gbuxssCreation.ReusableTests;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gbuxssCreation.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver ;
	public LandingPage landingPageObj; 

	public WebDriver initDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/gbuxssCreation/Resources/globalData.properties");
		prop.load(fis);

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();			
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--no-sandbox");
			co.addArguments("--disable-dev-shm-usage");
			//co.addArguments("headless");
			driver = new ChromeDriver(co);


		}else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			//invoke firefox code here

		}else if (prop.getProperty("browser").equalsIgnoreCase("edgr")) {

			//edge browser code
		}
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage lunchApplication() throws IOException {
		driver =initDriver();
		landingPageObj = new LandingPage(driver);
		landingPageObj.goToURL();
		return landingPageObj;

	}

	//method to read and covert json into Hashmap
	public List<HashMap<String,String>> convertJsonToHashMap(String path) throws IOException {

		//convert Json to string		
		String JsonContent= FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

		//Convert string to hashmap with jackson databind
		ObjectMapper map = new ObjectMapper();
		List<HashMap<String,String>>	data= map.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;

	}

	public String takeScreenshot(String testcaseName, WebDriver driver) throws IOException {
		//adding timestamp to avoid screenshot overriding
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination= new File(System.getProperty("user.dir")+"/Media/"+testcaseName+timestamp+".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"/Media/"+testcaseName+timestamp+".png";

	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException {
		//close driver
		driver.close();
	}



}
